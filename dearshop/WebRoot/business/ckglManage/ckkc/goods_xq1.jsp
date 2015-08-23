<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageContent" layoutH="0">
	<div>
		<div style="margin:15px;float:left">
			<div style="float:left"><img style="float:left" src="${good.picpath}" id="img" width="210" height="280"/></div>
			<div style="float:right">
			<table style="margin-left:15px;">
				<tr><td style="height:53px;font-size:21px">编码：&nbsp;&nbsp;${good.code}</td></tr>
				<tr><td style="height:53px;font-size:21px">名称：&nbsp;&nbsp;${good.showname}</td></tr>
				<tr><td style="height:53px;font-size:21px">价格：&nbsp;&nbsp;${good.price}元</td></tr>
				<tr><td style="height:53px;font-size:21px">类型：&nbsp;&nbsp;${good.sex} &nbsp;&nbsp;&nbsp;&nbsp;${good.sname}</td></tr>
			</table>
			<table style="margin-left:15px;" border="solid 1px;">
				<tr align="center" style="height:25px">
					<c:forEach var="size" items="${sizelist}" varStatus="status" >
						<td style="width:35px;">${size.name}</td>
					</c:forEach>
				</tr>
				<tr align="center" style="height:25px">
					<td>${good.size1}</td>
					<td>${good.size2}</td>
					<td>${good.size3}</td>
					<td>${good.size4}</td>
					<td>${good.size5}</td>
					<td>${good.size6}</td>
				</tr>
			</table>
			</div>
		</div>
		
		<div style="margin-left:15px;margin-right:15px;float:left;width:440px;height:160px">
			<fieldset style="height:100%;width:100%;">
				<legend style="font-size:17px">详细介绍</legend>
				${good.remark}
			</fieldset>
		</div>
	</div>
</div>
