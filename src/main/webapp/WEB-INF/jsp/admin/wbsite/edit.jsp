<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<legend>
    <h2>
        <spring:message code="page.general.admin.header"/>: <spring:message code="pagehdr.edit.site"/>
    </h2>
</legend>
<div class="row">
    <div class="col-xs-10">

        <form:form method="post"
                   commandName="wbSite" cssClass="form-horizontal">


            <tiles:insertAttribute name="form"/>

            <div class="form-actions pull-right">
                <input class="btn btn-primary" type="submit" name="submit"
                       id="submit" value="<spring:message code="button.edit"/>"/> <input
                    class="btn btn-primary" type="reset" name="reset" id="reset"
                    value="<spring:message code="button.reset"/>"/>
                <a class="btn btn-primary" href="<c:url value="/admin/wboard/site/list.xhtml"/>"><spring:message
                        code="button.close"/></a>
            </div>
        </form:form>

    </div>
</div>