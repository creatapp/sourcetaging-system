<template>
  <div>
    <el-table
      :data="missions"
      :height="windowHeight-150"
      :default-sort="{prop: 'id', order: 'ascending'}"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="任务 ID">
              <span>{{ props.row.id }}</span>
            </el-form-item>
            <el-form-item label="任务标题">
              <span>{{ props.row.title }}</span>
            </el-form-item>
            <el-form-item label="发布日期">
              <span>{{ props.row.publishDate }}</span>
            </el-form-item>
            <el-form-item label="完结日期">
              <span>{{ props.row.finishDate }}</span>
            </el-form-item>
            <el-form-item label="任务描述">
              <span>{{ props.row.description }}</span>
            </el-form-item>
            <el-form-item label="任务类型">
              <span>{{ props.row.type }}</span>
            </el-form-item>
            <el-form-item label="任务标签">
              <span>{{ props.row.classLabel }}</span>
            </el-form-item>
            <el-form-item label="任务总积分">
              <span>{{ props.row.totalPoints }}</span>
            </el-form-item>
            <el-form-item label="已完成图片">
              <span>{{ props.row.done }}</span>
            </el-form-item>
            <el-form-item label="图片总数">
              <span>{{ props.row.total }}</span>
            </el-form-item>
            <el-form-item label="发布者" v-if="roles.indexOf('admin')>=0">
              <span>{{ props.row.requester}}</span>
            </el-form-item>
            <el-form-item label="参与者">
              <el-button size="small" @click="viewParticipants(props.row.id)">查看参与者</el-button>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
        label="任务 ID"
        sortable
        prop="id">
      </el-table-column>
      <el-table-column
        label="任务标题"
        prop="title">
      </el-table-column>
      <el-table-column
        label="发布日期"
        sortable
        prop="publishDate">
      </el-table-column>
      <!--<el-table-column-->
        <!--label="类型标签"-->
        <!--prop="classLabel"-->
        <!--:filters="[{ text: '人物', value: 'people' }, { text: '动物', value: 'animals' }, { text: '植物', value: 'plants' }, { text: '科技', value: 'science' }, { text: '商品', value: 'goods' }]" :filter-method="filterClass" filter-placement="bottom-end">-->
        <!--<template slot-scope="scope">-->
          <!--<el-tag type="primary" disable-transitions>{{scope.row.classLabel}}</el-tag>-->
        <!--</template>-->
      <!--</el-table-column>-->
      <el-table-column
        label="任务状态"
        prop="status"
        :filters="[{ text: 'doing', value: 'doing' }, { text: 'done', value: 'done' }]" :filter-method="filterStatus" filter-placement="bottom-end">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === 'doing' ? 'danger' : 'success'" disable-transitions>{{scope.row.status}}</el-tag>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="'任务'+chosenMission+'的参与者'" :visible.sync="showParticipants" center width='80%'>
      <el-table :data="participants" border height="500">
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
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
  import { getParticipants } from '../../api/mission'
import { mapGetters } from 'vuex'
  export default {
    data() {
      return {
        participants: [],
        showParticipants: false,
        chosenMission: ''
      }
    },
    props: ['missions'],
    computed: {
      windowHeight() {
        return window.innerHeight
      },
      ...mapGetters([
        'roles'
      ])
    },
    methods: {
      filterStatus(value, row) {
        return row.status === value
      },
      filterClass(value, row) {
        return row.classLabel === value
      },
      viewParticipants: function(missionId) {
        const vm = this
        vm.chosenMission = missionId
        getParticipants(missionId).then(res => {
          if (res.data.result) {
            vm.participants = res.data.message
            vm.showParticipants = true
          } else {
            vm.$message({
              message: res.data.message,
              type: 'warning'
            })
          }
        })
      }
    }
  }
</script>


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

