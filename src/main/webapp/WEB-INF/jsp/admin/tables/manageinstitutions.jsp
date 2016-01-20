<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="institution" name="institutions" class="volume"
               requestURI=""
               decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
    <display:column property="id" titleKey="tablehdr.id"
                    headerClass="headid" />
    <display:column titleKey="tablehdr.country" headerClass="headcountry">
        <img src="/images/countries/<c:url value="${institution.czip}"/>.png"data-toggle="tooltip"
             title="<c:url value="${institution.cname}"/>"
             alt="<c:url value="${institution.czip}"/>" />
    </display:column>
    <display:column property="zip" titleKey="tablehdr.code" />
    <display:column property="name" titleKey="tablehdr.name"
                    href="/24h/usersinstitutionrank.xhtml" paramId="inst_id"
                    paramProperty="id" />
    <display:column titleKey="tablehdr.enabled">
        <c:choose>
            <c:when test="${country.enabled == true}">
                <span class="label label-success"><fmt:message
                        key="page.general.yes" /></span>
                </c:when>
                <c:otherwise>
                <span class="label label-danger"><fmt:message
                        key="page.general.no" /></span>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.actions">
        <a
            href="<c:url value="/admin/manageinstitution.xhtml?inst_id=${institution.id}"/>"
            >
            <i title="<spring:message code="messages.general.edit"/>"
               data-toggle="tooltip" class="fa fa-edit"></i></a>
        </display:column>
    </display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>