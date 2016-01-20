<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="source" name="sources" class="volume" requestURI=""
               defaultsort="0" defaultorder="ascending">
    <display:column titleKey="tablehdr.source" property="name" headerClass="headsub" />
        <%--<input style="width: 98%" type="text" value="${source.name}"
               name="name${source.idSource}" readonly/>--%>
    <%--</display:column>--%>
    <display:column titleKey="tablehdr.author" property="author" headerClass="headsub"/>
        <%--<input style="width: 95%" type="text" value="${source.author}"
               name="author${source.idSource}" />
    </display:column>--%>
    <display:column titleKey="tablehdr.actions"  headerClass="headedit">
        <a class="details"
           data-toggle="tooltip"
           href="<c:url value="/admin/detailssource.xhtml?idSource=${source.idSource}"/>"
           title="<spring:message code="messages.general.details"/>"><i
                class="fa fa-eye"></i></a>
        <a style="cursor: pointer;"
           <%--href="<c:url value="/admin/updatesource.xhtml?idSource=${source.idSource}&name=${source.name}&author=${source.author}"/>"--%>
           href="<c:url value="/admin/updatesource.xhtml?idSource=${source.idSource}"/>"
           ><i title="<spring:message code="messages.general.edit"/>"
            data-toggle="tooltip" class="fa fa-edit"></i></a>
        <a style="cursor: pointer;"
           onclick="confirm_delete('<c:url value="/admin/deletesource.xhtml?idSource=${source.idSource}"/>');"
           ><i title="<spring:message code="messages.general.delete"/>"
            data-toggle="tooltip" class="fa fa-trash"></i></a>
        </display:column>
    </display:table>

<script>
    $("[data-toggle='tooltip']").tooltip();
</script>

