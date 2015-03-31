<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Teams
</h2>
<div class="postcontent">
    <form id="filter-form" class="form-inline">
			<div class="form-group">
				<label><spring:message code="fieldhdr.searchusers" />:</label> <input
					type="text" name="pattern" value="${pattern}" class="form-control" /> 
			</div>
			<div class="form-group">	
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>

	</form>
    
    <a href="<c:url value="/admin/createteams.xhtml" />">Add Teams</a>
     <div id="display-table-container" data-reload-url="tables/manageteams.xhtml"></div> 
</div>
<script>
$(initStandardFilterForm);
</script>