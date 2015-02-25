<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <fmt:message key="pagehdr.faq"/>
</h2>
<div class="postcontent">
    <br/>
    <c:forEach var="faq" items="${faqs}">        
        <ul>
            <li>
                <a href="#${faq.id}">${faq.question}</a>
            </li>
        </ul>   
    </c:forEach>
    <dl class="dl-vertical">
    <c:forEach var="faq" items="${faqs}">        
        <dt><a id="${faq.id}"></a>   
        <h3>${faq.question}&nbsp;<a href="#header" title="<spring:message code="titval.top"/>"><i class="fa fa-toggle-up fa-lg" ></i></a></h3></dt>
        <dd>
        <div>${faq.answer}</div></dd>
    </c:forEach>
</div>