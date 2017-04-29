<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.managecontest.link.mc"/>
</h2>

<div class="postcontent">

    <ul class="nav nav-pills">
        <li class="active"><a
                href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.mc"/></a></li>
        <li><a
                href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.gf"/></a></li>
        <li><a
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
                href="<c:url value="contestoverview.xhtml?cid=${contest.cid}"/>"><fmt:message
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
        <table class="contestsetting">
            <tr>
                <td><fmt:message key="page.managecontest.name"/>:</td>
                <td><form:input path="name" cssClass="form-control"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors
                        path="name"/></span></td>
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.registration"/>:</td>
                <td><form:select path="registration" cssClass="form-control">
                    <form:option value="0">
                        <fmt:message key="page.managecontest.register.free"/>
                    </form:option>
                    <form:option value="1">
                        <fmt:message key="page.managecontest.register.limit"/>
                    </form:option>
                    <form:option value="2">
                        <fmt:message key="page.managecontest.register.admin"/>
                    </form:option>
                </form:select></td>
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.rglimit"/>:</td>

                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <div class="form-inline">
                                    <form:select path="ryear" cssClass="form-control">
                                        <c:forEach begin="1930" step="1" end="${contest.ryear + 1}"
                                                   var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select> <form:select path="rmonth" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="12" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select> <form:select path="rday" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="31" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>

                <td><fmt:message key="page.managecontest.inittime"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <div class="form-inline">
                                    <form:select path="rhour" cssClass="form-control">
                                        <c:forEach begin="0" step="1" end="23" var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select>
                                    <form:select path="rminutes" cssClass="form-control">
                                        <c:forEach begin="0" step="1" end="59" var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select> <form:select path="rseconds" cssClass="form-control">
                                    <c:forEach begin="0" step="1" end="59" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>

                <td><span class="label label-danger"><form:errors
                        path="rglimit"/></span></td>

            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.initdate"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <div class="form-inline">
                                    <form:select path="iyear" cssClass="form-control">
                                        <c:forEach begin="1930" step="1" end="${contest.iyear + 1}"
                                                   var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select> <form:select path="imonth" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="12" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select> <form:select path="iday" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="31" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
                <td><fmt:message key="page.managecontest.inittime"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <div class="form-inline">
                                    <form:select path="ihour" cssClass="form-control">
                                        <c:forEach begin="0" step="1" end="23" var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select> <form:select path="iminutes" cssClass="form-control">
                                    <c:forEach begin="0" step="1" end="59" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select> <form:select path="iseconds" cssClass="form-control">
                                    <c:forEach begin="0" step="1" end="59" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
                <td><span class="label label-danger"><form:errors
                        path="initdate"/></span></td>
            </tr>


            <tr>
                <td><fmt:message key="page.managecontest.enddate"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <div class="form-inline">
                                    <form:select path="eyear" cssClass="form-control">
                                        <c:forEach begin="1930" step="1" end="${contest.eyear + 1}"
                                                   var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select> <form:select path="emonth" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="12" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select> <form:select path="eday" cssClass="form-control">
                                    <c:forEach begin="1" step="1" end="31" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
                <td><fmt:message key="page.managecontest.inittime"/>:</td>
                <td>
                    <table class="login">
                        <tr>
                            <td colspan="3">
                                <div class="form-inline">
                                    <form:select path="ehour" cssClass="form-control">
                                        <c:forEach begin="0" step="1" end="23" var="value">
                                            <form:option value="${value}">${value}</form:option>
                                        </c:forEach>
                                    </form:select> <form:select path="eminutes" cssClass="form-control">
                                    <c:forEach begin="0" step="1" end="59" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select> <form:select path="eseconds" cssClass="form-control">
                                    <c:forEach begin="0" step="1" end="59" var="value">
                                        <form:option value="${value}">${value}</form:option>
                                    </c:forEach>
                                </form:select>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
                <td><span class="label label-danger"><form:errors
                        path="enddate"/></span></td>
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.style"/>:</td>
                <td><form:select path="style" cssClass="form-control">
                    <form:option value="0"><fmt:message key="page.managecontest.none"/></form:option>
                    <form:options items="${styles}" itemValue="sid" itemLabel="name"/>
                </form:select></td>
                <td colspan="3"><span class="label label-danger"><form:errors
                        path="style"/></span></td>
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.users"/>:</td>
                <td><form:select path="contestant" cssClass="form-control">
                    <form:option value="1">
                        <fmt:message key="page.managecontest.team"/>
                    </form:option>
                    <form:option value="2">
                        <fmt:message key="page.managecontest.user"/>
                    </form:option>
                    <form:option value="3">
                        <fmt:message key="page.managecontest.both"/>
                    </form:option>
                </form:select></td>
            </tr>

            <tr>
                <td><fmt:message key="page.managecontest.enabled"/>:</td>
                <td><form:checkbox path="enabled"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors
                        path="enabled"/></span></td>
            </tr>

          <%--  <tr>
                <td><fmt:message key="page.managecontest.vtemplate"/>:</td>
                <td><form:checkbox path="vtemplate"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors
                        path="vtemplate"/></span></td>
            </tr>
            <tr>--%>
                <td><fmt:message key="page.managecontest.status.block"/>:</td>
                <td><form:checkbox path="blocked"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors
                        path="blocked"/></span></td>
            </tr>
            <tr>
                <td><fmt:message key="page.managecontest.grouped"/>:</td>
                <td><form:checkbox path="grouped"/></td>
                <td colspan="3"><span class="label label-danger"><form:errors
                        path="grouped"/></span></td>
            </tr>

        </table>
        <div class="pull-right">

            <input type="submit" name="but" class="btn btn-primary"
                   value="<spring:message code="button.edit"/>"/>
            <a class="btn btn-primary" href="<c:url value="/admin/admincontests.xhtml"/>"><spring:message
                    code="button.close"/></a>
        </div>
    </form:form>
    <div class="clearfix"></div>
</div>

<script src="/js/admin/utility.js"></script>