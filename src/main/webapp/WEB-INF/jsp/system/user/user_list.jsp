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
	<form action="system/user/list" method="post" name="Form" id="Form" class="form-horizontal">
	<div class="panel panel-default">
		<div class="panel-heading">
		<h3 class="panel-title">搜索条件</h3>
		</div>
		<div class="panel-body">
			<div class="col-md-3">
			<div class="form-group" style="margin-left: 0px;">
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">姓名：</button>
					</span>
					<input type="text" class="form-control" id="name" placeholder="请输入姓名"> 
				</div>
			</div>
			</div>
			<div class="col-md-3">
			<div class="form-group" style="margin-left: 0px;">
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">帐号：</button>
					</span>
					<input type="text" class="form-control" id="password" placeholder="请输入帐号">
				</div>
			</div>
			</div>
			<div class="col-md-3">
			<div class="form-group" style="margin-left: 0px;">
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">邮箱：</button>
					</span>
					<input type="email" class="form-control" id="email" placeholder="请输入邮箱地址">
				</div>
			</div>
			</div>
			<div class="col-md-3">
			<div class="form-group" style="margin-left: 0px;">
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">状态：</button>
					</span>
					<select name="status" id="status" class="form-control">
						<option value="">请选择用户状态</option>
						<option value="0">待激活</option>
						<option value="1">正常</option>
						<option value="2">已注册</option>
						<option value="3">挂起</option>
						<option value="4">弃用</option>
					</select>
				</div>
			</div>
			</div>
			<div class="col-md-12"  style="text-align: center;">
				<button onclick="search();" type="submit" class="btn btn-success" data-placement="top" data-toggle="tooltip" data-original-title="根据条件搜索">搜索<i class="fa fa-fw fa-search"></i></button>
				<button onclick="reset();" type="submit" class="btn btn-default" data-placement="top" data-toggle="tooltip" data-original-title="清空搜索条件">清空<i class="fa fa-fw fa-trash-o"></i></button>
			</div>
		</div>
	</div>
	<div class="dataTables_wrapper form-inline"></div>
	<table cellpadding="0" cellspacing="0" id="table_report" class="datatable table table-striped table-bordered dataTable">
		<thead>
			<tr>
				<th>
				<input type="checkbox" id="zcheckbox" />
				</th>
				<th>序号</th>
				<th>用户类型</th>
				<th>姓名</th>
				<th>帐号</th>
				<th>密码</th>
				<th>手机</th>
				<th>固定电话</th>
				<th>用户邮箱</th>
				<th>用户状态</th>
				<th>最后登陆时间</th>
				<th>父用户ID</th>
				<th>采购代理商ID</th>
				<th>供应商ID</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<!-- 开始循环 -->
		<c:choose>
			<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="var" varStatus="vs">
					<tr>
						<td style="width: 30px;">
							<input type='checkbox' name='ids' value="${var.USER_ID}" />
						</td>
						<td style="width: 50px;">${vs.index+1}</td>
						<td>
						<c:if test="${var.USER_TYPE == 0}">平台</c:if>
						<c:if test="${var.USER_TYPE == 1}">代理商</c:if>
						<c:if test="${var.USER_TYPE == 2}">供应商</c:if>
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
							<a onclick="edit('${var.USER_ID}');" class="btn btn-info btn-xs" data-placement="top" data-toggle="tooltip" data-original-title="编辑"><i class="fa fa-fw fa-edit"></i></button>
							<a onclick="del('${var.USER_ID}');" class="btn btn-danger btn-xs" data-placement="top" data-toggle="tooltip" data-original-title="删除"><i class="fa fa-fw fa-trash-o"></i></button>
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
			<div style="float: left;">
				<a class="btn btn-success" onclick="add();" data-placement="top" data-toggle="tooltip" data-original-title="新增">新增<i class="fa fa-fw fa-plus"></i></a>
				<a class="btn btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" data-placement="top" data-toggle="tooltip" data-original-title="批量删除">删除<i class="fa fa-fw fa-trash-o"></i></a>
			</div>
			<div style="float: right;">
				${page.pageStr}
			</div>
		</div>
	</div>
	</div>
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
//检索
function search(){
	top.loading();
	$("#Form").submit();
}
//重置
function reset(){
	$("#Form :input").val("");
}
//新增
function add(){
	top.loading();
	top.layer.open({
		type: 2,
		title: '新增',
		shadeClose: true,
		shade: 0.3,
		area: ['40%', '70%'],
		content: '<%=basePath%>system/user/goAdd',
		btn: ['保存', '取消'],
		yes: function(index, layero){//按钮【保存】的回调
			var iframe = layero.find('iframe');
			var iframeWin = window.parent[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			iframeWin.save();
		},cancel: function(index){//按钮【取消】的回调
			top.layer.close(index);
		},end:function(){
			if('${page.currentPage}' == '0'){
				top.loading();
				setTimeout("self.location=self.location",100);
			}else{
				nextPage('${page.currentPage}');
			}
		}
	});
}
//删除
function del(Id){
	layer.confirm('您确定要删除吗？', {icon: 3, title:'提示'}, function(index){
		top.loading();
		var url = "<%=basePath%>system/user/delete?USER_ID="+Id+"&tm="+new Date().getTime();
		$.get(url,function(data){
			if(data==="success")
				nextPage('${page.currentPage}');
		});
		layer.close(index);
	});
}
//修改
function edit(Id){
	top.loading();
	top.layer.open({
		type: 2,
		title: '修改',
		shadeClose: true,
		shade: 0.3,
		area: ['40%', '70%'],
		content: '<%=basePath%>system/user/goEdit?USER_ID='+Id,
		btn: ['保存', '取消'],
		yes: function(index, layero){//按钮【保存】的回调
			var iframe = layero.find('iframe');
			var iframeWin = window.parent[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			iframeWin.save();
		},cancel: function(index){//按钮【取消】的回调
			top.layer.close(index);
		},end:function(){
			if('${page.currentPage}' == '0'){
				top.loading();
				setTimeout("self.location=self.location",100);
			}else{
				nextPage('${page.currentPage}');
			}
		}
	});
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
					top.loading();
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
