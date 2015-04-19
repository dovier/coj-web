<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Manage Country
</h2>
<div class="postcontent">                    
    <form:form method="post" commandName="country">
        <table class="createnewuser">
            <tbody>

                <tr>
                    <td style="align:right">ID<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="id" size="30" maxlength="30" readonly="true"/></td>
                    <td><span class="label label-danger"><form:errors path="id" /></span></td>
                </tr>
                <tr>
                    <td style="align:right"><fmt:message key="page.addcountry.name"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="name" size="30" maxlength="70"/></td>
                    <td><span class="label label-danger"><form:errors path="name" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="page.addcountry.zip"/><i class="fa fa-asterisk"></i></td>
                    <td><form:input path="zip" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="zip" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Two letters code<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="zip_two" size="2" maxlength="2"/></td>
                    <td><span class="label label-danger"><form:errors path="zip_two" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Website<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="website"/></td>
                    <td><span class="label label-danger"><form:errors path="website" /></span></td>
                </tr>

                <tr>
                    <td style="align:right"><fmt:message key="judge.register.enabled"/><i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:checkbox path="enabled"/>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" id="submit" value="Update" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>
</div>