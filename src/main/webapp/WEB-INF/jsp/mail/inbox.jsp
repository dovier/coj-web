<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">                    
    <spring:message code="pagehdr.mailinbox" />
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i> <spring:message code="${message}" />
        </div>                 
    </c:if> 
    <center><spring:message code="fieldhdr.usage"/>: <fmt:formatNumber value="${mail.percent}" minFractionDigits="2" maxFractionDigits="2"/>% (${mail.consumed_quote} <spring:message code="fieldval.bytesof"/> ${mail.mail_quote} <spring:message code="fieldval.bytes"/>) </center>
    <div class="panel panel-primary">
        &nbsp;<a href="<c:url value="composemail.xhtml"/>"><i class="fa fa-pencil"></i> <spring:message code="link.compose"/></a>
        &nbsp;<a onclick="confirm_delete('<c:url value="deleteallmail.xhtml?delete=1"/>')" href="#"><i class="fa fa-trash"></i> <spring:message code="link.delall"/></a>
    </div>
    <div id="display-table-container" data-reload-url="/tables/inbox.xhtml"></div>
</div>
<script type="text/javascript">
    function validate() {
        var answer = confirm("<spring:message code="imfomsg.inbox.1"/>");
        if (!answer)
            return false;
        return true;
    }
    $(document).ready(displayTableReload(""));
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
