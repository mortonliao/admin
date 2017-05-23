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
function removeMore(){
	
}
function onClickRow(){
	
}
function submit(){
	$.ajax({
		type:'post',
		async:false,
		url:form.attr("action"),
		data:form.serializeArray(),
		success:function(data){
			$("#add").dialog('close');
			$.messager.show({
				title:'提示',
				msg:data.msg,
				showType:'fade',
				timeout:4,
				style:{
					right:'',
					bottom:''
				}
			});
		},
		error:function(data){
			$.messager.alert('提示',data,'error');
		}
	});
}
$(document).ready(function() {
	$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})
});
</script>
<style type="text/css">
input{
	width:250px;
}
</style>
</head>
<body>
<body>
	<div>
		<table id="dg" style="width: 100%;" class="easyui-datagrid" data-options="rownumbers:true,
		singleSelect:true,collapsible:true,toolbar:'#tb',onClickRow:onClickRow">  
		    <thead>  
		        <tr>  
		           	<th data-options="field:'ck',checkbox:true,width:50,align:'center'"></th>
		           	<th data-options="field:'name',width:100,align:'center'">名称</th>
					<th data-options="field:'role',width:100,align:'center'">标识</th>
					<th data-options="field:'description',width:200,align:'center'">描述</th>
					<th data-options="field:'status',width:80,align:'center'">状态</th>
					<!-- <th data-options="field:'operation',width:180,align:'center'">操作</th> -->
		        </tr>  
		    </thead>  
		    <tbody>  
		       <c:forEach items="${list}" var="role">
		        	<tr>
		        		<td></td>
		        		<td>${role.name}</td>
		        		<td>${role.role}</td>
		        		<td>${role.description}</td>
		        		<td>
		        			<c:if test="${role.status == true}">
		        				有效
		        			</c:if>
		        			<c:if test="${role.status == false}">
		        				无效
		        			</c:if>
		        		</td>
		        	</tr>
		        </c:forEach>
		    </tbody>  
		</table>  
	</div>
	
	<div id="tb" style="height:auto">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeMore()">删除</a>
	</div>
	
	<div hidden="hidden" id="add"  style="text-align:center;padding:20px;">
		<div>
				<form id="roleForm" name="roleForm" method="post" action="${ctx}/system/saveRole">
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
			    </form>
			</div>
		    <div>
		    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="submit()" >保存</a>
		   </div>
	</div>
	
</body>
</html>