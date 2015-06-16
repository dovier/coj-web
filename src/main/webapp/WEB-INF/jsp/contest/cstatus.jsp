<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${refresh}">
	<c:choose>
		<c:when test="${contest.running}">
			<meta content="60" http-equiv="refresh">
		</c:when>
		<c:when test="${contest.frozen}">
			<meta content="60" http-equiv="refresh">
		</c:when>
	</c:choose>
</c:if>
<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
	<br />
	<spring:message code="pagehdr.judgments" />
</h2>

<div style="text-align: center; margin-top: -20px; margin-bottom: 15px">
	<c:if test="${contest.running}">
		<c:choose>
			<c:when test="${refresh}">
				<span class="label label-danger"><spring:message
						code="text.vcstandings.3" /></span>
			</c:when>
			<c:otherwise>
				<a href="cstatus.xhtml?&cid=${contest.cid}&refresh=true">[Auto
					refresh]</a>
			</c:otherwise>
		</c:choose>
	</c:if>
</div>
<div class="postcontent">
	<form id="filter-form" class="form-inline">

		<div class="form-group">
			<input placeholder="<spring:message code="fieldhdr.user" />"
				type="text" name="username" value="${filter.username}"
				class="form-control" />
		</div>
		<input type="hidden" name="cid" value="${contest.cid}" />
		<div class="form-group">
			<c:if test="${not empty letterlist}">
			<select name="pid" class="form-control">
				<option value="0"><spring:message code="fieldhdr.all" /></option>
				<c:forEach items="${letterlist}" var="letter">
					<c:choose>
						<c:when test="${filter.pid eq letter.pid}">
							<option value="${letter.pid}" selected>${letter.letter}</option>
						</c:when>
						<c:otherwise>
							<option value="${letter.pid}">${letter.letter}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
			</c:if>
			<c:if test="${empty letterlist}">
			<input class="form-control"
						placeholder="<spring:message code="fieldhdr.prob" />" type="text" pattern="/\d*/"
						name="pid" value="${filter.pid}" size="8" />
			</c:if>
		</div>
		<div class="form-group">
			<select name="status" class="form-control">
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
			</select>
		</div>
		<div class="form-group">
			<select class="form-control" name="planguage">
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
			</select>
		</div>
		<div class="form-group">
			<input id="filter-button" class="btn btn-primary" type="submit"
				value="<spring:message code="button.filter"/>" />
		</div>
		<input type="hidden" name="cid" value="${contest.cid}">
	</form>
	<c:choose>
		<c:when test="${contest.coming == true}">

		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${contest.show_status == true}">
					<div id="display-table-container"
						data-reload-url="/tables/cstatus.xhtml"></div>
				</c:when>
				<c:otherwise>
					<table class="volume" border="1px">
						<thead>
							<th class="headid"><spring:message code="tablehdr.id" /></th>
							<th class="headdate"><spring:message code="tablehdr.date" /></th>
							<th class="headuser"><spring:message code="tablehdr.user" /></th>
							<th class="headprob"><spring:message code="tablehdr.prob" /></th>
							<th class="headjudgment"><spring:message
									code="tablehdr.judgment" /></th>
							<th class="headtime"><spring:message code="tablehdr.time" /></th>
							<th class="headmem"><spring:message code="tablehdr.mem" /></th>
							<th class="headlang"><spring:message code="tablehdr.lang" /></th>
						</thead>
					</table>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>
<script>
	$(initStandardFilterForm);
</script>
