<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>


<h2 class="postheader">
	<spring:message code="pagehdr.pcsmylist" />
</h2>
<div class="postcontent">

	<div id="display-table-container"
		data-reload-url="/tables/mylist.xhtml"></div>

</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>