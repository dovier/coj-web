<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submission" name="submissions" class="volume"
	requestURI="" defaultsort="0" defaultorder="ascending">
	<display:column titleKey="tablehdr.id" headerClass="headid">
		<c:choose>
			<c:when test="${submission.authorize==true}">
				<a href="<c:url value="vsubmission.xhtml?id=${submission.sid}"/>"><c:out
						value="${submission.sid}" /></a>
			</c:when>
			<c:when test="${submission.authorize==false}">
				<c:out value="${submission.sid}" />
			</c:when>
		</c:choose>
	</display:column>
	<display:column property="date" titleKey="tablehdr.date"
		headerClass="headdate" />
	<display:column property="username" titleKey="tablehdr.user"
		headerClass="headuser" />
	<display:column property="pid" titleKey="tablehdr.prob"
		headerClass="headprob" href="vproblem.xhtml" paramId="pid"
		paramProperty="pid" />
	<display:column titleKey="tablehdr.judgment" headerClass="headjudgment">
		<c:if test="${!empty submission.statusName}">
			<c:choose>
				<c:when test="${submission.authorize==false}">
					<label class="sub${submission.statusClass}">
						${submission.status} </label>
				</c:when>
				<c:when test="${submission.authorize==true}">
					<a href="compileinfo.xhtml?sid=${submission.sid}">${submission.status}</a>
				</c:when>
			</c:choose>
		</c:if>
		<c:if test="${empty submission.statusName}">
			<label class="sub${submission.statusClass}">
				${submission.status} </label>
			<c:if test="${submission.ontest == true}">
				<br />
				<i><sub><spring:message code="tableval.test" />
						${submission.firstWaCase}</sub></i>
			</c:if>
		</c:if>
	</display:column>
	<display:column property="timeUsed" titleKey="tablehdr.time"
		headerClass="headtime" />
	<display:column property="memoryMB" titleKey="tablehdr.mem"
		headerClass="headmem" />
	<display:column property="fontMB" titleKey="tablehdr.size"
		headerClass="headsize" />
	<display:column property="lang" titleKey="tablehdr.lang"
		headerClass="headlang" />
</display:table>