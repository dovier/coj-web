<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="activation" name="activations" class="volume"
               requestURI="" defaultsort="0" defaultorder="descending">
    <display:column property="id" titleKey="tablehdr.id"
                    headerClass="headid" />
    <display:column property="username" titleKey="tablehdr.user" />
    <display:column property="token" titleKey="tablehdr.token" />
    <display:column property="date" titleKey="tablehdr.date" />
    <display:column titleKey="tablehdr.actions" headerClass="headedit">
        <a 
            data-toggle="tooltip"
            href="<c:url value="/admin/accountactivation.xhtml?key=${activation.token}"/>"
            title="<spring:message code="messages.general.confirm"/>"><i 
                class="text-success fa fa-check-circle"></i></a>

<!--        <a 
            data-toggle="tooltip"
            href="<c:url value="/admin/resendactivations.xhtml?act_id=${activation.id}"/>"
            title="<spring:message code="messages.general.edit"/>"><i 
                class="fa fa-envelope-o"></i></a> -->

        <a 
            data-toggle="tooltip" onclick="confirm_delete('<c:url value="/admin/deleteactivation.xhtml?key=${activation.token}"/>')"
            href="#"
            title="<spring:message code="messages.general.delete"/>"><i 
                class="fa fa-trash"></i></a>
        </display:column>
  
    </display:table>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<script>
    var i18n = {};
    i18n.title      = "<spring:message code="message.confirm.delete.hdr.entry"/>";
    i18n.message    = "<spring:message code="message.confirm.delete.entry"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>
