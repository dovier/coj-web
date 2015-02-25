<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<div class="art-content">
    <div class="art-Post">
        <div class="art-Post-tl"></div>
        <div class="art-Post-tr"></div>
        <div class="art-Post-bl"></div>
        <div class="art-Post-br"></div>
        <div class="art-Post-tc"></div>
        <div class="art-Post-bc"></div>
        <div class="art-Post-cl"></div>
        <div class="art-Post-cr"></div>
        <div class="art-Post-cc"></div>
        <div class="art-Post-body">
            <div class="art-Post-inner">
                <h2 class="art-PostHeader">                    
                    <!--fmt:message key="page.24h.problem.title"/-->
                </h2>
                <div class="art-PostContent">
                    <form action="rejudging.xhtml" method="post">
                        <table style="align:left">
                        <tr>
                            <td><fmt:message key="admin.rejudge.submitid"/></td><td colspan="2"><input type="text" name="submitid"></td>
                        <tr>
                        <tr>
                            <td><fmt:message key="admin.rejudge.range"/></td><td><input type="text" name="sid1"></td><td><input type="text" name="sid2"></td>
                        <tr>
                        <tr>
                            <td><fmt:message key="admin.rejudge.pid"/></td><td colspan="2"><input type="text" name="pid"></td>
                        <tr>
                        <tr>
                            <td><fmt:message key="admin.rejudge.status"/></td><td colspan="2"><select name="status"><option>Unqualified</option><option>Judging</option></select> </td>
                        <tr>
                        <tr>
                            <td><input type="submit" value="rejudge"/></td>
                        </tr>

                    </table>

                    </form>
                </div>
                <div class="cleared"></div>
            </div>

            <div class="cleared"></div>
        </div>
    </div>
</div>
