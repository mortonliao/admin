<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!-- easyui --> 
<link rel="stylesheet" type="text/css" href="${ctx}/plug/jquery-easyui-1/themes/default/easyui.css">
<%-- <link rel="stylesheet" type="text/css" href="${ctx}/plug/jquery-easyui-1/demo/demo.css"> --%>
<link rel="stylesheet" type="text/css" href="${ctx}/plug/jquery-easyui-1/themes/icon.css">
       
<script type="text/javascript" src="${ctx}/plug/jquery-easyui-1/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/plug/jquery-easyui-1/jquery.easyui.min.js"></script>

<!-- ztree -->
<link rel="stylesheet" href="${ctx}/plug/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/plug/zTree_v3-master/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/plug/zTree_v3-master/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${ctx}/plug/zTree_v3-master/js/jquery.ztree.exedit.js"></script>



