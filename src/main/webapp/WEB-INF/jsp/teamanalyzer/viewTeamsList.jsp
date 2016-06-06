<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<display:table id="teams" name="teams" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.id">
        <c:out value="${teams.id}"/>
    </display:column>
    <display:column titleKey="tablehdr.name1" style="text-transform:none">
        <c:out value="${teams.username1}"/>
    </display:column>
    <display:column titleKey="tablehdr.name2" style="text-transform:none">
        <c:out value="${teams.username2}"/>
    </display:column>
    <display:column titleKey="tablehdr.name3" style="text-transform:none">
        <c:out value="${teams.username3}"/>
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <ul class="list-inline">
            <li><a
                    href="<c:url value="/teamanalyzer/viewGraph.xhtml?tid=${teams.id}&taid=${teams.taid}" />"
            ><i title="<spring:message code="messages.general.view.graph"/>"
                data-toggle="tooltip" class="fa fa-bar-chart"></i></a></li>
        </ul>
    </display:column>
</display:table>
<script>
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });
</script>
