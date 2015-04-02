<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<script type="text/javascript" >
    jQuery(document).ready(function($) {

    //TODO: @LAN - ver si esto es necesario
    wsocketSubscribe();
    });</script>
<div class="plagMatrix" response-socket-name="${responseSocketName}">
    <c:choose>
        <c:when test="${sourceSubmissions.isEmpty() or destinationSubmissions.isEmpty()}">                            
            There are no results
        </c:when>
        <c:otherwise>
            <div id="left">
                <table class="navigating navvertical" align="center" width="100%">                

                    <c:if test="${not empty mrnfirst.active}">

                        <c:choose>
                            <c:when test="${mrnfirst.active==true}">
                                <tr class="navbuttons">
                                    <td><a onclick="ajaxMrNavigating(1${get});"><img src="<c:url value="/images/first.gif"/>" alt="<spring:message code="altval.first"/>" title="<spring:message code="titval.first"/>"/></a></td>
                                </tr >
                                <tr class="navbuttons">
                                    <td><a onclick="ajaxMrNavigating(${mrnfirst.value}${get});"><img src="<c:url value="/images/prev.gif"/>" alt="<spring:message code="altval.previous"/>" title="<spring:message code="titval.previous"/>"/></a></td>
                                </tr>            
                            </c:when>
                            <c:otherwise>
                                <tr class="navbuttons"><td></td></tr>
                                <tr class="navbuttons"><td></td></tr>
                                    </c:otherwise>
                                </c:choose>

                    </c:if>
                    <c:forEach items="${mrlinks}" var="navigating">

                        <c:if test="${not empty navigating.active}">
                            <c:choose>
                                <c:when test="${navigating.active==true}">
                                    <tr style="height: 4%">
                                        <td>
                                            <a onclick="ajaxMrNavigating(${navigating.value}${get});" ><c:out value="${navigating.value}"/></a>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${navigating.active==false}">
                                    <tr>
                                        <td>
                                            <c:out value="${navigating.value}"/>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:if>

                    </c:forEach>
                    <c:if test="${not empty mrnend.active}">

                        <c:choose>
                            <c:when test="${mrnend.active==true}">
                                <tr class="navbuttons">
                                    <td>
                                        <a onclick="ajaxMrNavigating(${mrnend.value}${get});"><img src="<c:url value="/images/next.gif"/>" alt="<spring:message code="altval.next"/>" title="<spring:message code="titval.next"/>"/></a>
                                    </td>
                                </tr>
                                <tr class="navbuttons">
                                    <td>
                                        <a onclick="ajaxMrNavigating(${mrend.value}${get});" ><img src="<c:url value="/images/last.gif"/>" alt="<spring:message code="altval.last"/>" title="<spring:message code="titval.last"/>"/></a>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr class="navbuttons" ><td></td></tr>
                                <tr class="navbuttons"><td></td></tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                </table>
            </div>

            <div id="matrix">
                <table class="navigating" align="center" width="100%">                
                    <tr>
                        <c:if test="${not empty mcnfirst.active}">
                            <td class="navbuttons">
                                <c:choose>
                                    <c:when test="${mcnfirst.active==true}">
                                        <a onclick="ajaxMcNavigating(1${get});"><img src="<c:url value="/images/first.gif"/>" alt="<spring:message code="altval.first"/>" title="<spring:message code="titval.first"/>"/></a>
                                        <a onclick="ajaxMcNavigating(${mcnfirst.value}${get});"><img src="<c:url value="/images/prev.gif"/>" alt="<spring:message code="altval.previous"/>" title="<spring:message code="titval.previous"/>"/></a>
                                        </c:when>
                                    </c:choose>
                            </td>
                        </c:if>
                        <c:forEach items="${mclinks}" var="navigating">
                            <td width = "4%">
                                <c:if test="${not empty navigating.active}">
                                    <c:choose>
                                        <c:when test="${navigating.active==true}">
                                            <a onclick="ajaxMcNavigating(${navigating.value}${get});" ><c:out value="${navigating.value}"/></a>
                                        </c:when>
                                        <c:when test="${navigating.active==false}">
                                            <c:out value="${navigating.value}"/>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${not empty mcnend.active}">
                            <td class="navbuttons">
                                <c:choose>
                                    <c:when test="${mcnend.active==true}">
                                        <a onclick="ajaxMcNavigating(${mcnend.value}${get});"><img src="<c:url value="/images/next.gif"/>" alt="<spring:message code="altval.next"/>" title="<spring:message code="titval.next"/>"/></a>
                                        <a onclick="ajaxMcNavigating(${mcend.value}${get});" ><img src="<c:url value="/images/last.gif"/>" alt="<spring:message code="altval.last"/>" title="<spring:message code="titval.last"/>"/></a>
                                        </c:when>
                                    </c:choose>
                            </td>
                        </c:if>
                    </tr>
                </table>
                <table class="volume matrixTable" border="1px" style="color: #ffffff">        
                    <thead class="orderby">
                    <th>S\D</th>        
                        <c:forEach  var="submission" items="${destinationSubmissions}">           
                        <th title="<c:out value="${submission.username}"/>">  <a href="/24h/submission.xhtml?id=${submission.sid}">  <c:out value="${submission.sid}"/></a></th> 
                        </c:forEach>
                    </thead>
                    <tbody>  
                        <c:forEach var="matrixRow"  items="${resultsMatrix}" varStatus="varStatus">
                            <tr>                            
                                <th title="<c:out value="${sourceSubmissions.get(varStatus.count-1).username}"/>"><a href="/24h/submission.xhtml?id=${sourceSubmissions.get(varStatus.count-1).sid}"> <c:out value="${sourceSubmissions.get(varStatus.count-1).sid}"/></a></th> 
                                    <c:forEach var="result" items="${matrixRow}" varStatus="destVarStatus">
                                        <c:choose>  
                                            <c:when test="${result.getFlag().name() eq 'NORMAL' and result.getDictum() lt 0}" >
                                            <td style="background-color: #AAAA00" class="pcojResult" ssid="${sourceSubmissions.get(varStatus.count-1).sid}" dsid="${destinationSubmissions.get(destVarStatus.count-1).sid}"><a class="pcojresultlink" target="_new" href="/plagicoj/plagicojresult.xhtml?ssid=${sourceSubmissions.get(varStatus.count-1).sid}&didd=${destinationSubmissions.get(destVarStatus.count-1).sid}"><b>?</b> </a></td>
                                        </c:when>
                                        <c:when test="${result.getFlag().name() eq 'NORMAL'}" >
                                            <td class="pcojResult dictumCategory<fmt:formatNumber maxFractionDigits="0" value="${result.getDictum()*10}" />" ssid="${sourceSubmissions.get(varStatus.count-1).sid}" dsid="${destinationSubmissions.get(destVarStatus.count-1).sid}"><a class="pcojresultlink" target="_new" href="/plagicoj/plagicojresult.xhtml?ssid=${sourceSubmissions.get(varStatus.count-1).sid}&didd=${destinationSubmissions.get(destVarStatus.count-1).sid}"><b><fmt:formatNumber type="percent" value="${result.getDictum()}" /></b> </a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td title="<fmt:message key="${result.getFlag().name()}"/>" class="flag<c:out value="${result.getFlag().name()}" />"> </td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </tr>
                        </c:forEach>             
                    </tbody>
                </table>
                <table class="navigating" align="center" width="100%">                
                    <tr>
                        <c:if test="${not empty mcnfirst.active}">
                            <td class="navbuttons">
                                <c:choose>
                                    <c:when test="${mcnfirst.active==true}">
                                        <a onclick="ajaxMcNavigating(1${get});"><img src="<c:url value="/images/first.gif"/>" alt="<spring:message code="altval.first"/>" title="<spring:message code="titval.first"/>"/></a>
                                        <a onclick="ajaxMcNavigating(${mcnfirst.value}${get});"><img src="<c:url value="/images/prev.gif"/>" alt="<spring:message code="altval.previous"/>" title="<spring:message code="titval.previous"/>"/></a>
                                        </c:when>
                                    </c:choose>
                            </td>
                        </c:if>
                        <c:forEach items="${mclinks}" var="navigating">
                            <td width = "4%">
                                <c:if test="${not empty navigating.active}">
                                    <c:choose>
                                        <c:when test="${navigating.active==true}">
                                            <a onclick="ajaxMcNavigating(${navigating.value}${get});" ><c:out value="${navigating.value}"/></a>
                                        </c:when>
                                        <c:when test="${navigating.active==false}">
                                            <c:out value="${navigating.value}"/>
                                        </c:when>
                                    </c:choose>
                                </c:if>
                            </td>
                        </c:forEach>
                        <c:if test="${not empty mcnend.active}">
                            <td class="navbuttons">
                                <c:choose>
                                    <c:when test="${mcnend.active==true}">
                                        <a onclick="ajaxMcNavigating(${mcnend.value}${get});"><img src="<c:url value="/images/next.gif"/>" alt="<spring:message code="altval.next"/>" title="<spring:message code="titval.next"/>"/></a>
                                        <a onclick="ajaxMcNavigating(${mcend.value}${get});" ><img src="<c:url value="/images/last.gif"/>" alt="<spring:message code="altval.last"/>" title="<spring:message code="titval.last"/>"/></a>
                                        </c:when>
                                    </c:choose>
                            </td>
                        </c:if>
                    </tr>
                </table>
            </div>
            <div id="right">
                <table class="navigating navvertical" align="center" width="100%">                

                    <c:if test="${not empty mrnfirst.active}">

                        <c:choose>
                            <c:when test="${mrnfirst.active==true}">
                                <tr class="navbuttons">
                                    <td><a onclick="ajaxMrNavigating(1${get});"><img src="<c:url value="/images/first.gif"/>" alt="<spring:message code="altval.first"/>" title="<spring:message code="titval.first"/>"/></a></td>
                                </tr >
                                <tr class="navbuttons">
                                    <td><a onclick="ajaxMrNavigating(${mrnfirst.value}${get});"><img src="<c:url value="/images/prev.gif"/>" alt="<spring:message code="altval.previous"/>" title="<spring:message code="titval.previous"/>"/></a></td>
                                </tr>            
                            </c:when>
                            <c:otherwise>
                                <tr class="navbuttons"><td></td></tr>
                                <tr class="navbuttons"><td></td></tr>
                                    </c:otherwise>
                                </c:choose>

                    </c:if>
                    <c:forEach items="${mrlinks}" var="navigating">

                        <c:if test="${not empty navigating.active}">
                            <c:choose>
                                <c:when test="${navigating.active==true}">
                                    <tr style="height: 4%">
                                        <td>
                                            <a onclick="ajaxMrNavigating(${navigating.value}${get});" ><c:out value="${navigating.value}"/></a>
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${navigating.active==false}">                                    
                                    <tr>
                                        <td>
                                            <c:out value="${navigating.value}"/>
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </c:if>

                    </c:forEach>
                    <c:if test="${not empty mrnend.active}">

                        <c:choose>
                            <c:when test="${mrnend.active==true}">
                                <tr class="navbuttons">
                                    <td>
                                        <a onclick="ajaxMrNavigating(${mrnend.value}${get});"><img src="<c:url value="/images/next.gif"/>" alt="<spring:message code="altval.next"/>" title="<spring:message code="titval.next"/>"/></a>
                                    </td>
                                </tr>
                                <tr class="navbuttons">
                                    <td>
                                        <a onclick="ajaxMrNavigating(${mrend.value}${get});" ><img src="<c:url value="/images/last.gif"/>" alt="<spring:message code="altval.last"/>" title="<spring:message code="titval.last"/>"/></a>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr class="navbuttons" ><td></td></tr>
                                <tr class="navbuttons"><td></td></tr>
                                    </c:otherwise>
                                </c:choose>

                    </c:if>
                    </tr>
                </table>
            </div>
            <div id="legend">
                <c:forEach items="${colorPerDictum}" var="colors">           
                    <div style="background-color: ${colors}; width: 10%;height: 20px;float: left"></div> 
                </c:forEach>
            </div>            
        </c:otherwise>
    </c:choose>
</div>  
