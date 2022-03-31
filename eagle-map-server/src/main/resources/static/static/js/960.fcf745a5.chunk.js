/*! For license information please see 960.fcf745a5.chunk.js.LICENSE.txt */
"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[960],{7993:function(e,t,r){r.d(t,{CQ:function(){return h},$I:function(){return v},bI:function(){return m},u9:function(){return f},n3:function(){return l},O2:function(){return p},Bh:function(){return d},r4:function(){return u},xt:function(){return s}});var n=r(4569),a=r.n(n),c=r(9012),i=r(2268);function o(e){return a().request(e)}function s(e){return o({method:"POST",url:"/sys/login",data:e,params:e})}function l(){return o({method:"GET",url:"/sys/config"})}function u(e){return o({method:"GET",url:"/sys/trace/server",data:e,params:e})}function d(e){return o({method:"GET",url:"/sys/trace",data:e,params:e})}function m(e){return o({method:"DELETE",url:"/sys/trace/".concat(e.id),data:e,params:e})}function f(e){return o({method:"GET",url:"/sys/fence",data:e,params:e})}function h(e){return o({method:"DELETE",url:"/sys/fence/".concat(e.fenceId),data:e})}function p(e){return o({method:"GET",url:"/sys/terminal",data:e,params:e})}function v(e){return o({method:"DELETE",url:"/sys/terminal/".concat(e.id),data:e})}a().defaults.headers={"Content-Type":"application/json;charset=utf-8"},a().defaults.baseURL="",a().interceptors.request.use((function(e){var t=(0,i.LP)();return t&&(e.headers.Authorization=t),e}),(function(e){return Promise.reject(e)})),a().interceptors.response.use((function(e){return e.data?200===e.status?e.data:(401===e.status&&((0,i.gy)(),window.location.href="/login"),c.sX.error(e.data.msg),Promise.reject(new Error(e.data.msg))):Promise.resolve(e)}),(function(e){return c.sX.error(e.message),Promise.reject(e)}))},6873:function(e,t,r){r.d(t,{Z:function(){return c}});var n=r(1350),a=r(184);function c(){return(0,a.jsxs)("div",{className:"noData",children:[(0,a.jsx)("img",{src:n,width:"245",height:"201",alt:""}),(0,a.jsx)("div",{children:"\u6ca1\u6709\u6570\u636eorz"})]})}},9311:function(e,t,r){r.d(t,{o:function(){return h}});var n=r(8683),a=r(8152),c=r(2791),i=r(9569),o=r(6381),s=r(7055);r(7610);function l(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function u(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?l(Object(r),!0).forEach((function(t){(0,o._)(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):l(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}var d={tag:"svg",attrs:{fill:"none",viewBox:"0 0 16 16",width:"1em",height:"1em"},children:[{tag:"path",attrs:{fill:"currentColor",d:"M9.51 10.22a4.76 4.76 0 11.7-.7l3.54 3.52-.7.71-3.54-3.53zm.77-3.7a3.76 3.76 0 10-7.53 0 3.76 3.76 0 007.53 0z",fillOpacity:.9}}]},m=(0,c.forwardRef)((function(e,t){return(0,c.createElement)(s.A,u(u({},e),{},{id:"search",ref:t,icon:d}))}));m.displayName="SearchIcon";var f=r(184);function h(e){var t=e.searchConf,r=(0,c.useState)({}),o=(0,a.Z)(r,2),s=o[0],l=o[1],u={};return(0,c.useEffect)((function(){t.forEach((function(e){u[e.key]=""!==e.value?e.value:void 0})),l(u)}),[t]),(0,f.jsx)("div",{className:"searchCont",children:t.map((function(e,t){return(0,f.jsxs)("div",{className:"searchitem",children:[(0,f.jsx)("div",{className:"label",children:e.label}),(0,f.jsx)(i.I,{placeholder:"\u8bf7\u8f93\u5165",value:s[e.key],type:"text",onEnter:function(){e.callback(s)},onChange:function(t){var r={};r[e.key]=t,e.setValue&&e.setValue((0,n.Z)((0,n.Z)({},s),r)),l((0,n.Z)((0,n.Z)({},s),r))}}),(0,f.jsx)(m,{className:"searchIcon",name:"search",size:"medium",onClick:function(){e.callback(s)}})]},t)}))})}},996:function(e,t,r){r.r(t);var n=r(8683),a=r(8152),c=r(2791),i=r(9012),o=r(9498),s=r(3307),l=r(7735),u=r(7993),d=r(9311),m=r(2819),f=r(6873),h=r(1082),p=r(2426),v=r.n(p),b=r(184),g=h.a.BreadcrumbItem;t.default=function(e){var t=(0,c.useState)(1),r=(0,a.Z)(t,2),p=r[0],j=r[1],y=(0,c.useState)(10),x=(0,a.Z)(y,2),O=x[0],P=x[1],w=(0,c.useState)([]),E=(0,a.Z)(w,2),I=E[0],N=E[1],k=(0,c.useState)(30),D=(0,a.Z)(k,2),C=D[0],S=D[1],Z=(0,c.useState)(!1),T=(0,a.Z)(Z,2),W=T[0],_=T[1],z=(0,c.useState)(""),B=(0,a.Z)(z,2),A=B[0],F=B[1],K=(0,c.useState)("BAIDU"),Y=(0,a.Z)(K,2),M=Y[0],G=(Y[1],(0,c.useState)({})),L=(0,a.Z)(G,2),V=L[0],H=L[1],U=[{label:"\u8bbe\u5907\u540d\u79f0:",key:"terminalName",value:"",callback:q,setValue:H},{label:"\u8bbe\u5907ID:",key:"terminalId",value:"",callback:q,setValue:H}];(0,c.useEffect)((function(){X()}),[]);var X=function(t){var r,n,a,c=e.match.params.id;if(!c)return!1;(0,u.O2)({page:null!==(r=null===t||void 0===t?void 0:t.terminalPage)&&void 0!==r?r:p,pageSize:null!==(n=null===t||void 0===t?void 0:t.terminalPageSize)&&void 0!==n?n:O,terminalName:null===t||void 0===t?void 0:t.terminalName,terminalId:null===t||void 0===t?void 0:t.terminalId,serverId:c,provider:null!==(a=null===t||void 0===t?void 0:t.provider)&&void 0!==a?a:M}).then((function(e){var t,r=e.data;0===e.code?(N(r.items),S(r.total)):i.sX.error(null!==(t=e.msg)&&void 0!==t?t:"\u8bf7\u6c42\u51fa\u9519\uff01")}))};function q(e){j(1),P(10),X((0,n.Z)({setTerminalPage:1,setTerminalPageSize:10},e))}return(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)("div",{className:"BreadcrumbCont",children:(0,b.jsxs)(h.a,{maxItemWidth:"200px",theme:"light",children:[(0,b.jsx)(g,{href:"/server",children:"\u670d\u52a1\u7ba1\u7406"}),(0,b.jsx)(g,{children:"\u8bbe\u5907\u7ba1\u7406"})]})}),(0,b.jsxs)("div",{className:"container",children:[(0,b.jsxs)("div",{className:"searchCont",children:[(0,b.jsx)(d.o,{searchConf:U}),(0,b.jsx)("div",{className:"tdesign-demo-block-row"})]}),I&&I.length>0?(0,b.jsx)(o.iA,{data:I,columns:[{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u8bbe\u5907\u540d\u79f0"},{align:"left",className:"test",ellipsis:!0,colKey:"provider",cell:function(e){var t=e.row;switch(null===t||void 0===t?void 0:t.provider){case"BAIDU":return(0,b.jsx)(b.Fragment,{children:"\u767e\u5ea6\u5730\u56fe"});case"AMAP":return(0,b.jsx)(b.Fragment,{children:"\u9ad8\u5fb7\u5730\u56fe"});case"QQ":return(0,b.jsx)(b.Fragment,{children:"\u817e\u8baf\u5730\u56fe"});default:return null}},title:"\u670d\u52a1\u5546"},{align:"left",className:"test4",ellipsis:!0,colKey:"id",title:"\u670d\u52a1ID"},{align:"left",className:"test4",ellipsis:!0,colKey:"created",cell:function(e){var t=e.row;return v()(t.created).format("YYYY-MM-DD HH:mm:ss")},title:"\u521b\u5efa\u65f6\u95f4"},{align:"left",className:"test4",ellipsis:!0,colKey:"updated",cell:function(e){var t=e.row;return v()(t.updated).format("YYYY-MM-DD HH:mm:ss")},title:"\u66f4\u65b0\u65f6\u95f4"},{align:"left",className:"test4",ellipsis:!0,colKey:"desc",title:"\u63cf\u8ff0"},{colKey:"op",width:100,title:"\u64cd\u4f5c",align:"center",fixed:"right",cell:function(e){return(0,b.jsx)(b.Fragment,{children:(0,b.jsx)("a",{className:"link",onClick:function(){F(e.row.terminalId),_(!0)},children:"\u5220\u9664"})})}}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0,pagination:{defaultCurrent:p,defaultPageSize:O,total:C,showJumper:!0,onChange:function(e){P(e.pageSize),j(e.current),X()}}}):(0,b.jsx)(f.Z,{}),(0,b.jsx)(s.Vq,{header:(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(m.Z,{style:{color:"#ED7B2F"}}),(0,b.jsx)("span",{children:"\u786e\u5b9a\u8981\u5220\u9664\u8fd9\u6761\u8f68\u8ff9\u5417\uff1f"})]}),footer:(0,b.jsxs)(b.Fragment,{children:[(0,b.jsx)(l.z,{theme:"default",variant:"outline",onClick:function(){return _(!1)},children:"\u53d6\u6d88"}),(0,b.jsx)(l.z,{theme:"danger",onClick:function(){A?((0,u.$I)({id:A,provider:M}),F(""),_(!1),X((0,n.Z)((0,n.Z)({},V),{},{provider:M}))):i.sX.error("\u5220\u9664\u8bf7\u6c42\u51fa\u9519\u4e86")},children:"\u5220\u9664"})]}),visible:W,onClose:function(){_(!1)}})]},"server")]})}},1082:function(e,t,r){r.d(t,{a:function(){return j}});var n=r(5905),a=r(3671),c=r(2791),i=r(421),o=r(6777),s=r(1694),l=r.n(s),u=r(3350),d=r(2670),m=(0,c.createContext)({maxItemWidthInContext:"",theme:"light"}),f=["children","separator","disabled","maxItemWidth","maxWidth","href","to","target","router","replace"];function h(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}var p=(0,c.forwardRef)((function(e,t){var r=e.children,o=e.separator,s=void 0===o?c.createElement(u.Z,{style:{color:"rgba(0,0,0,.3)"}}):o,p=e.disabled,v=e.maxItemWidth,b=e.maxWidth,g=e.href,j=e.to,y=e.target,x=e.router,O=e.replace,P=(0,a._)(e,f),w=(0,c.useContext)(m),E=w.maxItemWidthInContext,I=w.theme,N=(0,i.Z)().classPrefix,k=(0,d.Z)(),D=l()("".concat(N,"-breadcrumb__item")),C="".concat(N,"-breadcrumb__inner"),S=l()("".concat(N,"-breadcrumb--text-overflow"),(0,n._)({},k.STATUS.disabled,p)),Z="".concat(N,"-breadcrumb__separator"),T="".concat(N,"-link"),W=(0,c.useMemo)((function(){return{maxWidth:b||v||E||"120px"}}),[v,b,E]),_=c.createElement("span",{className:C,style:W},r),z=c.createElement("span",{className:S},_);if((g||j)&&!p){z=c.createElement("a",{className:l()(S,T),href:g,target:y,onClick:function(){!g&&x&&(O?x.replace(j):x.push(j))}},_)}var B="function"===typeof s?s():s;return c.createElement("div",function(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?h(Object(r),!0).forEach((function(t){(0,n._)(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):h(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}({className:l()(D,I),ref:t},P),z,c.createElement("span",{className:Z},B))}));p.displayName="BreadcrumbItem";r(2110);var v=["children","options","separator","maxItemWidth","theme"];function b(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}var g=(0,o.Z)((function(e,t){var r=e.children,o=e.options,s=e.separator,l=e.maxItemWidth,u=e.theme,d=(0,a._)(e,v),f=(0,i.Z)().classPrefix,h=r;return o&&o.length&&(h=o.map((function(e,t){return c.createElement(p,{key:t,maxWidth:e.maxWidth,disabled:e.disabled,href:e.href,target:e.target,to:e.to,router:e.router,replace:e.replace,separator:s,maxItemWidth:l},e.content||e.children)}))),c.createElement(m.Provider,{value:{maxItemWidthInContext:l,theme:u}},c.createElement("div",function(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?b(Object(r),!0).forEach((function(t){(0,n._)(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):b(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}({ref:t,className:"".concat(f,"-breadcrumb")},d),h))}),{BreadcrumbItem:p});g.displayName="Breadcrumb";var j=g},1350:function(e,t,r){e.exports=r.p+"static/media/nodata.e13ac17783aaacf82aee.png"}}]);
//# sourceMappingURL=960.fcf745a5.chunk.js.map