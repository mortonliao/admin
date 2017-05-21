<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<%-- <link rel="stylesheet" href="${ctx}/css/resource.css" type="text/css"> --%>
<script src="${ctx}/js/permission.js"></script>
<script type="text/javascript" >
$(document).ready(function() {
	$.get('${ctx}/resource/allresource2',function(data){
		var zTreeObj = $.fn.zTree.init($("#tree"), setting, data);
	});
});
</script>
</head>
<body>
<body>
	<div>
		<table style="width: 100%;" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,collapsible:true">  
		    <thead>  
		        <tr>  
		           	<th data-options="field:'name',width:100,align:'center'">名称</th>
					<th data-options="field:'role',width:80,align:'center'">标识</th>
					<th data-options="field:'operation',width:180,align:'center'">操作</th>
		        </tr>  
		    </thead>  
		    <tbody>  
		       <c:forEach items="${list}" var="role">
		        	<tr>
		        		<td>${role.name }</td>
		        		<td>${role.role }</td>
		        		<td>
		        			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" 
		        			onclick="queryPermissionByRoleId(${role.id })">权限</a>
		        		</td>
		        	</tr>
		        </c:forEach>
		    </tbody>  
		</table>  
	</div>
	
	<div hidden="hidden" id="permissionList"  style="text-align:center;padding:20px;">
		<ul id="tree" class="ztree" style="overflow:auto;"></ul>
	</div>
</body>
</html>