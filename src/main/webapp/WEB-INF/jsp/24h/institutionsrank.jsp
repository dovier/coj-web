<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<h2 class="postheader">
    <spring:message code="pagehdr.24histandings" />
</h2>
<div class="postcontent">
    <!-- content -->
    <form id="filter-form" class="form-inline coj_float_rigth">
        <div class="form-group">
            <input placeholder="<spring:message code="fieldhdr.searchinstitutions" />"type="text" class="form-control" name="pattern" value="${pattern}">
                </div>
        <div class="form-group">	
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="button.filter"/>">
        </div>
    </form>

        
    <br />
    <div class="clearfix"></div>

    <div id="display-table-container"
         data-reload-url="/tables/institutionsrank.xhtml"></div>
    <!-- /content -->
</div>
<script>
    $(initStandardFilterForm);
</script>