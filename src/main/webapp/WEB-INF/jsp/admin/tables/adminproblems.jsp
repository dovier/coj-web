<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="problem" name="problems" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="pid" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="title" titleKey="tablehdr.title"
		href="/24h/problem.xhtml" paramId="pid" paramProperty="pid" />
	<display:column titleKey="tablehdr.enabled24h">
		<c:choose>
			<c:when test="${problem.disable_24h == true}">
				<span class="label label-success"><fmt:message
						key="page.general.yes" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><fmt:message
						key="page.general.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.enabled">
		<c:choose>
			<c:when test="${problem.enabled}">
				<span class="label label-success"><fmt:message
						key="page.general.yes" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><fmt:message
						key="page.general.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.edit">
		<a
			href="<c:url value="/admin/manageproblem.xhtml?pid=${problem.pid}"/>"
			title="<fmt:message key="messages.general.edit"/>"> <i
			class="fa fa-edit"></i></a>
			&nbsp;<a
			href="<c:url value="/admin/manageproblemI18N.xhtml?pid=${problem.pid}"/>"
			title="<fmt:message key="messages.general.i18n"/>"> <i
			class="fa fa-language"></i></a>
			&nbsp;<a
			href="<c:url value="/admin/manageproblem.xhtml?pid=${problem.pid}"/>"
			title="<fmt:message key="messages.general.normalize"/>"> <i
			class="fa fa-medkit"></i></a>
	</display:column>
</display:table>