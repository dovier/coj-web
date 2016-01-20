<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel='stylesheet' href="<c:url value="/css/spectrum.css" />" />
<!--<script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>-->
<script type="text/javascript" src="<c:url value="/js/spectrum.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">

    <ul class="nav nav-pills">
        <li><a href="<c:url value="managecontest.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.mc" /></a></li>
        <li><a
                href="<c:url value="contestglobalflags.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.gf" /></a></li>
        <li><a href="<c:url value="globalsettings.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.gs" /></a></li>
        <li><a href="<c:url value="contestproblems.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.mp" /></a></li>
        <li><a
                href="<c:url value="contestproblemcolors.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.mpc" /></a></li>
        <li class="active"><a
                href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.iiu" /></a></li>
        <li><a href="<c:url value="baylorxml.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.bx" /></a></li>
        <li><a href="<c:url value="contestlanguages.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.ml" /></a></li>
        <li><a href="<c:url value="contestusers.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.mu" /></a></li>
        <li><a href="<c:url value="contestawards.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.aw" /></a></li>
        <li><a href="<c:url value="contestoverview.xhtml?cid=${cid}"/>"><fmt:message
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
    <form:form method="post" enctype="multipart/form-data"
               commandName="contest">
        <input type='hidden' name='cid' value="${cid}" />
        <p>
            <fmt:message key="page.importicpusers.info" />
        </p>
        <table>
            <tr>
                <td><fmt:message key="page.importicpusers.teamnameprefix" /></td>
                <td><input type='text' name='prefix' value=""  class="form-control" /></td>
            </tr>
            <tr>
                <td><fmt:message key="page.importicpusers.warmupcontestid" /></td>
                <td><input type='text' name='warmupCid' value="" class="form-control" /></td>
            </tr>
            <tr>
                <td>Person.tab</td>
                <td><div class="form-inline"><input type="file" name="personFile" size="40px" class="form-control" /><a><i data-toggle="tooltip" class="fa fa-asterisk"
                                                                                                                           title="<spring:message code="mandatory.field"/>">
                            </i></a></div></td>
            </tr>
            <tr>
                <td>School.tab</td>
                <td><div class="form-inline"><input type="file" name="schoolFile" size="40px" class="form-control" /> <a><i data-toggle="tooltip" class="fa fa-asterisk"
                                                                                                                            title="<spring:message code="mandatory.field"/>">
                            </i></a></div></td>
            </tr>
            <tr>
                <td>Site.tab</td>
                <td><div class="form-inline">
                        <input type="file" name="siteFile" size="40px" class="form-control" /> <a><i data-toggle="tooltip" class="fa fa-asterisk"
                                                                                                     title="<spring:message code="mandatory.field"/>">
                            </i></a>
                    </div>
                </td>
            </tr>
            <tr>
                <td>Team.tab</td>
                <td><div class="form-inline"><input type="file" name="teamFile" size="40px" class="form-control" /> <a><i data-toggle="tooltip" class="fa fa-asterisk"
                                                                                                                          title="<spring:message code="mandatory.field"/>">
                            </i></a></div></td>
            </tr>
            <tr>
                <td>TeamPerson.tab</td>
                <td><div class="form-inline"><input type="file" name="teamPersonFile" size="40px" class="form-control" /><a><i data-toggle="tooltip" class="fa fa-asterisk"
                                                                                                                               title="<spring:message code="mandatory.field"/>">
                            </i></a></div></td>
            </tr>
        </table>
        <input type="submit" name="but" class="btn btn-primary"
               value="<spring:message code="button.update"/>" />
    </form:form>
</div>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>


