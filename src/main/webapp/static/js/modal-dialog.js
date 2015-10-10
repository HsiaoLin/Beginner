function showAlert(title,content,url,callback){
	$.teninedialog({
		 title:title,
         content:content,//
         url:url,//不支持跨域，如果url不为空，就会加载url的内容，content的内容忽略
         showCloseButton:false,
         otherButtons:["确定"],
         otherButtonStyles:['btn-primary'],
         bootstrapModalOption:{keyboard: true},
         dialogHidden:function(){
        	 if (callback){
        		 callback();
        	 }
         },                    
         clickButton:function(sender,modal,index){
             $(this).closeDialog(modal);
         }
	})
}
function showConfirm(title,content,url,ok,cancel,callback){
	$.teninedialog({
		title:title,
		content:content,//
		url:url,//不支持跨域，如果url不为空，就会加载url的内容，content的内容忽略
		showCloseButton:false,
		otherButtons:["确定","取消"],
		otherButtonStyles:['btn-primary','btn-primary'],
		bootstrapModalOption:{keyboard: true},
		dialogHidden:function(){
			if (callback){
				callback();
			}
		},                    
		clickButton:function(sender,modal,index){
			if (index==0 && ok){
				$(this).closeDialog(modal);
				ok();
			}else if (index == 1){
				$(this).closeDialog(modal);
			}
		}
	})
}
