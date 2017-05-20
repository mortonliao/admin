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
			pIdKey : "parentId",
			rootPId : 0
		}
	},
	callback : {
		onClick : treeMenuonClick
	}
};
function treeMenuonClick(e,treeId, treeNode) {
	
}
	
function setFontCss(treeId, treeNode) {
	return treeNode.level == 0 ? {'font-size':'18px'} : {};
};
function showIconForTree(treeId, treeNode) {
	return treeNode.level != 1;
};
