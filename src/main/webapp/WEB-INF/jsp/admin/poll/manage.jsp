<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" commandName="poll" cssClass="form-horizontal">
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="pagetit.manage.poll" />
            </legend>

            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3"><spring:message
                            code="tablehdr.question" /></label>
                    <div class="col-xs-8">
                        <form:textarea cols="80" rows="5" id="question" path="question"
                                       cssClass="form-control" />                    
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="question" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>   

            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3"><spring:message
                            code="addfaq.answer" /> 1</label>
                    <div class="col-xs-8">
                        <form:textarea cols="80" rows="5" id="answer1" path="answer1"
                                       cssClass="form-control" />                    
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="answer1" /></span>
                    </div>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                </div>
            </authz:authorize>

            <div class="form-group">
                <label class="control-label col-xs-3"><spring:message
                        code="addfaq.answer" /> 2</label>
                <div class="col-xs-8">
                    <form:textarea cols="80" rows="5" id="answer2" path="answer2"
                                   cssClass="form-control" />                    
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="answer2" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3"><spring:message
                        code="addfaq.answer" /> 3</label>
                <div class="col-xs-8">
                    <form:textarea cols="80" rows="5" id="answer3" path="answer3"
                                   cssClass="form-control" />                    
                </div>
                <div class="error col-xs-8 col-xs-offset-3">
                    <span class="label label-danger"><form:errors path="answer3" /></span>
                </div>
                <a>
                    <i data-toggle="tooltip" class="fa fa-asterisk"
                       title="<spring:message code="mandatory.field"/>">
                    </i>
                </a>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3"><spring:message
                        code="addfaq.answer" /> 4</label>
                <div class="col-xs-8">
                    <form:textarea cols="80" rows="5" id="answer4" path="answer4"
                                   cssClass="form-control" />
                    <span class="label label-danger"><form:errors path="answer4" /></span>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3"><spring:message
                        code="addfaq.answer" /> 5</label>
                <div class="col-xs-8">
                    <form:textarea cols="80" rows="5" id="answer5" path="answer5"
                                   cssClass="form-control" />
                    <span class="label label-danger"><form:errors path="answer5" /></span>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-xs-3"><spring:message code="fieldhdr.enabled" /></label>
                <div class="col-xs-8">
                    <form:checkbox cssClass="checkbox" path="enabled" />
                </div>
            </div>

            <c:if test="${poll.pid == null}">
                <div class="col-xs-12">
                    <div class="form-actions pull-right ">
                        <input class="btn btn-primary" type="submit" name="submit"
                               id="submit" value="<spring:message code="judge.register.submit.value"/>" /> <input
                            class="btn btn-primary" type="reset" name="reset" id="reset"
                            value="<spring:message code="judge.register.reset.value"/>" />
                    </div>
                </div>
            </c:if>
            <c:if test="${poll.pid != null}">
                <div class="col-xs-12">
                    <div class="form-actions pull-right ">
                        <input class="btn btn-primary" type="submit" name="submit"
                               id="submit" value="<spring:message code="judgeregister.update.value"/>" /> <input
                            class="btn btn-primary" type="reset" name="reset" id="reset"
                            value="<spring:message code="judge.register.reset.value"/>" />
                    </div>
                </div>
            </c:if>


        </form:form>

    </div>
</div>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>