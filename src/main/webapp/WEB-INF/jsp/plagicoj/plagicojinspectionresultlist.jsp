<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

    <div id="resultListDiv">                 
        <a name="results"></a>
        <table class="navigating" align="center" width="100%">
            <caption class="plagicojtable"> Plagiarism Detection Results, List View. </caption>
            <tr>
                <c:if test="${not empty nfirst.active}">
                    <td class="navbuttons">
                        <c:choose>
                            <c:when test="${nfirst.active==true}">
                                <a onclick="ajaxNavigating(1${get});"><img src="<c:url value="/images/first.gif"/>" alt="<spring:message code="altval.first"/>" title="<spring:message code="titval.first"/>"/></a>
                                <a onclick="ajaxNavigating(${nfirst.value}${get});"><img src="<c:url value="/images/prev.gif"/>" alt="<spring:message code="altval.previous"/>" title="<spring:message code="titval.previous"/>"/></a>
                                </c:when>
                            </c:choose>
                    </td>
                </c:if>

                <c:forEach items="${links}" var="navigating">
                    <td width = "4%">
                        <c:if test="${not empty navigating.active}">
                            <c:choose>
                                <c:when test="${navigating.active==true}">
                                    <a onclick="ajaxNavigating(${navigating.value}${get});" ><c:out value="${navigating.value}"/></a>
                                </c:when>
                                <c:when test="${navigating.active==false}">
                                    <c:out value="${navigating.value}"/>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </td>
                </c:forEach>

                <c:if test="${not empty nend.active}">
                    <td class="navbuttons">
                        <c:choose>
                            <c:when test="${nend.active==true}">
                                <a onclick="ajaxNavigating(${nend.value}${get});"><img src="<c:url value="/images/next.gif"/>" alt="<spring:message code="altval.next"/>" title="<spring:message code="titval.next"/>"/></a>
                                <a onclick="ajaxNavigating(${end.value}${get});" ><img src="<c:url value="/images/last.gif"/>" alt="<spring:message code="altval.last"/>" title="<spring:message code="titval.last"/>"/></a>
                                </c:when>
                            </c:choose>
                    </td>
                </c:if>
            </tr>
        </table>
        <table class="volume intercalated" id="resultlist" border="1px">
            <thead class="orderby">
            <th id="sourceSubmission">Source Submission</th>
            <!--<th id="sourceUser">Source User</th>-->
            <th id="destinationSubmission">Destination Submission</th>
            <!--<th id="destinationUser">Destination User</th>-->
            <th >Dictum</th>            
            </thead>
            <tbody>                
                <c:forEach items="${resultsList}" varStatus="varStatus" var="result">      
                    <tr>
                        <td>
                            <a href="/24h/submission.xhtml?id=${result.sourceSubmissionId}">
                                <c:out value="${result.sourceSubmissionId}"/>
                            </a>
                        </td>                      
                        <td>                               
                            <a href="/24h/submission.xhtml?id=${result.destinationSubmissionId}">
                                <c:out value="${result.destinationSubmissionId}"/>
                            </a>
                        </td>
                        <td ><a class="dictumValue" dictum="${result.dictum}" target="_new" href="/plagicoj/plagicojresult.xhtml?ssid=${result.sourceSubmissionId}&didd=${result.destinationSubmissionId}">${result.formattedDictum()}</a></td>
                    </tr>                  
                </c:forEach>
            </tbody>
        </table>   
          <table class="navigating" align="center" width="100%">            
            <tr>
                <c:if test="${not empty nfirst.active}">
                    <td class="navbuttons">
                        <c:choose>
                            <c:when test="${nfirst.active==true}">
                                <a onclick="ajaxNavigating(1${get});"><img src="<c:url value="/images/first.gif"/>" alt="<spring:message code="altval.first"/>" title="<spring:message code="titval.first"/>"/></a>
                                <a onclick="ajaxNavigating(${nfirst.value}${get});"><img src="<c:url value="/images/prev.gif"/>" alt="<spring:message code="altval.previous"/>" title="<spring:message code="titval.previous"/>"/></a>
                                </c:when>
                            </c:choose>
                    </td>
                </c:if>

                <c:forEach items="${links}" var="navigating">
                    <td width = "4%">
                        <c:if test="${not empty navigating.active}">
                            <c:choose>
                                <c:when test="${navigating.active==true}">
                                    <a onclick="ajaxNavigating(${navigating.value}${get});" ><c:out value="${navigating.value}"/></a>
                                </c:when>
                                <c:when test="${navigating.active==false}">
                                    <c:out value="${navigating.value}"/>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </td>
                </c:forEach>

                <c:if test="${not empty nend.active}">
                    <td class="navbuttons">
                        <c:choose>
                            <c:when test="${nend.active==true}">
                                <a onclick="ajaxNavigating(${nend.value}${get});"><img src="<c:url value="/images/next.gif"/>" alt="<spring:message code="altval.next"/>" title="<spring:message code="titval.next"/>"/></a>
                                <a onclick="ajaxNavigating(${end.value}${get});" ><img src="<c:url value="/images/last.gif"/>" alt="<spring:message code="altval.last"/>" title="<spring:message code="titval.last"/>"/></a>
                                </c:when>
                            </c:choose>
                    </td>
                </c:if>
            </tr>
        </table>
    </div>