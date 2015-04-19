<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>


<h2 class="postheader">
    <spring:message code="pagehdr.aeannouncement"/>
</h2>
<div class="postcontent">
    <form:form method="post" commandName="announcement">
        <table class="createnewuser">
            <tbody>

                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.id"/>:</td>
                    <td><form:input path="aid" readonly="true" disabled="true"/></td>
                    <td><span class="label label-danger"><form:errors path="aid" /></span></td>
                </tr>   

                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.content"/>:<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:textarea path="content"/>
                    </td>
                    <td><span class="label label-danger"><form:errors path="content" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.enabled"/>:</td>
                    <td>
                        <form:checkbox path="enabled" />
                    </td>
                    <td><span class="label label-danger"><form:errors path="enabled" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.contest" />:</td>
                    <td>
                        <form:select path="contest">
                            <form:options items="${contests}" itemLabel="name" itemValue="cid" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="<spring:message code="button.edit"/>" /></td>
                    <td><input type="reset" value="<spring:message code="button.reset"/>" /></td>
                </tr>
            </tbody>
        </table>
    </form:form>   
</div>