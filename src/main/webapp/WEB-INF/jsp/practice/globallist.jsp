<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>


<h2 class="postheader">
	<spring:message code="pagehdr.pcsglist" />
</h2>

<form id="filter-form" class="form-inline">
	<table class="login filters">
		<tr class="filter">
			<td><spring:message code="fieldhdr.rc" />:</td>
			<td><input type="text" name="cid" value="${filter.contest_id}"
				size="10" /></td>
			<td><spring:message code="fieldhdr.creator" />:</td>
			<td><input type="text" name="creator" value="${filter.username}"
				size="10" /></td>
			<td><spring:message code="fieldhdr.access" />:</td>
			<td><select name="access">
					<option value="0"><spring:message code="fieldhdr.all" /></option>
					<c:choose>
						<c:when test="${filter.access eq 1}">
							<option selected value="1"><spring:message
									code="fieldval.public" /></option>
							<option value="2"><spring:message
									code="fieldval.private" /></option>
						</c:when>
						<c:when test="${filter.access eq 2}">
							<option selected value="2"><spring:message
									code="fieldval.private" /></option>
							<option value="1"><spring:message code="fieldval.public" /></option>
						</c:when>
						<c:otherwise>
							<option value="2"><spring:message
									code="fieldval.private" /></option>
							<option value="1"><spring:message code="fieldval.public" /></option>
						</c:otherwise>
					</c:choose>
			</select></td>
			<td><spring:message code="fieldhdr.status" />:</td>
			<td><select name="status">
					<option value="0"><spring:message code="fieldhdr.all" /></option>
					<c:choose>
						<c:when test="${filter.vstatus eq 1}">
							<option selected value="1"><spring:message
									code="fieldval.coming" /></option>
							<option value="2"><spring:message
									code="fieldval.running" /></option>
							<option value="3"><spring:message code="fieldval.past" /></option>
						</c:when>
						<c:when test="${filter.vstatus eq 2}">
							<option value="1"><spring:message code="fieldval.coming" /></option>
							<option selected value="2"><spring:message
									code="fieldval.running" /></option>
							<option value="3"><spring:message code="fieldval.past" /></option>
						</c:when>
						<c:when test="${filter.vstatus eq 3}">
							<option value="1"><spring:message code="fieldval.coming" /></option>
							<option value="2"><spring:message
									code="fieldval.running" /></option>
							<option selected value="3"><spring:message
									code="fieldval.past" /></option>
						</c:when>
						<c:otherwise>
							<option value="1"><spring:message code="fieldval.coming" /></option>
							<option value="2"><spring:message
									code="fieldval.running" /></option>
							<option value="3"><spring:message code="fieldval.past" /></option>
						</c:otherwise>
					</c:choose>
			</select></td>

			<td><input id="filter-button" type="submit"
				class="btn btn-primary"
				value="<spring:message code="button.filter"/>"></td>
		</tr>

	</table>
</form>

<div class="postcontent">

	<div id="display-table-container"
		data-reload-url="/tables/globallist.xhtml"></div>
</div>
<script>
$(initStandardFilterForm);
</script>