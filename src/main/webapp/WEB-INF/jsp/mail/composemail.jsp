<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<script type="text/javascript"
        src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script type='text/javascript'
        src="<c:url value="/js/jquery.js" />"></script>
<script type='text/javascript' src="<c:url value="/js/coj.js" />"></script>

<div class="pull-right"><a href="<c:url value="/mail/inbox.xhtml"/>" class="mailheader"><i
        class="fa fa-close"></i></a></div>
<h2 class="postheader">
    <spring:message code="pagehdr.mailcomposemsg"/>
</h2>

<div class="postcontent">
    <c:if test="${inboxOverflow}">
        <center>
            <div class="label label-info">
                <i class="fa fa-info-circle"></i>
                <spring:message code="inbox.overflow"/>
            </div>
        </center>
    </c:if>
    <c:if test="${not empty receiverInboxOverflow}">
        <center>
            <spring:message code="receiver.inbox.overflow"/>${receiverInboxOverflow}</center>
    </c:if>
</div>
<c:if test="${usernameerror != null}">
    <div class="alert alert-danger alert-dismissable fade in">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <i class="fa fa-check"></i><spring:message code="${usernameerror}"/>
    </div>
</c:if>

<form:form method="POST" class="form-horizontal" commandName="mail" id="composeform">

    <div class="form-group">
        <label class="col-md-1 control-label"><spring:message code="fieldhdr.to"/>:</label>

        <div class="col-md-4">

            <c:set var="usernameTo" value="${mail.usernameTo}"/>
            <c:set var="to" value="${mail.to.get(0)}"/>
            <c:set var="id_from" value="${mail.id_from}"/>
            <c:set var="tos" value="${usernameTo}"/>

            <c:forTokens items="${to}" delims=";" var="item_to">
                <c:if test="${item_to ne id_from}">
                    <c:set var="tos" value="${tos};${item_to}"/>
                </c:if>
            </c:forTokens>


            <%--<form:input path="usernameTo" class="form-control"/>--%>
                <input class="form-control" value="${tos}" name="usernameTo"/>
        </div>

        <div class="col-xs-8 col-xs-offset-1">
					<span class="label label-danger"><form:errors
                            path="usernameTo"/></span>
        </div>
    </div>

    <div class="form-group">
        <label class="col-md-1 control-label"><spring:message code="fieldhdr.subject"/>:</label>

        <div class="col-md-4"><form:input path="title" class="form-control"/></div>
        <div class="col-xs-8 col-xs-offset-1">
					<span class="label label-danger"><form:errors
                            path="title"/></span>
        </div>
    </div>

    <div class="form-group">

        <div class="col-md-10  col-md-offset-1"><form:textarea path="content" rows="15" id="code"
                                                               cssClass="des"/></div>
    </div>

    <div class="col-md-offset-1"><span class="label label-danger"><form:errors
            path="general"/></span></div>
    <div class="col-md-offset-5">
        <input type="submit" class="btn btn-primary" value="<spring:message code="button.send" />"/>
        <input type="reset" class="btn btn-primary" value="<spring:message code="button.reset" />"/>
        <input type="button" class="btn btn-primary" value="<spring:message code="button.savedraft" />"
               onclick="saveDraft()"/>
        <a href="<c:url value="/mail/inbox.xhtml"/>" class="mailheader btn btn-primary">&nbsp;<spring:message code="link.close"/></a>
    </div>

    <br/>

    <div id="notice" align="center" class="alert alert-info" style="display:  none">

    </div>


    </div>
</form:form>

<script src="/js/admin/utility.js"></script>

<script>
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.rejudge.title"/>";
    i18n.message = "<spring:message code="message.confirm.rejudge.body"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
    i18n.maildraftsave = "<spring:message code="pagehdr.maildraftsaved"/>";
</script>