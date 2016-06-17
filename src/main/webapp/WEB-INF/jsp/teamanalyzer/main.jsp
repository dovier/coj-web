<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>:
    <spring:message code="pagehdr.teamanalyzer.main"/>
</h2>

<div class="postcontent">


    <form class="form-inline coj_float_rigth">
        <div class="form-group">
            <a class="btn btn-primary"
               href="<c:url value="/teamanalyzer/dataAnalysis.xhtml?taid=0"/>"><spring:message code="button.add"/></a>
        </div>
    </form>


    <div id="display-table-container" data-reload-url="/teamanalyzer/analysisList.xhtml"></div>

    <script>
        $(initStandardFilterForm);
    </script>