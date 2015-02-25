<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<h2 class="postheader">
    Manage Course Users ${course.name}
</h2>
<div class="postcontent">
    <table class="navigating" width="100%">
        <tr>
            <td width="10%"><a href="editcourse.xhtml?course_id=<c:url value="${course.course_id}"/>">Manage Course</a></td>
            <td width="10%"><a href="editcoursechapters.xhtml?course_id=<c:url value="${course.course_id}"/>">Edit Chapters/Levels</a></td>
            <td width="10%">Edit Users</td>
            <td width="10%"><a href="editcourseginfo.xhtml?course_id=<c:url value="${course.course_id}"/>">General Information</a></td>
        </tr>
    </table>

    <form:form method="post" onsubmit="return SeleccionarRangosCourses();" enctype="multipart/form-data" commandName="course">
        <table class="contestsetting">

            <tr>
                <td rowspan="2" colspan="2">
                    <div>
                        <form:select path="problemids" id="contests_problem" size="14" cssClass="login" cssStyle="width: 310px; height: 230px; border: 1px solid #577A5A;" multiple="true">                            
                        </form:select>    
                    </div>
                </td>
                <td>
                    <div > <button name="boton" type="button" onclick="addremove('contests_problem', 'all_problems');">
                            <img src="../images/enviar.gif" width="16" height="16" alt="<fmt:message key="contestproblems.alt.rigth" />"/>
                        </button>
                    </div>
                </td>
                <td rowspan="2" colspan="3">
                    <div>
                        <form:select path="" id="all_problems" size="14" cssStyle="width: 330px; height: 230px; border: 1px solid #577A5A;" multiple="true" cssClass="login">
                            <form:options items="${users}" itemLabel="username" itemValue="username"/>
                        </form:select>     
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div ><button name="boton" type="button" onclick="addremove('all_problems', 'contests_problem');">
                            <i class="fa fa-arrow-left"></i>
                        </button>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="7">
                    <div>   <span class="label label-danger"><form:errors path="problemids" /></span></div>
                </td>
            </tr>           

        </table>
        <input type="submit" name="but" value="<spring:message code="button.update"/>"/>
    </form:form>

     <div id="display-table-container" data-reload-url="/tables/editcourseusers.xhtml"></div> 



</div>
<script>
$(document).ready(displayTableReload(" "));
</script>