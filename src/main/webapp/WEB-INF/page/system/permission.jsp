<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" href="${ctx}/css/resource.css" type="text/css">
<script src="${ctx}/js/permission.js"></script>
<script type="text/javascript" >
function append(){
	$('#add').dialog({
	    title: '创建角色',
	    width: 400,
	    height: 300,
	    closed: false,
	    cache: false,
	    modal: true
	});
}

function editRole(roleId){
	$.ajax({
		type:'post',
		async:false,
		url:'${ctx}/system/findRoleById.do',
		data:{'roleId':roleId},
		success:function(data){
			$("#name").textbox('setValue',data.data.name);
			$("#id").val(data.data.id);
			$("#role").textbox('setValue',data.data.role);
			$("#description").textbox('setValue',data.data.description);
			
			$('#edit').dialog({
			    title: '修改',
			    width: 400,
			    height: 350,
			    closed: false,
			    cache: false,
			    //href: 'get_content.php',
			    modal: true
			});
		},
		error:function(data){
			$.messager.alert('提示',data,'error');
		}
	});
}

$(document).ready(function() {
	
});
</script>
</head>
<body>
<body>
	<div>
		<table id="dg" style="width: 100%;" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,
			toolbar:'#tb'">  
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
		        		<td>${role.name}</td>
		        		<td>${role.role}</td>
		        		<td>
		        			<a href="#" class="easyui-linkbutton" data-options="" 
		        			onclick="editRole(${role.id})">修改</a>
		        			<a href="deleteRole?roleId=${role.id}" class="easyui-linkbutton" data-options="" >删除</a>
		        			<a href="#" class="easyui-linkbutton" data-options="" 
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
	
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加角色</a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeMore()">删除角色</a> -->
	</div>
	
	<div hidden="hidden" id="edit"  style="text-align:center;padding:20px;">
		<div>
				<form id="roleFrom_edit" name="roleFrom_edit" method="post" action="${ctx}/system/saveRole.do">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>名称:</td>
			    			<td>
			    				<input class="easyui-textbox" id="name" name="name" />
			    				<input type="hidden" id= "id" name="id"/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>标识:</td>
			    			<td>
			    				<input class="easyui-textbox" id="role" name="role" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>描述:</td>
			    			<td>
			    				<input class="easyui-textbox" id="description" name="description" data-options="multiline:true" style="height:60px"></input>
			    			</td>
			    		</tr>
			    	</table>
			    	<div >
		    			<input style="margin:10px;width:60px;height:30px;font-size: 14px;" type="submit" value="保存" />
		   			</div>
			    </form>
			</div>
	</div>
	<div hidden="hidden" id="add"  style="text-align:center;padding:20px;">
		<div>
				<form id="roleFrom_add" name="roleFrom_add" method="post" action="${ctx}/system/saveRole.do">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>名称:</td>
			    			<td>
			    				<input class="easyui-textbox" name="name"/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>标识:</td>
			    			<td>
			    				<input class="easyui-textbox" name="role" />
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>描述:</td>
			    			<td>
			    				<input class="easyui-textbox" name="description" data-options="multiline:true" style="height:60px"></input>
			    			</td>
			    		</tr>
			    	</table>
			    	<div>
		    			<input style="margin:10px;width:60px;height:30px;font-size: 14px;" type="submit" value="保存" />
		   			</div>
			    </form>
			</div>
		    
	</div>
</body>
</html>