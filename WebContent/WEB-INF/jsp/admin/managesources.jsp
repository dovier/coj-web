<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/jquery.js" />">
	
</script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
	<fmt:message key="psource.title" />
</h2>
<div class="postcontent">
	<form method="post" action="/admin/addsource.xhtml">
		<label><spring:message code="psource.newsource" />:</label> <input
			style="width: 100%" type="text" value="" name="name" /> <br /> <label><spring:message
				code="psource.author" />:</label> <input style="width: 100%" type="text"
			value="" name="author" /> <br /> <input type="submit"
			value="<spring:message code="button.add"/>">
	</form>
	<br />

	<div id="display-table-container"
		data-reload-url="/admin/tables/managesources.xhtml"></div>
</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>