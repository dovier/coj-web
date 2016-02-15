<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="<c:url value="/css/confirm-message.css"/>"
      type="text/css" media="screen" />

<h2 class="postheader">
    <spring:message code="pagehdr.profileof" />
    <c:out value="${user.nick}" />
</h2>
<div class="postcontent">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <spring:message code="fieldhdr.generalinfo" />
                    <div class="badge pull-right">
                        <a data-toggle="collapse" href="#gInfo"><i
                                class="fa fa-chevron-up"></i></a>
                    </div>
                </div>
                <div id="gInfo" class="panel-body collapse in">

                    <c:if test="${user.team == false}">
                        <div class="col-xs-2">
                            <div class="avatar_preview">

                                    <img class="avatar img-responsive" src="<c:url value="../images/avatars/${user.username}" />" />

                            </div>
                        </div>
                        <div class="col-xs-2 col-xs-offset-1">
                            <spring:message code="fieldhdr.fname" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <c:out value="${user.name}" />
                        </div>
                        <div class="col-xs-2 col-xs-offset-1">
                            <spring:message code="fieldhdr.lname" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <c:out value="${user.lastname}" />
                        </div>
                    </c:if>

                    <c:choose>
                        <c:when test="${user.team == false}">
                            <div class="col-xs-2 col-xs-offset-1">
                                <spring:message code="fieldhdr.username" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <a
                                    href="<c:url value="/mail/composemail.xhtml?usernameto=${user.username}"/>"
                                    title="<spring:message code="titval.sendpm"/>"><c:out
                                        value="${user.username}" /> <i class="fa fa-envelope"></i></a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.username" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">${user.username}</div>
                        </c:otherwise>
                    </c:choose>

                    <c:if test="${user.team == false}">

                        <div class="col-xs-2 col-xs-offset-1">
                            <spring:message code="fieldhdr.gender" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <c:choose>
                                <c:when test="${user.gender eq 1}">
                                    <spring:message code="fieldval.male" />
                                </c:when>
                                <c:otherwise>
                                    <spring:message code="fieldval.female" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:if>
                    <c:if test="${user.showdob}">
                        <div class="col-xs-2 col-xs-offset-1">
                            <spring:message code="fieldhdr.dob" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <fmt:formatDate pattern="yyyy-MM-dd" value="${user.dob1}" />
                        </div>
                    </c:if>

                    <c:choose>
                        <c:when test="${user.team == false}">
                            <div class="col-xs-2 col-xs-offset-1">
                                <spring:message code="fieldhdr.country" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <a href="<c:url value="/24h/userscountryrank.xhtml?country_id=${user.country_id}"/>">
                                    ${user.country_desc} </a>
                            </div>

                            <div class="col-xs-2 col-xs-offset-1">
                                <spring:message code="fieldhdr.institution" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <a href="<c:url value="/24h/usersinstitutionrank.xhtml?inst_id=${user.institution_id}"/>">
                                    ${user.institution_desc} </a>
                            </div>
                            <div class="col-xs-12"></div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.country" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <a href="<c:url value="/24h/userscountryrank.xhtml?country_id=${user.country_id}"/>">
                                    ${user.country_desc} </a>

                                <div class="col-xs-5">
                                    <spring:message code="fieldhdr.institution" />
                                </div>
                                <div class="col-xs-6 col-xs-offset-1">
                                    <a href="<c:url value="/24h/usersinstitutionrank.xhtml?inst_id=${user.institution_id}"/>">
                                        ${user.institution_desc} </a>
                                </div>
                                <div class="col-xs-12"></div>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${user.team == false}">

                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.notifcontest" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:if test="${user.contestNotifications}">
                                    <i class="fa fa-check-square-o"></i>
                                </c:if>
                                <c:if test="${!user.contestNotifications}">
                                    <i class="fa fa-square-o"></i>
                                </c:if>
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.notifsubmit" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:if test="${user.submissionNotifications}">
                                    <i class="fa fa-check-square-o"></i>
                                </c:if>
                                <c:if test="${!user.submissionNotifications}">
                                    <i class="fa fa-square-o"></i>
                                </c:if>
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.notifproblem" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:if test="${user.problemNotifications}">
                                    <i class="fa fa-check-square-o"></i>
                                </c:if>
                                <c:if test="${!user.problemNotifications}">
                                    <i class="fa fa-square-o"></i>
                                </c:if>
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.notifnewprivatemessage" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:if test="${user.newprivatemessageNotifications}">
                                    <i class="fa fa-check-square-o"></i>
                                </c:if>
                                <c:if test="${!user.newprivatemessageNotifications}">
                                    <i class="fa fa-square-o"></i>
                                </c:if>
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.notifwboard" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:if test="${user.wboardNotifications}">
                                    <i class="fa fa-check-square-o"></i>
                                </c:if>
                                <c:if test="${!user.wboardNotifications}">
                                    <i class="fa fa-square-o"></i>
                                </c:if>
                            </div>
                        </c:if>

                        <div class="col-xs-5">
                            <spring:message code="fieldhdr.defaultguilang" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <font>${user.glanguage}</font>
                        </div>

                        <div class="col-xs-5">
                            <spring:message code="fieldhdr.defaultproglang" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <font>${user.planguage} </font>
                        </div>
                        <c:if test="${user.team == false}">
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.status" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <label class="label bg${user.status}"><c:out
                                        value="${user.status}" /></label>
                            </div>
                        </c:if>
                        <div class="col-xs-5">
                            <spring:message code="fieldhdr.regdate" />
                        </div>
                        <div class="col-xs-6 col-xs-offset-1">
                            <c:out value="${user.rgdate}" />
                        </div>


                        <c:if test="${user.team == true}">
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.tcoach" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.coach}" />
                                &nbsp;
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.tmember" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.user_1}" />
                                &nbsp;
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.tmember" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.user_2}" />
                                &nbsp;
                            </div>
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.tmember" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.user_3}" />
                                &nbsp;
                            </div>
                        </c:if>

                        <c:if test="${user.team == false}">
                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.lastsubdate" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.last_submission}" />
                            </div>

                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.lastacdate" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.last_accepted}" />
                            </div>

                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.score" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <fmt:formatNumber type="number" value="${user.score}"
                                                  minFractionDigits="2" maxFractionDigits="2" />
                            </div>

                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.rankusers" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.ranking}" />
                                <spring:message code="fieldval.of" />
                                <a href="<c:url value="/24h/usersrank.xhtml"/>"><c:out
                                        value="${user.tot_ranking}" /></a>
                            </div>

                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.rankinstitution" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.rankingbyinstitution}" />
                                <spring:message code="fieldval.of" />
                                <a
                                    href="<c:url value="/24h/usersinstitutionrank.xhtml?inst_id=${user.institution_id}"/>"><c:out
                                        value="${user.tot_rankingbyinstitution}" /></a>
                            </div>

                            <div class="col-xs-5">
                                <spring:message code="fieldhdr.rankcountry" />
                            </div>
                            <div class="col-xs-6 col-xs-offset-1">
                                <c:out value="${user.rankingbycountry}" />
                                <spring:message code="fieldval.of" />
                                <a
                                    href="<c:url value="/24h/userscountryrank.xhtml?country_id=${user.country_id}"/>"><c:out
                                        value="${user.tot_rankingbycountry}" /></a>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <c:if test="${user.team == false}">
            <div class="row">
                <div class="col-xs-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <spring:message code="fieldhdr.achievements" />
                            <div class="badge pull-right">
                                <a data-toggle="collapse" href="#achievements"><i
                                        class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div id="achievements" class="panel-body collapse">
                            <div class="row">
                                <div class="col-xs-2 pull-right">
                                    <a class="pull-right"
                                       href="<c:url value="/general/achievements.xhtml?username=${user.username}"/>"><spring:message
                                            code="link.achievements" />&nbsp;<i class="fa fa-list"></i></a>
                                </div>
                            </div>
                            <c:forEach items="${achievements}" var="achievement">
                                <c:if test="${achievement.level > 0}">
                                    <div class="col-xs-1">
                                        <div class="shadow white" data-toggle="tooltip"
                                             title="${achievement.condition}">
                                            <img class="achievement-image"
                                                 src='<c:out value="/images/achievements/${achievement.id}.${achievement.level}.png" />' />
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <spring:message code="fieldhdr.user.entries" />
                            <div class="pull-right">
                                <div class="badge">
                                    <a data-toggle="collapse" href="#entries"><i
                                            class="fa fa-chevron-down"></i></a>
                                </div>
                            </div>
                        </div>
                        <div id="entries" class="panel-body collapse">
                            <div class="row">
                                <div class="col-xs-2">
                                    <a data-toggle="tooltip"
                                       title="<spring:message code='count.followers' />"
                                       href="<c:url value="/user/${user.username}/following.xhtml"/>">
                                        <i class="fa fa-users red"></i>&nbsp;${followers}
                                    </a>
                                </div>
                                <div class="col-xs-8">
                                    <spring:message code="last.entry" />
                                </div>
                                <div class="col-xs-2 pull-right">
                                    <a class="pull-right"
                                       href="<c:url value="/user/${user.username}/listentries.xhtml"/>"><spring:message
                                            code="link.list.entries" />&nbsp;<i class="fa fa-list"></i></a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-2">
                                    <a data-toggle="tooltip"
                                       title="<spring:message code="count.following" />"
                                       href="<c:url value="/user/${user.username}/following.xhtml"/>"><i
                                            class="fa fa-users green"></i>&nbsp;${following} </a>
                                </div>
                                <div class="col-xs-8">
                                    <div>
                                        <div>
                                            <a
                                                href="<c:url value="/user/useraccount.xhtml?username=${lastEntry.username}"/>">${lastEntry.username}</a>
                                        </div>
                                        <div class="text-justify">${lastEntry.text}</div>
                                        <div class="pull-left">
                                            <fmt:formatDate value="${lastEntry.date}"
                                                            pattern="yyyy-MM-dd HH:mm:ss" />
                                        </div>
                                        <div class="pull-right">
                                            <c:if test="${lastEntry.rate gt 0}">
                                                <b class="text-success" id="rating${lastEntry.id}">${lastEntry.rate}</b>
                                            </c:if>
                                            <c:if test="${lastEntry.rate lt 0}">
                                                <b class="text-danger" id="rating${lastEntry.id}">${lastEntry.rate}</b>
                                            </c:if>
                                            <c:if test="${lastEntry.rate eq 0}">
                                                <b id="rating${lastEntry.id}">${lastEntry.rate}</b>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-2">
                                    <c:if test="${isfollowing ne null}">
                                        <c:if test="${!isfollowing}">

                                            <a class="pull-right"
                                               href="<c:url value="/user/${user.username}/follow.xhtml"/>"><i
                                                    class="fa fa-plus-square"></i>&nbsp;<spring:message
                                                    code="link.follow" /></a>
                                            </c:if>
                                            <c:if test="${isfollowing}">
                                            <a class="pull-right"
                                               href="<c:url value="/user/${user.username}/unfollow.xhtml"/>"><i
                                                    class="fa fa-minus-square"></i>&nbsp;<spring:message
                                                    code="link.unfollow" /></a>
                                            </c:if>
                                        </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-xs-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <spring:message code="fieldhdr.statsjudgments" />
                            <div class="pull-right">
                                <div class="badge">
                                    <a data-toggle="collapse" href="#stats"><i
                                            class="fa fa-chevron-up"></i></a>
                                </div>
                            </div>
                        </div>
                        <div id="stats" class="panel-body collapse in">
                            <div class="row row-centered no-gutters">
                                <div class="col-xs-12">
                                    <c:if test="${userclassif.count > 0}">
                                        <label> <spring:message code="user.percent.stats" />
                                        </label>
                                        <canvas id="top-chart" width="500" height="500"></canvas>
                                        </c:if>
                                </div>

                                <c:if test="${userclassif.count > 0}">
                                    <div class="col-centered col-xs-11 no-gutters">
                                        <c:forEach var="loop" begin="0" end="${userclassif.count-1}">
                                            <div class="col-xs-4">
                                                <div class="col-padded">
                                                    <center>
                                                        <small><label id="classif-label-${loop}"
                                                                      class="text-sm text-muted"></label></small>                                                                      
                                                    </center>
                                                    <div>
                                                        <canvas id="classif-chart-${loop}"></canvas>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </c:if>
                                <div class="col-xs-12">
                                    <canvas id="timeline-chart"></canvas>
                                </div>
                            </div>
                            <div class="row row-centered">
                                <div class="col-xs-6">
                                    <canvas id="chart"></canvas>
                                </div>
                                <div class="col-xs-6">
                                    <table class="table table-bordered table-condensed">
                                        <thead>
                                            <tr>
                                                <th><spring:message code="tablehdr.ac" /></th>
                                                <th><spring:message code="tablehdr.ce" /></th>
                                                <th><spring:message code="tablehdr.mle" /></th>
                                                <th><spring:message code="tablehdr.ole" /></th>
                                                <th><spring:message code="tablehdr.pe" /></th>
                                                <th><spring:message code="tablehdr.rte" /></th>
                                                <th><spring:message code="tablehdr.tle" /></th>
                                                <th><spring:message code="tablehdr.wa" /></th>
                                                <th><spring:message code="tablehdr.total" /></th>
                                            </tr>
                                        </thead>
                                        <tr>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=ac"><c:out
                                                        value="${user.acc}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=ce"><c:out
                                                        value="${user.ce}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=mle"><c:out
                                                        value="${user.mle}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=ole"><c:out
                                                        value="${user.ole}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=pe"><c:out
                                                        value="${user.pe}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=rte"><c:out
                                                        value="${user.rte}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=tle"><c:out
                                                        value="${user.tle}" /></a>
                                            <td><a
                                                    href="/24h/status.xhtml?username=${user.username}&status=wa"><c:out
                                                        value="${user.wa}" /></a>
                                            <td><a href="/24h/status.xhtml?username=${user.username}"><c:out
                                                        value="${user.total}" /></a></td>
                                        </tr>
                                    </table>
                                    <authz:authorize ifNotGranted="ROLE_ANONYMOUS">
                                        <c:if test="${currentUsername}">
                                            <div class="col-xs-12">
                                                <a href="/24h/downloadsourcezip.xhtml?status=1">
                                                    <div class="col-xs-offset-1 col-xs-5 panel">
                                                        <div class="col-xs-12 label label-success">
                                                            <spring:message code="tablehdr.ac" />
                                                        </div>
                                                        <div class="label-success">
                                                            <i class="fa fa-download white fa-3x"></i>
                                                        </div>
                                                    </div>
                                                </a> <a href="/24h/downloadsourcezip.xhtml">
                                                    <div class="col-xs-offset-1 col-xs-5 panel">
                                                        <div class="col-xs-12 label label-primary">
                                                            <spring:message code="tablehdr.total" />
                                                        </div>
                                                        <div class="label-primary">
                                                            <i class="fa fa-download white fa-3x"></i>
                                                        </div>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </c:if>
                                </authz:authorize>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${solved ne null}">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="panel panel-primary">

                            <div class="panel-heading">
                                <spring:message code="fieldhdr.solvedprob" />
                                <span class="badge">${user.solved}</span>
                                <div class="badge pull-right">
                                    <a data-toggle="collapse" href="#probsACC"><i
                                            class="fa fa-chevron-down"></i></a>
                                </div>
                            </div>
                            <div id="probsACC" class="panel-body collapse">
                                <c:forEach items="${solved}" var="problem">
                                    <div class="col-xs-1 margin-top-05">
                                        <a
                                            href="/24h/status.xhtml?username=<c:out value="${user.username}"/>&pid=${problem.pid}"
                                            title="${problem.title}"><span class="badge alert-success">${problem.pid}</span></a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${unsolved ne null}">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="panel panel-primary">

                            <div class="panel-heading">
                                <spring:message code="fieldhdr.triedunsolvedprob" />
                                <span class="badge">${user.unsolved}</span>
                                <div class="badge pull-right">
                                    <a data-toggle="collapse" href="#probsWA"><i
                                            class="fa fa-chevron-down"></i></a>
                                </div>
                            </div>
                            <div id="probsWA" class="panel-body collapse">
                                <c:forEach items="${unsolved}" var="problem">
                                    <div class="col-xs-1 margin-top-05">
                                        <a
                                            href="/24h/status.xhtml?username=${user.username}&pid=${problem.pid}"
                                            title="${problem.title}"><span class="badge alert-danger">${problem.pid}</span></a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
        </c:if>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>
<script>
    $(function() {
        $("[data-toggle='tooltip']").tooltip();
    });

    $('img.avatar').error(function() {
        $(this).attr('src', '<c:url value="/images/default_avatar.png"/>');
    });

    $(function() {
        $('.fa-chevron-up').click(function() {
            $(this).toggleClass('fa-chevron-up');
            $(this).toggleClass('fa-chevron-down');
        });
        $('.fa-chevron-down').click(function() {
            $(this).toggleClass('fa-chevron-down');
            $(this).toggleClass('fa-chevron-up');
        });

        $("[data-toggle='tooltip']").tooltip();
    });

    $(document).ready(function() {
        callback();
        topCallback();
        classifCallback();
        timelineCallback();
    });

    function callback() {
        $("#loading").show();
        var nat = $('#username').val();
        var data = {
            labels: ["AC",
                "CE",
                "MLE",
                "OLE",
                "PE",
                "RTE",
                "TLE",
                "WA"
            ],
            datasets: [
                {
                    label: "Submits",
                    fillColor: "#4c83c3",
                    data: [${user.acc}, ${user.ce}, ${user.mle},
    ${user.ole}, ${user.pe}, ${user.rte},
    ${user.tle}, ${user.wa}]
                }
            ]
        };
        var chart = new Chart($("#chart").get(0).getContext("2d")).Bar(data, {scaleShowVerticalLines: false, });
    }
    ;

    function topCallback() {
        var data = {
            labels: ${userclassif.labels},
            datasets: [
                {
                    fillColor: "rgba(0,150,0,0.8)",
                    strokeColor: "rgba(0,150,0,0.8)",
                    pointColor: "rgba(0,150,0,0.8)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,150,0,0.8)",
                    data: ${userclassif.percentTop}
                },
                {
                    fillColor: "rgba(151,187,205,0.4)",
                    strokeColor: "rgba(151,187,205,0.4)",
                    pointColor: "rgba(151,187,205,0.4)",
                    pointStrokeColor: "#fff",
                    data: ${userclassif.percentTotal}
                }
            ]
        };
        var topChart = new Chart($("#top-chart").get(0).getContext("2d")).Radar(data, {pointDot: false, scaleOverride: false, scaleIntegersOnly: true, scaleSteps: 1, scaleStepWidth: 1, scaleStartValue: 0});
    }
    ;


    function classifCallback() {

        var labels = ${userclassif.labels};
        for (var i = 0; i < ${userclassif.count}; i++) {
            var data = {
                labels: ["1", "2", "3", "4", "5"],
                datasets: [
                    {
                        label: labels[i],
                        fillColor: "rgba(0,150,0,0.6)",
                        data: ${userclassif.classifications}[i]
                    }
                ]
            };
            $("#classif-label-" + i).html(labels[i]);
            var chart = new Chart($("#classif-chart-" + i).get(0).getContext("2d")).Bar(data, {scaleShowVerticalLines: false, animation: false, responsive: true});
        }
    }
    ;

    function timelineCallback() {

        var data = {
            labels: ${userclassif.dates},
            datasets: [
                {
                    label: 'AC',
                    fillColor: "rgba(0,150,0,0.4)",
                    strokeColor: "rgba(0,150,0,1)",
                    pointColor: "rgba(0,150,0,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(0,150,0,1)",
                    data: ${userclassif.acStatus}
                },
                {
                    label: 'Error',
                    fillColor: "rgba(150,0,0,0.4)",
                    strokeColor: "rgba(150,0,0,1)",
                    pointColor: "rgba(150,0,0,1)",
                    pointStrokeColor: "#fff",
                    pointHighlightFill: "#fff",
                    pointHighlightStroke: "rgba(200,0,0,1)",
                    data: ${userclassif.errorStatus}
                }
            ]
        };

        Chart.types.Line.extend({
            name: "AltLine",
            initialize: function(data) {
                Chart.types.Line.prototype.initialize.apply(this, arguments);
                this.scale.draw = function() {
                    if (this.display && true) {
                        this.endPoint = this.height - 5;
                    }
                    Chart.Scale.prototype.draw.apply(this, arguments);
                };
            }
        });

        var chart = new Chart($("#timeline-chart").get(0).getContext("2d")).AltLine(data, {scaleShowVerticalLines: true, pointDotRadius: 2, scaleShowLabels: true, pointHitDetectionRadius: 5, animation: false, responsive: true});
        chart.draw();
    }
    ;

</script>