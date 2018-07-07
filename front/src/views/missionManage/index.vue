<template>
  <div class="app-container">
    <mission-table :missions="missions"></mission-table>
  </div>
</template>

<script>
  import missionTable from '@/views/missionTable'
  import { getAllMissions } from '../../api/mission'

export default {
    components: { missionTable },
    name: 'missionManage',
    data() {
      return {
        missions: []
      }
    },
    created() {
      const vm = this
      getAllMissions().then(res => {
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

