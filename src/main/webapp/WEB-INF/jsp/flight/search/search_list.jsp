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
	
			<!-- 检索  -->
			<form action="flight/search/list.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="fromCity" value="HKG" placeholder="出发城市" />
						</span>
					</td>
					<td>
						<span class="input-icon">
							<input autocomplete="off" id="nav-search-input" type="text" name="toCity" value="BJS" placeholder="目的城市" />
						</span>
					</td>					
					<td><input class="span10 date-picker" name="fromDate" id="fromDate" value="${pd.fromDate}" type="text" data-date-format="yyyymmdd" readonly="readonly" style="width:88px;" placeholder="出发日期"/></td>
					<td><input class="span10 date-picker" name="retDate" id="retDate" value="${pd.retDate}" type="text" data-date-format="yyyymmdd" readonly="readonly" style="width:88px;" placeholder="回程日期"/></td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="查询"><i id="nav-search-icon" class="icon-search"></i></button></td>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">序号</th>
						<th class="center">成人单价</th>
						<th class="center">成人税费</th>
						<th class="center">儿童单价</th>
						<th class="center">儿童税费</th>
						<th class="center">报价类型</th>
						<th class="center">税费类型</th>
						<th class="center">去程航段</th>
						<th class="center">回程航段</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">

						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class='center' style="width: 30px;">${var.adultPrice}</td>
								<td class='center' style="width: 30px;">${var.adultTax}</td>
								<td class='center' style="width: 30px;">${var.childPrice}</td>
								<td class='center' style="width: 30px;">${var.childTax}</td>
								<td class='center' style="width: 30px;">${var.priceType}</td>
								<td class='center' style="width: 30px;">${var.adultTaxType}</td>
								<td class='center' style="width: 200px;">
								<c:if test="${not empty var.fromSegments}">
									<c:forEach items="${var.fromSegments}" var="varFrom">
										<p>航司:${varFrom.carrier},航班号:${varFrom.flightNumber},出发机场:${varFrom.depAirport},到达机场:${varFrom.arrAirport},仓位:${varFrom.cabin},机型:${varFrom.aircraftCode}</p>
									</c:forEach>
								</c:if>								
								</td>
								<td class='center' style="width: 200px;">
								<c:if test="${not empty var.retSegments}">
									<c:forEach items="${var.retSegments}" var="varRet">
										<p>航司:${varRet.carrier},航班号:${varRet.flightNumber},出发机场:${varRet.depAirport},到达机场:${varRet.arrAirport},仓位:${varRet.cabin},机型:${varRet.aircraftCode}</p>
									</c:forEach>
								</c:if>
								</td>								
								<td style="width: 30px;" class="center">
									<a href="flight/search/goBuy.do?searchId=${searchId}&routingId=${var.id}" class="btn btn-mini btn-purple"><i class="icon-pencil"></i>预定</a>
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
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
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
		<script type="text/javascript">
		
		$(top.shutdown());
		
		//检索
		function search(){
			top.loading();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.loading();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>search/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.loading();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
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
					top.loading();
					var url = "<%=basePath%>search/delete.do?id="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function edit(Id){
			 top.loading();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>search/goEdit.do?id='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
		}
		
		function buy(searchId, routingId){
			 top.loading();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="预定";
			 diag.URL = '<%=basePath%>flight/search/goBuy.do?searchId='+searchId+'&routingId='+routingId;
			 diag.Width = 860;
			 diag.Height = 500;
			 diag.CancelEvent = function(){ //关闭事件
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
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
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
								url: '<%=basePath%>search/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>search/excel.do';
		}
		</script>
		
	</body>
</html>

