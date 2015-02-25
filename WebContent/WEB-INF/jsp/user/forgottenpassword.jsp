<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>



<h2 class="postheader">
    <spring:message code="pagehdr.rupassword"/>
</h2>
<div class="postcontent">
    <form:form method="post" commandName="user">
        <table class="createnewuser">
            <tbody>
                <c:choose>
                    <c:when test="${showpassword != true}">
                        <tr>
                            <td colspan="2">
                                <spring:message code="text.rupassword.1"/>
                            </td>
                        </tr>                        
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td style="align:right"><spring:message code="fieldhdr.email"/>:</td>
                    <td><form:input path="email" size="30" readonly="${showpassword == true}" /> <a><i class="fa fa-asterisk" title="<spring:message code="mandatory.field"/>"></i></a></td>
                </tr>

                <tr>
                    <td></td>
                    <td><div class="error"><form:errors path="email" /></div></td>
                </tr>
                <c:choose>
                    <c:when test="${showpassword == true}">
                        <tr>
                            <td style="align:right"><spring:message code="fieldhdr.npassword"/>:</td>
                            <td><form:password path="password" size="30" maxlength="100"/><a><i class="fa fa-info-circle" title="<spring:message code="infomsg.8"/>"></i></a></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td><div class="error"><form:errors path="password" /></div></td>
                        </tr>

                        <tr>
                            <td style="align:right"><spring:message code="fieldhdr.cpassword"/>:</td>
                            <td><form:password path="confirmPassword" size="30" maxlength="100"/></td>
                        </tr>

                        <tr>
                            <td></td>
                            <td><div class="error"><form:errors path="confirmPassword" /></div></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>

                <tr>
                    <td><input type="submit" name="submit" id="submit" value="<spring:message code="button.recover"/>" /> <input type="reset" value="<spring:message code="button.reset"/>"/></td>
                </tr>
            </tbody>
        </table>
    </form:form>
</div>