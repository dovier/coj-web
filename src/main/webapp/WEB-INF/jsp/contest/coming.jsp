<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <spring:message code="pagehdr.rcscoming"/>
</h2>
<div class="postcontent">
	<div>
		<spring:message code="pagehdr.wboardlink"/>
		<a href="<c:url value="/wboard/contests.xhtml" />">COJboard</a>
	</div>	
    <div id="display-table-container" data-reload-url="/tables/coming.xhtml"></div>
</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>

