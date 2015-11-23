<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="session" name="sessions" class="volume" decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
		<display:column property="lastRequest" titleKey="tablehdr.date" headerClass="date" />
		<display:column property="principal" titleKey="tablehdr.user" />
		<display:column property="sessionid" titleKey="tablehdr.sessionid" />
		<display:column property="isExpired" titleKey="tablehdr.expired" />
		<display:column titleKey="tablehdr.actions">
			<a href="<c:url value="/admin/expire.xhtml?session=${session.sessionid}"/>">
                            <i title="<spring:message code="messages.general.delete"/>"
                                                                data-toggle="tooltip" class="fa fa-trash"></i></a>
		</display:column>
	</display:table>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>