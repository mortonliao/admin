<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<script type="text/javascript" >
function append(){
	$('#add').dialog({
	    title: '创建用户',
	    width: 300,
	    height: 200,
	    closed: false,
	    cache: false,
	    modal: true
	});
}
function editUser(userId){
	$.ajax({
		type:'get',
		async:false,
		url:'${ctx}/user/findUserById.do',
		data:{'userId':userId},
		success:function(data){
			console.log(data);
			$("#username").textbox('setValue',data.data.username);
			$("#id").val(data.data.id);
			$("#roleId").combobox('setValue',data.data.roleId);
			$('#edit').dialog({
			    title: '修改',
			    width: 300,
			    height: 300,
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
function deleteUser(userId){
	$.messager.confirm('确认', '确定要删除该账号吗?', function(r){
		if (r){
			window.location.href = webName+"/user/deleteUser.do?userId="+userId;
		}
	});
	
}
function resetPwd(){
	var obj = $("#dg").datagrid("getChecked");
	if(obj.length > 0){
		var ids = new Array();
		$(obj).each(function(){
			ids.push(this.id);
		});
		console.log(ids);
		$.ajax({
			type:"post",			
			url:webName+"/user/resetPwd.do",
			data:{'ids':ids},
			async:false,
			success:function(data){
				if(data.code == 1){
					$.messager.show({
						title:'提示',
						msg:data.msg,
						showType:'fade',
						timeout:1400,
						style:{
							right:'',
							bottom:''
						}
					});
				}else{
					$.messager.alert('提示',data,'error');
				}
			},
			error:function(data){
				$.messager.alert('提示',data,'error');
			}
		});
	}else{
		$.messager.alert('提示',"没有选择",'error');
	}
}
$(document).ready(function() {
	$("#reset-but").linkbutton({disabled: true});
	$('#dg').datagrid({
		onCheck: function(index,row){
			$("#reset-but").linkbutton({disabled: false});
		}
	});
	$('#dg').datagrid({
		onCheckAll: function(rows){
			$("#reset-but").linkbutton({disabled: false});
		}
	});
	$('#dg').datagrid({
		onUncheck: function(index,row){
			var obj = $("#dg").datagrid("getChecked");
			$("#reset-but").linkbutton({disabled: true});
			if(obj.length > 0){
				$("#reset-but").linkbutton({disabled: false});
			}else{
				$("#reset-but").linkbutton({disabled: true});
			}
		}
	});
	$('#dg').datagrid({
		onUncheckAll: function(rows){
			$("#reset-but").linkbutton({disabled: true});
		}
	});
});
</script>
</head>
<body>
<body>
	<div>
		<table id="dg"  class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false,toolbar:'#tb'">  
		    <thead>  
		        <tr>  
		           	<th data-options="field:'ck',checkbox:true,align:'center'">用户编号</th>
		           	<th data-options="field:'id',width:100,align:'center'">用户编号</th>
					<th data-options="field:'username',width:80,align:'center'">用户名</th>
					<th data-options="field:'roleName',width:80,align:'center'">角色</th>
					<th data-options="field:'operation',align:'center'">操作</th>
		        </tr>  
		    </thead>  
		    <tbody>  
		       <c:forEach items="${list}" var="user">
		        	<tr>
		        		<td></td>
		        		<td>${user.id}</td>
		        		<td>${user.username}</td>
		        		<td>${user.roleName}</td>
		        		<td>
							<a href="#" class="easyui-linkbutton" data-options="" 
		        			onclick="editUser(${user.id})">修改</a>
		        			<a href="#" onclick="deleteUser(${user.id})" class="easyui-linkbutton" data-options="" >删除</a>
						</td>
		        	</tr>
		        </c:forEach>
		    </tbody>  
		</table>  
	</div>
	
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加用户</a>
		<a id="reset-but" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-lock',plain:true" onclick="resetPwd()">重置密码</a>
	</div>
	
	<div hidden="hidden" id="add"  style="text-align:center;padding:20px;">
		<div>
				<form id="userFrom_add" name="userFrom_add" method="post" action="${ctx}/user/saveUser.do">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>用户名:</td>
			    			<td>
			    				<input class="easyui-textbox" name="username"/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>角色:</td>
			    			<td>
			    				<select class="easyui-combobox" name="roleId" >
			    					<c:forEach items="${roleList}" var="role">
			    						<option value="${role.id}">${role.name}</option>
			    					</c:forEach>
			    				</select>
			    			</td>
			    		</tr>
			    	</table>
			    	<div>
		    			<input style="margin:10px;width:60px;height:30px;font-size: 14px;" type="submit" value="保存" />
		   			</div>
			    </form>
			</div>
	</div>
	<div hidden="hidden" id="edit"  style="text-align:center;padding:20px;">
		<div>
				<form id="userFrom_edit" name="userFrom_edit" method="post" action="${ctx}/user/saveUser.do">
			    	<table cellpadding="5">
			    		<tr>
			    			<td>用户名:</td>
			    			<td>
			    				<input class="easyui-textbox" id="username" name="username"/>
			    				<input type="hidden" id="id" name="id"/>
			    			</td>
			    		</tr>
			    		<tr>
			    			<td>角色:</td>
			    			<td>
			    				<select class="easyui-combobox" id="roleId" name="roleId" >
			    					<c:forEach items="${roleList}" var="role">
			    						<option value="${role.id}">${role.name}</option>
			    					</c:forEach>
			    				</select>
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