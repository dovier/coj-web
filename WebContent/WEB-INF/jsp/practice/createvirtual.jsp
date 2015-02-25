<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<h2 class="postheader">
    <spring:message code="pagehdr.vccreate"/>
</h2>
<div class="postcontent">    
    <form:form method="post" commandName="virtualcontest">
        <table class="createnewuser">
            <tr>
                <td style="align:right"><spring:message code="fieldhdr.rc"/>: <i class="fa fa-asterisk"></i></td>
                <td colspan="8">
                    <form:select path="cid">
                        <form:option value="0">
                            <spring:message code="fieldval.select"/>
                        </form:option>
                        <form:options items="${contests}" itemLabel="name" itemValue="cid"/>
                    </form:select>
                </td>
                <td><span class="label label-danger"><form:errors path="cid" /></span></td>
            </tr>

            <tr>
                <td><spring:message code="fieldhdr.vcstart"/>:</td>                
                <td colspan="3">
                    <form:select path="iyear">
                        <c:forEach begin="${year - 1}" step="1" end="${year + 1}" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>

                    <form:select path="imonth">
                        <c:forEach begin="1" step="1" end="12" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>

                    <form:select path="iday">
                        <c:forEach begin="1" step="1" end="31" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>
                </td>

                <td><spring:message code="fieldhdr.timehms"/>:</td>
                <td>
                    <form:select path="ihour">
                        <c:forEach begin="0" step="1" end="23" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>

                    <form:select path="iminutes">
                        <c:forEach begin="0" step="1" end="59" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>

                    <form:select path="iseconds">
                        <c:forEach begin="0" step="1" end="59" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>
                </td>
                <td><span class="label label-danger"><form:errors path="initdate" /></span></td>
            </tr>
            <tr>
                <td><input type="submit" name="submit" id="submit" value="<spring:message code="button.create"/>" /></td>
                <td><input type="reset" name="submit" id="submit" value="<spring:message code="button.reset"/>" /></td>
                <td></td>
            </tr>
        </table>
    </form:form>    
</div>