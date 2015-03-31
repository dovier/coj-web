<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${contest.running}">
        <meta content="60" http-equiv="refresh">
    </c:when>
    <c:when test="${contest.frozen}">
        <meta content="60" http-equiv="refresh">
    </c:when>
</c:choose>
<div class="post-inner article">
										<c:if test="${contest != null and contest.initdate != null and (contest.past or contest.running or contest.coming)}">
											<div class="pull-right">
												<table class="contestlanguages" style="align:right">
													<tbody>
														<tr>
															<td><b><spring:message code="fieldhdr.status" />: </b></td>
															<td><c:choose>
																	<c:when test="${contest.running == true}">
																		<span class="label label-success"><spring:message code="fieldhdr.running" /></span>
																	</c:when>
																	<c:when test="${contest.coming == true}">
																		<span class="label label-danger"><spring:message code="fieldhdr.coming" /></span>
																	</c:when>
																	<c:when test="${contest.past == true}">
																		<spring:message code="fieldhdr.past" />
																	</c:when>
																</c:choose></td>
															<c:if test="${contest.running}">

																<td><b><spring:message code="fieldhdr.elapsed" />: </b></td>
																<td>${contest.elapsed}</td>
																<td><b><spring:message code="fieldhdr.remaining" />: </b></td>
																<td>${contest.remaining}</td>

															</c:if>
															<c:if test="${contest.past}">

																<td><b><spring:message code="fieldhdr.start" />:</b></td>
																<td><fmt:formatDate value="${contest.initdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
																<td><b><spring:message code="fieldhdr.end" />: </b></td>
																<td><fmt:formatDate value="${contest.enddate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
															</c:if>
														</tr>
														<c:if test="${contest.running == true && (contest.full_frozen == true || contest.frozen == true)}">
															<tr>
																<td colspan="6" style="text-align: right"><c:choose>
																		<c:when test="${contest.full_frozen == true}">
																			<span class="label label-danger"><i class="fa fa-warning"></i>&nbsp;<spring:message code="text.deadtime" /></span>
																		</c:when>
																		<c:otherwise>
																			<span class="label label-primary"><i class="fa fa-info-circle"></i>&nbsp;<spring:message code="text.frozentime" /></span>
																		</c:otherwise>
																	</c:choose></td>
															</tr>
														</c:if>

													</tbody>
												</table>
											</div>
											<br />
										</c:if>
										<div class="clearfix"></div>
<br/>

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>" >${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.standings"/>
</h2>

<c:if test="${contest.running == true}">
    <div style="text-align: center;margin-top: -20px;"><span class="label label-danger"><spring:message code="text.vcstandings.3"/></span></div>
</c:if>

<div class="postcontent">
    <table class="navigating">
        <tbody>
        <tr><td>
            <a href="<c:url value="/contest/contestview.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-eye"></i>&nbsp;<spring:message code="link.overview"/>
            </a>
        </td>
        <td>
            <a href="<c:url value="/contest/myclarifications.xhtml?cid=${contest.cid}"/>"><img src="<c:url value="/images/clarifications.png"/>" alt=""/> <spring:message code="link.clarifications"/>
                <c:if test="${totalmsg != 0}">(${totalmsg})</c:if>
                <c:if test="${unread != 0}">
                    <span class="label label-danger">(${unread})</span>
                </c:if>
                <c:if test="${isjudge == true and unreply ne 0}">
                    <span class="label label-info">(${unreply})</span>
                </c:if>
            </a>

        </td>
        <td>

            <a href="<c:url value="/contest/cproblems.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-list"></i>&nbsp;<spring:message code="link.problems"/>
            </a>

        </td>
        <authz:authorize ifAnyGranted="ROLE_USER,ROLE_TEAM">
            <c:choose>
                <c:when test="${contest.running == true}">
                    <td>

                        <a href="<c:url value="/contest/csubmit.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-file-code-o"></i>&nbsp;<spring:message code="link.submit"/></a>

                    </td>
                </c:when>
            </c:choose>
        </authz:authorize>
        <td><a href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-legal"></i>&nbsp;<spring:message code="link.judgments"/></a></td>        
        <td><a href="<c:url value="/contest/cstatistics.xhtml?cid=${contest.cid}" />"><i
										class="fa fa-bar-chart"></i>&nbsp;<spring:message code="link.statistics"/></a></td>
        </tr></tbody>
    </table>
    <c:choose>        
        <c:when test="${contest.repointing == true}">
            <div class="label label-info"><i class="fa fa-info-circle"></i>
                The contest its been repointed please wait a minute and try again
            </div>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${contest.show_status == true}">
                    <c:choose>
                        <c:when test="${contest.style eq 1}">                                                        
                            <table class="scoreboradstatus">
                                <tbody>
                                    <tr>
                                        <td class="FPS"><spring:message code="tablehdr.firstaccontest"/></td>
                                        <td class="FS"><spring:message code="tablehdr.firstacproblem"/></td>
                                        <td class="ACC"><spring:message code="tablehdr.acsubmission"/></td>
                                        <td class="WA"><spring:message code="tablehdr.rejectedsubmission"/></td>
                                        <td class="PEN"><spring:message code="tablehdr.pendingsubmission"/></td>
                                    </tr>
                                </tbody>
                            </table>                                                                                            
                            <c:choose>
                                <c:when test="${group == true}">                                    
                                    <c:forEach items="${groups}" var="igroup">
                                        <div style="clear: both;float: right"><a href="<c:url value="/contest/cscoreboard.xhtml?cid=${contest.cid}" />"> <spring:message code="link.ungroup" /></a></div>
                                        <h2>
                                            <center>${igroup.name}</center>
                                        </h2>                                        
                                        <table class="ACM">
                                            <thead>
                                            <th class="headrk">
                                                <spring:message code="tablehdr.rank" />
                                            </th>
                                            <th colspan="3" class="team" style="text-align: center;">
                                                <spring:message code="tablehdr.contestant" />
                                            </th>
                                            <th class="headacc">
                                                <spring:message code="tablehdr.ac" />
                                            </th>
                                            <th class="headtime">
                                                <spring:message code="tablehdr.time" />
                                            </th>
                                            <c:forEach items="${problems}" var="problem">
                                                <th width="6%">
                                                    <a class="linkheader" href="<c:out value="cproblem.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>" title="${problem.title}">${problem.letter}</a>
                                                </th>
                                            </c:forEach>
                                            </thead>
                                            <c:forEach items="${igroup.users}" var="user">
                                                <tr>
                                                    <td>
                                                                <c:url value="${user.rank}"/>
                                                    </td>
                                                    <td class="team">
                                                        <a title="${user.username}" href="<c:url value="cuseraccount.xhtml?uid=${user.username}&cid=${contest.cid}"/>"><c:out value="${user.nick}"/></a>
                                                        <c:choose>
                                                            <c:when test="${user.online == true}">
                                                                <sup><a  alt="logged" title="<spring:message code="altval.logged"/>"><i class="fa fa-plug"></i></a></sup>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td class="country"><img src="<c:url value="/images/countries/${user.country}"/>.png" title="<c:url value="${user.country_desc}"/>" alt="<c:url value="${user.country}"/>"/></td>
                                                    <td class="school"><img src="<c:url value="/images/school/${user.institution}"/>.png" title="<c:url value="${user.institution_desc}"/>" alt="<c:url value="${user.institution}"/>"/></td>
                                                    <td>
                                                        <a href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}&status=ac&username=${user.username}"/>">${user.acc}</a>
                                                    </td>
                                                    <td>${user.penalty}</td>
                                                    <c:forEach items="${user.problems}" var="problem">
                                                        <td class="${problem.scoreClass}" width="6%">
                                                            <c:choose>
                                                                <c:when test="${problem.accepted == true}">
                                                                    <label title="${problem.ac_time}">${problem.acmin}</label>
                                                                    <br/>
                                                                    <c:choose>
                                                                        <c:when test="${problem.beforeac != 0}">
                                                                            <small>(-${problem.beforeac})</small>
                                                                        </c:when>
                                                                    </c:choose>

                                                                    <c:choose>
                                                                        <c:when test="${problem.afterac != 0}">
                                                                            <small>(+${problem.afterac})</small>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:choose>
                                                                        <c:when test="${problem.beforeac != 0}">
                                                                            (-${problem.beforeac})
                                                                        </c:when>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${problem.pending != 0}">
                                                                            (+${problem.pending})
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </c:forEach>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>                                    
                                    <c:if test="${not empty users}">
                                        <div style="clear: both;float: right">  <a href="<c:url value="/contest/cscoreboard.xhtml?cid=${contest.cid}&groupby" />"> <spring:message code="link.group" /></a> </div>
                                    </c:if>
                                    <table class="ACM" border="1px">
                                        <thead>
                                        <th class="headrk">
                                            <spring:message code="tablehdr.rank" />
                                        </th>
                                        <th colspan="3" class="team" style="text-align: center;">
                                            <spring:message code="tablehdr.contestant" />
                                        </th>
                                        <th class="headacc">
                                            <spring:message code="tablehdr.ac" />
                                        </th>
                                        <th class="headtime">
                                            <spring:message code="tablehdr.time" />
                                        </th>
                                        <c:forEach items="${problems}" var="problem">
                                            <th width="6%">
                                                <a class="linkheader" href="<c:out value="cproblem.xhtml?pid=${problem.pid}&cid=${contest.cid}"/>" title="${problem.title}">${problem.letter}</a>
                                            </th>
                                        </c:forEach>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${users}" var="user">
                                                <tr>
                                                    <td>
                                                                <c:url value="${user.rank}"/>
                                                    </td>
                                                    <td class="team">
                                                        <a title="${user.username}" href="<c:url value="cuseraccount.xhtml?uid=${user.username}&cid=${contest.cid}"/>"><c:out value="${user.nick}"/></a>
                                                        <c:choose>
                                                            <c:when test="${user.online == true}">
                                                                <sup><a  alt="logged" title="<spring:message code="altval.logged"/>"><i class="fa fa-plug"></i></a></sup>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td class="country"><img src="<c:url value="/images/countries/${user.country}"/>.png" title="<c:url value="${user.country_desc}"/>" alt="<c:url value="${user.country}"/>"/></td>
                                                    <td class="school"><img src="<c:url value="/images/school/${user.institution}"/>.png" title="<c:url value="${user.institution_desc}"/>" alt="<c:url value="${user.institution}"/>"/></td>
                                                    <td>
                                                        <a href="<c:url value="/contest/cstatus.xhtml?cid=${contest.cid}&status=ac&username=${user.username}"/>">${user.acc}</a>
                                                        </td>
                                                    <td>${user.penalty}</td>
                                                    <c:forEach items="${user.problems}" var="problem">
                                                        <td class="${problem.scoreClass}">
                                                            <c:choose>
                                                                <c:when test="${problem.accepted == true}">
                                                                    <label title="${problem.ac_time}">${problem.acmin}</label>
                                                                    <br/>
                                                                    <c:choose>
                                                                        <c:when test="${problem.beforeac != 0}">
                                                                            <small>(-${problem.beforeac})</small>
                                                                        </c:when>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${problem.afterac != 0}">
                                                                            <small>(+${problem.afterac})</small>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:choose>
                                                                        <c:when test="${problem.beforeac != 0}">
                                                                            (-${problem.beforeac})                                                                            
                                                                        </c:when>
                                                                    </c:choose>
                                                                    <c:choose>
                                                                        <c:when test="${problem.pending != 0}">
                                                                            (+${problem.pending})
                                                                        </c:when>
                                                                    </c:choose>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </c:forEach>
                                                </tr>
                                            </c:forEach>                                            
                                        </tbody>
                                    </table>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:when test="${contest.style eq 3}">

                            <div id="display-table-container" data-reload-url="/tables/globalcontestscoreboard.xhtml"></div> 

                        </c:when>                        
                        <c:otherwise>
                            STYLE SCOREBOARD NOT IMPLEMENTED YET ${contest.style}
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <div class="error"><spring:message code="contest.error.disable"/></div>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>
<script>
	$(document).ready(displayTableReload(" "));
</script>