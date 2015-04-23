<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="vcontestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.problems"/>
</h2>
<div class="postcontent">
    <div id="display-table-container" data-reload-url="/tables/vproblems.xhtml"></div> 
</div>
<script>
$(document).ready(displayTableReload(" "));
</script>