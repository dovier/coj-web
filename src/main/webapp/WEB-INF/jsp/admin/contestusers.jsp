<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type="text/javascript" src="/js/jquery.typewatch.js"></script>
<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.managecontest.link.mu"/>
</h2>

<div class="postcontent">
    <ul class="nav nav-pills">
        <li><a
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
        <li class="active"><a
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
    </ul>
    <br/>
    <c:if test="${message1 != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message1}"/>
        </div>
    </c:if>
    <form:form method="post" onsubmit="return SeleccionarRangosContest();"
               commandName="contest">
        <c:if test="${contest.grouped eq true}">
            <div class="row">
                <div class="form-group">
                <div class="col-md-2"><fmt:message key="page.managecontest.group"/></div>
                <div class="col-md-3"><form:input path="groupd" cssClass="form-control"/></div>
                <div class="col-md-2"><fmt:message key="page.managecontest.guestgroup"/></div>
                <div class="col-md-3"><form:input path="guestGroup" cssClass="form-control"/>
                </div>
                <div><a> <i data-toggle="tooltip" class="fa fa-info-circle"
                                             title="<spring:message code="page.managecontest.guestgroup.info"/>"></i> </a></div>
                    </div>
            </div>
            <%--      <div class="row">
                  </div>--%>
            <%--  <table class="contestlanguages">
                  <tr>
                      <td colspan="2"><fmt:message
                              key="page.managecontest.guestgroup"/></td>
                      <td colspan="4"><form:input path="guestGroup" cssClass="form-control"/></td>
                  </tr>
                  <tr>
                      <td colspan="2"><fmt:message key="page.managecontest.group"/>:</td>
                      <td colspan="4"><form:input path="groupd" cssClass="form-control"/></td>
                  </tr>
              </table>--%>

        </c:if>
        <table class="contestlanguages">
            <c:choose>
                <c:when test="${contest.registration eq 0}">

                    <tr>

                        <td><span class="label label-info"><i
                                class="fa fa-info-circle"></i> <fmt:message
                                key="page.managecontest.register.free"/> </span></td>

                    </tr>
                </c:when>

                <c:when test="${contest.registration eq 1}">
                    <tr>
                        <td><fmt:message key="page.globalsettings.rg.limit"/></td>
                    </tr>
                </c:when>

                <c:when test="${contest.registration eq 2}">
                    <tr>
                        <td><fmt:message key="contestusers.cu"/></td>
                        <td></td>
                        <td><fmt:message key="contestusers.all"/></td>
                    </tr>
                    <tr>

                        <td rowspan="2"><form:select path="usersids"
                                                     id="contest_users" size="14" cssClass="login form-control"
                                                     cssStyle="width: 310px; border: 1px solid #577A5A;"
                                                     multiple="true">
                        </form:select></td>
                        <td>
                            <button name="boton" type="button" class="btn btn-primary"
                                    onclick="addremove('contest_users', 'allusers');">
                                <i class="fa fa-arrow-right"></i>
                            </button>
                        </td>
                        <td rowspan="2"><input id="search-allusers" cssClass="form-control"/>
                            <form:select path="" id="allusers" size="14" cssClass="login form-control"
                                         cssStyle="width: 310px; border: 1px solid #577A5A;"
                                         multiple="true">
                                <form:options items="${allusers}" itemValue="uid"
                                              itemLabel="username"/>
                            </form:select></td>

                    </tr>
                    <tr>
                        <td>
                            <button name="boton" type="button" class="btn btn-primary"
                                    onclick="addremove('allusers', 'contest_users');">
                                <i class="fa fa-arrow-left"></i>
                            </button>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </table>

        <table class="contestlanguages">
            <tr>
                <td><fmt:message key="contestjudges.cu"/></td>
                <td></td>
                <td><fmt:message key="contestjudges.all"/></td>
            </tr>


            <tr>

                <td rowspan="2"><form:select path="judgesids"
                                             id="contest_users_clarification" size="14" cssClass="login form-control"
                                             cssStyle="width: 310px; border: 1px solid #577A5A;"
                                             multiple="true">
                    <form:options items="${contest.judges}" itemValue="uid"
                                  itemLabel="username"/>
                </form:select></td>

                <td>
                    <button name="boton" type="button" class="btn btn-primary"
                            onclick="addremove('contest_users_clarification', 'all_judge');">
                        <i class="fa fa-arrow-right"></i>
                    </button>
                </td>

                <td rowspan="2"><form:select path="" id="all_judge" size="14"
                                             cssClass="login form-control"
                                             cssStyle="width: 310px; border: 1px solid #577A5A;"
                                             multiple="true">
                    <form:options items="${alljudges}" itemLabel="username"
                                  itemValue="uid"/>
                </form:select></td>
            </tr>
            <tr>
                <td>
                    <button name="boton" type="button" class="btn btn-primary"
                            onclick="addremove('all_judge', 'contest_users_clarification');">
                        <i class="fa fa-arrow-left"></i>
                    </button>
                </td>
            </tr>
        </table>
        <table class="contestlanguages">

            <c:choose>
                <c:when test="${contest.registration eq 0}">
                    <tr>

                        <td><span class="label label-info"><i
                                class="fa fa-info-circle"></i> <fmt:message
                                key="page.managecontest.register.free"/> </span></td>

                    </tr>
                </c:when>

                <c:when test="${contest.registration eq 1}">
                    <tr>
                        <td><fmt:message key="page.globalsettings.rg.limit"/></td>
                    </tr>
                </c:when>

                <c:when test="${contest.registration eq 2}">
                    <tr>
                        <td><fmt:message key="contestusers.bt"/></td>
                        <td></td>
                        <td><fmt:message key="contestusers.all"/></td>
                    </tr>
                    <tr>

                        <td rowspan="2"><form:select path="balloontrackerids"
                                                     id="balloontrackers" size="14" cssClass="login form-control"
                                                     cssStyle="width: 310px; border: 1px solid #577A5A;"
                                                     multiple="true">
                            <form:options items="${contest.balloontrackers}" itemValue="uid"
                                          itemLabel="username"/>
                        </form:select></td>

                        <td>
                            <button name="boton" type="button" class="btn btn-primary"
                                    onclick="addremove('balloontrackers', 'bt_allusers');">
                                <i class="fa fa-arrow-right"></i>
                            </button>
                        </td>

                        <td rowspan="2"><input id="search-btusers" cssClass="form-control"/><form:select path=""
                                                                                                         id="bt_allusers"
                                                                                                         size="14"
                                                                                                         cssClass="login form-control"
                                                                                                         cssStyle="width: 310px; border: 1px solid #577A5A;"
                                                                                                         multiple="true">
                            <form:options items="${btusers}" itemValue="uid"
                                          itemLabel="username"/>
                        </form:select></td>

                    </tr>
                    <tr>
                        <td>
                            <button name="boton" type="button" class="btn btn-primary"
                                    onclick="addremove('bt_allusers', 'balloontrackers');">
                                <i class="fa fa-arrow-left"></i>
                            </button>
                        </td>
                    </tr>
                </c:when>
            </c:choose>
        </table>
        <div class="pull-right">
            <input type="submit" name="but" class="btn btn-primary"
                   value="<spring:message code="button.update"/>"/>
            <a class="btn btn-primary" href="<c:url value="/admin/admincontests.xhtml"/>"><spring:message
                    code="button.close"/></a>
        </div>
    </form:form>
    <div class="clearfix"></div>
    <br/>
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>
    <c:if test="${contest.users[0]!=null}">
        <div class="coj_float_left">
            <a href="#"
               onclick="confirm_delete('<c:url value="deleteusercontest.xhtml?all=true&cid=${contest.cid}"/>')"><i
                    class="fa fa-trash"></i>&nbsp;<spring:message code="link.delall"/>
            </a>
        </div>
        <table class="table table-condensed table-striped">
            <thead>
            <tr>
                <th><spring:message code="fieldhdr.user"/></th>
                <th><spring:message code="fieldhdr.nname"/></th>
                <th><spring:message code="link.group"/></th>
                <th><spring:message code="tablehdr.actions"/></th>
            </tr>
            </thead>
            <c:forEach items="${contest.users}" var="user">

                <tr>
                    <td>
                        <div align="center"><a href="manageuser.xhtml?username=${user.username}">${user.username}</a>
                        </div>
                    </td>
                    <td>
                        <div align="center">${user.nick}</div>
                    </td>

                    <td>
                        <div align="center">${user.group}</div>
                    </td>
                    <td>
                        <div align="center"><a onclick="confirm_delete('<c:url
                                value="deleteusercontest.xhtml?uid=${user.uid}&cid=${contest.cid}"/>')"
                                               href="#"><i data-toggle="tooltip" class="fa fa-trash"
                                                           title="<spring:message code="messages.general.delete"/>">
                        </i></a></div>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<ul id="user-template" class="hide">
    <c:forEach items="${allusers}" var="user">
        <li data-uid="${user.uid}">${user.username}</li>
    </c:forEach>
</ul>
<ul id="bt-template" class="hide">
    <c:forEach items="${btusers}" var="user">
        <li data-uid="${user.uid}">${user.username}</li>
    </c:forEach>
</ul>
<script>
    $("#search-allusers").typeWatch(
            {
                callback: function (value) {
                    $("#allusers").empty();
                    $("#user-template li").filter(":contains(" + value + ")")
                            .each(
                                    function () {
                                        $("#allusers").append(
                                                "<option value="
                                                + $(this).data("uid")
                                                + ">" + $(this).html()
                                                + "</option>");
                                    });
                },
                wait: 250,
                highlight: true,
                captureLength: 0
            });
    $("#search-btusers").typeWatch(
            {
                callback: function (value) {
                    $("#bt_allusers").empty();
                    $("#bt-template li").filter(":contains(" + value + ")")
                            .each(
                                    function () {
                                        $("#bt_allusers").append(
                                                "<option value="
                                                + $(this).data("uid")
                                                + ">" + $(this).html()
                                                + "</option>");
                                    });
                },
                wait: 250,
                highlight: true,
                captureLength: 0
            });
</script>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css"/>
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<script>
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.delete.hdr.entry"/>";
    i18n.message = "<spring:message code="message.confirm.delete.entry"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
</script>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>