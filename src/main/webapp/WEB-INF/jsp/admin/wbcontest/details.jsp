<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.header.admin.wbcontest.details"/>
</h2>

<div class="postcontent">
    <div class="row">
        <div class="col-xs-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <spring:message code="fieldhdr.details"/>
                </div>
                <div id="details" class="panel-body">
                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.name"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${wbcontest.name}
                    </div>


                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.url"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        <a href="${wbcontest.url}">${wbcontest.url}</a>
                    </div>

                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.startdate"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${wbcontest.startDate}
                    </div>

                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.enddate"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${wbcontest.endDate}
                    </div>

                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.site"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        ${mapsites.get(wbcontest.sid).site}
                    </div>
                    <div class="clearfix"></div>
                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.notifcreated"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        <c:if test="${wbcontest.notifCreated}">
                            <span class="label label-success"><fmt:message
                                    key="page.general.yes"/></span>
                        </c:if>
                        <c:if test="${!wbcontest.notifCreated}">
                            <span class="label label-danger"><fmt:message
                                    key="page.general.no"/></span>
                        </c:if>
                    </div>

                    <div class="col-xs-5">
                        <spring:message code="fieldhdr.notifchanged"/>
                    </div>
                    <div class="col-xs-6 col-xs-offset-1">
                        <c:if test="${wbcontest.notifChanged}">
                           <span class="label label-success"><fmt:message
                                   key="page.general.yes"/></span>
                        </c:if>
                        <c:if test="${!wbcontest.notifChanged}">
                            <span class="label label-danger"><fmt:message
                                    key="page.general.no"/></span>
                        </c:if>
                    </div>

                </div>
            </div>
            <div class="coj_float_rigth">
                <a class="btn btn-primary" href="<c:url value="/admin/wboard/contest/list.xhtml"/>">
                    <spring:message code="button.close"/>
                </a>
            </div>
        </div>
    </div>
</div>