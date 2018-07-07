<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="工作数据" name="workData">
        <el-scrollbar :style="tabStyle">
          <div style="width: 100%">
            <h3>生涯总览</h3>
            <el-table
              :data="careerAsArray"
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
                label="已获取积分"
                prop="points">
              </el-table-column>
            </el-table>
          </div>
          <div style="width: 100%">
            <h3>系统评分</h3>
            <div style="height: 500px;width: 500px" ref="chart"></div>
          </div>
          <div style="width: 100%">
            <h3>积分历史</h3>
            <el-table
              :data="workData.pointsHistory"
              height="500"
              :default-sort="{prop: 'id', order: 'ascending'}"
              style="width: 100%">
              <el-table-column
                label="结算日"
                sortable
                prop="payDate">
              </el-table-column>
              <el-table-column
                label="任务ID"
                prop="missionId">
              </el-table-column>
              <el-table-column
                label="答题情况">
                <el-table-column
                  label="正确"
                  prop="right">
                </el-table-column>
                <el-table-column
                  label="错误"
                  prop="wrong">
                </el-table-column>
              </el-table-column>
              <el-table-column
                label="单价"
                sortable
                prop="price">
              </el-table-column>
              <el-table-column
                label="最终报酬"
                sortable
                prop="points">
              </el-table-column>
              <el-table-column
                label="任务类型"
                prop="kind"
                :filters="[{ text: '标注', value: 'tag' }, { text: '检查', value: 'check' }]" :filter-method="filterKind" filter-placement="bottom-end">
                <template slot-scope="scope">
                  <el-tag disable-transitions>{{scope.row.kind}}</el-tag>
                </template>
              </el-table-column>
              <el-table-column
                label="标注领域"
                prop="classLabel"
                :filters="[{ text: '人物', value: 'people' }, { text: '动物', value: 'animals' }, { text: '植物', value: 'plants' }, { text: '商品', value: 'goods' }, { text: '科技', value: 'science' }]" :filter-method="filterClass" filter-placement="bottom-end">
                <template slot-scope="scope">
                  <el-tag disable-transitions>{{scope.row.classLabel}}</el-tag>
                </template>
              </el-table-column>
            </el-table>

          </div>
        </el-scrollbar>
      </el-tab-pane>
      <el-tab-pane label="个人信息" name="personData">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div style="display: flex;justify-content: flex-start;">
            <div id="avatarUp">
              <!--<a class="btn" @click="toggleShow">set avatar</a>-->
              <!--<my-upload field="img"-->
                         <!--@crop-success="cropSuccess"-->
                         <!--@crop-upload-success="cropUploadSuccess"-->
                         <!--@crop-upload-fail="cropUploadFail"-->
                         <!--v-model="show"-->
                         <!--:width="300"-->
                         <!--:height="300"-->
                         <!--url="/upload"-->
                         <!--:params="params"-->
                         <!--:headers="headers"-->
                         <!--img-format="png"></my-upload>-->
              <!--<img :src="normalInfo.avatar">-->
              <pan-thumb :image="avatar"></pan-thumb>
            </div>
            <el-form style="margin-left: 20px;width: 300px">
              <el-form-item label="账号">
                <b>{{normalInfo.id}}</b>
              </el-form-item>
              <el-form-item label="昵称">
                <b>{{normalInfo.nickname}}</b><el-button size="small">更改</el-button>
              </el-form-item>
              <el-form-item label="邮箱">
                <b>{{normalInfo.email}}</b><el-button  size="small">更改</el-button>
              </el-form-item>
              <el-form-item label="密码">
                <b>********</b><el-button size="small">更改</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
  import echarts from 'echarts'
  import('echarts/theme/macarons') // echarts theme
  import { mapGetters } from 'vuex'
  import { getWorkerData, getUserInfo } from '../../../api/user'
import PanThumb from '@/components/PanThumb'
  export default {
    name: 'profile-worker',
    components: {
      PanThumb },
    data() {
      return {
        tabStyle: {
          height: window.innerHeight - 180 + 'px'
        },
        activeName: 'workData',
        chart: null,
        workData: {
          career: {
            id: '',
            nickname: '',
            doingMissions: 5,
            doneMissions: 40,
            rightAns: 100,
            wrongAns: 33,
            toBeJudgedAns: 30,
            totalAns: 163,
            ansAccuracy: 0.66,
            points: 106
          },
          fieldsRatings: {
            tag: [6, 8, 7, 9, 5],
            check: [7, 5, 6, 4, 7]
          },
          pointsHistory: [
            {
              payDate: '2018-5-20',
              missionId: '1000002',
              kind: 'tag',
              classLabel: 'plants',
              right: 5,
              wrong: 3,
              price: 2,
              points: 10
            }
          ]
        },
        normalInfo: {
          id: '',
          nickname: '',
          email: ''
        }
      }
    },
    computed: {
      careerAsArray() {
        const data = []
        data[0] = this.workData.career
        return data
      },
      ...mapGetters([
        'name',
        'avatar',
        'roles',
        'id'
      ])
    },
    mounted() {
      this.initChart()
    },
    created() {
      const vm = this
      getWorkerData(this.id).then(res => {
        if (res.data.result) {
          vm.workData.career = res.data.message
        } else {
          vm.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      })
      getUserInfo().then(res => {
        if (res.data.result) {
          vm.normalInfo = res.data.message
        } else {
          vm.$message({
            message: res.data.message,
            type: 'warning'
          })
        }
      })
    },
    methods: {
      filterClass(value, row) {
        return row.classLabel === value
      },
      filterKind(value, row) {
        return row.kind === value
      },
      initChart() {
        this.chart = echarts.init(this.$refs.chart, 'macarons')
        this.chart.setOption({
          title: {
            text: '领域得分(十分制)',
            textStyle: {
              color: '#333',
              fontWeight: 'bold'
            }
          },
          tooltip: {},
          legend: {
            data: ['准确度', '效率', '偏好']
          },
          toolbox: {
            show: true,
            feature: {
              dataView: { readOnly: false },
              restore: {},
              saveAsImage: {}
            }
          },
          radar: {
            radius: '66%',
            center: ['50%', '42%'],
            splitNumber: 8,
            splitArea: {
              areaStyle: {
                color: 'rgba(127,95,132,.3)',
                opacity: 1,
                shadowBlur: 45,
                shadowColor: 'rgba(0,0,0,.5)',
                shadowOffsetX: 0,
                shadowOffsetY: 15
              }
            },
            indicator: [
              { name: '人物', max: 10 },
              { name: '动物', max: 10 },
              { name: '植物', max: 10 },
              { name: '科技', max: 10 },
              { name: '商品', max: 10 }
            ]
          },
          series: [{
            type: 'radar',
            areaStyle: {
              normal: {
                shadowBlur: 13,
                shadowColor: 'rgba(0,0,0,.2)',
                shadowOffsetX: 0,
                shadowOffsetY: 10,
                opacity: 1
              }
            },
            data: [
              {
                value: [6, 8, 7, 9, 5],
                name: '准确度'
              }
            ]
          },
          {
            type: 'radar',
            areaStyle: {
              normal: {
                shadowBlur: 13,
                shadowColor: 'rgba(0,0,0,.2)',
                shadowOffsetX: 0,
                shadowOffsetY: 10,
                opacity: 1
              }
            },
            data: [
              {
                value: [7, 5, 6, 4, 7],
                name: '效率'
              }
            ]
          },
          {
            type: 'radar',
            areaStyle: {
              normal: {
                shadowBlur: 13,
                shadowColor: 'rgba(0,0,0,.2)',
                shadowOffsetX: 0,
                shadowOffsetY: 10,
                opacity: 1
              }
            },
            data: [
              {
                value: [5, 8, 2, 6, 3],
                name: '偏好'
              }
            ]
          }
          ]
        })
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
