<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>

<!-- Styles -->
<link type="text/css" href="<c:url value="/css/plagicoj.css"/>" rel="stylesheet" />

<h2 class="postheader">
    <fmt:message key="plagicoj.title"/>
</h2>
<div class="postcontent">       
    <div class="plagMatrix" response-socket-name="${responseSocketName}">     
    </div>  
    <c:if test="${ownSubmissionJudge  eq false and sameUser eq false}" >
        <a href="#" class="btnPlagicoj" id="btnToggleEqualSubmissionVisibility">Hide Equal SubmissionJudge Visibility</a>
    </c:if>
    <c:if test="${sameUser eq false}" >
        <a href="#" class="btnPlagicoj" id="btnToggleEqualUserVisibility">Hide Equal User Visibility</a>    
    </c:if>
    <div id="resultListDiv" response-socket-name="${listResponseSocketName}">                       
    </div>
    <a href="/admin/manageplagicoj.xhtml" >Back</a>
</div>

<!-- Scripts -->

<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script  type="text/javascript" src="/js/jquery.js"></script>
<script  type="text/javascript" src="<c:url value="/js/atmosphere.js" />"> </script>
<script  type="text/javascript" src="<c:url value="/js/jquery.mustache.js" />"> </script>
<script  type="text/javascript" src="/js/plagicoj.js"></script>

<script type="text/javascript" >   
    
    
    $(function() {        
        addFunctions();               
    });        
   
     jQuery(document).ready(function($) {
         ajaxMcNavigating(1);
         ajaxNavigating(1);     
         
         wsocketSubscribe('plagicoj', $("#resultListDiv").attr("response-socket-name"),onListSocketMessage);
    });
    
    
</script>
    
    
<script id="listItemTpl" type="x-tmpl-mustache">
    <tr class="{{ rowStyle }}">
        <td>
            <a href="/24h/submission.xhtml?id={{ ssid }}">
        <c:out value="{{ ssid }}"/>
            </a>
        </td>                      
        <td>                               
            <a href="/24h/submission.xhtml?id={{ dsid }}">
        <c:out value="{{ dsid }}"/>
            </a>
        </td>
        <td ><a class="dictumValue" dictum="{{dictum}}" href="/plagicoj/plagicojresult.xhtml?ssid={{ ssid }}&didd={{ dsid }}">{{ formattedDictum }}</a></td>
        </tr>   
</script>