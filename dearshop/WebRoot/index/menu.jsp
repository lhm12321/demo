<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<!--使用EL语法需要导入的包 -->
<div class="accordion" fillSpace="sidebar">
	<div class="accordionHeader">
		<h2><span>Folder</span>基本功能</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="spcs" target="navTab" rel="spcs">商品出售</a></li>
			<li><a href="thth" target="navTab" rel="thth">退货退换</a></li>
		</ul>
	</div>
	<div class="accordionHeader">
		<h2><span>Folder</span>账单详情</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="ckd" target="navTab" rel="ckd">销售清单</a></li>
			<li><a href="thd" target="navTab" rel="thd">退货退换</a></li>
			<li><a href="rkd" target="navTab" rel="rkd">入库清单</a></li>
		</ul>
	</div>
	<div class="accordionHeader">
		<h2><span>Folder</span>仓库管理</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="ckkc" target="navTab" rel="ckkc">查看库存</a></li>
			<li><a href="sprk" target="navTab" rel="sprk">商品入库</a></li>
			<li><a href="xjsp" target="navTab" rel="xjsp">下架商品</a></li>
		</ul>
	</div>
	<div class="accordionHeader">
		<h2><span>Folder</span>资产结算</h2>
	</div>
	<div class="accordionContent">
		<ul class="tree treeFolder">
			<li><a href="dbill" target="navTab" rel="dbill">日账单详情</a></li>
			<li><a href="mbill" target="navTab" rel="mbill">月账单详情</a></li>
			<li><a href="ybill" target="navTab" rel="ybill">年账单详情</a></li>
		</ul>
	</div>
</div>