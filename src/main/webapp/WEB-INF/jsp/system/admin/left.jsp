<%
	String pathl = request.getContextPath();
	String basePathl = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+pathl+"/";
%>
<!-- 本页面涉及的js函数，都在head.jsp页面中     -->
<div id="sidebar">
	<div id="sidebar-shortcuts">
		<ul class="nav nav-list">
			<li class="active" id="loginindex">
				<a href="login/index">
					<i class="icon-home"></i>
					<span>后台首页</span>
				</a>
			</li>
			<li id="lmmenuMENU_ID">
				<a style="cursor:pointer;" class="dropdown-toggle">
					<i class="icon-desktop"></i>
					<span>系统管理</span>
					<b class="arrow icon-angle-down"></b>
				</a>
				<ul class="submenu">
					<li id="zsubMENU_ID">
						<a style="cursor:pointer;" target="mainFrame" onclick="window.open('<%=basePathl%>static/UI_new');">
							<i class="icon-double-angle-right"></i>
							<span>UI实例</span>
						</a>
						<a style="cursor:pointer;" target="mainFrame" onclick="zidian();">
							<i class="icon-double-angle-right"></i>
							<span>数据字典</span>
						</a>
						<a style="cursor:pointer;" target="mainFrame" onclick="menu();">
							<i class="icon-double-angle-right"></i>
							<span>菜单管理</span>
						</a>
						<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('zsubMENU_ID2','lmmenuMENU_ID2','Druid Monitor','<%=basePathl %>druid/index.html');hangge();">
							<i class="icon-double-angle-right"></i>
							<span>系统监控</span>
						</a>
						<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('zsubMENU_ID','lmmenuMENU_ID','用户信息','system/user/list')">
							<i class="icon-double-angle-right"></i>
							<span>用户信息</span>
						</a>
						<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('zsubMENU_ID1','lmmenuMENU_ID1','地图展示','<%=basePathl %>static/map/map.html')">
							<i class="icon-double-angle-right"></i>
							<span>地图展示</span>
						</a>
					</li>
				</ul>
			</li>
			<c:forEach items="${menuList}" var="menu">
				<c:if test="${menu.hasMenu}">
					<li id="lm${menu.MENU_ID }">
						<a style="cursor:pointer;" class="dropdown-toggle" >
							<i class="${menu.MENU_ICON == null ? 'icon-desktop' : menu.MENU_ICON}"></i>
							<span>${menu.MENU_NAME }</span>
							<b class="arrow icon-angle-down"></b>
						</a>
						<ul class="submenu">
							<c:forEach items="${menu.subMenu}" var="sub">
								<c:if test="${sub.hasMenu}">
									<c:choose>
										<c:when test="${not empty sub.MENU_URL}">
											<li id="z${sub.MENU_ID }">
												<a style="cursor:pointer;" target="mainFrame" onclick="siMenu('z${sub.MENU_ID }','lm${menu.MENU_ID }','${sub.MENU_NAME }','${sub.MENU_URL }')">
													<i class="icon-double-angle-right"></i>${sub.MENU_NAME }
												</a>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<a href="javascript:void(0);">
													<i class="icon-double-angle-right"></i>${sub.MENU_NAME }
												</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</ul>
					</li>
				</c:if>
			</c:forEach>
		</ul>
		<div id="sidebar-collapse"><i class="icon-double-angle-left"></i></div>
	</div>
</div>