<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24huscountry" />
	<br />
	<center>${country.name}</center>
</h2>
<div class="row postcontent">
	<!-- content -->
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label><spring:message code="fieldhdr.searchusers" />:</label> <input
				type="text" class="form-control" name="pattern" value="${pattern}">
			<input type="hidden" name="country_id" value="${country_id}" />
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
	<c:if test="${search == true}">
		<label><spring:message code="fieldhdr.totalfound" />:
			${found}</label>
	</c:if>
	<br />
	<div style="clear: both; float: left">
		<spring:message code="fieldhdr.logged" />
		: ${logged}
	</div>
	<div class="col-xs-12">
		<div id="display-table-container"
			data-reload-url="/tables/userscountryrank.xhtml"></div>
	</div>
	<!-- /content -->
</div>
<script>
	$(function() {
		$('#filter-button').click(function(event) {
			displayTableReload();
			event.preventDefault();
		});

		displayTableReload("country_id=${country_id}");
	});
</script>