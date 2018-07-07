webpackJsonp([4],{"/faj":function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=n("do9+"),s=n.n(i),o=n("iD+W"),a=n.n(o),r=n("VNx6"),l=n.n(r),c=n("XKvO"),d=n.n(c),p=n("VDDH"),v=n.n(p),u=n("Wfvl"),f=n.n(u),m=n("vMJZ"),g=n("E4LH"),h={data:function(){return{loading:!1,showLogin:!1,showFindPassword:!1,loginForm:{id:"",password:"",type:"worker"},registerForm:{email:"",nickname:"",password:"",role:"worker"},findForm:{email:""},formRules:{id:[{required:!0,trigger:"blur"}],email:[{required:!0,trigger:"blur",validator:function(t,e,n){Object(g.a)(e)?n():n(new Error("Please enter the correct email"))}}],username:[{required:!0,trigger:"blur",validator:function(t,e,n){Object(g.b)(e)?n():n(new Error("Please enter the correct username"))}}],password:[{required:!0,trigger:"blur",validator:function(t,e,n){e.length<6?n(new Error("The password can not be less than 6 digits")):n()}}]},passwordType:"password",showRegister:!1,activeIndex2:"1",slides:[s.a,a.a,l.a],summuryPic:d.a,featurePic1:v.a,featurePic2:f.a}},methods:{showPwd:function(){"password"===this.passwordType?this.passwordType="":this.passwordType="password"},handleLogin:function(){var t=this;this.$refs.loginForm.validate(function(e){if(!e)return console.log("error submit!!"),!1;t.loading=!0,t.$store.dispatch("LoginByUsername",t.loginForm).then(function(e){t.loading=!1,!0===e.data.result?t.$router.push({path:"/"}):t.$message(e.data.message)}).catch(function(){t.loading=!1})})},handleRegister:function(){var t=this;this.$refs.registerForm.validate(function(e){if(!e)return console.log("error submit!!"),!1;t.loading=!0,Object(m.j)(t.registerForm.email,t.registerForm.nickname,t.registerForm.password,t.registerForm.role).then(function(e){t.loading=!1,t.$message({message:"我们已经将您的账号下发至你的邮箱，请注意查收",type:"success"})}).catch(function(){t.loading=!1})})},handleFindPassword:function(){var t=this;this.$refs.findForm.validate(function(e){if(!e)return console.log("error submit!!"),!1;t.loading=!0,Object(m.d)(t.findForm.email).then(function(){t.loading=!1,t.$message({message:"我们已经将你的密码下发至您的邮箱，请注意查收。",type:"success"})}).catch(function(){t.loading=!1})})},openRegister:function(){this.showFindPassword=!1,this.showLogin=!1,this.showRegister=!0},openFindPassword:function(){this.showRegister=!1,this.showLogin=!1,this.showFindPassword=!0},handleSelect:function(t,e){},openLogin:function(){this.showRegister=!1,this.showFindPassword=!1,this.showLogin=!0}}},_={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("el-dialog",{attrs:{visible:t.showLogin,title:"登录",center:""},on:{"update:visible":function(e){t.showLogin=e}}},[n("div",{staticClass:"form-container"},[n("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{autoComplete:"on",model:t.loginForm,rules:t.formRules,"label-position":"left"}},[n("el-form-item",{attrs:{prop:"id"}},[n("el-input",{attrs:{name:"id",type:"text",autoComplete:"on",placeholder:"id"},model:{value:t.loginForm.id,callback:function(e){t.$set(t.loginForm,"id",e)},expression:"loginForm.id"}},[n("svg-icon",{attrs:{slot:"prefix","icon-class":"user"},slot:"prefix"})],1)],1),t._v(" "),n("el-form-item",{attrs:{prop:"password"}},[n("el-input",{attrs:{name:"password",type:t.passwordType,autoComplete:"on",placeholder:"password"},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key))return null;t.handleLogin(e)}},model:{value:t.loginForm.password,callback:function(e){t.$set(t.loginForm,"password",e)},expression:"loginForm.password"}},[n("svg-icon",{attrs:{slot:"prefix","icon-class":"password"},slot:"prefix"}),t._v(" "),n("span",{staticClass:"show-pwd",attrs:{slot:"suffix"},on:{click:t.showPwd},slot:"suffix"},[n("svg-icon",{attrs:{"icon-class":"eye"}})],1)],1)],1),t._v(" "),n("el-form-item",{staticStyle:{display:"flex","justify-content":"space-between"},attrs:{prop:"type"}},[n("el-radio",{attrs:{label:"worker"},model:{value:t.loginForm.type,callback:function(e){t.$set(t.loginForm,"type",e)},expression:"loginForm.type"}},[t._v("众包工人")]),t._v(" "),n("el-radio",{attrs:{label:"requester"},model:{value:t.loginForm.type,callback:function(e){t.$set(t.loginForm,"type",e)},expression:"loginForm.type"}},[t._v("发包企业")]),t._v(" "),n("el-radio",{attrs:{label:"admin"},model:{value:t.loginForm.type,callback:function(e){t.$set(t.loginForm,"type",e)},expression:"loginForm.type"}},[t._v("管理员")])],1),t._v(" "),n("el-button",{staticStyle:{width:"100%"},attrs:{type:"primary",loading:t.loading},nativeOn:{click:function(e){e.preventDefault(),t.handleLogin(e)}}},[t._v(t._s(t.$t("login.logIn")))]),t._v(" "),n("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[n("el-button",{attrs:{type:"text"},on:{click:t.openFindPassword}},[t._v("忘记密码？点此找回")]),t._v(" "),n("el-button",{attrs:{type:"text"},on:{click:t.openRegister}},[t._v("没有账号？注册一个")])],1)],1)],1)]),t._v(" "),n("el-dialog",{attrs:{visible:t.showRegister,title:"注册",center:""},on:{"update:visible":function(e){t.showRegister=e}}},[n("div",{staticClass:"form-container"},[n("el-form",{ref:"registerForm",staticClass:"login-form",attrs:{autoComplete:"on",model:t.registerForm,rules:t.formRules,"label-position":"left"}},[n("el-form-item",{attrs:{prop:"email"}},[n("el-input",{attrs:{name:"username",type:"text",autoComplete:"on",placeholder:"邮箱"},model:{value:t.registerForm.email,callback:function(e){t.$set(t.registerForm,"email",e)},expression:"registerForm.email"}},[n("svg-icon",{attrs:{slot:"prefix","icon-class":"email"},slot:"prefix"})],1)],1),t._v(" "),n("el-form-item",{attrs:{prop:"nickname"}},[n("el-input",{attrs:{name:"username",type:"text",autoComplete:"on",placeholder:"昵称"},model:{value:t.registerForm.nickname,callback:function(e){t.$set(t.registerForm,"nickname",e)},expression:"registerForm.nickname"}},[n("svg-icon",{attrs:{slot:"prefix","icon-class":"user"},slot:"prefix"})],1)],1),t._v(" "),n("el-form-item",{attrs:{prop:"password"}},[n("el-input",{attrs:{name:"password",type:t.passwordType,autoComplete:"on",placeholder:"密码"},nativeOn:{keyup:function(e){if(!("button"in e)&&t._k(e.keyCode,"enter",13,e.key))return null;t.handleRegister(e)}},model:{value:t.registerForm.password,callback:function(e){t.$set(t.registerForm,"password",e)},expression:"registerForm.password"}},[n("svg-icon",{attrs:{slot:"prefix","icon-class":"password"},slot:"prefix"}),t._v(" "),n("span",{staticClass:"show-pwd",attrs:{slot:"suffix"},on:{click:t.showPwd},slot:"suffix"},[n("svg-icon",{attrs:{"icon-class":"eye"}})],1)],1)],1),t._v(" "),n("el-form-item",{staticStyle:{display:"flex","justify-content":"space-between"},attrs:{prop:"role"}},[n("el-radio",{attrs:{label:"worker"},model:{value:t.registerForm.role,callback:function(e){t.$set(t.registerForm,"role",e)},expression:"registerForm.role"}},[t._v("众包工人")]),t._v(" "),n("el-radio",{attrs:{label:"requester"},model:{value:t.registerForm.role,callback:function(e){t.$set(t.registerForm,"role",e)},expression:"registerForm.role"}},[t._v("发包企业")])],1),t._v(" "),n("el-button",{staticStyle:{width:"100%"},attrs:{type:"primary",loading:t.loading},nativeOn:{click:function(e){e.preventDefault(),t.handleRegister(e)}}},[t._v("注册")]),t._v(" "),n("div",{staticStyle:{display:"flex","justify-content":"flex-end"}},[n("el-button",{attrs:{type:"text"},on:{click:t.openLogin}},[t._v("已有账号？去登录")])],1)],1)],1)]),t._v(" "),n("el-dialog",{attrs:{visible:t.showFindPassword,title:"找回密码",center:""},on:{"update:visible":function(e){t.showFindPassword=e}}},[n("div",{staticClass:"form-container"},[n("el-form",{ref:"findForm",staticClass:"login-form",attrs:{autoComplete:"on",model:t.findForm,rules:t.formRules,"label-position":"left"}},[n("el-form-item",{attrs:{prop:"email"}},[n("el-input",{attrs:{name:"username",type:"text",autoComplete:"on",placeholder:"邮箱"},model:{value:t.findForm.email,callback:function(e){t.$set(t.findForm,"email",e)},expression:"findForm.email"}},[n("svg-icon",{attrs:{slot:"prefix","icon-class":"email"},slot:"prefix"})],1)],1),t._v(" "),n("el-button",{staticStyle:{width:"100%"},attrs:{type:"primary",loading:t.loading},nativeOn:{click:function(e){e.preventDefault(),t.handleFindPassword(e)}}},[t._v("找回")]),t._v(" "),n("div",{staticStyle:{display:"flex","justify-content":"flex-end"}},[n("el-button",{attrs:{type:"text"},on:{click:t.openLogin}},[t._v("去登录")])],1)],1)],1)]),t._v(" "),n("el-row",{staticClass:"nav",staticStyle:{position:"fixed",top:"0",left:"0","z-index":"999",width:"100%"}},[n("el-col",{staticClass:"nav",attrs:{span:20}},[n("el-menu",{staticClass:"nav",attrs:{"default-active":t.activeIndex2,mode:"horizontal","background-color":"#199475","text-color":"#413C35","active-text-color":"#883A2B"},on:{select:t.handleSelect}},[n("el-menu-item",{attrs:{index:"1"}},[t._v("首页")]),t._v(" "),n("el-menu-item",{attrs:{index:"2"}},[t._v("关于众包")]),t._v(" "),n("el-menu-item",{attrs:{index:"3"}},[t._v("关于标注")])],1)],1),t._v(" "),n("el-col",{attrs:{span:4}},[n("el-menu",{staticClass:"nav",staticStyle:{display:"flex","justify-content":"flex-end"},attrs:{"default-active":t.activeIndex2,mode:"horizontal","text-color":"#413C35","active-text-color":"#883A2B"},on:{select:t.handleSelect}},[n("el-button",{staticClass:"navButton",attrs:{plain:""},on:{click:t.openLogin}},[t._v("登录")]),t._v(" "),n("el-button",{staticClass:"navButton",attrs:{plain:""},on:{click:t.openRegister}},[t._v("注册")])],1)],1)],1),t._v(" "),n("el-carousel",{staticStyle:{"margin-top":"60px"},attrs:{arrow:"always",type:"card",interval:5e3,trigger:"click",height:"300px"}},t._l(3,function(e){return n("el-carousel-item",{key:e},[n("img",{staticStyle:{width:"100%",height:"100%"},attrs:{src:t.slides[e-1]}})])})),t._v(" "),t._m(0),t._v(" "),n("div",{staticClass:"each-part",attrs:{id:"summary"}},[n("div",{staticClass:"group"},[n("div",{staticClass:"title"},[t._v("\n        平台概述\n      ")]),t._v(" "),n("div",{staticClass:"content"},[n("p",{staticClass:"text",attrs:{"ui-type":"text"}},[t._v("\n          SECIII众包标注平台提供一站式的数据众包服务，可根据特定领域、特定场景的客户需求，提供定制化的数据获取与加工方案的设计与执行服务， 为客户交付标准化结构化的可用数据。\n        ")]),t._v(" "),n("div",{staticStyle:{display:"flex","justify-content":"center"}},[n("img",{attrs:{src:t.summuryPic}})])])])]),t._v(" "),t._m(1),t._v(" "),t._m(2),t._v(" "),t._m(3),t._v(" "),t._m(4)],1)},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticStyle:{display:"flex","justify-content":"center",color:"#413c35"}},[e("h2",[this._v("SEC众包标注平台")])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"each-part",attrs:{id:"features"}},[n("div",{staticClass:"group"},[n("div",{staticClass:"title"},[t._v("\n        平台功能\n      ")]),t._v(" "),n("div",{staticClass:"content"},[n("div",{staticClass:"content-frame"},[n("div",{staticStyle:{display:"flex","justify-content":"space-between"}},[n("div",[n("ol",[t._v("\n                数据采集：\n                "),n("li",[t._v("人脸识别")]),t._v(" "),n("li",[t._v("马路描边")]),t._v(" "),n("li",[t._v("动物识别")]),t._v(" "),n("li",[t._v("音频视频文本（可以考虑尝试完成）")])])]),t._v(" "),n("div",[n("ol",[t._v("\n                数据标注：\n                "),n("li",[t._v("整体标注")]),t._v(" "),n("li",[t._v("方框标注")]),t._v(" "),n("li",[t._v("边界标注")]),t._v(" "),n("li",[t._v("音频视频文本标注（可以考虑尝试完成）")])])])])])])])])},function(){var t=this.$createElement,e=this._self._c||t;return e("div",{staticClass:"each-part",attrs:{id:"advantages"}},[e("div",{staticClass:"group"},[e("div",{staticClass:"title"},[this._v("\n        产品优势\n      ")]),this._v(" "),e("div",{staticClass:"content"},[e("p",{staticClass:"text",attrs:{"ui-type":"text"}},[this._v("\n          可能就只有免费使用免费注册干什么都不要钱吧！哈哈哈哈哈！\n        ")])])])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{staticClass:"each-part",attrs:{id:"processes"}},[n("div",{staticClass:"group"},[n("div",{staticClass:"title"},[t._v("\n        使用步骤\n      ")]),t._v(" "),n("div",{staticClass:"content"},[n("div",{staticStyle:{display:"flex","justify-content":"flex-start"}},[n("div",{staticStyle:{width:"50%"}},[n("ol",[t._v("\n              众包工人：\n              "),n("li",[t._v("注册登录")]),t._v(" "),n("li",[t._v("接取任务")]),t._v(" "),n("li",[t._v("提交任务")]),t._v(" "),n("li",[t._v("领取奖励")])])]),t._v(" "),n("div",{staticStyle:{width:"50%"}},[n("ol",[t._v("\n              发包者：\n              "),n("li",[t._v("建立任务")]),t._v(" "),n("li",[t._v("平台分发")]),t._v(" "),n("li",[t._v("工人标注")]),t._v(" "),n("li",[t._v("数据交付")])])])])])])])},function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",{attrs:{id:"footer"}},[n("div",[n("h3",[t._v(" Contact:")]),t._v(" "),n("p",{attrs:{id:"footer-contact"}},[t._v("\n        地址： 九乡河大学仙林校区"),n("br"),t._v("\n        Phone: 12345678910"),n("br"),t._v("\n        Email: shawandshaw@163.com"),n("br")])]),t._v(" "),n("div",[n("h3",[t._v("Links")]),t._v(" "),n("p",[n("a",{attrs:{href:"#"}},[t._v(" About ( Who we are )"),n("br")]),t._v(" "),n("a",{attrs:{href:"#"}},[t._v(" Services ( What we do )"),n("br")]),t._v(" "),n("a",{attrs:{href:"#"}},[t._v(" Contact ( Feel free to contact )"),n("br")])])]),t._v(" "),n("div",[n("h3",[t._v("\n        Socialize\n      ")]),t._v(" "),n("div",{attrs:{id:"social-icons"}},[n("a",{staticClass:"btn-group google-plus",attrs:{href:"#"}}),t._v(" "),n("a",{staticClass:"btn-group linkedin",attrs:{href:"#"}}),t._v(" "),n("a",{staticClass:"btn-group twitter",attrs:{href:"#"}}),t._v(" "),n("a",{staticClass:"btn-group facebook",attrs:{href:"#"}})])])])}]};var b=n("VU/8")(h,_,!1,function(t){n("Lhq0")},"data-v-2b7507b2",null);e.default=b.exports},"1yrv":function(t,e,n){(t.exports=n("FZ+f")(!1)).push([t.i,'\n.nav[data-v-2b7507b2] {\n  background-color: #199475;\n}\n.navButton[data-v-2b7507b2] {\n  height: 60px;\n  background-color: #199475;\n  color: #413C35;\n  border: none;\n  border-radius: 0;\n}\n.navButton[data-v-2b7507b2]:hover {\n  color: #413C35;\n  background-color: #14765E;\n}\n.navButton[data-v-2b7507b2]:focus {\n  color: #413C35;\n  background-color: #14765E;\n}\n.each-part[data-v-2b7507b2] {\n  padding: 0 0 40px 0;\n  overflow: hidden;\n  width: 100%;\n}\n.each-part .group[data-v-2b7507b2] {\n  width: 1180px;\n  margin: 0 auto;\n}\n.each-part img[data-v-2b7507b2] {\n  margin-left: 30px;\n}\n.each-part .content ol[data-v-2b7507b2] {\n  list-style-type: square;\n  font-size: 24px;\n  padding: 30px;\n}\n.each-part .content ol li[data-v-2b7507b2] {\n  padding: 10px;\n  margin-left: 30px;\n  font-size: 20px;\n}\n.group .title[data-v-2b7507b2] {\n  margin: 0px 0 30px 20px;\n  font-size: 24px;\n  color: #141a24;\n  padding-left: 14px;\n  position: relative;\n}\n.group .title[data-v-2b7507b2]::before {\n  content: "";\n  display: block;\n  position: absolute;\n  width: 8px;\n  bottom: 3px;\n  top: 6px;\n  left: 0px;\n  background: #199475;\n}\np[ui-type="text"][data-v-2b7507b2] {\n  font-size: 16px;\n  line-height: 24px;\n  color: #333;\n  margin: 1em 30px;\n}\n\n/*i{*/\n\n/*position: absolute;*/\n\n/*width: 40px;*/\n\n/*display: block;*/\n\n/*height: 4px;*/\n\n/*background: #199475;*/\n\n/*bottom: 0;*/\n\n/*transition: left .5s ease-out;*/\n\n/*}*/\n#footer[data-v-2b7507b2] {\n  padding: 0 10px;\n  width: 100%;\n  height: auto;\n  background-color: #413c35;\n  color: #fff;\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: justify;\n      -ms-flex-pack: justify;\n          justify-content: space-between;\n}\n',""])},E4LH:function(t,e,n){"use strict";e.b=function(t){return["admin","worker","requester"].indexOf(t.trim())>=0},e.a=function(t){return/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(t)},e.c=function(t){return/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/.test(t)}},Lhq0:function(t,e,n){var i=n("1yrv");"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);n("rjj0")("29fc9fd0",i,!0)},VDDH:function(t,e){t.exports="data:image/png;base64,"},VNx6:function(t,e,n){t.exports=n.p+"static/img/slides4.4e4d5ca.jpg"},Wfvl:function(t,e,n){t.exports=n.p+"static/img/introduce_features2.0d85f47.png"},XKvO:function(t,e,n){t.exports=n.p+"static/img/introduce_summary.6622478.png"},"do9+":function(t,e,n){t.exports=n.p+"static/img/slides2.5360ff7.jpg"},"iD+W":function(t,e,n){t.exports=n.p+"static/img/slides3.be13b9f.png"}});