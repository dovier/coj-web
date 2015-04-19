<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.tos"/>
</h2>
<div class="postcontent">
    <!-- article-content -->
    <p>
        <spring:message code="text.tos.1"/>
    </p>
    <ol>
        <li><spring:message code="text.tos.2"/></li>        
        <li><spring:message code="text.tos.3"/></li>
        <li><spring:message code="text.tos.4"/></li>
        <li><spring:message code="text.tos.5"/></li>
        <li><spring:message code="text.tos.6"/></li>
        <li><spring:message code="text.tos.7"/></li>
        <li><spring:message code="text.tos.8"/></li>
        <li><spring:message code="text.tos.9"/></li>
    </ol>
    <p>
        <spring:message code="text.tos.10"/>
        <br/>
        <spring:message code="text.tos.11"/>
    </p>   
    <!-- /article-content -->
</div>
