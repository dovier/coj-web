<%@page import="cu.uci.coj.utils.Utils"%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>


<div class="header">

    <div class="headercss">


        <div class="headerlf">
            <div class="headerleft">
                <div class="logoc">C</div>
                <div class="logoo">O</div>
                <div class="logoj">J</div>
            </div>

            <div class="slogan"><spring:message code="header.thinkingbetter" /></div>
        </div>
        <div class="headerrigth">
            <span class="bannername">Caribbean Online Judge</span>            
        </div>
        <div class="i18n">
            <%
                String url = null;
                if (request.getQueryString() == null) {
                    url = request.getAttribute("javax.servlet.forward.request_uri").toString();
                } else {
                    url = request.getAttribute("javax.servlet.forward.request_uri").toString() + "?" + request.getQueryString();
                }
                String url_en = Utils.addParameter(url, "lang=en");
                String url_es = Utils.addParameter(url, "lang=es");
                String url_pt = Utils.addParameter(url, "lang=pt");

            %>
            <a href="<%=url_en%>"><img src="<c:url value="/images/i18n/en.png"/>" alt="<spring:message code="altval.en"/>" title="<spring:message code="titval.en"/>" class="image" /></a>
            <a href="<%=url_es%>"><img src="<c:url value="/images/i18n/es.png"/>" alt="<spring:message code="altval.es"/>" title="<spring:message code="titval.es"/>" class="image" /></a>
            <!--a href="<%=url_pt%>"><img src="<c:url value="/images/i18n/pt.png"/>" alt="<spring:message code="altval.pt"/>" title="<spring:message code="titval.pt"/>" class="image" /></a-->
        </div>
    </div>


</div>
<c:if test="${hasann == true}">
		<div class="panel panel-info">
				<c:forEach items="${ann}" var="an">
						&nbsp;<span class="red">
						<i class="fa fa-info-circle"></i></span>${an}
					</c:forEach>
		</div>
</c:if>

<div class="nav">

    <div class="l">
        <p class="servertime">
            <jsp:useBean id="now" class="java.util.Date" />
            <fmt:formatDate value="${now}" pattern="EEEE" var="dia"/>
            ${dia} , <fmt:formatDate value="${now}" dateStyle="long" />.
            <fmt:formatDate value="${now}" pattern="HH:mm:ss" type="time" />            
        </p>
    </div>
    <div class="r"></div>
</div>

