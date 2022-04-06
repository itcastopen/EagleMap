/*! For license information please see 23.d300c348.chunk.js.LICENSE.txt */
"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[23],{8810:function(e,t,o){function i(){var e=window.BMapGL||{},t=window.BMapGLLib||{},o=window.gcoord||{},i=this.$options,r=i.id,n=i.points,a=void 0===n?[116.404,39.915]:n,l=i.areaName,c=i.poiText,s=void 0===c||c,p=i.poiIcon,d=void 0===p||p,m=i.zoom,g=void 0===m?10:m,v=i.drivingPath,h=void 0===v?[]:v,u=i.enableScrollWheel,f=void 0!==u&&u,y=i.isScaleCtrl,w=void 0!==y&&y,b=i.isZoomCtrl,k=void 0!==b&&b,C=i.navi3DCtrl,O=void 0!==C&&C,M=i.enclosure,P=void 0===M?{}:M,L=i.coordType,A=i.drivingType,F=new e.Map(r),x=a;function T(e){var t=[];return e.forEach((function(e){var o=W([e.lng,e.lat]);t.push({lng:o[0],lat:o[1]})})),t}function W(e){return o.transform(e,o.GCJ02,o.BD09)}L||(x=W(a),h&&(h=T(h)),P&&P.point&&(P.point=W(P.point)),P&&P.points&&(P.points=T(P.points)));var E=this.$options.points&&2===a.length?new e.Point(x[0],x[1]):null;if(F.centerAndZoom(null!==E&&void 0!==E?E:l,g),F.enableScrollWheelZoom(f),F.setDisplayOptions({poiText:s,poiIcon:d}),O){var j=new e.NavigationControl3D;F.addControl(j)}if(w){var D=new e.ScaleControl;F.addControl(D)}if(k){var S=new e.ZoomControl;F.addControl(S)}if(h&&h.length>=2){var B=[h[0].lng,h[0].lat],I=[h.at(-1).lng,h.at(-1).lat];if(A&&"path"===A){var _=new e.Point(B[0],B[1]),z=new e.Point(I[0],I[1]);new e.DrivingRoute(F,{renderOptions:{map:F,autoViewport:!0}}).search(_,z)}else{F.centerAndZoom(new e.Point(B[0],B[1]));for(var Z=[],N=0;N<h.length;N++)Z.push(new e.Point(h[N].lng,h[N].lat));var G=new e.Polyline(Z,{strokeColor:"#6699FF",strokeWeight:6}),$=new e.Marker(new e.Point(B[0],B[1]),{title:"\u8d77\u70b9"}),V=new e.Marker(new e.Point(I[0],I[1]),{title:"\u7ec8\u70b9"});setTimeout((function(){new t.TrackAnimation(F,G,{overallView:!0,tilt:30,duration:4e3,delay:300}).start(),F.addOverlay($),F.addOverlay(V)}),1e3)}}if(P&&"circle"===P.type){var R=P.fillColor,q=void 0===R?"#6699FF":R,U=P.fillOpacity,H=void 0===U?.3:U,J=P.strokeColor,K=void 0===J?"#6699FF":J,Q=P.strokeWeight,Y=void 0===Q?2:Q,X=P.strokeOpacity,ee=void 0===X?1:X,te=P.point,oe=P.radius,ie=new e.Circle(new e.Point(te[0],te[1]),oe,{fillColor:q,fillOpacity:H,strokeColor:K,strokeWeight:Y,strokeOpacity:ee});!function(e,t){var o=20,i=20;for(;i<t;)i*=2,o--;F.centerAndZoom(e,o+1)}(new e.Point(te[0],te[1]),oe),F.addOverlay(ie)}else if(P&&"polygon"===P.type){var re=P.fillColor,ne=(q=void 0===re?"#6699FF":re,P.fillOpacity),ae=(H=void 0===ne?.3:ne,P.strokeColor),le=(K=void 0===ae?"#6699FF":ae,P.strokeWeight),ce=(Y=void 0===le?2:le,P.strokeOpacity),se=(ee=void 0===ce?1:ce,P.points),pe=void 0===se?[]:se,de=[];for(N=0;N<pe.length;N++)de.push(new e.Point(pe[N].lng,pe[N].lat));var me=new e.Polygon(de,{fillColor:q,fillOpacity:H,strokeColor:K,strokeWeight:Y,strokeOpacity:ee});je(de),F.addOverlay(me)}else if(P&&"district"===P.type){var ge=P.fillColor,ve=void 0===ge?"#6699FF":ge,he=P.fillOpacity,ue=void 0===he?.3:he,fe=P.strokeColor,ye=void 0===fe?"#6699FF":fe,we=P.strokeWeight,be=void 0===we?2:we,ke=P.strokeOpacity,Ce=(ee=void 0===ke?1:ke,P.proname),Oe=void 0===Ce?"\u5317\u4eac\u5e02":Ce;P.prokind,(new e.Boundary).get(Oe,(function(e){je(e.boundaries[0].split(";").map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}})))})),setTimeout((function(){var t=new e.DistrictLayer({name:"(".concat(Oe,")"),fillColor:ve,strokeWeight:be,strokeColor:ye,fillOpacity:ue});F.addDistrictLayer(t)}),1200)}else if(P&&"polyline"===P.type){var Me=P.strokeColor,Pe=(K=void 0===Me?"#6699FF":Me,P.strokeWeight),Le=(Y=void 0===Pe?4:Pe,P.strokeOpacity),Ae=(ee=void 0===Le?1:Le,P.points),Fe=P.offset,xe=[];for(N=0;N<Ae.length;N++)xe.push(new e.Point(Ae[N].lng,Ae[N].lat));var Te={position:new e.Point(Ae[0].lng,Ae[0].lat),offset:new e.Size(30,-30)},We=new e.Label("\u7ebf\u6027\u8986\u76d6\u8f90\u5c04\u8303\u56f4\uff1a ".concat(Fe," \u7c73"),Te);We.setStyle({color:"#6699FF",borderRadius:"5px",borderColor:"#ccc",padding:"5px 15px",fontSize:"14px",height:"28px",lineHeight:"30px",fontFamily:"\u5fae\u8f6f\u96c5\u9ed1"});var Ee=new e.Polyline(xe,{strokeColor:K,strokeWeight:Y,strokeOpacity:ee});je(Ae),F.addOverlay(We),F.addOverlay(Ee)}function je(e){var t=F.getViewport(e),o=t.zoom,i=t.center;F.centerAndZoom(i,o)}}function r(){var e=window.AMap||{},t=window.AMapUI||{},o=this.$options,i=o.id,r=o.points,n=void 0===r?[116.404,39.915]:r;o.areaName;var a=o.zoom,l=void 0===a?10:a,c=o.isScaleCtrl,s=void 0!==c&&c,p=o.isZoomCtrl,d=void 0!==p&&p,m=o.navi3DCtrl,g=void 0!==m&&m,v=o.enclosure,h=void 0===v?{}:v,u=o.drivingType,f=o.drivingPath,y=void 0===f?[]:f,w=new e.Map(i,{center:n,resizeEnable:!0,zoom:l});if(s&&w.addControl(new e.Scale({visible:s})),d&&w.addControl(new e.ToolBar({visible:d,position:{bottom:"60px",right:"40px"}})),g&&w.addControl(new e.MapType),"path"===u&&y.length>2&&e.plugin(["AMap.DragRoute"],(function(){for(var t=[],o=0;o<y.length;o++)t.push([y[o].lng,y[o].lat]);new e.DragRoute(w,t,e.DrivingPolicy.LEAST_FEE).search()})),y&&y.length>2){for(var b=[],k=0;k<y.length;k++)b.push(new e.LngLat(y[k].lng,y[k].lat));var C=new e.Polyline({path:b,strokeColor:"#6699FF",strokeWeight:6,strokeOpacity:.9,zIndex:50,bubble:!0}),O=new e.Icon({size:new e.Size(25,34),image:"//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png",imageSize:new e.Size(135,40),imageOffset:new e.Pixel(-9,-3)}),M=new e.Marker({position:new e.LngLat(y[0].lng,y[0].lat),icon:O,offset:new e.Pixel(-13,-30)}),P=new e.Icon({size:new e.Size(25,34),image:"//a.amap.com/jsapi_demos/static/demo-center/icons/dir-marker.png",imageSize:new e.Size(135,40),imageOffset:new e.Pixel(-95,-3)}),L=y.at(-1),A=new e.Marker({position:new e.LngLat(L.lng,L.lat),icon:P,offset:new e.Pixel(-13,-30)});w.add(M),w.add(C),w.add(A),w.setFitView()}if(h&&h.points&&"polygon"===h.type){var F=h.points,x=h.fillColor,T=void 0===x?"#6699FF":x,W=h.fillOpacity,E=void 0===W?.3:W,j=h.strokeColor,D=void 0===j?"#6699FF":j,S=h.borderWeight,B=void 0===S?2:S;for(b=[],k=0;k<F.length;k++)b.push(new e.LngLat(F[k].lng,F[k].lat));var I=new e.Polygon({path:b,fillColor:T,borderWeight:B,strokeColor:D,fillOpacity:E});w.add(I),w.setFitView()}if(h&&h.point&&"circle"===h.type){var _=h.point,z=h.radius,Z=h.fillColor,N=(T=void 0===Z?"#6699FF":Z,h.fillOpacity),G=(E=void 0===N?.3:N,h.strokeColor),$=(D=void 0===G?"#6699FF":G,h.strokeWeight),V=void 0===$?2:$,R=new e.Circle({center:new e.LngLat(_[0],_[1]),radius:z,fillColor:T,fillOpacity:E,strokeColor:D,strokeWeight:V});w.add(R),w.setFitView()}if(h&&"district"===h.type){var q=h.fillColor,U=void 0===q?"#6699FF":q,H=h.fillOpacity,J=void 0===H?.3:H,K=h.strokeColor,Q=void 0===K?"#6699FF":K,Y=h.strokeWeight,X=void 0===Y?1:Y,ee=h.strokeOpacity,te=void 0===ee?1:ee,oe=h.adcodes;t.loadUI(["geo/DistrictExplorer"],(function(e){var t=new e({map:w});t.loadMultiAreaNodes(oe,(function(e,o){t.clearFeaturePolygons();for(var i=0,r=o.length;i<r;i++)n=o[i],t.renderSubFeatures(n,(function(e,t){return{cursor:"default",bubble:!0,strokeColor:Q,strokeOpacity:te,strokeWeight:X,fillColor:U,fillOpacity:J}}));var n;w.setFitView(t.getAllFeaturePolygons())}))}))}if(h&&h.points&&"polyline"===h.type){var ie=h.strokeColor,re=(D=void 0===ie?"#6699FF":ie,h.strokeWeight);V=void 0===re?4:re;h.strokeOpacity;var ne=h.points,ae=h.offset;for(_=[],k=0;k<ne.length;k++)_.push(new e.LngLat(ne[k].lng,ne[k].lat));var le=new e.Text({map:w,text:"\u7ebf\u6027\u8986\u76d6\u8f90\u5c04\u8303\u56f4\uff1a ".concat(ae," \u7c73"),position:new e.LngLat(ne[0].lng,ne[0].lat),style:{borderRadius:"5px",borderColor:"#ccc",padding:"0px 15px",fontSize:"14px",height:"28px",lineHeight:"30px",fontFamily:"\u5fae\u8f6f\u96c5\u9ed1"}});C=new e.Polyline({path:_,borderWeight:V,strokeColor:D,color:"#6699FF"});w.add(le),w.add(C),w.setFitView()}}function n(e,t,o){if(o||2===arguments.length)for(var i,r=0,n=t.length;r<n;r++)!i&&r in t||(i||(i=Array.prototype.slice.call(t,0,r)),i[r]=t[r]);return e.concat(i||Array.prototype.slice.call(t))}function a(){var e=window.TMap||{},t=this.$options,o=t.id,i=void 0===o?"map":o,r=t.points,a=void 0===r?[116.307503,39.984104]:r,l=t.zoom,c=void 0===l?10:l,s=t.isScaleCtrl,p=void 0!==s&&s,d=t.isZoomCtrl,m=void 0!==d&&d,g=t.navi3DCtrl,v=void 0!==g&&g,h=t.drivingPath,u=void 0===h?[]:h,f=t.enclosure,y=void 0===f?{}:f,w=new e.LatLng(a[1],a[0]),b=new e.Map(document.getElementById(i),{center:w,zoom:c});if(m||b.removeControl(e.constants.DEFAULT_CONTROL_ID.ZOOM),p||b.removeControl(e.constants.DEFAULT_CONTROL_ID.SCALE),v||b.removeControl(e.constants.DEFAULT_CONTROL_ID.ROTATION),u&&u.length>=2){var k=[];u.forEach((function(t){k.push(new e.LatLng(t.lat,t.lng))}));var C=u.at(-1),O=new e.LatLng(u[0].lat,u[0].lng),M=new e.LatLng(C.lat,C.lng);new e.MultiMarker({id:"marker-layer",map:b,styles:{start:new e.MarkerStyle({width:25,height:35,anchor:{x:16,y:32},src:"https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/start.png"}),end:new e.MarkerStyle({width:25,height:35,anchor:{x:16,y:32},src:"https://mapapi.qq.com/web/lbs/javascriptGL/demo/img/end.png"})},geometries:[{id:"start",styleId:"start",position:O},{id:"end",styleId:"end",position:M}]}),new e.MultiPolyline({id:"polyline-layer",map:b,styles:{style_blue:new e.PolylineStyle({color:"#3777FF",width:6,lineCap:"round"})},geometries:[{id:"pl_1",styleId:"style_blue",paths:k}]}),j(E(k))}if(y&&y.points){var P=y.points,L=y.radius,A=y.strokeColor,F=y.strokeWeight,x=[];P.forEach((function(t){x.push(new e.LatLng(t.lat,t.lng))})),new e.MultiPolygon({id:"polygon-layer",map:b,styles:{polygon:new e.PolygonStyle({color:"rgba(0,125,255,0.3)",showBorder:!0,borderColor:A||"#6699FF"})},geometries:[{id:"polygon",styleId:"polygon",paths:x,properties:{title:"polygon"}}]}),j(E(x)),b.setCenter(x[0])}if(y&&y.point){var T=y.point,W=(L=y.radius,A=y.strokeColor,F=y.strokeWeight,new e.LatLng(T[1],T[0]));new e.MultiCircle({map:b,styles:{circle:new e.CircleStyle({color:"rgba(41,91,255,0.3)",showBorder:!0,borderColor:A||"rgba(41,91,255,1)",borderWidth:F})},geometries:[{styleId:"circle",center:W,radius:L}]}),j({distance:2*L,cent:W})}function E(o){var i=[],r=[],a=[],l=[];o.forEach((function(e){(void 0===i[0]||i[0]<e.lat)&&(i[0]=e.lat),(void 0===i[1]||i[1]>e.lng)&&(i[1]=e.lng),(void 0===r[0]||r[0]>e.lat)&&(r[0]=e.lat),(void 0===r[1]||r[1]<e.lng)&&(r[1]=e.lng),(void 0===a[0]||a[0]<e.lat)&&(a[0]=e.lat),(void 0===a[1]||a[1]<e.lng)&&(a[1]=e.lng),(void 0===l[0]||l[0]>e.lat)&&(l[0]=e.lat),(void 0===l[1]||l[1]>e.lng)&&(l[1]=e.lng)}));var c=[new e.LatLng(i[0],i[1]),new e.LatLng(r[0],r[1])],s=e.geometry.computeDistance(c),p=n(n([],c,!0),[new e.LatLng(a[0],a[1]),new e.LatLng(l[0],l[1])],!1),d="";try{d=e.geometry.computeCentroid(p)}catch(t){d=void 0}return{distance:s.toFixed(2),cent:d}}function j(e){var t=e.distance,o=e.cent;if(console.log(t,o),o&&(console.log(2,t,o),b.setCenter(o)),t){var i=10;i=t<80?16:80<t&&t<1e3?15:1e3<t&&t<3e3?14:3e3<t&&t<7e3?13:7e3<t&&t<15e3?12:10,b.setZoom(i)}}}o.d(t,{Z:function(){return l}});var l=function(){function e(t){this.initBMap=i,this.initAMap=r,this.initTMap=a,this.$options=t,this.mapAk="";var o=t.type;e.wd.$catch&&e.wd.$catch===o?o&&"TMAP"===o?this.initTMap():o&&"AMAP"===o?this.initAMap():o&&"BMAP"===o&&this.initBMap():this.initMap()}return e.prototype.initMap=function(){var e=this.$options.type;this.getMapAK(e),window.$catch=e,e&&"TMAP"===e?this.createTMap():e&&"AMAP"===e?this.createAMap():e&&"BMAP"===e&&this.createBMap()},e.prototype.getMapAK=function(e){var t=this.$options.mapAk;if(t)this.mapAk=t;else switch(e){case"TMAP":this.mapAk="OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77";break;case"AMAP":this.mapAk="c081218aae02057742b9f1a100d6cf66";break;default:this.mapAk="f7HnzEYGLgOyMyDVTh99ZEQqjlEzuhyC"}},e.prototype.createBMap=function(){var e=this,t=window||{},o=this.$options,i=o.drivingPath,r=o.enclosure,n=o.coordType,a=document.createElement("link");if(a.setAttribute("rel","stylesheet"),a.setAttribute("type","text/css"),a.setAttribute("href","http://api.map.baidu.com/res/webgl/10/bmap.css"),document.getElementsByTagName("head")[0].appendChild(a),!n){var l=document.createElement("script");l.src="https://unpkg.com/gcoord/dist/gcoord.js",document.body.appendChild(l)}var c=document.createElement("script");if(c.src="http://api.map.baidu.com/getscript?type=webgl&v=1.0&ak=".concat(e.mapAk,"&services=&t=").concat((new Date).getTime()),document.body.appendChild(c),t.BMapGL_loadScriptTime=(new Date).getTime(),t.BMapGL=t.BMapGL||{},t.BMapGL.apiLoad=function(){delete t.BMapGL.apiLoad,console.log(!r,!r||r&&"district"!=r.type),(!r||r&&"district"!=r.type)&&(console.log(33333,"\u521d\u59cb\u5316\u8f68\u8ff9"),e.initBMap())},i&&i.length>=2){var s=document.createElement("script");s.src="//mapopen.bj.bcebos.com/github/BMapGLLib/TrackAnimation/src/TrackAnimation.min.js",document.body.appendChild(s)}r&&"district"===r.type&&(c.onload=function(){console.log(33333,"\u521d\u59cb\u5316\u975e\u8f68\u8ff9"),e.initBMap()})},e.prototype.createAMap=function(){var e=this,t=this.$options.enclosure,o="https://webapi.amap.com/maps?v=2.0&key=".concat(e.mapAk,"&&plugin=AMap.Scale,AMap.HawkEye,AMap.ToolBar,AMap.MapType"),i=document.createElement("script");i.src=o,document.body.appendChild(i),i.onload=function(){if(t&&"district"===t.type){var o=document.createElement("script");o.src="//webapi.amap.com/ui/1.1/main.js?v=1.1.1",document.body.appendChild(o),o.onload=function(){e.initAMap()}}else e.initAMap()}},e.prototype.createTMap=function(){var e=this,t="https://map.qq.com/api/gljs?v=1.exp&key=".concat(e.mapAk),o=document.createElement("script");o.src=t,document.body.appendChild(o),o.onload=function(){e.initTMap()}},e.wd=window||{},e}()},1082:function(e,t,o){o.d(t,{a:function(){return w}});var i=o(5905),r=o(3671),n=o(2791),a=o(421),l=o(6777),c=o(1694),s=o.n(c),p=o(3350),d=o(2670),m=(0,n.createContext)({maxItemWidthInContext:"",theme:"light"}),g=["children","separator","disabled","maxItemWidth","maxWidth","href","to","target","router","replace"];function v(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);t&&(i=i.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,i)}return o}var h=(0,n.forwardRef)((function(e,t){var o=e.children,l=e.separator,c=void 0===l?n.createElement(p.Z,{style:{color:"rgba(0,0,0,.3)"}}):l,h=e.disabled,u=e.maxItemWidth,f=e.maxWidth,y=e.href,w=e.to,b=e.target,k=e.router,C=e.replace,O=(0,r._)(e,g),M=(0,n.useContext)(m),P=M.maxItemWidthInContext,L=M.theme,A=(0,a.Z)().classPrefix,F=(0,d.Z)(),x=s()("".concat(A,"-breadcrumb__item")),T="".concat(A,"-breadcrumb__inner"),W=s()("".concat(A,"-breadcrumb--text-overflow"),(0,i._)({},F.STATUS.disabled,h)),E="".concat(A,"-breadcrumb__separator"),j="".concat(A,"-link"),D=(0,n.useMemo)((function(){return{maxWidth:f||u||P||"120px"}}),[u,f,P]),S=n.createElement("span",{className:T,style:D},o),B=n.createElement("span",{className:W},S);if((y||w)&&!h){B=n.createElement("a",{className:s()(W,j),href:y,target:b,onClick:function(){!y&&k&&(C?k.replace(w):k.push(w))}},S)}var I="function"===typeof c?c():c;return n.createElement("div",function(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?v(Object(o),!0).forEach((function(t){(0,i._)(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):v(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}({className:s()(x,L),ref:t},O),B,n.createElement("span",{className:E},I))}));h.displayName="BreadcrumbItem";o(2110);var u=["children","options","separator","maxItemWidth","theme"];function f(e,t){var o=Object.keys(e);if(Object.getOwnPropertySymbols){var i=Object.getOwnPropertySymbols(e);t&&(i=i.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),o.push.apply(o,i)}return o}var y=(0,l.Z)((function(e,t){var o=e.children,l=e.options,c=e.separator,s=e.maxItemWidth,p=e.theme,d=(0,r._)(e,u),g=(0,a.Z)().classPrefix,v=o;return l&&l.length&&(v=l.map((function(e,t){return n.createElement(h,{key:t,maxWidth:e.maxWidth,disabled:e.disabled,href:e.href,target:e.target,to:e.to,router:e.router,replace:e.replace,separator:c,maxItemWidth:s},e.content||e.children)}))),n.createElement(m.Provider,{value:{maxItemWidthInContext:s,theme:p}},n.createElement("div",function(e){for(var t=1;t<arguments.length;t++){var o=null!=arguments[t]?arguments[t]:{};t%2?f(Object(o),!0).forEach((function(t){(0,i._)(e,t,o[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(o)):f(Object(o)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(o,t))}))}return e}({ref:t,className:"".concat(g,"-breadcrumb")},d),v))}),{BreadcrumbItem:h});y.displayName="Breadcrumb";var w=y}}]);
//# sourceMappingURL=23.d300c348.chunk.js.map