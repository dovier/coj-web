<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--<script type="text/javascript" src="<c:url value="/js/jquery.js" />">

</script>--%>

<link rel="stylesheet" href="<c:url value="/css/wboard.css"/>"
      type="text/css" media="screen"/>

<h2 class="postheader">
    <spring:message code="page.general.admin.header"/>: <spring:message code="page.header.admin.manageparsers"/>
</h2>

<button id="parseall" class="btn btn-primary " type="button">
    <fmt:message key="text.parseall"/>
</button>
<div class="postcontent">
    <div id="display-table-container"
         data-reload-url="/admin/tables/manageparsers.xhtml"></div>

    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close" />
        </a>
    </div>
    <div class="clearfix"></div>
</div>


    <script>
        $(document).ready(displayTableReload(" "));
    </script>