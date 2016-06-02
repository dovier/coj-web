<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submission" name="submissions" class="volume"
	requestURI="" defaultsort="0" defaultorder="ascending">
	<display:column titleKey="tablehdr.id" headerClass="headid">
		<c:choose>
			<c:when test="${submission.authorize==true}">
				<a
					href="<c:url value="csubmission.xhtml?id=${submission.sid}&cid=${contest.cid}"/>"><c:out
						value="${submission.sid}" /></a>
			</c:when>
			<c:when test="${submission.authorize==false}">
				<c:out value="${submission.sid}" />
			</c:when>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.date" headerClass="headdate">
		<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
			value="${submission.date}" />
	</display:column>
	<display:column property="username" titleKey="tablehdr.user"
		headerClass="headuser" href="cuseraccount.xhtml?cid=${contest.cid}"
		paramId="uid" paramProperty="username" style="text-transform:none" />
	<display:column titleKey="tablehdr.prob" headerClass="headprob">
		<c:choose>
			<c:when test="${contest.style == 1}">
				<a
					href="<c:url value="cproblem.xhtml?cid=${contest.cid}&pid=${submission.pid}"/>"
					title="${submission.problemTitle}"><c:out
						value="${submission.letter}" /></a>
			</c:when>
			<c:otherwise>
				<a
					href="<c:url value="cproblem.xhtml?cid=${contest.cid}&pid=${submission.pid}"/>"
					title="${submission.problemTitle}"><c:out
						value="${submission.pid}" /></a>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.judgment" headerClass="headjudgment">
		<c:if test="${!empty submission.statusName}">
			<c:choose>
				<c:when test="${submission.authorize==false}">
					<label class="sub${submission.statusClass}">
						${submission.status} </label>
				</c:when>
				<c:when test="${submission.authorize==true}">
					<a
						href="ccompileinfo.xhtml?sid=${submission.sid}&cid=${contest.cid}">${submission.status}</a>
				</c:when>
			</c:choose>
		</c:if>
		<c:if test="${empty submission.statusName}">
			<label class="sub${submission.statusClass}">
				${submission.status} </label>
			<c:if test="${contest.show_ontest and submission.ontest}">
				<br />
				<c:if test="${ submission.firstWaCase != -1 }">
					<i><sub><spring:message code="tableval.test" />
							${submission.firstWaCase+1}</sub></i>
				</c:if>
			</c:if>
		</c:if>
	</display:column>
	<display:column titleKey="tablehdr.time"
		headerClass="headtime">
			<c:if test="${submission.timeUsed == -1}">
				...
			</c:if>
			<c:if test="${submission.timeUsed != -1}">
				${submission.timeUsed}
			</c:if>
		</display:column>
	<display:column property="memoryMB" titleKey="tablehdr.mem"
		headerClass="headmem" />
	<display:column property="fontMB" titleKey="tablehdr.size"
		headerClass="headsize" />
	<display:column property="lang" titleKey="tablehdr.lang"
		headerClass="headlang" />
</display:table>