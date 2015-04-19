<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Manage Programming Language
</h2>
<div class="postcontent">                    
    <form:form method="post" commandName="language">
        <table class="createnewuser">
            <tbody> 
                
                <tr>
                    <td style="align:right">Language<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="lid" readonly="true"/></td>
                    <td><span class="label label-danger"><form:errors path="lid" /></span></td>
                </tr>
                
                <tr>
                    <td style="align:right">Language<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="language" size="30" maxlength="70" /></td>
                    <td><span class="label label-danger"><form:errors path="language" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Key<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="key" size="30" maxlength="30" /></td>
                    <td><span class="label label-danger"><form:errors path="key" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Description<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="descripcion" size="30" maxlength="30"/></td>
                    <td><span class="label label-danger"><form:errors path="descripcion" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Name Bin<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="name_bin" size="30" maxlength="30" /></td>
                    <td><span class="label label-danger"><form:errors path="name_bin" /></span></td>
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