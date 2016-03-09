<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<%--<h2 class="postheader">
    <spring:message code="page.general.admin.header"/>: <spring:message code="psource.title"/>
</h2>--%>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <div class="row">
        <div class="col-xs-12">
            <form method="post" action="/admin/addsource.xhtml" id="filter-form" class="form-inline">
                <div class="form-group coj_float_rigth">                        
                    <input class="form-control" type="text" value="" name="name" placeholder="<spring:message code="psource.newsource" />" /> 
                    <input class="form-control" type="text" value="" name="author" placeholder="<spring:message code="psource.author" />" />
                    <input id="filter-button" type="submit" class="btn btn-primary"
                           value="<spring:message code="button.add"/>"> 
                </div>        

            </form>
            <br />

            <div id="display-table-container"
                 data-reload-url="/admin/tables/managesources.xhtml"></div>
        </div>
    </div>

</div>
<script>
    $(document).ready(displayTableReload(" "));
</script>

<script>
    $("[data-toggle='tooltip']").tooltip();
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.delete.hdr.entry"/>";
    i18n.message = "<spring:message code="message.confirm.delete.entry"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>
