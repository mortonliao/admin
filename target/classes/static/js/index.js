setting = {
	view : {
		showLine : false,
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
			pIdKey : "pId",
			rootPId : 0
		}
	},
	callback : {
		onClick : treeMenuonClick
	}
};
function treeMenuonClick(e,treeId, treeNode) {
	if(treeNode.isParent == true){
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.expandNode(treeNode);
	}else{
		if ($('#tt').tabs('exists', treeNode.name)) {
			$('#tt').tabs('select', treeNode.name);
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'
					+ treeNode.path + '" style="width:100%;height:100%;"></iframe>';
			$('#tt').tabs('add', {
				title : treeNode.name,
				content : content,
				closable : true
			});
		}
	}
}
	
function setFontCss(treeId, treeNode) {
	return treeNode.level == 0 ? {'font-size':'18px'} : {};
};
function showIconForTree(treeId, treeNode) {
	return treeNode.level != 1;
};
function removeTabs(){
	var tiles = new Array();  
    var tabs = $('#tt').tabs('tabs');      
    var len =  tabs.length;           
    if(len>0){  
      for(var j=0;j<len;j++){  
          var a = tabs[j].panel('options').title;   
          if(a != '首页'){
	          tiles.push(a);  
          }
      }  
      for(var i=0;i<tiles.length;i++){               
          $('#tt').tabs('close', tiles[i]);  
      }  
    }  
}