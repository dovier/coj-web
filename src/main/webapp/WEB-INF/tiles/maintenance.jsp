<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US" xml:lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <meta http-equiv="Cache-Control" max-age="0"/>
        <meta name="description" content="Caribbean Online Judge (COJ)" />
        <meta name="keywords" content="coj, caribbean, programming, contest, online, judge, problems" />
        <meta name="robots" content="index, about" />
        <title>
            <%
                try {
            %>
            <c:set var="title"><tiles:getAsString name="title"/></c:set>
            <spring:message code="${title}" argumentSeparator="," arguments="${user.username},${problem.title},${institution.name},${country.name},${contest.name},${user.nick}" />
            <%} catch (Exception e) {%>
            <c:out value="COJ"/>
            <%}%>
        </title>
        <link rel="shortcut icon" href="<c:url value="/images/coj_favicon.png"/>" />
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" type="text/css" media="screen" />
        <link rel="stylesheet" href="<c:url value="/css/datatable.css"/>" type="text/css" media="screen" />
        <link rel="stylesheet" href="<c:url value="/css/main.css"/>" type="text/css" media="screen" />        

        <!--[if IE 6]>
        <link rel="stylesheet" href="<c:url value="/css/style.ie6.css"/>" type="text/css" media="screen" />
        <![endif]-->
        <!--[if IE 7]>
            <link rel="stylesheet" href="<c:url value="/css/style.ie7.css"/>" type="text/css" media="screen" />
        <![endif]-->

        <script type="text/javascript" src="<c:url value="/js/script.js"/>"></script>
    </head>
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
                                            <tiles:insertAttribute name='content'/>
                                            <p style="align:right">
                                                <a href="#header" title="<spring:message code="titval.top"/>"><i class="fa fa-toggle-up fa-lg" ></i></a>
                                            </p>
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
    </body>
</html>

