<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.services"/>
</h2>
<div class="postcontent">
    <spring:message code="text.services.1" />
    <br/>
    <ul>
        <li>
            ProblemService <a href="<c:url value="/ProblemService?wsdl"/>">WSDL</a>
        </li>
        <li>
            UserService <a href="<c:url value="/UserService?wsdl"/>">WSDL</a>
        </li>
    </ul>

     


</div>
