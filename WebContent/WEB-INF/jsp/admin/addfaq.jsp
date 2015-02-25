<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="pagehdr.addfaq" />
</h2>
<div class="postcontent">                    
    <form:form method="post" enctype="multipart/form-data" commandName="faq">
        <label><fmt:message key="addfaq.question" /></label><i class="fa fa-asterisk"></i>
        <div>
            <form:input path="question" cssStyle="width: 99%"/>
            <span class="label label-danger"><form:errors path="question" /></span>
        </div>
        <label><fmt:message key="addfaq.answer" /></label><i class="fa fa-asterisk"></i>
        <div>
            <form:textarea path="answer" id="answer" rows="15" cssStyle="width: 99%"/>
            <span class="label label-danger"><form:errors path="answer" /></span>
        </div>
        <input type="submit" name="but" value="Add"/>
    </form:form>
</div>
