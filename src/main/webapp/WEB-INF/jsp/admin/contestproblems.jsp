<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

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
        <li class="active"><a
                href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mp" /></a></li>
        <li><a
                href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mpc" /></a></li>
        <li><a
                href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.iiu" /></a></li>
        <li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.bx" /></a></li>
        <li><a
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
    <br/>
    <c:if test="${message != null}">
    <div class="alert alert-success alert-dismissable fade in">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <i class="fa fa-check"></i><spring:message code="${message}" />
                </div>                 
</c:if> 
    <form:form method="post" onsubmit="return SeleccionaRangos('problem');"
               commandName="contest">
        <fieldset style="border: none;">
            <table class="login">
                <tr>
                    <td><fmt:message key="contestproblems.cp" /></td>
                    <td></td>
                    <td><fmt:message key="contestproblems.all" /></td>
                </tr>
                <tr>
                    <td rowspan="2"><form:select cssClass="form-control" path="problemids" id="problem"
                                 size="14" cssStyle="width: 330px; border: 1px solid #577A5A;"
                                 multiple="true">
                            <form:options items="${contest.problems}" itemLabel="title"
                                          itemValue="pid" />
                        </form:select></td>
                    <td>
                        <button name="boton" type="button" class="btn btn-primary"
                                onclick="addremove('problem', 'contests_problem');">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </td>
                    <td rowspan="2">
                        <form:select path="" id="contests_problem" 
                                     size="14" cssStyle="width: 330px; border: 1px solid #577A5A;"
                                     multiple="true" cssClass="form-control">
                            <form:options items="${problems}" itemLabel="title" 
                                          itemValue="pid" />
                        </form:select></td>
                </tr>
                <tr>
                    <td>
                        <button name="boton" type="button" class="btn btn-primary" 
                                onclick="addremove('contests_problem', 'problem');">
                            <i class="fa fa-arrow-left"></i>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td><span class="label label-danger"><form:errors
                                path="style" /></span></td>
                </tr>
            </table>
        </fieldset>

        <c:choose>
            <c:when test="${contest.style == 4}">
                <table>
                    <tr>
                        <td><fmt:message key="contestproblems.chooselevel" /></td>
                        <td><form:select path="levels">
                                <form:options items="${levels}" />
                            </form:select></td>
                    </tr>
                </table>
            </c:when>
        </c:choose>

        <br />
        <input type="submit" name="but" class="btn btn-primary"
               value="<spring:message code="button.update"/>" />
        <br />
    </form:form>
    <c:choose>
        <c:when test="${contest.style == 4}">
            <c:forEach items="${contest.plevels}" var="level">
                <fieldset>
                    <legend>Level ${level.level}</legend>
                    <table>
                        <c:forEach items="${level.problems}" var="problem">
                            <tr>
                                <td>${problem.title} ${problem.pid} <a
                                        href="<c:url value="removeproblemcontest.xhtml?cid=${contest.cid}&pid=${problem.pid}"/>">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </fieldset>
            </c:forEach>
        </c:when>
    </c:choose>

</div>

