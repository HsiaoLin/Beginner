<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="top.jsp"%>
<script type="text/javascript">
$(top.shutdown());
</script>
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
<li><a href="javascript:void(0);">首页</a></li>
<li class="active">任务监控</li>
</ol>
<div class="panel panel-plain">
<div class="panel-heading">
<h2 class="panel-title">任务监控</h2>
</div>
</div>
</div>
<div class="container-fluid container-padded">
<div class="row">
<div class="col-md-12">
<div class="panel panel-plain">
<div class="panel-body">
<table id="table_report" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<!-- th class="center">序号</th-->
			<th class="center">任务组名称</th>
			<th class="center">定时任务名称</th>
			<!-- <th class="center">触发器组名称</th>
			<th class="center">触发器名称</th> -->
			<th class="center">时间表达式</th>
			<th class="center">上次运行时间</th>
			<th class="center">下次运行时间</th>
			<th class="center">任务状态</th>
			<!-- <th class="center">已经运行时间</th> -->
			<!-- <th class="center">持续运行时间</th> -->
			<th class="center">开始时间</th>
			<th class="center">结束时间</th>
			<th class="center">任务类名</th>
			<!-- <th class="center">方法名称</th> -->
			<!-- <th class="center">jobObject</th> -->
			<!-- <th class="center">运行次数</th> -->
			<th class="center">操作</th>
		</tr>
	</thead>
	<tbody>
		<!-- 开始循环 -->
		<c:choose>
			<c:when test="${not empty jobInfos && jobInfos.size()>0}">
				<c:forEach items="${jobInfos}" var="var" varStatus="vs">
					<tr>
						<td class='center' style="width: auto;">${var.jobGroup}</td>
						<td class='center' style="width: auto;">${var.jobName}</td>
						<%-- <td class='center' style="width: auto;">${var.triggerGroupName}</td>
						<td class='center' style="width: auto;">${var.triggerName}</td> --%>
						<td class='center' style="width: auto;">${var.cronExpr}</td>
						<td class='center' style="width: auto;"><fmt:formatDate value="${var.previousFireTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class='center' style="width: auto;"><fmt:formatDate value="${var.nextFireTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class='center' style="width: auto;">
							<c:if test="${var.jobStatus == 'NONE'}">
								<span class="label">未知</span>
							</c:if>
							<c:if test="${var.jobStatus == 'NORMAL'}">
								<span class="label label-success arrowed">正常运行</span>
							</c:if>
							<c:if test="${var.jobStatus == 'PAUSED'}">
								<span class="label label-warning">暂停状态</span>
							</c:if>
							<c:if test="${var.jobStatus == 'COMPLETE'}">
								<span class="label label-important arrowed-in">完成状态</span>
							</c:if>
							<c:if test="${var.jobStatus == 'ERROR'}">
								<span class="label label-info arrowed-in-right arrowed">错误状态</span>
							</c:if>
							<c:if test="${var.jobStatus == 'BLOCKED'}">
								<span class="label label-inverse">锁定状态</span>
							</c:if>
						</td>
						<%-- <td class='center' style="width: auto;">${var.runTimes}</td> --%>
						<%-- <td class='center' style="width: auto;">${var.duration}</td> --%>
						<td class='center' style="width: auto;"><fmt:formatDate value="${var.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class='center' style="width: auto;"><fmt:formatDate value="${var.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td class='center' style="width: auto;">${var.jobClass}</td>
						<%-- <td class='center' style="width: auto;">${var.jobMethod}</td> --%>
						<%-- <td class='center' style="width: auto;">${var.jobObject}</td> --%>
						<%-- <td class='center' style="width: auto;">${var.count}</td> --%>
						<td class='center' style="width: auto;">
							<a class="btn btn-minier btn-info btn-xs" onclick="triggerJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>运行</a>
							<a class="btn btn-minier btn-purple btn-xs" onclick="resumeJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>恢复</a>
							<a class="btn btn-minier btn-success btn-xs" onclick="edit('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>编辑</a>
							<a class="btn btn-minier btn-warning btn-xs" onclick="pauseJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>暂停</a>
							<a class="btn btn-minier btn-danger btn-xs" onclick="deleteJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>删除</a>
						</td><!-- cron,jobName,jobGroupName,triggerName,triggerGroupName -->
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="100" class="center">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
<div class="widget-main">
	<div class="step-content row-fluid position-relative">
	</table>
		<tr>
			<td style="text-align: center;">
				<a class="btn btn-success" onclick="add();"><i class="icon-edit"></i>新增</a>
				<!-- <a class="btn btn-danger" onclick="del();"><i class="icon-trash"></i>删除</a> -->
			</td>
		</tr>
	</table>
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
</div>
</div>
</body>
</html>
