<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<h2 class="postheader">
    <spring:message code="page.general.admin.header"/>: <spring:message code="pagetit.listpolls" />
</h2>

<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <a href="<c:url value="/admin/poll/manage.xhtml" />" class="btn btn-primary"><spring:message
            code="add.poll" /></a> <br />

    <display:table id="poll" name="polls" class="volume">
        <display:column property="pid" titleKey="tablehdr.id"
                        headerClass="headid" />
        <display:column property="question" titleKey="tablehdr.question"
                        />
        <display:column titleKey="tablehdr.enabled" headerClass="headid">
            <c:choose>
                <c:when test="${poll.enabled}">
                    <span class="label label-success"><fmt:message
                            key="page.general.yes" /></span>
                    </c:when>
                    <c:otherwise>
                    <span class="label label-danger"><fmt:message
                            key="page.general.no" /></span>
                    </c:otherwise>
                </c:choose>
            </display:column>
            <display:column titleKey="tablehdr.actions" headerClass="headid">
            <a href="<c:url value="/admin/poll/manage.xhtml?pid=${poll.pid}"/>"
               ><i title="<spring:message code="messages.general.edit"/>"
                data-toggle="tooltip" class="fa fa-edit"></i></a>
                <a href="#" onclick="confirm_delete('<c:url value="/admin/poll/delete.xhtml?pid=${poll.pid}"/>')"
               ><i title="<spring:message code="messages.general.delete"/>"
                data-toggle="tooltip" class="fa fa-trash"></i> </a>
            </display:column>
        </display:table>

    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close" />
        </a>
    </div>
    <div class="clearfix"></div>
</div>
<script>
    $.ready(function () {
        $(".enable").on("click", function (event) {
            var elem = $(this);
            var pid = elem.data("id");

            elem.toggleClass("btn-success");
            elem.toggleClass("btn-danger");
            elem.toggleClass("disabled");

            $.ajax({
                url: "/admin/poll/enable.xhtml",
                type: 'POST',
                data: {
                    "pid": pid
                },
                success: function (data) {
                    elem.toggleClass("disabled");

                    if (elem.text() == yes_text) {
                        elem.text(no_text);
                    } else {
                        elem.text(yes_text);
                    }
                }
            });
        });
    });
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