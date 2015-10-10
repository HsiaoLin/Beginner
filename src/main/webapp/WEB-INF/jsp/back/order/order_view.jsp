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
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>
<meta name="description" content="overview & stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="static/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
<link rel="stylesheet" href="static/css/font-awesome.min.css" />
<!-- 下拉框 -->
<link rel="stylesheet" href="static/css/chosen.css" />
<link rel="stylesheet" href="static/css/ace.min.css" />
<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
<link rel="stylesheet" href="static/css/ace-skins.min.css" />
<link rel="stylesheet" href="static/css/datepicker.css" />
<!-- 日期框 -->
<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<!-- 引入 -->
<script type="text/javascript">
	window.jQuery|| document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");
</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>
<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
<!-- 下拉框 -->
<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script>
<!-- 日期框 -->
</head>
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<div class="span8">
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h4>订单信息</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main  form-horizontal">
								<div class="widget-box">
									<div class="widget-header widget-header-flat">
										<h6>基本信息</h4>
									</div>
									<div class="widget-main  form-horizontal">
										<p>出发地:${pd.from_city}</p>
										<p>目的地:${pd.to_city}</p>
										<p>出发时间:${pd.from_date}</p>
										<p>是否中转:<c:choose><c:when test="${pd.is_transit=='1'}">是</c:when><c:otherwise>否</c:otherwise></c:choose></p>
									</div>
								</div>
								<div class="widget-box">
									<div class="widget-header widget-header-flat">
										<h6>航段信息</h4>
									</div>
									<div class="widget-main  form-horizontal">
										<table id="table_report" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th class="center">航空公司</th>
													<th class="center">航班号</th>
													<th class="center">机型</th>
													<th class="center">到达时间</th>
													<th class="center">仓位代码</th>
												</tr>
											</thead>
											<tbody>
												<c:choose>
													<c:when test="${not empty segmList }">
														<c:forEach varStatus="vs" var="var" items="${segmList}">
															<tr>
																<td class='center' style="width: auto;">${var.carrier_code}</td>
																<td class='center' style="width: auto;">${var.flight_number }</td>
																<td class='center' style="width: auto;">${var.aircraft_code}</td>
																<td class='center' style="width: auto;">${var.arr_time}</td>
																<td class='center' style="width: auto;">暂无</td>
															</tr>
														</c:forEach>
													</c:when>
													<c:otherwise>
														<tr>
															<td colspan="5" style="text-align: center;">没有相关数据!</td>
														</tr>
													</c:otherwise>
												</c:choose>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h4>乘机人信息</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main  form-horizontal">
								<table id="table_report" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">乘客姓名</th>
											<th class="center">乘客类型</th>
											<th class="center">乘客国籍</th>
											<th class="center">出生日期</th>
											<th class="center">证件类型</th>
											<th class="center">证件号</th>
											<th class="center">证件发行国</th>
											<th class="center">证件有效期</th>
											<th class="center">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${not empty passList}">
												<c:forEach items="${passList}" var="var" varStatus="vs">
													<tr>
														<td class='center' style="width: auto;">${var.passenger_name}</td>
														<td class='center' style="width: auto;">${var.passenger_type }</td>
														<td class='center' style="width: auto;">${var.passenger_nationality}</td>
														<td class='center' style="width: auto;">${var.passenger_birthday}</td>
														<td class='center' style="width: auto;">${var.card_type}</td>
														<td class='center' style="width: auto;">${var.card_no}</td>
														<td class='center' style="width: auto;">${var.card_Issueplace}</td>
														<td class='center' style="width: auto;">${var.card_expired}</td>
														<td class='center' style="width: auto;">
														<c:choose>
															<c:when test="${var.hasTicket=='1'}">
																<a class="btn btn-mini btn-purple" href="javascript:void" onclick="viewTicket('${var.passenger_name}','${var.card_no}')">
																	<i class="icon-eye-open"></i>
																	查看机票
																</a>
															</c:when>
															<c:otherwise>
																<a class="btn btn-mini btn-danger" href="javascript:void" 
																	onclick="addTicket('${var.passenger_name}','${var.card_no}','${pd.order_id}')">
																	<i class="icon-pencil"></i>
																	添加票号
																</a>
															</c:otherwise>
														</c:choose>
														</td>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td colspan="8" style="text-align: center;">没有相关数据!</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h4>费用明细</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main  form-horizontal">
								<c:forEach items="${passengers}" var="var" varStatus="vs">
									<p>乘客类型:${var.getName()}</p>
									<p>票面价格:${var.getCardNum()}</p>
									<p>税金:${var.getCardIssuePlace()}</p>
									<p>结算单价:${var.getCardExpired()}</p>
									<p>乘机人数:${var.getAgeType()}</p>
									<p>快递费:${var.getAgeType()}</p>
									<p>合计金额 :${var.getAgeType()}</p>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="span4">
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h4>联系人信息</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<p>联系人姓名:${pd.contact_name}</p>
								<p>联系人电话:${pd.contact_phone}</p>
								<p>联系人邮箱:${pd.contact_mail}</p>
								<p>详细地址:${pd.contact_address}</p>
								<p>邮编:${pd.contact_postcode}</p>
							</div>
						</div>
					</div>
					<div class="widget-box">
						<div class="widget-header widget-header-flat">
							<h4>订单历史状态</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<p>时间:${pdOrder.contact_name}</p>
								<p>状态:${pdOrder.contact_phone}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript">
	//查看机票信息的窗口
	function viewTicket(passName,passCardNo){
		 var diag = new top.Dialog();
		 diag.Drag=true;
		 diag.Title ="机票信息";
		 diag.URL = '<%=basePath%>back/order/goViewTicket.do?passName='+passName+'&passCardNo='+passCardNo;
		 diag.Width = 600;
		 diag.Height = 355;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//给乘客挂票号的窗口
	function addTicket(passName,passCardNo,orderId){
		var diag = new top.Dialog();
		diag.Drag=true;
		diag.Title ="添加机票";
		diag.URL = '<%=basePath%>back/order/goViewTicketAdd.do?passName='+passName+'&passCardNo='+passCardNo+'&orderId='+orderId;
		diag.Width = 600;
		diag.Height = 355;
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
			location.reload();
		};
		diag.show();
	}
</script>

