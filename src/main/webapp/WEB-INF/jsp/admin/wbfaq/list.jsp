<%--
  User: alison
  Date: 15/01/16
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen" />

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="pagehdr.faq"/>
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>
    </c:if>
    <a href="<c:url value="/admin/addfaq.xhtml" />" class="btn btn-primary"><fmt:message key="page.faqs.add"/></a>

    <div id="display-table-container" data-reload-url="/admin/tables/faqs.xhtml"></div>
</div>
<script>
    $(document).ready(displayTableReload("?page=1"));
</script>
