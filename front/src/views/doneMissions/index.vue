<template>
  <div>
    <h3 v-if="missions.length==0">您暂时没有已完成的任务，快去新任务列表领取一个吧</h3>
    <div class="cardList">
      <mission-card v-for="(m, index) in missions" :key="index" :mission="m"></mission-card>
    </div>
  </div>
</template>

<script>
  import { getDoneMissions } from '../../api/mission'
  import missionCard from './components/missionCard'
  export default {
    name: 'index',
    components: { missionCard },
    data() {
      return {
        missions: []
      }
    },
    created: function() {
      const vm = this
      getDoneMissions()
        .then(res => {
          if (res.data.result) {
            vm.missions = res.data.message
          } else {
            vm.$message({
              message: res.data.message,
              type: 'warning'
            })
          }
        }
        )
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
