<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="classification" name="classifications" class="volume" requestURI="" defaultsort="0" defaultorder="ascending">                
        <display:column titleKey="tablehdr.name">
            <input type="text" value="${classification.name}" name="class${classification.idClassification}"/>            
        </display:column>
        <display:column titleKey="tablehdr.actions">
            <a style="cursor: pointer;" onclick="updateClassification(${classification.idClassification})" title="<fmt:message key="messages.general.go"/>"><i class="fa fa-edit"></i></a>
            &nbsp;
            <a style="cursor: pointer;" onclick="deleteClassification(${classification.idClassification})" title="<fmt:message key="messages.general.go"/>"><i class="fa fa-trash"></i></a>
            </display:column>
        </display:table>
