<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<div class="postcontent">
<h3><spring:message code="maintenance.message" /></h3>
    Sessions remaining: ${sessions}
</div>



