<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submission" name="submissions" class="volume"
	requestURI="" defaultsort="0" defaultorder="ascending">
	<display:column titleKey="tablehdr.id" headerClass="headid">
		<c:choose>
			<c:when test="${submission.authorize==true}">
				<a href="<c:url value="submission.xhtml?id=${submission.sid}"/>"><c:out
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
		headerClass="headuser" href="/user/useraccount.xhtml"
		paramId="username" paramProperty="username"
		style="text-transform:none" />
	<display:column property="pid" titleKey="tablehdr.prob"
		headerClass="headprob" href="problem.xhtml" paramId="pid"
		paramProperty="pid" />
	<display:column titleKey="tablehdr.judgment" headerClass="headjudgment">
		<div id="status${submission.sid}">

			<c:if test="${!empty submission.statusName}">

				<c:choose>
					<c:when test="${submission.authorize==false}">
						<label class="sub${submission.statusClass}">
							${submission.status} </label>
					</c:when>
					<c:when test="${submission.authorize==true}">
						<label> <a
							href="/24h/compileinfo.xhtml?sid=${submission.sid}">${submission.status}</a>
						</label>
					</c:when>
				</c:choose>
			</c:if>
			<c:if test="${empty submission.statusName}">
				<label class="sub${submission.statusClass}">
					${submission.status} </label>
			</c:if>
			<c:if test="${submission.ontest}">
				<br />
				<i><sub><spring:message code="tableval.test" />&nbsp;${submission.firstWaCase}</sub></i>
			</c:if>
		</div>
	</display:column>
	<display:column titleKey="tablehdr.time" headerClass="headtime">
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