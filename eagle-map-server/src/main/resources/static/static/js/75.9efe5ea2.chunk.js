"use strict";(self.webpackChunkjava_eaglemap=self.webpackChunkjava_eaglemap||[]).push([[75],{6885:function(e,t,i){i.d(t,{v:function(){return u}});var n=i(5671),l=i(3144),r=i(136),s=i(5716),a=i(2791),o=i(8810),c=i(184),u=function(e){(0,r.Z)(i,e);var t=(0,s.Z)(i);function i(){return(0,n.Z)(this,i),t.apply(this,arguments)}return(0,l.Z)(i,[{key:"componentDidMount",value:function(){new o.Z(this.props.options)}},{key:"render",value:function(){return(0,c.jsx)("div",{id:"baidumap",style:{width:"100%",height:"560px"}})}}]),i}(a.Component)},9468:function(e,t,i){i.r(t);var n=i(9498),l=i(6885),r=i(1937),s=i(1082),a=i(2426),o=i.n(a),c=i(184),u=s.a.BreadcrumbItem;t.default=function(e){var t=e.location.state;console.log(2323,t);var i=JSON.parse(t.pointList),a="BAIDU"===t.provider?"BMAP":t.provider,d=i?(0,r.Pf)(a,i):[];console.log(a);var p={id:"baidumap",mapAk:"",poiText:!0,poiIcon:!0,type:a,points:[116.404,39.915],areaName:"\u4e0a\u6d77\u5e02",zoom:15,enableScrollWheel:!1,isScaleCtrl:!0,isZoomCtrl:!0,navi3DCtrl:!0,drivingPath:d};return(0,c.jsxs)(c.Fragment,{children:[(0,c.jsx)("div",{className:"BreadcrumbCont",children:(0,c.jsxs)(s.a,{maxItemWidth:"200px",theme:"light",children:[(0,c.jsx)(u,{href:"/trajectory",children:"\u8f68\u8ff9"}),(0,c.jsx)(u,{children:"\u8be6\u60c5"})]})}),(0,c.jsx)("div",{className:"container trajectory_details",children:(0,c.jsxs)("div",{className:"detailCont",children:[(0,c.jsx)(n.iA,{data:[t],columns:[{align:"left",className:"test",ellipsis:!0,colKey:"name",title:"\u8f68\u8ff9\u540d\u79f0"},{align:"left",className:"test4",ellipsis:!0,colKey:"id",title:"\u8f68\u8ff9ID"},{align:"left",className:"test4",ellipsis:!0,cell:function(e){var t=e.row;switch(null===t||void 0===t?void 0:t.provider){case"BAIDU":return(0,c.jsx)(c.Fragment,{children:"\u767e\u5ea6\u5730\u56fe"});case"AMAP":return(0,c.jsx)(c.Fragment,{children:"\u9ad8\u5fb7\u5730\u56fe"});case"QQ":return(0,c.jsx)(c.Fragment,{children:"\u817e\u8baf\u5730\u56fe"});default:return null}},title:"\u670d\u52a1\u5546",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"serverId",title:"\u670d\u52a1ID",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"distance",title:"\u8f68\u8ff9\u957f\u5ea6",cell:function(e){var t=e.row;return null!==t&&void 0!==t&&t.distance?null===t||void 0===t?void 0:t.distance.toFixed(2):"--"},minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"time",title:"\u6301\u7eed\u65f6\u95f4",cell:function(e){var t=e.row;return(0,r.i$)(t.time)},minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"startTime",cell:function(e){var t=e.row;return t.startTime?o()(t.startTime).format("YYYY-MM-DD HH:mm:ss"):"--"},title:"\u521b\u5efa\u65f6\u95f4",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"endTime",cell:function(e){var t=e.row;return t.startTime?o()(t.endTime).format("YYYY-MM-DD HH:mm:ss"):"--"},title:"\u7ed3\u675f\u65f6\u95f4",minWidth:200},{align:"left",className:"test4",ellipsis:!0,colKey:"size",title:"\u8f68\u8ff9\u70b9\u6570",minWidth:200}],rowKey:"index",verticalAlign:"top",bordered:!0,hover:!0}),(0,c.jsx)("div",{className:"DetBMap",children:(0,c.jsx)(l.v,{options:p})})]})})]})}},1937:function(e,t,i){function n(e,t){var i=[];return"BMAP"===e?i=t.map((function(e){return{lng:e.longitude,lat:e.latitude}})):"AMAP"===e?i=t.map((function(e){var t=e.location.split(",");return{lng:t[0],lat:t[1]}})):"TMAP"===e&&console.log("\u5f00\u53d1\u4e2d..."),i}function l(e,t,i){var n={};if("AMAP"===e&&"CIRCLE"===t)n.point=i.center.split(","),n.radius=i.radius,n.type="circle";else if("AMAP"===e&&"POLYGON"===t){n.type="polygon";var l=i.points.split(";");n.points=l.length>0&&l.map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}}))}else if("AMAP"===e&&"POLYLINE"===t){n.type="polyline";var r=i.points.split(";");n.offset=i.bufferradius,n.points=r.length>0&&r.map((function(e){var t=e.split(",");return{lng:t[0],lat:t[1]}}))}else if("AMAP"===e&&"DISTRICT"===t)n.type="district",n.adcodes=[i.adcode];else if("BMAP"===e&&"CIRCLE"===t)n.point=[i.longitude,i.latitude],n.radius=i.radius,n.type="circle";else if("BMAP"===e&&"POLYGON"===t){n.type="polygon";var s=i.vertexes.split(";");n.points=s.length>0&&s.map((function(e){var t=e.split(",");return{lng:t[1],lat:t[0]}}))}else if("BMAP"===e&&"POLYLINE"===t){n.type="polyline",n.offset=i.offset;var a=i.vertexes.split(";");n.points=a.length>0&&a.map((function(e){var t=e.split(",");return{lng:t[1],lat:t[0]}}))}else"BMAP"===e&&"DISTRICT"===t&&(n.type="district",n.proname=i.keyword);return n}function r(e){var t="--";return e&&e<6e4&&(t=Number(e)/1e3+"s"),e&&e>=6e4&&e<36e5&&(t=Number(e)/6e4+"m"),e&&e>36e5&&(t=Number(e)/36e5+"H"),t}i.d(t,{Pf:function(){return n},eB:function(){return l},i$:function(){return r}})}}]);
//# sourceMappingURL=75.9efe5ea2.chunk.js.map