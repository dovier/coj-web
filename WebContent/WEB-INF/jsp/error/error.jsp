<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <c:choose>
        <c:when test="${error eq '1'}">
            <spring:message code="page.error.overload"/>
        </c:when>
        <c:when test="${error eq '2'}">
            <spring:message code="page.error.unavailable"/>
        </c:when>
        <c:when test="${error eq '3'}">
            Refresh a page many times can provoke overload on the server.Please wait a few minutes and try again.
        </c:when>
        <c:when test="${error eq '4'}">
            Your user account is disabled. 
        </c:when>
        <c:when test="${error eq '6'}">
            MaxUpload Size Exceeded
        </c:when>
        <c:when test="${error eq '7'}">
            User account doesn't exist.
        </c:when>
        <c:when test="${error eq '8'}">
            Account activation error.
        </c:when>
        <c:otherwise>
            <spring:message code="page.error.generic"/>
        </c:otherwise>
    </c:choose>    
</h2>
<div class="postcontent">

</div>