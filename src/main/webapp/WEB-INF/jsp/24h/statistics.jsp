<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "32kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.24hstats"/>
</h2>
<div class="postcontent">
    <!-- article-content -->
    <table class="volume" border="1px">
        <thead>
        <th><spring:message code="tablehdr.lang"/></th>
        <th><spring:message code="tablehdr.ac"/></th>
        <th><spring:message code="tablehdr.ce"/></th>
        <th><spring:message code="tablehdr.sle"/></th>
        <th><spring:message code="tablehdr.ivf"/></th>
        <th><spring:message code="tablehdr.mle"/></th>
        <th><spring:message code="tablehdr.ole"/></th>
        <th><spring:message code="tablehdr.pe"/></th>
        <th><spring:message code="tablehdr.rte"/></th>
        <th><spring:message code="tablehdr.tle"/></th>        
        <th><spring:message code="tablehdr.wa"/></th>
        <th><spring:message code="tablehdr.total"/></th>
        </thead>
        <c:forEach items="${statistics}" var="stats">
            <tr>
                <td><c:out value="${stats.language}"/></td>
                <td><a href="<c:url value="status.xhtml?status=ac&planguage=${stats.key}"/>"><c:out value="${stats.acc}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=ce&planguage=${stats.key}"/>"><c:out value="${stats.ce}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=sle&planguage=${stats.key}"/>"><c:out value="${stats.fle}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=ivf&planguage=${stats.key}"/>"><c:out value="${stats.ivf}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=mle&planguage=${stats.key}"/>"><c:out value="${stats.mle}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=ole&planguage=${stats.key}"/>"><c:out value="${stats.ole}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=pe&planguage=${stats.key}"/>"><c:out value="${stats.pe}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=rte&planguage=${stats.key}"/>"><c:out value="${stats.rte}"/></a></td>
                <td><a href="<c:url value="status.xhtml?status=tle&planguage=${stats.key}"/>"><c:out value="${stats.tle}"/></a></td>                
                <td><a href="<c:url value="status.xhtml?status=wa&planguage=${stats.key}"/>"><c:out value="${stats.wa}"/></a></td>
                <td><a href="<c:url value="status.xhtml?planguage=${stats.key}"/>"><c:out value="${stats.total}"/></a></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="colheader"><spring:message code="tablehdr.total"/></td>
            <td><a href="<c:url value="status.xhtml?status=ac"/>"><c:out value="${stat.acc}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=ce"/>"><c:out value="${stat.ce}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=sle"/>"><c:out value="${stat.fle}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=ivf"/>"><c:out value="${stat.ivf}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=mle"/>"><c:out value="${stat.mle}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=ole"/>"><c:out value="${stat.ole}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=pe"/>"><c:out value="${stat.pe}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=rte"/>"><c:out value="${stat.rte}"/></a></td>
            <td><a href="<c:url value="status.xhtml?status=tle"/>"><c:out value="${stat.tle}"/></a></td>            
            <td><a href="<c:url value="status.xhtml?status=wa"/>"><c:out value="${stat.wa}"/></a></td>
            <td><a href="<c:url value="status.xhtml"/>"><c:out value="${stat.total}"/></a></td>
        </tr>
    </table>
    <br/>
    <img alt="<spring:message code="pagehdr.24hstats"/>" src="<c:url value="/graph/24h/statistics.xhtml"/>"/>
    <!-- /article-content -->   
</div>


