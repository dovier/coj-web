<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="classification" name="classifications" class="volume" requestURI="" defaultsort="0" defaultorder="ascending">                
    <display:column titleKey="tablehdr.name">
        <input type="text" value="${classification.name}" name="class${classification.idClassification}"/>            
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <a style="cursor: pointer;" onclick="updateClassification(${classification.idClassification})">
            <i title="<spring:message code="messages.general.edit"/>"
               data-toggle="tooltip" class="fa fa-edit"></i></a>
        &nbsp;
        <a style="cursor: pointer;" onclick="confirm_delete('<c:url value="/admin/deleteclassifications.xhtml?classid=${classification.idClassification}"/>')" >
            <i title="<spring:message code="messages.general.delete"/>" data-toggle="tooltip" class="fa fa-trash"></i></a>
        </display:column>
    </display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<script>
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.delete.hdr.entry"/>";
    i18n.message = "<spring:message code="message.confirm.delete.entry"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>