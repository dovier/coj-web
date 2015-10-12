
<div class="row">
    <div class="col-xs-12">
        <table class="scoreboradstatus">
            <tbody>
                <tr>
                    <td class="FPS"><spring:message code="tablehdr.firstaccontest" /></td>
            <td class="FS"><spring:message code="tablehdr.firstacproblem" /></td>
            <td class="ACC"><spring:message code="tablehdr.acsubmission" /></td>
            <td class="WA"><spring:message
                code="tablehdr.rejectedsubmission" /></td>
            <td class="PEN"><spring:message
                code="tablehdr.pendingsubmission" /></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<c:if test="${group}">
    <c:if test="${contest.grouped}">
        <div class="row margin-top-05">
            <div class="col-xs-1">
                <a class="pull-right"
                   href='<c:url value="/contest/cscoreboard.xhtml?cid=${contest.cid}&ungroup&refresh=${refresh == true}" />'>
                    <spring:message code="link.ungroup" />
                </a>
            </div>
            <div class="col-xs-11">
                <span class="pull-right">
                    <form method="get" class="form-inline">
                        <select class="form-control" id="selGroup" name="selGroup">
                            <option value="">All</option>
                            <c:forEach items="${cbGroups}" var="igroup">
                                <c:if test="${igroup == selGroup}">
                                    <option selected="selected" value="${igroup}">${igroup}</option>
                                </c:if>
                                <c:if test="${igroup != selGroup}">
                                    <option value="${igroup}">${igroup}</option>
                                </c:if>
                            </c:forEach>
                        </select> <input type="hidden" value="${contest.cid}" name="cid" /> <input
                            type="hidden" value="${refresh == true}" name="refresh" /> <input
                            class="btn btn-primary" type="submit"
                            value="<spring:message code="button.filter"/>" />
                    </form>
                </span>
            </div>
        </div>
    </c:if>
    <div class="row margin-top-05">
        <div class="col-xs-12">
            <c:forEach items="${groups}" var="igroup">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        ${igroup.name}
                        <c:if test="${showSaris}">
                            <span class="badge"><a target="_blank"
                                                   href="<c:url value="/contest/saris.xhtml?cid=${contest.cid}&group=${igroup.name}" />"><i
                                        class="fa fa-flag-checkered"></i></a></span>
                        </c:if>
                        <div class="pull-right">
                            <span class="badge"> <i class="fa fa-check-circle"></i>
                                ${igroup.acs}
                            </span> <span class="badge"> <i class="fa fa-group"></i>
                                ${igroup.users.size()}
                            </span> <span class="badge"> <i class="fa fa-clock-o"></i>
                                ${igroup.time}
                            </span> <span class="badge"> <a data-toggle="collapse"
                                                            href="#group${igroup.id}"><i class="fa fa-chevron-up"></i></a>
                            </span>
                        </div>
                    </div>
                    <div id="group${igroup.id}" class="panel-body collapse in">
                        <table class="ACM" id="myscoretable" onload="toggleGroup(1);">
                            <thead>
                                <tr>
                                    <!--  th class="headrk"><spring:message code="tablehdr.rank" /></th-->
                                    <th width="5%"></th>
                                    <th><spring:message code="tablehdr.contestant" /></th>
                            <th width="3%"><spring:message code="tablehdr.ac" /></th>
                            <th width="5%"><spring:message code="tablehdr.time" /></th>
                            <c:forEach items="${problems}" var="problem">
                                <th width="5%"><a data-toggle="tooltip"
                                                  class="linkheader"
                                                  href="<c:out value="cproblem.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>"
                                                  title="${problem.title}"> <c:if test="${contest.balloon}">
                                            <span style="color:${problem.balloonColor}"> <i
                                                    class="shadow fa fa-circle"> </i></span>
                                        </c:if> ${problem.letter}
                                    </a></th>
                            </c:forEach>
                            </tr>
                            </thead>
                            <c:forEach items="${igroup.users}" var="user">
                                <tr>
                                    <td><c:url value="${user.rank}" /></td>
                                <td class="team"><a data-placement="right"
                                                    data-toggle="tooltip" title="<c:out value="${user.tooltip}"/>"
                                    href="<c:url value="cuseraccount.xhtml?uid=${user.username}&cid=${contest.cid}"/>"><c:out
                                    value="${user.nick}" /></a> <c:choose>
                                    <c:when test="${user.online == true}">
                                        <sup><a  alt="logged" title="<spring:message code="altval.logged"/>"><i class="fa fa-plug"></i></a></sup>
                                    </c:when>
                                    </c:choose> <span class="pull-right"> <img  
                                        src="<c:url value="/images/countries/${user.country}"/>.png"
                                        title="<c:url value="${user.country_desc}"/>"
                                    alt="<c:url value="${user.country}"/>" /> <img class="school" 
                                        src="<c:url value="/images/school/${user.institution}"/>.png"
                                        title="<c:out value="${user.institution_desc}"/>"
                                    alt="<c:out value="${user.institution}"/>" />
                                </span></td>
                                <td><a
                                        href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}&status=ac&username=${user.username}"/>">${user.acc}</a></td>
                                <td>${user.penalty}</td>
                                <c:forEach items="${user.problems}" var="problem">
                                    <td class="${problem.scoreClass}" width="6%"><c:choose>
                                        <c:when test="${problem.accepted == true}">
                                            <label>${problem.acmin}</label>
                                            <br />
                                            <c:choose>
                                                <c:when test="${problem.beforeac != 0}">
                                                    <small>(-${problem.beforeac})</small>
                                                </c:when>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${problem.beforeac != 0}">
                                                    <label>(-${problem.beforeac})</label>
                                                </c:when>
                                            </c:choose>
                                            <c:choose>
                                                <c:when test="${problem.pending != 0}">
                                                    <label>+${problem.pending}</label>
                                                </c:when>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose></td>
                                </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>
<c:if test="${not group}">
    <c:if test="${contest.grouped}">
        <c:if test="${not empty users}">
            <div class="row margin-top-05">
                <div class="col-xs-1">
                    <a class="pull-right"
                       href='<c:url value="/contest/cscoreboard.xhtml?cid=${contest.cid}&group&refresh=${refresh == true}" />'>
                        <spring:message code="link.group" />
                    </a>
                </div>
            </div>
        </c:if>
    </c:if>
    <div class="row">
        <div class="col-xs-12">
            <center>
                <table class="ACM" id="myscoretable" onload="toggleGroup(1);">
                    <thead>
                        <tr>
                            <!--  th class="headrk"><spring:message code="tablehdr.rank" /></th-->
                            <th width="5%"></th>
                            <th><spring:message code="tablehdr.contestant" /></th>
                    <th width="3%"><spring:message code="tablehdr.ac" /></th>
                    <th width="5%"><spring:message code="tablehdr.time" /></th>
                    <c:forEach items="${problems}" var="problem">
                        <th width="5%"><a data-toggle="tooltip" class="linkheader"
                                          href="<c:out value="cproblem.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>"
                                          title="${problem.title}"> <c:if test="${contest.balloon}">
                                    <span style="color:${problem.balloonColor}"> <i
                                            class="shadow fa fa-circle"> </i></span>
                                </c:if> ${problem.letter}
                            </a></th>
                    </c:forEach>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td><center>
                            <c:url value="${user.rank}" />
                        </center></td>
                        <td class="team"><a
                                href="<c:url value="cuseraccount.xhtml?uid=${user.username}&cid=${contest.cid}"/>"
                                data-placement="right" data-toggle="tooltip"
                                title="<c:out value="${user.tooltip}"/>"><c:out
                                    value="${user.nick}" /></a> <c:choose>
                            <c:when test="${user.online == true}">
                                <sup><a  alt="logged" title="<spring:message code="altval.logged"/>"><i class="fa fa-plug"></i></a></sup>
                            </c:when>
                        </c:choose><span class="pull-right"> <img
                                src="<c:url value="/images/countries/${user.country}"/>.png"
                                title="<c:url value="${user.country_desc}"/>"
                            alt="<c:url value="${user.country}"/>" /> <img class="school" 
                                src="<c:url value="/images/school/${user.institution}"/>.png"
                                title="<c:out value="${user.institution_desc}"/>"
                            alt="<c:out value="${user.institution}"/>" />
                        </span></td>
                        <td><a
                                href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}&status=ac&username=${user.username}"/>">${user.acc}</a></td>
                        <td>${user.penalty}</td>
                        <c:forEach items="${user.problems}" var="problem">
                            <td class="${problem.scoreClass}"><c:choose>
                                <c:when test="${problem.accepted == true}">
                                    <label>${problem.acmin}</label>
                                    <br />
                                    <c:choose>
                                        <c:when test="${problem.beforeac != 0}">
                                            <small>(-${problem.beforeac})</small>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${problem.beforeac != 0}">
                                            <label>(-${problem.beforeac})</label>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${problem.pending != 0}">
                                            <label>+${problem.pending}</label>
                                        </c:when>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose></td>
                        </c:forEach>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </center>
        </div>
    </div>

</c:if>