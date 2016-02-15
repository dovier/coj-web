<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header" />: <fmt:message key="page.general.admin.manageinstitutions" />
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if>     
    <div>
        <form id="filter-form" class="form-inline"
              action="manageinstitutions.xhtml">
            <div class="form-group coj_float_left">
                <a href="<c:url value="/admin/addinstitution.xhtml" />" class="btn btn-primary"><spring:message code="page.general.admin.manageinstitution.addnewinstitution"/></a>
            </div>
            <div class="form-group coj_float_rigth">                
                <input type="text" placeholder="<fmt:message key="page.general.admin.search" />"
                       class="form-control" name="pattern" value="${pattern}">
                <input id="filter-button" type="submit" class="btn btn-primary"
                       value="<spring:message code="button.filter"/>">
            </div>

        </form>
    </div>
    <c:choose>
        <c:when test="${search == true}">
            <label><fmt:message key="fieldhdr.totalfound" />: ${found}</label>
        </c:when>
    </c:choose>
    <br /> 

    <div id="display-table-container"
         data-reload-url="/admin/tables/manageinstitutions.xhtml"></div>
</div>
<script>
    $(initStandardFilterForm);
</script>