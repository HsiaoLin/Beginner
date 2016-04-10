<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String pathf = request.getContextPath();
	String basePathf = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePathf%>">
</head>
<body>
<footer>
<div class="container" style="text-align: center;">
<div class="row">
<div class="col-md-12">
<ul class="list-inline">
<li><a href="#">关于我们</a>　丨</li>
<li><a href="#">意见反馈</a>　丨</li>
<li><a href="#">帮助中心</a>　丨</li>
<li><a href="#">友情链接</a></li>
</ul>
<ul class="list-inline">
<li><small>&copy; 2015-9999 Beginner 版权所有</small></li>
<li><small><a href="#"></a></small></li>
<li><small><a href="#"></a></small></li>
<li><small><a href="#"></a></small></li>
</ul>
</div>
</div>
</div>
</footer>
</body>