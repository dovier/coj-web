<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<spring:message code="pagehdr.amannouncements" />
</h2>
<div class="postcontent">
	<br />
	<form id="filter-form" class="form-inline">
		<div id="form-group">
			<label><spring:message code="filedhdr.searchcontent" />:</label> <input
				type="text" class="form-control" name="pattern" value="${pattern}">
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
	<br /> <a href="<c:url value="/admin/addann.xhtml" />"><spring:message
			code="link.aaddannouncement" /></a> <br />

	<div id="display-table-container"
		data-reload-url="/admin/tables/listann.xhtml"></div>

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