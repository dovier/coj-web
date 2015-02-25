<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Submission
</h2>
<div class="postcontent">
    <form:form method="post" commandName="submission">
        <table class="createnewuser">
            <tbody>
                <tr>
                    <td style="align:right">ID<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="submitId" readonly="true" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="submitId" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Username<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="username" size="30" maxlength="30" readonly="true"/></td>
                    <td><span class="label label-danger"><form:errors path="username" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Status<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="status">
                            <form:options items="${results}" itemLabel="status" itemValue="status"/>
                        </form:select>
                    </td>
                    <td><span class="label label-danger"><form:errors path="status" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Time (MS)<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="time" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="time" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Memory (KB)<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="memory" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="memory" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Source (KB)<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="font" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="font" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.register.language"/>:<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="language">
                            <form:options items="${planguages}" itemLabel="descripcion" itemValue="language"/>
                        </form:select>
                    </td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.register.enabled"/><i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:checkbox path="enabled"/>
                    </td>
                </tr>

                <tr>
                    <td style="align:right">Source Code</td>
                    <td>
                        <form:textarea path="code"/>
                    </td>
                    <td><span class="label label-danger"><form:errors path="code" /></span></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" id="submit" value="<fmt:message key="judge.updateaccount.submit"/>" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>
</div>