<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submission" name="submissions" class="volume"
		requestURI="" defaultsort="0" defaultorder="ascending">
		<display:column titleKey="tablehdr.rank" headerClass="headid">
			<c:url value="${submission.rank}" />
		</display:column>
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
		<display:column property="date" titleKey="tablehdr.date"
			headerClass="headdate" />
		<display:column property="username" titleKey="tablehdr.user"
			headerClass="headuser"
			href="/24h/status.xhtml?pid=${submission.pid}&status=ac"
			paramId="username" paramProperty="username" />
		<display:column property="timeUsed" titleKey="tablehdr.time"
			headerClass="headtime" />
		<display:column property="memoryUsed" titleKey="tablehdr.mem"
			headerClass="headmem" />
		<display:column property="font" titleKey="tablehdr.size"
			headerClass="headsize" />
		<display:column property="lang" titleKey="tablehdr.lang"
			headerClass="headlang" />
	</display:table>