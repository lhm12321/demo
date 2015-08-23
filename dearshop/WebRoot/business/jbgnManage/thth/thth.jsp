<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<style type="text/css">
#hq_list td{
background:#fbf0fb;
border: 1px solid #fff;
border-bottom:1px dashed #ccc;
height:30px;
text-align: center;
}
#hq_list td a:link {color: blue; text-decoration:none;} 
#hq_list td a:hover{color: red; }
#th_list td{
background:#fbf0fb;
border: 1px solid #fff;
border-bottom:1px dashed #ccc;
height:30px;
text-align: center;
}
#th_list td a:link {color: blue; text-decoration:none;} 
#th_list td a:hover{color: red; }
}
</style>
<div class="pageContent" style="padding:5px">
	<form method="post" id="f1" action="thth/cxbh" class="pageForm required-validate" onsubmit="return false">
		<div class="panel" defH="30">
		<h1>商品编号</h1>
		<div>
			<span style="margin-left:6px;float:left;line-height:23px">商品编号：</span>
			<input style="float:left;margin-top:2px" type="text" name="code" id="code" />
			<div class="button" style="margin-left:6px;line-height:1.5"><div class="buttonContent"><button type="button" onclick="th()">退货</button></div></div>
			<div class="button" style="margin-left:6px;line-height:1.5"><div class="buttonContent"><button type="button" onclick="hq()">换取</button></div></div>
		</div>
	</div>
	</form>
	<form method="post" id="f2" action="thth/hqd_save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="tabs" style="margin:4px">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>退换清单</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
				<div layoutH="380" style="float:left;margin-left:20px;margin-top:20px;margin-right:20px;display:block;verflow:hidden;width:95%; line-height:21px;">
				    <table id="th_list" style="width:100%;verflow-y:scroll;">
						<tr>
							<td width="35%">退货商品</td>
							<td width="10%">标价</td>
							<td width="15%">尺寸</td>
							<td width="15%">售价</td>
							<td width="15%">数量</td>
							<td width="10%">操作</td>
						</tr>
				    </table>
				</div>
				<div layoutH="380" style="float:left;margin-left:20px;margin-top:20px;margin-right:20px;display:block;verflow:hidden;width:95%; line-height:21px;">
				    <table id="hq_list" style="width:100%;verflow-y:scroll;">
						<tr>
							<td width="35%">换取商品</td>
							<td width="10%">标价</td>
							<td width="15%">尺寸</td>
							<td width="15%">售价</td>
							<td width="15%">数量</td>
							<td width="10%">操作</td>
						</tr>
				    </table>
				</div>
				<div class="button" style="margin-right:40px;margin-top:10px;line-height:1.5;float:right"><div class="buttonContent"><button type="submit" >退换结算</button></div></div>
				<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">结算金额:<input type="text" id="total" class="required" name="total" style="font-size:15px;color:red;width:70px" />元<span id="total1" style="font-size:15px;color:red;"></span></div>
				<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">退换原因:<input type="text" class="required" name="name" style="font-size:15px;color:red;width:300px" /></div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	</form>
</div>
<script type="text/javascript">
	var i_th = 1;
	var i_hq = 1;
	var com = setInterval(docom_thth, 1000);
	function docom_thth() {
		var sum_price = 0;
		for ( var j = 1; j < i_hq; j++) {
			sum_price = parseInt(sum_price) + parseInt($("#" + j + "_hqprice", navTab.getCurrentPanel()).val()) * parseInt($("#" + j + "_hqamount", navTab.getCurrentPanel()).val());
		}
		for ( var j = 1; j < i_th; j++) {
			sum_price = parseInt(sum_price) - parseInt($("#" + j + "_thprice", navTab.getCurrentPanel()).val()) * parseInt($("#" + j + "_thamount", navTab.getCurrentPanel()).val());
		}
		$("#total1", navTab.getCurrentPanel()).text("(预估"+sum_price+"元)");
	}
	function th() {
		var reg = /^\d+$/;
		var code = $("#code", navTab.getCurrentPanel()).val();
		if (reg.test(code)) {
			$.post("spcs/cxbh", {
				code : code
			}, function(data) {
				if (data == "null") {
					alert("无此商品！");
				} else if (data == "close") {
					alert("无此商品已下架！");
				} else if (data == "open") {
					add_th(code);
				}
			});
		} else {
			alert("请输入正确的编号");
		}
	}
	function add_th(code) {
		var thd = $("#th_list", navTab.getCurrentPanel());
		$.post("thth/th_add", {
			code : code,
			i : i_th
		}, function(data) {
			thd.append(data);
		}, "text");
		i_th = i_th + 1;
	}
	function del_th(px) {
		$.post("spcs/th_del", {
			px : px
		}, function(data) {
			$("#" + px + "_thtr", navTab.getCurrentPanel()).hide();
			$("#" + px + "_thamount", navTab.getCurrentPanel()).val("0");
			$("#" + px + "_thprice", navTab.getCurrentPanel()).val("0");
		}, "text");
	}

	function hq() {
		var reg = /^\d+$/;
		var code = $("#code", navTab.getCurrentPanel()).val();
		if (reg.test(code)) {
			$.post("spcs/cxbh", {
				code : code
			}, function(data) {
				if (data == "null") {
					alert("无此商品！");
				} else if (data == "close") {
					alert("无此商品已下架！");
				} else if (data == "open") {
					add_hq(code);
				}
			});
		} else {
			alert("请输入正确的编号");
		}
	}
	function add_hq(code) {
		var thd = $("#hq_list", navTab.getCurrentPanel());
		$.post("thth/hq_add", {
			code : code,
			i : i_hq
		}, function(data) {
			thd.append(data);
		}, "text");
		i_hq = i_hq + 1;
	}
	function del_hq(px) {
		$.post("spcs/hq_del", {
			px : px
		}, function(data) {
			$("#" + px + "_hqtr", navTab.getCurrentPanel()).hide();
			$("#" + px + "_hqamount", navTab.getCurrentPanel()).val("0");
			$("#" + px + "_hqprice", navTab.getCurrentPanel()).val("0");
		}, "text");
	}
</script>
