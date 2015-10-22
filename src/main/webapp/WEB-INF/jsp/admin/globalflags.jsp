<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.general.admin.globalflags"/>
</h2>
<div class="postcontent">                    
    <form:form method="post" commandName="globalFlags" cssClass="form-">
        <table class="createnewuser">
            <tbody>
                <tr>
                    <td style="align:right"><fmt:message key="page.globalflags.disabledmailnotifications"/></td>
                    <td><form:checkbox path="mailNotificationDisabled"/></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="page.globalflags.enabledinternalmail"/></td>
                    <td><form:checkbox path="enabled_mail"/></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="page.globalflags.enabledsourcecodeview"/></td>
                    <td><form:checkbox path="enabled_source_code_view"/></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="page.globalflags.enabledsubmissionjudge"/></td>
                    <td><form:checkbox path="enabled_submission"/></td>
                </tr>
                <tr>
                    <td style="align:right"><spring:message code="tableval.maintenancemode" /></td>
                    <td><form:checkbox path="maintenanceMode"/></td>
                </tr>

                <tr>
                    <td>                        
                        <input class="btn btn-primary" type="submit" name="submit" id="submit" 
                               value="<spring:message code="judgeregister.update.value"/>" />
                    </td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>
</div>