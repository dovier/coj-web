<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<display:table id="analysis" name="analysis" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.id">
        <c:out value="${analysis.id}"/>
    </display:column>
    <display:column titleKey="tablehdr.date">
        <fmt:formatDate value="${analysis.date}" pattern="yyyy-MM-dd HH:mm:ss" />
    </display:column>
    <display:column titleKey="tablehdr.name" style="text-transform:none">
            <c:out value="${analysis.name}" />
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <ul class="list-inline">
            <li><a
                    href="<c:url value="/teamanalyzer/viewAnalysis.xhtml?taid=${analysis.id}" />"
            ><i title="<spring:message code="messages.general.view"/>"
                data-toggle="tooltip" class="fa fa-eye"></i></a></li>
            <li><a
                    href="<c:url value="/teamanalyzer/dataAnalysis.xhtml?taid=${analysis.id}" />"
            ><i title="<spring:message code="messages.general.edit"/>"
                data-toggle="tooltip" class="fa fa-edit"></i></a></li>
            <li><a
                    href="<c:url value="/teamanalyzer/deleteAnalysis.xhtml?taid=${analysis.id}" />"
            ><i title="<spring:message code="messages.general.delete"/>"
                data-toggle="tooltip" class="fa fa-trash"></i></a></li>
        </ul>
    </display:column>
</display:table>
<script>
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });
</script>
