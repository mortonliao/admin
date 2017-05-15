<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="./common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" href="${ctx}/css/index.css" type="text/css">
<script src="${ctx}/js/index.js"></script>
<script type="text/javascript" >
$(document).ready(function() {
	var mainMenu = $($("#mainMenu").children("span").get(0));
	initMenu(mainMenu);
});
function initMenu(mainMenu){
	var ids  = mainMenu.attr("class").split(" ");
	var id = ids[ids.length-1];
	
	$("#mainMenu .info").css({"background":"#b3acac"});
	mainMenu.css({"background":"orange"});
	$.get('${ctx}/system/menu_list',{id:id},function(data){
		var zTreeObj = $.fn.zTree.init($("#tree"), setting, data);
		
	});
}
function changeMainMenu(mainMenu){
	initMenu($(mainMenu));	
}
</script>
</head>
<body>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 50px; background: #CFCFCF; ">
		<div style="float: left;">
			<img alt="" src="${ctx}/images/12xingzuo_05.png">
		</div>
		<div style="float: left;margin-left: 10px;margin-right: 40px;margin-top: 20px;">
			后台管理系统
		</div>
		<div class="container" style="float: left;width:70%;hright:50px;">
				<div id="mainMenu" class="row">
					<c:forEach items="${menuList}" var="menu" varStatus="s">
						<span class="btn btn-medium info ${menu.id}" onclick="changeMainMenu(this)">
							${menu.name}
						</span>
						<!-- <div class="btn btn-medium separator"> </div> -->
					</c:forEach>
				</div>
		</div>
		<div style="float: right;line-height: 50px;margin-right: 10px;">
			<shiro:user>  
				<shiro:principal/> &nbsp;<a href="/user/logout">退出</a>
			</shiro:user> 
			  
		</div>
	</div>

	<div data-options="region:'west',split:true,title:'控制台'" style="width: 150px;font-size:28px;">
		<ul id="tree" class="ztree" style="width:100%; overflow:auto;"></ul>
	</div>
	<div data-options="region:'center',title:''">
		<div id="tt" class="easyui-tabs" data-options="tools:'#tab-tools'" style="width:100%; height: 100%;">
			<!-- <div title="首页" style="line-height:800px;text-align: center;font-size: 48px;">
				欢迎使用后台管理系统
			</div> -->
		</div>
		<div id="tab-tools">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-no'" onclick="removeTabs()">
			</a>
		</div>
	</div>
</body>
</html>