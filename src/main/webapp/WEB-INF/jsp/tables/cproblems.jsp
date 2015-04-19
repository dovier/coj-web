<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="problem" name="problems" class="volume" requestURI="">
	<display:column titleKey="tablehdr.id" headerClass="headid">
		<c:choose>
			<c:when test="${contest.style eq 1}">
				<c:out value="${problem.letter}" />
			</c:when>
			<c:otherwise>
				<c:out value="${problem.pid}" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<c:if test="${contest.balloon}">
		<display:column titleKey="tablehdr.color" headerClass="headid">
			<span style="color:${problem.balloonColor}"><i
				class="shadow fa fa-circle"></i></span>
		</display:column>
	</c:if>
	<authz:authorize ifNotGranted="ROLE_ANONYMOUS">
		<display:column titleKey="tablehdr.solved" headerClass="headsolved">
			<c:choose>
				<c:when test="${problem.solved == true}">
					<i class="green fa fa-check-circle"></i>
				</c:when>
				<c:when test="${problem.unsolved == true}">
					<i class="red fa fa-minus-circle"></i>
				</c:when>
			</c:choose>
		</display:column>
	</authz:authorize>
	<display:column titleKey="tablehdr.title" headerClass="headtitle">
		<c:choose>
			<c:when test="${problem.see == true}">
				<a
					href="<c:url value="cproblem.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>">${problem.title}</a>
			</c:when>
			<c:otherwise>
                                    ${problem.title}
                                </c:otherwise>
		</c:choose>
	</display:column>
	<display:column property="accu" titleKey="tablehdr.ac"
		headerClass="headac" />
	<c:if test="${contest.style eq 4}">
		<display:column property="level" titleKey="tablehdr.level"
			headerClass="headlevel" />
	</c:if>
	<c:if test="${contest.style eq 3}">
		<display:column titleKey="tablehdr.score" headerClass="headscore">
			<fmt:formatNumber value="${problem.points}" pattern="#.##" />
		</display:column>
	</c:if>
</display:table>