<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<%@ include file="../../system/admin/top.jsp"%> 
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<link rel="stylesheet" href="static/css/datepicker.css" />
<script src="static/js/jquery-1.9.1.min.js"></script>
<script src="static/js/highcharts/highcharts.js"></script>
<script src="static/js/highcharts/modules/data.js"></script>
<script src="static/js/highcharts/modules/exporting.js"></script>
<title></title>
</head>
<body>
<div id="zhongxin">
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<!-- 检索  -->
				<form action="job/goList" method="post"
					name="Form" id="Form">
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h4>定时任务搜索</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main  form-horizontal">
								<table class="table table-bordered">
									<tr>
										<td class="td1">任务状态：</td>
										<td class="td2"><select class="chzn-select" name="jobStatus"
											id="jobStatus" data-placeholder="任务状态"
											style="vertical-align:top;width: 120px;">
												<option value="">请选择</option>
												<option
													<c:if test="${not empty pd and pd.jobStatus=='NONE'}">selected</c:if>
													value="NONE">未知</option>
												<option
													<c:if test="${not empty pd and pd.jobStatus=='NORMAL'}">selected</c:if>
													value="NORMAL">正常运行</option>
												<option
													<c:if test="${not empty pd and pd.jobStatus=='PAUSED'}">selected</c:if>
													value="PAUSED">暂停状态</option>
												<option
													<c:if test="${not empty pd and pd.jobStatus=='COMPLETE'}">selected</c:if>
													value="COMPLETE">完成状态</option>
												<option
													<c:if test="${not empty pd and pd.jobStatus=='ERROR'}">selected</c:if>
													value="ERROR">错误状态</option>
												<option
													<c:if test="${not empty pd and pd.jobStatus=='BLOCKED'}">selected</c:if>
													value="BLOCKED">锁定状态</option>
											</select>
										</td>
										<td class="td1">任务名称：</td>
										<td class="td2">
											<input id="jobName" name="jobName" data-placeholder="这里输入任务名称" value="${pd.jobName}" />
										</td><%-- 
										<td class="td1">起止时间：</td>
										<td class="td2"><input class="span10 date-picker"
											name="startDate" id="from_date" value="${pd.startDate}"
											type="text" data-date-format="yyyy-mm-dd" readonly="readonly"
											style="width:88px;" placeholder="开始日期" />-- <input
											class="span10 date-picker" name="endDate" id="ret_date"
											value="${pd.endDate}" type="text"
											data-date-format="yyyy-mm-dd" readonly="readonly"
											style="width:88px;" placeholder="结束日期" /></td> --%>
									</tr>
									<tr>
										<td style="vertical-align:middle;text-align: center;"
											colspan="100">
											<button class="btn btn-success" onclick="search();">
												<i id="nav-search-icon" class="icon-search"></i>搜索
											</button> <a class="btn btn-danger" onclick="resetForm();" title="清空">
												<i id="nav-search-icon" class="icon-trash"></i>清空 </a></td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</form>
				<div class="widget-box transparent">
					<div class="widget-header widget-header-flat">
						<h4 class="lighter">
							<i class="icon-cog"></i> 定时任务管理监控
						</h4>
					</div>
					<!--/widget-body-->
				</div>
				<!--/widget-box-->
				<!-- 检索  -->
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
											<a class="btn btn-minier btn-info" onclick="triggerJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>运行</a>
											<a class="btn btn-minier btn-purple" onclick="resumeJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>恢复</a>
											<a class="btn btn-minier btn-success" onclick="edit('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>编辑</a>
											<a class="btn btn-minier btn-warning" onclick="pauseJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>暂停</a>
											<a class="btn btn-minier btn-danger" onclick="deleteJob('${var.jobName}','${var.jobGroup}');"><i class="icon-edit"></i>删除</a>
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
<div id="zhongxin1">

</div>
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
	<script type="text/javascript">
	var locat = (window.location+'').split('/'); 
	$(function() {
	$(top.hangge());
	//日期框
	$('.date-picker').datepicker();
	//下拉框
	$(".chzn-select").chosen(); 
	$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
	
	function curentTime() { 
		var now = new Date();
		var year = now.getFullYear();       //年
		var month = now.getMonth() + 1;     //月
		var day = now.getDate();            //日
		return year+"年"+month+"月"+day+"日　";
	}
	//检索
	function search(){
		top.jzts();
		$("#Form").submit();
	}
	//重置
	function resetForm(){
		$("#Form input[type='text']").val("");
	}
});
	//新增定时任务
	function add(){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="新增定时任务";
		 diag.URL = '<%=basePath%>job/goAdd';
		 diag.Width = 500;
		 diag.Height = 500;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//编辑
	function edit(jobName,jobGroupName){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="编辑定时任务";
		 diag.URL = '<%=basePath%>job/goEdit?jobName='+jobName+'&jobGroupName='+jobGroupName;
		 diag.Width = 500;
		 diag.Height = 500;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
			window.location.reload();
		 };
		 diag.show();
	}
	//删除
	function deleteJob(jobName,jobGroupName){
		top.jzts();
		$.ajax({
             cache: false,
             type: "POST",
             url:"<%=basePath%>job/deleteJob",
             data:{
             	jobName:jobName,
             	jobGroupName:jobGroupName
             },
             async: false,
             dataType:'text',
             error: function(request) {
             	showAlert("提示","删除任务出错啦!",'',null);
             },
             success: function(data) {
           		showAlert("提示","删除任务成功!",'',null);
       			window.location.reload();
             }
         });
	}
	//运行
	function triggerJob(jobName,jobGroupName){
		top.jzts();
		$.ajax({
             cache: false,
             type: "POST",
             url:"<%=basePath%>job/triggerJob",
             data:{
             	jobName:jobName,
             	jobGroupName:jobGroupName
             },
             async: false,
             dataType:'text',
             error: function(request) {
             	showAlert("提示","立即运行出错啦!",'',null);
             },
             success: function(data) {
           		showAlert("提示","立即运行成功!",'',null);
       			window.location.reload();
             }
         });
	}
	//恢复
	function resumeJob(jobName,jobGroupName){
		top.jzts();
		$.ajax({
             cache: false,
             type: "POST",
             url:"<%=basePath%>job/resumeJob",
             data:{
             	jobName:jobName,
             	jobGroupName:jobGroupName
             },
             async: false,
             dataType:'text',
             error: function(request) {
             	showAlert("提示","恢复运行出错啦!",'',null);
             },
             success: function(data) {
           		showAlert("提示","恢复运行成功!",'',null);
       			window.location.reload();
             }
         });
	}
	//暂停
	function pauseJob(jobName,jobGroupName){
		top.jzts();
		$.ajax({
             cache: false,
             type: "POST",
             url:"<%=basePath%>job/pauseJob",
             data:{
             	jobName:jobName,
             	jobGroupName:jobGroupName
             },
             async: false,
             dataType:'text',
             error: function(request) {
             	showAlert("提示","暂停运行出错啦!",'',null);
             },
             success: function(data) {
           		showAlert("提示","暂停运行成功!",'',null);
           		window.location.reload();
             }
         });
	}
	</script>
</body>
</html>