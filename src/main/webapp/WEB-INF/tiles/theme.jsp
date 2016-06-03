<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <c:if test="${contest.coming}">
        <meta content="60" http-equiv="refresh"/>
    </c:if>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-Control" content="no-cache"/>
    <meta http-equiv="Cache-Control" max-age="0"/>
    <meta name="description" content="Caribbean Online Judge (COJ)"/>
    <meta name="keywords"
          content="coj, caribbean, programming, contest, online, judge, problems"/>
    <meta name="robots" content="index, about"/>
    <meta http-equiv="X-Frame-Options" content="deny"/>
    <title>
        <%
            try {
        %> <c:set var="title">
        <tiles:getAsString name="title"/>
    </c:set> <spring:message code="${title}" argumentSeparator=","
                             arguments="${user.username},${problem.title},${institution.name},${country.name},${contest.name},${user.nick}"/>
        <%
        } catch (Exception e) {
        %> <c:out value="COJ"/> <%
        }
    %>
    </title>
    <link rel="shortcut icon"
          href="<c:url value="/images/coj_favicon.png"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/bootstrap.min.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="<c:url value="/css/font-awesome.min.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet"
          href="<c:url value="/css/custom.bootstrap.tooltip.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="<c:url value="/css/fileinput.min.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="<c:url value="/css/datatable.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet" href="<c:url value="/css/main.css"/>"
          type="text/css" media="screen"/>
    <link rel="stylesheet"
          href="<c:url value="/css/ui-1.11.2/jquery-ui-1.11.2.min.css"/>"
          type="text/css" media="screen"/>
    <!--[if IE 6]>
    <link rel="stylesheet" href="<c:url value="/css/style.ie6.css"/>" type="text/css" media="screen" />
    <![endif]-->
    <!--[if IE 7]>
    <link rel="stylesheet" href="<c:url value="/css/style.ie7.css"/>" type="text/css" media="screen" />
    <![endif]-->
    <script type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
    <script type="text/javascript"
            src="<c:url value="/js/ui-1.11.2/jquery-ui-1.11.2.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="/js/bootstrap.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="/js/fileinput.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="/js/bootstrap-maxlength.min.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="/js/jquery.numeric.js"/>"></script>
    <script type="text/javascript"
            src="<c:url value="/js/jquery.form.js" />"></script>
    <script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
	<script>
	    $(document).ready(function () {
	        window.i18nPleaseWait="<spring:message code="text.please.wait"/>";  
	    });
	</script>
    
    <script type="text/javascript" src="<c:url value="/js/displayTables.js" />"></script>
    <script src="<c:url value="/js/countdown.js"/>"></script>
</head>

<c:set var="idpage">
    <tiles:getAsString name="idpage"/>
</c:set>
<%
    ServletContext context = request.getSession().getServletContext();
    String idpage = (String) pageContext.getAttribute("idpage");
    context.setAttribute("idpage", idpage);
%>

<body>
<div id="main">
    <div class="sheet">
        <div class="sheet-body">
            <tiles:insertAttribute name='header'/>
            <div class="content-layout">
                <div class="content-layout-row">
                    <tiles:insertAttribute name='sidebar'/>
                    <div class="layout-cell content">
                        <div class="post">
                            <div class="post-body">
                                <div class="post-inner article">
                                    <c:if test="${contest != null and contest.initdate != null and (contest.past or contest.running or contest.coming)}">
                                        <div class="pull-right">
                                            <table class="contestlanguages" style="align: right">
                                                <tbody>
                                                <tr>
                                                    <td><b><spring:message code="fieldhdr.status"/>:
                                                    </b></td>
                                                    <td><c:choose>
                                                        <c:when test="${contest.running == true}">
																		<span class="label label-success"><spring:message
                                                                                code="fieldhdr.running"/></span>
                                                        </c:when>
                                                        <c:when test="${contest.coming == true}">
																		<span class="label label-danger"><spring:message
                                                                                code="fieldhdr.coming"/></span>
                                                        </c:when>
                                                        <c:when test="${contest.past == true}">
																		<span class="label label-default"><spring:message
                                                                                code="fieldhdr.past"/></span>
                                                        </c:when>
                                                    </c:choose></td>
                                                    <c:if test="${contest.running}">
                                                        <td><b><spring:message code="fieldhdr.elapsed"/>:</b></td>
                                                        <td>
                                                            <div class="countup"
                                                                 data-start-date="${contest.initdate.time}"
                                                                 data-end-date="${contest.enddate.time}"></div>
                                                        </td>
                                                        <td><b><spring:message code="fieldhdr.remaining"/>:</b></td>
                                                        <td>
                                                            <div class="countdown"
                                                                 data-date="${contest.enddate.time}"></div>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${contest.past or contest.coming}">
                                                        <td><b><spring:message code="fieldhdr.start"/>:</b></td>
                                                        <td><fmt:formatDate value="${contest.initdate}"
                                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                        <td><b><spring:message code="fieldhdr.end"/>:
                                                        </b></td>
                                                        <td><fmt:formatDate value="${contest.enddate}"
                                                                            pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                    </c:if>
                                                </tr>
                                                <c:if
                                                        test="${contest.running == true && (contest.full_frozen == true || contest.frozen == true)}">
                                                    <tr>
                                                        <td colspan="6" style="text-align: right"><c:choose>
                                                            <c:when test="${contest.full_frozen == true}">
																			<span class="label label-danger"><i
                                                                                    class="fa fa-warning"></i>&nbsp;<spring:message
                                                                                    code="text.deadtime"/></span>
                                                            </c:when>
                                                            <c:otherwise>
																			<span class="label label-primary"><i
                                                                                    class="fa fa-info-circle"></i>&nbsp;<spring:message
                                                                                    code="text.frozentime"/></span>
                                                            </c:otherwise>
                                                        </c:choose></td>
                                                    </tr>
                                                </c:if>

                                                </tbody>
                                            </table>
                                        </div>
                                        <br/>
                                    </c:if>
                                    <div class="clearfix"></div>
                                    <a id="header"></a>
                                    <tiles:insertAttribute name='content'/>
                                    <div class="pull-right">
                                        <a href="#header" title="<spring:message code="titval.top"/>"><i
                                                class="fa fa-toggle-up fa-lg"></i></a>
                                    </div>
                                    <div class="cleared"></div>
                                </div>

                                <div class="cleared"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="cleared"></div>
            <div class="footer">
                <div class="footer-inner">
                    <div class="footer-text">
                        <tiles:insertAttribute name='footer'/>
                    </div>
                </div>
            </div>
            <div class="cleared"></div>
        </div>
    </div>
    <div class="cleared"></div>
</div>


<script>
    $(document).ready(function () {
        var server_time = ${now.time};
        var time_access = (new Date().getTime());

        Countdown(server_time, time_access).countup;
        setInterval(Countdown(server_time, time_access).countup, 1000);

        Countdown(server_time, time_access).countdown;
        setInterval(Countdown(server_time, time_access).countdown, 1000);
    });
</script>
</body>
</html>
