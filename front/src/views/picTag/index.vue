<template>
  <div id="viewBox">
    <div id="toolbar" >
      <!--<div>-->
      <!--<fieldset>-->
      <!--<legend>Tools</legend>-->
      <!--<div id="toolsOptions">-->
      <!--<input type="radio" id="tools_pencil" value="pencil" v-model="tagType" ><label for="tools_pencil"><i class="fa fa-pencil fa-2x"></i></label>-->
      <!--<input type="radio" id="tools_eraser" value="eraser" v-model="tagType" ><label for="tools_eraser"><i class="fa fa-eraser fa-2x"></i></label>-->
      <!--<input type="radio" id="tools_rectangle" value="rectangle" v-model="tagType" ><label for="tools_rectangle"><i class="fa fa-stop fa-2x"></i></label>-->
      <!--<input type="radio" id="tools_circle" value="circle" v-model="tagType" ><label for="tools_circle"><i class="fa fa-circle fa-2x"></i></label>-->
      <!--<span>Picked: {{ tagType }}</span>-->
      <!--</div>-->
      <!--</fieldset>-->
      <!--</div>-->
      <el-card class="info">
        <div slot="header">{{'任务要求：' + ' ('+ChineseTagType + ')'}}</div>
        <div id="desc">{{mission.description}}</div>
      </el-card>
      <div id="func-bar">
        <fieldset>
          <legend>快捷工具</legend>
          <el-button-group id="two_btn">
            <el-tooltip effect="dark" content="清除所有" placement="top">
              <el-button id="tools_clear" @click="onClearCanvas" type="info" plain size="small" icon="el-icon-delete"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="撤销" placement="top">
              <el-button id="tools_undo" @click="undo" type="info" plain size="small" icon="el-icon-arrow-left"></el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="重做" placement="top">
              <el-button id="tools_redo" @click="redo" type="info" plain size="small" icon="el-icon-arrow-right"></el-button>
            </el-tooltip>
          </el-button-group>
        </fieldset>
      </div>
      <div id="setting-bar">
        <fieldset>
          <legend>线条设置</legend>
          <span style="margin-top: 6px;">width: </span>
          <el-select id="penWidth" v-model="lineSetting.width" size="small" style="width: 80px">
            <el-option
              v-for="i in 5"
              :key="i"
              :label="i+'px'"
              :value=i>
            </el-option>
          </el-select>
          <span>Color:</span>
          <el-color-picker size="small" v-model="lineSetting.color"></el-color-picker>
        </fieldset>
      </div>
      <el-card class="info">
        <div slot="header">图片总数：{{mission.total}}</div>
        <div>剩余：{{left}}</div>
      </el-card>
    </div>
    <div style="margin-bottom: 12px">
      <el-progress  :percentage="mission.done/mission.total*100" :stroke-width="6" :show-text="false" text-inside style="padding: 0"></el-progress>
    </div>
    <div id="main-content" style="display: flex;justify-content: space-around">
      <div class="arrow" style="display:flex;align-items: center">
        <el-button type="info" plain @click="onLast" icon="el-icon-d-arrow-left"><b>上一张</b></el-button>
      </div>
      <div id="picture" :style="styleObj" >
        <canvas id="myCanvas" width="720px" height="405px"  @mousedown="mouse_down($event)" @mouseup="mouse_up($event)" @mousemove="draw($event)"></canvas>
      </div>
      <div class="arrow" style="display:flex;align-items: center">
        <el-button type="info" plain @click="onNext"><b>下一张</b><i class="el-icon-d-arrow-right el-icon--right"></i></el-button>
      </div>
    </div>
    <div id="tag" style="display: flex;justify-content: center;margin-top: 4px" v-show="drawMode>2">
      <div style="width: 720px"><span><b>评注>>>：</b></span><el-input  required v-model="picTagObj.tag"  autofocus placeholder="请输入您对标记区域的评注" ></el-input></div>
    </div>
  </div>
</template>

<script>
  import { askForPic, saveTagAnswer, updateTagAnswer } from '@/api/mission'
  export default {
    name: 'pic-tag-view',
    data() {
      return {
        lineSetting: {
          color: '#000',
          width: 2
        },
        mission: {
          id: '',
          type: '',
          title: '',
          description: '',
          done: 0,
          total: 0
        },
        picTagObj: {
          authorId: 0,
          picData: '',
          picId: '',
          mark: '',
          tag: '',
          time: 0
        },
        canvasState: {
          startX: 0,
          startY: 0,
          isMouseDown: false
        },
        operatingHistory: {
          history: [],
          version: -1,
          endVersion: 0
        },
        doneImages: {
          index: -1,
          images: []
        },
        startAt: 0
      }
    },
    computed: {
      drawMode: function() {
        // if(this.mission.type == 'check') return 4//eslint-disable-line
        const dic = { 'border_mark': 1, 'frame_mark': 2, 'comment': 3, 'border_mc': 4, 'frame_mc': 5 }
        return dic[this.mission.type]
      },
      ChineseTagType: function() {
        const dic = { 'border_mark': '边界标记', 'frame_mark': '方框标记', 'comment': '整体评注', 'border_mc': '边界标记+评注', 'frame_mc': '方框标记+评注' }
        return dic[this.mission.type]
      },
      canvas: function() {
        return document.getElementById('myCanvas')
      },
      ctx: function() {
        return document.getElementById('myCanvas').getContext('2d')
      },
      styleObj: function() {
        return {
          backgroundImage: 'url("' + this.picTagObj.picData + '")'
        }
      },
      left() {
        return this.mission.total - this.mission.done
      }
    },
    methods: {
      // outputPicTag: function () {
      //   console.log(this.picTagObj)
      // },
      mouse_down: function(e) {
        this.canvasState.isMouseDown = true
        this.canvasState.startX = e.offsetX
        this.canvasState.startY = e.offsetY
        this.ctx.moveTo(this.canvasState.startX, this.canvasState.startY)
      },
      mouse_up: function() {
        this.ctx.beginPath()
        this.canvasState.isMouseDown = false
        this.addVersion()
      },
      onClearCanvas: function() {
        this.clearCanvas()
        this.addVersion()
      },
      onNext: function() {
        if (this.doneImages.index <= this.doneImages.images.length - 2) {
          this.updateTheAnswer()
          this.nextOld()
        } else {
          this.postTheAnswer()
          if (!this.nextOld()) this.getNewPic()
        }
        if (this.mission.done === this.mission.total) {
          this.$message({
            message: '您已经顺利完成此任务，可在已完成列表中查看。',
            type: 'success'
          })
          this.$router.push({ path: '/dashboard' })
        }
      },
      onLast: function() {
        if (!this.preOld()) {
          this.$message({
            message: '这已经是第一张图了',
            type: 'warning'
          })
        }
      },
      clearCanvas: function() {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
      },
      fillCanvasWith: function(url) {
        const img = new Image()
        img.src = url
        const vm = this
        img.onload = function() { vm.ctx.drawImage(img, 0, 0, vm.canvas.width, vm.canvas.height) }
      },
      draw: function(e) {
        this.ctx.strokeStyle = this.lineSetting.color
        this.ctx.lineWidth = this.lineSetting.width
        switch (this.drawMode) {
          case 4:
          case 1: this.drawPencil(e); break // 绘制方法
          case 2:
          case 5:this.drawRect(e); break
          case 3:break
        }
      },
      drawPencil: function(e) {
        if (this.canvasState.isMouseDown) {
          this.ctx.lineTo(e.offsetX, e.offsetY)
          this.ctx.stroke() // 调用绘制方法
        }
        // else {
        //   // this.ctx.beginPath()
        //   // this.ctx.moveTo(this.canvasState.startX, this.canvasState.startY)
        // }
      },
      drawRect: function(e) {
        if (this.canvasState.isMouseDown) {
          this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
          this.ctx.beginPath()
          this.ctx.strokeRect(this.canvasState.startX, this.canvasState.startY, e.offsetX - this.canvasState.startX, e.offsetY - this.canvasState.startY)
          this.fillCanvasWith(this.operatingHistory.history[this.operatingHistory.version])
        }
      },
      drawCircle: function(e) {
        if (this.canvasState.isMouseDown) {
          this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)
          this.ctx.beginPath()
          const rx = (e.offsetX - this.canvasState.startX) / 2
          const ry = (e.offsetY - this.canvasState.startY) / 2
          const r = Math.sqrt(rx * rx + ry * ry)
          this.ctx.arc(rx + this.canvasState.startX, ry + this.canvasState.startY, r, 0, Math.PI * 2) // 第5个参数默认是false-顺时针
          this.ctx.stroke()
          // loadLabel(history[version])
        }
      },
      //  版本控制模块
      addVersion: function() {
        this.operatingHistory.history[++this.operatingHistory.version] = this.canvas.toDataURL()
        this.operatingHistory.endVersion = this.operatingHistory.version
      },
      undo: function() {
        if (this.operatingHistory.version - 1 >= 0) {
          this.clearCanvas()
          this.fillCanvasWith(this.operatingHistory.history[--this.operatingHistory.version])
        }
      },
      redo: function() {
        if (this.operatingHistory.version + 1 <= this.operatingHistory.endVersion) {
          this.clearCanvas()
          this.fillCanvasWith(this.operatingHistory.history[++this.operatingHistory.version])
        }
      },
      restartVCS: function() {
        this.operatingHistory.history.splice(0, this.operatingHistory.history.length)
        this.operatingHistory.version = -1
        this.addVersion()
      },
      getNewPic: function() {
        const vm = this
        askForPic(this.mission.id, 'tag')
          .then(res => {
            vm.picTagObj = res.data
            vm.clearCanvas()
            vm.restartVCS()
            vm.oldImgAdd(vm.picTagObj)
            vm.startAt = new Date().getTime()
          })
        // request
        //   .get(serverAddr + '/requestnewlabel.do')
        //   .accept('text')
        //   .query({title: 'edge'})
        //   .end((err, res) => {
        //     if (!err) {
        //       vm.picTagObj.picData = res.text
        //       console.log(vm.picTagObj.picData)
        //       vm.clearCanvas()
        //       vm.picTagObj.tag = ''
        //       vm.restartVCS()
        //       vm.oldImgAdd(vm.picTagObj.picData)
        //     }
        //   })
      },
      oldImgAdd: function(picTagObj) {
        this.doneImages.images.push(picTagObj)
        this.doneImages.index++
      },
      nextOld: function() {
        this.startAt = new Date().getTime()
        if (this.doneImages.index <= this.doneImages.images.length - 2) {
          this.picTagObj = this.doneImages.images[++this.doneImages.index]
          this.clearCanvas()
          this.fillCanvasWith(this.picTagObj.mark)
          this.restartVCS()
          return true
        } else {
          return false
        }
      },
      preOld: function() {
        this.startAt = new Date().getTime()
        if (this.doneImages.index - 1 < 0) {
          return false
        } else {
          this.picTagObj = this.doneImages.images[--this.doneImages.index]
          this.clearCanvas()
          this.fillCanvasWith(this.picTagObj.mark)
          this.restartVCS()
          return true
        }
      },
      postTheAnswer: function() {
        this.mission.done++
        this.picTagObj.authorId = this.$store.state.user.id
        this.picTagObj.mark = this.canvas.toDataURL()
        this.picTagObj.time = new Date().getTime() - this.startAt
        return saveTagAnswer(this.mission.id, this.picTagObj.picId, this.picTagObj.mark, this.picTagObj.tag, this.picTagObj.time)
      },
      updateTheAnswer: function() {
        this.picTagObj.authorId = this.$store.state.user.id
        this.picTagObj.mark = this.canvas.toDataURL()
        this.picTagObj.time += new Date().getTime() - this.startAt
        updateTagAnswer(this.mission.id, this.picTagObj.picId, this.picTagObj.mark, this.picTagObj.tag, this.picTagObj.time).then()
      }
    },
    created: function() {
      this.mission = this.$store.state.mission.currentMission
      this.getNewPic()
    },
    mounted: function() {
      this.restartVCS()
    }
  }
</script>

<style scoped lang="scss">
  $main:#413c35;

  #toolbar{
    display: flex;
    justify-content: space-between;
    align-items: baseline;
    /*margin-bottom: 12px;*/
    /*border-bottom: gray 2px solid;*/
    padding: 4.8px 48px;
    background-color: $main;
    color: #fff;
    font-size: 14px;
  }
  .info{
    background-color: #1BBD9B;
    color:#fff;
    font-size: 14px;
    /*padding: 4px;*/
  }
  #picture{
    background-size: 100% 100%;
    background-repeat: no-repeat;
    width: 726px;
    height: 411px;
    border: #000 dashed 3px;
  }
  /*.arrow-btn{*/
    /*!*font-size: 38px;*!*/
    /*!*display: inline-block;*!*/
    /*width: 120px;*/
    /*height:48px;*/
    /*background-color: #061F20;*/
    /*color:white;*/
    /*border: 2px solid #fff;*/
    /*border-radius: 4.8px;*/
  /*}*/
  /*.arrow-btn:hover{*/
    /*background-color: #42b983;*/
  /*}*/
  /*.choice_btn{*/
    /*color: white;*/
    /*padding: 2px;*/
    /*margin: 8px 0;*/
    /*border: none;*/
    /*border-radius: 4px;*/
    /*cursor: pointer;*/
  /*}*/
  #_right{
    background-color: #4CAF50;
  }
  #_right:hover{
    background-color: #45a049;
  }
  #_wrong{
    background-color: #ff2d2d;
  }
  #_wrong:hover{
    background-color: #ce0000;
  }
  /*input[type=text]{*/
    /*font-size: 25px;*/
    /*width: 570px;*/
    /*border: 1px solid #ccc;*/
    /*border-radius: 4px;*/
    /*box-sizing: border-box;*/
    /*padding: 2px;*/
  /*}*/

</style>
