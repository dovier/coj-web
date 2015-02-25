<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><a href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="<c:url  value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.mu" /></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>
	<form:form method="post" onsubmit="return SeleccionarRangosContest();" commandName="contest">
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
		<c:if test="${contest.grouped eq true}">
			<fieldset style="width: 400px;">
				<legend>
					<fmt:message key="page.managecontest.style" />
				</legend>
				<table class="contestlanguages">
					<tr>
						<td colspan="2"><fmt:message key="page.managecontest.guestgroup" /></td>
						<td><form:input path="guestGroup" /></td>
					</tr>
					<tr>
						<td colspan="2"><fmt:message key="page.managecontest.group" />:</td>
						<td><form:input path="groupd" /></td>
					</tr>
				</table>
			</fieldset>
		</c:if>
		<table class="contestlanguages">
			<tr>
				<td><fmt:message key="contestusers.cu" /></td>
				<td></td>
				<td><fmt:message key="contestusers.all" /></td>
			</tr>
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
					</tr>
				</c:when>

				<c:when test="${contest.registration eq 2}">
					<tr>

						<td rowspan="2"><form:select path="usersids" id="contest_users" size="14" cssClass="login" cssStyle="width: 310px; border: 1px solid #577A5A;" multiple="true">

							</form:select></td>

						<td>
							<button name="boton" type="button" onclick="addremove('contest_users','allusers');">
								<i class="fa fa-arrow-right"></i>
							</button>
						</td>

						<td rowspan="2"><form:select path="" id="allusers" size="14" cssClass="login" cssStyle="width: 310px; border: 1px solid #577A5A;" multiple="true">
								<form:options items="${allusers}" itemValue="uid" itemLabel="username" />
							</form:select></td>

					</tr>
					<tr>
						<td>
							<button name="boton" type="button" onclick="addremove('allusers','contest_users');">
								<i class="fa fa-arrow-left"></i>
							</button>
						</td>
					</tr>
				</c:when>
			</c:choose>
		</table>

		<table class="contestlanguages">
			<tr>
				<td><fmt:message key="contestjudges.cu" /></td>
				<td></td>
				<td><fmt:message key="contestjudges.all" /></td>
			</tr>


			<tr>

				<td rowspan="2"><form:select path="judgesids" id="contest_users_clarification" size="14" cssClass="login" cssStyle="width: 310px; border: 1px solid #577A5A;" multiple="true">
						<form:options items="${contest.judges}" itemValue="uid" itemLabel="username" />
					</form:select></td>

				<td>
					<button name="boton" type="button" onclick="addremove('contest_users_clarification','all_judge');">
						<i class="fa fa-arrow-right"></i>
					</button>
				</td>

				<td rowspan="2"><form:select path="" id="all_judge" size="14" cssClass="login" cssStyle="width: 310px; border: 1px solid #577A5A;" multiple="true">
						<form:options items="${alljudges}" itemLabel="username" itemValue="uid" />
					</form:select></td>
			</tr>
			<tr>
				<td>
					<button name="boton" type="button" onclick="addremove('all_judge','contest_users_clarification');">
						<i class="fa fa-arrow-left"></i>
					</button>
				</td>
			</tr>
		</table>
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
	</form:form>
	<table class="contestlanguages">
		<tr>
			<td>Delete All</td>
			<td><a href="deleteusercontest.xhtml?all=true&cid=${contest.cid}">Delete</a></td>
		</tr>
		<c:forEach items="${contest.users}" var="user">
			<tr>
				<td><a href="manageuser.xhtml?username=${user.username}">${user.username}</a></td>
				<td>${user.nick}</td>

				<td>${user.group}</td>
				<td><a href="deleteusercontest.xhtml?uid=${user.uid}&cid=${contest.cid}">Delete</a></td>

				<td><a href="repointuser.xhtml?username=${user.username}&cid=${contest.cid}">Repoint</a></td>

			</tr>
		</c:forEach>
	</table>

</div>


