<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<!-- 引入 -->
<script type="text/javascript">
	window.jQuery|| document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
<!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script>
<!-- 日期框 -->
</head>
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4 style="margin-left: 42%;">机票信息</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main  form-horizontal">
							<c:choose>
								<c:when test="${not empty pd}">
									<table class="table table-bordered">
										<tr>
											<td style="width: 50%;">乘机人姓名：${pd.psr_name}</td>
											<td style="width: 50%;">证件号码：${pd.card_no}</td>
										</tr>
										<tr>
											<td style="width: 50%;">票号：${pd.ticket_no}</td>
											
											<td style="width: 50%;">票号回填时间：<fmt:formatDate type="time" value="${pd.backfill_time}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
										<tr>
											<td colspan="2" style="width: auto;">备注信息：${pd.ticket_remark}</td>
										</tr>
									</table>
								</c:when>
								<c:otherwise>
									<div class="center">没有机票信息请手动挂机票信息！</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

