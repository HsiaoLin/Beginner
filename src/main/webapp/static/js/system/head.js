var locat = (window.location+'').split('/'); 
$(function(){if('main'== locat[3]){locat =  locat[0]+'//'+locat[2];}else{locat =  locat[0]+'//'+locat[2]+'/'+locat[3];};});


//菜单状态切换
var fmid = "loginindex";
var mid = "loginindex";
function siMenu(id,fid,MENU_NAME,MENU_URL){
	if(id != mid){
		$("#"+mid).removeClass();
		mid = id;
	}
	if(fid != fmid){
		$("#"+fmid).removeClass();
		fmid = fid;
	}
	$("#"+fid).attr("class","active open");
	$("#"+id).attr("class","active");
	top.mainFrame.tabAddHandler(id,MENU_NAME,MENU_URL);
	if(MENU_URL != "druid/index.html"){
		jzts();
	}
}

function gotoMenu(id,MENU_NAME,MENU_URL){
	top.mainFrame.src=MENU_URL;
}

$(function(){
	//换肤
	/*$("#skin-colorpicker").ace_colorpicker().on("change",function(){
		var b=$(this).find("option:selected").data("class");
		hf(b);
		var url = locat+'/head/setSKIN.do?SKIN='+b+'&tm='+new Date().getTime();
		$.get(url,function(data){});
	});*/
});

var USER_ID;

//用于即时通讯（ 当前登录用户）
var user = "Beginner";

$(function(){
	$.ajax({
		type: "POST",
		url: locat+'/head/getUname.do?tm='+new Date().getTime(),
    	data: encodeURI(""),
		dataType:'json',
		//beforeSend: validateData,
		cache: false,
		async:false,
		success: function(data){
			//debugger;
			 $.each(data.list, function(i, list){
				 //登陆者资料
				 $("#user_info").html('欢迎您， '+list.userName+'');
				 user = list.userName;
				 USER_ID = list.userId;//用户ID
				 hf(list.userSkin);//皮肤
			 });
		}
	});
});

//换肤
function hf(b){
	var a=$(document.body);
	a.attr("class",a.hasClass("navbar-fixed")?"navbar-fixed":"");
	if(b!="default"){
		a.addClass(b);
	}if(b=="skin-1"){
		$(".ace-nav > li.grey").addClass("dark");
	}else{
		$(".ace-nav > li.grey").removeClass("dark");
	}
	if(b=="skin-2"){
			$(".ace-nav > li").addClass("no-border margin-1");
			$(".ace-nav > li:not(:last-child)").addClass("white-pink")
			.find('> a > [class*="icon-"]').addClass("pink").end()
			.eq(0).find(".badge").addClass("badge-warning");
	}else{
			$(".ace-nav > li").removeClass("no-border").removeClass("margin-1");
			$(".ace-nav > li:not(:last-child)").removeClass("white-pink")
			.find('> a > [class*="icon-"]').removeClass("pink").end()
			.eq(0).find(".badge").removeClass("badge-warning");
	}
	if(b=="skin-3"){
		$(".ace-nav > li.grey").addClass("red").find(".badge").addClass("badge-yellow");
	}else{
		$(".ace-nav > li.grey").removeClass("red").find(".badge").removeClass("badge-yellow");
	}
}

//修改个人资料
function editUserH(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="个人资料";
	 diag.URL = locat+'/user/goEditU.do?USER_ID='+USER_ID+'&fx=head';
	 diag.Width = 225;
	 diag.Height = 389;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//系统设置
function editSys(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="系统设置";
	 diag.URL = locat+'/head/goSystem.do';
	 diag.Width = 600;
	 diag.Height = 596;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
}

//代码生成
function productCode(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="代码生成器";
	 diag.URL = locat+'/head/goProductCode.do';
	 diag.Width = 800;
	 diag.Height = 450;
	 diag.CancelEvent = function(){ //关闭事件
		hangge();
		diag.close();
	 };
	 diag.show();
}

//数据字典
function zidian(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="数据字典";
	 diag.URL = locat+'/dictionaries/list.do';
	 diag.Width = 799;
	 diag.Height = 460;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
	 
}

//菜单
function menu(){
	 jzts();
	 var diag = new top.Dialog();
	 diag.Drag=true;
	 diag.Title ="菜单编辑";
	 diag.URL = locat+'/menu.do';
	 diag.Width = 720;
	 diag.Height = 390;
	 diag.CancelEvent = function(){ //关闭事件
		diag.close();
	 };
	 diag.show();
	 
}

//切换菜单
function changeMenu(){
	window.location.href=locat+'/main/yes';
}

//关闭加载层
function shutdown(){
	top.layer.closeAll('loading');
}

//显示加载层
function loading(){
	loading(0);
}

//显示加载层(0,1,2共三种类型可选择)
function loading(type){
	if(type && (type == 0 || type == 1 || type == 2)){
		top.layer.load(type);
	}else{
		top.layer.load(0);
	}
}

//iFrame页面高度
window.onresize=function(){
	iFrameHeight();
}

//iFrame页面高度
function iFrameHeight() {
	var ifm= document.getElementById("mainFrame");
	var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;
	if(ifm && subWeb)
		ifm.height = subWeb.body.scrollHeight;
}

$(function(){
	loading();
	$("#toggleClazz").click(function(){
		var ifm= document.getElementById("mainFrame");
		var subWeb = document.frames ? document.frames["mainFrame"].document : ifm.contentDocument;
		if(ifm != null && subWeb != null)
			ifm.contentWindow.toggleClazzs();
	});

	$(".sub-menu-list>li").click(function(){
		$(".sub-menu-list>li").removeClass("active");
		$(this).addClass("active");
	});
});