<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />: <spring:message code="page.menu.admin.createcontest" />
</h2>


<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="contest" cssClass="form-horizontal">


            <!-- ID OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                       <spring:message code="mandatory.id"/>
                         
                    </label>
                        
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="cid" size="30"
                                    maxlength="9"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="cid" /></span>
                    </div> 
                   <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a> 
                </div>
            </authz:authorize>

            <!-- IMPORT OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.createcontest.import" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="importData" />
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="importData" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- CID OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                       <spring:message code="mandatory.cid"/>
                    </label>
                    <!-- ADD ALL COUNTRIES -->
                    <div class="col-xs-8">                        
                        <form:select cssClass="form-control" path="importCid">
                            <form:option value="0" label="none"/>
                            <form:options items="${contests}" itemValue="cid" itemLabel="name"/>
                        </form:select> 
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="importCid" /></span>
                    </div>
                                 </div>
            </authz:authorize>

            <legend><spring:message code="page.createcontest.importtag" /></legend>

            <!-- IMPORT ALL OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.createcontest.import.all" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="imports" value="all"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="imports" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- IMPORT GENERAL OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.createcontest.import.general" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="imports" value="general"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="imports" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- IMPORT PROBLEMS OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="page.managecontest.link.mp" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="imports" value="problems"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="imports" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- IMPORT LANGUAGES OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <fmt:message key="page.managecontest.link.ml"/>
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="imports" value="languages"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="imports" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- IMPORT FLAGS OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <fmt:message key="page.createcontest.import.flags"/>
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="imports" value="flags"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="imports" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- IMPORT USERS OF CONTEST -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <fmt:message key="page.managecontest.link.mu"/>
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="imports" value="users"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="imports" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="judge.register.submit.value"/>" /> <input
                       class="btn btn-primary" type="reset" name="reset" id="reset"
                       value="<spring:message code="judge.register.reset.value"/>" />
                <a class="btn btn-primary" href="<c:url value="/admin/admincontests.xhtml"/>"><spring:message code="button.close"/></a>
            </div>

        </form:form>
    </div>
</div>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>
