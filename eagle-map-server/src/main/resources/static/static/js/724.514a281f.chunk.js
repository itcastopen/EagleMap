"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[724],{7993:function(e,t,n){n.d(t,{CQ:function(){return h},$I:function(){return g},bI:function(){return f},u9:function(){return p},n3:function(){return c},O2:function(){return v},iy:function(){return m},Bh:function(){return d},r4:function(){return u},xt:function(){return o}});var r=n(4569),i=n.n(r),a=n(9012),s=n(2268);function l(e){return i().request(e)}function o(e){return l({method:"POST",url:"/sys/login",data:e,params:e})}function c(){return l({method:"GET",url:"/sys/config"})}function u(e){return l({method:"GET",url:"/sys/trace/server",data:e,params:e})}function d(e){return l({method:"GET",url:"/sys/trace",data:e,params:e})}function m(e){return l({method:"GET",url:"/sys/trace/image",data:e,params:e})}function f(e){return l({method:"DELETE",url:"/sys/trace/".concat(e.id),data:e,params:e})}function p(e){return l({method:"GET",url:"/sys/fence",data:e,params:e})}function h(e){return l({method:"DELETE",url:"/sys/fence/".concat(e.fenceId),data:e,params:e})}function v(e){return l({method:"GET",url:"/sys/terminal",data:e,params:e})}function g(e){return l({method:"DELETE",url:"/sys/terminal/".concat(e.id),data:e,params:e})}i().defaults.headers={"Content-Type":"application/json;charset=utf-8"},i().defaults.baseURL="",i().interceptors.request.use((function(e){var t=(0,s.LP)();return t&&(e.headers.Authorization=t),e}),(function(e){return Promise.reject(e)})),i().interceptors.response.use((function(e){return e.data?200===e.status?e.data:(401!==e.status&&401!==e.data.code||((0,s.gy)(),window.location.href="/login"),a.sX.error(e.data.msg),Promise.reject(new Error(e.data.msg))):Promise.resolve(e)}),(function(e){if(a.sX.error(e.message),401!==e.response.status)return Promise.reject(e);(0,s.gy)(),window.location.href="/login"}))},6873:function(e,t,n){n.d(t,{Z:function(){return a}});var r=n(1350),i=n(184);function a(){return(0,i.jsxs)("div",{className:"noData",children:[(0,i.jsx)("img",{src:r,width:"245",height:"201",alt:""}),(0,i.jsx)("div",{children:"\u6ca1\u6709\u6570\u636eorz"})]})}},6885:function(e,t,n){n.d(t,{v:function(){return u}});var r=n(5671),i=n(3144),a=n(136),s=n(5716),l=n(2791),o=n(5594),c=n(184),u=function(e){(0,a.Z)(n,e);var t=(0,s.Z)(n);function n(){return(0,r.Z)(this,n),t.apply(this,arguments)}return(0,i.Z)(n,[{key:"componentDidMount",value:function(){new o.Z(this.props.options)}},{key:"render",value:function(){return(0,c.jsx)("div",{id:"baidumap",style:{width:"100%",height:"560px"}})}}]),n}(l.Component)},4931:function(e,t,n){n.r(t);n(8683);var r=n(8152),i=n(6885),a=n(1082),s=n(2791),l=n(9012),o=n(9498),c=n(3307),u=n(7735),d=n(2819),m=n(6873),f=n(1937),p=n(7993),h=n(2426),v=n.n(h),g=n(184),x=a.a.BreadcrumbItem;t.default=function(e){e.match.params.id;var t=e.location.state,n=(0,s.useState)(1),h=(0,r.Z)(n,2),y=h[0],j=h[1],I=(0,s.useState)(10),N=(0,r.Z)(I,2),w=N[0],A=N[1],C=(0,s.useState)([]),P=(0,r.Z)(C,2),D=P[0],E=P[1],M=(0,s.useState)(30),Y=(0,r.Z)(M,2),b=Y[0],F=Y[1],T=(0,s.useState)(!1),S=(0,r.Z)(T,2),L=S[0],k=S[1],B=(0,s.useState)({}),O=(0,r.Z)(B,2),Z=O[0],K=O[1],W=(0,s.useState)(""),z=(0,r.Z)(W,1)[0],G="BAIDU"===t.provider?"BMAP":t.provider,H=JSON.parse(t.param),R=(0,f.eB)(G,t.type,H),X={strokeColor:"#6699FF",strokeWeight:2,strokeOpacity:1},Q={id:"baidumap",mapAk:"",poiText:!0,poiIcon:!0,type:G,points:[116.404,39.915],areaName:"\u4e0a\u6d77\u5e02",zoom:11,enableScrollWheel:!1,isScaleCtrl:!0,isZoomCtrl:!0,navi3DCtrl:!1,enclosure:X=Object.assign(X,R)};function U(e){var n,r;console.log(t),(0,p.O2)({page:null!==(n=null===e||void 0===e?void 0:e.page)&&void 0!==n?n:y,pageSize:null!==(r=null===e||void 0===e?void 0:e.pageSize)&&void 0!==r?r:w,terminalName:null===e||void 0===e?void 0:e.terminalName,terminalId:null===e||void 0===e?void 0:e.terminalId,fenceId:t.fenceId,provider:t.provider,serverId:t.serverId}).then((function(e){var t=e.data;0===e.code?(E(t.items),F(t.total)):l.sX.error("\u8bf7\u6c42\u51fa\u9519\uff01")}))}return(0,s.useEffect)((function(){U()}),[]),(0,g.jsxs)(g.Fragment,{children:[(0,g.jsx)("div",{className:"BreadcrumbCont",children:(0,g.jsxs)(a.a,{maxItemWidth:"200px",theme:"light",children:[(0,g.jsx)(x,{href:"/fence",children:"\u7535\u5b50\u56f4\u680f"}),(0,g.jsx)(x,{children:"\u8be6\u60c5"})]})}),(0,g.jsxs)("div",{className:"container trajectory_details",children:[(0,g.jsxs)("div",{className:"detailCont",children:[(0,g.jsx)(o.iA,{data:[t],columns:[{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u56f4\u680f\u540d\u79f0",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"fenceId",title:"\u56f4\u680fID",minWidth:200},{align:"left",className:"test4",ellipsis:!0,cell:function(e){var t=e.row;switch(null===t||void 0===t?void 0:t.type){case"DISTRICT":return(0,g.jsx)(g.Fragment,{children:"\u884c\u653f\u533a\u57df"});case"CIRCLE":return(0,g.jsx)(g.Fragment,{children:"\u5706\u5f62"});case"POLYGON":return(0,g.jsx)(g.Fragment,{children:"\u591a\u8fb9\u578b"});case"POLYLINE":return(0,g.jsx)(g.Fragment,{children:"\u7ebf\u5f62"});default:return null}},title:"\u56f4\u680f\u7c7b\u578b",minWidth:200},{align:"left",className:"test4",ellipsis:!0,cell:function(e){var t=e.row;switch(null===t||void 0===t?void 0:t.provider){case"BAIDU":return(0,g.jsx)(g.Fragment,{children:"\u767e\u5ea6\u5730\u56fe"});case"AMAP":return(0,g.jsx)(g.Fragment,{children:"\u9ad8\u5fb7\u5730\u56fe"});case"QQ":return(0,g.jsx)(g.Fragment,{children:"\u817e\u8baf\u5730\u56fe"});default:return null}},title:"\u670d\u52a1\u5546",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"serverId",title:"\u670d\u52a1ID",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"created",title:"\u521b\u5efa\u65f6\u95f4",cell:function(e){var t=e.row;return t.created?v()(t.created).format("YYYY-MM-DD HH:mm:ss"):"--"},minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"updated",title:"\u66f4\u65b0\u65f6\u95f4",cell:function(e){var t=e.row;return t.updated?v()(t.updated).format("YYYY-MM-DD HH:mm:ss"):"--"},minWidth:200}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0}),(0,g.jsx)("div",{className:"DetBMap",children:(0,g.jsx)(i.v,{options:Q})})]}),(0,g.jsx)("div",{className:"searchCont",children:(0,g.jsx)("div",{className:"tdesign-demo-block-row"})}),(0,g.jsxs)("div",{className:"tableCont",children:[D&&D.length>0?(0,g.jsx)(o.iA,{data:D,columns:[{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u8bbe\u5907\u540d\u79f0"},{align:"left",className:"test",ellipsis:!0,colKey:"provider",cell:function(e){var t=e.row;switch(null===t||void 0===t?void 0:t.provider){case"BAIDU":return(0,g.jsx)(g.Fragment,{children:"\u767e\u5ea6\u5730\u56fe"});case"AMAP":return(0,g.jsx)(g.Fragment,{children:"\u9ad8\u5fb7\u5730\u56fe"});case"QQ":return(0,g.jsx)(g.Fragment,{children:"\u817e\u8baf\u5730\u56fe"});default:return null}},title:"\u670d\u52a1\u5546"},{align:"left",className:"test4",ellipsis:!0,colKey:"serverId",title:"\u670d\u52a1ID"},{align:"left",className:"test4",ellipsis:!0,colKey:"created",cell:function(e){var t=e.row;return t.created?v()(t.created).format("YYYY-MM-DD HH:mm:ss"):"--"},title:"\u521b\u5efa\u65f6\u95f4"},{align:"left",className:"test4",ellipsis:!0,colKey:"updated",cell:function(e){var t=e.row;return t.updated?v()(t.updated).format("YYYY-MM-DD HH:mm:ss"):"--"},title:"\u66f4\u65b0\u65f6\u95f4"},{align:"left",className:"test4",ellipsis:!0,title:"\u8303\u56f4",cell:function(e){var t=e.row;switch(!(null!==t&&void 0!==t&&t.isOut)){case!0:return(0,g.jsx)("div",{className:"succ",children:"\u672a\u8d85\u51fa"});case!1:return(0,g.jsx)("div",{className:"fail",children:"\u5df2\u8d85\u51fa"});default:return(0,g.jsx)(g.Fragment,{children:"--"})}}},{align:"left",className:"test4",ellipsis:!0,colKey:"desc",title:"\u63cf\u8ff0",cell:function(e){var t=e.row;return(0,g.jsx)(g.Fragment,{children:null!==t&&void 0!==t&&t.desc?null===t||void 0===t?void 0:t.desc:"--"})}},{colKey:"op",width:100,title:"\u64cd\u4f5c",align:"center",fixed:"right",cell:function(e){return(0,g.jsx)(g.Fragment,{children:(0,g.jsx)("a",{className:"link",onClick:function(){K(e.row),k(!0)},children:"\u5220\u9664"})})}}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0,pagination:{defaultCurrent:y,defaultPageSize:w,total:b,showJumper:!0,onChange:function(e){A(e.pageSize),j(e.current),U()}}}):(0,g.jsx)(m.Z,{}),(0,g.jsx)(c.Vq,{header:(0,g.jsxs)(g.Fragment,{children:[(0,g.jsx)(d.Z,{style:{color:"#ED7B2F"}}),(0,g.jsx)("span",{children:"\u786e\u5b9a\u8981\u5220\u9664\u8fd9\u4e2a\u7ec8\u7aef\u7684\u4fe1\u606f\u5417\uff1f"})]}),footer:(0,g.jsxs)(g.Fragment,{children:[(0,g.jsx)(u.z,{theme:"default",variant:"outline",onClick:function(){return k(!1)},children:"\u53d6\u6d88"}),(0,g.jsx)(u.z,{theme:"danger",onClick:function(){return function(){if(Z&&Z.terminalId){var e=Z,t=e.terminalId,n=e.serverId;(0,p.$I)({id:t,serverId:n,provider:z}).then((function(e){0==e.code?(K({}),k(!1),l.sX.success("\u4fe1\u606f\u5220\u9664\u6210\u529f\uff01"),U()):l.sX.error(e.msg)}))}else l.sX.error("\u5220\u9664\u8bf7\u6c42\u51fa\u9519\u4e86")}()},children:"\u5220\u9664"})]}),visible:L,onClose:function(){k(!1)}})]})]})]})}},1937:function(e,t,n){function r(e,t){var n=[];return"BMAP"===e?n=t.map((function(e){return{lng:e.longitude,lat:e.latitude}})):"AMAP"===e?n=t.map((function(e){var t=e.location.split(",");return{lng:t[0],lat:t[1]}})):"TMAP"===e&&console.log("\u5f00\u53d1\u4e2d..."),n}function i(e,t,n){var r={};if("AMAP"===e&&"CIRCLE"===t)r.point=n.center.split(","),r.radius=n.radius,r.type="circle";else if("AMAP"===e&&"POLYGON"===t){r.type="polygon";var i=n.points.split(";");r.points=i.length>0&&i.map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}}))}else if("AMAP"===e&&"POLYLINE"===t){r.type="polyline";var a=n.points.split(";");r.offset=n.bufferradius,r.points=a.length>0&&a.map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}}))}else if("AMAP"===e&&"DISTRICT"===t)r.type="district",r.adcodes=[n.adcode];else if("BMAP"===e&&"CIRCLE"===t)r.point=[n.longitude,n.latitude],r.radius=n.radius,r.type="circle";else if("BMAP"===e&&"POLYGON"===t){r.type="polygon";var s=n.vertexes.split(";");r.points=s.length>0&&s.map((function(e){var t=e.split(",");return{lng:t[1],lat:t[0]}}))}else if("BMAP"===e&&"POLYLINE"===t){r.type="polyline",r.offset=n.offset;var l=n.vertexes.split(";");r.points=l.length>0&&l.map((function(e){var t=e.split(",");return{lng:t[1],lat:t[0]}}))}else"BMAP"===e&&"DISTRICT"===t&&(r.type="district",r.proname=n.keyword);return r}function a(e){var t="--";return e&&e<6e4&&(t=Number(e)/1e3+"s"),e&&e>=6e4&&e<36e5&&(t=Number(e)/6e4+"m"),e&&e>36e5&&(t=Number(e)/36e5+"H"),t}n.d(t,{Pf:function(){return r},eB:function(){return i},i$:function(){return a}})},1350:function(e,t,n){e.exports=n.p+"static/media/nodata.e13ac17783aaacf82aee.png"}}]);
//# sourceMappingURL=724.514a281f.chunk.js.map