<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <spring:message code="pagehdr.suaregistration"/>
</h2>
<div class="postcontent">
    <c:choose>
        <c:when test="${message != null}">
            <div class="label label-danger"><i class="fa fa-close"></i>
                <spring:message code="${message}"/>
            </div>
        </c:when>
        <c:otherwise>
            <div class="label label-info"><i class="fa fa-info-circle"></i>
                <spring:message code="text.suaregistration.1"/>
            </div>
        </c:otherwise>
    </c:choose>
</div>
