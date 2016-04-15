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
				<label for="exampleInputEmail1">姓名</label>
				<input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入姓名">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">帐号</label>
				<input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入帐号">
			</div>
			</div>
			<div class="col-md-6">
			<div class="form-group">
				<label for="exampleInputEmail1">邮箱</label>
				<input type="email" class="form-control" id="exampleInputEmail1" placeholder="请输入邮箱地址">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">手机</label>
				<input type="password" class="form-control" id="exampleInputPassword1" placeholder="请输入手机号码">
			</div>
			</div>
			<div class="col-md-12"  style="text-align: center;">
				<button onclick="search();" type="submit" class="btn btn-primary btn-lg">搜索<i class="fa fa-fw fa-search"></i></button>
				<button onclick="toExcel();" type="submit" class="btn btn-danger btn-lg">清空<i class="fa fa-fw fa-trash-o"></i></button>
			</div>
		</div>
	</div>
	<table id="table_report" class="datatable table table-striped table-bordered">
		<thead>
			<tr>
				<th class="center">
				<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
				</th>
				<th class="center">序号</th>
				<th class="center">用户类型</th>
				<th class="center">中文名</th>
				<th class="center">登陆账号</th>
				<th class="center">登陆密码</th>
				<th class="center">手机</th>
				<th class="center">固定电话</th>
				<th class="center">用户邮箱</th>
				<th class="center">用户状态</th>
				<th class="center">最后登陆时间</th>
				<th class="center">父用户ID</th>
				<th class="center">采购代理商ID</th>
				<th class="center">供应商ID</th>
				<th class="center">操作</th>
			</tr>
		</thead>
		<tbody>
		<!-- 开始循环 -->
		<c:choose>
			<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<tr>
						<td class='center' style="width: 30px;">
							<label><input type='checkbox' name='ids' value="${var.USER_ID}" /><span class="lbl"></span></label>
						</td>
						<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td>${var.USER_TYPE}</td>
								<td>${var.CHINESE_NAME}</td>
								<td>${var.USER_NAME}</td>
								<td>${var.USER_PASSWORD}</td>
								<td>${var.MOBILE_PHONE}</td>
								<td>${var.USER_PHONE}</td>
								<td>${var.USER_MAIL}</td>
								<td>${var.USER_STATUS}</td>
								<td>${var.LAST_LOGIN}</td>
								<td>${var.PARENT_ID}</td>
								<td>${var.BUYING_AGENT_ID}</td>
								<td>${var.SUPPLIER_ID}</td>
						<td style="width: 30px;" class="center">
							<div class='hidden-phone visible-desktop btn-group'>
								<div class="inline position-relative">
								<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
									<li><button title="编辑" onclick="edit('${var.USER_ID}');" class="btn btn-round btn-social-icon" data-rel="tooltip" data-placement="left"><i class="icon-edit"></i></button></li>
									<li><button title="删除" onclick="del('${var.USER_ID}');" class="btn btn-round btn-social-icon" data-rel="tooltip" data-placement="left"><i class="icon-trash"></i></button></li>
								</ul>
								</div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="100" class="center" >没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<div class="page-header position-relative">
	<table style="width:100%;">
		<tr>
			<td style="vertical-align:top;">
				<a class="btn btn-primary btn-lg" onclick="add();">新增</a>
				<a class="btn btn-danger btn-lg" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class="fa fa-fw fa-trash-o"></i></a>
			</td>
			<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
		</tr>
	</table>
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
