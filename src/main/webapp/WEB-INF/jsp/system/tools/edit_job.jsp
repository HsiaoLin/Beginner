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
		<%@ include file="../../system/admin/top.jsp"%> 
		<meta charset="utf-8" />
		<title>定时任务</title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		
		<script type="text/javascript">
		$(top.hangge());
		function save(){
			$.ajax({
             cache: false,
             type: "POST",
             url:$("#Form").attr("action"),
             data:$("#Form").serialize(),
             async: false,
             dataType:'text',
             error: function(request) {
             	showAlert("提示","出错啦!",'',null);
             },
             success: function(data) {
           		showAlert("提示","保存成功!",'',null);
             }
         });
			fixProgress();Dialog.getInstance('0').cancelButton.onclick.apply(Dialog.getInstance('0').cancelButton,[]);
		}
		</script>
	</head>
<body>
<div>
	<form action="<%=basePath%>job/${msg }" name="Form" id="Form" method="post">
		<table style="width:100%;" id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="margin-top:0px;">
					 <input type="text" name="cron" id="cron" rows="20" cols="50" style="width:97.5%;" placeholder="请选输入表达式" title="" value="${pd.cron}" />
				</td>
			</tr>
			<tr>
				<td style="margin-top:0px;">
					 <input type="text" name="clazz" id="clazz" rows="20" cols="50" style="width:97.5%;" placeholder="请选输入执行任务类：包名+类名的全称" title="" value="${pd.clazz}" />
				</td>
			</tr>
			<tr>
				<td style="margin-top:0px;">
					 <input type="text" name="jobName" id="jobName" rows="20" cols="50" style="width:97.5%;" placeholder="请选输入任务名称" title="" value="${pd.jobName}" />
				</td>
			</tr>
			<tr>
				<td style="margin-top:0px;">
					 <input type="text" name="jobGroupName" id="jobGroupName" rows="20" cols="50" style="width:97.5%;" placeholder="请选输入任务组名称" title="" value="${pd.jobGroupName}" />
				</td>
			</tr>
			<tr>
				<td style="margin-top:0px;">
					 <input type="text" name="triggerName" id="triggerName" rows="20" cols="50" style="width:97.5%;" placeholder="请选输入触发器名称" title="" value="${pd.triggerName}" />
				</td>
			</tr>
			<tr>
				<td style="margin-top:0px;">
					 <input type="text" name="triggerGroupName" id="triggerGroupName" rows="20" cols="50" style="width:97.5%;" placeholder="请选输入触发器组名称" title="" value="${pd.triggerGroupName}" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;">
					<a class="btn btn-primary" onclick="save();">保存</a>
					<a class="btn btn-danger" onclick="fixProgress();Dialog.getInstance('0').cancelButton.onclick.apply(Dialog.getInstance('0').cancelButton,[]);">取消</a>
				</td>
			</tr>
		</table>
	</form>
		
	<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
	<!-- 引入 -->
	<script src="static/js/bootstrap.min.js"></script>
	<script src="static/js/ace-elements.min.js"></script>
	<script src="static/js/ace.min.js"></script>
	<script src="static/js/chosen.jquery.min.js"></script>
	<script src="static/js/modal-dialog.js"></script>
	<script src="static/js/bootstrap-datepicker.min.js"></script>
	<script src="static/js/bootbox.min.js"></script>
	<script src="static/js/jquery.tips.js"></script>
	<script type="text/javascript" src="static/js/jquery.bootstrap.teninedialog.v3.js"></script>
</div>
</body>
</html>