<template>
  <div id="viewBox">
    <div id="toolbar" >
      <el-card class="info">
        <div slot="header">{{'任务要求：' + ' ('+ChineseTagType + ')'}}</div>
        <div id="desc">{{mission.description}}</div>
      </el-card>
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
        <canvas id="myCanvas" width="720px" height="405px"></canvas>
      </div>
      <div class="arrow" style="display:flex;align-items: center">
        <el-button type="info" plain @click="onNext"><b>下一张</b><i class="el-icon-d-arrow-right el-icon--right"></i></el-button>
      </div>
    </div>
    <div id="tag" style="display: flex;justify-content: center;margin-top: 4px" v-show="drawMode>2">
      <div style="width: 720px"><span><b>评注>>>：</b></span><el-input  required v-model="picTagObj.tag"  disabled placeholder="无评注" ></el-input></div>
    </div>
    <div style="display: flex;justify-content: center">
      <p  style="font-size: 16px;"><b>请您对这张图片标注的准确性作出评估>>></b>
        <el-radio-group v-model="checkAnswer">
          <el-radio-button :label="true" :text-color="'#00ff00'" fill="#67C23A"><i class="el-icon-success"></i>准确无误</el-radio-button>
          <el-radio-button :label="false" :fill="'#F56C6C'"><i class="el-icon-error"></i>误差较大</el-radio-button>
        </el-radio-group>
      </p>
    </div>

  </div>
</template>

<script>
  import { askForPic, saveCheckAnswer, updateCheckAnswer } from '@/api/mission'
  export default {
    name: 'pic-tag-view',
    data() {
      return {
        mission: {
          id: '',
          type: '',
          title: '',
          description: '',
          done: 0,
          total: 0
        },
        checkAnswer: false,
        picTagObj: {
          authorId: 0,
          picData: '',
          picId: '',
          mark: '',
          tag: '',
          time: 0
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
      getNewPic: function() {
        const vm = this
        askForPic(this.mission.id, 'check')
          .then(res => {
            vm.picTagObj = res.data
            vm.clearCanvas()
            this.fillCanvasWith(vm.picTagObj.mark)
            vm.oldImgAdd(vm.picTagObj)
            this.startAt = new Date().getTime()
          })
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
          return true
        }
      },
      postTheAnswer: function() {
        this.mission.done++
        this.picTagObj.authorId = this.$store.state.user.id
        this.picTagObj.mark = this.canvas.toDataURL()
        this.picTagObj.time = new Date().getTime() - this.startAt
        return saveCheckAnswer(this.mission.id, this.picTagObj.picId, this.picTagObj.authorId, this.checkAnswer, this.picTagObj.time)
      },
      updateTheAnswer: function() {
        this.picTagObj.authorId = this.$store.state.user.id
        this.picTagObj.mark = this.canvas.toDataURL()
        this.picTagObj.time += new Date().getTime() - this.startAt
        updateCheckAnswer(this.mission.id, this.picTagObj.picId, this.picTagObj.authorId, this.checkAnswer, this.picTagObj.time).then()
      }
    },
    created: function() {
      this.mission = this.$store.state.mission.currentMission
    },
    mounted: function() {
      this.getNewPic()
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
</style>
