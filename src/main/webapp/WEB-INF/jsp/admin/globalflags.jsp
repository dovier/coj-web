<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.general.admin.globalflags"/>
</h2

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="globalFlags" cssClass="form-horizontal">


            <c:if test="${message != null}">
                <div class="alert alert-success alert-dismissable fade in">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-check"></i><spring:message code="${message}" />
                </div>                 
            </c:if> 

            <!-- EMAILS NOTIFICATIONS OF FLAGS -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-6">
                        <spring:message code="page.globalflags.disabledmailnotifications" />
                    </label>
                    <div class="col-xs-6">
                        <form:checkbox path="mailNotificationDisabled" />
                    </div>
                    <div class="error col-xs-6 col-xs-offset-6">
                        <span class="label label-danger"><form:errors path="mailNotificationDisabled" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- ABLE EMAIL OF FLAGS -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-6">
                        <spring:message code="page.globalflags.enabledinternalmail" />
                    </label>
                    <div class="col-xs-6">
                        <form:checkbox path="enabled_mail" />
                    </div>
                    <div class="error col-xs-6 col-xs-offset-6">
                        <span class="label label-danger"><form:errors path="enabled_mail" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- ABLE CODE OF FLAGS -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-6">
                        <spring:message code="page.globalflags.enabledsourcecodeview" />
                    </label>
                    <div class="col-xs-6">
                        <form:checkbox path="enabled_source_code_view" />
                    </div>
                    <div class="error col-xs-6 col-xs-offset-6">
                        <span class="label label-danger"><form:errors path="enabled_source_code_view" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- ABLE SEND OF FLAGS -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-6">
                        <spring:message code="page.globalflags.enabledsubmissionjudge" />
                    </label>
                    <div class="col-xs-6">
                        <form:checkbox path="enabled_submission" />
                    </div>
                    <div class="error col-xs-6 col-xs-offset-6">
                        <span class="label label-danger"><form:errors path="enabled_submission" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- MAINTENANCE OF FLAGS -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-6">
                        <spring:message code="tableval.maintenancemode" />
                    </label>
                    <div class="col-xs-6">
                        <form:checkbox path="maintenanceMode" />
                    </div>
                    <div class="error col-xs-6 col-xs-offset-6">
                        <span class="label label-danger"><form:errors path="maintenanceMode" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="judgeregister.update.value"/>" />
                <a class="btn btn-primary" href="<c:url value="/admin/index.xhtml"/>"><spring:message
                        code="button.close"/></a>
            </div>

        </form:form>
    </div>
</div>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>