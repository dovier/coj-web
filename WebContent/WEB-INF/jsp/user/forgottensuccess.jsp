<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <spring:message code="pagehdr.suprecovery"/>
</h2>
<div class="postcontent">
    <div class="notice">
        <c:choose>
            <c:when test="${change == 0}">
                <spring:message code="text.suprecovery.1"/>
            </c:when>
            <c:otherwise>
                <spring:message code="text.suprecovery.2"/>
            </c:otherwise>
        </c:choose>
        
    </div>
</div>
