webpackJsonp([12],{"+zj5":function(t,e,i){(t.exports=i("FZ+f")(!1)).push([t.i,"\n#toolbar[data-v-6aba73e0] {\n  display: -webkit-box;\n  display: -ms-flexbox;\n  display: flex;\n  -webkit-box-pack: justify;\n      -ms-flex-pack: justify;\n          justify-content: space-between;\n  -webkit-box-align: baseline;\n      -ms-flex-align: baseline;\n          align-items: baseline;\n  /*margin-bottom: 12px;*/\n  /*border-bottom: gray 2px solid;*/\n  padding: 4.8px 48px;\n  background-color: #413c35;\n  color: #fff;\n  font-size: 14px;\n}\n.info[data-v-6aba73e0] {\n  background-color: #1BBD9B;\n  color: #fff;\n  font-size: 14px;\n  /*padding: 4px;*/\n}\n#picture[data-v-6aba73e0] {\n  background-size: 100% 100%;\n  background-repeat: no-repeat;\n  width: 726px;\n  height: 411px;\n  border: #000 dashed 3px;\n}\n",""])},FgUC:function(t,e,i){var s=i("+zj5");"string"==typeof s&&(s=[[t.i,s,""]]),s.locals&&(t.exports=s.locals);i("rjj0")("07bfdae8",s,!0)},WozV:function(t,e,i){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=i("BVtZ"),a={name:"pic-tag-view",data:function(){return{mission:{id:"",type:"",title:"",description:"",done:0,total:0},checkAnswer:!1,picTagObj:{authorId:0,picData:"",picId:"",mark:"",tag:"",time:0},doneImages:{index:-1,images:[]},startAt:0}},computed:{drawMode:function(){return{border_mark:1,frame_mark:2,comment:3,border_mc:4,frame_mc:5}[this.mission.type]},ChineseTagType:function(){return{border_mark:"边界标记",frame_mark:"方框标记",comment:"整体评注",border_mc:"边界标记+评注",frame_mc:"方框标记+评注"}[this.mission.type]},canvas:function(){return document.getElementById("myCanvas")},ctx:function(){return document.getElementById("myCanvas").getContext("2d")},styleObj:function(){return{backgroundImage:'url("'+this.picTagObj.picData+'")'}},left:function(){return this.mission.total-this.mission.done}},methods:{onClearCanvas:function(){this.clearCanvas(),this.addVersion()},onNext:function(){var t=this,e=this;this.doneImages.index<=this.doneImages.images.length-2?this.updateTheAnswer().then(function(t){t.data.result?e.nextOld():e.$message({message:t.data.message,type:"danger"})}):this.postTheAnswer().then(function(i){i.data.result?(e.mission.done++,e.mission.done<e.mission.total?e.getNewPic():t.mission.done===t.mission.total&&(t.$message({message:"您已经顺利完成此任务，可在已完成列表中查看。",type:"success"}),t.$router.push({path:"/dashboard"}))):e.$message({message:i.data.message,type:"danger"})})},onLast:function(){this.preOld()||this.$message({message:"这已经是第一张图了",type:"warning"})},clearCanvas:function(){this.ctx.clearRect(0,0,this.canvas.width,this.canvas.height)},fillCanvasWith:function(t){var e=new Image;e.setAttribute("crossOrigin","anonymous"),e.src=t;var i=this;e.onload=function(){i.ctx.drawImage(e,0,0,i.canvas.width,i.canvas.height)}},getNewPic:function(){var t=this;this.loadingPic=!0,Object(s.b)(this.mission.id,"check",0).then(function(e){t.loadingPic=!1,e.data.result?(t.picTagObj=e.data.message,t.clearCanvas(),t.fillCanvasWith(t.picTagObj.mark),t.oldImgAdd(t.picTagObj),t.startAt=(new Date).getTime()):t.$message({message:"获取图片失败",type:"warning"})})},oldImgAdd:function(t){this.doneImages.images.push(t),this.doneImages.index++},nextOld:function(){return this.startAt=(new Date).getTime(),this.doneImages.index<=this.doneImages.images.length-2&&(this.picTagObj=this.doneImages.images[++this.doneImages.index],this.clearCanvas(),this.fillCanvasWith(this.picTagObj.mark),!0)},preOld:function(){return this.startAt=(new Date).getTime(),!(this.doneImages.index-1<0)&&(this.picTagObj=this.doneImages.images[--this.doneImages.index],this.clearCanvas(),this.fillCanvasWith(this.picTagObj.mark),!0)},postTheAnswer:function(){return this.picTagObj.authorId=this.$store.state.user.id,this.picTagObj.mark=this.canvas.toDataURL(),this.picTagObj.time=(new Date).getTime()-this.startAt,Object(s.l)(this.mission.id,this.picTagObj.picId,this.picTagObj.authorId,this.checkAnswer,this.picTagObj.time)},updateTheAnswer:function(){return this.picTagObj.authorId=this.$store.state.user.id,this.picTagObj.mark=this.canvas.toDataURL(),this.picTagObj.time+=(new Date).getTime()-this.startAt,Object(s.o)(this.mission.id,this.picTagObj.picId,this.picTagObj.authorId,this.checkAnswer,this.picTagObj.time)}},created:function(){this.mission=this.$store.state.mission.currentMission},mounted:function(){this.getNewPic()}},n={render:function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{attrs:{id:"viewBox"}},[i("div",{attrs:{id:"toolbar"}},[i("el-card",{staticClass:"info"},[i("div",{attrs:{slot:"header"},slot:"header"},[t._v(t._s("任务要求： ("+t.ChineseTagType+")"))]),t._v(" "),i("div",{attrs:{id:"desc"}},[t._v(t._s(t.mission.description))])]),t._v(" "),i("el-card",{staticClass:"info"},[i("div",{attrs:{slot:"header"},slot:"header"},[t._v("图片总数："+t._s(t.mission.total))]),t._v(" "),i("div",[t._v("剩余："+t._s(t.left))])])],1),t._v(" "),i("div",{staticStyle:{"margin-bottom":"12px"}},[i("el-progress",{staticStyle:{padding:"0"},attrs:{percentage:t.mission.done/t.mission.total*100,"stroke-width":4,"show-text":!1,"text-inside":""}})],1),t._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"space-around"},attrs:{id:"main-content"}},[i("div",{staticClass:"arrow",staticStyle:{display:"flex","align-items":"center"}},[i("el-button",{attrs:{type:"info",plain:"",icon:"el-icon-d-arrow-left"},on:{click:t.onLast}},[i("b",[t._v("上一张")])])],1),t._v(" "),i("div",{style:t.styleObj,attrs:{id:"picture"}},[i("canvas",{attrs:{id:"myCanvas",width:"720px",height:"405px"}})]),t._v(" "),i("div",{staticClass:"arrow",staticStyle:{display:"flex","align-items":"center"}},[i("el-button",{attrs:{type:"info",plain:"",loading:this.loadingPic},on:{click:t.onNext}},[i("b",[t._v("下一张")]),i("i",{staticClass:"el-icon-d-arrow-right el-icon--right"})])],1)]),t._v(" "),i("div",{directives:[{name:"show",rawName:"v-show",value:t.drawMode>2,expression:"drawMode>2"}],staticStyle:{display:"flex","justify-content":"center","margin-top":"4px"},attrs:{id:"tag"}},[i("div",{staticStyle:{width:"720px"}},[t._m(0),i("el-input",{attrs:{required:"",disabled:"",placeholder:"无评注"},model:{value:t.picTagObj.tag,callback:function(e){t.$set(t.picTagObj,"tag",e)},expression:"picTagObj.tag"}})],1)]),t._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"center"}},[i("p",{staticStyle:{"font-size":"16px"}},[i("b",[t._v("请您对这张图片标注的准确性作出评估>>>")]),t._v(" "),i("el-radio-group",{model:{value:t.checkAnswer,callback:function(e){t.checkAnswer=e},expression:"checkAnswer"}},[i("el-radio-button",{attrs:{label:!0,"text-color":"#00ff00",fill:"#67C23A"}},[i("i",{staticClass:"el-icon-success"}),t._v("准确无误")]),t._v(" "),i("el-radio-button",{attrs:{label:!1,fill:"#F56C6C"}},[i("i",{staticClass:"el-icon-error"}),t._v("误差较大")])],1)],1)])])},staticRenderFns:[function(){var t=this.$createElement,e=this._self._c||t;return e("span",[e("b",[this._v("评注>>>：")])])}]};var o=i("VU/8")(a,n,!1,function(t){i("FgUC")},"data-v-6aba73e0",null);e.default=o.exports}});