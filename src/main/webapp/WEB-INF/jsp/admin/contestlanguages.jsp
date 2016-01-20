<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->


<h2 class="postheader">
    <fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">
    <ul class="nav nav-pills">
        <li><a
                href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mc" /></a></li>
        <li><a
                href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.gf" /></a></li>
        <li><a
                href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.gs" /></a></li>
        <li><a
                href="<c:url  value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mp" /></a></li>
        <li><a
                href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mpc" /></a></li>
        <li><a
                href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.iiu" /></a></li>
        <li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.bx" /></a></li>
        <li class="active"><a
                href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.ml" /></a></li>
        <li><a
                href="<c:url value="contestusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mu" /></a></li>
        <li><a
                href="<c:url value="contestawards.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.aw" /></a></li>
        <li><a
                href="<c:url value="contestoverview.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.ov" /></a></li>
        <li><a
                href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.img" /></a></li>
    </ul>
    <br />
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}" />
        </div>                 
    </c:if> 
    <form:form method="post" commandName="contest">
        <fieldset style="width: 400px;">
            <legend>
                <fmt:message key="page.advancedcfg.languages" />
            </legend>
            <div class="contestlanguages">
                <form:checkboxes path="currlanguages" items="${languages}"
                                 itemValue="lid" delimiter="<br/>" itemLabel="descripcion" />
            </div>
        </fieldset>
        <br />
        <input type="submit" name="but" class="btn btn-primary"
               value="<spring:message code="button.update"/>" />
        <br />
    </form:form>
</div>
