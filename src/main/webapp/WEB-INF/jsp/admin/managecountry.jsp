<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="country" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <h2><spring:message code="page.general.admin.header"/>: <spring:message
                        code="page.addcountry.managecountry"/></h2>
            </legend>

            <!-- ID OF COUNTRY -->

            <div class="form-group">
                <label class="control-label col-xs-3">ID</label>

                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="id" size="30"
                                maxlength="15" readonly="true"/>
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="id"/></span>
                </div>

            </div>

            <!-- NAME OF COUNTRY -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addcountry.name"/>
                    </label>

                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="name" size="30"
                                    maxlength="30"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="name"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- ACRONYM OF COUNTRY -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addcountry.zip"/>
                    </label>

                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="zip" size="30"
                                    maxlength="3"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="zip"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>


            <!-- TWO LETTER OF COUNTRY -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addcountry.twozip"/>
                    </label>

                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="zip_two" size="30"
                                    maxlength="2"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="zip_two"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- WEB OF COUNTRY -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addcountry.website"/>
                    </label>

                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="website" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="website"/></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>

                </div>
            </authz:authorize>

            <!-- ENABLED OF COUNTRY -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="judge.register.enabled"/>
                </label>

                <div class="col-xs-8">
                    <form:checkbox path="enabled"/>
                </div>

            </div>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.edit"/>"/>
                <input
                        class="btn btn-primary" type="reset" name="reset" id="reset"
                        value="<spring:message code="judge.register.reset.value"/>"/>
                <a class="btn btn-primary" href="<c:url value="/admin/managecountries.xhtml"/>"><spring:message
                        code="button.close"/></a>
            </div>
        </form:form>
    </div>
</div>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>