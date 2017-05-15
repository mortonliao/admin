<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<link rel="stylesheet" href="${ctx}/css/login.css" type="text/css"/>
<script type="text/javascript">
		var index = 0;
		function addPanel(){
			index++;
			$('#tt').tabs('add',{
				title: 'Tab'+index,
				content: '<div style="padding:10px">Content'+index+'</div>',
				closable: true
			});
		}
		function removePanel(){
			var tab = $('#tt').tabs('getSelected');
			if (tab){
				var index = $('#tt').tabs('getTabIndex', tab);
				$('#tt').tabs('close', index);
			}
		}
	</script>
</head>
<body>
	<div class="center login">
		<div>
			<h1>后台登陆</h1>
		</div>
		<form action="/user/login" method="post">
			<div style="margin-bottom: 20px">
			<input class="easyui-textbox" type="text" name="username" data-options="prompt:'输入账号'" />
			</div>
			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="password" type="password" />
			</div>
			<!-- <div style="margin-bottom: 20px">
				<input class="easyui-textbox" type="text" data-options="prompt:'验证码'" style="width: 30%;float: left;">
				
			</div> -->
			<div style="margin-bottom: 20px">
				<%-- <a href="${ctx}/user/login" class="easyui-linkbutton"><span>登陆</span></a> --%>
				<input type="submit" value="登陆" class="easyui-linkbutton"/>
			</div>
			<div >
				<span class="msg">
				${msg}
				</span>
			</div>
		</form>
		
		
		<!-- <div style="bottom: 10px;">
			<span style="font-family:Arial;">©</span>
		</div> -->
	</div>
</body>
</html>