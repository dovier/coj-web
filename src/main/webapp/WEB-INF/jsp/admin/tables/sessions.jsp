<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="session" name="sessions" class="volume" decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
		<display:column property="lastRequest" titleKey="tablehdr.date" headerClass="date" />
		<display:column property="principal" titleKey="tablehdr.username" />
		<display:column property="sessionid" titleKey="tablehdr.sessionid" />
		<display:column property="isExpired" titleKey="tablehdr.expired" />
		<display:column titleKey="tablehdr.delete">
			<a href="<c:url value="/admin/expire.xhtml?session=${session.sessionid}"/>">
                            <i title="<spring:message code="messages.general.edit"/>"
                                                                data-toggle="tooltip" class="fa fa-edit"></i></a>
		</display:column>
	</display:table>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>