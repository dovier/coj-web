<%--
  User: alison
  Date: 13/01/16
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form:hidden path="idSource"/>

<div class="form-group">
    <label class="control-label col-xs-3"><spring:message code="psource.newsource" /></label>
    <div class="col-xs-8">
        <form:input cssClass="form-control" path="name" />
    </div>
    <div class="error col-xs-8 col-xs-offset-3">
        <span class="label label-danger"><form:errors path="name"/></span>
    </div>
    <a><i data-toggle="tooltip" class="fa fa-asterisk"
          title="<spring:message code="onemandatory.field"/>"></i></a>
</div>
<div class="form-group">
    <label class="control-label col-xs-3"><spring:message code="psource.author" /></label>
    <div class="col-xs-8">
        <form:input cssClass="form-control" path="author" />
    </div>
    <div class="error col-xs-8 col-xs-offset-3">
        <span class="label label-danger"><form:errors path="author" /></span>
    </div>
    <%--<a><i data-toggle="tooltip" class="fa fa-asterisk"
          title="<spring:message code="mandatory.field"/>"></i></a>--%>
</div>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>


