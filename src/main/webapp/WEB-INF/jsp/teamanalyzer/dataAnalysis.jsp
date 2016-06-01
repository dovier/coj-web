<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script type="text/javascript" src="/js/jquery.typewatch.js"></script>

<h2 class="postheader">
    <spring:message code="pagehdr.teamanalyzer"/>
</h2>

<div class="postcontent">
    <div class="row">
        <div class="col-xs-12">
            <form:form method="post" onsubmit="return SeleccionarRangosTeamAnalyzer();"
                       commandName="analysis" cssClass="form-horizontal">
                <div class="form-group">
                    <form:hidden path="id"/>
                    <form:hidden path="coach"/>
                </div>
                <div class="form-group">
                    <label class="control-label col-xs-2"><spring:message
                            code="fieldhdr.name"/></label>

                    <div class="col-xs-8">
                        <form:input cssClass="form-control" path="name" size="30"
                                    maxlength="15"/>
                    </div>
                    <div class="error col-xs-8 col-xs-offset-3">
                        <span class="label label-danger"><form:errors path="name"/></span>
                    </div>
                    <a><i data-toggle="tooltip" class="fa fa-asterisk"
                          title="<spring:message code="mandatory.field"/>"></i></a>
                </div>
                <div class="col-xs-10">
                    <div class="form-group coj_float_rigth">
                        <input placeholder="<spring:message code="fieldhdr.teamanalyzer.filter.users" />"
                        id="search-allusers" cssClass="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-5">
                        <label class="control-label"><spring:message
                                code="teamanalyzer.analysisusers"/></label>
                        <a><i data-toggle="tooltip" class="fa fa-asterisk"
                              title="<spring:message code="mandatory.field"/>"></i></a>
                        <form:select path="usersids"
                                     id="analysis_users" size="14" cssClass="form-control"
                                     cssStyle="border: 1px solid #577A5A;"
                                     multiple="true">
                            <form:options items="${analysis.users}" itemValue="uid"
                                          itemLabel="username"/>
                        </form:select>
                        <div class="error col-xs-8">
                            <span class="label label-danger"><form:errors path="usersids"/></span>
                        </div>
                    </div>
                    <div class="col-xs-1">
                        <button name="boton" type="button" class="btn btn-primary"
                                style="margin-top: 6em"
                                onclick="addremove('all_users', 'analysis_users');">
                            <i class="fa fa-arrow-left"></i>
                        </button>
                        <button name="boton" type="button" class="btn btn-primary"
                                style="margin-top: 3em"
                                onclick="addremove('analysis_users', 'all_users');">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                    <div class="col-xs-5">
                        <label class="control-label"><spring:message
                                code="teamanalyzer.allusers"/></label>

                        <form:select path="" id="all_users" size="14" cssClass="form-control"
                                     cssStyle="border: 1px solid #577A5A;"
                                     multiple="true">
                            <form:options items="${allusers}" itemValue="uid"
                                          itemLabel="username"/>
                        </form:select>
                    </div>

                </div>
                <div class="col-xs-10">
                    <div class="form-group coj_float_rigth">
                        <input placeholder="<spring:message code="fieldhdr.teamanalyzer.filter.contests" />"
                               id="search-allcontests" cssClass="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-5">
                        <label class="control-label"><spring:message
                                code="teamanalyzer.analysiscontests"/></label>
                        <a><i data-toggle="tooltip" class="fa fa-asterisk"
                              title="<spring:message code="mandatory.field"/>"></i></a>
                        <form:select path="contestsids"
                                     id="analysis_contests" size="14" cssClass="form-control"
                                     cssStyle="border: 1px solid #577A5A;"
                                     multiple="true">
                            <form:options items="${analysis.contest}" itemValue="cid"
                                          itemLabel="name"/>
                        </form:select>
                        <div class="error col-xs-8">
                            <span class="label label-danger"><form:errors path="contestsids"/></span>
                        </div>
                    </div>
                    <div class="col-xs-1">
                        <button name="boton" type="button" class="btn btn-primary"
                                style="margin-top: 6em"
                                onclick="addremove('all_contests', 'analysis_contests');">
                            <i class="fa fa-arrow-left"></i>
                        </button>
                        <button name="boton" type="button" class="btn btn-primary"
                                style="margin-top: 3em"
                                onclick="addremove('analysis_contests', 'all_contests');">
                            <i class="fa fa-arrow-right"></i>
                        </button>
                    </div>
                    <div class="col-xs-5">
                        <label class="control-label"><spring:message
                                code="teamanalyzer.allcontest"/></label>
                        <form:select path="" id="all_contests" size="14" cssClass="form-control"
                                     cssStyle="border: 1px solid #577A5A;"
                                     multiple="true">
                            <form:options items="${allcontests}" itemValue="cid"
                                          itemLabel="name"/>
                        </form:select>
                    </div>
                </div>
                <div class="form-group col-xs-3 coj_float_rigth">
                    <c:if test="${analysis.id == 0}">
                        <input type="submit" name="but" class="btn btn-primary"
                               value="<spring:message code="button.add"/>"/>
                    </c:if>
                    <c:if test="${analysis.id != 0}">
                        <input type="submit" name="but" class="btn btn-primary"
                               value="<spring:message code="button.edit"/>"/>
                    </c:if>
                    <a class="btn btn-primary"
                       href="<c:url value="/teamanalyzer/main.xhtml"/>"><spring:message
                            code="button.close"/></a>
                </div>
            </form:form>
        </div>
    </div>
</div>

<ul id="user-template" class="hide">
    <c:forEach items="${allusers}" var="user">
        <li data-uid="${user.uid}">${user.username}</li>
    </c:forEach>
</ul>
<ul id="contest-template" class="hide">
    <c:forEach items="${allcontests}" var="contest">
        <li data-uid="${contest.cid}">${contest.name}</li>
    </c:forEach>
</ul>
<script>
    $("#search-allusers").typeWatch(
            {
                callback: function (value) {
                    $("#all_users").empty();
                    $("#user-template li").filter(":contains(" + value + ")")
                            .each(
                                    function () {
                                        $("#all_users").append(
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
    $("#search-allcontests").typeWatch(
            {
                callback: function (value) {
                    $("#all_contests").empty();
                    $("#contest-template li").filter(":contains(" + value + ")")
                            .each(
                                    function () {
                                        $("#all_contests").append(
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