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
	<!-- JS/CSS -->
	<%@ include file="top.jsp"%>
	<style type="text/css">
		.dropdown:hover .dropdown-menu {
			display: block;
			margin-top: 0; // remove the gap so it doesn't close
		}
	</style>
</head>
<body>
	<!-- 顶部导航 -->
	<%@ include file="head.jsp"%>

	<div class="container-fluid" id="content-container">
	<div class="content-wrapper">
	<div class="row">
	<div class="side-nav-content">
		<!-- 左侧菜单 -->
		<%@ include file="left.jsp"%>

		<iframe name="mainFrame" id="mainFrame" scrolling="no" frameborder="0"
			src="<%=basePath%>login/default" onLoad="iFrameHeight()"
			style="margin:0 auto;float: right;width: 100%">
		</iframe>
	</div>
	</div>
	</div>
	</div>

	<%@ include file="footer.jsp"%>
	<!-- <script type="text/javascript" src="static/js/system/index.js"></script> -->
	<!-- <script type="text/javascript" src="static/js/system/menusf.js"></script> -->
	<!-- <script type="text/javascript" src="static/js/jquery.cookie.js"></script> -->
</body>
</html>
