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
			<td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc" /></a></td>
			<td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gf" /></a></td>
			<td width="10%"><a href="globalsettings.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs" /></a></td>
			<td width="10%"><a href="contestproblems.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp" /></a></td>
			<td width="10%"><fmt:message key="page.managecontest.link.mpc" /></td>
			<td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
			<td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
			<td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml" /></a></td>
			<td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu" /></a></td>
			<td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
			<td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ov" /></a></td>
		</tr>
	</table>
	<form:form method="post" commandName="contest">
		<table>
			<c:forEach items="${contest.problems}" var="problem">
				<tr>
					<td>${problem.title}</td>
					<td>${problem.letter}</td>
					<td><input type='text' class='colors' id='${problem.pid}' name='colors' value="${problem.balloonColor}" /></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" name="but" value="<spring:message code="button.update"/>" />
	</form:form>
</div>
<script>
	function spectrumInit() {

		$(".colors").each(
				function() {
					$(this).spectrum(
							{
								//showPalette : true,
								/*palette : [
										[ '#FFFFFF', '#000000', '#Fcbd00' ],
										[ '#FFF000', '#123456',
												'#954555' ] ],*/
								showInput : true,
								allowEmpty : true,
								clickoutFiresChange : true,
								change : function(color) {
									$("#"+$(this).attr('id')).attr("value",color.toHexString()); // #ff0000
								}
							});
				});
	};
	spectrumInit();
</script>