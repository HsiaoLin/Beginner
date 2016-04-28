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
	//保存
	function save(){
		$.ajax({
             cache: true,
             type: "POST",
             url:"<%=basePath%>back/account/add.do",
             data:$('#Form').serialize(),// 你的formid
             async: false,
             dataType:'text',
             error: function(request) {
             	showAlert("提示","添加出错!",'',null);
             },
             success: function(data) {
             	if ("succ"==data){
             		showAlert("提示","添加成功!",'',null);
             	}else{
             		showAlert("提示","添加失败!",'',null);
             	}
             }
         });
	}
	
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
    					<input type="text" class="form-control" id="userName" name="userName" style="width:180px;"
    						value="${pd.userName}" required="required">
  					</div>
				</td>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">用户密码</span>
    					<input type="password" class="form-control" id="userPassword" name="userPassword" 
    						value='${pd.userPassword}'>
  					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">邮箱</span>
    					<input type="email" class="form-control" id="userMail" name="userMail" style="width:180px;"
    						value="${pd.userMail}" required="required">
  					</div>
				</td>
				<td>
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">电话</span>
    					<input type="text" class="form-control" id="userPhone" name="userPhone" style="width:180px;"
    						value="${pd.userPhone}">
  					</div>
				</td>
			</tr>
			<tr>
				<td colspan="1">
					<div class="input-group" style="vertical-align: middle;">
    					<span class="btn btn-mini btn-info">用户类型</span>
    					<input type="text" class="form-control" id="userType" name="userType" style="width:180px;"
    						value="${pd.userType}">
  					</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
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
		$(top.shutdown());
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