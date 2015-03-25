<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	Manage Institutions
</h2>
<div class="postcontent">
	<br />
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label>Search in name and code</label> <input size="25" type="text"
				name="pattern" value="${pattern}" class="form-control" /> <input
				type="hidden" name="filter" value="${filter}" class="form-control" />
		</div>
		<div class="form-group">
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>
	</form>
	<c:choose>
		<c:when test="${search == true}">
			<label><fmt:message key="fieldhdr.totalfound" />: ${found}</label>
		</c:when>
	</c:choose>
	<br /> <a href="<c:url value="/admin/addinstitution.xhtml" />">Add
		New Institution</a>

	<div id="display-table-container"
		data-reload-url="/admin/tables/manageinstitutions.xhtml"></div>
</div>
<script>
	$(initStandardFilterForm);
</script>