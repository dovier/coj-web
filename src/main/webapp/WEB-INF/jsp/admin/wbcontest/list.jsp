<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/wboard.css"/>"
	type="text/css" media="screen" />

<h2 class="postheader">
	<fmt:message key="page.header.admin.wbcontest.list" />
</h2>
<div class="postcontent">
	<a class="btn btn-primary"
		href='<c:url value="/admin/wboard/contest/create.xhtml"/>'> <spring:message
			code="pagehdr.create.contest" />
	</a> <br />

	<div id="display-table-container"
		data-reload-url="/admin/tables/wbcontests.xhtml"></div>
</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>