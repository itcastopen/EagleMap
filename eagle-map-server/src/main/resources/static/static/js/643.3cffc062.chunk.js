"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[643],{7993:function(e,t,n){n.d(t,{CQ:function(){return p},$I:function(){return v},bI:function(){return f},u9:function(){return m},n3:function(){return l},O2:function(){return h},Bh:function(){return d},r4:function(){return o},xt:function(){return c}});var r=n(4569),s=n.n(r),a=n(9012),i=n(2268);function u(e){return s().request(e)}function c(e){return u({method:"POST",url:"/sys/login",data:e,params:e})}function l(){return u({method:"GET",url:"/sys/config"})}function o(e){return u({method:"GET",url:"/sys/trace/server",data:e,params:e})}function d(e){return u({method:"GET",url:"/sys/trace",data:e,params:e})}function f(e){return u({method:"DELETE",url:"/sys/trace/".concat(e),data:e,params:e})}function m(e){return u({method:"GET",url:"/sys/fence",data:e,params:e})}function p(e){return u({method:"DELETE",url:"/sys/fence/".concat(e.fenceId),data:e})}function h(e){return u({method:"GET",url:"/sys/terminal",data:e,params:e})}function v(e){return u({method:"DELETE",url:"/sys/terminal/".concat(e.terminalId),data:e})}s().defaults.headers={"Content-Type":"application/json;charset=utf-8"},s().defaults.baseURL="",s().interceptors.request.use((function(e){var t=(0,i.LP)();return t&&(e.headers.Authorization=t),e}),(function(e){return Promise.reject(e)})),s().interceptors.response.use((function(e){return e.data?200===e.status?e.data:(a.sX.error(e.data.msg),Promise.reject(new Error(e.data.msg))):Promise.resolve(e)}),(function(e){return a.sX.error(e.message),Promise.reject(e)}))},3112:function(e,t,n){n.r(t);var r=n(8152),s=n(2791),a=n(7076),i=n(7993),u=n(184);function c(e){var t=e.data;return t?(0,u.jsxs)("div",{className:"mapList",children:[(0,u.jsxs)("div",{className:"title",children:[(0,u.jsx)("span",{children:t.serverName})," ",t.enable?(0,u.jsxs)("span",{className:"off",children:[(0,u.jsx)("em",{}),"\u5f00\u542f"]}):(0,u.jsxs)("span",{className:"on",children:[(0,u.jsx)("em",{}),"\u5173\u95ed"]})]}),(0,u.jsx)("div",{className:"cont",children:(0,u.jsx)(a.iA,{data:t.servers,columns:[{align:"left",className:"row",ellipsis:!0,colKey:"id",title:"\u5e94\u7528ID"},{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u5e94\u7528\u540d\u79f0"},{align:"left",className:"test4",ellipsis:!0,colKey:"ak",title:"AK"},{align:"left",className:"test4",ellipsis:!0,colKey:"sk",title:"SK"},{align:"left",className:"test4",ellipsis:!0,colKey:"type",title:"\u7c7b\u578b"}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0})})]}):null}t.default=function(e){var t=(0,s.useState)([]),n=(0,r.Z)(t,2),a=n[0],l=n[1];return(0,s.useEffect)((function(){(0,i.n3)().then((function(e){var t=e.data;0===e.code?l(t):console.log("\u8bf7\u6c42\u51fa\u9519\uff01")}))}),[]),(0,u.jsx)("div",{className:"systemSet container",children:a.map((function(e,t){return(0,u.jsx)(c,{data:e},t)}))},"sts")}}}]);
//# sourceMappingURL=643.3cffc062.chunk.js.map