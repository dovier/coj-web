<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Users
</h2>
<div class="postcontent">
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label>Search in username, nick and email:</label> <input type="text"
				class="form-control" name="pattern" value="${pattern}">
		</div>
		<div class="form-group">
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>
	</form>
	<div id="display-table-container"
		data-reload-url="/admin/tables/manageusers.xhtml"></div>
</div>
<script>
$(initStandardFilterForm);
</script>