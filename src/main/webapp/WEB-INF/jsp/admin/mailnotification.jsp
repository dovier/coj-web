<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="notification" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />
	: <spring:message code="pagehdr.mailnotification" />
            </legend>

            <!-- SUBJECT OF MAIL -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="mail.subject" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="subject" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="subject" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- BODY OF MAIL -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="mail.body" />
                    </label>
                    <div class="col-xs-8">
                        <form:textarea cssClass="form-control" path="text" rows="15"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="text" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="judge.register.submit.value"/>" /> 
                <input
                       class="btn btn-primary" type="reset" name="reset" id="reset"
                       value="<spring:message code="judge.register.reset.value"/>" />
            </div>

        </form:form>
    </div>
</div>

<script>   
    $("[data-toggle='tooltip']").tooltip();
</script>