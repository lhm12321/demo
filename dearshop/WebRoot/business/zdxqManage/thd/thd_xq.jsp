<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<table class="list" style="width:100%;overflow:auto" layoutH="180">
		<thead>
			<tr align="center">
				<th width="7%">序号</th>
				<th width="42%">退货商品</th>
				<th width="13%">售价</th>
				<th width="13%">标码</th>
				<th width="13%">数量</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty thgoods}">
		    	<tr><td colspan="11"><center>暂时退换商品！</center></td></tr>
		    </c:if>
			<c:forEach var="good" items="${thgoods}" varStatus="status" >
				<tr target="good_id" rel="${good.id}" align="center">
					<td>${status.index+1} </td>
					<td><img src="${good.picpath}" style="margin:3px;margin-left:20px;width:30px;height:40px;float:left" />
					<div style="float:left;margin:3px;margin-left:20px;"><span style="font-weight:bold;">${good.code}</span>
					<br><br><span>${good.showname}</span></div></td>
					<td>${good.price_t}</td>
					<td>${good.name}</td>
					<td>${good.amount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table class="list" style="width:100%;overflow:auto" layoutH="0">
		<thead>
			<tr align="center">
				<th width="7%">序号</th>
				<th width="42%">换购商品</th>
				<th width="13%">售价</th>
				<th width="13%">标码</th>
				<th width="13%">数量</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty hqgoods}">
		    	<tr><td colspan="11"><center>没有换购商品！</center></td></tr>
		    </c:if>
			<c:forEach var="good" items="${hqgoods}" varStatus="status" >
				<tr target="good_id" rel="${good.id}" align="center">
					<td>${status.index+1} </td>
					<td><img src="${good.picpath}" style="margin:3px;margin-left:20px;width:30px;height:40px;float:left" />
					<div style="float:left;margin:3px;margin-left:20px;"><span style="font-weight:bold;">${good.code}</span>
					<br><br><span>${good.showname}</span></div></td>
					<td>${good.price_t}</td>
					<td>${good.name}</td>
					<td>${good.amount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
