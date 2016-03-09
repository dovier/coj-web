<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<h2 class="postheader">
    <spring:message code="page.general.admin.header"/>: <spring:message code="page.24h.problems.header"/>
</h2>

<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>
    <form id="filter-form" class="form-inline">
        <div id="form-group">
            <div class="coj_float_left">
                <a class="btn btn-primary" href="<c:url value="/admin/manageproblem.xhtml" />"><spring:message
                        code="link.addproblem"/></a>
            </div>
            <div class="coj_float_rigth">

                <input placeholder="<fmt:message key="page.24h.problemsearch.search" />" type="text"
                       class="form-control" name="pattern"
                       value="${pattern}"/>
                <input id="filter-button" type="submit" class="btn btn-primary"
                       value="<spring:message code="button.filter"/>"/>
            </div>

        </div>
    </form>

    <div class="clearfix"></div>
    <div id="display-table-container"
         data-reload-url="/admin/tables/adminproblems.xhtml"></div>
    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close"/>
        </a>
    </div>
    <div class="clearfix"></div>
</div>

<script src="/js/admin/utility.js"></script>

<script>
    $(initStandardFilterForm);
</script>