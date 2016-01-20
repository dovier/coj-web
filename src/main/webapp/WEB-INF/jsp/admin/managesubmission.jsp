<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script  type="text/javascript" src="/js/edit_area/edit_area_full.js"></script>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="submission" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="pagehdr.submissions" />
            </legend>

            <!-- ID OF SUBMISSION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        ID:
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="sid" size="30"
                                    maxlength="70" readonly="true"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="sid" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- USERNAME OF SUBMISSION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="judge.register.username" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="username" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="username" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- STATUS OF SUBMISSION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="tablehdr.status" />
                    </label>
                    <!-- ADD ALL COUNTRIES -->
                    <div class="col-xs-8">
                        <form:select path="status" cssClass="form-control">
                            <form:options items="${results}" itemLabel="status" itemValue="status"/>
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="status" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>                   
                </div>
            </authz:authorize>

            <!-- TIME (MS)OF SUBMISSION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="addproblem.time" /> (MS)
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="timeUsed" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="timeUsed" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- MEMORY OF SUBMISSION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="addproblem.memory" /> (KB)
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="memoryUsed" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="memoryUsed" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- LANGUAGUE OF SUBMISSION -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="judge.register.language" />
                    </label>
                    <!-- ADD ALL COUNTRIES -->
                    <div class="col-xs-8">
                        <form:select path="lang" cssClass="form-control">
                            <form:options items="${planguages}" itemLabel="descripcion" itemValue="language"/>
                        </form:select> 
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="lang" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>                   
                </div>
            </authz:authorize>

            <!-- ENABLE OF SUBMISSION-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.enabled" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="enabled" />
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="enabled" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- SOURCE CODE OF SUBMISSION-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.sourcecode" />
                    </label>
                    <div class="col-xs-8">
                        <form:textarea path="code" id="code" cssClass="form-control" cssStyle="height: 205px;" />
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="enabled" /></span>
                    </div>                    
                </div>
            </authz:authorize>
            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.edit"/>" />
            </div>

        </form:form>
    </div>
</div>

<script  type="text/javascript">

    var lang = document.getElementById("lang").innerHTML;
    if (lang == "Pascal") {
        lang = "pas";
    }
    if (lang == "C") {
        lang = "c";
    }
    if (lang == "Text") {
        lang = "robotstxt";
    }
    if (lang == "C++") {
        lang = "cpp";
    }
    editAreaLoader.init({
        id: "code"	// id of the textarea to transform
        , start_highlight: true
        , font_size: "10"
        , font_family: "verdana, monospace"
        , allow_toggle: false
        , language: "en"
        , syntax: lang
        , toolbar: "search, go_to_line,|, select_font, |, highlight, reset_highlight, |, help"
        , plugins: "charmap"
        , charmap_default: "arrows"
        , word_wrap: true
    });

    function toogle_editable(id)
    {
        editAreaLoader.execCommand(id, 'set_editable', !editAreaLoader.execCommand(id, 'is_editable'));
    }

    $("[data-toggle='tooltip']").tooltip();

</script>