<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="进行中的任务" name="doing">
        <el-scrollbar :style="tabStyle">
          <doing-missions></doing-missions>
        </el-scrollbar>
      </el-tab-pane>
      <el-tab-pane label="已完成的任务" name="done">
        <el-scrollbar :style="tabStyle">
          <done-missions></done-missions>
        </el-scrollbar>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import doingMissions from '@/views/doingMissions'
import doneMissions from '@/views/doneMissions'
export default {
  name: 'dashboard-worker',
  components: { doingMissions, doneMissions },
  data() {
    return {
      tabStyle: {
        height: window.innerHeight - 180 + 'px'
      },
      activeName: 'doing'
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'avatar',
      'roles'
    ])
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .emptyGif {
    display: block;
    width: 45%;
    margin: 0 auto;
  }

  .dashboard-worker-container {
    background-color: #e3e3e3;
    min-height: 100vh;
    padding: 50px 60px 0px;
    .pan-info-roles {
      font-size: 12px;
      font-weight: 700;
      color: #333;
      display: block;
    }
    .info-container {
      position: relative;
      margin-left: 190px;
      height: 150px;
      line-height: 200px;
      .display_name {
        font-size: 48px;
        line-height: 48px;
        color: #212121;
        position: absolute;
        top: 25px;
      }
    }
  }
</style>
