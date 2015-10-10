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
		<title>菜单</title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<!--提示框-->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/js/modal-dialog.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.bootstrap.teninedialog.v3.js"></script>
</head>

<script type="text/javascript">
	$(function(){
		var opera = $("#opera").val();
		if ("edit"==opera){
			$("#sa").text("修改");
		}
	});
	//保存
	function save(){
		if($("#type").val()==""){
			$("#type").tips({side:3,msg:'请输入字典类型名称',bg:'#AE81FF', time:2});
			$("#type").focus();
			return;
		}
		if($("#iden").val()==""){
			$("#iden").tips({side:1,msg:'请输入字典标示',bg:'#AE81FF',time:2});
			$("#iden").focus();
			return;
		}
		if($("#code").val()==""){
			$("#code").tips({side:1,msg:'请输入字典编码',bg:'#AE81FF',time:2});
			$("#code").focus();
			return;
		}
		var opera = $("#opera").val();
		if ("add"==opera){
			$.ajax({
				url:'<%=basePath%>dictionaries/addPar.do',
				type:'post',
				data:$('#Form').serialize(),
				dataType:'text',
				cache:false,
				async:false,
				success:function(ret){
					if ("succ"==ret){
						showAlert("提示","添加成功",'',function(){cancel();});
					}else{
						showAlert("提示","添加失败",'',function(){cancel();});
					}
				}
			});
		}else{
			$.ajax({
				url:'<%=basePath%>dictionaries/updateDic.do',
				type:'post',
				data:$('#Form').serialize(),
				dataType:'text',
				cache:false,
				async:false,
				success:function(ret){
					if ("succ"==ret){
						showAlert("提示","修改成功",'',function(){cancel();});
					}else{
						showAlert("提示","修改失败",'',function(){cancel();});
					}
				}
			});
		}
		function cancel(){top.Dialog.close();}
	}
</script>


<body>
	<form  action="" name="Form" id="Form" method="post" >
		<input id="parentId" type="hidden" name='parent_id' value="<c:if test="${not empty pd}">${pd.parentId}</c:if>">
		<input id="opera" type="hidden" name='opera' value="<c:if test="${not empty pd}">${pd.opera}</c:if>">
		<input id="code_id" type="hidden" name='code_id' value="<c:if test="${not empty pd}">${pd.code_id}</c:if>">
		<table class="table table-borderd">
			<tr>
				<td><input type="text" name="code_description" id="type" placeholder="这里输入业务字典类型名称" value="<c:if test="${not empty pd}">${pd.code_description}</c:if>" title="类型"/></td>
			</tr>
			<tr>
				<td><input type="text" name="code_name" id="iden" placeholder="这里输入类型标识" value="<c:if test="${not empty pd}">${pd.code_name}</c:if>" title="标示"/></td>
			</tr>
			<tr>
				<td><input type="text" name="code_code" id="code" placeholder="这里输入字典编码" value="<c:if test="${not empty pd}">${pd.code_code}</c:if>" title="编码"/></td>
			</tr>
			<tr id='bot'>
				<td style="text-align: center; padding-top: 10px;">
					<a class="btn btn-mini btn-primary" id="sa" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
	</form>
</body>
</html>