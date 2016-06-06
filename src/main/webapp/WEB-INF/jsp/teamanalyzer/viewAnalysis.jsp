<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>
</h2>

<div class="postcontent">


    <div id="display-table-container" data-reload-url="/teamanalyzer/viewTeamsList.xhtml?taid=${taid}"></div>
    <div class="row">
        <div class="col-xs-12">
            <div class="form-group col-xs-2 coj_float_rigth">
                <a class="btn btn-primary"
                   href="<c:url value="/teamanalyzer/main.xhtml"/>"><spring:message
                        code="button.close"/></a>
            </div>
        </div>
    </div>
    <script>
        $(initStandardFilterForm);
    </script>