<template>
  <div>
    <el-dialog
    :visible.sync="showLogin"
    title="登录"
    center>
      <div class="form-container">
        <el-form class="login-form" autoComplete="on" :model="loginForm" :rules="formRules" ref="loginForm" label-position="left">
          <!--<div class="title-container">-->
            <!--<h3 class="title">{{$t('login.title')}}</h3>-->
            <!--&lt;!&ndash;<lang-select class="set-language"></lang-select>&ndash;&gt;-->
          <!--</div>-->
          <el-form-item prop="id">
            <el-input name="id" type="text" v-model="loginForm.id" autoComplete="on" placeholder="id" >
              <svg-icon slot="prefix" icon-class="user"></svg-icon>
            </el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input name="password" :type="passwordType" @keyup.enter.native="handleLogin" v-model="loginForm.password" autoComplete="on" placeholder="password" >
              <svg-icon slot="prefix" icon-class="password" />
              <span slot="suffix" class="show-pwd" @click="showPwd">
                <svg-icon icon-class="eye" />
              </span>
            </el-input>
          </el-form-item>
          <el-form-item prop="type" style="display: flex;justify-content: space-between">
            <el-radio v-model="loginForm.type" label="worker">众包工人</el-radio>
            <el-radio v-model="loginForm.type" label="requester">发包企业</el-radio>
            <el-radio v-model="loginForm.type" label="admin">管理员</el-radio>
          </el-form-item>

          <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleLogin">{{$t('login.logIn')}}</el-button>
          <div style="display:flex; justify-content: space-between;">
            <el-button type="text"  @click="openFindPassword">忘记密码？点此找回</el-button>
            <el-button type="text"  @click="openRegister">没有账号？注册一个</el-button>
          </div>
        </el-form>

      </div>
    </el-dialog>
    <el-dialog
    :visible.sync="showRegister"
    title="注册"
    center>
      <div class="form-container">
        <el-form class="login-form" autoComplete="on" :model="registerForm" :rules="formRules" ref="registerForm" label-position="left">
          <el-form-item prop="email">
            <el-input name="username" type="text" v-model="registerForm.email" autoComplete="on" placeholder="邮箱" >
              <svg-icon slot="prefix" icon-class="email" />
            </el-input>
          </el-form-item>

          <el-form-item prop="nickname">
            <el-input name="username" type="text" v-model="registerForm.nickname" autoComplete="on" placeholder="昵称" >
              <svg-icon slot="prefix" icon-class="user" />
            </el-input>
          </el-form-item>

          <el-form-item prop="password">
            <el-input name="password" :type="passwordType" @keyup.enter.native="handleRegister" v-model="registerForm.password" autoComplete="on" placeholder="密码">
              <svg-icon slot="prefix" icon-class="password" />
              <span slot="suffix" class="show-pwd" @click="showPwd">
          <svg-icon icon-class="eye" />
        </span>
            </el-input>
          </el-form-item>
          <el-form-item prop="role" style="display: flex;justify-content: space-between">
            <el-radio v-model="registerForm.role" label="worker">众包工人</el-radio>
            <el-radio v-model="registerForm.role" label="requester">发包企业</el-radio>
          </el-form-item>

          <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleRegister">注册</el-button>
          <div style="display:flex; justify-content: flex-end;">
            <el-button type="text"  @click="openLogin">已有账号？去登录</el-button>
          </div>

        </el-form>
      </div>
    </el-dialog>
    <el-dialog
    :visible.sync="showFindPassword"
    title="找回密码"
    center>
      <div class="form-container">
        <el-form class="login-form" autoComplete="on" :model="findForm" :rules="formRules" ref="findForm" label-position="left">
          <el-form-item prop="email">
            <el-input name="username" type="text" v-model="findForm.email" autoComplete="on" placeholder="邮箱" >
              <svg-icon slot="prefix" icon-class="email" />
            </el-input>
          </el-form-item>

          <el-button type="primary" style="width:100%;" :loading="loading" @click.native.prevent="handleFindPassword">找回</el-button>
          <div style="display:flex; justify-content: flex-end;">
            <el-button type="text"  @click="openLogin">去登录</el-button>
          </div>

        </el-form>
      </div>
    </el-dialog>

    <el-row class="nav" style="position: fixed;top: 0;left: 0;z-index:999;width: 100%; ">
      <el-col :span="20" class="nav">
        <el-menu
          :default-active="activeIndex2"
          class="nav"
          mode="horizontal"
          @select="handleSelect"
          background-color="#199475"
          text-color="#413C35"
          active-text-color="#883A2B">
          <el-menu-item index="1">首页</el-menu-item>
          <el-menu-item index="2">关于众包</el-menu-item>
          <el-menu-item index="3">关于标注</el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="4">
        <el-menu
          :default-active="activeIndex2"
          class="nav"
          mode="horizontal"
          @select="handleSelect"
          text-color="#413C35"
          active-text-color="#883A2B"
          style="display: flex;justify-content: flex-end;">
          <el-button plain class="navButton" @click="openLogin">登录</el-button>
          <el-button plain class="navButton" @click="openRegister">注册</el-button>
        </el-menu>
      </el-col>
    </el-row>
    <el-carousel arrow="always" type="card" :interval="5000" trigger="click" height="300px" style="margin-top: 60px">
      <el-carousel-item v-for="item in 3" :key="item">
        <img :src="slides[item-1]" style="width: 100%;height: 100%">
      </el-carousel-item>
    </el-carousel>
    <div id="summary" class="each-part">
      <div class="group">
        <div class="title">
          平台概述
        </div>
        <div class="content">
          <p ui-type="text" class="text">
            SECIII众包标注平台提供一站式的数据众包服务，可根据特定领域、特定场景的客户需求，提供定制化的数据获取与加工方案的设计与执行服务， 为客户交付标准化结构化的可用数据。
          </p>
          <div style="display: flex; justify-content: center">
            <img :src="summuryPic"/>
          </div>
        </div>
      </div>
    </div>
    <div id="features" class="each-part">
      <div class="group">
        <div class="title">
          平台功能
        </div>
        <div class="content">
          <div class="content-frame">
            <div style="display: flex;justify-content: space-between">
              <div>
                <!--<div style="display: flex; justify-content: center">-->
                  <!--<img :src="featurePic1"/>-->
                <!--</div>-->
                <ol>
                  数据采集：
                  <li>人脸识别</li>
                  <li>马路描边</li>
                  <li>动物识别</li>
                  <li>音频视频文本（可以考虑尝试完成）</li>
                </ol>
              </div>
              <div>
                <!--<div style="display: flex; justify-content: center">-->
                <!--<img :src="featurePic2"/>-->
                <!--</div>-->
                <ol >
                  数据标注：
                  <li>整体标注</li>
                  <li>方框标注</li>
                  <li>边界标注</li>
                  <li>音频视频文本标注（可以考虑尝试完成）</li>
                </ol>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="advantages" class="each-part">
      <div class="group">
        <div class="title">
          产品优势
        </div>
        <div class="content">
          <p ui-type="text" class="text">
            可能就只有免费使用免费注册干什么都不要钱吧！哈哈哈哈哈！
          </p>
        </div>
      </div>
    </div>
    <div id="processes" class="each-part">
      <div class="group">
        <div class="title">
          使用步骤
        </div>
        <div class="content">
          <div style="display:flex;justify-content: flex-start">
            <div style="width: 50%">
              <ol>
                众包工人：
                <li>注册登录</li>
                <li>接取任务</li>
                <li>提交任务</li>
                <li>领取奖励</li>
              </ol>
            </div>
            <div style="width: 50%">
              <ol>
                发包者：
                <li>建立任务</li>
                <li>平台分发</li>
                <li>工人标注</li>
                <li>数据交付</li>
              </ol>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="footer">
      <div >
        <h3> Contact:</h3>
        <p id="footer-contact">
          地址： 九乡河大学仙林校区<br>
          Phone: 12345678910<br>
          Email: shawandshaw@163.com<br>
        </p>
      </div>
      <div>
        <h3>Links</h3>
        <p>
          <a href="#"> About ( Who we are )<br></a>
          <a href="#"> Services ( What we do )<br></a>
          <a href="#"> Contact ( Feel free to contact )<br></a>
        </p>
      </div>
      <div>
        <h3>
          <!--<i class="fa fa-heart"></i> -->
          Socialize
        </h3>
        <div id="social-icons">
          <a href="#" class="btn-group google-plus">
            <!--<i class="fa fa-google-plus"></i>-->
          </a>
          <a href="#" class="btn-group linkedin">
            <!--<i class="fa fa-linkedin-square"></i>-->
          </a>
          <a href="#" class="btn-group twitter">
            <!--<i class="fa fa-twitter"></i>-->
          </a>
          <a href="#" class="btn-group facebook">
            <!--<i class="fa fa-facebook"></i>-->
          </a>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
  import pic1 from '@/assets/slides2.jpg'
  import pic2 from '@/assets/slides3.png'
  import pic3 from '@/assets/slides4.jpg'
  import summuryPic from '@/assets/introduce_summary.png'
  import featurePic1 from '@/assets/introduce_features1.png'
  import featurePic2 from '@/assets/introduce_features2.png'
  import { register, findPassword } from '@/api/user'
  import { isvalidUsername, isvalidEmail } from '@/utils/validate'
  export default {
    data() {
      const validatePassword = (rule, value, callback) => {
        if (value.length < 6) {
          callback(new Error('The password can not be less than 6 digits'))
        } else {
          callback()
        }
      }
      const validateEmail = (rule, value, callback) => {
        if (!isvalidEmail(value)) {
          callback(new Error('Please enter the correct email'))
        } else {
          callback()
        }
      }
      const validateUsername = (rule, value, callback) => {
        if (!isvalidUsername(value)) {
          callback(new Error('Please enter the correct username'))
        } else {
          callback()
        }
      }
      return {
        loading: false,
        showLogin: false,
        showFindPassword: false,
        loginForm: {
          id: 'admin',
          password: '1111111',
          type: 'worker'
        },
        registerForm: {
          email: '',
          nickname: '',
          password: '',
          role: 'worker'
        },
        findForm: {
          email: ''
        },
        formRules: {
          id: [{ required: true, trigger: 'blur' }],
          email: [{ required: true, trigger: 'blur', validator: validateEmail }],
          username: [{ required: true, trigger: 'blur', validator: validateUsername }],
          password: [{ required: true, trigger: 'blur', validator: validatePassword }]
        },
        passwordType: 'password',
        showRegister: false,
        activeIndex2: '1',
        slides: [pic1, pic2, pic3],
        summuryPic: summuryPic,
        featurePic1: featurePic1,
        featurePic2: featurePic2
      }
    },
    methods: {
      showPwd() {
        if (this.passwordType === 'password') {
          this.passwordType = ''
        } else {
          this.passwordType = 'password'
        }
      },
      handleLogin() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('LoginByUsername', this.loginForm).then(res => {
              this.loading = false
              if (res.data.result === true) {
                this.$router.push({ path: '/' })
              } else {
                this.$message(res.data.message)
              }
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      handleRegister() {
        this.$refs.registerForm.validate(valid => {
          if (valid) {
            this.loading = true
            register(this.registerForm.email, this.registerForm.nickname, this.registerForm.password, this.registerForm.role).then(res => {
              this.loading = false
              this.$router.push({ path: '/login' })
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      handleFindPassword() {
        this.$refs.findForm.validate(valid => {
          if (valid) {
            this.loading = true
            findPassword(this.findForm.email).then(() => {
              this.loading = false
              this.$message({
                message: '我们已经将你的密码下发至您的邮箱，请注意查收。',
                type: 'success'
              })
              this.$router.push({ path: '/login' })
            }).catch(() => {
              this.loading = false
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      openRegister() {
        this.showFindPassword = false
        this.showLogin = false
        this.showRegister = true
      },
      openFindPassword() {
        this.showRegister = false
        this.showLogin = false
        this.showFindPassword = true
      },
      handleSelect(key, keyPath) {
      },
      openLogin() {
        this.showRegister = false
        this.showFindPassword = false
        this.showLogin = true
      }
    }
  }

</script>


<style lang="scss" scoped>
  $navColor2:#f3f2eb;
  $navHoverColor2:#D7d6cf;

  $navColor:#199475;
  $navHoverColor: #14765E;
  .nav{
    background-color: $navColor;
  }
.navButton{
  height:60px;
  background-color: $navColor;
  color:#413C35;
  border: none;
  border-radius: 0;
}
.navButton:hover{
  color:#413C35;
  background-color: $navHoverColor;
}
.navButton:focus{
  color:#413C35;
  background-color: $navHoverColor;
}


.each-part{
  padding: 0 0 40px 0;
  overflow: hidden;
  width: 100%;
}
.each-part .group{
  width: 1180px;
  margin: 0 auto;
}
.each-part img{
  margin-left: 30px;
}
.each-part .content ol{
  list-style-type: square;
  font-size: 24px;
  padding: 30px;
}
.each-part .content ol li{
  padding: 10px;
  margin-left: 30px;
  font-size: 20px;
}

.group .title{
  margin: 0px 0 30px 20px;
  font-size: 24px;
  color: #141a24;
  padding-left: 14px;
  position: relative;
}
.group .title::before{
  content: "";
  display: block;
  position: absolute;
  width: 8px;
  bottom: 3px;
  top: 6px;
  left: 0px;
  background: #199475;
}

p[ui-type="text"]{
  font-size: 16px;
  line-height: 24px;
  color: #333;
  margin: 1em 30px;
}
/*i{*/
  /*position: absolute;*/
  /*width: 40px;*/
  /*display: block;*/
  /*height: 4px;*/
  /*background: #199475;*/
  /*bottom: 0;*/
  /*transition: left .5s ease-out;*/
/*}*/
#footer{
  padding: 0 10px;
  width: 100%;
  height: auto;
  background-color: #413c35;
  color: #fff;
  display: flex;
  justify-content: space-between;
}
</style>
