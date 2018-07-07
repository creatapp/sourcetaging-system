<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="众包工人" name="worker">
        <el-table :data="workers" border height="500">
          <el-table-column
            prop="id"
            sortable
            label="账号">
          </el-table-column>
          <el-table-column
            prop="nickname"
            label="昵称">
          </el-table-column>
          <el-table-column
            prop="points"
            sortable
            label="积分">
          </el-table-column>
          <el-table-column
            prop="email"
            label="邮箱">
          </el-table-column>
          <el-table-column
          label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="viewWorkerDetail(scope.row.id)">更多资料</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-dialog :title="'工人'+chosenWorker[0].id" :visible.sync="showWorkerDetail" center width='80%'>
        <el-table
          :data="chosenWorker"
          style="width: 100%">
          <el-table-column
            label="任务"
          >
            <el-table-column
              label="进行中"
              prop="doingMissions">
            </el-table-column>
            <el-table-column
              label="已完成"
              prop="doneMissions">
            </el-table-column>
          </el-table-column>
          <el-table-column
            label="标注图片">
            <el-table-column
              label="总数"
              prop="totalAns">
            </el-table-column>
            <el-table-column
              label="正确"
              prop="rightAns">
            </el-table-column>
            <el-table-column
              label="错误"
              prop="wrongAns">
            </el-table-column>
            <el-table-column
              label="待评估"
              prop="toBeJudgedAns">
            </el-table-column>
          </el-table-column>
          <el-table-column
            label="正确率"
            prop="ansAccuracy">
          </el-table-column>
          <el-table-column
            label="积分"
            prop="points">
          </el-table-column>
        </el-table>
      </el-dialog>
      <el-tab-pane label="发包企业" name="requester">
        <el-table :data="requesters" border height="500">
          <el-table-column
            prop="id"
            sortable
            label="账号">
          </el-table-column>
          <el-table-column
            prop="nickname"
            label="昵称">
          </el-table-column>
          <el-table-column
            prop="paidPoints"
            sortable
            label="已付积分">
          </el-table-column>
          <el-table-column
            prop="email"
            label="邮箱">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="viewRequesterDetail(scope.row.id)">更多资料</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-dialog :title="'发包企业'+chosenRequester[0].nickname" :visible.sync="showRequesterDetail" center width='80%'>
          <el-table
            :data="chosenRequester"
            style="width: 100%">
            <el-table-column
              label="任务"
            >
              <el-table-column
                label="进行中"
                prop="doingMissions">
              </el-table-column>
              <el-table-column
                label="已验收"
                prop="doneMissions">
              </el-table-column>
            </el-table-column>
            <el-table-column
              label="积分(支付）"
              prop="paidPoints">
            </el-table-column>
          </el-table>
        </el-dialog>

      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getAllWorkers, getAllRequesters } from '../../api/admin'
import { getWorkerData, getRequesterData } from '../../api/user'

export default {
  name: 'userManage',
  data() {
    return {
      activeName: 'worker',
      workers: [],
      requesters: [],
      chosenWorker: [{ id: '123' }],
      chosenRequester: [{ id: '123' }],
      showWorkerDetail: false,
      showRequesterDetail: false
    }
  },
  methods: {
    viewWorkerDetail: function(workerId) {
      const vm = this
      getWorkerData(workerId).then(res => {
        if (res.data.result) {
          vm.chosenWorker[0] = res.data
          vm.showWorkerDetail = true
        } else {
          vm.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      })
    },
    viewRequesterDetail: function(id) {
      const vm = this
      getRequesterData(id).then(res => {
        if (res.data.result) {
          vm.chosenRequester[0] = res.data.message
          vm.showRequesterDetail = true
        } else {
          vm.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      })
    }
  },
  created() {
    const vm = this
    getAllWorkers().then(res => {
      if (res.data.result) {
        vm.workers = res.data.message
      } else {
        vm.$message({
          message: res.data.message,
          type: 'warning'
        })
      }
    })
    getAllRequesters().then(res => {
      if (res.data.result) {
        vm.requesters = res.data.message
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

