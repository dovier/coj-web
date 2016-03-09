<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<display:table id="mail" name="mails" class="volume" requestURI=""
               decorator="cu.uci.coj.utils.tabledecorator.mailsTableDecorator">
    <display:column property="id_from" titleKey="tablehdr.from"
                    headerClass="headtable-4" href="/user/useraccount.xhtml"
                    paramId="username" paramProperty="id_from"/>
    <display:column titleKey="tablehdr.subject" headerClass="headtable-7">
        <c:choose>
            <c:when test="${mail.cclass eq 'unread'}">
                <b><a href="mailview.xhtml?idmail=${mail.idmail}">${mail.title}</a></b>
            </c:when>
            <c:otherwise>
                <a href="mailview.xhtml?idmail=${mail.idmail}">${mail.title}</a>
            </c:otherwise>
        </c:choose>
    </display:column>
    <display:column property="size" titleKey="tablehdr.size" headerClass="headtable-2"/>
    <display:column titleKey="tablehdr.date" headerClass="headtable-5"><fmt:formatDate value="${mail.date}" pattern="yyyy-MM-dd HH:mm:ss"/></display:column>
    <display:column titleKey="tablehdr.actions" headerClass="headtable-2">
        <a href="#" onclick="confirm_delete('<c:url value="deletemail.xhtml?idmail=${mail.idmail}"/>')"
           data-toggle="tooltip" title="<spring:message code="titval.delete" />"> <i
                class="fa fa-trash"></i>
        </a>
    </display:column>
</display:table>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>
