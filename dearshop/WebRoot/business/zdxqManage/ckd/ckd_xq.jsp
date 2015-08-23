<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<table class="list" style="width:100%;overflow:auto" layoutH="0">
		<thead>
			<tr align="center">
				<th width="7%">序号</th>
				<th width="42%">商品</th>
				<th width="13%">标价</th>
				<th width="13%">售价</th>
				<th width="13%">标码</th>
				<th width="13%">数量</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty outgoods}">
		    	<tr><td colspan="11"><center>暂时没有信息！</center></td></tr>
		    </c:if>
			<c:forEach var="good" items="${outgoods}" varStatus="status" >
				<tr target="good_id" rel="${good.id}" align="center">
					<td>${status.index+1} </td>
					<td><img src="${good.picpath}" style="margin:3px;margin-left:20px;width:30px;height:40px;float:left" />
					<div style="float:left;margin:3px;margin-left:20px;"><span style="font-weight:bold;">${good.code}</span>
					<br><br><span>${good.showname}</span></div></td>
					<td>${good.price}</td>
					<td>${good.price_t}</td>
					<td>${good.name}</td>
					<td>${good.amount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
