<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->


<h2 class="postheader">
	<fmt:message key="pagehdr.contest.awards" />
</h2>
<div class="postcontent">
	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><a href="<c:url value="globalsettings.xhtml?cid=${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="<c:url  value="contestproblems.xhtml?cid=${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.aw" /></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contestawardsflags.cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>

	<form:form method="post" commandName="contestawardsflags">
		<table class="contestsetting">
			<tr>
				<td>Accurate Team:</td>
				<td><form:checkbox path="accurate" /></td>
			</tr>
			<tr>
				<td>Fast Team:</td>
				<td><form:checkbox path="fast" /></td>
			</tr>
			<tr>
				<td>Exclusive Team:</td>
				<td><form:checkbox path="exclusive" /></td>
			</tr>
		</table>
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
	</form:form>
</div>