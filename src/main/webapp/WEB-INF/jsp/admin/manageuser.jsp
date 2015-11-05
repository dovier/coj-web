<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="user" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="pagehdr.euaccount" />
            </legend>

            <!-- NICKNAME OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.nname" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="nick" size="30"
                                    maxlength="50"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="nick" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-info-circle"
                           title="<spring:message code="infomsg.2"/>"></i>
                    </a>
                </div>
            </authz:authorize>

            <!-- MODIFY NICKNAME OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.modnickname" />
                    </label>
                    <div class="col-xs-8">
                        <form:checkbox path="update_nick" />
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="update_nick" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- COUNTRY OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.country" />
                    </label>
                    <!-- ADD ALL COUNTRIES -->
                    <div class="col-xs-8">
                        <form:select path="country_id" id="country" cssClass="form-control" onchange="getInstitution();">
                            <form:option value="0">none</form:option>
                            <form:options items="${countries}" itemValue="id" itemLabel="name" />
                        </form:select>
                    </div>                    
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="country" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>   
                    <a>
                        <i data-toggle="tooltip" class="fa fa-info-circle"
                           title="<spring:message code="infomsg.5"/>"></i>
                    </a>
                </div>
            </authz:authorize>

            <!-- INSTITUTION OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.institution" />
                    </label>
                    <!-- ADD ALL INSTITUTIONS -->
                    <div class="col-xs-8">
                        <form:select cssClass="form-control" path="institution_id" id="institution">
                            <form:option value="0">
                                <spring:message code="fieldval.none" />
                            </form:option>
                            <form:options items="${institutions}" itemLabel="name"
                                          itemValue="id" />
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

            <!-- LANGUAGE OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.defaultguilang" />
                    </label>
                    <!-- ADD ALL LANGUAGES -->
                    <div class="col-xs-8">
                        <form:select path="locale" cssClass="form-control">
                            <form:options items="${locales}" itemLabel="description"
                                          itemValue="lid" />
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

            <!-- PROGRAMMING LANGUAGE OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.defaultproglang" />
                    </label>
                    <!-- ADD ALL LANGUAGES -->
                    <div class="col-xs-8">
                        <form:select path="lid" cssClass="form-control">
                            <form:options items="${planguages}" itemLabel="descripcion"
                                          itemValue="lid" />
                        </form:select>
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
            </authz:authorize>

            <!-- PASSWORD OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.npassword" />
                    </label>
                    <div class="col-xs-8">
                        <form:password cssClass="form-control" path="password" size="30"
                                       maxlength="50"/>
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

            <!-- CONFIRM PASSWORD OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.cpassword" />
                    </label>
                    <div class="col-xs-8">
                        <form:password cssClass="form-control" path="confirmPassword" size="30"
                                       maxlength="50"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="confirmPassword" /></span>
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

            <!-- ACCESS OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.accessrule" />
                    </label>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="access_rule" size="30"
                                    maxlength="50"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="access_rule" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- ENABLE OF USER-->
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

            <!-- BANNER OF USER-->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="fieldhdr.banreason" />
                    </label>
                    <div class="col-xs-8">
                        <form:textarea cols="80" rows="5" id="banReason" path="banReason"
                                       cssClass="form-control"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="access_rule" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <!-- TO ADD TEAM USER RECORDS -->

            <c:if test="${user.team == true}">

                <!-- COUCH OF TEAM-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.tcoach" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="coach" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="coach" /></span>
                        </div>                        
                    </div>
                </authz:authorize>

                <!-- 1 MEMBER OF TEAM-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.tmember" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="user_1" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="user_1" /></span>
                        </div>                        
                    </div>
                </authz:authorize>

                <!-- 2 MEMBER OF TEAM-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.tmember" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="user_2" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="user_2" /></span>
                        </div>                        
                    </div>
                </authz:authorize>

                <!-- 3 MEMBER OF TEAM-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.tmember" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="user_3" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="user_3" /></span>
                        </div>                        
                    </div>
                </authz:authorize>

            </c:if>

            <c:if test="${user.team == false}">

                <!-- NAME OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.fname" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="name" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="name" /></span>
                        </div>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-info-circle"
                               title="<spring:message code="infomsg.3"/>"></i>
                        </a>
                    </div>
                </authz:authorize>

                <!-- LAST NAME OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.lname" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="lastname" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="lastname" /></span>
                        </div>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-info-circle"
                               title="<spring:message code="infomsg.4"/>"></i>
                        </a>
                    </div>
                </authz:authorize>

                <!-- GENDER OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.gender" />
                        </label>
                        <!-- ADD ALL GENDERS -->
                        <div class="col-xs-8">
                            <form:select path="gender" cssClass="form-control">
                                <form:option value="1">
                                    <spring:message code="fieldval.male" />
                                </form:option>
                                <form:option value="2">
                                    <spring:message code="fieldval.female" />
                                </form:option>
                            </form:select>
                        </div>                    
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="gender" /></span>
                        </div>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                    </div>
                </authz:authorize>

                <!-- BIRTH DATE OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.dob" />
                        </label>
                        <!-- ADD DATE -->
                        <div class="col-xs-8">
                            <div class="form-inline">
                                <form:select path="year" cssClass="form-control">
                                    <c:forEach begin="1930" step="1" end="${year}" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                <form:select path="month" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="12" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                <form:select path="day" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="31" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                        </div>                                            
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                    </div>
                </authz:authorize>

                <!-- ENABLE BIRTH DATE OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.sdob" />
                        </label>
                        <div class="col-xs-8">
                            <form:checkbox path="showdob" />
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="showdob" /></span>
                        </div>                    
                    </div>
                </authz:authorize>

                <!-- EMAIL OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.email" />
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="email" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="email" /></span>
                        </div>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-info-circle"
                               title="<spring:message code="infomsg.7"/>"></i>
                        </a>                           
                    </div>
                </authz:authorize>

                <!-- NOTIFY CONTESTS OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.notifcontest" />
                        </label>
                        <div class="col-xs-8">
                            <form:checkbox path="contestNotifications" />
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="contestNotifications" /></span>
                        </div>                    
                    </div>
                </authz:authorize>

                <!-- NOTIFY PROBLEMS OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.notifproblem" />
                        </label>
                        <div class="col-xs-8">
                            <form:checkbox path="problemNotifications" />
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="problemNotifications" /></span>
                        </div>                    
                    </div>
                </authz:authorize>

                <!-- NOTIFY SUBMITS OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.notifsubmit" />
                        </label>
                        <div class="col-xs-8">
                            <form:checkbox path="submissionNotifications" />
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="submissionNotifications" /></span>
                        </div>                    
                    </div>
                </authz:authorize>

                <!-- EMAIL COUTA OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.mailquota" /> (<spring:message code="fieldval.bytes" />)
                        </label>
                        <div class="col-xs-8">
                            <form:input cssClass="form-control" path="mail_quote" size="30"
                                        maxlength="50"/>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="mail_quote" /></span>
                        </div>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                    </div>
                </authz:authorize>

                <!-- ROLES OF USER-->
                <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                    <div class="form-group">
                        <label class="control-label col-xs-3">
                            <spring:message code="fieldhdr.roles" />
                        </label>
                        <div class="col-xs-8">
                            <div class="col-xs-4">
                                <form:checkboxes cssClass="checkbox" path="authorities"
                                                 items="${authorities}" itemValue="authority"
                                                 itemLabel="authority" delimiter="</div><div class='col-xs-4'>" />
                            </div>
                        </div>
                        <div class="error col-xs-8 col-xs-offset-3">
                            <span class="label label-danger"><form:errors path="authorities" /></span>
                        </div>
                        <a>
                            <i data-toggle="tooltip" class="fa fa-asterisk"
                               title="<spring:message code="mandatory.field"/>">
                            </i>
                        </a>
                    </div>
                </authz:authorize>

            </c:if>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.edit"/>" /> <input
                       class="btn btn-primary" type="reset" name="reset" id="reset"
                       value="<spring:message code="button.reset"/>" />
            </div>

        </form:form>
    </div>
</div>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>