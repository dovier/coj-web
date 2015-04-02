<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<h2 class="postheader">
    Manage Course ${course.name}
</h2>
<div class="postcontent">
    <table class="navigating" width="100%">
        <tr>
            <td width="10%">Manage Course</td>
            <td width="10%"><a href="editcoursechapters.xhtml?course_id=<c:url value="${course.course_id}"/>">Edit Chapters/Levels</a></td>
            <td width="10%"><a href="editcourseusers.xhtml?course_id=<c:url value="${course.course_id}"/>">Edit Users</a></td>
            <td width="10%"><a href="editcourseginfo.xhtml?course_id=<c:url value="${course.course_id}"/>">General Information</a></td>

        </tr>
    </table>

    <form:form method="post" commandName="course">
        <table class="contestsetting">            
            <tr>
                <td>Course Name:</td>
                <td><form:input path="name"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors path="name" /></span></td>
            </tr>

            <tr>
                <td>
                    Public:
                </td>
                <td>
                    <form:checkbox path="ispublic"/>                  
                </td>
            </tr>  

            <tr>
                <td>Chapters:</td>
                <td>
                    <form:select path="chapter">
                        <c:forEach begin="1" step="1" end="12" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select>

                </td>
                <td colspan="3"><span class="label label-danger"><form:errors path="chapter" /></span></td>
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.initdate"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <form:select path="iyear">
                                    <c:forEach begin="${year}" step="1" end="${year + 1}" var="value">
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
                        </tr>
                    </table>
                </td>

                <td><fmt:message key="page.managecontest.inittime"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
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
                        </tr>
                    </table>
                </td>
                <td><span class="label label-danger"><form:errors path="initdate" /></span></td>
            </tr>            

            <tr>
                <td>
                    <fmt:message key="page.managecontest.style" />:
                </td>
                <td>
                    <form:select path="type">                        
                        <form:option value="0">Free</form:option>
                    </form:select>                    
                </td>
                <td colspan="3"><span class="label label-danger"><form:errors path="type" /></span></td>
            </tr>  

            <tr>
                <td>
                    Problem Points:
                </td>
                <td>
                    <form:select path="problem_points">                        
                        <form:option value="0">0</form:option>
                        <form:option value="1">1</form:option>
                        <form:option value="2">2</form:option>
                        <form:option value="3">3</form:option>
                        <form:option value="4">4</form:option>
                        <form:option value="5">5</form:option>
                    </form:select>                    
                </td>                
            </tr>  

            <tr>
                <td>Score Active:</td>
                <td>
                    <form:checkbox path="score_active"/>
                </td>                
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.enabled" />:</td>
                <td>
                    <form:checkbox path="enabled"/>
                </td>
                <td colspan="3"><span class="label label-danger"><form:errors path="enabled" /></span></td>
            </tr>



        </table>


        <input type="submit" name="but" value="<spring:message code="button.update"/>"/>
    </form:form>
</div>