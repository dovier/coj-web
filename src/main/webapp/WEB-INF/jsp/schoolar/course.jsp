<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/jquery.js" />"></script>

<h2 class="postheader">                    
    Course: ${course.name}
</h2>

<div class="postcontent">  

    <fieldset>
        <legend>Course Overview</legend>

        ${course.overview}
    </fieldset>
    <fieldset>
        <legend>Course Awards</legend>

        <span class="label label-success"> Points</font> ${userpoints} / ${allpoints}</span>
    </fieldset>
    <table class="volume">
        <tr>
            <td class="schoolarleft">
                <c:forEach items="${chapters}" var="chapter">
                    <fieldset>
                        <c:choose>
                            <c:when test="${chapter.number == 0}">
                                <legend> <span class="label label-success">Complementary Materials</span></legend>       
                                </c:when>
                                <c:otherwise>
                                <legend>Chapter #${chapter.number} / <span class="label label-success">${chapter.name}</span></legend>
                                </c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${chapter.enabled == true}">
                                    <c:forEach items="${chapter.materials}" var="content">
                                    <i class="fa fa-file-pdf-o"></i>&nbsp;   
                                    <a alt="FILE" href="<c:url value="/schoolar/viewfile.xhtml?course_id=${course.course_id}&content=${content.content_id}"/>">${content.content_address}</a> 
                                    <br/>
                                </c:forEach>

                                <c:forEach items="${chapter.problems}" var="problem">
                                    <a alt="PROBLEM" href="<c:url value="/schoolar/viewproblem.xhtml?course_id=${course.course_id}&pid=${problem.pid}"/>"><i class="fa fa-list"></i>&nbsp;${problem.title}</a> 
                                    <br/>
                                </c:forEach>

                            </c:when>

                            <c:otherwise>
                                <span class="label label-danger">${chapter.initdate}</span>                    
                            </c:otherwise>

                        </c:choose>

                    </fieldset>
                </c:forEach>
            </td>
            <td class="schoolarright">
                <fieldset>
                    <legend>Dash Board</legend>
                    <div class="designDiv">
                        <table class="volume" id="schoolarright">
                        </table>
                    </div>
                </fieldset>
            </td>
        </tr>
    </table>    

</div>

<script>
    setTimeout("updateBoard(0,${course.course_id})", 1000);
</script>