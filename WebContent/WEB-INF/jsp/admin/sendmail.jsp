<%-- 
    Document   : clarification
    Created on : 16-feb-2011, 15:54:27
    Author     : jorge
--%>

<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="mail.compose" />
</h2>
<div class="postcontent">
    <form:form action="sendmail.xhtml" method="post" commandName="sendmail">
        <table class="userinfo" style="width: 100%;">
            <tr>
                <td><fmt:message key="mail.subject" /></td>
                <td><input type="text" name="title" size="40" value="${data.title}"/> <span class="label label-danger"><form:errors path="title" /></span></td>
            </tr>
            <tr>
                <td width="10%"><fmt:message key="mail.body" /></td>
                <td style="width: 100%;">
                    <textarea id="code" class="des" name="description">${data.description}</textarea>
                </td>
                <td><span class="label label-danger"><form:errors path="description" /></span></td>
            </tr>
            <tr>
                <td>
                    <span class="label label-danger"><form:errors path="general" /></span>
                </td>
            </tr>
        </table>
        <input type="submit" value="<fmt:message key="messages.general.send" />" />
        <input type="reset" value="Reset" />
    </form:form>
</div>