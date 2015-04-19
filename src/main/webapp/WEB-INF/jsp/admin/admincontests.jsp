<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<spring:message code="pagehdr.amcontests" />
</h2>
<div class="postcontent">
	<a href="<c:url value="/admin/createcontest.xhtml" />"><spring:message
			code="link.aaddcontest" /></a>

	<div id="display-table-container" data-reload-url="/admin/tables/admincontests.xhtml"></div>
</div>
<script>
	$(document).ready(displayTableReload("?page=1"));
</script>











