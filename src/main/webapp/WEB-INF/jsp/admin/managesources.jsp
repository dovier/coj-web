<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
    : <spring:message code="psource.title" />	
</h2>
<div class="postcontent">

    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 

    <form method="post" action="/admin/addsource.xhtml">
        <div class="form-group">
            <label class="control-label"><spring:message code="psource.newsource" />:</label> 
            <input class="form-control" style="width: 100%" type="text" value="" name="name" /> 
        </div>
        <div class="form-group">
            <label class="control-label"><spring:message code="psource.author" />:</label> 
            <input class="form-control" style="width: 100%" type="text" value="" name="author" />
        </div>
        <input id="filter-button" type="submit" class="btn btn-primary"
               value="<spring:message code="button.add"/>">        
    </form>
    <br />

    <div id="display-table-container"
         data-reload-url="/admin/tables/managesources.xhtml"></div>
</div>
<script>
    $(document).ready(displayTableReload(" "));
</script>
