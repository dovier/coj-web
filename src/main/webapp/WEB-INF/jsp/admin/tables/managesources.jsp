<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="source" name="sources" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.source" style="width:60%">
        <input style="width: 98%" type="text" value="${source.name}"
               name="name${source.idSource}" />
    </display:column>
    <display:column titleKey="tablehdr.author">
        <input style="width: 95%" type="text" value="${source.author}"
               name="author${source.idSource}" />
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <a style="cursor: pointer;"
           onclick="updateSource(${source.idSource});"
           ><i title="<spring:message code="messages.general.edit"/>"
            data-toggle="tooltip" class="fa fa-edit"></i></a>
        <a style="cursor: pointer;"
           onclick="confirm_delete('<c:url value="/admin/deletesource.xhtml?idSource=${source.idSource}"/>');"
           ><i title="<spring:message code="messages.general.delete"/>"
            data-toggle="tooltip" class="fa fa-trash"></i></a>
        </display:column>
    </display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>

<script>
    $("[data-toggle='tooltip']").tooltip();
    var i18n = {};
    i18n.title      = "<spring:message code="message.confirm.delete.hdr.entry"/>";
    i18n.message    = "<spring:message code="message.confirm.delete.entry"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>
<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>