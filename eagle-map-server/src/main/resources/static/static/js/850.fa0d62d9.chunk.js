"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[850],{7993:function(e,t,n){n.d(t,{CQ:function(){return p},$I:function(){return v},bI:function(){return f},u9:function(){return m},n3:function(){return c},O2:function(){return h},Bh:function(){return d},r4:function(){return u},xt:function(){return o}});var r=n(4569),i=n.n(r),a=n(9012),l=n(2268);function s(e){return i().request(e)}function o(e){return s({method:"POST",url:"/sys/login",data:e,params:e})}function c(){return s({method:"GET",url:"/sys/config"})}function u(e){return s({method:"GET",url:"/sys/trace/server",data:e,params:e})}function d(e){return s({method:"GET",url:"/sys/trace",data:e,params:e})}function f(e){return s({method:"DELETE",url:"/sys/trace/".concat(e.id),data:e,params:e})}function m(e){return s({method:"GET",url:"/sys/fence",data:e,params:e})}function p(e){return s({method:"DELETE",url:"/sys/fence/".concat(e.fenceId),data:e})}function h(e){return s({method:"GET",url:"/sys/terminal",data:e,params:e})}function v(e){return s({method:"DELETE",url:"/sys/terminal/".concat(e.id),data:e})}i().defaults.headers={"Content-Type":"application/json;charset=utf-8"},i().defaults.baseURL="",i().interceptors.request.use((function(e){var t=(0,l.LP)();return t&&(e.headers.Authorization=t),e}),(function(e){return Promise.reject(e)})),i().interceptors.response.use((function(e){return e.data?200===e.status?e.data:(401===e.status&&((0,l.gy)(),window.location.href="/login"),a.sX.error(e.data.msg),Promise.reject(new Error(e.data.msg))):Promise.resolve(e)}),(function(e){return a.sX.error(e.message),Promise.reject(e)}))},6873:function(e,t,n){n.d(t,{Z:function(){return a}});var r=n(1350),i=n(184);function a(){return(0,i.jsxs)("div",{className:"noData",children:[(0,i.jsx)("img",{src:r,width:"245",height:"201",alt:""}),(0,i.jsx)("div",{children:"\u6ca1\u6709\u6570\u636eorz"})]})}},9311:function(e,t,n){n.d(t,{o:function(){return p}});var r=n(8683),i=n(8152),a=n(2791),l=n(9569),s=n(6381),o=n(7055);n(7610);function c(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function u(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?c(Object(n),!0).forEach((function(t){(0,s._)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):c(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var d={tag:"svg",attrs:{fill:"none",viewBox:"0 0 16 16",width:"1em",height:"1em"},children:[{tag:"path",attrs:{fill:"currentColor",d:"M9.51 10.22a4.76 4.76 0 11.7-.7l3.54 3.52-.7.71-3.54-3.53zm.77-3.7a3.76 3.76 0 10-7.53 0 3.76 3.76 0 007.53 0z",fillOpacity:.9}}]},f=(0,a.forwardRef)((function(e,t){return(0,a.createElement)(o.A,u(u({},e),{},{id:"search",ref:t,icon:d}))}));f.displayName="SearchIcon";var m=n(184);function p(e){var t=e.searchConf,n=(0,a.useState)({}),s=(0,i.Z)(n,2),o=s[0],c=s[1],u={};return(0,a.useEffect)((function(){t.forEach((function(e){u[e.key]=""!==e.value?e.value:void 0})),c(u)}),[t]),(0,m.jsx)("div",{className:"searchCont",children:t.map((function(e,t){return(0,m.jsxs)("div",{className:"searchitem",children:[(0,m.jsx)("div",{className:"label",children:e.label}),(0,m.jsx)(l.I,{placeholder:"\u8bf7\u8f93\u5165",value:o[e.key],type:"text",onEnter:function(){e.callback(o)},onChange:function(t){var n={};n[e.key]=t,e.setValue&&e.setValue((0,r.Z)((0,r.Z)({},o),n)),c((0,r.Z)((0,r.Z)({},o),n))}}),(0,m.jsx)(f,{className:"searchIcon",name:"search",size:"medium",onClick:function(){e.callback(o)}})]},t)}))})}},7244:function(e,t,n){n.r(t);var r=n(8683),i=n(8152),a=n(2791),l=n(9311),s=n(9012),o=n(4328),c=n(9498),u=n(3307),d=n(7735),f=n(2819),m=n(1937),p=n(7993),h=n(6873),v=n(2426),g=n.n(v),y=n(184);t.default=function(e){var t=(0,a.useState)(!1),n=(0,i.Z)(t,2),v=n[0],j=n[1],x=(0,a.useState)(1),P=(0,i.Z)(x,2),b=P[0],N=P[1],w=(0,a.useState)(10),A=(0,i.Z)(w,2),E=A[0],I=A[1],C=(0,a.useState)([]),O=(0,i.Z)(C,2),k=O[0],D=O[1],T=(0,a.useState)(30),S=(0,i.Z)(T,2),Z=S[0],M=S[1],Y=(0,a.useState)(""),z=(0,i.Z)(Y,2),B=z[0],L=z[1],K=(0,a.useState)("BAIDU"),W=(0,i.Z)(K,2),G=W[0],F=W[1],R=(0,a.useState)({}),H=(0,i.Z)(R,2),V=H[0],U=H[1],X=[{label:"\u8f68\u8ff9\u540d\u79f0:",value:"",key:"traceName",callback:q,setValue:U},{label:"\u8f68\u8ff9ID:",value:"",key:"traceId",callback:q,setValue:U}];function q(e){N(1),I(10),U(e),Q((0,r.Z)({terminalPage:1,terminalPageSize:10},e))}function Q(e){var t,n,r;(0,p.Bh)({page:null!==(t=null===e||void 0===e?void 0:e.tracePage)&&void 0!==t?t:b,pageSize:null!==(n=null===e||void 0===e?void 0:e.tracePageSize)&&void 0!==n?n:E,traceName:null===e||void 0===e?void 0:e.traceName,traceId:null===e||void 0===e?void 0:e.traceId,provider:null!==(r=null===e||void 0===e?void 0:e.provider)&&void 0!==r?r:G}).then((function(e){var t,n=e.data;0===e.code?(D(n.items),M(n.total)):s.sX.error(null!==(t=e.msg)&&void 0!==t?t:"\u8bf7\u6c42\u51fa\u9519\uff01")}))}return(0,a.useEffect)((function(){Q()}),[]),(0,y.jsxs)("div",{className:"container trajectory",children:[(0,y.jsxs)("div",{className:"searchCont",children:[(0,y.jsx)(l.o,{searchConf:X}),(0,y.jsx)("div",{className:"tdesign-demo-block-row",children:(0,y.jsxs)(o.Y.Group,{value:G,onChange:function(e){F(e),Q((0,r.Z)((0,r.Z)({},V),{},{provider:e}))},children:[(0,y.jsx)(o.Y,{value:"BAIDU",children:"\u767e\u5ea6\u5730\u56fe"}),(0,y.jsx)(o.Y,{value:"AMAP",children:"\u9ad8\u5fb7\u5730\u56fe"})]})})]}),(0,y.jsx)("div",{className:"tableCont",children:k&&k.length>0?(0,y.jsx)(c.iA,{data:k,columns:[{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u8f68\u8ff9\u540d\u79f0",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"terminalId",title:"\u8f68\u8ff9ID",minWidth:200},{align:"left",className:"test4",ellipsis:!0,cell:function(e){var t=e.row;switch(null===t||void 0===t?void 0:t.provider){case"BAIDU":return(0,y.jsx)(y.Fragment,{children:"\u767e\u5ea6\u5730\u56fe"});case"AMAP":return(0,y.jsx)(y.Fragment,{children:"\u9ad8\u5fb7\u5730\u56fe"});case"QQ":return(0,y.jsx)(y.Fragment,{children:"\u817e\u8baf\u5730\u56fe"});default:return null}},title:"\u670d\u52a1\u5546\u540d\u79f0",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"serverId",title:"\u670d\u52a1ID",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"distance",title:"\u8f68\u8ff9\u957f\u5ea6",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"time",cell:function(e){var t=e.row;return(0,m.i$)(t.time)},title:"\u6301\u7eed\u65f6\u95f4",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"startTime",cell:function(e){var t=e.row;return t.startTime?g()(t.startTime).format("YYYY-MM-DD HH:mm:ss"):"--"},title:"\u521b\u5efa\u65f6\u95f4",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"endTime",cell:function(e){var t=e.row;return t.endTime?g()(t.endTime).format("YYYY-MM-DD HH:mm:ss"):"--"},title:"\u7ed3\u675f\u65f6\u95f4",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"size",title:"\u8f68\u8ff9\u70b9\u6570",minWidth:200},{colKey:"op",width:140,title:"\u64cd\u4f5c",fixed:"right",cell:function(t){return(0,y.jsxs)(y.Fragment,{children:[(0,y.jsx)("a",{className:"link",onClick:function(){e.history.push({pathname:"/trajectory/".concat(t.row.id),state:t.row})},children:"\u67e5\u770b"}),(0,y.jsx)("a",{className:"link",onClick:function(){L(t.row.traceId),j(!0)},children:"\u5220\u9664"})]})}}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0,pagination:{defaultCurrent:b,defaultPageSize:E,total:Z,showJumper:!0,onChange:function(e){I(e.pageSize),N(e.current),Q()}}}):(0,y.jsx)(h.Z,{})}),(0,y.jsx)(u.Vq,{header:(0,y.jsxs)(y.Fragment,{children:[(0,y.jsx)(f.Z,{style:{color:"#ED7B2F"}}),(0,y.jsx)("span",{children:"\u786e\u5b9a\u8981\u5220\u9664\u8fd9\u6761\u8f68\u8ff9\u5417\uff1f"})]}),footer:(0,y.jsxs)(y.Fragment,{children:[(0,y.jsx)(d.z,{theme:"default",variant:"outline",onClick:function(){return j(!1)},children:"\u53d6\u6d88"}),(0,y.jsx)(d.z,{theme:"danger",onClick:function(){B?((0,p.bI)({id:B,provider:G}),L(""),j(!1),Q((0,r.Z)({},V))):s.sX.error("\u5220\u9664\u8bf7\u6c42\u51fa\u9519\u4e86")},children:"\u5220\u9664"})]}),visible:v,onClose:function(){j(!1)}})]})}},1937:function(e,t,n){function r(e,t){var n=[];return"BMAP"===e?n=t.map((function(e){return{lng:e.longitude,lat:e.latitude}})):"AMAP"===e?n=t.map((function(e){var t=e.location.split(",");return{lng:t[0],lat:t[1]}})):"TMAP"===e&&console.log("\u5f00\u53d1\u4e2d..."),n}function i(e,t,n){var r={};if("AMAP"===e&&"CIRCLE"===t)r.point=n.center.split(","),r.radius=n.radius,r.type="circle";else if("AMAP"===e&&"POLYGON"===t){r.type="polygon";var i=n.points.split(";");r.points=i.length>0&&i.map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}}))}else if("AMAP"===e&&"POLYLINE"===t){r.type="polyline";var a=n.points.split(";");r.offset=n.bufferradius,r.points=a.length>0&&a.map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}}))}else if("AMAP"===e&&"DISTRICT"===t)r.type="district",r.adcodes=[n.adcode];else if("BMAP"===e&&"CIRCLE"===t)r.point=[n.longitude,n.latitude],r.radius=n.radius,r.type="circle";else if("BMAP"===e&&"POLYGON"===t){r.type="polygon";var l=n.vertexes.split(";");r.points=l.length>0&&l.map((function(e){var t=e.split(",");return{lng:t[1],lat:t[0]}}))}else if("BMAP"===e&&"POLYLINE"===t){r.type="polyline",r.offset=n.offset;var s=n.vertexes.split(";");r.points=s.length>0&&s.map((function(e){var t=e.split(",");return{lng:t[1],lat:t[0]}}))}else"BMAP"===e&&"DISTRICT"===t&&(r.type="district",r.proname=n.keyword);return r}function a(e){var t="--";return e&&e<6e4&&(t=Number(e)/1e3+"s"),e&&e>=6e4&&e<36e5&&(t=Number(e)/6e4+"m"),e&&e>36e5&&(t=Number(e)/36e5+"H"),t}n.d(t,{Pf:function(){return r},eB:function(){return i},i$:function(){return a}})},1350:function(e,t,n){e.exports=n.p+"static/media/nodata.e13ac17783aaacf82aee.png"}}]);
//# sourceMappingURL=850.fa0d62d9.chunk.js.map