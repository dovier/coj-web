<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>-->

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />: <spring:message code="pclassifi.title" />    
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <c:choose>
            <c:when test="${errorcreate}">
                <div class="alert alert-danger alert-dismissable fade in">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-check"></i><spring:message code="${message}" />
                </div>
            </c:when>

            <c:otherwise>
                <div class="alert alert-success alert-dismissable fade in">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-check"></i><spring:message code="${message}" />
                </div>
            </c:otherwise>
        </c:choose>
    </c:if> 
    <form:form  method="post" cssClass="form-inline" action="/admin/addclassifications.xhtml">
        <div class="form-group coj_float_rigth">             
            <input class="form-control" type="text" placeholder="<spring:message code="pclassifi.newclassification"/>" name="name"/>            
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.add"/>"> 
        </div>
    </form:form>
    <br />

    <div id="display-table-container" data-reload-url="/admin/tables/manageclassifications.xhtml"></div>

</div>
<script>
    $(document).ready(displayTableReload(""));
</script>

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




