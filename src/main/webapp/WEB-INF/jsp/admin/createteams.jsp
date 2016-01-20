<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<!--<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>-->

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="team" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="judge.createteams.title" />
            </legend>

            <!-- USERNAME OF TEAM -->
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

            <!-- NICK OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.nname" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="nick" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="nick" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-info-circle"
                           title="<spring:message code="infomsg.2"/>"></i>
                    </a>
                </div>
            </authz:authorize>

            <!-- UPDATE NICK OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.modnickname" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="update_nick"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="update_nick" /></span>
                    </div>
                   </div>
            </authz:authorize>

            <!-- PASSWORD OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.npassword" />
                    </label>
                    <div class="col-xs-8">
                        <form:password path="password" cssClass="form-control" size="30"
                                       maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="password" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                    <a><i data-toggle="tooltip" class="fa fa-info-circle"
                          title="<spring:message code="infomsg.8"/>"></i>
                    </a>
                </div>
            </authz:authorize>

            <!-- CONFIRM-PASSWORD OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.cpassword" />
                    </label>
                    <div class="col-xs-8">
                        <form:password path="confirmPassword" cssClass="form-control" size="30"
                                       maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="confirmPassword" /></span>
                    </div>
                    <a>
                        
                    </a>
                </div>
            </authz:authorize>

            <!-- TOTAL OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="judge.createteams.total" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="total" size="30"
                                    maxlength="70"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="total" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- COUNTRY OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.country" />
                    </label>
                    <!-- ADD ALL COUNTRIES -->
                    <div class="col-xs-8">
                        <form:select cssClass="form-control" path="country" onchange="getInstitution();">
                            <form:option value="0">
                                <fmt:message key="judge.register.select"/>
                            </form:option>
                            <form:options items="${countries}" itemLabel="name" itemValue="id"/>
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="country" /></span>
                    </div>
                     <a>
                        <i data-toggle="tooltip" class="fa fa-info-circle"
                           title="<spring:message code="infomsg.5"/>"></i>
                    </a>
                </div>
            </authz:authorize>

            <!-- INSTITUTION OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.institution" />
                    </label>
                    <!-- ADD ALL INSTITUTIONS -->
                    <div class="col-xs-8">                        
                        <form:select cssClass="form-control" id="institution" path="institution">
                            <form:option value="0"><spring:message code="fieldval.none"/></form:option>
                            <form:options items="${institutions}" itemLabel="name" itemValue="id"/>
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="institution" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-info-circle"
                           title="<spring:message code="infomsg.6"/>"></i>
                    </a>
                </div>
            </authz:authorize>

            <!-- DEFAULT GUI LANGUAGUE OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.defaultguilang" />
                    </label>
                    <!-- ADD ALL LANGUAGES -->
                    <div class="col-xs-8">                                                
                        <form:select cssClass="form-control" path="locale">
                            <form:option value="0">
                                <fmt:message key="judge.register.select"/>
                            </form:option>
                            <form:options items="${locales}" itemValue="lid" itemLabel="description"/>
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="locale" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>                    
                </div>
            </authz:authorize>

            <!-- CONTEST OF TEAM -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="judge.createteams.contest" />
                    </label>
                    <!-- ADD ALL CONTEST -->
                    <div class="col-xs-8">                                                                        
                        <form:select cssClass="form-control" path="contest">
                            <form:option value="0">
                                <fmt:message key="judge.register.select"/>
                            </form:option>
                            <form:options items="${contests}" itemValue="cid" itemLabel="name"/>
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="contest" /></span>
                    </div>
                                
                </div>
            </authz:authorize>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.create"/>" /> <input
                       class="btn btn-primary" type="reset" name="reset" id="reset"
                       value="<spring:message code="button.reset"/>" />
            </div>
        </form:form>
    </div>
</div>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>