<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="rul" name="rules" requestURI="" class="volume"
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column titleKey="tablehdr.id" headerClass="headid"
		property="rid" />
	<display:column titleKey="tablehdr.content" property="rule" />
	<display:column titleKey="tablehdr.delete">
		<a href="<c:url value="/admin/deleterule.xhtml?rid=${rul.rid}"/>"
			title="<fmt:message key="messages.general.go"/>"><i
			class="fa fa-trash"></i></a>
	</display:column>
</display:table>
