<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<head>
<base href="<%=basePath%>"></base>
<title>移动办公</title>
<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<style type="text/css">
<!--
#back {
	height: 593px;
	width: 1003px;
	background-image: url(login/images/login.jpg);
	background-repeat: no-repeat;
	position: absolute;
	left: 54%;
	top: 50%;
	margin-top: -306px;
	margin-left: -501px;
}
body {
	background-color: #016FA1;
}
#login {
	position: absolute;
	border:0px solid red;
	top: 291px;
	left: 400px;
	height: 100px;
	width: 168px;
}

Input
{
BACKGROUND-COLOR: transparent;
BORDER-BOTTOM: #66C2FF 1px solid;
BORDER-LEFT: #66C2FF 1px solid;
BORDER-RIGHT: #66C2FF 1px solid;
BORDER-TOP: #66C2FF 1px solid;
COLOR: #000000;
HEIGHT: 20px;
width: 150px;
background-image:url(login/images/input1.gif);
font-size: 12pt
}

span{
	font-size:12px;
}

img{
	width:46px;
	height:20px
}
img.button{
    width:62px;
	height:25px
}

-->
</style>
<script type="text/javascript">
document.onkeydown = keyDown;
$(function(){
	$("#username").focus();
	$("#login_button").click(function(){
		var name=$("#username"); 
		var password=$("#password");
		var code=$("#randomCode");
		if(name.val()==""){
			$("#nulltext").html("请输入用户名");
			name.focus();
			return false;     
		}
		if(!password.val()){
			$("#nulltext").html("请输入密码");
			password.focus();
			return false;     
		}
		$("#loginForm").submit();
	});
	$("#reset_button").bind("click",function(){
		$("#loginForm").reset();;
	});
});
function keyDown(event){
	if (event.keyCode == 13) {
		$("#login_button").click();
    };
};
</script>
</head>

<body>
<form method="post" action="login/index" name="loginForm" id="loginForm">
<div id="back">
  <div id="login">
	<table>
		<tr>
			<td><input type="text" id="username" name="username" value="" /><span id="namenull"></span></td>
		</tr>
		<tr>
			<td><input type="password" id="password" name="password" value="" style="font-size:10pt" /></td>
		</tr>	
		</table>
			<table width="100%" style="margin-top:5px;margin-left:-50px"  >
				<tr>
					<td><div id="login_button"><img style="margin-left:50px;cursor:pointer;" class="button" src="login/images/dl.png"  /></div></td>
					<td style="margin-left:200px"><div style="margin-left:20px;cursor:pointer;"  id="reset_button"><img class="button" src="login/images/qx.png" /></div>
					</td>
				</tr>
				<tr>
			     	<td colspan="2" align="center"><span id="nulltext"   style="color: red;">${error }</span></td>
				</tr>
			</table>
  		</div>
</div>
</form>
</body>
