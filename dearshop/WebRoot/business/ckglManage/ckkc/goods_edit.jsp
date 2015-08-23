<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<form method="post" id="f1" action="ckkc/goods_save" enctype="multipart/form-data"  class="pageForm required-validate" onsubmit="return iframeCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="15">
		<input type="hidden" name="id" id="id" value="${good.Id}"/>
			<table style="margin-left:15px">
			<tr style="height:40px">
				<td><span style="line-height: 1.5;">商品编号：</span></td>
				<td colspan="3"><input type="text" style="width:125px;" name="code" id="code" class="required  digits" readonly value="${good.code}"/></td>
			</tr>
			<tr style="height:40px">
				<td><span style="line-height: 1.5;">商品名称：</span></td>
				<td><input type="text" style="width:125px" name="showname" id="showname" class="required" value="${good.showname}" /></td>
				<td><span style="line-height: 1.5;">商品价格：</span></td>
				<td><input type="text" style="width:125px" name="price" id="price" class="required digits" value="${good.price}" /></td>
			</tr>
			<tr>
				<td><span style="line-height: 1.5;">图片（240*320）：</span></td>
				<td colspan="3"><input style="float:left" type="file" name="file1" id="file1" /><div><img style="float:left" src="${good.picpath}" id="img" width="30" height="40"/></div></td>
			</tr>
			<tr style="height:40px">
				<td><span style="line-height: 1.5;">类型：</span></td>
				<td><select id="sex" name="sex" style="width:130px">
						<option value="男性" ${good.sex eq '男性' ? 'selected="selected"' : '' }>男性</option>
						<option value="女性" ${good.sex eq '女性' ? 'selected="selected"' : '' }>女性</option>
						<option value="中性" ${good.sex eq '中性' ? 'selected="selected"' : '' }>中性</option>
					</select></td>
				<td><span style="line-height: 1.5;">季节：</span></td>
				<td><select id="season_id" name="season_id" style="width:130px;">
					<c:forEach var="item" items="${seasonlist}">
						<option value="${item.id}" ${good.season_id eq item.id ? 'selected="selected"' : '' }>${item.name}</option>
					</c:forEach>
					</select></td>
			</tr>
			<tr>
				<td colspan="4"><script type="text/plain" id="remark" style="width:500px;height:250px;margin-top:10px">
					${good.remark}
					</script>
					<input type="hidden" name="remark" value="${good.remark}"></input></td>
			</tr>
			<tr style="height:30px">
				<td colspan="4"><input style="float:right" type="submit" value="保存修改"></input></td>
			</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
var editor_remark = new UE.ui.Editor();
editor_remark.render("remark");

editor_remark.ready(function () {
    var val_remark = $("[name='remark']").val();
    if(val_remark.length==0){
    	editor_remark.setContent("商品详细描述");
    }else{
    	editor_remark.setContent(val_remark);
    }
});
$(function(){
	var img = $("#img", $.pdialog.getCurrent());
	var id = $("#id", $.pdialog.getCurrent()).val();
	if(id.length==0){
		$("#code", $.pdialog.getCurrent()).removeAttr("readonly");
	}
	var reg = /^\d+$/;
	if(img.attr("src")== undefined||img.attr("src")==""){img.hide();}
	$("#file1", $.pdialog.getCurrent()).uploadPreview({ Img: "img", Width: 30, Height: 40 });
	$("#file1", $.pdialog.getCurrent()).change(function(){
		img.show();
	});
});
</script>