<%--
  User: alison
  Date: 13/01/16
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-xs-10">

        <form:form method="post"
                   commandName="wproblemSource" cssClass="form-horizontal">

            <legend>
                <spring:message code="page.general.admin.header" />: <spring:message code="page.header.admin.source.edit" />
            </legend>

            <tiles:insertAttribute name="form"/>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.edit"/>" /> <input
                    class="btn btn-primary" type="reset" name="reset" id="reset"
                    value="<spring:message code="button.reset"/>" />
            </div>
        </form:form>

    </div>
</div>