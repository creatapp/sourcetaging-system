<template>
  <div>
    <el-form ref="missionForm" :model="mission" :rules="missionRules" status-icon label-width="80px" size="small"  style="width: 500px;" @submit.native.prevent。>
      <el-form-item label="任务标题" prop="title">
        <el-input v-model="mission.title" placeholder="请输入一个精简的有意义的标题"></el-input>
      </el-form-item>
      <el-form-item label="标注类型" prop="type">
        <el-select v-model="mission.type" placeholder="请选择标注类型"  style="width: 100%">
          <el-option label="仅评注" value="comment"></el-option>
          <el-option label="边界标记" value="border_mark"></el-option>
          <el-option label="方框标记" value="frame_mark"></el-option>
          <el-option label="边界标记+评注" value="border_mc"></el-option>
          <el-option label="方框标记+评注" value="frame_mc"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="分类标签" prop="label">
        <el-select v-model="mission.label" placeholder="给任务选1-3个合适的标签" multiple :multiple-limit="3" filterable style="width: 100%">
          <el-option
            v-for="(item,index) in tagOptions"
            :key="index"
            :label="item"
            :value="index">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="需求描述" prop="description">
        <el-input type="textarea" autosize v-model="mission.description" placeholder="请说明任务的需求"></el-input>
      </el-form-item>


    </el-form>
    <el-form size="small" label-width="80px" :rules="missionRules" style="width: 100%" @submit.native.prevent>
      <el-form-item label="图片上传">
        <el-upload
          ref="upload"
          accept="image/*"
          :limit=60
          action="http://httpbin.org/"
          :auto-upload="false"
          list-type="picture-card"
          :on-change="handleChange"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          multiple
          prop="upload">
          <i class="el-icon-plus"></i>
          <!--<i class="el-icon-upload"></i>-->
          <!--<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>-->
        </el-upload>
      </el-form-item>

    </el-form>

    <el-form inline size="small"label-width="80px" style="margin-bottom: 50px" @submit.native.prevent>
      <el-form-item label="悬赏积分">
        <el-tooltip class="item" effect="dark" content="积分越多，预计完成时间就越短" placement="top-end">
        <el-input-number v-model="mission.perPoints"  :min="1" :max="10" :step="1"></el-input-number>
        </el-tooltip>
        <span> / 张</span>
      </el-form-item>
      <el-form-item label="总计" style="">
        {{mission.perPoints*picNumber}}
      </el-form-item>
      <el-form-item >
        <el-button :loading="loading" type="primary" @click="onSubmit">创建任务</el-button>
        <el-button @click="resetForm">取消</el-button>
      </el-form-item>
    </el-form>
  </div>

</template>

<script>
  import { isvalidWords } from '../../utils/validate'
import { buildMission } from '../../api/mission'
  export default {
    name: 'index',
    data() {
      var validateTitle = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('标题不能为空'))
        } else if (!isvalidWords(value)) {
          return callback(new Error('不要带特殊符号'))
        } else if (value.length < 4 || value.length > 10) {
          return callback(new Error('标题长度请在4到10之间'))
        } else {
          callback()
        }
      }
      var validateNull = (rule, value, callback) => {
        if (!value) {
          callback(new Error('该项不能为空'))
        } else {
          callback()
        }
      }
      var validateDesc = (rule, value, callback) => {
        if (!value) {
          callback(new Error('描述不能为空'))
        } else if (value.length < 10) {
          callback(new Error('描述长度应该大于10'))
        } else {
          callback()
        }
      }
      return {
        tagOptions: ['科技', '人物', '生活', '军事', '风景', '建筑', '艺术', '影视', '工业', '交通', '书籍', '食物', '玩具', '用具', '服饰', '体育', '医疗', '天文', '标志', '其他'],
        mission: {
          title: '',
          type: '',
          pics: [],
          description: '',
          label: [],
          perPoints: 3
        },
        missionRules: {
          title: [
            { validator: validateTitle, trigger: 'change' }
          ],
          type: [
            { validator: validateNull, trigger: 'change' }
          ],
          label: [
            { validator: validateNull, trigger: 'change' }
          ],
          description: [
            { validator: validateDesc, trigger: 'change' }
          ],
          upload: [
            { validator: validateNull, trigger: 'change' }
          ]
        },
        loading: false,
        picNumber: 0
      }
    },
    watch: {

    },
    computed: {
      estimateTime: function() {
        if (this.picNumber === 0) return 0
        const normalOnePicTime = 0.5
        const normalOnePicPoints = 3
        const realOnePicTime = normalOnePicTime * (normalOnePicPoints / this.mission.perPoints)
        const days = realOnePicTime * this.picNumber
        return days > 3 ? days : 3
      }
    },
    methods: {
      onSubmit() {
        this.$refs['missionForm'].validate((valid) => {
          if (valid) {
            const fileList = this.$refs.upload.uploadFiles
            const vm = this
            vm.mission.pics = []
            let cnt = 0
            for (let i = 0; i < fileList.length - 1; i++) {
              const f = fileList[i].raw
              this.readFile(f)
                .then(function(result) {
                  vm.mission.pics[cnt++] = result
                })
            }
            const i = fileList.length - 1
            if (i > 0) {
              const f = fileList[i].raw
              this.readFile(f)
                .then(function(result) {
                  vm.mission.pics[cnt++] = result
                  vm.loading = true
                  const m = vm.mission
                  buildMission(m.label, m.description, m.title, m.type, m.perPoints, m.pics).then(() => {
                    vm.loading = false
                    vm.$message(
                      {
                        message: '任务建立成功',
                        type: 'success'
                      })
                  })
                })
            } else {
              vm.$message(
                {
                  message: '图片数量过少 ',
                  type: 'error'
                })
            }
          } else {
            return false
          }
        })
      },
      resetForm() {
        this.$refs.missionForm.resetFields()
        this.$refs.upload.uploadFiles = []
        this.picNumber = 0
        this.mission.perPoints = 0.8
      },
      handleRemove(file, fileList) {
        this.picNumber = fileList.length
      },
      handleChange(file, fileList) {
        this.picNumber = fileList.length
      },
      handlePreview(file) {
      },
      readFile(file) {
        return new Promise(function(resolve, reject) {
          const reader = new FileReader()
          reader.onload = function() {
            resolve(reader.result)
          }
          reader.onerror = reject
          reader.readAsDataURL(file)
        })
      }
    }
  }
</script>

<style scoped>

</style>
