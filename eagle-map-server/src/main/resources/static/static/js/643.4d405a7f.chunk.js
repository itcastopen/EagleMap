"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[643],{7993:function(e,t,n){n.d(t,{CQ:function(){return p},$I:function(){return v},bI:function(){return f},u9:function(){return m},n3:function(){return c},O2:function(){return h},Bh:function(){return d},r4:function(){return l},xt:function(){return u}});var r=n(4569),s=n.n(r),a=n(9012),i=n(2268);function o(e){return s().request(e)}function u(e){return o({method:"POST",url:"/sys/login",data:e,params:e})}function c(){return o({method:"GET",url:"/sys/config"})}function l(e){return o({method:"GET",url:"/sys/trace/server",data:e,params:e})}function d(e){return o({method:"GET",url:"/sys/trace",data:e,params:e})}function f(e){return o({method:"DELETE",url:"/sys/trace/".concat(e.id),data:e,params:e})}function m(e){return o({method:"GET",url:"/sys/fence",data:e,params:e})}function p(e){return o({method:"DELETE",url:"/sys/fence/".concat(e.fenceId),data:e,params:e})}function h(e){return o({method:"GET",url:"/sys/terminal",data:e,params:e})}function v(e){return o({method:"DELETE",url:"/sys/terminal/".concat(e.id),data:e,params:e})}s().defaults.headers={"Content-Type":"application/json;charset=utf-8"},s().defaults.baseURL="",s().interceptors.request.use((function(e){var t=(0,i.LP)();return t&&(e.headers.Authorization=t),e}),(function(e){return Promise.reject(e)})),s().interceptors.response.use((function(e){return e.data?200===e.status?e.data:(401!==e.status&&401!==e.data.code||((0,i.gy)(),window.location.href="/login"),a.sX.error(e.data.msg),Promise.reject(new Error(e.data.msg))):Promise.resolve(e)}),(function(e){if(a.sX.error(e.message),401!==e.response.status)return Promise.reject(e);(0,i.gy)(),window.location.href="/login"}))},3112:function(e,t,n){n.r(t);var r=n(8152),s=n(2791),a=n(9012),i=n(9498),o=n(7993),u=n(184);function c(e){var t=e.data;return t?(0,u.jsxs)("div",{className:"mapList",children:[(0,u.jsxs)("div",{className:"title",children:[(0,u.jsx)("span",{children:t.serverName})," ",t.enable?(0,u.jsxs)("span",{className:"off",children:[(0,u.jsx)("em",{}),"\u5f00\u542f"]}):(0,u.jsxs)("span",{className:"on",children:[(0,u.jsx)("em",{}),"\u5173\u95ed"]})]}),(0,u.jsx)("div",{className:"cont",children:(0,u.jsx)(i.iA,{data:t.servers,columns:[{align:"left",className:"row",ellipsis:!0,colKey:"id",cell:function(e){var t=e.row;return t.id?t.id:"--"},title:"\u5e94\u7528ID"},{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u5e94\u7528\u540d\u79f0"},{align:"left",className:"test4",ellipsis:!0,colKey:"ak",title:"AK"},{align:"left",className:"test4",ellipsis:!0,colKey:"sk",title:"SK",cell:function(e){var t=e.row;return t.sk?t.sk:"--"}},{align:"left",className:"test4",ellipsis:!0,colKey:"type",title:"\u7c7b\u578b"}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0})})]}):null}t.default=function(e){var t=(0,s.useState)([]),n=(0,r.Z)(t,2),i=n[0],l=n[1];return(0,s.useEffect)((function(){(0,o.n3)().then((function(e){var t,n=e.data;0===e.code?l(n):a.sX.error(null!==(t=e.msg)&&void 0!==t?t:"\u8bf7\u6c42\u51fa\u9519\uff01")}))}),[]),(0,u.jsx)("div",{className:"systemSet container",children:i.map((function(e,t){return(0,u.jsx)(c,{data:e},t)}))},"sts")}}}]);
//# sourceMappingURL=643.4d405a7f.chunk.js.map