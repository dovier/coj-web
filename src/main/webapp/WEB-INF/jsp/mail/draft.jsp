<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>


<h2 class="postheader">                    
    <spring:message code="pagehdr.maildraft" />
</h2>
<div class="postcontent">
    <center><spring:message code="fieldhdr.usage"/>: <fmt:formatNumber value="${mail.percent}" minFractionDigits="2" maxFractionDigits="2"/>% (${mail.consumed_quote} <spring:message code="fieldval.bytesof"/> ${mail.mail_quote} <spring:message code="fieldval.bytes"/>) </center>
    <a href="<c:url value="composemail.xhtml"/>"><i class="fa fa-pencil"></i>&nbsp;<spring:message code="link.compose"/></a>
    <a onclick="return validate();" href="<c:url value="deleteallmail.xhtml?delete=3"/>" title="<fmt:message key="link.delall"/>"><i class="fa fa-trash"></i></a>
    <br/>

    <div id="display-table-container" data-reload-url="/tables/draft.xhtml"></div>    

</div>
<script type="text/javascript">
    function validate(){
        var answer = confirm ("<spring:message code="imfomsg.draft.1"/>");
        if(!answer)
            return false;
        return true;
    }
    
    $(document).ready(displayTableReload("?page=1"));
</script>
