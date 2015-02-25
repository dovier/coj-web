<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="site" name="sites" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending">
	<display:column property="site" titleKey="tablehdr.site"
		sortable="true" sortProperty="site" sortName="site" autolink="true"
		headerClass="headfirst" href="details.xhtml" paramId="sid"
		paramProperty="sid" />
	<display:column titleKey="tablehdr.completed" headerClass="headsub">
		<c:choose>
			<c:when test="${site.completed}">
				<span class="label label-success"><spring:message
						code="page.general.yes" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"> <spring:message
						code="page.general.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.enabled" headerClass="headsub">
		<c:choose>
			<c:when test="${site.enabled}">
				<span class="label label-success"><spring:message
						code="page.general.yes" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"> <spring:message
						code="page.general.no" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.actions" headerClass="headedit">
		<a class="details"
			href="<c:url value="/admin/wboard/site/details.xhtml?sid=${site.sid}"/>"
			title="<spring:message code="messages.general.details"/>"><i
			class="fa fa-eye"></i></a>
		<a class="edit"
			href="<c:url value="/admin/wboard/site/edit.xhtml?sid=${site.sid}"/>"
			title="<spring:message code="messages.general.edit"/>"><i
			class="fa fa-edit"></i></a>
		<a class="confirm-message"
			data-confirm-title='<spring:message code="message.confirm.delete.hdr.wbsite"/>'
			data-confirm-message='<spring:message code="message.confirm.delete.wbsite"/>'
			data-confirm-type="delete"
			href="<c:url value="/admin/wboard/site/delete.xhtml?sid=${site.sid}"/>"
			title="<spring:message code="messages.general.delete"/>"><i
			class="fa fa-trash"></i> </a>
	</display:column>
</display:table>