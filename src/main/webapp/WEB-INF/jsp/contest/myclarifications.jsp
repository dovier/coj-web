<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>


<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>"
		title="Go to Contest">${contest.name}</a> <br />
	<spring:message code="pagehdr.clarifications" />
</h2>
<div class="postcontent">

	<div>
		<form action="myclarifications.xhtml" method="get">
			<div class="form-group coj_float_rigth">
				<label class="col-xs-4 control-label""><spring:message code="fieldhdr.searchclarifications" /></label>
				<div class="col-xs-4">
					<input type="text" class="form-control" size="25" name="pattern"
						value="${pattern}">
				</div>
				<input type="hidden" name="cid" value="${contest.cid}"> <input
					type="submit" class="btn btn-primary"
					value="<spring:message code="button.search"/>">
			</div>
		</form>
	</div>
	<c:choose>
		<c:when test="${search == true}">
			<label><spring:message code="fieldhdr.totalfound" />:
				${found}</label>
		</c:when>
	</c:choose>

	<br />

	<authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
		<c:if test="${contest.running eq true}">
			<a
				href="<c:url value="/contest/clarification.xhtml?cid=${contest.cid}"/>"><i
				class="fa fa-pencil"></i>&nbsp;<spring:message code="link.request" /></a>
		</c:if>
	</authz:authorize>
	<authz:authorize
		ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_PSETTER,ROLE_PSETTER">

		<c:if test="${contest.running}">
			<a href="<c:url value="sendclarification.xhtml?cid=${contest.cid}"/>"><i
				class="fa fa-send"></i>&nbsp;<spring:message code="link.send" /></a>
		</c:if>
	</authz:authorize>

	<table class="volume">
		<thead>
			<th><spring:message code="tablehdr.id" /></th>
			<th class="headfrom"><spring:message code="tablehdr.from" /></th>
			<th><spring:message code="tablehdr.subject" /></th>
			<!--th><spring:message code="tablehdr.topic" /></th-->
			<th><spring:message code="tablehdr.date" /></th>
			<th><spring:message code="tablehdr.actions" /></th>
		</thead>
		<tbody>
			<c:forEach items="${clarifications}" var="clarification">
				<tr>
					<td><a
						href="<c:url value="clarificationview.xhtml?cid=${contest.cid}&ccid=${clarification.id}"/>">${clarification.id}</a></td>
					<td><a
						href="<c:url value="/user/useraccount.xhtml?username=${clarification.username}" />">${clarification.username}</a></td>
					<!--td>
                        <a href="<c:url value="clarificationview.xhtml?cid=${contest.cid}&ccid=${clarification.id}"/>">${clarification.title}</a>
                    </td-->
					<td><c:choose>
							<c:when test="${clarification.pid eq 0}">
								<c:choose>
									<c:when test="${clarification.isread == false}">
										<b>General</b>
									</c:when>
									<c:otherwise>
										General
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${clarification.isread == false}">
										<b><a
											href="<c:url value="cproblem.xhtml?cid=${contest.cid}&pid=${clarification.pid}" />">${clarification.ptitle}</a></b>
									</c:when>
									<c:otherwise>
										<a
											href="<c:url value="cproblem.xhtml?cid=${contest.cid}&pid=${clarification.pid}" />">${clarification.ptitle}</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose></td>
					<td class="date">${clarification.date}</td>

					<td><authz:authorize
							ifAnyGranted="ROLE_ADMIN,ROLE_SUPER_PSETTER,ROLE_PSETTER">
							<c:if test="${isContestJudge and contest.running}">
								<a
									href="<c:url value="sendclarification.xhtml?cid=${contest.cid}&ccid=${clarification.id}&uid=${clarification.username}&pid=${clarification.pid}"/>">
									<i class="fa fa-reply"></i>
								</a>
							</c:if>
						</authz:authorize> <c:if test="${clarification.isread == false}">
							<a
								href="<c:url value="markanswered.xhtml?cid=${contest.cid}&ccid=${clarification.id}"/>"
								title="<spring:message code="titval.markasanswered"/>"><i
								class="green fa fa-check-circle"></i></a>
						</c:if></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
