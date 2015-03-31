<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" lang="en-US"
	xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>
	<%
		try {
	%> <c:set var="title">
		<tiles:getAsString name="title" />
	</c:set> <spring:message code="${title}" argumentSeparator="," arguments="${user.username},${problem.title},${institution.name},${country.name},${contest.name},${user.nick}" /> <%
 	} catch (Exception e) {
 %> <c:out value="COJ" /> <%
 	}
 %>
</title>
<link rel="shortcut icon"
	href="<c:url value="/images/coj_favicon.png"/>" />
<link rel="stylesheet" href="<c:url value="/css/style.css"/>"
	type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/css/main.css"/>"
	type="text/css" media="screen" />
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
		<tiles:insertAttribute name='content' />
		<div class="cleared"></div>
	</div>
</body>
</html>


