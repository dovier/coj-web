<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <spring:message code="pagehdr.rcsprevious"/>
</h2>
<div class="postcontent">
<form id="filter-form" class="form-inline">
		<div class="form-group">
		<label><spring:message code="fieldhdr.searchcontests"/>:</label>
                <input type="text"class="form-control" name="pattern" value="${pattern}"/> 
		</div>
		<div class="form-group">
			<input id="filter-button" type="submit" class="btn btn-primary"
				value="<spring:message code="button.filter"/>">
		</div>
	</form>
    <c:choose>
        <c:when test="${search == true}">
            <label><spring:message code="fieldhdr.totalfound"/>: ${found}</label>
        </c:when>
    </c:choose>
    <br/>

     <div id="display-table-container" data-reload-url="/tables/past.xhtml"></div>
</div>    
<script>
	$(function() {
		$('#filter-button').click(function(event) {
			displayTableReload($('#filter-form').formSerialize());
			event.preventDefault();
		});
	});

	$(document).ready(displayTableReload($('#filter-form').formSerialize()));
</script>        