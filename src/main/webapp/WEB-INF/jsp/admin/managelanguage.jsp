<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<div class="row">
    <div class="col-xs-10">
        <!-- enctype="multipart/form-data" -->
        <form:form method="post"
                   commandName="language" cssClass="form-horizontal">
            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="page.general.admin.managelanguage" />
            </legend>

            <!-- NAME OF LANGUAGE -->

            <div class="form-group">
                <label class="control-label col-xs-3"><spring:message code="tablehdr.language" /></label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="lid" size="30"
                                maxlength="30" readonly="true"/>
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="lid" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>

            <!-- KEY OF LANGUAGE -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="tablehdr.key" />
                </label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="key" size="30"
                                maxlength="15"/>
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="key" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>

            <!-- DESCRIPTION OF LANGUAGE -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="tablehdr.description" />
                </label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="descripcion" size="30"
                                maxlength="70"/>
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="descripcion" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>

            <!-- NAME BIN OF LANGUAGE -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="tablehdr.namebin" />
                </label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="name_bin" size="30"
                                maxlength="15"/>
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="name_bin" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>            

            <!-- ENABLE OF LANGUAGE -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="judge.register.enabled" />
                </label>
                <div class="col-xs-8">
                    <form:checkbox path="enabled" />
                </div>                    
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>


            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="judgeregister.update.value"/>" />
            </div>

        </form:form>
    </div>
</div>
<script>    
    $("[data-toggle='tooltip']").tooltip();
</script>