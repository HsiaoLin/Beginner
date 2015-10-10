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
<div class="container-fluid" id="main-container">
<div id="page-content" class="clearfix">
  <div class="row-fluid">
	<div class="row-fluid">
			<div style="width: 100%;text-align: center;">
				<a style="margin: 0 auto;width: 100px;" class="btn btn-large btn-info" title="" >帐户管理</a>
			</div>
			<!-- 检索  -->
			<form action="back/account/list.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input style="width:200px;" autocomplete="off" id="nav-search-input" type="text" name="user" value="" placeholder="这里输入要查询的用户" />
							<i id="nav-search-icon" class="icon-search"></i>
						</span>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>
			<!-- 检索  -->
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<!--  -->
						<!--th class="center">
						<!--label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						<!--/th-->
						<th class="center">选择</th>
						<th class="center">序号</th>
						<th class="center">用户名</th>
						<th class="center">用户状态</th>
						<th class="center">上次登录时间</th>
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
									<label><input type='checkbox' name='ids' value="${var.userId}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class='center' style="width: 30px;">${var.userName}</td>
								<td class='center' style="width: 30px;">
									<c:if test="${var.userStatus=='1'}"><font style="color:green">审核通过</font></c:if>
    								<c:if test="${var.userStatus=='0'}"><font style="color:blue">待审核</font></c:if>
								</td>
								<td class='center' style="width: 30px;">${var.lastLogin}</td>
								<td style="width: 30px" class="center">
									<div class="btn-group" role="group" aria-label="..." class="center" style="text-align: center;">
										<button type="button" title="编辑" style="width: 30px;" class="btn btn-mini btn-success" onclick="edit('${var.userId}');"><i class="icon-edit"></i></button>
									  	<button type="button" title="查看" style="width: 30px;" class="btn btn-mini btn-primary" onclick="view('${var.userId}');"><i class="icon-eye-open"></i></button>
									  	<button id="delUser" type="button" title="删除" style="width: 30px;" class="btn btn-mini btn-danger" onclick="del('${var.userId}');"><i class="icon-trash"></i></button>
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
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					<a class="btn btn-small btn-info" onclick="commitUser();">审核</a>
					<a class="btn btn-small btn-info" id="checkAll">全选</a>
					<a class="btn btn-small btn-danger" onclick="delAll();" title="批量删除" ><i class='icon-trash'></i></a>
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
		</form>
	</div>
  </div>
</div>
</div>
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript" src="static/js/modal-dialog.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.bootstrap.teninedialog.v3.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		//审核通过用户
		$("#checkAll").toggle(
			function () {
		    	$("input[name='ids']").attr("checked","checked");
		  	},
		  	function () {
		    	$("input[name='ids']").removeAttr("checked");
		  	}
		);
		function commitUser(){
			var str = '';
			for(var i=0;i < document.getElementsByName('ids').length;i++){
				  if(document.getElementsByName('ids')[i].checked){
				  	if(str=='') {
				  		str += document.getElementsByName('ids')[i].value;
				  	}else{
				  	 	str += ',' + document.getElementsByName('ids')[i].value;
				  	 }
				  }
			}
			if(str==''){
				bootbox.alert("请选择要审核的用户！", function(){});
				return;
			}
			$.ajax({
				url:'<%=basePath%>back/account/commit.do',
				data:{userId:str},
				async:false,
				dataType:'text',
				type:'post',
				success:function(ret){
					if ("succ"==ret){
						bootbox.alert("审核成功！", function(){search();});
					}else{
						bootbox.alert("审核失败！", function(){search();});
					}
				}
			});
			return;
		}
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 //top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>back/account/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				search();
				diag.close();
			 };
			 diag.show();
		}
		//删除
		function del(Id){
		    bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					$.ajax({
						url:'<%=basePath%>back/account/delete.do?userId='+Id+'&tm='+new Date().getTime(),
						async:false,
						dataType:'text',
						type:'post',
						success:function(ret){
							top.hangge();
							if ("succ"==ret){
								bootbox.alert("删除成功！", function(){search();});
							}else{
								bootbox.alert("删除失败！", function(){search();});
							}
						}
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 //top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>back/account/goEdit.do?userId='+Id;
			 diag.Width = 450;
			 diag.Height = 400;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				search();
				diag.close();
			 };
			 diag.show();
		}
		//查看
		function view(Id){
			 //top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="查看";
			 diag.URL = '<%=basePath%>back/account/goView.do?userId='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				//search();
				diag.close();
			 };
			 diag.show();
		}
		</script>
		
		<script type="text/javascript">
		$(function() {
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			//日期框
			$('.date-picker').datepicker();
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
		});
		//批量操作
		function delAll(){
			showConfirm("提示","确定要删除吗？",'',function(){
				var str = '';
				for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
				}
				if(str==''){
					bootbox.dialog("您没有选择任何内容!", 
						[{"label" : "关闭","class" : "btn-small btn-success","callback": function() {}}]
					);
					$("#checkAll").tips({side:3,msg:'点这里全选', bg:'#AE81FF', time:8});
					return;
				}else{
					$.ajax({
						type: "POST",
						url: '<%=basePath%>back/account/delete.do?tm='+new Date().getTime(),
				    	data: {userId:str},
						dataType:'text',
						cache: false,
						async:false,
						success: function(ret){
							if ("succ"==ret){
								showAlert("提示","删除成功!",'',function(){search();});
							}else{
								showAlert("提示","删除成功!",'',function(){search();});
							}
						}
					});
				}
			},null,null);
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>account/excel.do';
		}
		</script>
		
	</body>
</html>

