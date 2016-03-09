<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.24hcstandings"/>
</h2>

<div class="postcontent">
    <!-- content -->
    <form id="filter-form" class="form-inline coj_float_rigth">
        <div class="form-group">
            <input placeholder="<spring:message code="fieldhdr.searchcountries" />" type="text" class="form-control"
                   name="pattern" value="${pattern}">

            <div class="form-group">
                <input id="filter-button" type="submit" class="btn btn-primary"
                       value="<spring:message code="button.filter"/>">
            </div>
        </div>
    </form>
    <c:choose>
        <c:when test="${search == true}">
            <label><spring:message code="fieldhdr.totalfound"/>:
                    ${found}</label>
        </c:when>
    </c:choose>
    <br/>

    <div class="clearfix"></div>
    <div id="display-table-container"
         data-reload-url="/tables/countriesrank.xhtml"></div>
</div>
<script>
    $(initStandardFilterForm);
</script>