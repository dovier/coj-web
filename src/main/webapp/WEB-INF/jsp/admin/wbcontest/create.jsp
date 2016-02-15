<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />:
    <spring:message code="page.header.admin.wbcontest.create" />
</h2>

<div class="row">
    <div class="col-xs-10">

        <form:form method="post"
                   commandName="wbContest" cssClass="form-horizontal">

            <tiles:insertAttribute name="form"/>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.create"/>" /> <input
                       class="btn btn-primary" type="reset" name="reset" id="reset"
                       value="<spring:message code="button.reset"/>" />
                <a class="btn btn-primary" href="<c:url value="/admin/wboard/contest/list.xhtml"/>">
                    <spring:message code="button.close"/>
                </a>
            </div>
        </form:form>
    
    </div>
</div>	
