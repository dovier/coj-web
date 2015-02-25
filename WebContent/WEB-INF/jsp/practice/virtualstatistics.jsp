<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader" style="clear: both">
    <spring:message code="pagehdr.vcstatistics"/>
</h2>
<div class="postcontent">
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
                <td><c:out value="${stats.acc}"/></td>
                <td><c:out value="${stats.ce}"/></td>
                <td><c:out value="${stats.fle}"/></td>
                <td><c:out value="${stats.ivf}"/></td>
                <td><c:out value="${stats.mle}"/></td>
                <td><c:out value="${stats.ole}"/></td>
                <td><c:out value="${stats.pe}"/></td>
                <td><c:out value="${stats.rte}"/></td>
                <td><c:out value="${stats.tle}"/></td>                
                <td><c:out value="${stats.wa}"/></td>
                <td><c:out value="${stats.total}"/></td>
            </tr>
        </c:forEach>
        <tr>
            <td class="colheader"><spring:message code="tablehdr.total"/></td>
            <td><c:out value="${stat.acc}"/></td>
            <td><c:out value="${stat.ce}"/></td>
            <td><c:out value="${stat.fle}"/></td>
            <td><c:out value="${stat.ivf}"/></td>
            <td><c:out value="${stat.mle}"/></td>
            <td><c:out value="${stat.ole}"/></td>
            <td><c:out value="${stat.pe}"/></td>
            <td><c:out value="${stat.rte}"/></td>
            <td><c:out value="${stat.tle}"/></td>            
            <td><c:out value="${stat.wa}"/></td>
            <td><c:out value="${stat.total}"/></td>
        </tr>
    </table>
    <br/>
   <!--  <img alt="<spring:message code="pagehdr.vcstatistics"/>" src="<c:url value="/graph/practice/statistics.xhtml"/>"/>  -->
</div>            