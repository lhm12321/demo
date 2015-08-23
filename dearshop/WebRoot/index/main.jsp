<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>   <!--使用EL语法需要导入的包 -->

<div class="accountInfo">
	<div class="right">
	<!-- 这里也使用JSTL语法，获取当前时间，并把他格式化 -->
		<p>今天是：<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日 E"/></p>
	</div>
	<p>商品编码：<input type="text" name="code" id="code_main" /></p>
</div>
<!-- 下面这个div是主要内容显示的DIV，里面可以添加form，table,等等一系列活动页面 -->
<div class="pageContent">

</div>
<script type="text/javascript">

function cx(){
	var reg = /^\d+$/;
	var code = $("#code_main",navTab.getCurrentPanel()).val();
	if (reg.test(code)) {
			$.post("sprk/cxbh", {
				code : code
			}, function(data) {
				if (data == "null") {
					alert("无此商品");
				} else if (data == "close") {
					alert("商品已下架");
				} else if (data == "open") {
					$.pdialog.open("ckkc/goods_xq?code=" + code+"&type=1", "goods_xq", "创建商品信息", {
						width : 500,
						height : 530,
						mask : true,
						minable : false,
						maxable : false,
						resizable : false
					});
				}
			});
		} else {
			alert("请输入正确的编号");
		}
	}
	
document.onkeydown = keyDown;

function keyDown(e) {
	if (e.which == 13) {
		if ($("#code_main",navTab.getCurrentPanel()).is(":focus") == true) {
			cx();
		}
		if ($("#code_sprk",navTab.getCurrentPanel()).is(":focus") == true) {
			sprk();
		}
		if ($("#code_spcs",navTab.getCurrentPanel()).is(":focus") == true) {
			jrgwc();
		}
	}
}
</script>

