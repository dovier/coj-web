<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<h2 class="postheader">
    Edit
</h2>
<div class="postcontent">
    <table class="navigating" width="100%">
        <tr>
            <td width="10%"><a href="editcourse.xhtml?course_id=<c:url value="${course.course_id}"/>">Manage Course</a></td>
            <td width="10%"><a href="editcoursechapters.xhtml?course_id=<c:url value="${course.course_id}"/>">Edit Chapters/Levels</a></td>
            <td width="10%"><a href="editcourseusers.xhtml?course_id=<c:url value="${course.course_id}"/>">Edit Users</a></td>
            <td width="10%">General Information</td>
        </tr>
    </table>
    <form:form method="post"  commandName="course" enctype="multipart/form-data">
        <div style="height: 100%">
            <form:textarea path="overview" id="code" cssClass="des" rows="15" cssStyle="width: 99%;"></form:textarea>
                <br/>
            </div>

            <div style="height: 100%">
                <input type="file" name="uploadfile" min="1024K"/>
                <div>   <span class="label label-danger"><form:errors path="uploadfile" /></span></div>
        </div>



        <br/>
        <input type="submit" name="but" value="<spring:message code="button.update"/>"/>
        <br/>
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