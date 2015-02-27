<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="course" name="courses" requestURI="" class="volume">
	<display:column title="Course" property="course_id" class="headid" />
	<display:column titleKey="tablehdr.creator" property="username"
		href="/user/useraccount.xhtml" paramId="username"
		paramProperty="username" class="headuser" />
	<display:column titleKey="tablehdr.template" property="name"
		href="/schoolar/course.xhtml" paramId="course_id"
		paramProperty="course_id" />
	<display:column titleKey="tablehdr.access">
		<c:choose>
			<c:when test="${course.ispublic == false}">
				<img alt="<spring:message code="altval.private"/>"
					src="<c:url value="/images/private.png" />"
					title="<spring:message code="altval.private"/>" />
			</c:when>
			<c:otherwise>
				<img alt="<spring:message code="altval.open"/>"
					src="<c:url value="/images/public.png" />"
					title="<spring:message code="altval.open"/>" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.status">
		<c:choose>
			<c:when test="${course.running == true}">
				<span class="label label-danger"><spring:message
						code="fieldval.running" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><spring:message
						code="fieldval.coming" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.start" class="date">
		<a
			href="http://timeanddate.com/worldclock/fixedtime.html?day=${course.initdate.date}&month=${course.initdate.month + 1}&year=${course.initdate.year +1900}&hour=${course.initdate.hours}&min=${course.initdate.minutes}&sec=${course.initdate.seconds}&p1=99"
			target="new"> <fmt:formatDate value="${course.initdate}"
				pattern="yyyy-MM-dd HH:mm:ss" />
		</a>
	</display:column>
	<display:column titleKey="tablehdr.users">
            ${course.total_users}
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
			<c:if test="${course.ispublic == true || isadmin == true}">
				<c:choose>
					<c:when
						test="${isadmin == true || (course.total_chapters < 30 && (course.coming == true || course.running == true))}">
						<br />
						<a href="joincourse.xhtml?course_id=${course.course_id}"> <spring:message
								code="link.join" />
						</a>
					</c:when>
				</c:choose>
			</c:if>
		</authz:authorize>
	</display:column>
</display:table>
