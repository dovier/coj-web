<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.gs" /></td>
			<td width="10%"><a href="<c:url  value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}" />"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>

	<form:form method="post" commandName="contest">
		<fieldset style="width: 400px;">
			<legend>
				<fmt:message key="page.managecontest.style" />
			</legend>
			<table class="contestlanguages">
				<tr>
					<td><fmt:message key="page.managecontest.style" />:</td>
					<td><form:input path="style" readonly="true" /></td>
				</tr>
				<tr>
					<td><fmt:message key="page.managecontest.registration" />:</td>
					<td><form:input path="registration" readonly="true" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset style="width: 400px;">
			<legend>
				<fmt:message key="page.managecontest.style" />
			</legend>
			<table class="contestlanguages">
				<c:choose>
					<c:when test="${contest.style eq 0}">
						<tr>
							<td><span class="label label-danger">Undefined Property</span></td>
						</tr>
					</c:when>
					<c:when test="${contest.style eq 1}">
						<tr>
							<td><fmt:message key="page.globalsettings.acm.penal" /></td>
							<td><form:input path="penalty" /></td>
							<td><span class="label label-danger"><form:errors path="penalty" /></span></td>
						</tr>
						<tr>
							<td><fmt:message key="page.globalsettings.acm.frtime" /></td>
							<td><form:input path="frtime" /></td>
							<td><span class="label label-danger"><form:errors path="frtime" /></span></td>
						</tr>
						<tr>
							<td><fmt:message key="page.globalsettings.acm.dead" /></td>
							<td><form:input path="deadtime" /></td>
							<td><span class="label label-danger"><form:errors path="deadtime" /></span></td>
						</tr>
						<tr>
							<td><fmt:message key="page.globalsettings.acm.unfreeze" /></td>
							<td><form:input path="unfreeze_time" /></td>
							<td><span class="label label-danger"><form:errors path="unfreeze_time" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.style eq 2}">
						<tr>
							<td><fmt:message key="page.globalsettings.ioi.task" /></td>
							<td><form:input path="ioimark" /></td>
							<td><span class="label label-danger"><form:errors path="ioimark" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.style eq 3}">
						<tr>
							<td><fmt:message key="page.globalsettings.free.points" /></td>
							<td><form:input path="ppoints" /></td>
							<td><span class="label label-danger"><form:errors path="ppoints" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.style eq 4}">
						<tr>
							<td><spring:message code="fieldhdr.levels" /></td>
							<td><form:input path="levels" /></td>
							<td><span class="label label-danger"><form:errors path="levels" /></span></td>
						</tr>

						<tr>
							<td><spring:message code="fieldhdr.acbylevels" /></td>
							<td><form:input path="acbylevels" /></td>
							<td><span class="label label-danger"><form:errors path="acbylevels" /></span></td>
						</tr>

						<tr>
							<td><spring:message code="fieldhdr.aclimit" /></td>
							<td><form:input path="aclimit" /></td>
							<td><span class="label label-danger"><form:errors path="aclimit" /></span></td>
						</tr>

						<tr>
							<td><spring:message code="fieldhdr.pointsbyproblem" /></td>
							<td><form:input path="ppoints" /></td>
							<td><span class="label label-danger"><form:errors path="ppoints" /></span></td>
						</tr>

					</c:when>

				</c:choose>

			</table>
		</fieldset>

		<fieldset style="width: 400px;">
			<legend>
				<fmt:message key="page.managecontest.registration" />
			</legend>
			<table class="contestlanguages">
				<c:choose>
					<c:when test="${contest.registration eq 0}">
						<tr>

							<td><span class="label label-danger"> <fmt:message key="page.managecontest.register.free" />
							</span></td>

						</tr>
					</c:when>

					<c:when test="${contest.registration eq 1}">
						<tr>
							<td><fmt:message key="page.globalsettings.rg.limit" /></td>
							<td><form:input path="total_users" /></td>
							<td><span class="label label-danger"><form:errors path="total_users" /></span></td>
						</tr>
					</c:when>

					<c:when test="${contest.registration eq 2}">
						<tr>

							<td><span class="label label-danger"> <fmt:message key="page.managecontest.register.admin" />
							</span></td>

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
					<td><span class="label label-danger"><form:errors path="gold" /></span></td>
				</tr>
				<tr>
					<td><fmt:message key="page.general.silver" /></td>
					<td><form:input path="silver" /></td>
					<td><span class="label label-danger"><form:errors path="silver" /></span></td>
				</tr>
				<tr>
					<td><fmt:message key="page.general.bronze" /></td>
					<td><form:input path="bronze" /></td>
					<td><span class="label label-danger"><form:errors path="bronze" /></span></td>
				</tr>
			</table>
		</fieldset>
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
	</form:form>
</div>


