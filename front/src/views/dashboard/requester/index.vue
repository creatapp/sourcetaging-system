<template>
  <div>
    <h3>>>>我发布的任务</h3>
    <mission-table :missions="missions"></mission-table>
  </div>
</template>

<style>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>

<script>
  import { getRequesterMissions } from '@/api/mission'
  import missionTable from '@/views/missionTable'
  export default {
    components: { missionTable },
    data() {
      return {
        missions: []
      }
    },
    methods: {
      filterStatus(value, row) {
        return row.status === value
      }
    },
    created() {
      const vm = this
      getRequesterMissions().then(res => {
        if (res.data.result) {
          vm.missions = res.data.message
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
