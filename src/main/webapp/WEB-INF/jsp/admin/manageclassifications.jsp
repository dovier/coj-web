<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"> </script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="pclassifi.title"/>
</h2>
<div class="postcontent">
    <form method="post" action="/admin/addclassifications.xhtml">
        <label><spring:message code="pclassifi.newclassification"/>:</label>       
        <input type="text" placeholder="new tag" name="name"/>            
        <input type="submit" value="<spring:message code="button.add"/>">
    </form>
   <br />

     <div id="display-table-container" data-reload-url="/admin/tables/manageclassifications.xhtml"></div>

</div>





