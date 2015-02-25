<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<h2 class="postheader">
	<fmt:message key="page.header.admin.overview" />
</h2>

<div class="postcontent">
	<table class="navigating" width="100%">
		<tr>
			<td width="10%"><a href="managecontest.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><a href="globalsettings.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="contestproblems.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.ov" /></td>
		</tr>
	</table>
	<form:form method="post" commandName="contest">
		<div style="height: 100%">
			<form:textarea path="overview" id="code" cssClass="des" rows="15" cssStyle="width: 99%;"></form:textarea>
			<br />
		</div>

		<br />
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
		<br />
	</form:form>
</div>
