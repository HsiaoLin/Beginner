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
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="page-header position-relative">
				<h1>
					后台首页 <small><i class="icon-double-angle-right"></i> </small>
				</h1>
			</div>
			<div class="row-fluid">
				<div class="space-6"></div>
				<div class="row-fluid">
				</div>
			</div>
	</div>
</div>
<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse"> <i
	class="icon-double-angle-up icon-only"></i>
</a>
<script type="text/javascript">window.jQuery|| document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script type="text/javascript">$(top.hangge());</script>
</body>
</html>
