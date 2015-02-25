<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<!-- Styles -->
<link type="text/css" href="<c:url value="/css/plagicoj.css"/>" rel="stylesheet" />

<link type="text/css" href="<c:url value="/css/ui/jquery.ui.all.css"/>" rel="stylesheet" /> 
<!-- Scripts -->
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"> </script>
<script  type="text/javascript" src="<c:url value="/js/plagicoj.js" />"></script>

<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.core.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.widget.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.mouse.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.slider.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.button.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.draggable.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.position.min.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/ui/jquery.ui.dialog.min.js" />"></script>

<script  type="text/javascript" >
//    $(function() {            
//        checkDetectionParameters("<fmt:message key="admin.plagicoj.hours"/>","<fmt:message key="admin.plagicoj.minutes"/>","<fmt:message key="admin.plagicoj.seconds"/>"); 
//    });
</script>

<!-- Content -->
<h2 class="postheader">
    <fmt:message key="plagicoj.title"/>
</h2>
<div class="postcontent">   
    <div>
        <form action="/plagicoj/plagicojinspectionresult.xhtml" method="post" name="leandro" >        
            <div class="leftdiv">
                <table class="plagicoj" id="plagicoj">
                    <caption><fmt:message key="admin.plagicoj.detectionparameters"/></caption>
                    <tr>
                        <th><fmt:message key="admin.plagicoj.pivotsubmit"/>:</th><td><input type="text" name="submitid"></td>
                    <tr>           
                    <tr>
                        <th><fmt:message key="admin.plagicoj.pid"/>:</th><td><input type="text" name="pid"></td>
                    <tr>  
                    <tr>
                        <th><fmt:message key="admin.plagicoj.submitid"/>:</th><td><input type="text" name="submitid1"> / <input type="text" name="submitid2"></td>
                    <tr>
                    <tr>
                        <th><fmt:message key="admin.plagicoj.range"/>:</th><td><input type="text" name="sid1"> / <input type="text" name="sid2"></td>
                    <tr>

                </table>
            </div>
            <div class="rigthdiv" >
                <h5>Filters</h5>
                <label for="onlyac">
                    <input type="checkbox" id="onlyac" name="onlyac"/>
                    <fmt:message key="admin.plagicoj.onlyac"/>                
                </label>
                <br>
                <label for="matchlanguage">
                    <input type="checkbox" id="matchlanguage" name="matchlanguage"/>
                    <fmt:message key="admin.plagicoj.matchlanguage"/>                
                </label>
                <br>
                <label for="ownsubmission">
                    <input type="checkbox" id="ownsubmission" name="ownsubmission"/>
                    <fmt:message key="admin.plagicoj.ownsubmission"/>                
                </label><br>
                <label for="sameuser">
                    <input type="checkbox" id="sameuser" name="sameuser"/>
                    <fmt:message key="admin.plagicoj.sameuser"/>                
                </label><br>
            </div>
            <div class="buttons nofloat">
                <input type="submit" value="<fmt:message key="admin.plagicoj.detect"/>" />
                <input type="reset" value="<fmt:message key="admin.plagicoj.reset"/>" />
            </div>
        </form>
    </div>
    <div class="nofloat judgement">  
        <h4 >
            <spring:message code="pagehdr.24hjudgments"/>
        </h4>
        <form id="filter-form" class="form-inline">
            <table class="login filters">
                <tr class="filter">
                    <td><spring:message code="fieldhdr.user"/>:</td>
                    <td>
                        <input type="text" name="username" value="${filter.username}" size="10"/>
                    </td>
                    <td><spring:message code="fieldhdr.prob"/>:</td>
                    <td>
                        <input type="text" name="abb" value="${filter.pid}" size="8"/>
                    </td>
                    <td><spring:message code="fieldhdr.judgment"/>:</td>
                    <td>
                        <select name="status" style="text-transform: lowercase;">
                            <option value="All"><spring:message code="fieldhdr.all"/></option>
                            <c:forEach items="${statuslist}" var="status">
                                <c:choose>
                                    <c:when test="${filter.current_status eq status.key}">
                                        <option value="${status.key}" selected style="text-transform: lowercase;">${status.key}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${status.key}" style="text-transform: lowercase;">${status.key}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td><spring:message code="fieldhdr.lang"/>:</td>
                    <td>
                        <select name="planguage" style="text-transform: lowercase;">
                            <option value="All"><spring:message code="fieldhdr.all"/></option>
                            <c:forEach items="${filter.languages}" var="language">
                                <c:choose>
                                    <c:when test="${filter.language eq language.key}">
                                        <option value="${language.key}" selected style="text-transform: lowercase;">${language.language} (${language.descripcion})</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${language.key}" style="text-transform: lowercase;">${language.language} (${language.descripcion})</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input id="filter-button" type="submit" value="<spring:message code="button.filter"/>"/>
                    </td>
                </tr>

            </table>
        </form>    

        <div id="display-table-container" data-reload-url="/admin/tables/manageplagicoj.xhtml"></div>
        

        <!-- /article-content -->
    </div>
</div>
<div id="confirm" hidden=""  title="<fmt:message key="admin.plagicoj.areyousure"/>">
    <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span><span id="cantSub"></span> <fmt:message key="admin.plagicoj.areyousure.1"/> <span id="tiempo">5</span> <fmt:message key="admin.plagicoj.areyousure.2"/> </p>
</div>
<div id="checkerror" hidden="" title="<fmt:message key="admin.plagicoj.error"/>"><p><fmt:message key="admin.plagicoj.check"/></p></div>
<script>
	$(function() {
		$('#filter-button').click(function(event) {
			displayTableReload($('#filter-form').formSerialize());
			event.preventDefault();
		});
	});

	$(document).ready(displayTableReload($('#filter-form').formSerialize()));
</script>