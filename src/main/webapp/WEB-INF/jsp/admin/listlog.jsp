<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
	: <spring:message code="pagehdr.alogs" />
</h2>
<div class="postcontent row">
	<div class="col-xs-12">
		<form id="filter-form" method="get" class="form-inline">

			<div class="form-group">
				<label><spring:message code="fieldhdr.searchusers" />:</label> <input
					type="text" name="pattern" value="${pattern}" class="form-control" />
			</div>
			<div class="form-group">
				<input id="filter-button" type="submit" class="btn btn-primary"
					value="<spring:message code="button.filter"/>">
			</div>

		</form>
	</div>
	<div id="display-table-container"
		data-reload-url="/admin/tables/listlog.xhtml"></div>

</div>
<script>
$(initStandardFilterForm);
</script>