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
	<form action="flight/search/goOrder.do" name="Form" id="Form" method="post">
	<input type="hidden" name="searchId" id="searchId" value="${pd.searchId }"/>
	<input type="hidden" name="routingId" id="routingId" value="${pd.routingId }"/>
	<input type="hidden" name="sessionId" id="sessionId" value="${sessionId }"/>
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>乘客信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main  form-horizontal">
			<div class="control-group">
			<label for="form-field-1" class="control-label">英文姓名:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_name" name="p_name" value="Marcus/Tom">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">证件号码:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_cardNum" name="p_cardNum" value="G756789">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">证件发行国:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_cardIssuePlace" name="p_cardIssuePlace" value="CN">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">证件有效期:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_cardExpired" name="p_cardExpired" value="20200101">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">乘客类型:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_ageType" name="p_ageType" value="0">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">性别:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_gender" name="p_gender" value="M">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">出生日期:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_birthday" name="p_birthday" value="19740225">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">国籍:</label>
			<div class="controls">
				<input type="text" placeholder="" id="p_nationality" name="p_nationality" value="CN">
			</div>
			</div>																											
		 </div>
		</div>
	</div>
	<div class="widget-box">
		<div class="widget-header widget-header-flat">
			<h4>联系信息</h4>
		</div>
		
		<div class="widget-body">
		 <div class="widget-main  form-horizontal">
			<div class="control-group">
			<label for="form-field-1" class="control-label">姓名:</label>
			<div class="controls">
				<input type="text" placeholder="" id="c_name" name="c_name" value="Marcus/Tom">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">手机号码:</label>
			<div class="controls">
				<input type="text" placeholder="" id="c_mobile" name="c_mobile" value="13800000000">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">邮箱地址:</label>
			<div class="controls">
				<input type="text" placeholder="" id="c_email" name="c_email" value="">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">详细地址:</label>
			<div class="controls">
				<input type="text" placeholder="" id="c_address" name="c_address" value="xxxx spring valley road">
			</div>
			</div>
			<div class="control-group">
			<label for="form-field-1" class="control-label">邮编:</label>
			<div class="controls">
				<input type="text" placeholder="" id="c_postcode" name="c_postcode" value="11111">
			</div>
			</div>
		 </div>
		</div>
	</div>	
	
	<button class="btn btn-warning btn-large">提交订单</button>	
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
				<p>座位数:${maxSeats}</p>
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
			top.jzts();
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
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>search/goAdd.do';
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
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>search/delete.do?id="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
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
			 top.jzts();
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
							top.jzts();
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

