<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/js/modal-dialog.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.bootstrap.teninedialog.v3.js"></script>
		
<script type="text/javascript">
</script>
	</head>
<body>
	<form action="back/account/add.do" name="Form" id="Form" method="post">
		<input type="hidden" name="userId" id="userId" value="${pd.userId}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">用户名</span>
    					<input disabled="disabled" type="text" class="form-control" id="userName" name="userName" style="width:180px;"
    						value="${pd.userName}">
  					</div>
				</td>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">邮箱</span>
    					<input disabled="disabled" type="email" class="form-control" id="userMail" name="userMail" style="width:180px;"
    						value="${pd.userMail}">
  					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">电话</span>
    					<input disabled="disabled" type="text" class="form-control" id="userPhone" name="userPhone" style="width:180px;"
    						value="${pd.userPhone}">
  					</div>
				</td>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">用户状态</span>
    					<input disabled="disabled" type="text" class="form-control" id="userStatus" name="userStatus" 
    						value='<c:choose><c:when test="${pd.userStatus=='1'}">审核通过</c:when><c:otherwise>待审核</c:otherwise></c:choose>' style='width:180px;color:<c:choose><c:when test="${pd.userStatus=='1'}">green</c:when><c:otherwise>blue</c:otherwise></c:choose>'>
  					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">用户类型</span>
    					<input disabled="disabled" type="text" class="form-control" id="userType" name="userType" style="width:180px;"
    						value="${pd.userType}">
  					</div>
				</td>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">上次登录时间</span>
    					<input disabled="disabled" type="text" class="form-control" id="lastLogin" name="lastLogin" style="width:180px;" 
    						value='${pd.lastLogin }'>
  					</div>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			//日期框
			$('.date-picker').datepicker();
		});
		</script>
</body>
</html>