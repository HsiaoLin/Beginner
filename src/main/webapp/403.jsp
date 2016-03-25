<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/static/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="403 Error Page" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="css/bootstrap.min.css" rel="stylesheet" />
		<link href="css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="css/font-awesome.min.css" />
		<link rel="stylesheet" href="css/ace.min.css" />
		<link rel="stylesheet" href="css/ace-responsive.min.css" />
		<link rel="stylesheet" href="css/ace-skins.min.css" />
		<!--[if lt IE 9]>
			<link rel="stylesheet" href="css/ace-ie.min.css" />
		<![endif]-->
<body>
	<div class="jumbotron">
		<h1>Jumbotron</h1>
		<p>This is a simple hero unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
		<p><a class="btn btn-primary btn-lg">Learn more</a></p>
	</div>
	<div class="navbar navbar-inverse"></div>
	<div class="container-fluid" id="main-container">
		<div id="main-content" class="clearfix">
			<div id="page-content" class="clearfix">
				<div class="row-fluid">
					<div class="error-container">
						<div class="well">
							<h1 class="grey lighter smaller">
								<span class="blue bigger-125"><i class="icon-sitemap"></i> 403 Forbidden</span> 服务器拒绝了您的请求 
							</h1>
							<hr />
							<h3 class="lighter smaller">You don't have permission to access on this server!</h3>
							
							<div>
								
								<div class="space"></div>
								
								<h4 class="smaller">Try one of the following:</h4>
								<ul class="unstyled spaced inline bigger-110">
									<li><i class="icon-hand-right blue"></i> 服务器拒绝了您的请求</li>
									<li><i class="icon-hand-right blue"></i> 请确定您拥有访问权限</li>
								</ul>
							</div>
							
							<hr />
							<div class="space"></div>
							
							<div class="row-fluid">
								<div id="zhongxin">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="1.9.1/jquery.min.js"></script>
	<script type="text/javascript">
		window.jQuery || document.write("<script src='js/jquery-1.11.3.min.js'>\x3C/script>");
	</script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/ace-elements.min.js"></script>
	<script src="js/ace.min.js"></script>
	<script type="text/javascript">$(top.hangge());</script>
</body>
</html>