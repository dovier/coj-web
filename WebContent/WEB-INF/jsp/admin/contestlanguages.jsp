<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->


<h2 class="postheader">
    <fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">                      
    <table class="navigating" width="100%">
        <tr>
            <td width="10%"><a href="managecontest.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc"/></a></td>
            <td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>" ><fmt:message
						key="page.managecontest.link.gf" /></a></td>
            <td width="10%"><a href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs"/></a></td>
            <td width="10%"><a href="<c:url  value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mp"/></a></td>
            <td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
            <td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
            <td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
            <td width="10%"><fmt:message key="page.managecontest.link.ml"/></td>
            <td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu"/></a></td>
            <td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
            <td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ov"/></a></td>
        </tr>
    </table>
    <form:form method="post" commandName="contest">
        <fieldset style="width: 400px;">
            <legend><fmt:message key="page.advancedcfg.languages" /></legend>
            <div class="contestlanguages">
                <form:checkboxes path="currlanguages" items="${languages}" itemValue="lid" delimiter="<br/>" itemLabel="descripcion" />
            </div>
        </fieldset>
        <br/>
        <input type="submit" name="but" value="<spring:message code="button.update"/>"/>
        <br/>
    </form:form>
</div>            