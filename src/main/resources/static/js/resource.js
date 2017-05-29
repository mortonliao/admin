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
	$("#iconFile").filebox({disabled: true});
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
	$("#iconFile").filebox({disabled: false});
	
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
//	var form = $("#"+formId);
//	
//	console.log($("#iconFile").val());
//	var formData = new FormData($("#"+formId));
//	console.log(formData);
//	$.ajax({
//		type:'post',
//		async:false,
//		cache: false,  
//        processData: false,
//		contentType: false,
//		url:form.attr("action"),
//		data:formData,//form.serializeArray(),
//		success:function(data){
//			if(formId == 'resourceFormAdd'){
//				$("#add").dialog('close');
//			}
//			$.messager.show({
//				title:'提示',
//				msg:data.msg,
//				showType:'fade',
//				timeout:4,
//				style:{
//					right:'',
//					bottom:''
//				}
//			});
//			$("#createLowerBut").linkbutton({disabled: false});
//			$.get('allResource',function(treeData){
//				var treeObj = $.fn.zTree.init($("#tree"), setting, treeData);
//				var nodes =  treeObj.getNodeByParam("id", data.data.id);
//				treeObj.selectNode(nodes);
//			});
//		},
//		error:function(data){
//			$.messager.alert('提示',data,'error');
//		}
//	});
	
	var ajax_option={
			      url: getRootPath()+'save.do',                  //String, 表单提交的目标地址，此属性会覆盖表单的action属性
			      type:'POST',             //String，表单提交的方式(POST or GET)，此属性会覆盖表单的method属性
			      dataType: 'json',    //String，指定接受服务端返回的数据类型(xml，script  or  json)
			      clearFomr: true,   //boolean，默认为false，成功提交后是否清除所有表单元素的值
			      resetFomr: true,  //boolean，默认为false，成功提交后是否重置所有表单元素的值
			      timeout: 3000,    //number，单位ms，限制请求的时间，当请求大于设置的时间后，跳出请求
			      success:function(responseText,statusText,xhr,$form){
			         console.log(responseText);
			         //业务提示
			     },//提交成功后的回调函数 。参数详解：responseText，服务器返回的数据内容；statusText，服务器返回的状态
			     beforSubmit: function(formData, jqForm, options){
			         console.log(formData);
			         //业务提示
			     },//提交之前的回调函数。参数详解：formData，数组对象，为表单的内容；jqForm，jQuery对象，封装了表单的元素；options，之前设置的ajaxSubmit的option对象。
			 };
			console.log(ajax_option);
			 $("#resourceForm").ajaxSubmit(ajax_option);
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

function getRootPath() {  
    var pathName = window.location.pathname.substring(1);  
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));  
    return window.location.protocol + '//' + window.location.host + '/' + webName + '/';  
} 





