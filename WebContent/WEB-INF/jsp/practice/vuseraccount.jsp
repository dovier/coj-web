<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader" style="clear: both">
    <fmt:message key="virtual.header"/>:

    User Account
</h2>
<div class="postcontent">
    <%
                String uid = request.getParameter("uid");
    %>
    <table width="100%" class="userinfo">
        <tr>
            <td><fmt:message key="page.24h.useraccount.user"/></td> <td> <c:out value="${user.username}"/> </td>
        </tr>
        <tr>
            <td><fmt:message key="page.24h.useraccount.country"/></td>
            <td> <img src=<c:url value="/images/countries/${user.country}.png"/> alt="${user.country_desc}" title="<c:url value="${user.country_desc}"/>" /> </td>
        </tr>
        <tr>
            <td><fmt:message key="page.24h.useraccount.inst"/></td>
            <td> <img src=<c:url value="/images/school/${user.institution}.png"/> alt="${user.institution_desc}" title="<c:url value="${user.institution_desc}"/>" /> </td>
        </tr>
    </table>
    <br/>

    <table class="volume" border="1px">
        <thead>
        <th>TOTAL</th>
        <th>ACC</th>
        <th>CE</th>
        <th>FLE</th>
        <th>IVF</th>
        <th>MLE</th>
        <th>OLE</th>
        <th>PE</th>
        <th>RTE</th>
        <th>TLE</th>
        <th>UQ</th>
        <th>WA</th>
        </thead>
        <tr>
            <td><c:out value="${user.total}"/></td>
            <td><c:out value="${user.acc}"/></td>
            <td><c:out value="${user.ce}"/></td>
            <td><c:out value="${user.fle}"/></td>
            <td><c:out value="${user.ivf}"/></td>
            <td><c:out value="${user.mle}"/></td>
            <td><c:out value="${user.ole}"/></td>
            <td><c:out value="${user.pe}"/></td>
            <td><c:out value="${user.rte}"/></td>
            <td><c:out value="${user.tle}"/></td>
            <td><c:out value="${user.uq}"/></td>
            <td><c:out value="${user.wa}"/></td>
        </tr>
    </table>
    <h3><fmt:message key="page.24h.useraccount.solved"/></h3>
    <div class="plistACC">
        <c:forEach items="${solved}" var="problem">
            <a href="vstatus.xhtml?username=<%=uid%>&pid=${problem.pid}&status=ac" title="${problem.title}">${problem.pid}</a>
        </c:forEach>
    </div>
    <h3><fmt:message key="page.24h.useraccount.unsolved"/></h3>
    <div class="plistWA">
        <c:forEach items="${unsolved}" var="problem">
            <a href="vstatus.xhtml?username=<%=uid%>&pid=${problem.pid}" title="${problem.title}">${problem.pid}</a>
        </c:forEach>
    </div>

</div>