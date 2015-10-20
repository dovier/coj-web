<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="n" name="disabled" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending">
	<display:column property="id" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column titleKey="tablehdr.text">
		<p id="${n.id}">${n.text}</p>
	</display:column>
	<display:column titleKey="tablehdr.date">
		<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.date}" />
	</display:column>
	<display:column titleKey="tablehdr.user">
		<a
			href="<c:url value="/user/useraccount.xhtml?username=${n.username}"/>">${n.username}</a>
	</display:column>

	<display:column headerClass="headentries-actions"
		titleKey="tablehdr.actions">
		<a data-toggle="tooltip" title="Show entry"
			href="<c:url value="/admin/enableentry.xhtml?id=${n.id}"/>"> <i
			class="fa fa-eye"></i></a>
		<a data-toggle="tooltip" title="Send PM" href="<c:url value="/mail/composemail.xhtml?usernameto=${n.username}"/>">
			<i class="fa fa-envelope"></i></a>
		<a data-toggle="tooltip" title="Edit entry"
			href="<c:url value="javascript:edit(${n.id})"/>"> <i
			class="fa fa-edit"></i></a>
		<a class="confirm-message"
			data-confirm-title='<spring:message code="message.confirm.delete.hdr.entry"/>'
			data-confirm-message='<spring:message code="message.confirm.delete.entry"/>'
			data-confirm-type="delete" data-toggle="tooltip" title="Delete entry"
			href="<c:url value="/admin/deleteentry.xhtml?id=${n.id}"/>"> <i
			class="fa fa-trash"></i></a>
	</display:column>
</display:table>