<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Manage Institutions
</h2>
<div class="postcontent">
    <br/>
    <form action="manageinstitutions.xhtml" method="get">
        <table>
			<tr>
				<td>

					<div>
						<label>Search in name and code:</label> <input type="text" size="25" name="pattern" value="${pattern}"> <input type="submit" value="<fmt:message key="page.24h.problemsearch.submit"/>">
					</div>
				</td>
			</tr>

		</table>
    </form>
    <c:choose>
        <c:when test="${search == true}">
            <label><fmt:message key="fieldhdr.totalfound"/>: ${found}</label>
        </c:when>
    </c:choose>
    <br/>   

    <a href="<c:url value="/admin/addinstitution.xhtml" />">Add New Institution</a>

    <div id="display-table-container" data-reload-url="/admin/tables/manageinstitutions.xhtml"></div>
</div>