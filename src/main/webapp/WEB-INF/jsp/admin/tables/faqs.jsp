<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="faq" name="faqs" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="id" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="question" titleKey="tablehdr.question"
		paramId="question" paramProperty="question" />
	<display:column titleKey="tablehdr.edit">
		<a href="<c:url value="/admin/addfaq.xhtml?id=${faq.id}"/>"> <i 
                        title="<spring:message code="messages.general.edit"/>" data-toggle="tooltip"
			class="fa fa-edit"></i></a>
	</display:column>
	<display:column titleKey="tablehdr.delete">
            <a href="#" onclick="confirm_delete('<c:url value="/admin/deletefaq.xhtml?id=${faq.id}"/>')"> <i title="<spring:message code="messages.general.delete"/>"
				data-toggle="tooltip" class="fa fa-trash"></i>
		</a>
	</display:column>
</display:table>

<script>    
    $("[data-toggle='tooltip']").tooltip();    
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.delete.hdr.entry"/>";
    i18n.message = "<spring:message code="message.confirm.delete.entry"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>
<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>