<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
	<spring:message code="pagehdr.24hcstandings" />
</h2>
<div class="postcontent">
	<!-- content -->
	<form id="filter-form" class="form-inline">
		<div class="form-group">
			<label><spring:message code="fieldhdr.searchcountries" />:</label> <input
				type="text" name="pattern" value="${pattern}">
			<div class="form-group">
				<input id="filter-button" type="submit" class="btn btn-primary"
					value="<spring:message code="button.filter"/>">
			</div>
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
		data-reload-url="/tables/countriesrank.xhtml"></div>
</div>
<script>
	$(initStandardFilterForm);
</script>