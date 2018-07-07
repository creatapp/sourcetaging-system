<template>
  <el-tabs tab-position="left">
    <el-tab-pane>
      <div slot="label" style="font-weight: bolder;font-size: 16px;">个性推荐<svg-icon icon-class="crown" style="color: #cf9236;"></svg-icon></div>
      <el-scrollbar  :style="tabStyle">
        <h3 v-if="recommendMissions.length==0">暂无个性推荐</h3>
        <div class="cardList">
          <mission-card v-for="(m,index) in recommendMissions" :key="index" :mission="m"></mission-card>
        </div>
      </el-scrollbar>
    </el-tab-pane>
    <el-tab-pane>
      <div slot="label" style="font-weight: bolder;font-size: 16px;">时下流行<svg-icon icon-class="fire" style="color: red"></svg-icon></div>
      <h3 v-if="hotMissions.length==0">暂无时下流行</h3>
      <div class="cardList">
        <mission-card v-for="(m,index) in hotMissions" :key="index" :mission="m">
          <div style="display: flex;justify-content: flex-end;">
            <el-tooltip  effect="dark" content="参与人数" placement="left">
            <span><svg-icon icon-class="fire" />{{m.participantsNum}}</span>
            </el-tooltip>
          </div>
        </mission-card>
      </div>
    </el-tab-pane>
    <el-tab-pane>
        <div slot="label" style="font-weight: bolder;font-size: 16px;">最新发布<svg-icon icon-class="new" style="color: #30b08f;"></svg-icon></div>
        <el-scrollbar  :style="tabStyle">
          <h3 v-if="newMissions.length==0">系统暂未发布任何任务</h3>
          <div class="cardList">
            <mission-card v-for="(m,index) in newMissions" :key="index" :mission="m"></mission-card>
          </div>
        </el-scrollbar>
    </el-tab-pane>
  </el-tabs>

</template>

<script>
  import missionCard from './components/missionCard'
  import { getNewMissions, getHotMissions, getRecommendMissions } from '../../api/mission'

export default {
    name: 'index',
    components: { missionCard },
    data() {
      return {
        tabStyle: {
          height: window.innerHeight - 90 + 'px'
        },
        newMissions: [],
        hotMissions: [],
        recommendMissions: []
      }
    },
    created: function() {
      const vm = this
      getNewMissions()
        .then(res => {
          console.log(res)
          if (res.data.result) {
            vm.newMissions = res.data.message
          } else {
            vm.$message({
              message: res.data.message,
              type: 'warning'
            })
          }
        }
        )
      getHotMissions().then(res => {
        if (res.data.result) {
          vm.hotMissions = res.data.message
        } else {
          vm.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      }
      )
      getRecommendMissions().then(res => {
        if (res.data.result) {
          vm.recommendMissions = res.data.message
        } else {
          vm.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      })
    }
  }
</script>


<style scoped>
  .cardList{
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
  }
  .cardList:after{
    content: '';
    width: 580px;
  }

</style>
