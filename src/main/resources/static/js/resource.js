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
	$("#sort").textbox('setValue',treeNode.sort);
	
	$("#type").combobox('setValue',treeNode.type);
	$("#iconimg").attr('src',treeNode.icon);
	$("#node-detail .context .icon").attr("src",treeNode.icon);
	
	if(treeNode.type != 'menu' && treeNode.type != 'root'){
		$("#createLowerBut").hide();
	}else{
		$("#createLowerBut").show();
	}
	
	if(treeNode.type == 'root'){
		$("#type").combobox('readonly',true);
		$("#icon").filebox({disabled: true});
	}else{
		$("#type").combobox('readonly',false);
		$("#icon").filebox({disabled: false});
	}
	
	
	$("#name").textbox('readonly',true);
	$("#path").textbox('readonly',true);
	$("#permission").textbox('readonly',true);
	$("#sort").textbox('readonly',true);
	
	$("#icon").filebox({disabled: true});
	
	$("#createLowerBut").linkbutton({disabled: false});
	$("#node-detail").show();
}
	
function setFontCss(treeId, treeNode) {
	return treeNode.level == 0 ? {'font-size':'20px'} : {};
};
function showIconForTree(treeId, treeNode) {
	return treeNode.level != 1;
};
function editResource(){
	$("#name").textbox('readonly',false);
	$("#path").textbox('readonly',false);
	$("#permission").textbox('readonly',false);
	$("#sort").textbox('readonly',false);
	$("#createLowerBut").linkbutton({disabled: true});
	$("#type").combobox('readonly',false);
	$("#icon").filebox({disabled: false});
	
//	if(treeNode.type == 'root'){
//		$("#type").combobox('readonly',true);
//		$("#icon").filebox({disabled: true});
//	}else{
//		$("#type").combobox('readonly',false);
//		$("#icon").filebox({disabled: false});
//	}
	
}
function subForm(formId){
	var form = $("#"+formId);
	$.ajax({
		type:'post',
		async:false,
		url:form.attr("action"),
		data:form.serializeArray(),
		success:function(data){
			
			if($("#add")){
				$("#add").dialog('close');
			}
			$.messager.show({
				title:'提示',
				msg:data,
				showType:'fade',
				timeout:3,
				style:{
					right:'',
					bottom:''
				}
			});
			$.get('allresource',function(treeData){
				var zTreeObj = $.fn.zTree.init($("#tree"), setting, treeData);
			});
		},
		error:function(data){
			$.messager.show({
				title:'提示',
				msg:data,
				timeout:3,
				showType:'fade',
				style:{
					right:'',
					bottom:''
				}
			});
		}
	});
}

function add(){
	$("#parentId").val($("#resourceId").val());
	$("#parentIds").val($("#resourceIds").val()+$("#parentId").val()+"/");
	$('#add').dialog({
	    title: '创建下级',
	    width: 400,
	    height: 300,
	    closed: false,
	    cache: false,
	    //href: 'get_content.php',
	    modal: true
	});
	//$('#add').dialog('refresh', 'new_content.php');
}







