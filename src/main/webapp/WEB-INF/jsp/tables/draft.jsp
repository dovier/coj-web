<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="mail" name="mails" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="title" titleKey="tablehdr.subject"
		href="mailview.xhtml?send=0" paramId="draft" paramProperty="idmail" />
	<display:column property="size" titleKey="tablehdr.size" />
	<display:column titleKey="tablehdr.date" headerClass="headdate">
		<fmt:formatDate value="${mail.date}" pattern="yyyy-MM-dd HH:mm:ss" />
	</display:column>
	<display:column titleKey="tablehdr.actions">
            <a href="#" onclick="confirm_delete('<c:url value="deletemail.xhtml?draft=${mail.idmail}"/>')"
			data-toggle="tooltip" title="<spring:message code="titval.delete" />"> <i
			  class="fa fa-trash"></i></a>
	</display:column>
</display:table>

<script> 
$("[data-toggle='tooltip']").tooltip();
</script>