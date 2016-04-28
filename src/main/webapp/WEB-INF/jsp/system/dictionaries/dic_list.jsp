<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/modal-dialog.js"></script>
		<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.bootstrap.teninedialog.v3.js"></script>
<script type="text/javascript">
	$(top.shutdown());	
	
	//新增或修改字典类型
	function addmenu(opera,code_id){
		var title = "新增业务字典类型";
		if (opera=="edit"){
			title = "修改业务字典类型";
		}
		var diag = new top.Dialog();
		diag.Drag=true;
		diag.Title =title;
		diag.URL = '<%=basePath%>dictionaries/toAddPar.do?code_id='+code_id+'&opera='+opera;
		diag.Width = 237;
		diag.Height = 256;
		diag.CancelEvent = function(){ //关闭事件
			//location.reload();
			diag.close();
		};
		diag.show();
	}
	
	//增加或修改具体字典
	function editmenu(opera,code_id){
		var title = "新增业务字典";
		if (opera=="edit"){
			title = "修改业务字典";
		}
	   	var diag = new top.Dialog();
		diag.Drag=true;
		diag.Title =title;
		diag.URL = '<%=basePath%>dictionaries/toAddChild.do?code_id='+code_id+'&opera='+opera;
		diag.Width = 237;
		diag.Height = 256;
		diag.CancelEvent = function(){ //关闭事件
			//location.reload();
			diag.close();
		};
		diag.show();
	}
	//删除字典
	function delmenu(codeId,isParent){
		isParent = isParent==true?'1':'0';
		var url = '<%=basePath%>dictionaries/del.do?code_id='+codeId+'&isParent='+isParent+'&guid='+new Date().getTime();
		showConfirm("提示","确定删除此数据字典吗？",'',function(){
			$.get(url,function(ret){
				if ("succ"==ret){
					showAlert("提示","删除成功！",'',function(){document.location.reload();});
				}else{
					showAlert("提示","删除失败！",'',function(){document.location.reload();});
				}
			},'text');
		});
	}
	//折叠展开
	function openClose(parentId,curObj,trIndex){
		var txt = $(curObj).text();
		if(txt=="展开"){
			$(curObj).text("折叠");
			$("#tr"+parentId).after("<tr id='tempTr"+parentId+"'><td colspan='5'>数据载入中</td></tr>");
			if(trIndex%2==0){
				$("#tempTr"+parentId).addClass("main_table_even");
			}
			var url = "<%=basePath%>dictionaries/sub.do?parentId="+parentId+"&guid="+new Date().getTime();
			$.get(url,function(data){
				if (data.data != null){
					data = data.data;
				}
				if(data.length>0){
					var html = "";
					$.each(data,function(i){
						html = "<tr style='height:24px;line-height:24px;background-color:#A5A5A5' name='subTr"+parentId+"'>";
						html += "<td class='center'><span style='width:80px;display:inline-block;'></span>";
						if(i==data.length-1)
							html += "<img src='static/images/joinbottom.gif' style='vertical-align: middle;'/>";
						else
							html += "<img src='static/images/join.gif' style='vertical-align: middle;'/>";
						html += "<span style='width:100px;text-align:left;display:inline-block;'>"+this.code_description+"</span>";
						html += "</td>";
						html += "<td class='center'>"+this.code_code+"</td>";
						html += "<td class='center'>"+this.code_name+"</td>";
						html += "<td><a class='btn btn-mini btn-info' title='编辑' onclick='editmenu(\"edit\",\""+this.code_id+"\")'><i class='icon-edit'></i></a> <a class='btn btn-mini btn-danger' title='删除' onclick='delmenu(\""+this.code_id+"\",false)'><i class='icon-trash'></i></a></td>";
						html += "</tr>";
						$("#tempTr"+parentId).before(html);
					});
					$("#tempTr"+parentId).remove();
					if(trIndex%2==0){
						$("tr[name='subTr"+parentId+"']").addClass("main_table_even");
					}
				}else{
					$("#tempTr"+parentId+" > td").html("<div class='center'>没有相关数据</div>");
				}
			},"json");
		}else{
			$("#tempTr"+parentId).remove();
			$("tr[name='subTr"+parentId+"']").remove();
			$(curObj).text("展开");
		}
	}
	//刷新页面
	function refresh(){
		alert("hhehh");
		location.reload();
	}
</script>
</head>
<body>
	<table id="table_report" class="table table-bordered ">
		<thead>
		<tr>
			<th class='center'>字典名称</th>
			<th class='center'>字典编码</th>
			<th class='center'>字典标识</th>
			<th class='center'>操作</th>
		</tr>
		</thead>
		<c:choose>
			<c:when test="${not empty varList}">
				<c:forEach items="${varList}" var="dic" varStatus="vs">
					<tr id="tr${dic.code_id}">
						<td class="center" style="color:blue;">${dic.code_description}</td>
						<td class='center'>${dic.code_code}</td>
						<td class='center'>${dic.code_name}</td>
						<td style="width: 25%;">
							<a class='btn btn-mini btn-warning' onclick="openClose('${dic.code_id}',this,${vs.index })" >展开</a>
							<a class='btn btn-mini btn-info' title="编辑" onclick="addmenu('edit','${dic.code_id }')" ><i class='icon-edit'></i></a>
							<a class='btn btn-mini btn-info' title="添加字典" onclick="editmenu('add','${dic.code_id }')" ><i class='icon-plus'></i></a>
							<a class='btn btn-mini btn-danger' title="删除"  onclick="delmenu('${dic.code_id }',true)"><i class='icon-trash'></i></a>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td colspan="100">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	
	<div class="page_and_btn">
		<div>
			&nbsp;&nbsp;<a class="btn btn-small btn-success" onclick="addmenu('add','');">新增</a>
		</div>
	</div>
	
</body>
</html>