<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="page.general.header.contest"/>: <a href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" title="Go to Contest">${contest.name}</a>
    <br/>
    <fmt:message key="rejudge.title"/><br/>
</h2>
<div class="postcontent">
    <form:form  method="POST" commandName="rejudge">
        <table class="rejudge">
            <tr>
                <td style="align:right"><fmt:message key="admin.rejudge.submitid"/>:</td><td style="align:left"><form:input path="submitid" size="23"/></td>
            <tr/>
            <tr>
                <td style="align:right"><fmt:message key="admin.rejudge.range"/>:</td><td><form:input path="rg1" size="10" />/<form:input path="rg2" size="10"/></td>
            <tr/>
            <tr>
                <td style="align:right"><fmt:message key="admin.rejudge.pid"/>:</td>
                <td>
                    <form:select path="pid">
                        <form:option value="0" label="Select"/>
                        <form:options items="${problems}" itemValue="pid" itemLabel="title"/>                        
                    </form:select>
                </td>
            <tr/>
            <tr>
                <td style="align:right"><fmt:message key="admin.rejudge.status"/>:</td><td>
                    <form:select path="status">
                        <form:option value="Unqualified"/>
                        <form:option value="Judging"/>
                        <form:option value="Compilation Error"/>
                        <form:option value="Accepted"/>
                        <form:option value="Wrong Answer"/>
                        <form:option value="Runtime Error"/>
                        <form:option value="Presentation Error"/>
                        <form:option value="Memory Limit Exceeded"/>
                        <form:option value="Output Limit Exceeded"/>
                        <form:option value="Time Limit Exceeded"/>
                    </form:select> 
                </td>
            <tr/>
            <tr>
                <td><input type="submit" value="rejudge"/></td>
            </tr>
        </table>
    </form:form>
</div>            