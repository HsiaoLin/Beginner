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

<div class="span8">
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>乘客信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main  form-horizontal">
		 <c:forEach items="${passengers}" var="var" varStatus="vs">
		 	<p>英文姓名:${var.getName()}</p>
		 	<p>证件号码:${var.getCardNum()}</p>
		 	<p>证件发行国:${var.getCardIssuePlace()}</p>
		 	<p>证件有效期:${var.getCardExpired()}</p>
		 	<p>乘客类型:${var.getAgeType()}</p>		 			
		 </c:forEach>																							
		 </div>
		</div>
	</div>
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>联系信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main">
		 	<p>姓名:${pdOrder.contact_name}</p>
		 	<p>手机号码:${pdOrder.contact_phone}</p>
		 	<p>邮箱地址:${pdOrder.contact_mail}</p>
		 	<p>详细地址:${pdOrder.contact_address}</p>
		 	<p>邮编:${pdOrder.contact_postcode}</p>
		 </div>
		</div>
	</div>	
	
	<form action="flight/search/doPay.do" name="Form" id="Form" method="post">
	<input type="hidden" name="orderId" id="orderId" value="${pdOrder.order_id }"/>
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>支付信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main">
			<p>PNR：${pdOrder.trip_pnr}</p>
			<p>金额：</p>																									
		 </div>
		</div>
	</div>	
	<button class="btn btn-warning btn-large">立即支付</button>	
	</form>
 </div>
 
 <div class="span4">
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>航程信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main">
			<div class="row-fluid">
				<c:if test="${not empty routing.fromSegments}">
					<p>去程:</p>
					<c:forEach items="${routing.fromSegments}" var="varFrom">
						<p>航司:${varFrom.carrier},航班号:${varFrom.flightNumber},出发机场:${varFrom.depAirport},到达机场:${varFrom.arrAirport},仓位:${varFrom.cabin},机型:${varFrom.aircraftCode}</p>
					</c:forEach>
				</c:if>				
      
				<c:if test="${not empty routing.retSegments}">
					<p>返程:</p>
					<c:forEach items="${routing.retSegments}" var="varRet">
						<p>航司:${varRet.carrier},航班号:${varRet.flightNumber},出发机场:${varRet.depAirport},到达机场:${varRet.arrAirport},仓位:${varRet.cabin},机型:${varRet.aircraftCode}</p>
					</c:forEach>
				</c:if>          
			</div>
			
		 </div>
		</div>
	</div>
	
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>费用信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main">
			<p>成人单价:${routing.adultPrice}</p>
			<p>成人税费:${routing.adultTax}</p>
			<p>儿童单价:${routing.childPrice}</p>
			<p>儿童税费:${routing.childTax}</p>
			<p></p>
			<p>费用总计:</p>
		 </div>
		</div>
	</div>	
 </div>
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
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
		
		$(top.hangge());
		
		//订票
		function order(){
			//检索
			top.loading();
			$("#Form").submit();			
		}
		function order2(){
			var p_name = '';
			var ageType = 0;
			var birthday = '';
			var gender = '';
			var cardNum = '';
			var cardType = '';
			var cardIssuePlace = '';
			var cardExpired = '';
			var nationality = '';
			
			var c_name = '';
			var address = '';
			var postcode = '';
			var email = '';
			var mobile = '';
			
			var url = "<%=basePath%>flight/search/goOrder.do";
			var postData;
			
			postData = {"p_name":p_name,"ageType":ageType,"birthday":birthday,"gender":gender,"cardNum":cardNum,"cardType":cardType,"cardIssuePlace":cardIssuePlace,"cardExpired":cardExpired,"nationality":nationality,
						"c_name":c_name,"address":address,"postcode":postcode,"email":email,"mobile":mobile};
			
			$.post(url,postData,function(data){
//				top.Dialog.close();
			});			
		}
		
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

