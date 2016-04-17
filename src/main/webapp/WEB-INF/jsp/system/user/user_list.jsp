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
<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
<%@ include file="../../system/admin/top.jsp"%> 
</head>
<body>
<div class="container-fluid" id="content-container">
<div class="content-wrapper">
<div class="row">
<div class="side-nav-content">
<div class="main-content-wrapper">
<div class="container-fluid container-padded dash-controls">
<div class="row">
<div class="col-md-12">
<ol class="breadcrumb">
<li><a href="javascript:void(0);">系统管理</a></li>
<li><a href="javascript:void(0);">组织机构</a></li>
<li class="active">帐号管理</li>
</ol>
</div>
</div>
</div>
<div class="main-content">
<section>
<div class="container-fluid container-padded">
<div class="row">
<div class="col-md-12 page-title">
<h3>帐号管理</h3>
<hr>
</div>
</div>
</div>
<div class="container-fluid container-padded">
<div class="row">
<div class="col-md-12">
<div class="panel panel-plain">
<div class="panel-body">
<div class="table-responsive">
<table cellpadding="0" cellspacing="0" border="0" class="datatable table table-striped table-bordered">
	<form action="system/user/list" method="post" name="Form" id="Form">
	<div class="panel panel-default">
		<div class="panel-heading">
		<h3 class="panel-title">检索条件</h3>
		</div>
		<div class="panel-body">
			<div class="col-md-6">
			<div class="form-group">
				<!-- <label>姓名</label> -->
				<input type="email" class="form-control" id="namee" placeholder="请输入姓名"> 
			</div>
			<div class="form-group">
				<!-- <label>帐号</label> -->
				<input type="password" class="form-control" id="password1" placeholder="请输入帐号">
			</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<!-- <label>邮箱</label> -->
				<input type="email" class="form-control" id="email" placeholder="请输入邮箱地址">
			</div>
			<div class="form-group">
				<!-- <label>状态</label> -->
				<input type="password" class="form-control" id="phone" placeholder="请选择状态">
			</div>
			</div>
			<div class="col-md-12"  style="text-align: center;">
				<button onclick="search();" type="submit" class="btn btn-success" data-placement="top" data-toggle="tooltip" data-original-title="根据条件搜索">搜索<i class="fa fa-fw fa-search"></i></button>
				<button onclick="toExcel();" type="submit" class="btn btn-default" data-placement="top" data-toggle="tooltip" data-original-title="清空搜索条件">清空<i class="fa fa-fw fa-trash-o"></i></button>
			</div>
		</div>
	</div>
	<table id="table_report" class="datatable table table-striped table-bordered" style="text-align: center;">
		<thead>
			<tr>
				<th>
				<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
				</th>
				<th style="text-align: center;">序号</th>
				<th style="text-align: center;">用户类型</th>
				<th style="text-align: center;">姓名</th>
				<th style="text-align: center;">帐号</th>
				<th style="text-align: center;">密码</th>
				<th style="text-align: center;">手机</th>
				<th style="text-align: center;">固定电话</th>
				<th style="text-align: center;">用户邮箱</th>
				<th style="text-align: center;">用户状态</th>
				<th style="text-align: center;">最后登陆时间</th>
				<th style="text-align: center;">父用户ID</th>
				<th style="text-align: center;">采购代理商ID</th>
				<th style="text-align: center;">供应商ID</th>
				<th style="text-align: center;">操作</th>
			</tr>
		</thead>
		<tbody>
		<!-- 开始循环 -->
		<c:choose>
			<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<tr>
						<td style="width: 30px;">
							<label><input type='checkbox' name='ids' value="${var.USER_ID}" /><span class="lbl"></span></label>
						</td>
						<td style="width: 30px;">${vs.index+1}</td>
						<td>
						<c:if test="${var.USER_TYPE == 0}"><span class="label label-primary">平台</span></c:if>
						<c:if test="${var.USER_TYPE == 1}"><span class="label label-success">代理商</span></c:if>
						<c:if test="${var.USER_TYPE == 2}"><span class="label label-info">供应商</span></c:if>
						</td>
						<td>${var.CHINESE_NAME}</td>
						<td>${var.USER_NAME}</td>
						<td>${var.USER_PASSWORD}</td>
						<td>${var.MOBILE_PHONE}</td>
						<td>${var.USER_PHONE}</td>
						<td>${var.USER_MAIL}</td>
						<td>
						<c:if test="${var.USER_STATUS == 0}">
							<span class="badge badge-primary">待激活</span>
						</c:if>
						<c:if test="${var.USER_STATUS == 1}">
							<span class="badge badge-success">正常</span>
						</c:if>
						<c:if test="${var.USER_STATUS == 2}">
							<span class="badge badge-info">已注册</span>
						</c:if>
						<c:if test="${var.USER_STATUS == 3}">
							<span class="badge badge-warning">挂起</span>
						</c:if>
						<c:if test="${var.USER_STATUS == 4}">
							<span class="badge badge-danger">弃用</span>
						</c:if>
						</td>
						<td>${var.LAST_LOGIN}</td>
						<td>${var.PARENT_ID}</td>
						<td>${var.BUYING_AGENT_ID}</td>
						<td>${var.SUPPLIER_ID}</td>
						<td>
							<button onclick="edit('${var.USER_ID}');" class="btn btn-info btn-xs" data-placement="top" data-toggle="tooltip" data-original-title="编辑"><i class="fa fa-fw fa-edit"></i></button>
							<button onclick="del('${var.USER_ID}');" class="btn btn-danger btn-xs" data-placement="top" data-toggle="tooltip" data-original-title="删除"><i class="fa fa-fw fa-trash-o"></i></button>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="100">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<div class="row">
	<div class="col-sm-12">
	<div class="page-header position-relative">
	<table style="width:100%;">
		<tr>
			<td style="vertical-align:top;">
				<a class="btn btn-primary" onclick="add();" data-placement="top" data-toggle="tooltip" data-original-title="新增">新增<i class="fa fa-fw fa-plus"></i></a>
				<a class="btn btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" data-placement="top" data-toggle="tooltip" data-original-title="批量删除">删除<i class="fa fa-fw fa-trash-o"></i></a>
			</td>
			<td style="vertical-align:top;">
				<div class="dataTables_paginate paging_bs_four_button" id="DataTables_Table_0_paginate">
				<div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">
					${page.pageStr}
				</div>
				</div>
			</td>
		</tr>
	</table>
	</div>
	</div>
	</div>
	</form>
</section>
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
//检索
function search(){
	top.jzts();
	$("#Form").submit();
}
//新增
function add(){
	top.jzts();
	var diag = new top.Dialog();
	diag.Drag=true;
	diag.Title ="新增";
	diag.URL = '<%=basePath%>system/user/goAdd';
	diag.Width = $(document).width()/3;
	diag.Height = $(document).height()/2;
	diag.CancelEvent = function(){ //关闭事件
		if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			if('${page.currentPage}' == '0'){
				top.jzts();
				setTimeout("self.location=self.location",100);
			}else{
				nextPage('${page.currentPage}');
			}
		}
		diag.close();
	};
	diag.show();
}
//删除
function del(Id){
	bootbox.confirm("确定要删除吗?", function(result) {
		if(result) {
			top.jzts();
			var url = "<%=basePath%>system/user/delete?USER_ID="+Id+"&tm="+new Date().getTime();
			$.get(url,function(data){
				nextPage('${page.currentPage}');
			});
		}
	});
}
//修改
function edit(Id){
	top.jzts();
	var diag = new top.Dialog();
	diag.Drag=true;
	diag.Title ="编辑";
	diag.URL = '<%=basePath%>system/user/goEdit?USER_ID='+Id;
	diag.Width = 450;
	diag.Height = 355;
	diag.CancelEvent = function(){ //关闭事件
		if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
			nextPage('${page.currentPage}');
		}
		diag.close();
	};
	diag.show();
}
//批量删除
function makeAll(msg){
	bootbox.confirm(msg, function(result) {
		if(result) {
			var str = '';
			for(var i=0;i < document.getElementsByName('ids').length;i++)
			{
				if(document.getElementsByName('ids')[i].checked){
					if(str=='') str += document.getElementsByName('ids')[i].value;
					else str += ',' + document.getElementsByName('ids')[i].value;
				}
			}
			if(str==''){
				bootbox.dialog("您没有选择任何内容!", 
					[{
						"label" : "关闭",
						"class" : "btn-small btn-success",
						"callback": function() {
							//Example.show("great success");
						}
					}]
				);
				$("#zcheckbox").tips({
					side:3,
					msg:'点这里全选',
					bg:'#AE81FF',
					time:8
				});
				return;
			}else{
				if(msg == '确定要删除选中的数据吗?'){
					top.jzts();
					$.ajax({
						type: "POST",
						url: '<%=basePath%>system/user/deleteAll?tm='+new Date().getTime(),
						data: {DATA_IDS:str},
						dataType:'json',
						//beforeSend: validateData,
						cache: false,
						success: function(data){
							$.each(data.list, function(i, list){
								nextPage('${page.currentPage}');
							});
						}
					});
				}
			}
		}
	});
}
//导出Excel
function toExcel(){
	window.location.href='<%=basePath%>system/user/excel';
}
</script>
</body>
</html>
