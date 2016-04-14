<script type="text/javascript" src="static/js/myjs/head.js"></script>
<header>
	<nav class="navbar navbar-default navbar-transparent">
		<div class="container-fluid" id="nav-container">
			<div class="navbar-header">
				<button class="navbar-toggle navbar-toggle-settings" data-target="#top-navbar" data-toggle="collapse" type="button">
					<span class="sr-only">导航</span>
					<i class="ion ion-ios7-gear-outline"></i>
				</button>
				<a class="navbar-brand logo" href="javascript:void(0);">
					<img src="static/img/logo.png" alt="系统图标" width="175">
				</a>
				<div class="navbar-side-menu-toggle">
					<button class="toggle-btn" type="button" id="toggleClazz">
						<span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button>
				</div>
			</div>
			<div class="collapse navbar-collapse" id="top-navbar">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="<%=basePath%>/login/index"><i class="glyphicon glyphicon-home"></i></a></li>
					<li class="dropdown">
						<a class="dropdown-toggle" href="javascript:void(0);">
							<i class="fa fa-bell"></i>
							<span class="notification-title">Notifications</span>
							<span class="badge badge-danger notification-badge">6</span>
						</a>
						<div class="dropdown-menu notification-menu">
							<div class="panel panel-plain m-bot0">
								<div class="list-group">
									<a href="javascript:void(0);" class="list-group-item">
										<i class="fa fa-ban text-danger"></i> 您拒收了Github的邮件.
									</a>
									<a href="javascript:void(0);" class="list-group-item">
										<i class="fa fa-check-circle text-success"></i> 您接收了Github的邮件.
									</a>
								</div>
								<div class="panel-footer p-0">
									<a href="javascript:void(0);" class="btn btn-link btn-block m-bot0">查看全部</a>
								</div>
							</div>
						</div>
					</li>
					<li class="dropdown">
						<a class="dropdown-toggle" href="javascript:void(0);">
							<img src="static/images/china.png" alt="头像" class="user-settings-pic">
							<span id="user_info"></span>
							<i class="fa fa-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="javascript:void(0);">初心之旅</a></li>
							<li><a href="javascript:editUserH();">个人资料</a></li>
							<li><a href="javascript:editSys();">系统设置</a></li>
							<li><a href="javascript:productCode();">代码生成</a></li>
							<li class="divider"></li>
							<li><a href="logout">退出</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</header>