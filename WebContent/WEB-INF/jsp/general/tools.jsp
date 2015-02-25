<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.tools"/>
</h2>
<div class="postcontent">
    <!-- article-content -->

    <ul>
    <!-- 
        <li>
            <a href="/datagen/datasets.xhtml?mode=customsol"><spring:message code="text.tools.1"/></a>
        </li>
         -->
        <li>
            <a href="/services/specification.xhtml"><spring:message code="text.tools.2"/></a>
        </li>
        <li>
            <a href="<c:url value="/wboard/contests.xhtml"/>">Web board</a>
        </li>
    </ul>
    <!-- /article-content -->
</div>
