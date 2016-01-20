<%--
  User: alison
  Date: 13/01/16
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.header.admin.source.details"/>
</h2>

<div class="postcontent">
    <a class="btn btn-primary mybutton" href='<c:url value="/admin/managesources.xhtml"/>'> <spring:message
            code="wpsource.list.source"/> </a>

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
                        <spring:message code="psource.newsource"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${wproblemSource.name}
                    </div>

                    <div class="col-xs-5">
                        <spring:message code="psource.author"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${wproblemSource.author}
                    </div>

                </div>
            </div>
        </div>
    </div>