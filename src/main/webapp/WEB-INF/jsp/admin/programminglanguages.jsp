<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.general.admin.languages"/>
</h2>
<div class="postcontent"> 

    <a href="<c:url value="managelanguage.xhtml" />"><fmt:message key="page.general.admin.addlanguage"/></a>

    <table class="volume" border="1px">
        <thead class="orderby">
        <th>ID</th>
        <th><fmt:message key="tablehdr.language"/></th>
        <th><fmt:message key="tablehdr.description"/></th>
        <th><fmt:message key="tablehdr.namebin"/></th>
        <th><fmt:message key="tablehdr.key"/></th>
        <th><fmt:message key="tablehdr.enable"/></th>
        <th><fmt:message key="tablehdr.edit"/></th>
        </thead>

        <c:forEach items="${languages}" var="language">
            <tr class="<c:out value="${language.even}"/>">
                <td><c:out value="${language.lid}"/></td>
                <td><c:out value="${language.language}"/></td>
                <td><c:out value="${language.descripcion}"/></td>
                <td><c:out value="${language.name_bin}"/></td>
                <td><c:out value="${language.key}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${language.enabled == true}">
                            <span class="label label-success"><fmt:message key="page.general.yes" /></span>
                        </c:when>
                        <c:otherwise>
                            <span class="label label-danger"><fmt:message key="page.general.no" /></span>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td><a href="<c:url value="/admin/managelanguage.xhtml?lid=${language.lid}"/>">
                        <i title="<spring:message code="messages.general.edit"/>"
                           data-toggle="tooltip" class="fa fa-edit"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<script>
$("[data-toggle='tooltip']").tooltip();
</script>