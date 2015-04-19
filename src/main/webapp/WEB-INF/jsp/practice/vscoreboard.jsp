<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
	<c:when test="${contest.running}">
		<meta content="60" http-equiv="refresh">
	</c:when>
	<c:when test="${contest.frozen}">
		<meta content="60" http-equiv="refresh">
	</c:when>
</c:choose>

<div style="clear: both; height: 19px;">
	<table class="contestlanguages" style="align: right">
		<tbody>
			<tr>
				<td><b><spring:message code="fieldhdr.status" />: </b></td>
				<td><c:choose>
						<c:when test="${contest.running == true}">
							<span class="label label-danger"><spring:message
									code="fieldval.running" /></span>
						</c:when>
						<c:when test="${contest.coming == true}">
							<span class="label label-danger"><spring:message
									code="fieldval.coming" /></span>
						</c:when>
						<c:when test="${contest.past == true}">
							<spring:message code="fieldval.past" />
						</c:when>
					</c:choose></td>
				<c:if test="${contest.running == true}">

					<td><b><spring:message code="fieldhdr.elapsed" />: </b></td>
					<td>${contest.elapsed}</td>
					<td><b><spring:message code="fieldhdr.remaining" />: </b></td>
					<td>${contest.remaining}</td>
				</c:if>

				<c:if test="${contest.running != true}">

					<td><b><spring:message code="fieldhdr.start" />:</b></td>
					<td><fmt:formatDate value="${contest.initdate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><b><spring:message code="fieldhdr.end" />: </b></td>
					<td><fmt:formatDate value="${contest.enddate}"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</c:if>
			</tr>
			<c:if
				test="${contest.running == true && (contest.full_frozen == true || contest.frozen == true)}">
				<tr>
					<td colspan="6" style="text-align: right"><c:choose>
							<c:when test="${contest.full_frozen == true}">
								<span class="label label-danger"><i class="fa fa-warning"></i>&nbsp;<spring:message
										code="text.deadtime" /></span>
							</c:when>
							<c:otherwise>
								<span class="label label-primary"><i class="fa fa-info-circle"></i>&nbsp;<spring:message
										code="text.frozentime" /></span>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
<br />
<h2 class="postheader" style="clear: both">
	<a class="linkheader"
		href="<c:url value="vcontestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
	<br />
	<spring:message code="pagehdr.standings" />
</h2>
<c:if test="${contest.running == true}">
	<div style="text-align: center; margin-top: -20px;">
		<span class="label label-danger"><spring:message
				code="text.vcstandings.3" /></span>
	</div>
</c:if>
<div class="postcontent">
	<table class="navigating">
		<tbody>
			<td><a href="<c:url value="/practice/vcontestview.xhtml" />"><i
					class="fa fa-eye"></i>&nbsp;<spring:message code="link.overview" /></a></td>
			<td><a href="<c:url value="/practice/vproblems.xhtml" />"><i
					class="fa fa-list"></i>&nbsp;<spring:message code="link.problems" /></a></td>
			<td><a href="<c:url value="/practice/vsubmit.xhtml" />"><i
					class="fa fa-file-code-o"></i>&nbsp;<spring:message
						code="link.submit" /></a></td>
			<td><a href="<c:url value="/practice/vstatus.xhtml" />"><i
					class="fa fa-legal"></i>&nbsp;<spring:message code="link.judgments" /></a></td>
		</tbody>
	</table>


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
			<c:choose>
				<c:when test="${contest.style eq 1}">
					<table class="scoreboradstatus">
						<tbody>
							<tr>
								<td class="FPS"><spring:message
										code="tablehdr.firstaccontest" /></td>
								<td class="FS"><spring:message
										code="tablehdr.firstacproblem" /></td>
								<td class="ACC"><spring:message
										code="tablehdr.acsubmission" /></td>
								<td class="WA"><spring:message
										code="tablehdr.rejectedsubmission" /></td>
								<td class="PEN"><spring:message
										code="tablehdr.pendingsubmission" /></td>
							</tr>
						</tbody>
					</table>
					<br />
					<table class="ACM" border="1px">
						<thead>
							<th class="headrk anim:position"><spring:message
									code="tablehdr.rank" /></th>
							<th colspan="3" class="team anim:id" style="text-align: center;">
								<spring:message code="tablehdr.contestant" />
							</th>
							<th class="headacc anim:constant"><spring:message
									code="tablehdr.ac" /></th>
							<th class="headtime anim:constant"><spring:message
									code="tablehdr.time" /></th>
							<c:forEach items="${problems}" var="problem">
								<th width="6%" class="anim:constant"><a class="linkheader"
									href="<c:out value="vproblem.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>"
									title="${problem.title}">${problem.letter}</a></th>
							</c:forEach>
						</thead>
						<tbody>
							<c:forEach items="${users}" var="user">
								<tr
									<c:if test="${user.virtual == true}">
                                        class="virtual"
                                    </c:if>>
									<td class="country"><c:url value="${user.rank}" /></td>
									<td class="team"><c:out value="${user.nick}" /></td>
									<td class="country"><img
										src="<c:url value="/images/countries/${user.country}"/>.png"
										title="<c:url value="${user.country_desc}"/>"
										alt="<c:url value="${user.country}"/>" /></td>
									<td class="school"><img
										src="<c:url value="/images/school/${user.institution}"/>.png"
										title="<c:url value="${user.institution_desc}"/>"
										alt="<c:url value="${user.institution}"/>" /></td>
									<td><a
										href="<c:url value="/practice/vstatus.xhtml?cid=${contest.cid}&status=ac&username=${user.username}"/>">${user.acc}</a></td>
									<td>${user.penalty}</td>
									<c:forEach items="${user.problems}" var="problem">
										<td class="${problem.scoreClass}"><c:choose>
												<c:when test="${problem.accepted == true}">
													<label title="${problem.ac_time}">${problem.acmin}</label>
													<br />
													<c:choose>
														<c:when test="${problem.beforeac != 0}">
															<small>(-${problem.beforeac})</small>
														</c:when>
													</c:choose>

													<c:choose>
														<c:when test="${problem.afterac != 0}">
															<small>(+${problem.afterac})</small>
														</c:when>
													</c:choose>


												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${problem.beforeac != 0}">
                                                            (-${problem.beforeac})
                                                        </c:when>
													</c:choose>
													<c:choose>
														<c:when test="${problem.pending != 0}">
                                                            (+${problem.pending})
                                                        </c:when>
													</c:choose>
												</c:otherwise>
											</c:choose></td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:when>
				<c:otherwise>
					<div class="error">
						<spring:message code="contest.error.disable" />
					</div>
				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>