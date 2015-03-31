<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: Add News
</h2>
<div class="postcontent">    
    <form:form method="post" commandName="addnews">
        <table class="createnewuser">
            <tbody>
                <tr>
                    <td style="align:right">Title<i class="fa fa-asterisk"></i></td>
                    <td><form:input path="title"/></td>
                    <td><span class="label label-danger"><form:errors path="title" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Overview<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:textarea path="overview" />
                    </td>
                    <td><span class="label label-danger"><form:errors path="overview" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Content<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:textarea path="content" />
                    </td>
                    <td><span class="label label-danger"><form:errors path="content" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Overview<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:select path="rate">
                            <c:forEach begin="1" step="1" var="cont" end="10">
                                <form:option value="${cont}" />
                            </c:forEach>
                        </form:select>
                    </td>
                    <td><span class="label label-danger"><form:errors path="rate" /></span></td>
                </tr>

                <tr>
                    <td style="align:right">Enabled<i class="fa fa-asterisk"></i></td>
                    <td>
                        <form:checkbox path="enabled"/>
                    </td>
                    <td><span class="label label-danger"><form:errors path="enabled" /></span></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submit" id="submit" value="<fmt:message key="judge.register.submit.value"/>" /></td>
                    <td><input type="reset" name="submit" id="submit" value="<fmt:message key="judge.register.reset.value"/>" /></td>
                    <td></td>
                </tr>
            </tbody>
        </table>
    </form:form>    
</div>
