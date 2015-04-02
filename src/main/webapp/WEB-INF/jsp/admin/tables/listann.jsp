<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="ann" name="announcements" class="volume"
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="aid" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="content" titleKey="tablehdr.content" />
	<display:column property="date" titleKey="tablehdr.date"
		headerClass="headdate" />
	<display:column titleKey="tablehdr.contest">
		<c:choose>
			<c:when test="${ann.contest ne '0'}">
				<a href="/contest/contestview.xhtml?cid=${ann.contest}"><c:out
						value="${ann.contest}" /></a>
			</c:when>
			<c:otherwise>
				<c:out value="${ann.contest}" />
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.enabled">
		<c:choose>
			<c:when test="${ann.enabled == true}">
				<spring:message code="fieldval.yes" />
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><spring:message
						code="fieldval.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.edit">
		<a href="<c:url value="/admin/manageann.xhtml?aid=${ann.aid}"/>"
			title="<spring:message code="messages.general.go"/>"><i
			class="fa fa-edit"></i></a>
		<a href="<c:url value="/admin/deleteann.xhtml?aid=${ann.aid}"/>"
			title="<spring:message code="messages.general.go"/>"><i
			class="fa fa-trash"></i> </a>
	</display:column>
</display:table>