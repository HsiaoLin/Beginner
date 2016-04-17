<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<base href="<%=basePath%>">
<title>应用程序异常</title>
<link href="static/theme/coco/assets/libs/jqueryui/ui-lightness/jquery-ui-1.10.4.custom.min.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/fontello/css/fontello.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/animate-css/animate.min.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/nifty-modal/css/component.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/magnific-popup/magnific-popup.css" rel="stylesheet" /> 
<link href="static/theme/coco/assets/libs/ios7-switch/ios7-switch.css" rel="stylesheet" /> 
<link href="static/theme/coco/assets/libs/pace/pace.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/sortable/sortable-theme-bootstrap.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/bootstrap-datepicker/css/datepicker.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/jquery-icheck/skins/all.css" rel="stylesheet" />
<link href="static/theme/coco/assets/libs/prettify/github.css" rel="stylesheet" />
<link href="static/theme/coco/assets/css/style.css" rel="stylesheet" type="text/css" />
<link href="static/theme/coco/assets/css/style-responsive.css" rel="stylesheet" />
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
<link rel="shortcut icon" href="static/avatars/favicon.ico">
<link rel="apple-touch-icon" href="static/theme/coco/assets/img/apple-touch-icon.png" />
<link rel="apple-touch-icon" sizes="57x57" href="static/theme/coco/assets/img/apple-touch-icon-57x57.png" />
<link rel="apple-touch-icon" sizes="72x72" href="static/theme/coco/assets/img/apple-touch-icon-72x72.png" />
<link rel="apple-touch-icon" sizes="76x76" href="static/theme/coco/assets/img/apple-touch-icon-76x76.png" />
<link rel="apple-touch-icon" sizes="114x114" href="static/theme/coco/assets/img/apple-touch-icon-114x114.png" />
<link rel="apple-touch-icon" sizes="120x120" href="static/theme/coco/assets/img/apple-touch-icon-120x120.png" />
<link rel="apple-touch-icon" sizes="144x144" href="static/theme/coco/assets/img/apple-touch-icon-144x144.png" />
<link rel="apple-touch-icon" sizes="152x152" href="static/theme/coco/assets/img/apple-touch-icon-152x152.png" />
<script type="text/javascript">
	$(top.hangge());
	function showErr(){
		document.getElementById("err").style.display = "";
	}
</script>
</head>
    <body class="fixed-left full-content internal-error">
        <!-- Modal Start -->
        	<!-- Modal Task Progress -->	
	<div class="md-modal md-3d-flip-vertical" id="task-progress">
		<div class="md-content">
			<h3><strong>Task Progress</strong> Information</h3>
			<div>
				<p>CLEANING BUGS</p>
				<div class="progress progress-xs for-modal">
				  <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 80%">
					<span class="sr-only">80&#37; Complete</span>
				  </div>
				</div>
				<p>POSTING SOME STUFF</p>
				<div class="progress progress-xs for-modal">
				  <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 65%">
					<span class="sr-only">65&#37; Complete</span>
				  </div>
				</div>
				<p>BACKUP DATA FROM SERVER</p>
				<div class="progress progress-xs for-modal">
				  <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 95%">
					<span class="sr-only">95&#37; Complete</span>
				  </div>
				</div>
				<p>RE-DESIGNING WEB APPLICATION</p>
				<div class="progress progress-xs for-modal">
				  <div class="progress-bar progress-bar-primary" role="progressbar" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
					<span class="sr-only">100&#37; Complete</span>
				  </div>
				</div>
				<p class="text-center">
				<button class="btn btn-danger btn-sm md-close">Close</button>
				</p>
			</div>
		</div>
	</div>
		
	<!-- Modal Logout -->
	<div class="md-modal md-just-me" id="logout-modal">
		<div class="md-content">
			<h3><strong>Logout</strong> Confirmation</h3>
			<div>
				<p class="text-center">Are you sure want to logout from this awesome system?</p>
				<p class="text-center">
				<button class="btn btn-danger md-close">Nope!</button>
				<a href="login.html" class="btn btn-success md-close">Yeah, I'm sure</a>
				</p>
			</div>
		</div>
	</div>        <!-- Modal End -->	
	
	<!-- Begin page -->
	<div class="container">
		<div class="full-content-center animated flipInX">
			<h1>500</h1>
			<h3>抱歉！您访问的页面出现异常，请稍后重试或联系管理员！</h3><br>
			<p class="text-lightblue-3">重试请点击返回按钮，如需要了解详情请点击详情按钮！</p>
			<div class="row">
				<div class="icon-added input-group col-sm-8 col-sm-offset-2">
					<span class="input-group-btn">
				        <button class="btn btn-success searchbtn" type="button" onclick="javascript:showErr();">详 情</button>
						<a class="btn btn-primary btn-sm backbtn" href="javascript:history.back(-1)">返 回</a>
				      </span>
				</div>
			</div><br>
		</div>
	</div>
	<!-- End of page -->
		<!-- the overlay modal element -->
	<div style="text-align: left;display: none;" id="err">${ ex }</div>
	<!-- End of eoverlay modal -->
	<script>
		var resizefunc = [];
	</script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="static/theme/coco/assets/libs/jquery/jquery-1.11.1.min.js"></script>
	<script src="static/theme/coco/assets/libs/bootstrap/js/bootstrap.min.js"></script>
	<script src="static/theme/coco/assets/libs/jqueryui/jquery-ui-1.10.4.custom.min.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-ui-touch/jquery.ui.touch-punch.min.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-detectmobile/detect.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-animate-numbers/jquery.animateNumbers.js"></script>
	<script src="static/theme/coco/assets/libs/ios7-switch/ios7.switch.js"></script>
	<script src="static/theme/coco/assets/libs/fastclick/fastclick.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-blockui/jquery.blockUI.js"></script>
	<script src="static/theme/coco/assets/libs/bootstrap-bootbox/bootbox.min.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-slimscroll/jquery.slimscroll.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-sparkline/jquery-sparkline.js"></script>
	<script src="static/theme/coco/assets/libs/nifty-modal/js/classie.js"></script>
	<script src="static/theme/coco/assets/libs/nifty-modal/js/modalEffects.js"></script>
	<script src="static/theme/coco/assets/libs/sortable/sortable.min.js"></script>
	<script src="static/theme/coco/assets/libs/bootstrap-fileinput/bootstrap.file-input.js"></script>
	<script src="static/theme/coco/assets/libs/bootstrap-select/bootstrap-select.min.js"></script>
	<script src="static/theme/coco/assets/libs/bootstrap-select2/select2.min.js"></script>
	<script src="static/theme/coco/assets/libs/magnific-popup/jquery.magnific-popup.min.js"></script> 
	<script src="static/theme/coco/assets/libs/pace/pace.min.js"></script>
	<script src="static/theme/coco/assets/libs/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script src="static/theme/coco/assets/libs/jquery-icheck/icheck.min.js"></script>
	<script src="static/theme/coco/assets/libs/prettify/prettify.js"></script>
	<script src="static/theme/coco/assets/js/init.js"></script>
	</body>
</html>
