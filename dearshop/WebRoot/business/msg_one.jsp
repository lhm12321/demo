<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${controllerKey}/msg_one" >
    <input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${listPage.pageSize}" />
	<input type="hidden" name="key" value="${param.key}" />
	<input type="hidden" name="starttime" value="${param.starttime}" />
	<input type="hidden" name="endtime" value="${param.endtime}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="${controllerKey }/msg_one" method="post">
		<input type="hidden" name="id" value="${param.id}" />
		<table align="center" style="height:15px;width:99%" border="0">
			<tr>
				<td style="width:12%"><span style="margin-left: 5px">关键字：</span></td>
				<td><input style="width:100px;margin-left: 5px" type="text" name="key" value="${param.key}"/></td>
				<td style="width:12%"><span style="margin-left: 5px">时间区：</span></td>
				<td><input style="width:100px;margin-left: 5px" type="text" name="starttime" class="date" readonly value="${param.starttime}"/>
				&nbsp;&nbsp;--&nbsp;&nbsp;
				<input style="width:100px;margin-left: 5px" type="text" name="endtime" class="date" readonly value="${param.endtime}"/>
				</td>
				<td style="width:18%"><div class="button" style="margin-left: 5px"><div class="buttonContent"><button type="submit">查询</button></div></div></td>
			</tr>
		</table>
	</form>
</div>
<div class="pageContent">
	<table class="list" style="width:100%;overflow:auto" layoutH="65">
		<thead>
			<tr>
				<th>序号</th>
				<th>发送人</th>
				<th>接受人</th>
				<th>发送内容</th>
				<th>发送时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty listPage.list}">
		    	<tr><td colspan="4"><center>暂时没有信息！</center></td></tr>
		    </c:if>
			<c:forEach var="pojo" items="${listPage.list}" varStatus="status" >
				<tr target="pojo_id" rel="${pojo.id}">
					<td>${status.index+1} </td>
					<td>${pojo.username}</td>
					<td>${pojo.trueName}</td>
					<td>${pojo.message}</td>
					<td><fmt:formatDate value="${pojo.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="/common/pagination/pagination2.jsp?type=navTab"></c:import>
</div>