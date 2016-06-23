<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>:
    <spring:message code="pagehdr.teamanalyzer.main"/>
</h2>

<div class="postcontent">
    <c:if test="${message != null}">
    <div class="alert alert-success alert-dismissable fade in">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <i class="fa fa-check"></i><spring:message code="${message}"/>
    </div>
    </c:if>
    <form class="form-inline coj_float_rigth">
        <div class="form-group">
            <a class="btn btn-primary"
               href="<c:url value="/teamanalyzer/dataAnalysis.xhtml?taid=0"/>"><spring:message code="button.add"/></a>
        </div>
    </form>


    <div id="display-table-container" data-reload-url="/teamanalyzer/analysisList.xhtml"></div>

    <link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css"/>
    <script src="/js/bootstrap-dialog.min.js"></script>
    <script src="/js/admin/utility.js"></script>

    <script>
        $(initStandardFilterForm);
    </script>

    <script>
        var i18n = {};
        i18n.title = "<spring:message code="message.confirm.delete.hdr.entry"/>";
        i18n.message = "<spring:message code="message.confirm.delete.entry"/>";
        i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
        i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
    </script>