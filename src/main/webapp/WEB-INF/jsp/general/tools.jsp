<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

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
        <%--<li>
            <a href="/services/specification.xhtml"><spring:message code="text.tools.2"/></a>
        </li>--%>
        <li>
            <a href="<c:url value="/wboard/contests.xhtml"/>">COJboard</a>
        </li>
        <li>
            <a href="/poll/list.xhtml"><spring:message code="text.tools.3"/></a>
        </li>
        <li>
            <a href="/downloads/COJ_Manual_Usuario.pdf"><spring:message code="text.tools.5"/></a>
        </li>
        <li>
            <a href="<c:url value="/general/mapsite.xhtml"/>"><spring:message code="text.tools.6"/></a>
        </li>
        <authz:authorize ifAnyGranted="ROLE_ADMIN,ROLE_COACH">
            <li>
                <a href="/teamanalyzer/main.xhtml"><spring:message code="text.tools.7"/></a>
            </li>
        </authz:authorize>
    </ul>
    <!-- /article-content -->
</div>
