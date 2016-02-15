<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<display:table id="user" name="users" class="volume" requestURI="">
    <display:column property="username" titleKey="tablehdr.user"
                    href="/user/useraccount.xhtml" paramId="username"
                    paramProperty="username"/>
    <display:column titleKey="tablehdr.date" headerClass="headdate">
        <c:set var="newdate" value="${fn:substring(user.rgdate, 0, 19)}"/>
        ${newdate}
    </display:column>
    <display:column titleKey="tablehdr.connected">
        <c:choose>
            <c:when test="${user.online == true}">
                <span class="label label-success"><fmt:message
                        key="page.general.yes"/></span>
            </c:when>
            <c:otherwise>
                <span class="label label-danger"><fmt:message
                        key="page.general.no"/></span>
            </c:otherwise>
        </c:choose>
    </display:column>
    <display:column property="lastip" titleKey="tablehdr.ip"/>
    <display:column titleKey="tablehdr.status">
        <label class="label bg${fn:replace(user.status,' ','')}"><fmt:message
                key="user.status.${fn:replace(user.status,' ','')}"/></label>
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <ul class="list-inline">
            <li><a
                    href="<c:url value="/admin/manageuser.xhtml?username=${user.username}" />"
            ><i title="<spring:message code="messages.general.edit"/>"
                data-toggle="tooltip" class="fa fa-edit"></i></a></li>
        </ul>
    </display:column>
</display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>                       