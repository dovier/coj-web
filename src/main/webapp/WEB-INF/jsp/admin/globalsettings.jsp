<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
	<ul class="nav nav-pills">
		<li><a
			href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mc" /></a></li>
		<li><a
			href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gf" /></a></li>
		<li class="active"><a
			href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gs" /></a></li>
		<li><a
			href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mp" /></a></li>
		<li><a
			href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mpc" /></a></li>
		<li><a
			href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.iiu" /></a></li>
		<li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.bx" /></a></li>
		<li><a
			href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.ml" /></a></li>
		<li><a
			href="<c:url value="contestusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mu" /></a></li>
		<li><a
			href="<c:url value="contestawards.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.aw" /></a></li>
		<li><a
			href="<c:url value="contestoverview.xhtml?cid=${contest.cid}" />"><fmt:message
					key="page.managecontest.link.ov" /></a></li>
		<li><a
			href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.img" /></a></li>
	</ul>

	<form:form method="post" commandName="contest">
		<fieldset style="width: 400px;">
			<legend>
				<fmt:message key="page.managecontest.style" />
			</legend>
			<table class="contestlanguages">
				<tr>
					<td><fmt:message key="page.managecontest.style" /></td>
					<td>${style.name}</td>
				</tr>
				<tr>
					<td><fmt:message key="page.managecontest.registration" /></td>
					<td><c:choose>
							<c:when test="${contest.registration eq 0}">
								<span class="label label-info"><i
									class="fa fa-info-circle"></i> <fmt:message
										key="page.managecontest.register.free" /> </span>
							</c:when>

							<c:when test="${contest.registration eq 1}">
								<fmt:message key="page.globalsettings.rg.limit" />&nbsp;
									<form:input path="total_users" />&nbsp;
									<span class="label label-danger">&nbsp;<form:errors
										path="total_users" /></span>
							</c:when>

							<c:when test="${contest.registration eq 2}">
								<span class="label label-info"><i
									class="fa fa-info-circle"></i> <fmt:message
										key="page.managecontest.register.admin" /> </span>
							</c:when>
						</c:choose></td>
				</tr>
			</table>
			<table class="contestlanguages">
				<c:choose>
					<c:when test="${contest.style eq 0}">
						<tr>
							<td><span class="label label-danger">Undefined
									Property</span></td>
						</tr>
					</c:when>
					<c:when test="${contest.style eq 1}">
						<tr>
							<td><fmt:message key="page.globalsettings.acm.penal" /></td>
							<td><form:input path="penalty" /></td>
							<td><span class="label label-danger"><form:errors
										path="penalty" /></span></td>
						</tr>
						<tr>
							<td><fmt:message key="page.globalsettings.acm.frtime" /></td>
							<td><form:input path="frtime" /></td>
							<td><span class="label label-danger"><form:errors
										path="frtime" /></span></td>
						</tr>
						<tr>
							<td><fmt:message key="page.globalsettings.acm.dead" /></td>
							<td><form:input path="deadtime" /></td>
							<td><span class="label label-danger"><form:errors
										path="deadtime" /></span></td>
						</tr>
						<tr>
							<td><fmt:message key="page.globalsettings.acm.unfreeze" /></td>
							<td><form:input path="unfreeze_time" /></td>
							<td><span class="label label-danger"><form:errors
										path="unfreeze_time" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.style eq 2}">
						<tr>
							<td><fmt:message key="page.globalsettings.ioi.task" /></td>
							<td><form:input path="ioimark" /></td>
							<td><span class="label label-danger"><form:errors
										path="ioimark" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.style eq 3}">
						<tr>
							<td><fmt:message key="page.globalsettings.free.points" /></td>
							<td><form:input path="ppoints" /></td>
							<td><span class="label label-danger"><form:errors
										path="ppoints" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.style eq 4}">
						<tr>
							<td><spring:message code="fieldhdr.levels" /></td>
							<td><form:input path="levels" /></td>
							<td><span class="label label-danger"><form:errors
										path="levels" /></span></td>
						</tr>

						<tr>
							<td><spring:message code="fieldhdr.acbylevels" /></td>
							<td><form:input path="acbylevels" /></td>
							<td><span class="label label-danger"><form:errors
										path="acbylevels" /></span></td>
						</tr>

						<tr>
							<td><spring:message code="fieldhdr.aclimit" /></td>
							<td><form:input path="aclimit" /></td>
							<td><span class="label label-danger"><form:errors
										path="aclimit" /></span></td>
						</tr>

						<tr>
							<td><spring:message code="fieldhdr.pointsbyproblem" /></td>
							<td><form:input path="ppoints" /></td>
							<td><span class="label label-danger"><form:errors
										path="ppoints" /></span></td>
						</tr>

					</c:when>

				</c:choose>

			</table>
		</fieldset>
		<fieldset style="width: 400px;">
			<legend>
				<fmt:message key="page.globalsettings.flags.header" />
			</legend>
			<table class="contestlanguages">
				<tr>
					<td><fmt:message key="page.globalsettings.flags.balloon" /></td>
					<td><form:checkbox path="balloon" /></td>
				</tr>
				<tr>
					<td><fmt:message key="page.globalsettings.flags.gallery" /></td>
					<td><form:checkbox path="gallery" /></td>
				</tr>
				<tr>
					<td><fmt:message key="page.globalsettings.flags.saris" /></td>
					<td><form:checkbox path="saris" /></td>
				</tr>
				<tr>
					<td><fmt:message key="page.globalsettings.flags.stats" /></td>
					<td><form:checkbox path="show_stats" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.statsout" /></td>
					<td><form:checkbox path="show_stats_out" /></td>
				</tr>
				<tr>
					<td><fmt:message key="page.globalsettings.flags.status" /></td>
					<td><form:checkbox path="show_status" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.statusout" /></td>
					<td><form:checkbox path="show_status_out" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.board" /></td>
					<td><form:checkbox path="show_scoreboard" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.boardout" /></td>
					<td><form:checkbox path="show_scoreboard_out" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.allrg" /></td>
					<td><form:checkbox path="allow_registration" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.unfreezeauto" /></td>
					<td><form:checkbox path="unfreeze_auto" /></td>
				</tr>

				<tr>
					<td><fmt:message key="page.globalsettings.flags.problem" /></td>
					<td><form:checkbox path="show_problem_out" /></td>
				</tr>

				<tr>
					<td>Show On Test</td>
					<td><form:checkbox path="show_ontest" /></td>
				</tr>

			</table>
		</fieldset>


		<fieldset style="width: 400px;">
			<legend>
				<fmt:message key="page.globalsettings.medals" />
			</legend>
			<table class="contestlanguages">
				<tr>
					<td><fmt:message key="page.general.gold" /></td>
					<td><form:input path="gold" /></td>
					<td><span class="label label-danger"><form:errors
								path="gold" /></span></td>
				</tr>
				<tr>
					<td><fmt:message key="page.general.silver" /></td>
					<td><form:input path="silver" /></td>
					<td><span class="label label-danger"><form:errors
								path="silver" /></span></td>
				</tr>
				<tr>
					<td><fmt:message key="page.general.bronze" /></td>
					<td><form:input path="bronze" /></td>
					<td><span class="label label-danger"><form:errors
								path="bronze" /></span></td>
				</tr>
			</table>
		</fieldset>
		<input type="submit" name="but"
			value="<spring:message code="button.update"/>" />
	</form:form>
</div>


