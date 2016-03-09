<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="rul" name="rules" requestURI="" class="volume"
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column titleKey="tablehdr.id" headerClass="headid"
		property="rid" />
	<display:column titleKey="tablehdr.content" property="rule" />
	<display:column titleKey="tablehdr.actions" headerClass="headdate">
            <a href="#" onclick="confirm_delete('<c:url value="/admin/deleterule.xhtml?rid=${rul.rid}"/>')"
			><i title="<spring:message code="messages.general.delete"/>"
				data-toggle="tooltip" class="fa fa-trash"></i></a>
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