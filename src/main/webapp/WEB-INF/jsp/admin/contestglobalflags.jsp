<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<h2 class="postheader">
	<fmt:message key="page.header.admin.contestglobalflags" />
</h2>
<div class="postcontent">

	<ul class="nav nav-pills">
		<li><a
			href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mc" /></a></li>
		<li class="active"><a
			href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gf" /></a></li>
		<li><a
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
			href="<c:url value="contestoverview.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.ov" /></a></li>
		<li><a
			href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.img" /></a></li>
	</ul>
	<form:form method="post" commandName="globalFlags">
		<input type="hidden" name="cid" value="${cid}" />
		<table class="createnewuser">
			<tbody>
				<tr>
					<td style="align: right">Disabled Mail Notifications</td>
					<td><form:checkbox path="mailNotificationDisabled" /></td>
				</tr>
				<tr>
					<td style="align: right">Enabled Mail</td>
					<td><form:checkbox path="enabled_mail" /></td>
				</tr>

				<tr>
					<td style="align: right">Enabled Source Code View</td>
					<td><form:checkbox path="enabled_source_code_view" /></td>
				</tr>

				<tr>
					<td style="align: right">Enabled SubmissionJudge 24h</td>
					<td><form:checkbox path="enabled_submission" /></td>
				</tr>
				<tr>
					<td><input type="submit" name="submit" id="submit"
						value="Update" /></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>
