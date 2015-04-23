<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="year" value="${now}" pattern="yyyy" />
<h2 class="postheader">
    Manage Course ${course.name}
</h2>
<div class="postcontent">
    <table class="navigating" width="100%">
        <tr>
            <td width="10%"><a href="editcourse.xhtml?course_id=<c:url value="${course.course_id}"/>">Manage Course</a></td>
            <td width="10%">Edit Chapters/Levels</td>
            <td width="10%"><a href="editcourseusers.xhtml?course_id=<c:url value="${course.course_id}"/>">Edit Users</a></td>
            <td width="10%"><a href="editcourseginfo.xhtml?course_id=<c:url value="${course.course_id}"/>">General Information</a></td>

        </tr>
    </table>

    <form:form method="post" onsubmit="return SeleccionarRangosCourses();" enctype="multipart/form-data" commandName="course">
        <table class="contestsetting">   


            <tr>
                <td>Chapters:</td>
                <td><form:select path="total_chapters" onchange="changeCourse(${course.course_id});" id="courses">                         
                        <c:forEach begin="1" step="1" end="${course.chapter}" var="value">
                            <form:option value="${value}">${value}</form:option>
                        </c:forEach>
                    </form:select></td>
                <td colspan="3"><span class="label label-danger"><form:errors path="total_chapters" /></span></td>
            </tr>

            <tr>
                <td>Chapter's Name:</td>
                <td><form:input id="chpname" path="chapter_name"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors path="chapter_name" /></span></td>
            </tr>

            <tr>
                <td>Chapter's Init :</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <form:select path="iyear" id="iyear">
                                    <c:forEach begin="${year}" step="1" end="${year + 1}" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>

                                <form:select path="imonth" id="imonth">
                                    <c:forEach begin="1" step="1" end="12" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>

                                <form:select path="iday" id="iday">
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
                                <form:select path="ihour" id="ihours">
                                    <c:forEach begin="0" step="1" end="23" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>

                                <form:select path="iminutes" id="iminutes">
                                    <c:forEach begin="0" step="1" end="59" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>

                                <form:select path="iseconds" id="iseconds">
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
                <td rowspan="2" colspan="2">
                    <div>
                        <form:select path="problemids" id="contests_problem" size="14" cssClass="login" cssStyle="width: 310px; height: 230px; border: 1px solid #577A5A;" multiple="true">
                            <form:options items="${course.problems}" itemLabel="title" itemValue="pid"/>
                        </form:select>    
                    </div>
                </td>
                <td>
                    <div > <button name="boton" type="button" onclick="addremove('contests_problem', 'all_problems');">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                </td>
                <td rowspan="2" colspan="3">
                    <div>
                        <form:select path="" id="all_problems" size="14" cssStyle="width: 330px; height: 230px; border: 1px solid #577A5A;" multiple="true" cssClass="login">
                            <form:options items="${problems}" itemLabel="title" itemValue="pid"/>
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

            <tr>
                <td>Upload Materials</td>
                <td>
                    <input type="file" name="uploadfile" min="1024K"/>
                </td>
                <td>
                    <div>   <span class="label label-danger"><form:errors path="uploadfile" /></span></div>
                </td>
            </tr>

        </table>


        <input type="submit" name="but" value="<spring:message code="button.update"/>"/>
    </form:form>

    <table class="volume" id="mat_tbl">
        <thead>
        <th>Chapter Material</th>
        <th>Chapter</th>
        <th>Action</th>
        </thead>
        <tbody id="all_materials">
            <c:forEach items="${course.files}" var="mat">
                <tr id="${mat.content_id}">
                    <td>
                        ${mat.content_address} 
                    </td>
                    <td>${course.total_chapters}</td>
                    <td>
                        <a onclick="removeMaterial(${course.course_id}, ${course.total_chapters}, ${mat.content_id})">delete</a>                       
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>