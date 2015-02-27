<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24histandings" />
</h2>
<div class="postcontent">
	<!-- content -->
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label><spring:message code="fieldhdr.searchinstitutions" />:</label>
			<input type="text" name="pattern" value="${pattern}">
		</div>
		<div class="form-group">	
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>
	</form>
	<c:choose>
		<c:when test="${search == true}">
			<label><spring:message code="fieldhdr.totalfound" />:
				${found}</label>
		</c:when>
	</c:choose>
	<br />

	<div id="display-table-container"
		data-reload-url="/tables/institutionsrank.xhtml"></div>
	<!-- /content -->
</div>
<script>
	$(function() {
		$('#filter-button').click(function(event) {
			displayTableReload($('#filter-form').formSerialize());
			event.preventDefault();
		});
	});

	$(document).ready(displayTableReload($('#filter-form').formSerialize()));
</script>