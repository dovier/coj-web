<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
<% pageContext.setAttribute("newLineChar", "\n"); %>

${fn:replace(result, newLineChar, "<br>")}
	
</div>

