<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="vcontestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.overview"/>
</h2>
<div class="postcontent">
    <fieldset>
        <legend><spring:message code="fieldhdr.overalldata"/></legend>
        <table width="100%" class="userinfo">
            <tr>
                <td>
                    <spring:message code="fieldhdr.contesttype"/>:
                </td>
                <td>
                    <c:forEach items="${styles}" var="style">
                        <c:if test="${style.sid eq contest.style}">
                            ${style.name}
                        </c:if>
                    </c:forEach>
                </td>
            </tr>            
            <tr>
                <td>
                    <spring:message code="fieldhdr.accesstype"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.registration eq 2}">
                            <a title="<spring:message code="titval.private"/>"><i class="fa fa-lock fa-lg"></i></a>
                        </c:when>
                        <c:otherwise>
                        <a title="<spring:message code="titval.first"/>"><i class="fa fa-unlock fa-lg"></i></a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <spring:message code="fieldhdr.regtype"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.registration eq 0}">
                            <spring:message code="page.managecontest.register.free"/>
                        </c:when>
                        <c:when test="${contest.registration eq 1}">
                            <spring:message code="page.managecontest.register.limit"/>
                        </c:when>
                        <c:when test="${contest.registration eq 2}">
                            <spring:message code="page.managecontest.register.admin"/>
                        </c:when>
                    </c:choose>
                </td>
            </tr>            
            <tr>
                <td>
                    <spring:message code="fieldhdr.templatevirtual"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.vtemplate == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>
                    <spring:message code="fieldhdr.penaltyrejectedsub"/>:
                </td>
                <td>
                    ${contest.penalty}
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.proglanguages"/>:
                </td>
                <td>
                    <c:forEach items="${planguages}" var="planguage">
                        ${planguage.language} 
                    </c:forEach>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.frozentime"/>:
                </td>
                <td>
                    ${contest.frtime}
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.deadtime"/>:
                </td>
                <td>
                    ${contest.deadtime}
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showclarifall"/>:
                </td>
                <td>
                    <spring:message code="fieldval.yes"/>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showproball"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_problem_out == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showjudgscontestants"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_status == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showjudgsall"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_status_out == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showrankcontestants"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_scoreboard == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showrankall"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_scoreboard_out == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showstatscontestants"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_status == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.showstatsall"/>:
                </td>
                <td>
                    <c:choose>
                        <c:when test="${contest.show_status_out == true}">
                            <spring:message code="fieldval.yes"/>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="fieldval.no"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.goldmedals"/>:
                </td>
                <td>
                    ${contest.gold}
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.silvermedals"/>:
                </td>
                <td>
                    ${contest.silver}
                </td>
            </tr>

            <tr>
                <td>
                    <spring:message code="fieldhdr.bronzemedals"/>:
                </td>
                <td>
                    ${contest.bronze}
                </td>
            </tr>
        </table>
    </fieldset>


    <fieldset>
        <legend><spring:message code="fieldhdr.specificrules"/></legend>
        ${contest.overview}
    </fieldset>

</div>
