	$(function() {
			if (typeof ($.cookie('menusf')) == "undefined") {
				$("#sidebar").attr("class", "");
			} else {
				$("#menusf").attr("checked", true);
				$("#sidebar").attr("class", "menu-min");				
			}
		});
		function cmainFrame(){
			var hmain = document.getElementById("mainFrame");
			var bheight = document.documentElement.clientHeight;
			hmain .style.width = '100%';
			hmain .style.height = (bheight  - 51) + 'px';
			var bkbgjz = document.getElementById("bkbgjz");
			bkbgjz .style.height = (bheight  - 41) + 'px';
			
		}
		cmainFrame();
		window.onresize=function(){  
			cmainFrame();
		};
		jzts();