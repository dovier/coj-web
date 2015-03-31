<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="user" name="usersin" requestURI="" class="volume">
	<display:column title="fullname" property="name" />
	<display:column title="username" property="username" />
	<display:column title="email" property="email" />
	<display:column title="ban status">
		<c:choose>
			<c:when test="${user.banned == true}">
				<font style="color: red"> YES</span>
			</c:when>
			<c:otherwise>
                    NO <a
					href="banuser.xhtml?course_id=${course.course_id}&username=${user.username}">ban</a>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column title="Delete">
		<a
			href="removecourseuser.xhtml?course_id=${course.course_id}&username=${user.username}">delete</a>
	</display:column>
</display:table>