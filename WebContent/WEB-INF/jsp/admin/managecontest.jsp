<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->


<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><fmt:message key="page.managecontest.link.mc" /></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><a href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="<c:url  value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>

	<form:form method="post" commandName="contest">
		<table class="contestsetting">
			<tr>
				<td><fmt:message key="page.managecontest.name" />:</td>
				<td><form:input path="name" /></td>
				<td colspan="3"><span class="label label-danger"><form:errors path="name" /></span></td>
			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.registration" />:</td>
				<td><form:select path="registration">
						<form:option value="0">
							<fmt:message key="page.managecontest.register.free" />
						</form:option>
						<form:option value="1">
							<fmt:message key="page.managecontest.register.limit" />
						</form:option>
						<form:option value="2">
							<fmt:message key="page.managecontest.register.admin" />
						</form:option>
					</form:select></td>
			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.rglimit" />:</td>

				<td>
					<table class="login">
						<tr>
							<td colspan="3"><form:select path="ryear">
									<c:forEach begin="1930" step="1" end="${contest.ryear + 1}" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="rmonth">
									<c:forEach begin="1" step="1" end="12" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="rday">
									<c:forEach begin="1" step="1" end="31" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
					</table>
				</td>

				<td><fmt:message key="page.managecontest.inittime" />:</td>
				<td>
					<table class="login">
						<tr>
							<td colspan="3"><form:select path="rhour">
									<c:forEach begin="0" step="1" end="23" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="rminutes">
									<c:forEach begin="0" step="1" end="59" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="rseconds">
									<c:forEach begin="0" step="1" end="59" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
					</table>
				</td>

				<td><span class="label label-danger"><form:errors path="rglimit" /></span></td>

			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.initdate" />:</td>
				<td>
					<table class="login">
						<tr>
							<td colspan="3"><form:select path="iyear">
									<c:forEach begin="1930" step="1" end="${contest.iyear + 1}" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="imonth">
									<c:forEach begin="1" step="1" end="12" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="iday">
									<c:forEach begin="1" step="1" end="31" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
					</table>
				</td>
				<td><fmt:message key="page.managecontest.inittime" />:</td>
				<td>
					<table class="login">
						<tr>
							<td colspan="3"><form:select path="ihour">
									<c:forEach begin="0" step="1" end="23" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="iminutes">
									<c:forEach begin="0" step="1" end="59" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="iseconds">
									<c:forEach begin="0" step="1" end="59" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
					</table>
				</td>
				<td><span class="label label-danger"><form:errors path="initdate" /></span></td>
			</tr>



			<tr>
				<td><fmt:message key="page.managecontest.enddate" />:</td>
				<td>
					<table class="login">
						<tr>
							<td colspan="3"><form:select path="eyear">
									<c:forEach begin="1930" step="1" end="${contest.eyear + 1}" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="emonth">
									<c:forEach begin="1" step="1" end="12" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="eday">
									<c:forEach begin="1" step="1" end="31" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
					</table>
				</td>
				<td><fmt:message key="page.managecontest.inittime" />:</td>
				<td>
					<table class="login">
						<tr>
							<td colspan="3"><form:select path="ehour">
									<c:forEach begin="0" step="1" end="23" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="eminutes">
									<c:forEach begin="0" step="1" end="59" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select> <form:select path="eseconds">
									<c:forEach begin="0" step="1" end="59" var="value">
										<form:option value="${value}">${value}</form:option>
									</c:forEach>
								</form:select></td>
						</tr>
					</table>
				</td>
				<td><span class="label label-danger"><form:errors path="enddate" /></span></td>
			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.style" />:</td>
				<td><form:select path="style">
						<form:option value="0">none</form:option>
						<form:options items="${styles}" itemValue="sid" itemLabel="name" />
					</form:select></td>
				<td colspan="3"><span class="label label-danger"><form:errors path="style" /></span></td>
			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.users" />:</td>
				<td><form:select path="contestant">
						<form:option value="1">
							<fmt:message key="page.managecontest.team" />
						</form:option>
						<form:option value="2">
							<fmt:message key="page.managecontest.user" />
						</form:option>
						<form:option value="3">Both</form:option>
					</form:select></td>
			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.enabled" />:</td>
				<td><form:checkbox path="enabled" /></td>
				<td colspan="3"><span class="label label-danger"><form:errors path="enabled" /></span></td>
			</tr>

			<tr>
				<td><fmt:message key="page.managecontest.vtemplate" />:</td>
				<td><form:checkbox path="vtemplate" /></td>
				<td colspan="3"><span class="label label-danger"><form:errors path="vtemplate" /></span></td>
			</tr>
			<tr>
				<td>Status(Block):</td>
				<td><form:checkbox path="blocked" /></td>
				<td colspan="3"><span class="label label-danger"><form:errors path="blocked" /></span></td>
			</tr>
			<tr>
				<td>Grouped:</td>
				<td><form:checkbox path="grouped" /></td>
				<td colspan="3"><span class="label label-danger"><form:errors path="grouped" /></span></td>
			</tr>

		</table>



		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
	</form:form>
</div>