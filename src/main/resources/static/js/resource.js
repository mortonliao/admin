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
	
	if(treeNode.type == 'menu'){
		$("#createLowerBut").show();
	}else{
		$("#createLowerBut").hide();
	}
	
	$("#name").textbox('readonly',true);
	$("#path").textbox('readonly',true);
	$("#permission").textbox('readonly',true);
	$("#sort").textbox('readonly',true);
	$("#type").combobox('readonly',true);
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

var resourceId,resourceIds,name,path,permission,sort,type;

function editResource(){
	
	resourceId = $("#resourceId").val();
	resourceIds = $("#resourceIds").val();
	name = $("#name").textbox('getValue');
	path = $("#path").textbox('getValue');
	permission = $("#permission").textbox('getValue');
	sort = $("#sort").textbox('getValue');
	type = $("#type").combobox('getValue');
	
	$("#name").textbox('readonly',false);
	$("#path").textbox('readonly',false);
	$("#permission").textbox('readonly',false);
	$("#sort").textbox('readonly',false);
	$("#createLowerBut").linkbutton({disabled: true});
	$("#type").combobox('readonly',false);
	$("#icon").filebox({disabled: false});
	
}

function cancelEditResource(){
	
	$("#resourceId").val(resourceId);
	$("#resourceIds").val(resourceIds);
	$("#name").textbox('setValue',name);
	$("#path").textbox('setValue',path);
	$("#permission").textbox('setValue',permission);
	$("#sort").textbox('setValue',sort);
	$("#type").combobox('setValue',type);
	
	$("#name").textbox('readonly',true);
	$("#path").textbox('readonly',true);
	$("#permission").textbox('readonly',true);
	$("#sort").textbox('readonly',true);
	$("#createLowerBut").linkbutton({disabled: false});
	$("#type").combobox('readonly',true);
	$("#icon").filebox({disabled: true});
	
}

function subForm(formId){
	var form = $("#"+formId);
	$.ajax({
		type:'post',
		async:false,
		url:form.attr("action"),
		data:form.serializeArray(),
		success:function(data){
			if(formId == 'resourceFormAdd'){
				$("#add").dialog('close');
			}
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
			$("#createLowerBut").linkbutton({disabled: false});
			$.get('allResource',function(treeData){
				var treeObj = $.fn.zTree.init($("#tree"), setting, treeData);
				var nodes =  treeObj.getNodeByParam("id", data.data.id);
				treeObj.selectNode(nodes);
			});
		},
		error:function(data){
			$.messager.alert('提示',data,'error');
		}
	});
}

function add(){
	$("#parentId").val($("#resourceId").val());
	$("#parentIds").val($("#resourceIds").val()+$("#parentId").val()+"/");
	$('#add').dialog({
	    title: '创建下级',
	    width: 400,
	    height: 350,
	    closed: false,
	    cache: false,
	    //href: 'get_content.php',
	    modal: true
	});
	//$('#add').dialog('refresh', 'new_content.php');
}







