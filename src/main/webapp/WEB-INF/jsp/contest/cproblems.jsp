<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" />${contest.name}</a>
	<br />
	<spring:message code="pagehdr.problems" />
</h2>
<div class="postcontent">
	<c:choose>
		<c:when test="${contest.coming == true}">
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${contest.show_status == true}">
					<div id="display-table-container"
						data-reload-url="/tables/cproblems.xhtml"></div>
				</c:when>
				<c:otherwise>
					<table class="volume">
						<thead>
							<th class="headid"><spring:message code="tablehdr.id" /></th>
							<authz:authorize ifNotGranted="ROLE_ANONYMOUS">
								<th class="headsolved"><spring:message
										code="tablehdr.solved" /></th>
							</authz:authorize>
							<th><spring:message code="tablehdr.title" /></th>
							<c:if test="${contest.style eq 4}">
								<th><spring:message code="tablehdr.level" /></th>
							</c:if>
						</thead>
					</table>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>
<script>
	$(document).ready(displayTableReload("?cid=${contest.cid}"));
</script>