<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24hiscountry" />
	<br /> ${country.name}
</h2>
<div class="postcontent">
	<!-- content -->
	<div class="row row-centered">
		<div class="col-xs-5 col-centered">
			<form id="filter-form" class="form-inline">
				<input type="hidden" name="country_id" value="${country_id}">
				<div class="form-group">
					<input class="form-control"
						placeholder="<spring:message code="fieldhdr.searchinstitutions" />"
						type="text" name="pattern" value="${pattern}">
				</div>
				<div class="form-group">
					<input id="filter-button" type="submit" class="btn btn-primary"
						value="<spring:message code="button.filter"/>">
				</div>
			</form>
		</div>
	</div>
	<c:choose>
		<c:when test="${search == true}">
			<label><spring:message code="fieldhdr.totalfound" />:
				${found}</label>
		</c:when>
	</c:choose>
	<br />
	<div id="display-table-container"
		data-reload-url="/tables/institutionsrankbycountry.xhtml"></div>
	<!-- /content -->
</div>
<script>
	$(function() {
		$('#filter-button').click(function(event) {
			displayTableReload($('#filter-form').formSerialize());
			event.preventDefault();
		});
	});

	$(document).ready(displayTableReload("?country_id=${country_id}"));
</script>