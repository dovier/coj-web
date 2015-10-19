<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="pagehdr.faq"/>
</h2>
<div class="postcontent">
    <br/>
    <a href="<c:url value="/admin/addfaq.xhtml" />"><fmt:message key="page.faqs.add"/></a>

     <div id="display-table-container" data-reload-url="/admin/tables/faqs.xhtml"></div> 
</div>
<script>
	$(document).ready(displayTableReload("?page=1"));
	</script>