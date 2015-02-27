<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="contest" name="contests" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="vid" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="username" titleKey="tablehdr.user"
		headerClass="headuser" href="/user/useraccount.xhtml"
		paramId="username" paramProperty="username" />
	<display:column property="cid" titleKey="tablehdr.user"
		headerClass="headuser" href="/contest/contestview.xhtml" paramId="cid"
		paramProperty="cid" />
	<display:column titleKey="tablehdr.access">
		<c:choose>
			<c:when test="${contest.ispublic == true}">
				<a title="<fmt:message key="messages.general.public"/>"><i
					class="fa fa-unlock fa-lg"></i></a>
			</c:when>
			<c:otherwise>
				<a title="<spring:message code="messages.general.private"/>"><i
					class="fa fa-lock fa-lg"></i></a>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column property="father" titleKey="tablehdr.father" />
	<display:column titleKey="tablehdr.status">
		<c:choose>
			<c:when test="${contest.running == true}">
				<font><fmt:message key="contest.status.rn" /></font>
			</c:when>
			<c:when test="${contest.coming == true}">
				<font><fmt:message key="contest.status.com" /></font>
			</c:when>
			<c:when test="${contest.past == true}">
				<fmt:message key="contest.status.end" />
			</c:when>
		</c:choose>
	</display:column>
	<display:column property="ctime" titleKey="tablehdr.creation"
		headerClass="headdate" />
	<display:column property="itime" titleKey="tablehdr.start"
		headerClass="headdate" />
	<display:column property="etime" titleKey="tablehdr.end"
		headerClass="headdate" />
	<display:column titleKey="tablehdr.delete">
		<a
			href="<c:url value="/admin/deletevirtual.xhtml?vid=${contest.vid}&uid=${contest.username}"/>"
			title="<fmt:message key="messages.general.go"/>"><i
			class="fa fa-trash"></i></a>
	</display:column>
</display:table>