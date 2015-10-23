<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header" />
    : <fmt:message key="page.general.admin.globalaccessrules" />
</h2>
<div class="postcontent">
    <form:form method="post" cssClass="form-inline" action="globalaccessrules.xhtml"
               commandName="rule">
        <div class="form-group">
            <label class="control-label"><fmt:message key="page.general.admin.rule" /></label>            
            <form:input cssClass="form-control" path="rule"/>
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.add"/>">            
        </div>
        <div class="error col-xs-8 col-xs-offset-3">
            <span class="label label-danger"><form:errors path="rule" cssClass="error" /></span>
        </div>
    </form:form>
    <br />

    <div id="display-table-container"
         data-reload-url="/admin/tables/globalaccessrules.xhtml"></div>
</div>
<script>
    $(document).ready(displayTableReload("?page=1"));
</script>