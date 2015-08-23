<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageContent" layoutH="0">
	<div>
		<div style="margin:15px;float:left">
			<div style="float:left"><img style="float:left" src="${good.picpath}" id="img" width="180" height="240"/></div>
			<div style="float:right">
			<table style="margin-left:15px;">
				<tr><td style="height:60px;font-size:22px">编码：&nbsp;&nbsp;${good.code}</td></tr>
				<tr><td style="height:60px;font-size:22px">名称：&nbsp;&nbsp;${good.showname}</td></tr>
				<tr><td style="height:60px;font-size:22px">价格：&nbsp;&nbsp;${good.price}元</td></tr>
				<tr><td style="height:60px;font-size:22px">类型：&nbsp;&nbsp;${good.sex} &nbsp;&nbsp;&nbsp;&nbsp;${good.sname}</td></tr>
			</table>
			</div>
		</div>
		
		<div style="margin-left:15px;margin-right:15px;float:left;width:400px;height:160px">
			<fieldset style="height:100%;width:100%;">
				<legend style="font-size:17px">详细介绍</legend>
				${good.remark}
			</fieldset>
		</div>
	</div>
</div>
