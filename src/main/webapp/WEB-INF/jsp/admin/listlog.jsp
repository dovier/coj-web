<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<spring:message code="pagehdr.alogs" />
</h2>
<div class="postcontent">
	<br />
	<form id="filter-form" class="form-inline">

		<div class="form-group">
			<label><spring:message code="fieldhdr.searchusers" />:</label> <input
				type="text" name="pattern" value="${pattern}" class="form-control" />
			<input type="hidden" name="filter" value="${filter}"
				class="form-control" />
		</div>
		<div class="form-group">
			<select name="online" class="form-control">
				<option value="false"><spring:message code="link.all" /></option>
				<option value="true"><spring:message code="link.logged" /></option>
			</select>
		</div>
		<div class="form-group">
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>

	</form>
	<div id="display-table-container"
		data-reload-url="/admin/tables/listlog.xhtml"></div>

</div>