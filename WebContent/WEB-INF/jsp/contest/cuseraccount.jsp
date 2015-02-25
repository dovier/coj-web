<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->


<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
	<br />
	<spring:message code="pagehdr.profileof" />
	<c:out value="${user.nick}" />
</h2>
<div class="postcontent">
	<c:choose>
		<c:when test="${contest.coming == true}">
			<table class="volume">
				<thead>
					<th><spring:message code="fieldhdr.start" /></th>
					<th><spring:message code="fieldhdr.end" /></th>
					<th><spring:message code="fieldhdr.countdown" /></th>
					<th><spring:message code="fieldhdr.status" /></th>
				</thead>
				<tbody>
					<tr>
						<td>${contest.initdate}</td>
						<td>${contest.tostart}</td>
						<td>${contest.now}</td>
						<td><span class="label label-danger"><spring:message
									code="fieldhdr.status" /></span></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<c:choose>
				<c:when test="${contest.show_status == true}">
					<%
						String uid = request.getParameter("uid");
					%>
					<fieldset>
						<legend>
							<spring:message code="fieldhdr.generalinfo" />
						</legend>
						<table width="100%" class="userinfo">
							<tr>
								<td><spring:message code="fieldhdr.username" />:</td>
								<td><c:out value="${user.username}" /></td>
							</tr>
							<tr>
								<td><spring:message code="fieldhdr.country" />:</td>
								<td><c:url value="${user.country_desc}" /></td>
							</tr>
							<tr>
								<td><spring:message code="fieldhdr.institution" />:</td>
								<td><c:url value="${user.institution_desc}" /></td>
							</tr>
						</table>
					</fieldset>
							<c:if test="${(not empty user.coach and user.coach ne 'none') or
							(not empty user.user_1 and user.user_1 ne 'none') or
							(not empty user.user_2 and user.user_2 ne 'none') or
							(not empty user.user_3 and user.user_3 ne 'none') }">
					<fieldset>
						<legend>
							<spring:message code="fieldhdr.members" />
						</legend>
						<table width="100%" class="userinfo">
							<c:if test="${not empty user.coach and user.coach ne 'none'}">
								<tr>
									<td><c:out value="${user.coach}" />&nbsp;(Coach)</td>
								</tr>
							</c:if>
							<c:if test="${not empty user.user_1 and user.user_1 ne 'none'}">
								<tr>
									<td><c:out value="${user.user_1}" /></td>
								</tr>
							</c:if>
							<c:if test="${not empty user.user_2 and user.user_2 ne 'none'}">
							<tr>
								<td><c:out value="${user.user_2}" /></td>
							</tr>
							</c:if>
							<c:if test="${not empty user.user_3 and user.user_3 ne 'none'}">
								<tr>
									<td><c:out value="${user.user_3}" /></td>
								</tr>
							</c:if>
						</table>
					</fieldset>
							</c:if>

					<br />
					<h3>
						<spring:message code="fieldhdr.statsjudgments" />
					</h3>
					<table class="volume" border="1px">
						<thead>
							<th><spring:message code="tablehdr.ac" /></th>
							<th><spring:message code="tablehdr.ce" /></th>
							<th><spring:message code="tablehdr.sle" /></th>
							<th><spring:message code="tablehdr.ivf" /></th>
							<th><spring:message code="tablehdr.mle" /></th>
							<th><spring:message code="tablehdr.ole" /></th>
							<th><spring:message code="tablehdr.pe" /></th>
							<th><spring:message code="tablehdr.rte" /></th>
							<th><spring:message code="tablehdr.tle" /></th>
							<th><spring:message code="tablehdr.wa" /></th>
							<th><spring:message code="tablehdr.total" /></th>
						</thead>
						<tr>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=ac"><c:out
										value="${user.acc}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=ce"><c:out
										value="${user.ce}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=sle"><c:out
										value="${user.fle}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=ivf"><c:out
										value="${user.ivf}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=mle"><c:out
										value="${user.mle}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=ole"><c:out
										value="${user.fle}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=pe"><c:out
										value="${user.pe}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=rte"><c:out
										value="${user.rte}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=tle"><c:out
										value="${user.tle}" /></a></td>
							<td><a
								href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}&status=wa"><c:out
										value="${user.wa}" /></a></td>
							<td><a href="/contest/cstatus.xhtml?username=<%=uid%>&cid=${contest.cid}"><c:out
										value="${user.total}" /></a></td>
						</tr>
					</table>
					<fieldset>
						<legend>
							<spring:message code="fieldhdr.solvedprob" />
							(${user.solved})
						</legend>
						<div class="plistACC">
							<c:forEach items="${solved}" var="problem">
								<a
									href="cstatus.xhtml?username=<%=uid%>&pid=${problem.pid}&cid=${contest.cid}"
									title="${problem.title}">${problem.pid}</a>
							</c:forEach>
						</div>
					</fieldset>
					<fieldset>
						<legend>
							<spring:message code="fieldhdr.triedunsolvedprob" />
							(${user.unsolved})
						</legend>
						<div class="plistWA">
							<c:forEach items="${unsolved}" var="problem">
								<a
									href="cstatus.xhtml?username=<%=uid%>&pid=${problem.pid}&cid=${contest.cid}"
									title="${problem.title}">${problem.pid}</a>
							</c:forEach>
						</div>
					</fieldset>
				</c:when>
				<c:otherwise>
					<center>
						<span class="label label-danger"><spring:message
								code="contest.error.disable" /></span>
					</center>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>
