<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader" style="clear: both">
    <fmt:message key="virtual.header"/>: <fmt:message key="page.menu.24h.statistics"/>
</h2>
<div class="postcontent">
    <table class="volume" border="1px">
        <thead>
        <th><fmt:message key="status.table.lang"/></th>
        <th>AC</th>
        <th>CE</th>
        <th>FLE</th>
        <th>IVF</th>
        <th>MLE</th>
        <th>OLE</th>
        <th>PE</th>
        <th>RTE</th>
        <th>TLE</th>        
        <th>WA</th>
        <th><fmt:message key="statistics.total"/></th>
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
            <td><fmt:message key="statistics.total"/></td>
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
</div>            