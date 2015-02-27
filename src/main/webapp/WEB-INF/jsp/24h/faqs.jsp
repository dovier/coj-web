<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
    <spring:message code="pagehdr.faq"/>
</h2>
<div class="postcontent">
    <!-- content -->
    <c:forEach var="faq" items="faqs">
        <div>
            <div>
                ${faq.question}
            </div>
            <div>
                ${faq.answer}
            </div>
        </div>
    </c:forEach>
    <!-- /content -->
</div>