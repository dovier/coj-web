<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Global Flags
</h2>
<div class="postcontent">                    
    <form:form method="post" commandName="globalFlags">
        <table class="createnewuser">
            <tbody>
                <tr>
                    <td style="align:right">Disabled Mail Notifications</td>
                    <td><form:checkbox path="mailNotificationDisabled"/></td>
                </tr>
                
                <tr>
                    <td style="align:right">Enabled Internal Mail</td>
                    <td><form:checkbox path="enabled_mail"/></td>
                </tr>

                <tr>
                    <td style="align:right">Enabled Source Code View</td>
                    <td><form:checkbox path="enabled_source_code_view"/></td>
                </tr>

                <tr>
                    <td style="align:right">Enabled SubmissionJudge 24h</td>
                    <td><form:checkbox path="enabled_submission"/></td>
                </tr>
                <tr>
                    <td style="align:right"><spring:message code="tableval.maintenancemode" /></td>
                    <td><form:checkbox path="maintenanceMode"/></td>
                </tr>
				
                <tr>
                    <td><input type="submit" name="submit" id="submit" value="Update" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>
</div>