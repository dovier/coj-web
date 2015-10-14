<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader" style="clear: both">
    <a class="linkheader"
       href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>"
       title="Go to Contest">${contest.name}</a> <br />
    <spring:message code="pagehdr.statistics" />
</h2>
<div class="postcontent">
    <c:if
        test="${(contest.past && contest.blocked) || (contest.running && (contest.full_frozen || contest.frozen))}">
        <center><c:choose>
                <c:when test="${contest.full_frozen == true}">
                    <span class="label label-danger"><i
                            class="fa fa-warning"></i>&nbsp;<spring:message
                            code="text.deadtime" /></span>
                    </c:when>
                    <c:otherwise>
                    <span class="label label-primary"><i
                            class="fa fa-info-circle"></i>&nbsp;<spring:message
                            code="text.frozentime" /></span>
                    </c:otherwise>
                </c:choose></center>
        </c:if>
        <c:if
            test="${(contest.past && not contest.blocked) || (contest.running && not contest.full_frozen && not contest.frozen)}">
            <c:if test="${showStats}">
            <table class="table table-condensed table-striped">
                <thead>
                    <tr>
                        <th><spring:message code="tablehdr.lang" /></th>
                        <th><spring:message code="tablehdr.ac" /></th>
                        <th><spring:message code="tablehdr.ce" /></th>
                        <th><spring:message code="tablehdr.sle" /></th>
                        <th><spring:message code="tablehdr.ivf" /></th>
                        <th><spring:message code="tablehdr.mle" /></th>
                        <th><spring:message code="tablehdr.ole" /></th>
                        <th><spring:message code="tablehdr.pe" /></th>
                        <th><spring:message code="tablehdr.rte" /></th>
                        <th><spring:message code="tablehdr.tle" /></th>
                        <th><spring:message code="tablehdr.wa" /></th>
                        <th><spring:message code="tablehdr.total" /></th>
                    </tr>
                </thead>
                <c:forEach items="${statistics}" var="stats">
                    <tr>
                        <td><c:out value="${stats.language}" /></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ac&planguage=${stats.key}"/>"><c:out
                                    value="${stats.acc}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ce&planguage=${stats.key}"/>"><c:out
                                    value="${stats.ce}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=sle&planguage=${stats.key}"/>"><c:out
                                    value="${stats.fle}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ivf&planguage=${stats.key}"/>"><c:out
                                    value="${stats.ivf}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=mle&planguage=${stats.key}"/>"><c:out
                                    value="${stats.mle}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ole&planguage=${stats.key}"/>"><c:out
                                    value="${stats.ole}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=pe&planguage=${stats.key}"/>"><c:out
                                    value="${stats.pe}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=rte&planguage=${stats.key}"/>"><c:out
                                    value="${stats.rte}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=tle&planguage=${stats.key}"/>"><c:out
                                    value="${stats.tle}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=wa&planguage=${stats.key}"/>"><c:out
                                    value="${stats.wa}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&planguage=${stats.key}"/>"><c:out
                                    value="${stats.total}" /></a></td>
                    </tr>
                </c:forEach>
                <thead>
                    <tr>
                        <th><spring:message code="tablehdr.total" /></th>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ac"/>"><c:out
                                    value="${stat.acc}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ce"/>"><c:out
                                    value="${stat.ce}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=sle"/>"><c:out
                                    value="${stat.fle}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ivf"/>"><c:out
                                    value="${stat.ivf}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=mle"/>"><c:out
                                    value="${stat.mle}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=ole"/>"><c:out
                                    value="${stat.ole}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=pe"/>"><c:out
                                    value="${stat.pe}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=rte"/>"><c:out
                                    value="${stat.rte}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=tle"/>"><c:out
                                    value="${stat.tle}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}&status=wa"/>"><c:out
                                    value="${stat.wa}" /></a></td>
                        <td><a
                                href="<c:url value="cstatus.xhtml?cid=${contest.cid}"/>"><c:out
                                    value="${stat.total}" /></a></td>
                    </tr>
                </thead>
            </table>
            <br />



            <div class="row">
                <div class="col-xs-5">
                    <select id="username" class="form-control"
                            onchange="javascript:callback();">
                        <option selected="selected" value="">All Users</option>
                        <c:forEach items="${contest.users}" var="user">
                            <option value="${user.username}">${user.nick}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-5">
                    <canvas id="chart" style="width: 756px; height: 475px">
                    </canvas>
                </div>

                <div class="col-xs-7"><label><spring:message code="contest.cant.users" /> </label> ${cuser}</div>
                <div class="col-xs-7"><label><spring:message code="contest.cant.country" /> </label> ${ccountry}</div>
                <div class="col-xs-7"><label><spring:message code="contest.cant.inst" /> </label> ${cinst}</div>



            </div>
        </c:if>
    </c:if>
</div>

<script type="text/javascript" src="<c:url value="/js/Chart.min.js"/>"></script>

<script id="scriptInit" type="text/javascript">
                                var chart = null;
                                $(document).ready(function() {
                                    callback();
                                });

                                function callback() {
                                    $("#loading").show();
                                    var nat = $('#username').val();

                                    $.ajax({
                                        url: "/contest/contestsubmits.json",
                                        type: 'GET',
                                        data: {
                                            "cid": ${contest.cid},
                                            "username": nat,
                                        },
                                        dataType: 'json',
                                        success: function(result) {
                                            if (chart == null) {
                                                var data = {
                                                    labels: result[0],
                                                    datasets: [
                                                        {
                                                            label: "Submits",
                                                            fillColor: "#4c83c3",
                                                            data: result[1]
                                                        }
                                                    ]
                                                };


                                                chart = new Chart($("#chart").get(0).getContext("2d")).Bar(data, {animation: false, barShowStroke: false})
                                            }
                                            else {
                                                while (chart.datasets[0].bars.length > 0) {
                                                    chart.removeData();
                                                    chart.update();
                                                }
                                                chart.addData([result[1][0]], result[0][0]);
                                                chart.datasets[0].bars[0].value = result[1][0];
                                                for (var i = 0; i < result[0].length; i++) {
                                                    chart.addData([result[1][i]], result[0][i]);
                                                    chart.datasets[0].bars[i + 1].value = result[1][i];
                                                    chart.update();
                                                }
                                                chart.removeData();
                                                chart.update();
                                            }
                                        }


                                    });
                                }
</script>
