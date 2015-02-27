<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel='stylesheet' href="<c:url value="/css/spectrum.css" />" />
<script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/spectrum.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">

	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><a href="globalsettings.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="contestproblems.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="manageproblemcolors.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.iiu" /></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>
	<form:form method="post" enctype="multipart/form-data" commandName="contest">
		<input type='hidden' name='cid' value="${cid}" />
		<p>Import file from <a href="http://icpc.baylor.edu/">Baylor ICPC site</a>. The Contest file must be downloaded, decompressed and the .tab files inside upload in their corresponding fields below.</p>
		<table>
			<tr>
			<td>Team name prefix</td>
				<td><input type='text' name='prefix' value="" /></td>
			</tr>
			<tr>
			<td>Warm up contest id</td>
				<td><input type='text' name='warmupCid' value="" /></td>
			</tr>
			<tr>
			<td>Person.tab</td>
				<td><input type="file" name="personFile" size="40px" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
			<td>School.tab</td>
				<td><input type="file" name="schoolFile" size="40px" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
			<td>Site.tab</td>
				<td><input type="file" name="siteFile" size="40px" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
			<td>Team.tab</td>
				<td><input type="file" name="teamFile" size="40px" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
			<td>TeamPerson.tab</td>
				<td><input type="file" name="teamPersonFile" size="40px" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
		</table>
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
	</form:form>
</div>


