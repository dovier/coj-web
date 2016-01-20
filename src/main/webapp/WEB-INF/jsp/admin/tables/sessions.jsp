<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="session" name="sessions" class="volume" decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
    <display:column property="lastRequest" titleKey="tablehdr.date" headerClass="date" />
    <display:column property="principal" titleKey="tablehdr.user" />
    <display:column property="sessionid" titleKey="tablehdr.sessionid" />
    <display:column titleKey="tablehdr.expired" >
        <c:choose>
            <c:when test="${session.isExpired == true}">
                <span class="label label-success">
                    <fmt:message key="page.general.true" />
                </span>
            </c:when>
            <c:otherwise>
                <span class="label label-danger">
                    <fmt:message key="page.general.false" />
                </span>
            </c:otherwise>
        </c:choose>
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <a href="#" onclick="confirm_delete('<c:url value="/admin/expire.xhtml?session=${session.sessionid}"/>')">
            <i title="<spring:message code="messages.general.delete"/>"
               data-toggle="tooltip" class="fa fa-trash"></i></a>
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