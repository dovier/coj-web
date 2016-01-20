<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
    : <spring:message code="pagehdr.alogs" />
</h2>
<div class="postcontent row">

    <form id="filter-form" method="get" class="form-inline">

        <div class="form-group coj_float_rigth">
            <input placeholder="<spring:message code="filedhdr.searchcontent" />"
                   type="text" name="pattern" value="${pattern}" class="form-control" />
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.filter"/>">
        </div>
        <div class="form-group">

        </div>

    </form>

    <div id="display-table-container"
         data-reload-url="/admin/tables/listlog.xhtml"></div>

</div>
<script>
    $(initStandardFilterForm);
</script>