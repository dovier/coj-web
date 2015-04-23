<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/TimeCircles.css" />" type="text/css" />
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/TimeCircles.js" />"></script>

<h2 class="postheader">
	<a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" />${contest.name}</a> <br />
	<c:if test="${not contest.coming}">
	<spring:message code="pagehdr.overview" />
	</c:if>
	<c:if test="${contest.coming}">
			<b><spring:message code="fieldhdr.startsin" />:</b>
		<div id="circles" data-timer="${timeToInit}">
		</div>
	</c:if>
</h2>


<div class="postcontent">
	<fieldset>
		<legend>
			<spring:message code="fieldhdr.overalldata" />
		</legend>
		<table width="100%" class="userinfo">
			<tr>
				<td><spring:message code="fieldhdr.contesttype" />:</td>
				<td><c:forEach items="${styles}" var="style">
						<c:if test="${style.sid eq contest.style}">
                            ${style.name}
                        </c:if>
					</c:forEach></td>
			</tr>
			<tr>
				<td><spring:message code="fieldhdr.contestanttype" />:</td>
				<td><c:choose>
						<c:when test="${contest.contestant eq 1}">
							<spring:message code="fieldval.teams" />
						</c:when>
						<c:when test="${contest.contestant eq 2}">
							<spring:message code="page.managecontest.user" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.teams" />, <spring:message code="page.managecontest.user" />
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td><spring:message code="fieldhdr.accesstype" />:</td>
				<td><c:choose>
						<c:when test="${contest.registration eq 2}">
						<spring:message code="titval.private"/>
						</c:when>
						<c:otherwise>
							<spring:message code="titval.public"/>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td><spring:message code="fieldhdr.regtype" />:</td>
				<td><c:choose>
						<c:when test="${contest.registration eq 0}">
							<spring:message code="fieldval.freereg" />
						</c:when>
						<c:when test="${contest.registration eq 1}">
							<spring:message code="fieldval.limitedreg" />
						</c:when>
						<c:when test="${contest.registration eq 2}">
							<spring:message code="fieldval.regbyadmin" />
						</c:when>
					</c:choose></td>
			</tr>
			<tr>
				<c:if test="${contest.style == 1}">
					<td><spring:message code="fieldhdr.templatevirtual" />:</td>
					<td><c:choose>
							<c:when test="${contest.vtemplate == true}">
								<spring:message code="fieldval.yes" />
							</c:when>
							<c:otherwise>
								<spring:message code="fieldval.no" />
							</c:otherwise>
						</c:choose></td>
				</c:if>

			</tr>
			<c:if test="${contest.style eq 1}">
				<tr>
					<td><spring:message code="fieldhdr.penaltyrejectedsub" />:</td>
					<td>${contest.penalty}</td>
				</tr>

				<tr>
					<td><spring:message code="fieldhdr.frozentime" />:</td>
					<td>${contest.frtime}</td>
				</tr>

				<tr>
					<td><spring:message code="fieldhdr.deadtime" />:</td>
					<td>${contest.deadtime}</td>
				</tr>
			</c:if>

			<c:if test="${contest.style eq 4}">
				<tr>
					<td><spring:message code="fieldhdr.levels" />:</td>
					<td>${contest.levels}</td>
				</tr>
				<tr>
					<td><spring:message code="fieldhdr.aclimit" />:</td>
					<td>${contest.aclimit}</td>
				</tr>

				<tr>
					<td><spring:message code="fieldhdr.acbylevels" />:</td>
					<td>${contest.acbylevels}</td>
				</tr>
				<tr>
					<td><spring:message code="fieldhdr.pointsbyproblem" />:</td>
					<td>${contest.ppoints}</td>
				</tr>
			</c:if>

			<tr>
				<td><spring:message code="fieldhdr.proglanguages" />:</td>
				<td><c:forEach items="${planguages}" var="planguage">
                        ${planguage.language} &ensp;
                    </c:forEach></td>
			</tr>

			<!--tr>
                <td>
            <spring:message code="fieldhdr.showclarifall"/>:
        </td>
        <td>
            <spring:message code="fieldval.yes"/>
        </td>
    </tr-->

			<tr>
				<td><spring:message code="fieldhdr.showproball" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_problem_out == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.showjudgscontestants" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_status == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.showjudgsall" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_status_out == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.showrankcontestants" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_scoreboard == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.showrankall" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_scoreboard_out == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.showstatscontestants" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_stats == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.showstatsall" />:</td>
				<td><c:choose>
						<c:when test="${contest.show_stats_out == true}">
							<spring:message code="fieldval.yes" />
						</c:when>
						<c:otherwise>
							<spring:message code="fieldval.no" />
						</c:otherwise>
					</c:choose></td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.goldmedals" />:</td>
				<td>${contest.gold}</td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.silvermedals" />:</td>
				<td>${contest.silver}</td>
			</tr>

			<tr>
				<td><spring:message code="fieldhdr.bronzemedals" />:</td>
				<td>${contest.bronze}</td>
			</tr>
		</table>
	</fieldset>


	<fieldset>
		<legend>
			<spring:message code="fieldhdr.specificrules" />
		</legend>
		<c:choose>
			<c:when test="${contest.overview == null}">
				<spring:message code="fieldval.none" />
			</c:when>
			<c:otherwise>
                ${contest.overview}
            </c:otherwise>
		</c:choose>

	</fieldset>

</div>
<script>
	$(document).ready(function() {
		$("#circles").TimeCircles();
	});
</script>