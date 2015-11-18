<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header" />
    : <fmt:message key="page.general.admin.globalaccessrules" />
</h2>
<div class="postcontent">    
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <form:form method="post" cssClass="form-inline" action="globalaccessrules.xhtml"
               commandName="rule">
        <div class="form-group coj_float_left">                        
            <form:input cssClass="form-control"  id="myrule" path="rule" />
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.add"/>">            
        </div>
        <div class="error col-xs-8 col-xs-offset-3">
            <span class="label label-danger"><form:errors path="rule" cssClass="error" /></span>
        </div>
    </form:form>    

    <div id="display-table-container"
         data-reload-url="/admin/tables/globalaccessrules.xhtml"></div>
</div>
<script>
    $("#myrule").attr('placeholder', '<spring:message code="page.general.admin.rule" />');
    $(document).ready(displayTableReload("?page=1"));
</script>