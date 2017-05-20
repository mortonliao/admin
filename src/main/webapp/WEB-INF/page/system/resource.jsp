<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<%-- <link rel="stylesheet" href="${ctx}/css/index.css" type="text/css"> --%>
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
	<ul id="tree" class="ztree" style="overflow:auto;"></ul>
</body>
</html>