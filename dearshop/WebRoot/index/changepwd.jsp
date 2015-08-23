<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" action="main/changepwd?callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="57">
			<div class="unit">
				<label>旧密码：</label>
				<input type="password" name="oldpwd" size="30" class="required" />
			</div>
			<div class="unit">
				<label>新密码：</label>
				<input type="password" id="cp_newpwd" name="newpwd" size="30" class="required alphanumeric"/>
			</div>
			<div class="unit">
				<label>重复输入新密码：</label>
				<input type="password" name="rnewpwd" size="30" equalTo="#cp_newpwd" class="required alphanumeric"/>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>

