<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/include.inc.jsp"%>
<form id="pagerForm" method="post" action="${controllerKey}/msg_one" >
    <input type="hidden" name="pageNum" value="1" /> 
	<input type="hidden" name="numPerPage" value="${listPage.pageSize}" />
	<input type="hidden" name="key" value="${param.key}" />
</form>
<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="ckkc" method="post">
		<div>
			<span style="margin-left:6px;float:left;line-height:23px">关键字：</span>
			<input style="float:left;margin-top:2px" type="text" name="key" id="key" value="${param.key}" />
			<div class="button" style="margin-left:6px;line-height:1.5"><div class="buttonContent"><button type="submit" >查询</button></div></div>
		</div>
		
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="ckkc/goods_add" width="560" height="530" target="dialog" rel="goods_add"><span>添加商品</span></a></li>
			<li><a class="delete" href="ckkc/goods_xj" target="selectedTodo" rel="ids" title="下架后商品清空，确定下架吗?"><span>批量下架</span></a></li>
		</ul>
	</div>
	<table class="list" style="width:100%;overflow:auto" layoutH="90">
		<thead>
			<tr align="center">
				<th width="5%">序号</th>
				<th width="3%"><input type="checkbox" group="ids" class="checkboxCtrl"/></th>
				<th width="28%">商品</th>
				<th width="7%">售价</th>
				<c:forEach var="size" items="${sizelist}" varStatus="status" >
					<th width="7%">${size.name}</th>
				</c:forEach>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty listPage.list}">
		    	<tr><td colspan="11"><center>暂时没有信息！</center></td></tr>
		    </c:if>
			<c:forEach var="pojo" items="${listPage.list}" varStatus="status" >
				<tr target="pojo_id" rel="${pojo.id}" align="center">
					<td>${status.index+1} </td>
					<td><input type="checkbox" name="ids" value="${pojo.id}"/></td>
					<td><img src="${pojo.picpath}" style="margin:3px;margin-left:20px;width:30px;height:40px;float:left" />
					<div style="float:left;margin:3px;margin-left:20px;"><span style="font-weight:bold;">${pojo.code}</span>
					<br><br><a target="dialog" width="460" height="490" href="ckkc/goods_xq?id=${pojo.id}" rel="goods_xq" ><span>${pojo.showname}</span></a></div></td>
					<td>${pojo.price}</td>
					<td>${pojo.size1}</td>
					<td>${pojo.size2}</td>
					<td>${pojo.size3}</td>
					<td>${pojo.size4}</td>
					<td>${pojo.size5}</td>
					<td>${pojo.size6}</td>
					<td><a target="dialog" href="ckkc/goods_edit?id=${pojo.id}" width="560" height="530" rel="goods_edit" ><span>修改信息</span></a>|<a href="ckkc/goods_xj?id=${pojo.id}" target="ajaxTodo" title="下架后商品清空，确定下架?"><span>下架</span></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="/common/pagination/pagination.jsp?type=navTab"></c:import>
</div>