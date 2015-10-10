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
<script type="text/javascript" src="static/js/modal-dialog.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="static/js/jquery.bootstrap.teninedialog.v3.js"></script>
<!-- 日期框 -->
</head>
<body>
	<div class="container-fluid" id="main-container">
		<div id="page-content" class="clearfix">
			<div class="row-fluid">
				<div class="widget-box">
					<div class="widget-header widget-header-flat">
						<h4 style="margin-left: 42%;">添加票号</h4>
					</div>
					<div class="widget-body">
						<div class="widget-main  form-horizontal">
							<table class="table table-bordered">
								<tr>
									<td style="width: 20%;">机票票号：</td>
									<td style="width: 80%;">
										<input type="text" id="ticketNo" name="ticketNo" required="required"></input>
										<font style="color: red">*</font>
									</td>
								</tr>
								<tr>
									<td style="width: 20%;">备注信息：</td>
									<td style="width: 80%;height: 100px;"><textarea style="width: 97%;resize: none;height: 100%" type="text" id="ticketRemark" name="ticketRemark"></textarea></td>
								</tr>
							</table>
							<div class="center">
								<button class="btn btn-small btn-primary" onclick="addTicket();">添加</button>
								<button class="btn btn-small btn-danger" onclick="cancel();">取消</button>
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
	function addTicket(){
		//校验票号有没有输入
		var tn = $("#ticketNo").val();
		if (tn == null){
			showAlert("提示","请输入票号!",'',function(){});
			return;
		}else{
			tn = tn.replace(/(^\s*)|(\s*$)/g,'');
			if (""==tn){
				showAlert("提示","请输入票号!",'',function(){});
				return;
			}
		}
		$.ajax({
			url:'<%=basePath%>back/order/addTicket.do',
			data:{
				ticketNo:tn,
				ticketRemark:$("#ticketRemark").val(),
				passName:'${pd.passName}',
				passCardNo:'${pd.passCardNo}',
				orderId:'${pd.orderId}'
			},
			dataType:'text',
			cache:false,
			async:false,
			success:function(ret){
				if ("succ" == ret){
					showAlert("提示","添加成功!",'',function(){cancel();});
				}else{
					showAlert("提示","添加失败!",'',function(){});
				}
			}
		});
	}
	function cancel(){
		top.Dialog.close();
	}
</script>

