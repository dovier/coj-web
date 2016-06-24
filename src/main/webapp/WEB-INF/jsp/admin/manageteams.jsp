<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<h2 class="postheader">
    <spring:message code="page.general.admin.header"/>:
    <spring:message code="pagehdr.teams"/>
</h2>

<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>
    <form id="filter-form" class="form-inline">
        <div class="form-group coj_float_rigth">
            <input
                    type="text" placeholder="<fmt:message key="page.general.admin.usersearch" />" name="pattern"
                    value="${pattern}" class="form-control"/>
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.filter"/>">
        </div>
        <div class="form-group coj_float_left">
            <a class="btn btn-primary" href="<c:url value="/admin/createteams.xhtml" />"><spring:message
                    code="fieldhdr.addteams"/></a>
        </div>

    </form>
    <div class="clearfix"></div>

    <div id="display-table-container" data-reload-url="tables/manageteams.xhtml"></div>

    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close"/>
        </a>
    </div>
    <div class="clearfix"></div>
</div>
<script>
    $(initStandardFilterForm);
</script>