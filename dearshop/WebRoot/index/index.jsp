<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>   <!--使用EL语法需要导入的包 -->

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<link rel="shortcut icon" href="image/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>鸯鸯的小仓库</title>
<link href="dwz/themes/purple/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>

<script src="dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="dwz/js/jquery.bgiframe.js" type="text/javascript"></script>

<script src="dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="dwz/js/dwz.print.js" type="text/javascript"></script>
<!--
<script src="bin/dwz.min.js" type="text/javascript"></script>
-->
<script src="dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.js"></script>

<!-- 图片上传即时显示js -->
<script src="js/uploadPreview.min.js" type="text/javascript"></script>

<!-- 其他js -->
<script src="js/trim.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript" ></script>

<script type="text/javascript">
$(function(){
	DWZ.init("dwz/dwz.frag.xml", {
		loginUrl:"login/login_dialog.jsp", loginTitle:"登录",	// 弹出登录对话框
//		loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			//$("#splitBar").hide();
			$("#themeList").theme({themeBase:"dwz/themes"}); // themeBase 相对于index页面的主题base路径
		}
	});
});

</script>

</head>

<!-- 这里的div里面有class的，如果在当前页面没有定义，则其为DWZ包装的class 例如theme : 其修改可以在FrameWork/dwz/themes下进行修改 -->
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				<a class="logo" href="###" style="cursor:auto">=标志</a>   <!-- 链接到起始页  -->
				<ul class="nav">
				<!-- 这里<a></a>标志里面的链接可以使用页面地址，也可以使用方法，不过方法的参数传递不是整个页面post传递，而是需要自己手动在链接后面加例如：&id=1 -->
					<li><a href="javascript:void(0)" style="TEXT-DECORATION:none; cursor: default">欢迎您：${truename}</a></li>
					<li><a target="dialog" href="index/changepwd.jsp" width="380" height="200">修改密码</a></li>
					<li><a href="login/logout">退出</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<li theme="default"><div>蓝色</div></li>
					<li theme="purple"><div class="selected">紫色</div></li>
				</ul>
				<!-- 这里可以选择的主题都是已经有DWZ提供，如果需要添加主题，则要在FrameWork/dwz.theme里面进行添加主题 -->
			</div>
		</div>
		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse"><div></div></div>
				</div>
			</div>
			<div id="sidebar">
			<!-- 这里使用了JSTL语法，把页面代码导入到这里的DIV中来 这里带入的页面是一个导航栏的代码-->
				<div class="toggleCollapse">
					<h2>控制面板</h2>
					<div>收缩</div>
				</div>
				<jsp:include page="menu.jsp" />
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">商品查询</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div> 
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">商品查询</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<jsp:include page="main.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- html滚动字母条  -->
	<div id="footer">Copyright &copy; 2014  BY fox調調</div>
</body>
</html>