<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: Manage Countries
</h2>
<div class="postcontent">
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label>Search in name and code:</label> <input type="text"
				class="form-control" name="pattern" value="${pattern}">
		</div>
		<div class="form-group">
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>
	</form>
	
	<br /> <a href="<c:url value="/admin/addcountry.xhtml" />">Add New
		Country</a>

	<div id="display-table-container"
		data-reload-url="/admin/tables/managecountries.xhtml"></div>
</div>
<script>
$(initStandardFilterForm);
</script>