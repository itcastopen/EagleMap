/*! For license information please see 307.027f8ba5.chunk.js.LICENSE.txt */
"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[307],{3307:function(e,t,n){n.d(t,{Vq:function(){return D}});var r=n(3671),o=n(5905),c=n(3234),i=n(2791),a=n(9618),l=n(1686),s=n(2261),u=n(9594),f=n(5126),d=n(7735),m=n(1883),v=n(4595),p=n(1694),y=n.n(p),b=n(8471),O=n(3689);n(4164),n(5475);function h(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function g(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?h(Object(n),!0).forEach((function(t){(0,o._)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):h(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var w;"undefined"!==typeof window&&window.document&&window.document.documentElement&&document.documentElement.addEventListener("click",(function(e){w={x:e.clientX,y:e.clientY},setTimeout((function(){w=null}),100)}),!0);var E=function(e){var t=e.prefixCls,n=e.attach,r=e.visible,o=e.mode,c=e.zIndex,a=e.showOverlay,l=e.onEscKeydown,s=void 0===l?m.Z:l,u=e.onClosed,f=void 0===u?m.Z:u,d=e.onClose,p=void 0===d?m.Z:d,h=e.onCloseBtnClick,E=void 0===h?m.Z:h,j=e.onOverlayClick,P=void 0===j?m.Z:j,x=e.preventScrollThrough,_=e.closeBtn,C=(0,i.useRef)(),Z=(0,i.useRef)(),k=(0,i.useRef)(),N=(0,i.useRef)(),D=(0,i.useRef)(),B="modal"===o,S=e.draggable&&"modeless"===o;(0,i.useEffect)((function(){N.current=document.body.style.overflow,D.current=document.body.style.cssText}),[]),(0,O.Z)((function(){if(r){if(B&&"hidden"!==N.current&&x){var e=window.innerWidth-document.body.offsetWidth;if(""===D.current){var t="overflow: hidden;";e>0&&(t+="position: relative;width: calc(100% - ".concat(e,"px);")),document.body.style.cssText=t}else e>0&&(document.body.style.width="calc(100% - ".concat(e,"px)"),document.body.style.position="relative"),document.body.style.overflow="hidden"}C.current&&C.current.focus()}else B&&(document.body.style.cssText=D.current)}),[x,n,r,o,B]),(0,i.useEffect)((function(){r&&w&&Z.current&&(Z.current.style.transformOrigin="".concat(w.x-Z.current.offsetLeft,"px ").concat(w.y-Z.current.offsetTop,"px"))}),[r]);var T=function(){if(C.current&&(C.current.style.display="none"),B&&x&&B&&(document.body.style.overflow=N.current),!B){var e=Z.current.style;e.left="50%",e.top="50%"}f&&f()},R=function(e){e.target===e.currentTarget&&(P({e:e}),p({e:e,trigger:"overlay"}))},L=function(e){E({e:e}),p({e:e,trigger:"close-btn"})},z=function(e){27!==+e.code&&27!==e.keyCode||(e.stopPropagation(),s({e:e}),p({e:e,trigger:"esc"}))};return function(){var l={};r&&(l.display="block");var s=g(g({},l),{},{zIndex:c}),u=function(n){var r={};void 0!==e.width&&(r.width=e.width);var o=e.footer?i.createElement("div",{className:"".concat(t,"__footer")},e.footer):null,c=i.createElement("div",{className:"".concat(t,"__header")},e.header),a=i.createElement("div",{className:"".concat(t,"__body")},e.body||e.children),l=_&&i.createElement("span",{onClick:L,className:"".concat(t,"__close")},_),s=g(g({},r),e.style),u={x:0,y:0},f=function(e){var t=Z.current,n=t.style,r=t.offsetWidth,o=t.offsetHeight,c=t.clientHeight/2,i=t.clientWidth/2,a=e.clientX-u.x,l=e.clientY-u.y;a<i&&(a=i),a>window.innerWidth-r+i&&(a=window.innerWidth-r+i),l<c&&(l=c),l>window.innerHeight-o+c&&(l=window.innerHeight-o+c),n.left="".concat(a,"px"),n.top="".concat(l,"px")},d=function e(){Z.current.style.cursor="default",document.removeEventListener("mousemove",f),document.removeEventListener("mouseup",e)},m=i.createElement("div",{ref:Z,style:s,className:y()("".concat(t),"".concat(t,"--default"),n),onMouseDown:function(e){if(S){var t=Z.current,n=t.offsetLeft,r=t.offsetTop;Z.current.style.cursor="move";var o=e.clientX-n,c=e.clientY-r;u={x:o,y:c},document.addEventListener("mousemove",f),document.addEventListener("mouseup",d)}}},l,c,a,o);return i.createElement(v.Z,{in:e.visible,appear:!0,mountOnEnter:!0,unmountOnExit:e.destroyOnClose,timeout:300,classNames:"".concat(t,"-zoom"),onEntered:e.onOpened,onExited:T,nodeRef:Z},m)}("".concat(e.placement?"".concat(t,"--").concat(e.placement):"")),f=y()(e.className,"".concat(t,"__ctx"),"".concat(t,"__ctx--fixed")),d=i.createElement("div",{ref:C,className:f,style:s,onKeyDown:z},"modal"===o&&function(){var e;return a&&(e=i.createElement(v.Z,{in:r,appear:!0,timeout:300,classNames:"".concat(t,"-fade"),mountOnEnter:!0,unmountOnExit:!0,nodeRef:k},i.createElement("div",{ref:k,onClick:R,className:"".concat(t,"__mask")}))),e}(),u),m=null;return(r||C.current)&&(m=n?i.createElement(b.Z,{attach:n},d):d),m}()};function j(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function P(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?j(Object(n),!0).forEach((function(t){(0,o._)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):j(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var x=function(e){return"function"===typeof e},_=n(421),C=["visible","attach","closeBtn","footer","onCancel","onConfirm","cancelBtn","confirmBtn","onClose","isPlugin"];function Z(e,t){var n=Object.keys(e);if(Object.getOwnPropertySymbols){var r=Object.getOwnPropertySymbols(e);t&&(r=r.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),n.push.apply(n,r)}return n}function k(e){for(var t=1;t<arguments.length;t++){var n=null!=arguments[t]?arguments[t]:{};t%2?Z(Object(n),!0).forEach((function(t){(0,o._)(e,t,n[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(n)):Z(Object(n)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(n,t))}))}return e}var N=function(e,t){var n=(0,_.Z)().classPrefix,o=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},t=(0,i.useState)(e),n=(0,c._)(t,2),r=n[0],o=n[1],a=(0,i.useCallback)((function(e){o((function(t){return P(P({},t),x(e)?e(t):e)}))}),[]);return[r,a]}(k({width:520,visible:!1,zIndex:2500,placement:"center",mode:"modal",showOverlay:!0,destroyOnClose:!1,draggable:!1,preventScrollThrough:!0,isPlugin:!1},e)),v=(0,c._)(o,2),p=v[0],y=v[1],b=(0,f.E)("dialog"),O=(0,c._)(b,2),h=O[0],g=O[1],w=g(h.confirm),j=g(h.cancel),Z=p.visible,N=p.attach,D=void 0===N?"body":N,B=p.closeBtn,S=p.footer,T=p.onCancel,R=void 0===T?m.Z:T,L=p.onConfirm,z=void 0===L?m.Z:L,W=p.cancelBtn,H=void 0===W?j:W,I=p.confirmBtn,V=void 0===I?w:I,X=p.onClose,Y=void 0===X?m.Z:X,A=p.isPlugin,K=void 0!==A&&A,M=(0,r._)(p,C);(0,i.useEffect)((function(){K||y((function(t){return k(k({},t),e)}))}),[e,y,K]);var q="".concat(n,"-dialog");i.useImperativeHandle(t,(function(){return{show:function(){y({visible:!0})},hide:function(){y({visible:!1})},destroy:m.Z,update:function(e){y((function(t){return k(k({},t),e)}))}}}));var F=(0,i.useMemo)((function(){if(!p.header)return null;var e={info:i.createElement(s.Z,{className:"".concat(n,"-is-info")}),warning:i.createElement(s.Z,{className:"".concat(n,"-is-warning")}),error:i.createElement(s.Z,{className:"".concat(n,"-is-error")}),success:i.createElement(u.Z,{className:"".concat(n,"-is-success")})};return i.createElement("div",{className:"".concat(q,"__header")},e[p.theme],p.header)}),[p.header,p.theme,q,n]),G=function(e){R({e:e}),Y({e:e,trigger:"cancel"})},J=function(e){z({e:e})};return i.createElement(E,k(k({},M),{},{visible:Z,prefixCls:q,header:F,attach:D,closeBtn:!1===B?null:!0===B?i.createElement(l.Z,{style:{verticalAlign:"unset"}}):B||i.createElement(l.Z,{style:{verticalAlign:"unset"}}),classPrefix:n,onClose:Y,footer:void 0===S?function(){var e=H&&i.createElement(d.z,{variant:"outline"},(0,a.i)(H)?H:j),t=V&&i.createElement(d.z,{theme:"primary"},(0,a.i)(V)?V:w);return i.isValidElement(H)&&(e=H),i.isValidElement(V)&&(t=V),"function"===typeof H&&(e=H()),"function"===typeof V&&(t=V()),i.createElement(i.Fragment,null,e&&i.cloneElement(e,k({onClick:G},e.props)),t&&i.cloneElement(t,k({onClick:J},t.props)))}():S}))},D=(0,i.forwardRef)(N)}}]);
//# sourceMappingURL=307.027f8ba5.chunk.js.map