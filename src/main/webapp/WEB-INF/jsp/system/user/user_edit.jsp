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
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<script type="text/javascript">
	//保存
	function save(){
			if($("#USER_TYPE").val()==""){
			$("#USER_TYPE").tips({
				side:3,
				msg:'请输入用户类型',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_TYPE").focus();
			return false;
		}
		if($("#CHINESE_NAME").val()==""){
			$("#CHINESE_NAME").tips({
				side:3,
				msg:'请输入中文名',
				bg:'#AE81FF',
				time:2
			});
			$("#CHINESE_NAME").focus();
			return false;
		}
		if($("#USER_NAME").val()==""){
			$("#USER_NAME").tips({
				side:3,
				msg:'请输入登陆账号',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_NAME").focus();
			return false;
		}
		if($("#USER_PASSWORD").val()==""){
			$("#USER_PASSWORD").tips({
				side:3,
				msg:'请输入登陆密码',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_PASSWORD").focus();
			return false;
		}
		if($("#MOBILE_PHONE").val()==""){
			$("#MOBILE_PHONE").tips({
				side:3,
				msg:'请输入手机',
				bg:'#AE81FF',
				time:2
			});
			$("#MOBILE_PHONE").focus();
			return false;
		}
		if($("#USER_PHONE").val()==""){
			$("#USER_PHONE").tips({
				side:3,
				msg:'请输入固定电话',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_PHONE").focus();
			return false;
		}
		if($("#USER_MAIL").val()==""){
			$("#USER_MAIL").tips({
				side:3,
				msg:'请输入用户邮箱',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_MAIL").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="system/user/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="USER_ID" id="USER_ID" value="${pd.USER_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户类型:</td>
				<td><input type="text" name="USER_TYPE" id="USER_TYPE" value="${pd.USER_TYPE}" maxlength="32" placeholder="这里输入用户类型" title="用户类型"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">中文名:</td>
				<td><input type="text" name="CHINESE_NAME" id="CHINESE_NAME" value="${pd.CHINESE_NAME}" maxlength="32" placeholder="这里输入中文名" title="中文名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">登陆账号:</td>
				<td><input type="text" name="USER_NAME" id="USER_NAME" value="${pd.USER_NAME}" maxlength="32" placeholder="这里输入登陆账号" title="登陆账号"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">登陆密码:</td>
				<td><input type="text" name="USER_PASSWORD" id="USER_PASSWORD" value="${pd.USER_PASSWORD}" maxlength="32" placeholder="这里输入登陆密码" title="登陆密码"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">手机:</td>
				<td><input type="text" name="MOBILE_PHONE" id="MOBILE_PHONE" value="${pd.MOBILE_PHONE}" maxlength="32" placeholder="这里输入手机" title="手机"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">固定电话:</td>
				<td><input type="text" name="USER_PHONE" id="USER_PHONE" value="${pd.USER_PHONE}" maxlength="32" placeholder="这里输入固定电话" title="固定电话"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户邮箱:</td>
				<td><input type="text" name="USER_MAIL" id="USER_MAIL" value="${pd.USER_MAIL}" maxlength="32" placeholder="这里输入用户邮箱" title="用户邮箱"/></td>
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