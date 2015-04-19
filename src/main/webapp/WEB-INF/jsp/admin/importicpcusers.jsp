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

	<ul class="nav nav-pills">
		<li><a href="<c:url value="managecontest.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.mc" /></a></li>
		<li><a
			href="<c:url value="contestglobalflags.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.gf" /></a></li>
		<li><a href="<c:url value="globalsettings.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.gs" /></a></li>
		<li><a href="<c:url value="contestproblems.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.mp" /></a></li>
		<li><a
			href="<c:url value="contestproblemcolors.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.mpc" /></a></li>
		<li class="active"><a
			href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.iiu" /></a></li>
		<li><a href="<c:url value="baylorxml.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.bx" /></a></li>
		<li><a href="<c:url value="contestlanguages.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.ml" /></a></li>
		<li><a href="<c:url value="contestusers.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.mu" /></a></li>
		<li><a href="<c:url value="contestawards.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.aw" /></a></li>
		<li><a href="<c:url value="contestoverview.xhtml?cid=${cid}"/>"><fmt:message
					key="page.managecontest.link.ov" /></a></li>
		<li><a
			href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.img" /></a></li>
	</ul>
	<form:form method="post" enctype="multipart/form-data"
		commandName="contest">
		<input type='hidden' name='cid' value="${cid}" />
		<p>
			Import file from <a href="http://icpc.baylor.edu/">Baylor ICPC
				site</a>. The Contest file must be downloaded, decompressed and the .tab
			files inside upload in their corresponding fields below.
		</p>
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
				<td><input type="file" name="personFile" size="40px" /> <a><i
						class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
				<td>School.tab</td>
				<td><input type="file" name="schoolFile" size="40px" /> <a><i
						class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
				<td>Site.tab</td>
				<td><input type="file" name="siteFile" size="40px" /> <a><i
						class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
				<td>Team.tab</td>
				<td><input type="file" name="teamFile" size="40px" /> <a><i
						class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
			<tr>
				<td>TeamPerson.tab</td>
				<td><input type="file" name="teamPersonFile" size="40px" /> <a><i
						class="fa fa-asterisk"
						title="<spring:message code="mandatory.field"/>"></i></a></td>
			</tr>
		</table>
		<input type="submit" name="but"
			value="<spring:message code="button.update"/>" />
	</form:form>
</div>


