webpackJsonp([6],{"3KuP":function(t,n,e){var a=e("omPd");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);e("rjj0")("fab73560",a,!0)},MrJj:function(t,n,e){"use strict";Object.defineProperty(n,"__esModule",{value:!0});var a=e("BVtZ"),o={data:function(){return{coverImage:"https://wpimg.wallstcn.com/e7d23d71-cf19-4b90-a1cc-f56af8c0903d.png"}},props:{mission:{type:Object,default:function(){return{id:"m0001",classLabel:[1,2,3],publishDate:"2018-5-20",title:"苹果画框",kind:"tag",description:"框出图中苹果的边界",price:3}}}},computed:{labelString:function(){for(var t=["游戏","人物","生活","军事","风景","建筑","艺术","影视","工业","交通","书籍","食物","玩具","用具","服饰","体育","医疗","天文","标志","其他"],n=[],e=0;e<this.mission.classLabel.length;e++)n.push(t[this.mission.classLabel[e]]);return n.join(" / ")},kind:function(){return{tag:"标注",check:"检查"}[this.mission.kind]}},methods:{signUpMission:function(t,n){var e=this;Object(a.n)(t,n).then(function(t){t.data.result?e.$message({message:"您已经成功注册此任务，可在您的主页中查看",type:"success"}):e.$message({message:t.data.message,type:"warning"})})}}},i={render:function(){var t=this,n=t.$createElement,e=t._self._c||n;return e("el-card",{staticClass:"box-card-component",staticStyle:{margin:"10px"}},[e("div",{staticClass:"box-card-header",attrs:{slot:"header"},slot:"header"},[e("img",{attrs:{src:t.coverImage}})]),t._v(" "),e("div",{staticStyle:{position:"relative"}},[e("el-row",{attrs:{type:"flex",justify:"space-between"}},[e("div",[t._v(t._s(t.labelString))])]),t._v(" "),e("el-row",{attrs:{type:"flex",justify:"space-between",align:"bottom"}},[e("div",{staticStyle:{"font-size":"23px"}},[t._v(t._s(t.mission.title))]),t._v(" "),t._t("btn")],2),t._v(" "),e("el-row",[e("div",{staticStyle:{color:"#999"}},[t._v(" "+t._s(t.mission.description))])]),t._v(" "),t._t("rate"),t._v(" "),e("el-row",{attrs:{type:"flex",justify:"space-between"}},[e("el-tooltip",{attrs:{effect:"dark",content:"标注价",placement:"top"}},[e("el-tag",{attrs:{type:"info"}},[t._v(t._s(t.mission.tagPrice)+"分/张")])],1),t._v(" "),e("el-button",{staticStyle:{padding:"3px 0px"},attrs:{type:"text",size:"small"},on:{click:function(n){t.signUpMission(t.mission.id,"tag")}}},[t._v("参与标注")]),t._v(" "),e("el-tooltip",{attrs:{effect:"dark",content:"检查价",placement:"top"}},[e("el-tag",{attrs:{type:"info"}},[t._v(t._s(t.mission.checkPrice)+"分/张")])],1),t._v(" "),e("el-button",{staticStyle:{padding:"3px 0px"},attrs:{type:"text",size:"small"},on:{click:function(n){t.signUpMission(t.mission.id,"check")}}},[t._v("参与检查")])],1)],2)])},staticRenderFns:[]};var s=e("VU/8")(o,i,!1,function(t){e("VdIt"),e("3KuP")},"data-v-7af6fc76",null);n.default=s.exports},V0KV:function(t,n,e){(t.exports=e("FZ+f")(!1)).push([t.i,"\n.el-row {\n  margin-bottom: 10px;\n}\n.el-row:last-child {\n    margin-bottom: 0;\n}\n.box-card-component {\n  width: 270px;\n}\n.box-card-component .el-card__header {\n    padding: 0px !important;\n}\n",""])},VdIt:function(t,n,e){var a=e("V0KV");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);e("rjj0")("acbb0240",a,!0)},omPd:function(t,n,e){(t.exports=e("FZ+f")(!1)).push([t.i,"\n.box-card-component .box-card-header[data-v-7af6fc76] {\n  position: relative;\n  height: 200px;\n}\n.box-card-component .box-card-header img[data-v-7af6fc76] {\n    width: 100%;\n    height: 100%;\n    -webkit-transition: all 0.2s linear;\n    transition: all 0.2s linear;\n}\n.box-card-component .box-card-header img[data-v-7af6fc76]:hover {\n      -webkit-transform: scale(1.1, 1.1);\n              transform: scale(1.1, 1.1);\n      -webkit-filter: contrast(130%);\n              filter: contrast(130%);\n}\n.box-card-component .time[data-v-7af6fc76] {\n  font-size: 15px;\n  color: #999;\n}\n.box-card-component .mallki-text[data-v-7af6fc76] {\n  position: absolute;\n  top: 0px;\n  right: 0px;\n  font-size: 20px;\n  font-weight: bold;\n}\n.box-card-component .panThumb[data-v-7af6fc76] {\n  z-index: 100;\n  height: 70px !important;\n  width: 70px !important;\n  position: absolute !important;\n  top: -45px;\n  left: 0px;\n  border: 5px solid #ffffff;\n  background-color: #fff;\n  margin: auto;\n  -webkit-box-shadow: none !important;\n          box-shadow: none !important;\n}\n.box-card-component .panThumb[data-v-7af6fc76] .pan-info {\n    -webkit-box-shadow: none !important;\n            box-shadow: none !important;\n}\n.box-card-component .progress-item[data-v-7af6fc76] {\n  margin-bottom: 10px;\n  font-size: 14px;\n}\n@media only screen and (max-width: 1510px) {\n.box-card-component .mallki-text[data-v-7af6fc76] {\n    display: none;\n}\n}\n",""])}});