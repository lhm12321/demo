<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<style type="text/css">
#ckd_list td{
background:#fbf0fb;
border: 1px solid #fff;
border-bottom:1px dashed #ccc;
height:30px;
text-align: center;
}
#ckd_list td a:link {color: blue; text-decoration:none;} 
#ckd_list td a:hover{color: red; }
}
</style>
<div class="pageContent" style="padding:5px">
	<form method="post" id="f1" action="spcs/cxbh" class="pageForm required-validate" onsubmit="return false">
		<div class="panel" defH="30">
		<h1>商品编号</h1>
		<div>
			<span style="margin-left:6px;float:left;line-height:23px">商品编号：</span>
			<input style="float:left;margin-top:2px" type="text" name="code" id="code_spcs" />
			<div class="button" style="margin-left:6px;line-height:1.5"><div class="buttonContent"><button type="button" onclick="jrgwc()">加入购物车</button></div></div>
		</div>
	</div>
	</form>
	<form method="post" id="f2" action="spcs/ckd_save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="tabs" style="margin:4px">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>购物车</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
				<div layoutH="190" style="float:left;margin-left:20px;margin-top:20px;margin-right:20px;display:block;verflow:hidden;width:95%; line-height:21px;">
				    <table id="ckd_list" style="width:100%;verflow-y:scroll;">
						<tr>
							<td width="30%">商品</td>
							<td width="10%">标价</td>
							<td width="20%">尺寸</td>
							<td width="15%">售价</td>
							<td width="15%">数量</td>
							<td width="10%">操作</td>
						</tr>
				    </table>
				</div>
				<div class="button" style="margin-right:40px;margin-top:10px;line-height:1.5;float:right"><div class="buttonContent"><button type="submit" >购物结算</button></div></div>
				<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">总计金额:<span id="total" style="font-size:15px;color:red">0</span>元</div>
				<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">总计数量:<span id="sum" style="font-size:15px;color:red">0</span>件</div>
				<input type="hidden" name="total" id="total1" />
				<input type="hidden" name="sum" id="sum1" />
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	</form>
</div>
<script type="text/javascript">
var i_spcs=1;
var com = setInterval(docom_spcs,1000);
function docom_spcs(){
	var sum_price = 0;
	var sum_amount = 0;
	for(var j=1;j<i_spcs;j++){
		sum_amount = parseInt(sum_amount) + parseInt($("#"+j+"_amount",navTab.getCurrentPanel()).val());
		sum_price = parseInt(sum_price) + parseInt($("#"+j+"_price",navTab.getCurrentPanel()).val())*parseInt($("#"+j+"_amount",navTab.getCurrentPanel()).val());
		//alert(sum_amount);
		//alert(sum_price);
	}
	$("#total",navTab.getCurrentPanel()).text(sum_price);
	$("#sum",navTab.getCurrentPanel()).text(sum_amount);
	$("#total1",navTab.getCurrentPanel()).val(sum_price);
	$("#sum1",navTab.getCurrentPanel()).val(sum_amount);
}
function jrgwc(){
	var reg = /^\d+$/;
	var code = $("#code_spcs",navTab.getCurrentPanel()).val();
	if (reg.test(code)) {
			$.post("spcs/cxbh", {
				code : code
			}, function(data) {
				if (data == "null") {
					alert("无此商品！");
				} else if (data == "close") {
					alert("无此商品已下架！");
				} else if (data == "open") {
					add_spcs(code);
				}
			});
		} else {
			alert("请输入正确的编号");
		}
	}
	function add_spcs(code) {
		var rkd = $("#ckd_list",navTab.getCurrentPanel());
		$.post("spcs/good_add", {
			code : code,
			i : i_spcs
		}, function(data) {
			rkd.append(data);
		}, "text");
		i_spcs = i_spcs + 1;
	}
	function del_spcs(px) {
		$.post("spcs/good_del", {
			px : px
		}, function(data) {
			$("#" + px + "_tr",navTab.getCurrentPanel()).hide();
			$("#" + px + "_amount",navTab.getCurrentPanel()).val("0");
			$("#" + px + "_price",navTab.getCurrentPanel()).val("0");
		}, "text");
	}
</script>
