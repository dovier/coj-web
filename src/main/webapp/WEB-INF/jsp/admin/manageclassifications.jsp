<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>-->

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
    : <spring:message code="pclassifi.title" />    
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <form:form  method="post" cssClass="form-inline" action="/admin/addclassifications.xhtml">
        <div class="form-group">
            <label><spring:message code="pclassifi.newclassification"/>:</label>       
            <input class="form-control" type="text" placeholder="<spring:message code="pclassifi.newclassification"/>" name="name"/>            
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.add"/>"> 
        </div>
    </form:form>
    <br />

    <div id="display-table-container" data-reload-url="/admin/tables/manageclassifications.xhtml"></div>

</div>
<script>
    $(document).ready(displayTableReload(""));
</script>





