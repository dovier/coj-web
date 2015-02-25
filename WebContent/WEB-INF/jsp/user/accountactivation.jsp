<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <spring:message code="pagehdr.uaactivation"/>
</h2>
<div class="postcontent">
    <c:choose>
        <c:when test="${ok == true}">
            <div class="label label-info"><i class="fa fa-info-circle"></i>
                <spring:message code="text.uaactivation.1"/>
            </div>
        </c:when>
        <c:when test="${ok == false}">
            <div class="error">
                <spring:message code="text.uaactivation.2"/>
            </div>
        </c:when>
    </c:choose>
</div>            