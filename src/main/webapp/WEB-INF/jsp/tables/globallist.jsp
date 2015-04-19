<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="contest" name="contests" requestURI="" class="volume">
	<display:column titleKey="tablehdr.rc" property="cid"
		href="/contest/contestview.xhtml" paramId="cid" paramProperty="cid"
		class="headid" />
	<display:column titleKey="tablehdr.creator" property="username"
		href="/user/useraccount.xhtml" paramId="username"
		paramProperty="username" class="headuser" />
	<display:column titleKey="tablehdr.template" property="name" />
	<display:column titleKey="tablehdr.access">
		<c:choose>
			<c:when test="${contest.is_public == false}">
				<a title="<spring:message code="altval.private"/>"><i
					class="fa fa-lock fa-lg"></i></a>
			</c:when>
			<c:otherwise>
				<a title="altval.open"><i class="fa fa-unlock fa-lg"></i></a>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.status">
		<c:choose>
			<c:when test="${contest.running == true}">
				<span class="label label-danger"><spring:message
						code="fieldval.running" /></span>
			</c:when>
			<c:when test="${contest.coming == true}">
				<span class="label label-danger"><spring:message
						code="fieldval.coming" /></span>
			</c:when>
			<c:when test="${contest.past == true}">
				<spring:message code="fieldval.past" />
			</c:when>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.start" class="date">
		<a
			href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.enddate.date}&month=${contest.enddate.month + 1}&year=${contest.enddate.year +1900}&hour=${contest.enddate.hours}&min=${contest.enddate.minutes}&sec=${contest.enddate.seconds}&p1=99"
			target="new"> <fmt:formatDate value="${contest.enddate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</a>
	</display:column>
	<display:column titleKey="tablehdr.duration" property="duration" />
	<display:column titleKey="tablehdr.users">
            ${contest.total_users}
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
			<c:if test="${contest.is_public == true || isadmin == true}">
				<c:choose>
					<c:when
						test="${isadmin == true || (contest.total_users < 30 && (contest.coming == true || contest.running == true))}">
						<br />
						<a href="joinvirtual.xhtml?vid=${contest.vid}"> <spring:message
								code="link.join" />
						</a>
					</c:when>
				</c:choose>
			</c:if>
		</authz:authorize>
	</display:column>
</display:table>