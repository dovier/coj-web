<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<h2 class="postheader">
	<fmt:message key="page.header.admin.contestglobalflags" />
</h2>
<div class="postcontent">

	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.gf" /></td>
			<td width="10%"><a href="globalsettings.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="contestproblems.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>
	<form:form method="post" commandName="globalFlags">
		<input type="hidden" name="cid" value="${cid}" />
		<table class="createnewuser">
			<tbody>
				<tr>
					<td style="align:right">Disabled Mail Notifications</td>
					<td><form:checkbox path="mailNotificationDisabled" /></td>
				</tr>
				<tr>
					<td style="align:right">Enabled Mail</td>
					<td><form:checkbox path="enabled_mail" /></td>
				</tr>

				<tr>
					<td style="align:right">Enabled Source Code View</td>
					<td><form:checkbox path="enabled_source_code_view" /></td>
				</tr>

				<tr>
					<td style="align:right">Enabled SubmissionJudge 24h</td>
					<td><form:checkbox path="enabled_submission" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" id="submit" value="Update" /></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
