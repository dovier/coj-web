<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="mail" name="mails" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column titleKey="tablehdr.to" headerClass="headtable-4">
        ${mail.getFirstTo()}
    </display:column>
	<display:column property="title" titleKey="tablehdr.subject"
		href="mailview.xhtml?idmail=0" paramId="send" paramProperty="idmail" headerClass="headtable-7"/>
	<display:column property="size" titleKey="tablehdr.size" headerClass="headtable-2"/>
	<display:column titleKey="tablehdr.date" headerClass="headtable-5">
		<fmt:formatDate value="${mail.date}" pattern="yyyy-MM-dd HH:mm:ss" />
	</display:column>
	<display:column titleKey="tablehdr.actions" headerClass="headtable-2">
            <a href="#" onclick="confirm_delete('<c:url value="deletemail.xhtml?send=${mail.idmail}"/>')"
			data-toggle="tooltip" title="<spring:message code="titval.delete" />"> <i
			class="fa fa-trash"></i>
		</a>
	</display:column>
</display:table>
<script> 
$("[data-toggle='tooltip']").tooltip();
</script>