<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
var $dia = $.pdialog.getCurrent();
function tj(){
	$("#loginForm",$dia).submit();
	$.pdialog.closeCurrent();
}
function onkeydown(event){
    if (event.keyCode == 13) {  
    	$("#loginForm",$dia).submit();
    	$.pdialog.closeCurrent();
    }  
} 
</script>
<div class="pageContent">
	<form method="post" action="login/index" name="loginForm" id="loginForm">
		<div class="pageFormContent" layoutH="58">
			<input type="hidden" name="flag" value="1"/>
			<input type="hidden" name="loginType" value="dialog"/>
			<div class="unit">
				<label>用户名：</label>
				<input type="text" name="username" size="28" class="required"/>
			</div>
			<div class="unit">
				<label>密码：</label>
				<input type="password" name="password" size="28" class="required"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="tj()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
