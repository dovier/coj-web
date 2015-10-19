<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script  type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<div class="row">
    <div class="col-xs-10">
        <form:form method="post" enctype="multipart/form-data"
                   commandName="faq" cssClass="form-horizontal">

            <!-- NAME OF VIEW -->
            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="pagehdr.addfaq" />
            </legend>

            <!-- NAME OF FAQ -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="addfaq.question" />
                    </label>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="question" size="30"
                                    maxlength="150"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="question" /></span>
                    </div>                    
                </div>
            </authz:authorize>

            <!-- ANSWEAR OF FAQ -->
            <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
                <div class="form-group">
                    <label class="control-label col-xs-3">
                        <spring:message code="addfaq.answer" />
                    </label>
                    <a>
                        <i data-toggle="tooltip" class="fa fa-asterisk"
                           title="<spring:message code="mandatory.field"/>">
                        </i>
                    </a>
                    <div class="col-xs-8">
                        <form:textarea path="answer" id="answer" rows="15" cssStyle="width: 99%"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="answer" /></span>
                    </div>                    
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
$(function(){
		$("[data-toggle='tooltip']").tooltip();	
	});
</script>