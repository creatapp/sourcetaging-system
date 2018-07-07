<template>
  <el-card class="box-card-component" style="margin:10px;">
    <div slot="header" class="box-card-header">
      <img :src="coverImage">
    </div>
    <div style="position:relative;">
      <!--<pan-thumb class="panThumb" :image="avatar"></pan-thumb>-->
      <!--<mallki className='mallki-text' text='vue-element-admin'></mallki>-->
      <el-row type="flex" justify="space-between">
        <div>{{labelString}}</div>
      </el-row>
      <el-row type="flex" justify="space-between" align="bottom">
        <div style="font-size: 23px;">{{mission.title}}</div>
        <div><el-tag type="info"><b>{{kind}}</b></el-tag></div>
      </el-row>
      <el-row>
        <div style="color: #999"> {{mission.description}}</div>
      </el-row>
      <el-row>
        <el-progress :percentage="Number((mission.done/mission.total*100).toFixed(2))" text-inside :stroke-width="10" style="font-size: 12px"></el-progress>
      </el-row>
      <el-row type="flex" justify="space-between">
        <el-tag><svg-icon icon-class="price"></svg-icon>{{mission.price.toFixed(2)}}/张</el-tag>
        <el-button type="text" size="small" @click="enterMission" style="padding: 3px 0px;">继续任务</el-button>
      </el-row>
    </div>
  </el-card>
</template>

<script>
  export default {
    data() {
      return {
        coverImage: 'https://wpimg.wallstcn.com/e7d23d71-cf19-4b90-a1cc-f56af8c0903d.png'
      }
    },
    props: {
      mission: {
        type: Object,
        default: function() {
          return {
            id: 'm0001',
            classLabel: '植物',
            publishDate: '2018-5-20',
            title: '苹果画框', // 任务标题
            type: 'border_mc', // 任务类型
            kind: 'tag',
            description: '框出图中苹果的边界', // 任务描述
            price: 3,
            done: 6,
            total: 10
          }
        }
      }
    },
    computed: {
      labelString() {
        const dic = ['科技', '科技', '人物', '生活', '军事', '风景', '建筑', '艺术', '影视', '工业', '交通', '书籍', '食物', '玩具', '用具', '服饰', '体育', '医疗', '天文', '标志', '其他']
        const labelWords = []
        for (let x = 0; x < this.mission.classLabel.length; x++) {
          labelWords.push(dic[this.mission.classLabel[x]])
        }
        return labelWords.join(' / ')
      },
      kind() {
        const dic = { 'tag': '标注', 'check': '检查' }
        return dic[this.mission.kind]
      }
    },
    methods: {
      enterMission() {
        this.$store.commit('ENTER_MISSION', this.mission)
        if (this.mission.kind === 'tag') this.$router.push('/picTag')
        else this.$router.push('/checkAnswer')
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" >
  .el-row {
    margin-bottom: 10px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .box-card-component{
    width: 270px;
    .el-card__header {
      padding: 0px!important;
    }
  }
</style>
<style rel="stylesheet/scss" lang="scss" scoped>
  .box-card-component {
    .box-card-header {
      position: relative;
      height: 200px;
      img {
        width: 100%;
        height: 100%;
        transition: all 0.2s linear;
        &:hover {
          transform: scale(1.1, 1.1);
          filter: contrast(130%);
        }
      }
    }
    .time {
      font-size: 15px;
      color: #999;
    }
    .mallki-text {
      position: absolute;
      top: 0px;
      right: 0px;
      font-size: 20px;
      font-weight: bold;
    }
    .panThumb {
      z-index: 100;
      height: 70px!important;
      width: 70px!important;
      position: absolute!important;
      top: -45px;
      left: 0px;
      border: 5px solid #ffffff;
      background-color: #fff;
      margin: auto;
      box-shadow: none!important;
      /deep/ .pan-info {
        box-shadow: none!important;
      }
    }
    .progress-item {
      margin-bottom: 10px;
      font-size: 14px;
    }
    @media only screen and (max-width: 1510px){
      .mallki-text{
        display: none;
      }
    }
  }
</style>
