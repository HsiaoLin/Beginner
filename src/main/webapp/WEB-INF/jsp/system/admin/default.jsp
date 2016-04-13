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
<script type="text/javascript">
function toggleClazzs() {
	$("body").toggleClass("left-side-collapsed");
}
</script>
</head>
<body class="left-side-collapsed">
	<!-- <div class="container-fluid" id="main-container">
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
</a> -->
<div class="container-fluid" id="content-container">
<div class="content-wrapper">
<div class="row">
<div class="side-nav-content">
<div class="main-content-wrapper">
<div class="container-fluid container-padded dash-controls">
<div class="row">

<div class="col-md-12">
<div class="panel panel-plain">
<div class="panel-heading">
<h3 class="panel-title">Responsive tabs</h3>
</div>
<div class="panel-body">
<ul class="nav nav-tabs tab-drop">
<li class="active"><a href="#tab1" data-toggle="tab"><i class="fa fa-home"></i></a></li>
<li><a href="#tab2" data-toggle="tab">Section 2</a></li>
<li><a href="#tab3" data-toggle="tab">Section 3</a></li>
<li><a href="#tab4" data-toggle="tab">Section 4</a></li>
<li><a href="#tab5" data-toggle="tab">Section 5</a></li>
<li><a href="#tab6" data-toggle="tab">Section 6</a></li>
<li><a href="#tab7" data-toggle="tab">Section 7</a></li>
<li><a href="#tab8" data-toggle="tab">Section 8</a></li>
<li><a href="#tab9" data-toggle="tab">Section 9</a></li>
</ul>
<div class="panel panel-default m-bot0">
<div class="panel-body tab-content">
<div class="tab-pane active" id="tab1">
<p>I'm in Section 1.</p>
<p>Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</p>
</div>
<div class="tab-pane" id="tab2">
<p>Howdy, I'm in Section 2.</p>
<p>Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.</p>
</div>
<div class="tab-pane" id="tab3">
<p>Howdy, I'm in Section 3.</p>
<p>Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</p>
</div>
<div class="tab-pane" id="tab4">
<p>Howdy, I'm in Section 4.</p>
<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.</p>
</div>
<div class="tab-pane" id="tab5">
<p>Howdy, I'm in Section 5.</p>
<p>Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</p>
</div>
<div class="tab-pane" id="tab6">
<p>Howdy, I'm in Section 6.</p>
<p>Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.</p>
</div>
<div class="tab-pane" id="tab7">
<p>Howdy, I'm in Section 7.</p>
<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et.</p>
</div>
<div class="tab-pane" id="tab8">
<p>Howdy, I'm in Section 8.</p>
<p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch.</p>
</div>
<div class="tab-pane" id="tab9">
<p>Howdy, I'm in Section 9.</p>
<p>Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.</p>
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
<!-- <script type="text/javascript">window.jQuery|| document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script> -->
<!-- <script type="text/javascript">$(top.hangge());</script> -->
</body>
</html>
