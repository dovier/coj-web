<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/js/edit_area/edit_area_full.js"></script>

<h2 class="postheader">
	<spring:message code="pagehdr.24hsubmission" />
</h2>
<div class="postcontent">
	<center>
		<table class="volume" border="1px">
			<thead>
				<th><spring:message code="tablehdr.id" /></th>
				<th><spring:message code="tablehdr.date" /></th>
				<th class="user"><spring:message code="tablehdr.user" /></th>
				<th><spring:message code="tablehdr.prob" /></th>
				<th class="result"><spring:message code="tablehdr.judgment" /></th>
				<th><spring:message code="tablehdr.time" /></th>
				<th><spring:message code="tablehdr.mem" /></th>
				<th><spring:message code="tablehdr.size" /></th>
				<th><spring:message code="tablehdr.lang" /></th>
			</thead>
			<tr>
				<td><c:out value="${submission.sid}" /></td>
				<td class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
						value="${submission.date}" /></td>
				<td><a
					href="<c:url value="/user/useraccount.xhtml?username=${submission.username}"/>"><c:out
							value="${submission.username}" /></a></td>
				<td><a
					href="<c:url value="problem.xhtml?pid=${submission.pid}"/>"><c:out
							value="${submission.pid}" /></a></td>
				<td width="15%" id="<c:out value="${submission.statusId}"/>"
					name="<c:out value="${submission.statusName}"/>"><b><c:if
							test="${!empty submission.statusName}">
							<c:choose>
								<c:when test="${submission.status eq 'Compilation Error'}">
									<a href="compileinfo.xhtml?sid=${submission.sid}">${submission.status}</a>
								</c:when>
								<c:otherwise>
									<label class="sub${submission.statusClass}">
										${submission.status} </label>
								</c:otherwise>
							</c:choose>
						</c:if> <c:if test="${empty submission.statusName}">
							<label class="sub${submission.statusClass}">
								${submission.status} </label>
							<c:if test="${submission.ontest == true}">
<!--								<br />
								<i><sub><spring:message code="tableval.test" />
										${submission.firstWaCase}</sub></i>-->
							</c:if>
						</c:if></b></td>
				<td><c:if test="${submission.timeUsed == -1}">
				...
			</c:if> <c:if test="${submission.timeUsed != -1}">
				${submission.timeUsed}
			</c:if></td>
				<td><c:out value="${submission.memoryMB}" /></td>
				<td><c:out value="${submission.fontMB}" /></td>
				<td id="lang"><c:out value="${submission.lang}" /></td>
			</tr>
		</table>
		<c:if
			test="${submission.status eq 'Accepted' or submission.status eq 'Presentation Error'}">
			<table class="volume">
				<thead>
					<th><spring:message code="tablehdr.tests" /></th>
					<th><spring:message code="tablehdr.totaltime" /></th>
					<th><spring:message code="tablehdr.avetime" /></th>
					<th><spring:message code="tablehdr.mintesttime" /></th>
					<th><spring:message code="tablehdr.maxtesttime" /></th>
				</thead>
				<tr>
					<td width="16%">${submission.acTestCases}</td>
					<td width="16%">${submission.timeUsed}</td>
					<td width="17%">${submission.avgTimeUsed}</td>
					<td width="17%">${submission.minTimeUsed}</td>
					<td width="17%">${submission.maxTimeUsed}</td>
				</tr>
			</table>
		</c:if>
		<br />
	</center>
	<div style="clear: both; float: right;">
		<a href="/24h/submit.xhtml?sid=${submission.sid}"> <i
			class="fa fa-edit"></i>&nbsp;<spring:message code="link.edit" /></a> <a
			href="/24h/downloadsource.xhtml?sid=${submission.sid}"><i
			class="fa fa-save"></i>&nbsp;<spring:message code="link.download" /></a>
		<a href="/mail/composemail.xhtml?usernameto=dovier;ybroche&title=Denuncia de fraude&content=ID: ${submission.sid} - PROB: ${submission.pid} - USER: ${submission.username}"<%-- usuario de yonny con problemas ymondelo20;--%>
			title="<spring:message code="link.fraud"/>"><i
			class="red fa fa-minus-circle"></i>&nbsp;<spring:message
				code="link.fraud" /></a>
	</div>
	<div>
		<textarea cols="" rows="" id="code" name="code"
			style="height: 410px; width: 100%; text-align: justify;" readonly><c:out
				value="${submission.code}" /></textarea>
	</div>
	<div class="coj_float_rigth">
		<a href="/24h/status.xhtml" class="btn btn-primary"><spring:message code="button.close" /></a>
	</div>
	<div class="clearfix">

	</div>
</div>
<script type="text/javascript">
	var lang = document.getElementById("lang").innerHTML;
	if (lang == "Pascal") {
		lang = "pas";
	}
	if (lang == "C") {
		lang = "c";
	}
	if (lang == "Text") {
		lang = "robotstxt";
	}
	if (lang == "C++") {
		lang = "cpp";
	}
	editAreaLoader
			.init({
				id : "code" // id of the textarea to transform
				,
				start_highlight : true,
				font_size : "10",
				font_family : "verdana, monospace",
				allow_toggle : false,
				language : "en",
				syntax : lang,
				toolbar : "search, go_to_line,|, select_font, |, highlight, reset_highlight, |, help",
				plugins : "charmap",
				charmap_default : "arrows",
				word_wrap : true
			});

	function toogle_editable(id) {
		editAreaLoader.execCommand(id, 'set_editable', !editAreaLoader
				.execCommand(id, 'is_editable'));
	}
</script>
