<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<div class="row">
    <div class="col-xs-10">
        <!-- enctype="multipart/form-data" -->
        <form:form method="post"
                   commandName="institution" cssClass="form-horizontal">
            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />
                : <spring:message code="page.general.admin.manageinstitution" />
            </legend>

            <!-- ID OF INTITUTION -->

            <div class="form-group">
                <label class="control-label col-xs-3">ID</label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="id" size="30"
                                maxlength="15" readonly="true"/>
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="id" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>


            <!-- NAME OF INTITUTION -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="page.addcountry.name" />
                </label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="name" size="30"
                                maxlength="15"/>
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


            <!-- ACRONYM OF INTITUTION -->

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


            <!-- LOGO OF INTITUTION (CHECK)-->
            <div class="form-group" >                
                <label class="control-label col-xs-3"></label>
                <div class="col-xs-8">
                    <img src="/images/school/${institution.zip}.png" >
                </div>
            </div>

            <div class="form-group">                    
                <label class="control-label col-xs-3" for="imagefile">Logo
                    (120x120, &lt;35KB)</label>                   
                <div class="col-xs-8">
                    <input id="logo" name="logo" type="file" class="file"
                           data-show-upload="false" accept="image/*"
                           data-show-caption="true">
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>


            <!-- WEBSITE OF INTITUTION -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="page.addinstitution.website" />
                </label>
                <div class="col-xs-8">
                    <form:input cssClass="form-control" path="website" size="30"
                                maxlength="15"/>
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


            <!-- ENABLED OF INTITUTION -->

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


            <!-- COUNTRY OF INTITUTION -->

            <div class="form-group">
                <label class="control-label col-xs-3">
                    <spring:message code="judge.register.country" />
                </label>
                <!-- ADD ALL COUNTRIES AND SELECT THE OWNER -->
                <div class="col-xs-8">
                    <form:select cssClass="form-control" path="country_id">
                        <form:options items="${countries}" itemValue="id" itemLabel="name" />
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

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="judgeregister.update.value"/>" />
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