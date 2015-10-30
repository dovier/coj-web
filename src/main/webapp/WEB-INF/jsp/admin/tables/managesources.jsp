<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="source" name="sources" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.source" style="width:60%">
        <input style="width: 98%" type="text" value="${source.name}"
               name="name${source.idSource}" />
    </display:column>
    <display:column titleKey="tablehdr.author">
        <input style="width: 95%" type="text" value="${source.author}"
               name="author${source.idSource}" />
    </display:column>
    <display:column titleKey="tablehdr.actions">
        <a style="cursor: pointer;"
           onclick="updateSource(${source.idSource});"
           ><i title="<spring:message code="messages.general.edit"/>"
            data-toggle="tooltip" class="fa fa-edit"></i></a>
        <a style="cursor: pointer;"
           onclick="deleteSource(${source.idSource});"
           ><i title="<spring:message code="messages.general.delete"/>"
            data-toggle="tooltip" class="fa fa-trash"></i></a>
        </display:column>
    </display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>