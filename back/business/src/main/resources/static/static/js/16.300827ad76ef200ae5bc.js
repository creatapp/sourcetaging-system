webpackJsonp([16],{"4iBx":function(t,e,l){(t.exports=l("FZ+f")(!1)).push([t.i,"\n.demo-table-expand {\n  font-size: 0;\n}\n.demo-table-expand label {\n  width: 90px;\n  color: #99a9bf;\n}\n.demo-table-expand .el-form-item {\n  margin-right: 0;\n  margin-bottom: 0;\n  width: 50%;\n}\n",""])},"R0c+":function(t,e,l){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var a=l("Dd8w"),s=l.n(a),r=l("NYxO"),o={props:["missions"],computed:s()({},Object(r.b)(["roles"])),methods:{filterStatus:function(t,e){return e.status===t}}},n={render:function(){var t=this,e=t.$createElement,l=t._self._c||e;return l("el-table",{staticStyle:{width:"100%"},attrs:{data:t.missions,height:"550","default-sort":{prop:"id",order:"ascending"}}},[l("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[l("el-form-item",{attrs:{label:"任务 ID"}},[l("span",[t._v(t._s(e.row.id))])]),t._v(" "),l("el-form-item",{attrs:{label:"任务标题"}},[l("span",[t._v(t._s(e.row.title))])]),t._v(" "),l("el-form-item",{attrs:{label:"发布日期"}},[l("span",[t._v(t._s(e.row.publishDate))])]),t._v(" "),l("el-form-item",{attrs:{label:"完结日期"}},[l("span",[t._v(t._s(e.row.finishDate))])]),t._v(" "),l("el-form-item",{attrs:{label:"任务描述"}},[l("span",[t._v(t._s(e.row.description))])]),t._v(" "),l("el-form-item",{attrs:{label:"任务类型"}},[l("span",[t._v(t._s(e.row.type))])]),t._v(" "),l("el-form-item",{attrs:{label:"任务标签"}},[l("span",[t._v(t._s(e.row.classLabel))])]),t._v(" "),l("el-form-item",{attrs:{label:"任务总积分"}},[l("span",[t._v(t._s(e.row.totalPoints))])]),t._v(" "),l("el-form-item",{attrs:{label:"已完成图片"}},[l("span",[t._v(t._s(e.row.done))])]),t._v(" "),l("el-form-item",{attrs:{label:"图片总数"}},[l("span",[t._v(t._s(e.row.total))])]),t._v(" "),t.roles.indexOf("admin")>=0?l("el-form-item",{attrs:{label:"发布者"}},[l("span",[t._v(t._s(e.row.requester))])]):t._e(),t._v(" "),l("el-form-item",{attrs:{label:"参与者"}},[l("el-button",{attrs:{size:"small"}},[t._v("查看参与者")])],1)],1)]}}])}),t._v(" "),l("el-table-column",{attrs:{label:"任务 ID",sortable:"",prop:"id"}}),t._v(" "),l("el-table-column",{attrs:{label:"任务标题",prop:"title"}}),t._v(" "),l("el-table-column",{attrs:{label:"发布日期",sortable:"",prop:"publishDate"}}),t._v(" "),l("el-table-column",{attrs:{label:"任务状态",prop:"status",filters:[{text:"doing",value:"doing"},{text:"done",value:"done"}],"filter-method":t.filterStatus,"filter-placement":"bottom-end"},scopedSlots:t._u([{key:"default",fn:function(e){return[l("el-tag",{attrs:{type:"doing"===e.row.status?"danger":"success","disable-transitions":""}},[t._v(t._s(e.row.status))])]}}])})],1)},staticRenderFns:[]};var i=l("VU/8")(o,n,!1,function(t){l("b7ay")},null,null);e.default=i.exports},b7ay:function(t,e,l){var a=l("4iBx");"string"==typeof a&&(a=[[t.i,a,""]]),a.locals&&(t.exports=a.locals);l("rjj0")("2398c448",a,!0)}});