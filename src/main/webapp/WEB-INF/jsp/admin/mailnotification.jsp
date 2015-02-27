<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="pagehdr.mailnotification" />
</h2>
<div class="postcontent">     
    <form:form method="post" enctype="multipart/form-data" commandName="notification">
        <label><fmt:message key="mail.subject" /></label><i class="fa fa-asterisk"></i>
        <div>
            <form:input path="subject" cssStyle="width: 99%"/>
            <span class="label label-danger"><form:errors path="subject" /></span>
        </div>
        <label><fmt:message key="mail.body" /></label><i class="fa fa-asterisk"></i>
        <div>
            <form:textarea path="text" id="answer" rows="15" cssStyle="width: 99%"/>
            <span class="label label-danger"><form:errors path="text" /></span>
        </div>
        
        <input type="submit" name="but" value="Add"/>
    </form:form>
</div>
