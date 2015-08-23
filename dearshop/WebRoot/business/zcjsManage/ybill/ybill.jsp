<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageHeader">
	<form id="f1"  onsubmit="return navTabSearch(this);" action="ybill" method="post">
		<div>
			<select style="float:left;margin-top:2px" id="stime" name="stime" onchange="go()" >
				<option value="2014" ${'2014' eq param.stime ? 'selected="selected"' : '' }>2014</option>
				<option value="2015" ${'2015' eq param.stime ? 'selected="selected"' : '' }>2015</option>
				<option value="2016" ${'2016' eq param.stime ? 'selected="selected"' : '' }>2016</option>
				<option value="2017" ${'2017' eq param.stime ? 'selected="selected"' : '' }>2017</option>
				<option value="2018" ${'2018' eq param.stime ? 'selected="selected"' : '' }>2018</option>
			</select>
		</div>
		
	</form>
</div>
<div class="pageContent">
	<table class="list" style="width:100%;overflow:auto" layoutH="80">
		<thead>
			<tr align="center">
				<th width="10%">序号</th>
				<th width="10%">月份</th>
				<th width="10%">销售单</th>
				<th width="10%">入库单</th>
				<th width="10%">退换单</th>
				<th width="10%">销售量</th>
				<th width="10%">入库量</th>
				<th width="10%">收入</th>
				<th width="10%">支出</th>
				<th width="10%">盈利</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tbill" items="${tbilllist}" varStatus="status" >
				<tr align="center">
					<td>${status.index+1} </td>
					<td>${tbill.month}</td>
					<td>${tbill.ckd}</td>
					<td>${tbill.rkd}</td>
					<td>${tbill.thd}</td>
					<td>${tbill.cs_sum}</td>
					<td>${tbill.rk_sum}</td>
					<td>${tbill.cs_total}</td>
					<td>${tbill.rk_total}</td>
					<td>${tbill.yl}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">盈利:<span id="sum" style="font-size:15px;color:red">${yl}</span>元</div>
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
