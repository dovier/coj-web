<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">                    
    Courses Board
</h2>
<authz:authorize ifAnyGranted="ROLE_ADMIN,ROLE_PROFESOR">
    <table class="navigating" width="100%">
        <tr>            
            <td width="10%"><a href="createcourse.xhtml>">Create Course</a></td>
            <td width="10%"><a href="mycourses.xhtml">My Courses</a></td>
        </tr>
    </table>
</authz:authorize>
<form id="filter-form" class="form-inline">
    <table class="login filters">
        <tr class="filter">            
            <td><spring:message code="fieldhdr.creator"/>:</td>
            <td>
                <input type="text" name="creator" value="${filter.username}" size="10"/>
            </td>
            <td>Course:</td>
            <td>
                <input type="text" name="name" value="${filter.name}" size="15"/>
            </td>
            <td><spring:message code="fieldhdr.access"/>:</td>
            <td>
                <select name="access" >
                    <option value="0"><spring:message code="fieldhdr.all"/></option>
                    <c:choose>
                        <c:when test="${filter.access eq 1}">
                            <option selected value="1"><spring:message code="fieldval.public"/></option>
                            <option value="2"><spring:message code="fieldval.private"/></option>
                        </c:when>
                        <c:when test="${filter.access eq 2}">
                            <option selected value="2"><spring:message code="fieldval.private"/></option>
                            <option value="1"><spring:message code="fieldval.public"/></option>
                        </c:when>
                        <c:otherwise>
                            <option value="2"><spring:message code="fieldval.private"/></option>
                            <option value="1"><spring:message code="fieldval.public"/></option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </td>
            <td>
                <spring:message code="fieldhdr.status"/>:
            </td>
            <td>
                <select name="status" >
                    <option value="0"><spring:message code="fieldhdr.all"/></option>
                    <c:choose>
                        <c:when test="${filter.vstatus eq 1}">
                            <option selected value="1"><spring:message code="fieldval.coming"/></option>
                            <option value="2"><spring:message code="fieldval.running"/></option>

                        </c:when>
                        <c:when test="${filter.vstatus eq 2}">
                            <option value="1"><spring:message code="fieldval.coming"/></option>
                            <option selected value="2"><spring:message code="fieldval.running"/></option>

                        </c:when>                        
                        <c:otherwise>
                            <option value="1"><spring:message code="fieldval.coming"/></option>
                            <option  value="2"><spring:message code="fieldval.running"/></option>                           
                        </c:otherwise>
                    </c:choose>
                </select>
            </td>

            <td>
                <input id="filter-button" type="submit" value="<spring:message code="button.filter"/>"/>
            </td>
        </tr>

    </table>
</form>

<div class="postcontent">                    

    <div id="display-table-container" data-reload-url="/tables/coursesboard.xhtml"></div>  
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