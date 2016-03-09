<%--RF34 Clasificar problema--%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<display:table id="classification" name="classifications" class="volume" requestURI="" defaultsort="0" defaultorder="ascending">                
    <display:column titleKey="tablehdr.name">
        <input type="text" value="${classification.name}" name="class${classification.idClassification}"/>            
    </display:column>
    <display:column titleKey="tablehdr.actions">
            <a style="cursor: pointer;" onclick="updateClassification(${classification.idClassification})">
            <i title="<spring:message code="messages.general.edit"/>"
               data-toggle="tooltip" class="fa fa-edit"></i></a>
        &nbsp;
        <a style="cursor: pointer;" onclick="confirm_delete('/admin/deleteclassifications.xhtml?classid=${classification.idClassification}')" >
            <i title="<spring:message code="messages.general.delete"/>" data-toggle="tooltip" class="fa fa-trash"></i></a>
        </display:column>
    </display:table>
        
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>