<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen" />

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
    : <spring:message code="page.header.admin.wbcontest.list" />		
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <a class="btn btn-primary"
       href='<c:url value="/admin/wboard/contest/create.xhtml"/>'> <spring:message
            code="pagehdr.create.contest" />
    </a> <br />

    <div id="display-table-container"
         data-reload-url="/admin/tables/wbcontests.xhtml"></div>
</div>
<script>
    $(document).ready(displayTableReload(" "));
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