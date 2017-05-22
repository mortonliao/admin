setting = {
		view : {
			showLine : true,
			expandSpeed : "normal",
			selectedMulti : false,
			fontCss : setFontCss,
			/* showIcon: showIconForTree, */
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "parentId",
				rootPId : 0
			}
		},
		check: {
			enable: true,
			chkStyle: "checkbox",
			chkboxType: { "Y": "ps", "N": "ss" }
		},
		callback : {
			onClick : nodeClick
		}
	};
function nodeClick(e,treeId, treeNode) {
	$("#resourceId").val(treeNode.id);
	$("#resourceIds").val(treeNode.parentIds);
	$("#name").textbox('setValue',treeNode.name);
	$("#path").textbox('setValue',treeNode.path);
	$("#permission").textbox('setValue',treeNode.permission);
	
	$("#type").combobox('setValue',treeNode.type);
	$("#iconimg").attr('src',treeNode.icon);
	$("#node-detail .context .icon").attr("src",treeNode.icon);
	
	if(treeNode.type != 'menu'){
		$("#createLowerBut").hide();
	}else{
		$("#createLowerBut").show();
	}
	$("#name").textbox('readonly',true);
	$("#path").textbox('readonly',true);
	$("#permission").textbox('readonly',true);
	$("#type").combobox('readonly',true);
	$("#icon").filebox({disabled: true});
	
	$("#createLowerBut").linkbutton({disabled: false});
	$("#node-detail").show();
}
	
function setFontCss(treeId, treeNode) {
	return treeNode.level == 0 ? {'font-size':'20px'} : {};
};

function queryPermissionByRoleId(roleId){
	$('#permissionList').dialog({
	    title: '权限管理',
	    width: 400,
	    height: 500,
	    closed: false,
	    modal: true,
	    buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				var treeObj = $.fn.zTree.getZTreeObj("tree");
				var addNodes = treeObj.getCheckedNodes(true);
				var addList = new Array();
				$(addNodes).each(function(i,v){
					addList.push(v.id);
				});
				var removeNodes = treeObj.getCheckedNodes(false);
				var removeList = new Array();
				$(removeNodes).each(function(i,v){
					removeList.push(v.id);
				});
				
				$.ajax({
					type:'post',
					async:false,
					url:'saveRolePermission',
					data:{'roleId':roleId,'addList':addList,'removeList':removeList},
					success:function(data){
						$("#permissionList").dialog('close');
						$.messager.show({
							title:'操作提示',
							msg:data,
							showType:'fade',
							timeout:3,
							style:{
								right:'',
								bottom:''
							}
						});
					},
					error:function(data){
						$.messager.show({
							title:'操作提示',
							msg:data,
							showType:'fade',
							timeout:3,
							style:{
								right:'',
								bottom:''
							}
						});
					}
				});
			}
		},{
			text:'取消',
			iconCls:'icon-no',
			handler:function(){
				$("#permissionList").dialog('close');
			}
		}]
	});
}