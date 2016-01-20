<%--
  User: alison
  Date: 15/01/16
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.header.admin.site.details"/>
</h2>

<div class="postcontent">
    <a class="btn btn-primary mybutton" href='<c:url value="/admin/faqs.xhtml"/>'> <spring:message
            code="wmfaq.list.faq"/>
    </a>

    <br/>
    <br/>

    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <spring:message code="fieldhdr.details"/>
                </div>
                <div id="details" class="panel-body">
                    <div class="col-xs-5">
                        <spring:message code="addfaq.question" />
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${faq.question}
                    </div>


                    <div class="col-xs-5">
                        <spring:message code="addfaq.answer" />
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${faq.answer}
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>