<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>
</head>
<body>
<div class="container-fluid" id="content-container">
<div class="content-wrapper">
<div class="row">
<div class="side-nav-content">
<div class="main-content-wrapper">
<div class="container-fluid container-padded dash-controls">
<div class="row">

<div class="col-md-12">
<ol class="breadcrumb">
<li><a href="#">首页</a></li>
<li><a href="#">团队监控</a></li>
<li class="active">定时任务监控</li>
</ol>
<div class="panel panel-plain">
<div class="panel-heading">
<h2 class="panel-title">定时任务监控</h2>
</div>
</div>
</div>

<div class="container-fluid container-padded">
<div class="row">
<div class="col-md-12">
<div class="panel panel-plain">
<div class="panel-body">
<div id="map"></div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=nlouHo1mQbPucGRjr84uFvDM"></script>
<script type="text/javascript">
	var map = new BMap.Map("map"); // 创建地图实例  
	var point = new BMap.Point(116.404, 39.915); // 创建点坐标  
	map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
	map.addControl(new BMap.NavigationControl());
	map.addControl(new BMap.ScaleControl());
	map.addControl(new BMap.OverviewMapControl());
	map.addControl(new BMap.MapTypeControl());
	map.setCurrentCity("北京"); // 仅当设置城市信息时，MapTypeControl的切换功能才能可用
</script>
</body>
</html>
