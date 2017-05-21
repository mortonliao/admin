<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" href="${ctx}/css/resource.css" type="text/css">
<script src="${ctx}/js/resource.js"></script>
<script type="text/javascript" >
$(document).ready(function() {
	$.get('${ctx}/resource/allresource',function(data){
		var zTreeObj = $.fn.zTree.init($("#tree"), setting, data);
	});
	
});

</script>
</head>
<body>
<body>
	<div class="layout left">
		<ul id="tree" class="ztree" style="overflow:auto;"></ul>
	</div>
	<div id="node-detail" hidden="hidden" class="layout center" style="margin-left: 20px;">
		<div class="easyui-panel" title="详细信息" style="width:400px;padding: 10px;text-align: center;">
			<div style="text-align:center;padding:5px;">
				<form id="resourceForm" name="resourceForm" method="post" action="${ctx}/resource/save">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>名称:</td>
			    			<td>
			    				<input readonly="readonly" class="easyui-textbox" id="name" name="name"/>
			    				<input type="hidden" id="resourceId" name="id" value=""/>
			    				<input type="hidden" id="resourceIds" name="parentIds" value=""/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>链接:</td>
			    			<td>
			    				<input readonly="readonly" class="easyui-textbox" id="path" name="path" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>权限:</td>
			    			<td>
			    				<input readonly="readonly" class="easyui-textbox" id="permission" name="permission" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>排序:</td>
			    			<td>
			    				<input readonly="readonly" class="easyui-textbox" id="sort" name="sort" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>类型:</td>
			    			<td>
			    				<select readonly="readonly" id="type" class="easyui-combobox" name="type">
									<option value="menu">菜单</option>
									<option value="button">按钮</option>
								</select>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>图标:</td>
			    			<td>
			    				<input disabled="disabled" id="icon" class="easyui-filebox" name="icon" data-options="buttonText:'选择',prompt:''" >
								<img style="float:right; margin-top: 5px;margin-left: 5px;" id="iconimg" class="icon" alt="" src="" />
			    			</td>
			    		</tr>
			    	</table>
			    </form>
			</div>
		    <div>
		    	<shiro:hasPermission name="resource:edit">
					<a onclick="editResource()" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">编辑</a>
					<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="subForm('resourceForm')" >保存</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="resource:create">
					<a id="createLowerBut" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="add()">创建下级</a>
				</shiro:hasPermission>
		   </div>
		</div>
	</div>
	
	<div hidden="hidden" id="add"  style="text-align:center;padding:20px;">
		<div>
				<form id="resourceFormAdd" name="resourceFormAdd" method="post" action="${ctx}/resource/save">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>名称:</td>
			    			<td>
			    				<input class="easyui-textbox" name="name"/>
			    				<input type="hidden" id="parentId" name="parentId" value=""/>
			    				<input type="hidden" id="parentIds" name="parentIds" value=""/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>链接:</td>
			    			<td>
			    				<input class="easyui-textbox" name="path" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>权限:</td>
			    			<td>
			    				<input class="easyui-textbox"  name="permission" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>排序:</td>
			    			<td>
			    				<input class="easyui-textbox" id="sort" name="sort" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>类型:</td>
			    			<td>
			    				<select  class="easyui-combobox" name="type">
									<option value="menu">菜单</option>
									<option value="button">按钮</option>
								</select>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>图标:</td>
			    			<td>
			    				<input class="easyui-filebox" name="icon" data-options="buttonText:'选择',prompt:''" >
								<img style="float:right; margin-top: 5px;margin-left: 5px;" id="iconimg" class="icon" alt="" src="" />
			    			</td>
			    		</tr>
			    	</table>
			    </form>
			</div>
		    <div>
		    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="subForm('resourceFormAdd')" >保存</a>
		   </div>
	</div>
</body>
</html>