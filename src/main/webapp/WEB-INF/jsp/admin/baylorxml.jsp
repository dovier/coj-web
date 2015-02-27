<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
			<td width="10%"><fmt:message key="page.managecontest.link.mp" /></td>
			<td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.bx" /></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>
	<form:form method="post" enctype="multipart/form-data" commandName="contest">
		<input type='hidden' name='cid' value="${cid}" />
		<fieldset style="border: none;">
			<table class="login">
				<tr>
					<td>Baylor empty XML Template</td>
				<td><input type="file" name="xml" size="40px" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
				</tr>
				<tr>
					<td><span class="label label-danger"><form:errors path="xml" /></span></td>
				</tr>
			</table>
		</fieldset>
		<br />
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
		<br />
	</form:form>
</div>

