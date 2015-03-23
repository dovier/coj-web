<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" />${contest.name}</a>
	<br />
	<spring:message code="pagehdr.problem" />
</h2>

<div class="row postcontent">
	<div class="panel panel-primary text-center">
		<authz:authorize ifNotGranted="ROLE_ANONYMOUS">
			<c:if test="${contest.running == true}">
                    &nbsp;&nbsp;<a
					href="<c:url value="csubmit.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>"><spring:message
						code="link.submit" /></a>
			</c:if>               
                &nbsp;&nbsp;<a
				href="<c:url value="cstatus.xhtml?pid=${problem.pid}&cid=${contest.cid}&username="/><authz:authentication property="principal.username" />"><spring:message
					code="link.mysubmissions" /></a>
		</authz:authorize>
		&nbsp;&nbsp;<a
			href="<c:url value="cstatus.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>"><spring:message
				code="link.submissions" /></a>
	</div>
	<div class="col-xs-12">
		<h3 class="text-center">
			<b><c:out value=" ${problem.pid}" /> - <c:out
					value="${problem.title}" /></b>
			<c:if test="${problem.special_judge}">
				<a data-toggle="tooltip"
					title="<spring:message code="titval.special.judge"/>"><span
					class="text-danger"><i class="fa fa-gavel"></i></span></a>
			</c:if>
		</h3>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table class="pinfo">
				<tbody>
					<c:if test="${contest.past == true}">
						<tr>
							<td><spring:message code="fieldhdr.createdby" /></td>
							<td><c:out value="${problem.author}" /></td>
						</tr>
						<tr>
							<td width="20%"><spring:message code="fieldhdr.addedby" /></td>
							<td><a
								href="<c:url value="/user/useraccount.xhtml?username=${problem.username}"/>"><c:out
										value="${problem.username}" /></a>&nbsp;(<c:out
									value="${problem.date}" />)</td>
						</tr>
					</c:if>
					<tr>
						<td><spring:message code="fieldhdr.limits" /></td>
						<td><b><spring:message code="fieldhdr.timelimit" />:</b> <c:out
								value="${problem.time}" /> MS <c:choose>
								<c:when test="${problem.multidata}">
									<c:out value="|" />
									<b><spring:message code="fieldhdr.testlimit" />:</b>
									<c:out value="${problem.casetimelimit}" /> MS
					</c:when>
							</c:choose> <c:out value="|" /> <span style="font-weight: bold;"><spring:message
									code="fieldhdr.memorylimit" />:</span> <c:out
								value="${problem.memoryMB}" /> <c:out value=" | " /><b><spring:message
									code="fieldhdr.outputlimit" />:</b> <c:out value="64" /> MB<c:out
								value=" | " /><b><spring:message code="fieldhdr.sizelimit" />:</b>
							<c:out value="${problem.fontMB}" /></td>
					</tr>
					<tr>
						<td><spring:message code="fieldhdr.enabledlanguages" /></td>
						<td><c:forEach items="${problem.languages}" var="language"
								varStatus="status">
								<c:if test="${status.count ne 1}">
									<c:out value="|" />
								</c:if>
                        ${language.language}
                    </c:forEach></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<h4 class="text-primary">
				<spring:message code="fieldhdr.description" />
			</h4>
			<div class="ex row">
				<div class="col-xs-12">
					<c:url value="${problem.description}" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<h4 class="text-primary">
				<spring:message code="fieldhdr.inputspec" />
			</h4>
			<div class="ex row">
				<div class="col-xs-12">
					<c:url value="${problem.input}" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<h4 class="text-primary">
				<spring:message code="fieldhdr.outputspec" />
			</h4>
			<div class="row">
				<div class="col-xs-12">
					<c:url value="${problem.output}" />
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<h4 class="text-primary">
				<spring:message code="fieldhdr.sampleinput" />
			</h4>
			<code>${problem.inputex}</code>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<h4 class="text-primary">
				<spring:message code="fieldhdr.sampleoutput" />
			</h4>
			<code>${problem.outputex}</code>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<h4 class="text-primary">
				<spring:message code="fieldhdr.hint" />
			</h4>
			<div class="ex">
				<c:url value="${problem.comments}" />
			</div>
		</div>
	</div>
</div>
