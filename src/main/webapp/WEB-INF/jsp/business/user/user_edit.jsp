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
		if($("#USER_NAME").val()==""){
			$("#USER_NAME").tips({
				side:3,
				msg:'请输入用户名',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_NAME").focus();
			return false;
		}
		if($("#USER_PASSWORD").val()==""){
			$("#USER_PASSWORD").tips({
				side:3,
				msg:'请输入用户密码',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_PASSWORD").focus();
			return false;
		}
		if($("#USER_PHONE").val()==""){
			$("#USER_PHONE").tips({
				side:3,
				msg:'请输入联系电话',
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
		if($("#USER_STATUS").val()==""){
			$("#USER_STATUS").tips({
				side:3,
				msg:'请输入用户状态',
				bg:'#AE81FF',
				time:2
			});
			$("#USER_STATUS").focus();
			return false;
		}
		if($("#LAST_LOGIN").val()==""){
			$("#LAST_LOGIN").tips({
				side:3,
				msg:'请输入最后登陆时间',
				bg:'#AE81FF',
				time:2
			});
			$("#LAST_LOGIN").focus();
			return false;
		}
		if($("#FIRST_NAME").val()==""){
			$("#FIRST_NAME").tips({
				side:3,
				msg:'请输入姓名',
				bg:'#AE81FF',
				time:2
			});
			$("#FIRST_NAME").focus();
			return false;
		}
		if($("#LAST_NAME").val()==""){
			$("#LAST_NAME").tips({
				side:3,
				msg:'请输入姓名',
				bg:'#AE81FF',
				time:2
			});
			$("#LAST_NAME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="user/user/${msg }" name="Form" id="Form" method="post">
		<input type="hidden" name="USER_ID" id="USER_ID" value="${pd.USER_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户类型:</td>
				<td><input type="text" name="USER_TYPE" id="USER_TYPE" value="${pd.USER_TYPE}" maxlength="32" placeholder="这里输入用户类型" title="用户类型"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户名:</td>
				<td><input type="text" name="USER_NAME" id="USER_NAME" value="${pd.USER_NAME}" maxlength="32" placeholder="这里输入用户名" title="用户名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户密码:</td>
				<td><input type="text" name="USER_PASSWORD" id="USER_PASSWORD" value="${pd.USER_PASSWORD}" maxlength="32" placeholder="这里输入用户密码" title="用户密码"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系电话:</td>
				<td><input type="text" name="USER_PHONE" id="USER_PHONE" value="${pd.USER_PHONE}" maxlength="32" placeholder="这里输入联系电话" title="联系电话"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户邮箱:</td>
				<td><input type="text" name="USER_MAIL" id="USER_MAIL" value="${pd.USER_MAIL}" maxlength="32" placeholder="这里输入用户邮箱" title="用户邮箱"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">用户状态:</td>
				<td><input type="text" name="USER_STATUS" id="USER_STATUS" value="${pd.USER_STATUS}" maxlength="32" placeholder="这里输入用户状态" title="用户状态"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">最后登陆时间:</td>
				<td><input class="span10 date-picker" name="LAST_LOGIN" id="LAST_LOGIN" value="${pd.LAST_LOGIN}" type="text" data-date-format="yyyy-mm-dd" readonly="readonly" placeholder="最后登陆时间" title="最后登陆时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">姓名:</td>
				<td><input type="text" name="FIRST_NAME" id="FIRST_NAME" value="${pd.FIRST_NAME}" maxlength="32" placeholder="这里输入姓名" title="姓名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">姓名:</td>
				<td><input type="text" name="LAST_NAME" id="LAST_NAME" value="${pd.LAST_NAME}" maxlength="32" placeholder="这里输入姓名" title="姓名"/></td>
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