<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageHeader">
	<form id="f1"  onsubmit="return navTabSearch(this);" action="dbill" method="post">
		<div>
			<select style="float:left;margin-top:2px" id="stime" name="stime" onchange="go()" >
			<c:forEach var="date" items="${datelist}" varStatus="status" >
				<option value="${date.value}" ${date.value eq param.stime ? 'selected="selected"' : '' }>${date.name}</option>
			</c:forEach>
			</select>
		</div>
		
	</form>
</div>
<div class="pageContent">
	<table class="list" style="width:100%;overflow:auto" layoutH="80">
		<thead>
			<tr align="center">
				<th width="7%">序号</th>
				<th width="10%">备注</th>
				<th width="36%">商品</th>
				<th width="10%">售价(入库价)</th>
				<th width="10%">标码</th>
				<th width="10%">数量</th>
				<th width="17%">时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:if test="${empty dbill}">
		    	<tr><td colspan="11"><center>没有记录！</center></td></tr>
		    </c:if>
			<c:forEach var="good" items="${dbill}" varStatus="status" >
				<tr align="center">
					<td>${status.index+1} </td>
					<td>${good.bz}</td>
					<td><img src="${good.picpath}" style="margin:3px;margin-left:20px;width:30px;height:40px;float:left" />
					<div style="float:left;margin:3px;margin-left:20px;"><span style="font-weight:bold;">${good.code}</span>
					<br><br><span>${good.showname}</span></div></td>
					<td>${good.price}</td>
					<td>${good.name}</td>
					<td>${good.amount}</td>
					<td>${good.addtime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">支出:<span id="sum" style="font-size:15px;color:red">${rk_total}</span>元</div>
	<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">收入:<span id="total" style="font-size:15px;color:red">${cs_total}</span>元</div>
	<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">入库量:<span id="sum" style="font-size:15px;color:red">${rk_sum}</span>件</div>
	<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">销售量:<span id="total" style="font-size:15px;color:red">${cs_sum}</span>件</div>
</div>
<script type="text/javascript">
function go(){
	$("#f1",navTab.getCurrentPanel()).submit();
}
</script>
