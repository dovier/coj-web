<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    Create Course
</h2>
<div class="postcontent">
    <form:form method="post" commandName="course">
        <table class="login">            
            
            <tr>
                <td>
                    Name <form:input path="name"/>
                </td>
                <td>
                    <form:errors path="name" />
                </td>
            </tr>            
        </table>
        
        <input type="submit" name="but" value="Add"/>
    </form:form>
</div>            
