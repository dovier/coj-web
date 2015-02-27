<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">                    
    Courses Board
</h2>
<table class="navigating" width="100%">
    <tr>            
        <td width="10%"><a href="createcourse.xhtml>">Create Course</a></td>
        <td width="10%"><a href="mycourses.xhtml>">My Courses</a></td>
    </tr>
</table>

<div class="postcontent">                    

     <div id="display-table-container" data-reload-url="/tables/mycourses.xhtml"></div>     
</div>
<script>
$(document).ready(displayTableReload(" "));
</script>