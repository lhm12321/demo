<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %><%@ include file="/common/include.inc.jsp"%>
<div class="panelBar">
	<div class="pages">
		<span>每页</span>
		<select class="combox" name="numPerPage" onchange="${param.type }PageBreak({numPerPage:this.value})">
			<option value="10" ${listPage.pageSize eq '10' ? 'selected="selected"' : ''}>10</option>
			<option value="20" ${listPage.pageSize eq '20' ? 'selected="selected"' : ''}>20</option>
			<option value="50" ${listPage.pageSize eq '50' ? 'selected="selected"' : ''}>50</option>
			<option value="100" ${listPage.pageSize eq '100' ? 'selected="selected"' : ''}>100</option>
			<option value="200" ${listPage.pageSize eq '200' ? 'selected="selected"' : ''}>200</option>
		</select>
		<span>条，共${listPage.totalRow}条记录，每页${listPage.pageSize}条，当前第${listPage.pageNumber}/${listPage.totalPage}页</span>
	</div>
	
	<div class="pagination" targetType="${param.type }" totalCount="${listPage.totalRow}" numPerPage="${listPage.pageSize}" pageNumShown="10" currentPage="${listPage.pageNumber}"></div>
</div>
