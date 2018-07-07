<template>
  <el-table
    :data="missions"
    height="550"
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
            <el-button size="small">查看参与者</el-button>
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

    <el-table-column
      label="任务状态"
      prop="status"
      :filters="[{ text: 'doing', value: 'doing' }, { text: 'done', value: 'done' }]" :filter-method="filterStatus" filter-placement="bottom-end">
      <template slot-scope="scope">
        <el-tag :type="scope.row.status === 'doing' ? 'danger' : 'success'" disable-transitions>{{scope.row.status}}</el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  import { mapGetters } from 'vuex'
  export default {
    props: ['missions'],
    computed: {
      ...mapGetters([
        'roles'
      ])
    },
    methods: {
      filterStatus(value, row) {
        return row.status === value
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

