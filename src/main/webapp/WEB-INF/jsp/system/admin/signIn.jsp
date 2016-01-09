<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<!DOCTYPE html>
<html lang="en" class="body-full-height">
    <head>
        <title>${pd.SYSNAME}</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="<%= basePath %>static/signin/favicon.ico" type="image/x-icon" />
        <link rel="bookmark" href="<%= basePath %>static/signin/favicon.ico" type="image/x-icon" />
        <link rel="shortcut icon" href="<%= basePath %>static/signin/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" type="text/css" id="theme" href="<%= basePath %>static/signin/css/theme-default.css"/>
        <script type="text/javascript" src="<%= basePath %>static/js/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="static/js/jquery.tips.js"></script>
        <script type="text/javascript" src="<%= basePath %>static/js/jquery.cookie.js"></script>
    </head>
    <body>

        <div class="login-container lightmode">

            <div class="login-box animated fadeInDown">
                <div class="login-logo" align="center"><h1>${pd.SYSNAME}</h1></div>
                <div class="login-body">
                    <form action="" class="form-horizontal" method="post" id="loginForm" name="loginForm">
                    <div class="form-group">
                    <div class="login-title col-md-12">用户名或邮箱地址</div>
                        <div class="col-md-12">
                            <input name="loginname" id="loginname" type="text" class="form-control" placeholder="请输入用户名或邮箱地址..."/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="login-title col-md-12">密码
                             <a style="float: right;" href="#" class="btn">忘记密码？</a>
                        </div>
                        <div class="col-md-12">
                            <input name="password" id="password" type="password" class="form-control" placeholder="请输入密码..."/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-12">
                            <a id="to-recover" class="btn btn-info btn-block" onclick="severCheck();">登陆</a>
                        </div>
                    </div>
                    <div class="login-or"><h2><i class="fa fa-hand-o-down"></i></h2></div>
                    <div class="form-group">
                        <div class="col-md-4">
                            <button class="btn btn-info btn-block btn-twitter" disabled="disabled"><span class="fa">邮箱登陆</span></button>
                        </div>
                        <div class="col-md-4">
                            <button class="btn btn-info btn-block btn-facebook" disabled="disabled"><span class="fa">快捷登陆</span></button>
                        </div>
                        <div class="col-md-4">
                            <button class="btn btn-info btn-block btn-google" disabled="disabled"><span class="fa">其他方式</span></button>
                        </div>
                    </div>
                    </form>
                </div>
                <div class="login-body" style="margin-top: 10px;">
                    <div align="center"><span>还没有账号？<a href="#">立即申请</a></span></div>
                </div>
                <div class="login-footer">
                    <div class="pull-left">
                        &copy; 2016 尹枭凌工作室
                    </div>
                    <div class="pull-right">
                        <a href="#">关于我们</a> |
                        <a href="#">隐私声明</a> |
                        <a href="#">联系我们</a>
                    </div>
                </div>
            </div>
        </div>
	<script type="text/javascript">
		$(document).ready(function(){
			changeCode();
			$("#codeImg").bind("click", changeCode);
		});
		$(document).keyup(function(event) {
			if (event.keyCode == 13) {
				$("#to-recover").trigger("click");
			}
		});
		//服务器校验
		function severCheck(){
			if(check()){
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = loginname+",99,"+password+",99,"+$("#code").val();
				$.ajax({
					type: "POST",
					url: '<%=basePath%>login/login_validation',
					data: {KEYDATA:code,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							saveCookie();
							window.location.href="<%=basePath%>login/index";
						}else if("usererror" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "用户名或密码有误",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}else if("codeerror" == data.result){
							$("#code").tips({
								side : 1,
								msg : "验证码输入有误",
								bg : '#FF5080',
								time : 15
							});
							$("#code").focus();
						}else if("accountNotAduit" == data.result){
							$("#loginname").tips({
								side : 1,
								msg : "帐号未通过审核！",
								bg : '#FF5080',
								time : 3
							});
							$("#code").focus();
						}else{
							$("#loginname").tips({
								side : 1,
								msg : "缺少参数",
								bg : '#FF5080',
								time : 15
							});
							$("#loginname").focus();
						}
					}
				});
			}
		}

		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
/* 			if ($("#code").val() == "") {

				$("#code").tips({
					side : 1,
					msg : '验证码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#code").focus();
				return false;
			}
 */
			$("#loginbox").tips({
				side : 1,
				msg : '正在登录 , 请稍后 ...',
				bg : '#68B500',
				time : 10
			});

			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}
		
		jQuery(function() {
			var loginname = $.cookie('loginname');
			var password = $.cookie('password');
			if (typeof(loginname) != "undefined"
					&& typeof(password) != "undefined") {
				$("#loginname").val(loginname);
				$("#password").val(password);
				$("#saveid").attr("checked", true);
				$("#code").focus();
			}
		});
	</script>
	<script>
		//TOCMAT重启之后 点击左侧列表跳转登录首页 
		if (window != top) {
			top.location.href = location.href;
		}
	</script>
    </body>
</html>
