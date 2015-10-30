<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<spring:message code="page.general.admin.header" />
	:
	<spring:message code="page.24h.problems.header" />
</h2>
<div class="postcontent">
	<br />
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label><fmt:message key="page.24h.problemsearch.search" />:</label>
			<input type="text" class="form-control" name="pattern"
				value="${pattern}">
		</div>
		<div class="form-group">
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>
	</form>
	<br /> <a href="<c:url value="/admin/manageproblem.xhtml" />">Add New
		Problem</a>

	<div id="display-table-container"
		data-reload-url="/admin/tables/adminproblems.xhtml"></div>
</div>
<script>
$(initStandardFilterForm);
</script>