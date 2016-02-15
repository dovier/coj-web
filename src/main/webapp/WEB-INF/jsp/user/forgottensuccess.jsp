<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>


<h2 class="postheader">
    <spring:message code="pagehdr.suprecovery"/>
</h2>

<div class="postcontent">
    <div class="notice">
        <c:choose>
            <c:when test="${change == 0}">
                <div class="alert alert-success alert-dismissable fade in">
                    <spring:message code="text.suprecovery.1"/>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success alert-dismissable fade in">
                    <spring:message code="text.suprecovery.2"/>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</div>
