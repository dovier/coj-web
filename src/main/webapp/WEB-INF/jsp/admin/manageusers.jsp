<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header" />: <fmt:message key="page.general.admin.users" />
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <form id="filter-form" class="form-inline">
        <div class="form-group coj_float_rigth">
            <input type="text" placeholder="<fmt:message key="page.general.admin.usersearch" />"
                   class="form-control" name="pattern" value="${pattern}">
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.filter"/>">
        </div>
        <div class="form-group">

        </div>
    </form>
    <div class="clearfix"></div>
    <div id="display-table-container"
         data-reload-url="/admin/tables/manageusers.xhtml"></div>
    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close" />
        </a>
    </div>
    <div class="clearfix"></div>
</div>

<script src="/js/admin/utility.js"></script>

<script>
    $(initStandardFilterForm);
</script>