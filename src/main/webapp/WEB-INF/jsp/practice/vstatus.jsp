<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<c:choose>
	<c:when test="${contest.running}">
		<meta content="60" http-equiv="refresh">
	</c:when>
	<c:when test="${contest.frozen}">
		<meta content="60" http-equiv="refresh">
	</c:when>
</c:choose>
<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="vcontestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
	<br />
	<spring:message code="pagehdr.judgments" />
</h2>
<c:if test="${contest.running == true}">
	<div style="text-align: center">
		<span class="label label-danger"><spring:message
				code="text.vcstandings.3" /></span>
	</div>
	<br />
</c:if>
<div class="postcontent">
	<form id="filter-form">
		<table class="login filters">
			<tr class="filter">
				<td><spring:message code="fieldhdr.user" />:</td>
				<td><c:choose>
						<c:when test="${not empty filter.username}">
							<input type="text" name="username" value="${filter.username}"
								size="10" />
						</c:when>
						<c:otherwise>
							<input type="text" name="username" value="${username}" size="10" />
						</c:otherwise>
					</c:choose></td>
				<td><spring:message code="fieldhdr.prob" />:</td>
				<td><input type="text" name="pid" value="${filter.pid}"
					size="8" /></td>
				<td><spring:message code="fieldhdr.judgment" />:</td>
				<td><select name="status">
						<option value="All"><spring:message code="fieldhdr.all" /></option>
						<c:forEach items="${statuslist}" var="status">
							<c:choose>
								<c:when test="${filter.current_status eq status.key}">
									<option value="${status.key}" selected>${status.key}</option>
								</c:when>
								<c:otherwise>
									<option value="${status.key}">${status.key}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
				<td><spring:message code="fieldhdr.lang" />:</td>
				<td><select name="planguage">
						<option value="All"><spring:message code="fieldhdr.all" /></option>
						<c:forEach items="${filter.languages}" var="language">
							<c:choose>
								<c:when test="${filter.language eq language.key}">
									<option value="${language.key}" selected>${language.descripcion}</option>
								</c:when>
								<c:otherwise>
									<option value="${language.key}">${language.descripcion}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
				<td><input type="submit" id="filter-button"
					value="<spring:message code="button.filter"/>" /></td>
			</tr>
		</table>
	</form>
	<c:choose>
		<c:when test="${contest.coming == true}">
			<table class="volume">
				<thead>
					<th><spring:message code="contest.start" /></th>
					<th><spring:message code="contest.tostart" /></th>
					<th><spring:message code="coming.table.now" /></th>
					<th><spring:message code="coming.table.status" /></th>
				</thead>
				<tbody>
					<tr>
						<td>${contest.initdate}</td>
						<td>${contest.tostart}</td>
						<td>${contest.now}</td>
						<td><span class="label label-danger"><spring:message
									code="coming.status" /></span></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>

			<div id="display-table-container"
				data-reload-url="/tables/vstatus.xhtml"></div>
		</c:otherwise>
	</c:choose>
</div>

<script>
$(initStandardFilterForm);
</script>