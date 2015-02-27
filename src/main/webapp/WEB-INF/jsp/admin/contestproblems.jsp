<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="page.header.admin.managecontest" />
</h2>
<div class="postcontent">                    

    <table class="navigating" width="100%">
        <tr>
            <td width="10%"><a href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message key="page.managecontest.link.mc"/></a></td>
            <td width="10%"><a href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>" ><fmt:message key="page.managecontest.link.gf" /></a></td>
            <td width="10%"><a href="globalsettings.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.gs"/></a></td>
            <td width="10%"><fmt:message key="page.managecontest.link.mp"/></td>
            <td width="10%"><a href="contestproblemcolors.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mpc" /></a></td>
            <td width="10%"><a href="importicpcusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.iiu" /></a></td>
            <td width="10%"><a href="baylorxml.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.bx" /></a></td>
            <td width="10%"><a href="contestlanguages.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ml"/></a></td>
            <td width="10%"><a href="contestusers.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.mu"/></a></td>
            <td width="10%"><a href="contestawards.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.aw" /></a></td>
            <td width="10%"><a href="contestoverview.xhtml?cid=<c:url value="${contest.cid}"/>"><fmt:message key="page.managecontest.link.ov"/></a></td>
        </tr>
    </table>
    <form:form method="post" onsubmit="return SeleccionaRangos('problem');" commandName="contest">
        <fieldset style="width: 400px;">
            <legend><fmt:message key="page.managecontest.style" /></legend>
            <table class="contestlanguages">
                <tr>
                    <td>
                        <fmt:message key="page.managecontest.style" />:
                    </td>
                    <td><form:input path="style" readonly="true" /></td>
                </tr>                
            </table>            
        </fieldset>
        <fieldset style="border: none;">
            <table class="login">
                <tr>
                    <td>
                        <fmt:message key="contestproblems.cp" />
                    </td>
                    <td>

                    </td>
                    <td>
                        <fmt:message key="contestproblems.all" />
                    </td>
                </tr>
                <tr>
                    <td rowspan="2">
                        <form:select path="problemids" id="problem" size="14" cssStyle="width: 330px; border: 1px solid #577A5A;" multiple="true">
                            <form:options items="${contest.problems}" itemLabel="title" itemValue="pid"/>
                        </form:select>                        
                    </td>
                    <td>
                        <button name="boton" type="button" onclick="addremove('problem','contests_problem');">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </td>
                    <td rowspan="2">

                        <form:select path="" id="contests_problem" size="14" cssStyle="width: 330px; border: 1px solid #577A5A;" multiple="true" cssClass="login">
                            <form:options items="${problems}" itemLabel="title" itemValue="pid"/>
                        </form:select>                        
                    </td>
                </tr>
                <tr>
                    <td>
                        <button name="boton" type="button" onclick="addremove('contests_problem','problem');">
                            <i class="fa fa-arrow-left"></i>
                        </button>
                    </td>
                </tr>                
                <tr>
                    <td>
                        <span class="label label-danger"><form:errors path="style" /></span>
                    </td>
                </tr>
            </table>
        </fieldset>

        <c:choose>
            <c:when test="${contest.style == 4}">
                <table>
                    <tr>
                        <td>
                            Choose Level
                        </td>
                        <td>
                            <form:select path="levels">
                                <form:options items="${levels}"/>
                            </form:select>                            
                        </td>
                    </tr>
                </table>
            </c:when>
        </c:choose>

        <br/>
        <input type="submit" name="but" value="<spring:message code="button.update"/>"/>
        <br/>
    </form:form>
    <c:choose>
        <c:when test="${contest.style == 4}">
            <c:forEach items="${contest.plevels}" var="level">
                <fieldset>
                    <legend>Level ${level.level}</legend>
                    <table>
                        <c:forEach items="${level.problems}" var="problem">
                            <tr>
                                <td>
                                    ${problem.title} ${problem.pid} <a href="<c:url value="removeproblemcontest.xhtml?cid=${contest.cid}&pid=${problem.pid}"/>">delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </fieldset>
            </c:forEach>
        </c:when>
    </c:choose>

</div>            

