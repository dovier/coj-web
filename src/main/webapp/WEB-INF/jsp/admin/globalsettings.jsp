<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.managecontest.link.gs"/>
</h2>

<div class="postcontent">
    <ul class="nav nav-pills">
        <li><a
                href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mc"/></a></li>
        <li><a
                href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.gf"/></a></li>
        <li class="active"><a
                href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.gs"/></a></li>
        <li><a
                href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mp"/></a></li>
        <c:if test="${eproblem}">
            <li><a
                    href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
                    key="page.managecontest.link.mpc"/></a></li>
        </c:if>
        <li><a
                href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.iiu"/></a></li>
        <li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.bx"/></a></li>
        <li><a
                href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.ml"/></a></li>
        <li><a
                href="<c:url value="contestusers.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mu"/></a></li>
        <li><a
                href="<c:url value="contestawards.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.aw"/></a></li>
        <li><a
                href="<c:url value="contestoverview.xhtml?cid=${contest.cid}" />"><fmt:message
                key="page.managecontest.link.ov"/></a></li>
        <li><a
                href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.img"/></a></li>
        <li><a
                href="<c:url value="downloadsourcezip.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.download.sources"/></a></li>
    </ul>

    <br/>
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>

    <form:form method="post" commandName="contest">
        <fieldset style="width: 400px;">
            <legend>
                <fmt:message key="page.managecontest.style"/>
            </legend>
                 <span class="label label-info"><i
                         class="fa fa-info-circle"></i> ${style.name}</span>


            <c:choose>
                <c:when test="${contest.registration eq 0}">
                                <span class="label label-info"><i
                                        class="fa fa-info-circle"></i> <fmt:message
                                        key="page.managecontest.register.free"/> </span>
                </c:when>

                <c:when test="${contest.registration eq 1}">
                    <fmt:message key="page.globalsettings.rg.limit"/>&nbsp;
                    <form:input path="total_users"/>&nbsp;
                                <span class="label label-danger">&nbsp;<form:errors
                                        path="total_users"/></span>
                </c:when>

                <c:when test="${contest.registration eq 2}">
                                <span class="label label-info"><i
                                        class="fa fa-info-circle"></i> <fmt:message
                                        key="page.managecontest.register.admin"/> </span>
                </c:when>
            </c:choose>

        </fieldset>
        <fieldset style="width: 400px;">
            <table class="contestlanguages">
                <c:choose>
                    <c:when test="${contest.style eq 0}">
                        <tr>
                            <td><span class="label label-danger"><spring:message code="messages.general.undefinedproperty"/> </span></td>
                        </tr>
                    </c:when>
                    <c:when test="${contest.style eq 1}">
                        <tr>
                            <td><fmt:message key="page.globalsettings.acm.penal"/></td>
                            <td><form:input path="penalty" id="penalty" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="penalty"/></span></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="page.globalsettings.acm.frtime"/></td>
                            <td><form:input path="frtime" id="frtime" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="frtime"/></span></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="page.globalsettings.acm.dead"/></td>
                            <td><form:input path="deadtime" id="deadtime" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="deadtime"/></span></td>
                        </tr>
                        <tr>
                            <td><fmt:message key="page.globalsettings.acm.unfreeze"/></td>
                            <td><form:input path="unfreeze_time" id="unfreeze_time" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="unfreeze_time"/></span></td>
                        </tr>
                    </c:when>

                    <c:when test="${contest.style eq 2}">
                        <tr>
                            <td><fmt:message key="page.globalsettings.ioi.task"/></td>
                            <td><form:input path="ioimark" id="ioimark" maxlength="9" /></td>
                            <td><span class="label label-danger"><form:errors
                                    path="ioimark"/></span></td>
                        </tr>
                    </c:when>

                    <c:when test="${contest.style eq 3}">
                        <tr>
                            <td><fmt:message key="page.globalsettings.free.points"/></td>
                            <td><form:input path="ppoints" id="ppoints" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="ppoints"/></span></td>
                        </tr>
                    </c:when>

                    <c:when test="${contest.style eq 4}">
                        <tr>
                            <td><spring:message code="fieldhdr.levels"/></td>
                            <td><form:input path="levels" id="levels" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="levels"/></span></td>
                        </tr>

                        <tr>
                            <td><spring:message code="fieldhdr.acbylevels"/></td>
                            <td><form:input path="acbylevels" id="acbylevels" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="acbylevels"/></span></td>
                        </tr>

                        <tr>
                            <td><spring:message code="fieldhdr.aclimit"/></td>
                            <td><form:input path="aclimit" id="aclimit" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="aclimit"/></span></td>
                        </tr>

                        <tr>
                            <td><spring:message code="fieldhdr.pointsbyproblem"/></td>
                            <td><form:input path="ppoints" id="ppoints" maxlength="9" cssClass="form-control"/></td>
                            <td><span class="label label-danger"><form:errors
                                    path="ppoints"/></span></td>
                        </tr>

                    </c:when>

                </c:choose>

            </table>
        </fieldset>
        <fieldset style="width: 400px;">
            <legend>
                <fmt:message key="page.globalsettings.flags.header"/>
            </legend>
            <table class="contestlanguages">
                <tr>
                    <td><fmt:message key="page.globalsettings.flags.balloon"/></td>
                    <td><form:checkbox path="balloon"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="page.globalsettings.flags.gallery"/></td>
                    <td><form:checkbox path="gallery"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="page.globalsettings.flags.saris"/></td>
                    <td><form:checkbox path="saris"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="page.globalsettings.flags.stats"/></td>
                    <td><form:checkbox path="show_stats"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.statsout"/></td>
                    <td><form:checkbox path="show_stats_out"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="page.globalsettings.flags.status"/></td>
                    <td><form:checkbox path="show_status"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.statusout"/></td>
                    <td><form:checkbox path="show_status_out"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.board"/></td>
                    <td><form:checkbox path="show_scoreboard"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.boardout"/></td>
                    <td><form:checkbox path="show_scoreboard_out"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.allrg"/></td>
                    <td><form:checkbox path="allow_registration"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.unfreezeauto"/></td>
                    <td><form:checkbox path="unfreeze_auto"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.problem"/></td>
                    <td><form:checkbox path="show_problem_out"/></td>
                </tr>

                <tr>
                    <td><fmt:message key="page.globalsettings.flags.show.test"/></td>
                    <td><form:checkbox path="show_ontest"/></td>
                </tr>

            </table>
        </fieldset>


        <fieldset style="width: 400px;">
            <legend>
                <fmt:message key="page.globalsettings.medals"/>
            </legend>
            <table class="contestlanguages">
                <tr>
                    <td><fmt:message key="page.general.gold"/></td>
                    <td><form:input path="gold" id="gold" maxlength="9" cssClass="form-control"/></td>
                    <td><span class="label label-danger"><form:errors
                            path="gold"/></span></td>
                </tr>
                <tr>
                    <td><fmt:message key="page.general.silver"/></td>
                    <td><form:input path="silver" id="silver" maxlength="9" cssClass="form-control"/></td>
                    <td><span class="label label-danger"><form:errors
                            path="silver"/></span></td>
                </tr>
                <tr>
                    <td><fmt:message key="page.general.bronze"/></td>
                    <td><form:input path="bronze" id="bronze" maxlength="9" cssClass="form-control"/></td>
                    <td><span class="label label-danger"><form:errors
                            path="bronze"/></span></td>
                </tr>
            </table>
        </fieldset>
        <div class="pull-right">
            <input type="submit" name="but" class="btn btn-primary"
                   value="<spring:message code="button.edit"/>"/>
            <a class="btn btn-primary" href="<c:url value="/admin/admincontests.xhtml"/>"><spring:message
                    code="button.close"/></a>
        </div>
    </form:form>

    <div class="clearfix"></div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $('#penalty').numeric();
        $('#frtime').numeric();
        $('#deadtime').numeric();
        $('#unfreeze_time').numeric();
        $('#gold').numeric();
        $('#silver').numeric();
        $('#bronze').numeric();
        $('#ppoints').numeric();
        $('#ioimark').numeric();
        $('#levels').numeric();
        $('#acbylevels').numeric();
        $('#aclimit').numeric();
    });
</script>


<script src="/js/admin/utility.js"></script>