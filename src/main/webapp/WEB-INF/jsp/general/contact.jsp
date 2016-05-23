<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<h2 class="postheader">
    <spring:message code="pagehdr.contactus"/>
</h2>

<div class="postcontent">
    <!-- article-content -->
    <p>
        <spring:message code="text.contactus.1"/>
    </p>

    <div class="col-md-12">
        <div class="col-md-12 margin-bottom-05">
            <legend>
                <spring:message code="text.contactus.6"/>
            </legend>
            Universidad de las Ciencias Informáticas. Carretera a San Antonio de los Baños, Km. 2 ½. Torrens, municipio de La Lisa. La Habana, Cuba.
        </div>
        <div class="col-md-4 margin-top-05 margin-bottom-05">
            <legend>
                <spring:message code="text.contactus.7"/>
            </legend>
            (+53) 7 837 2491
        </div>
        <div class="col-md-4 margin-top-05 margin-bottom-05">
            <legend>
                <spring:message code="text.contactus.8"/>
            </legend>
            <spring:message code="text.contactus.2"/><br>
            <spring:message code="text.contactus.3"/><br>
            <spring:message code="text.contactus.4"/><br>
        </div>
    </div>
    <p>
        <spring:message code="text.contactus.5"/>
    </p>
    <!-- /article-content -->
</div>
