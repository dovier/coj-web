<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="page.menu.admin.createcontest" />
</h2>
<div class="postcontent">
    <form:form method="post" commandName="contest">
        <table class="login">            
            <tr>
                <td style="align:right">ID:</td>
                <td><form:input path="cid"/></td>
                <td><span class="label label-danger"><form:errors path="cid" /></span></td>
            </tr>
            <tr>
                <td style="align:right">
                    <fmt:message key="page.createcontest.import" />
                </td>
                <td>
                    <form:checkbox path="importData"/>
                </td>
            </tr>

            <tr>
                <td style="align:right">CID:</td>
                <td>
                    <form:select path="importCid">
                        <form:option value="0" label="none"/>
                        <form:options items="${contests}" itemValue="cid" itemLabel="name"/>
                    </form:select>                    
                </td>
                <td><span class="label label-danger"><form:errors path="importCid" /></span></td>
            </tr>
        </table>
        <fieldset style="width: 400px;">
            <legend>Import</legend>
            <table class="login">
                <tr>
                    <td>
                        <fmt:message key="page.createcontest.import.all" />
                    </td>
                    <td>
                        <form:checkbox path="imports" value="all"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="page.createcontest.import.general" />
                    </td>
                    <td>
                        <form:checkbox path="imports" value="general"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="page.managecontest.link.mp"/>
                    </td>
                    <td>
                        <form:checkbox path="imports" value="problems"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="page.managecontest.link.ml"/>
                    </td>
                    <td>
                        <form:checkbox path="imports" value="languages"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="page.createcontest.import.flags" />
                    </td>
                    <td>
                        <form:checkbox path="imports" value="flags"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="page.managecontest.link.mu"/>
                    </td>
                    <td>
                        <form:checkbox path="imports" value="users"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <input type="submit" name="but" value="Add"/>
    </form:form>
</div>            
