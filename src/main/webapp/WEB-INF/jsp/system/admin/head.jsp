﻿<header>
<nav class="navbar navbar-default navbar-transparent">
<div class="container-fluid" id="nav-container">
<div class="navbar-header">
<button class="navbar-toggle navbar-toggle-settings" data-target="#top-navbar" data-toggle="collapse" type="button">
<span class="sr-only">导航</span>
<i class="ion ion-ios7-gear-outline"></i>
</button>
<a class="navbar-brand logo" href="#">
<img src="static/img/logo_white.fw.png" alt="系统图标" width="175">
</a>
<div class="navbar-side-menu-toggle">
<button class="toggle-btn" type="button">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
</div>
</div>
<div class="collapse navbar-collapse" id="top-navbar">
<ul class="nav navbar-nav navbar-right">
<li><a href="page_home.html">首页</a></li>
<li><a href="app_support.html" target="_blank"><i class="fa fa-support"></i> <span class="notification-title">Support</span></a></li>
<li class="dropdown">
<a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-bell"></i> <span class="notification-title">Notifications</span><span class="badge badge-danger notification-badge">6</span></a>
<div class="dropdown-menu notification-menu">
<div class="panel panel-plain m-bot0">
<div class="list-group">
<a href="page_notifications.html" class="list-group-item">
<i class="fa fa-ban text-danger"></i> Nikita Williams rejected an Action Step from you
</a>
<a href="page_notifications.html" class="list-group-item">
<i class="fa fa-check-circle text-success"></i> You accepted an Action Step from Scott Marshall
</a>
</div>
<div class="panel-footer p-0">
<a href="page_notifications.html" class="btn btn-link btn-block m-bot0">View all</a>
</div>
</div>
</div>
</li>
<li class="dropdown">
<a class="dropdown-toggle" data-toggle="dropdown" href="#"><img src="static/theme/proctor/images/image2.jpg" alt="Pic" class="user-settings-pic"> Regina Hanson <i class="fa fa-angle-down"></i></a>
<ul class="dropdown-menu">
<li><a href="app_tour.html">初心之旅</a></li>
<li><a href="app_profile.html">个人资料</a></li>
<li><a href="app_settings.html">系统设置</a></li>
<li><a href="app_tour.html">代码生成</a></li>
<li class="divider"></li>
<li><a href="app_login.html">退出</a></li>
</ul>
</li>
</ul>
</div>
</div>
</nav>
</header>
<%-- <div class="navbar navbar-inverse">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="brand">
				<small>
					<i class="icon-heart-empty"></i>
					${pd.SYSNAME}
				</small>
			</a>
			<ul class="nav ace-nav pull-right">
				<li class="grey"><a href="javascript:alert('预留功能,待开发');" class="dropdown-toggle" data-toggle="dropdown"> 
					<i class="icon-tasks"></i> <span class="badge">1</span> </a>
					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-closer">
						<li class="nav-header"><i class="icon-ok"></i> 4 任务完成</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<div class="clearfix">
									<span class="pull-left">软件更新</span> <span class="pull-right">65%</span>
								</div>
								<div class="progress progress-mini">
									<div class="bar" style="width:65%"></div>
								</div>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<div class="clearfix">
									<span class="pull-left">软件更新</span> <span class="pull-right">35%</span>
								</div>
								<div class="progress progress-mini progress-danger">
									<div class="bar" style="width:35%"></div>
								</div>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<div class="clearfix">
									<span class="pull-left">运行测试</span> <span class="pull-right">15%</span>
								</div>
								<div class="progress progress-mini progress-warning">
									<div class="bar" style="width:15%"></div>
								</div>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<div class="clearfix">
									<span class="pull-left">Bug 修复</span> <span class="pull-right">90%</span>
								</div>
								<div
									class="progress progress-mini progress-success progress-striped active">
									<div class="bar" style="width:90%"></div>
								</div>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								查看任务明细 <i class="icon-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>
				<li class="green">
					<a href="javascript:alert('预留功能,待开发');" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-envelope-alt icon-animated-vertical icon-only"></i>
						<span class="badge badge-success">1</span>
					</a>
					<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-closer">
						<li class="nav-header"><i class="icon-envelope"></i> 5 条信件</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<img alt="Alex's Avatar" class="msg-photo" src="static/avatars/avatar.png" />
								<span class="msg-body">
									<span class="msg-title">
										<span class="blue">张三:</span>
										你好，我们在哪里吃饭? ...
									</span>
									<span class="msg-time">
										<i class="icon-time"></i>
										<span>1个月以前</span>
									</span>
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<img alt="Susan's Avatar" class="msg-photo" src="static/avatars/avatar3.png" />
								<span class="msg-body">
									<span class="msg-title">
										<span class="blue">李四:</span>
										你在哪上班? ...
									</span>
									<span class="msg-time">
										<i class="icon-time"></i>
										<span>20分钟前</span>
									</span>
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								<img alt="Bob's Avatar" class="msg-photo" src="static/avatars/avatar4.png" />
								<span class="msg-body">
									<span class="msg-title">
									<span class="blue">王五:</span>
										你好，我对你很感兴趣 ...
									</span>
									<span class="msg-time">
										<i class="icon-time"></i>
										<span>下午 3:15 </span>
									</span>
								</span>
							</a>
						</li>
						<li>
							<a href="javascript:alert('预留功能,待开发');">
								查看所有信件<i class="icon-arrow-right"></i>
							</a>
						</li>
					</ul>
				</li>
				<li class="purple" onclick="creatw();">
					<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-bell-alt icon-animated-bell icon-only"></i>
					</a>
				</li>
				<li class="light-blue user-profile">
					<a class="user-menu dropdown-toggle" href="javascript:;" data-toggle="dropdown">
						<img alt="User's Avatar" src="static/avatars/user.jpg" class="nav-user-photo" />
						<span id="user_info"></span>
						<i class="icon-caret-down"></i>
					</a>
					<ul id="user_menu" class="pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-closer">
						<li>
							<a onclick="editUserH();" style="cursor:pointer;">
								<i class="icon-user"></i>修改资料
							</a>
						</li>
						<li id="systemset">
							<a onclick="editSys();" style="cursor:pointer;">
								<i class="icon-cog"></i>系统设置
							</a>
						</li>
						<li id="productCode">
							<a onclick="productCode();" style="cursor:pointer;">
								<i class="icon-cogs"></i>代码生成
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="logout">
								<i class="icon-off"></i>退出
							</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</div> --%>
<script type="text/javascript" src="static/js/myjs/head.js"></script>
