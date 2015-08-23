<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/include.inc.jsp"%>
<form id="pagerForm" method="post" action="thd" >
    <input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${listPage.pageSize}" />
	<input type="hidden" name="starttime" value="${param.starttime}" />
	<input type="hidden" name="endtime" value="${param.endtime}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="thd" method="post">
		<div>
			<span style="margin-left:6px;float:left;line-height:23px">时间区间：</span>
			<input style="float:left;margin-top:2px" type="text" name="starttime" class="date" readonly id="starttime" value="${param.starttime}" />
			<span style="float:left;line-height:23px">&nbsp;&nbsp;--&nbsp;&nbsp;</span>
			<input style="float:left;margin-top:2px;" type="text" name="endtime" class="date" readonly id="endtime" value="${param.endtime}"/>
			<div class="button" style="margin-left:6px;line-height:1.5"><div class="buttonContent"><button type="submit" >查询</button></div></div>
		</div>
		
	</form>
</div>
<div class="pageContent">
	<table class="list" style="width:100%;overflow:auto" layoutH="63">
		<thead>
			<tr align="center">
				<th width="10%">序号</th>
				<th width="40%">退换理由</th>
				<th width="15%">退换详情</th>
				<th width="15%">退换资金结算</th>
				<th width="20%">退换时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty listPage.list}">
		    	<tr><td colspan="11"><center>暂时没有信息！</center></td></tr>
		    </c:if>
			<c:forEach var="pojo" items="${listPage.list}" varStatus="status" >
				<tr target="pojo_id" rel="${pojo.id}" align="center">
					<td>${status.index+1} </td>
					<td>${pojo.name} </td>
					<td><a target="dialog" width="500" height="400" href="thd/thd_xq?id=${pojo.id}" rel="thd_xq" title="详细" ><span>详情</span></a></td>
					<td>${pojo.total}</td>
					<td><fmt:formatDate value="${pojo.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="/common/pagination/pagination.jsp?type=navTab"></c:import>
</div>