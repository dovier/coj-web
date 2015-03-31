<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel='stylesheet' href="<c:url value="/css/spectrum.css" />" />
<script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/spectrum.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/js/randomColor.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
	<fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">

	<ul class="nav nav-pills">
		<li><a
			href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mc" /></a></li>
		<li><a
			href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gf" /></a></li>
		<li><a
			href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gs" /></a></li>
		<li><a
			href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mp" /></a></li>
		<li class="active"><a
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
	<form:form method="post" commandName="contest">
		<button>Randomize</button>
		<table>
			<c:forEach items="${contest.problems}" var="problem">
				<tr>
					<td>${problem.title}</td>
					<td>${problem.letter}</td>
					<td><input type='text' class='colors' id='${problem.pid}'
						name='colors' value="${problem.balloonColor}" /></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" name="but"
			value="<spring:message code="button.update"/>" />
	</form:form>
</div>
<script>
	function randomizeColors(event) {

		$(".colors").each(function() {
			color = randomColor({
				luminosity : 'bright',
				hue : 'random'
			});
			$("#" + $(this).attr('id')).attr("value", color);
		});
		spectrumInit();
		event.preventDefault();
	};

	function spectrumInit() {

		$(".colors").each(
				function() {
					$(this).spectrum(
							{
								showInput : true,
								/*palette : [
										[ '#FFFFFF', '#000000', '#Fcbd00' ],
										[ '#FFF000', '#123456',
												'#954555' ] ],*/
								showInput : true,
								allowEmpty : true,
								clickoutFiresChange : true,
								change : function(color) {
									$("#" + $(this).attr('id')).attr("value",
											color.toHexString()); // #ff0000
								}
							});
				});
	};

	$(function() {

		spectrumInit();
		$("button").click(randomizeColors);
	});
</script>