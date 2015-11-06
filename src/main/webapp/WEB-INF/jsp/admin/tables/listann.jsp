<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="ann" name="announcements" class="volume"
               decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
    <display:column property="aid" titleKey="tablehdr.id"
                    headerClass="headid" />
    <display:column property="content" titleKey="tablehdr.content" />
    <display:column property="date" titleKey="tablehdr.date"
                    headerClass="headdate" />
    <display:column titleKey="tablehdr.contest">
        <c:choose>
            <c:when test="${ann.contest ne '0'}">
                <a href="/contest/contestview.xhtml?cid=${ann.contest}"><c:out
                        value="${ann.contest}" /></a>
                </c:when>
                <c:otherwise>
                    <c:out value="${ann.contest}" />
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.enabled">
            <c:choose>
                <c:when test="${ann.enabled == true}">
                <span class="label label-success"><fmt:message
                        key="page.general.yes" /></span>
                </c:when>
                <c:otherwise>
                <span class="label label-danger"><fmt:message
                        key="page.general.no" /></span>
                </c:otherwise>
            </c:choose>
        </display:column>
        <display:column titleKey="tablehdr.actions">
        <a href="<c:url value="/admin/manageann.xhtml?aid=${ann.aid}"/>"
           ><i title="<spring:message code="messages.general.edit"/>"
            data-toggle="tooltip" class="fa fa-edit"></i></a>
            <a href="#" onclick="confirm_delete('<c:url value="/admin/deleteann.xhtml?aid=${ann.aid}"/>')"
           ><i title="<spring:message code="messages.general.delete"/>"
				data-toggle="tooltip" class="fa fa-trash"></i></a>
        </display:column>
    </display:table>

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

