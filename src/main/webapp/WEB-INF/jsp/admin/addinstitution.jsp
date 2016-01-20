<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="institution" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="page.addinstitution.header" />
            </legend>

            <!-- NAME OF INTITUTION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addcountry.name" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="name" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="name" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- ACRONYM OF INTITUTION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addinstitution.acronym" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="zip" size="30"
                                    maxlength="15"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="zip" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>


            <!-- LOGO OF INTITUTION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3" for="imagefile">Logo
                        (120x120, &lt;35KB)</label>
                    <div class="col-xs-8">
                        <input id="logo" name="logo" type="file" class="file"
                               data-show-upload="false" accept="image/*"
                               data-show-caption="true">
                    </div>
                    
                </div>
            </authz:authorize>

            <!-- WEB OF INTITUTION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.addinstitution.website" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" placeholder="http://" path="website" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="website" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- COUNTRY OF INTITUTION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="judge.register.country" />
                    </label>
                    <!-- ADD ALL COUNTRIES -->
                    <div class="col-xs-8">
                        <form:select cssClass="form-control" path="country_id">
                            <form:option value="0">
                                <spring:message code="judge.register.select" />                            
                            </form:option>
                            <form:options items="${countries}" itemLabel="name" itemValue="id"/>                        
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="country_id" /></span>
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
                       id="submit" value="<spring:message code="judge.register.submit.value"/>" /> <input
                       class="btn btn-primary" type="reset" name="reset" id="reset"
                       value="<spring:message code="judge.register.reset.value"/>" />
            </div>

        </form:form>
    </div>
</div>

<script>
    $("#logo").fileinput({
        maxFileSize: 35,
        msgProgress: 'Loading {percent}%',
        previewClass: 'avatar_preview',
        previewFileType: "image",
        browseClass: "btn btn-primary",
        browseLabel: "Pick Image",
        browseIcon: '<i class="fa fa-picture-o"></i>&nbsp;',
        removeClass: "btn btn-default",
        removeLabel: "Delete",
        removeIcon: '<i class="fa fa-trash"></i>'
    });
    $("[data-toggle='tooltip']").tooltip();
</script>