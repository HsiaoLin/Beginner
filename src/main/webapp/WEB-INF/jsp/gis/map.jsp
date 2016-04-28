<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
<%@ include file="../system/admin/top.jsp"%> 
<!-- GIS -->
<link rel="stylesheet" type="text/css" href="static/arcgis/js/dojo/dijit/themes/tundra/tundra.css" />
<script type="text/javascript" src="../../../../serverapi.arcgisonline.com/jsapi/arcgis/-v=1.5"></script>
<script type="text/javascript" src="static/arcgis/init.js"></script>
<link rel="stylesheet" type="text/css" href="static/arcgis/js/esri/css/esri.css" />
<style>
#overviewMapDiv-map_gc{
	left:520px;
}
</style>
</head>
<body class="tundra">
<div class="container-fluid" id="main-container">
<div id="page-content" class="clearfix">
<div class="row-fluid">
	<div class="row-fluid">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="_name" type="text" name="_name" value="" placeholder="这里输入关键词" />
							<i class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" title="检索"><i id="queryName" class="icon-search">搜索</i></button></td>
				</tr>
			</table>
		</div>
	</div>
<div id="arcgisDiv" style="width: 1650px; height: 750px; border: 1px solid #000;">
	<div id="overviewMapDiv" style="width: 300px;"></div>
</div>
</div>
</div>
<!-- 返回顶部按钮  -->
<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
	<i class="icon-double-angle-up icon-only"></i>
</a>
<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
<script type="text/javascript">
	$(top.shutdown());
	//引入
	dojo.require("esri/map");
	dojo.require("esri/dijit/Scalebar");
	dojo.require("esri/dijit/OverviewMap");

	require(["esri/symbols/SimpleMarkerSymbol",
		"esri/symbols/SimpleLineSymbol",
		"esri/symbols/SimpleFillSymbol", "esri/graphic",
		"esri/Color", "dijit/registry", "esri/geometry/Point",
		"esri/InfoTemplate", "dojo/_base/connect", "dojo/domReady!" ],
		function(SimpleMarkerSymbol, SimpleLineSymbol,SimpleFillSymbol, Graphic, Color, registry, Point,InfoTemplate, connect) {
			
		}
	);

	var map,myTiledMapServiceLayer,markerSymbol;
	function init() {
		var r = Math.floor(Math.random() * 255);
		var g = Math.floor(Math.random() * 255);
		var b = Math.floor(Math.random() * 255);
		//图片标注
		//markerSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.STYLE_CIRCLE, 8, new SimpleLineSymbol(SimpleLineSymbol.STYLE_SOLID,new Color([ r, g, b, 4 ]), 8), new Color([r, g, b, 0.9 ]));
		// 地图缩放存续时间默认250毫秒
		esriConfig.defaults.map.zoomDuration = 1000;
		// 地图缩放刷新率默认25
		esriConfig.defaults.map.zoomRate = 50;

		//滑动条
		esriConfig.defaults.map.slider = { right:"10px", top:"10px", width:null, height:"100px" };

		//var myExtent = new esri.geometry.Extent(-125, 28, -62, 45, new esri.SpatialReference({wkid:4326}));
		map = new esri.Map(
			"arcgisDiv",//装在map地图的容器ID
			{
				center : [ 116.596664, 40.071667 ],
				//extent:myExtent,//地图显示范围的四个点
				logo:false,//右下的esri logo
				nav:true//是否显示八方向滑动器默认false
			}
		);

		//比例尺
		var scalebar = new esri.dijit.Scalebar({ map: map, attachTo: "bottom-left" },
		dojo.byId("scaleBarDiv"));
		
		//初始化信息窗口
		dojo.connect(map,"onLoad", function(map) {map.infoWindow.resize(250, 100);} );
		
		//缓存图层
		myTiledMapServiceLayer = new esri.layers.ArcGISTiledMapServiceLayer(
				"http://cache1.arcgisonline.cn/arcgis/rest/services/ChinaOnlineCommunity/MapServer");
		//加入缓存图层
		map.addLayer(myTiledMapServiceLayer);

		//初始化缩放级别
		map.setZoom(12);

		//绑定地图点击事件，显示地图信息窗口显示坐标信息
		dojo.connect(map, "onClick", addPoint);
		//点击查询按钮描点
		//dojo.connect(dijit.byId("queryName"), "onclick", point);
		
		overviewMap();
	}

	//显示地图信息窗口显示坐标信息
	function point(evt) {
		var name = $("#_name").val();
		if (!name) {
			alert("请输入查询条件");
			return false;
		}
		$.ajax({
			url : "head/findCamera",
			dataType : "text",
			type : "POST",
			data : {
				name : name,
				type : 1
			},
			success : function(data) {
				var row = JSON.parse(data);
				for (var i = 0; i < row.length; i++) {
					/* var point = new Point({
						latitude : 24.485475,
						longitude : 118.123266
					});
					map.graphics.add(new Graphic(point, markerSymbol)); */

					var infoTemplate = new InfoTemplate("场站："
							+ row[i].PARK_NAME, "路线：" + row[i].LINE_NAME
							+ "<br/>路线序号：" + row[i].STATION_ORDER + "<br/>经度："
							+ row[i].LONGITUDE + "<br/>纬度：" + row[i].LATITUDE);
					map.infoWindow.setTitle("查询坐标");
					map.infoWindow.setContent("lat（纬度）/lon（经度） : " + row[i].lat + ", "
							+ row[i].lon + "<br />名称 : " + row[i].name);
					map.infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
				}
			},
			error : function(msg) {
				alert("查询出错！"+msg);
			}
		});
	}

	//显示地图信息窗口显示坐标信息
	function addPoint(evt) {
		map.infoWindow.setTitle("坐标");
		map.infoWindow.setContent("lat（纬度）/lon（经度） : " + evt.mapPoint.y + ", "
				+ evt.mapPoint.x + "<br />屏幕 x/y : " + evt.screenPoint.x + ", "
				+ evt.screenPoint.y);
		map.infoWindow.show(evt.screenPoint, map.getInfoWindowAnchor(evt.screenPoint));
	}
	dojo.addOnLoad(init);

	function overviewMap(){
		var over = {
			map:map,
			attachTo:"bottom-right",
			color:"#D84E13",
			expandFactor:2,
			baseLayer:myTiledMapServiceLayer
		};
		var mapview = new esri.dijit.OverviewMap(over,dojo.byId("overviewMapDiv"));
		mapview.startup();
	}
</script>
</body>
</html>