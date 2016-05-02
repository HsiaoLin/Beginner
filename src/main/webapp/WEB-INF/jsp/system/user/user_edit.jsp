<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="static/assets/css/lib/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="static/assets/css/lib/font-awesome/font-awesome.min.css">
<link rel="stylesheet" href="static/assets/css/lib/ionicons/ionicons.min.css">
<link rel="stylesheet" href="static/assets/css/lib/daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" href="static/assets/css/lib/jqvmap/jqvmap.css">
<link rel="stylesheet" href="static/assets/css/lib/tabdrop/tabdrop.css" />
<link rel="stylesheet" href="static/assets/css/styles.css" id="theme-css" />


<script type="text/javascript" src="static/assets/js/lib/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="static/assets/js/lib/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="static/assets/js/lib/slimscroll/jquery.slimscroll.min.js"></script>
<script type="text/javascript" src="static/assets/js/lib/momentjs/moment.min.js"></script>
<script type="text/javascript" src="static/assets/js/lib/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="static/assets/js/lib/tabdrop/bootstrap-tabdrop.js"></script>
<script type="text/javascript" src="static/assets/js/scripts.min.js"></script>
<script type="text/javascript" src="static/js/lib/layer/layer.js"></script>
<script type="text/javascript">
//保存
function save(){
	if($("#USER_TYPE").val()==""){
		layer.tips("请输入用户类型", "#USER_TYPE");
		$("#USER_TYPE").focus();
		return false;
	}
	if($("#CHINESE_NAME").val()==""){
		layer.tips("请输入中文名", "#CHINESE_NAME");
		$("#CHINESE_NAME").focus();
		return false;
	}
	if($("#USER_NAME").val()==""){
		layer.tips("请输入登陆账号", "#USER_NAME");
		$("#USER_NAME").focus();
		return false;
	}
	if($("#USER_PASSWORD").val()==""){
		layer.tips("请输入登陆密码", "#USER_PASSWORD");
		$("#USER_PASSWORD").focus();
		return false;
	}
	if($("#MOBILE_PHONE").val()==""){
		layer.tips("请输入手机号码", "#MOBILE_PHONE");
		$("#MOBILE_PHONE").focus();
		return false;
	}
	if($("#USER_PHONE").val()==""){
		layer.tips("请输入固定电话", "#USER_PHONE");
		$("#USER_PHONE").focus();
		return false;
	}
	if($("#USER_MAIL").val()==""){
		layer.tips("请输入用户邮箱", "#USER_MAIL");
		$("#USER_MAIL").focus();
		return false;
	}
	$.ajax({
		type: "POST",
		url: $("#Form").attr("action"),
		data: $("#Form").serialize(),
		dataType:'text',
		//beforeSend: validateData,
		cache: false,
		success: function(data){
			if(data=="success"){
				top.layer.closeAll();
			} else {
				top.layer.msg("保存失败！");
			}
		}
	});
}
</script>
	</head>
<body>
<div class="container" id="content-container">
<div class="content-wrapper">
<div class="row">
<div class="side-nav-content">
<div class="main-content-wrapper">
<div class="main-content">
<div class="container-fluid container-padded">
<div class="row">
<div class="col-md-12">
<div class="panel panel-default">
<div class="panel-body">
	<form action="system/user/${msg }" name="Form" id="Form" method="post" class="form-horizontal">
		<input type="hidden" name="USER_ID" id="USER_ID" value="${pd.USER_ID}"/>
		<div class="form-group">
		<label class="col-md-3 control-label">Default</label>
		<div class="col-md-9">
		<input type="text" class="form-control">
		</div>
		</div>
		<div class="form-group">
		<label class="col-md-3 control-label">Help text</label>
		<div class="col-md-9">
		<input type="text" class="form-control">
		<span class="help-block">A block of help text that breaks onto a new line and may extend beyond one line.</span>
		</div>
		</div>
		<div class="form-group">
		<label class="col-md-3 control-label">姓名：</label>
		<div class="col-md-9">
		<input type="text" name="CHINESE_NAME" id="CHINESE_NAME" value="${pd.CHINESE_NAME}" maxlength="32" placeholder="这里输入中文名" data-original-title="Tooltip goes here" title="中文名"  class="form-control tooltips" data-trigger="hover" data-toggle="tooltip"/>
		</div>
		</div>
		<div class="form-group">
		<label class="col-md-3 control-label">登陆密码：</label>
		<div class="col-md-9">
			<input type="password" name="USER_PASSWORD" id="USER_PASSWORD" value="${pd.USER_PASSWORD}" maxlength="32" placeholder="这里输入登陆密码" title="登陆密码" class="form-control" />
		</div>
		</div>
		<button type="submit" class="btn btn-success">Submit</button>
		<table>
			<tr>
				<td>用户类型:</td>
				<td><input type="text" name="USER_TYPE" id="USER_TYPE" value="${pd.USER_TYPE}" maxlength="32" placeholder="这里输入用户类型" title="用户类型"/></td>
			</tr>
			<tr>
				<td>登陆账号:</td>
				<td><input type="text" name="USER_NAME" id="USER_NAME" value="${pd.USER_NAME}" maxlength="32" placeholder="这里输入登陆账号" title="登陆账号"/></td>
			</tr>
			<tr>
				<td>手机:</td>
				<td><input type="text" name="MOBILE_PHONE" id="MOBILE_PHONE" value="${pd.MOBILE_PHONE}" maxlength="32" placeholder="这里输入手机" title="手机"/></td>
			</tr>
			<tr>
				<td>固定电话:</td>
				<td><input type="text" name="USER_PHONE" id="USER_PHONE" value="${pd.USER_PHONE}" maxlength="32" placeholder="这里输入固定电话" title="固定电话"/></td>
			</tr>
			<tr>
				<td>用户邮箱:</td>
				<td><input type="text" name="USER_MAIL" id="USER_MAIL" value="${pd.USER_MAIL}" maxlength="32" placeholder="这里输入用户邮箱" title="用户邮箱"/></td>
			</tr>
		</table>
	</form>
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
	<script type="text/javascript">
	$(top.shutdown());
	</script>
</body>
</html>