<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<style type="text/css">
#rkd_list td{
background:#fbf0fb;
border: 1px solid #fff;
border-bottom:1px dashed #ccc;
height:30px;
text-align: center;
}
#rkd_list td a:link {color: blue; text-decoration:none;} 
#rkd_list td a:hover{color: red; }
}
</style>
<div class="pageContent" style="padding:5px">
	<form method="post" id="f1" action="sprk/cxbh" class="pageForm required-validate" onsubmit="return false">
		<div class="panel" defH="30">
		<h1>商品入库编号</h1>
		<div>
			<span style="margin-left:6px;float:left;line-height:23px">商品编号：</span>
			<input style="float:left;margin-top:2px" type="text" name="code" id="code_sprk" />
			<div class="button" style="margin-left:6px;line-height:1.5"><div class="buttonContent"><button type="button" onclick="sprk()">入库</button></div></div>
		</div>
	</div>
	</form>
	<form method="post" id="f2" action="sprk/rkd_save" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="tabs" style="margin:4px">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>商品入库列表</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
				<div align="center" style="font-size:15px;margin:10px">入库单名称<input type="text" name="rkd_name" style="width:100px" class="required" /></div>
				<div layoutH="210" style="float:left;margin-left:20px;margin-right:20px;display:block;verflow:hidden;width:95%; line-height:21px;">
				    <table id="rkd_list" style="width:100%;verflow-y:scroll;">
						<tr>
							<td width="40%">商品</td>
							<td width="10%">标价</td>
							<td width="10%">尺寸</td>
							<td width="15%">进货价</td>
							<td width="15%">数量</td>
							<td width="10%">操作</td>
						</tr>
				    </table>
				</div>
				<div class="button" style="margin-right:40px;margin-top:10px;line-height:1.5;float:right"><div class="buttonContent"><button type="submit" >确认入库</button></div></div>
				<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">总计金额:<span id="total" style="font-size:15px;color:red">0</span>元</div>
				<div style="float:right;margin-right:30px;margin-top:10px;line-height:25px;font-size:15px">总计数量:<span id="sum" style="font-size:15px;color:red">0</span>件</div>
				<input type="hidden" name="total" id="total1"/>
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
var i_sprk=1;
var com = setInterval(docom_sprk,1000);
function docom_sprk(){
	var sum_price = 0;
	var sum_amount = 0;
	for(var j=1;j<i_sprk;j++){
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
function sprk(){
	var reg = /^\d+$/;
	var code = $("#code_sprk",navTab.getCurrentPanel()).val();
	if (reg.test(code)) {
			$.post("sprk/cxbh", {
				code : code
			}, function(data) {
				if (data == "null") {
					if (confirm("仓库无此商品,是否创建?") == true) {
						$.pdialog.open("sprk/goods_add?code=" + code, "cgoods", "创建商品信息", {
							width : 560,
							height : 530,
							mask : true,
							minable : false,
							maxable : false,
							resizable : false
						});
					}
				} else if (data == "close") {
					if (confirm("商品已下架，是否重新上架?") == true) {
						$.post("sprk/goods_sj", {
							code : code
						}, function(data) {
							add(code);
						});
					}
				} else if (data == "open") {
					add_sprk(code);
				}
			});
		} else {
			alert("请输入正确的编号");
		}
	}
	function add_sprk(code) {
		var rkd = $("#rkd_list",navTab.getCurrentPanel());
		$.post("sprk/good_add", {
			code : code,
			i : i_sprk
		}, function(data) {
			rkd.append(data);
		}, "text");
		i_sprk = i_sprk + 1;
	}
	function del_sprk(px) {
		$.post("sprk/good_del", {
			px : px
		}, function(data) {
			$("#" + px + "_tr",navTab.getCurrentPanel()).hide();
			$("#" + px + "_amount",navTab.getCurrentPanel()).val("0");
			$("#" + px + "_price",navTab.getCurrentPanel()).val("0");
		}, "text");
	}
</script>
