<%--
  User: alison
  Date: 15/01/16
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<form:hidden path="id"/>

<div class="form-group">
    <label class="control-label col-xs-3">
        <spring:message code="addfaq.question" />
    </label>
    <a>
        <i data-toggle="tooltip" class="fa fa-asterisk"
           title="<spring:message code="mandatory.field"/>">
        </i>
    </a>
    <div class="col-xs-8">
        <form:input cssClass="form-control" path="question" size="30"
                    maxlength="150"/>
    </div>
    <div class="error col-xs-8 col-xs-offset-3">
        <span class="label label-danger"><form:errors path="question" /></span>
    </div>
</div>

<div class="form-group">
    <label class="control-label col-xs-3">
        <spring:message code="addfaq.answer" />

    </label>
    <a>
        <i data-toggle="tooltip" class="fa fa-asterisk"
           title="<spring:message code="mandatory.field"/>">
        </i>
    </a>
    <div class="col-xs-8">
        <form:textarea path="answer" id="answer" rows="15" cssStyle="width: 99%"/>

    </div>
    <div class="error col-xs-8 col-xs-offset-3">
        <span class="label label-danger"><form:errors path="answer" /></span>

    </div>
</div>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>