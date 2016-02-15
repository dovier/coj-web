<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<!-- Styles -->
<link type="text/css" href="<c:url value="/css/plagicoj.css"/>" rel="stylesheet" />

<link type="text/css" href="<c:url value="/css/ui/jquery.ui.all.css"/>" rel="stylesheet" /> 
<!-- Scripts -->
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
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
    <spring:message code="page.general.admin.header" />
    : <spring:message code="plagicoj.title" />
</h2>
<div class="postcontent"> 
    <form action="/plagicoj/plagicojinspectionresult.xhtml" method="post" name="leandro">
        <div class="row">

            <div class="col-md-7">

                <h5><fmt:message key="admin.plagicoj.detectionparameters"/></h5>

                <div class="form-group form-inline">
                    <input class="form-control" placeholder="<fmt:message key="admin.plagicoj.submitid"/>" type="text" name="submitid1"> / <input class="form-control" type="text" name="submitid2">
                </div>
                <div class="form-group form-inline">
                    <input class="form-control" placeholder="<fmt:message key="admin.plagicoj.range"/>" type="text" name="sid1"> / <input class="form-control" type="text" name="sid2"></td>
                </div>

                <div class="form-group">
                    <input class="form-control" placeholder="<fmt:message key="admin.plagicoj.pivotsubmit"/>" type="text" name="submitid">
                </div>
                <div class="form-group">
                    <input class="form-control" placeholder="<fmt:message key="admin.plagicoj.pid"/>" type="text" name="pid"></td>
                </div>
            </div>

            <div class="col-md-5">

                <h5><fmt:message key="admin.plagicoj.filters"/></h5>

                <div class="form-group">
                    <input type="checkbox" id="onlyac" name="onlyac"/>
                    <fmt:message key="admin.plagicoj.onlyac"/>  
                </div>
                <div class="form-group">
                    <input type="checkbox" id="matchlanguage" name="matchlanguage"/>
                    <fmt:message key="admin.plagicoj.matchlanguage"/> 
                </div>
                <div class="form-group">
                    <input type="checkbox" id="ownsubmission" name="ownsubmission"/>
                    <fmt:message key="admin.plagicoj.ownsubmission"/>  
                </div>
                <div class="form-group">
                    <input type="checkbox" id="sameuser" name="sameuser"/>
                    <fmt:message key="admin.plagicoj.sameuser"/> 
                </div>                
            </div>

        </div>

        <div class="buttons nofloat">
            <input class="btn btn-primary" type="submit" value="<fmt:message key="admin.plagicoj.detect"/>" />
            <input class="btn btn-primary" type="reset" value="<fmt:message key="admin.plagicoj.reset"/>" />
            <a class="btn btn-primary" href="<c:url value="/admin/index.xhtml"/>"><spring:message
                    code="button.close"/></a>
        </div>  
    </form>    
    <div class="nofloat judgement">  
        <h4 >
            <spring:message code="pagehdr.24hjudgments"/>
        </h4>
        <form id="filter-form" class="form-inline">
            <div class="form-group coj_float_rigth">
                <input type="text" class="form-control" placeholder="<spring:message code="fieldhdr.user"/>" name="username" value="${filter.username}" size="10"/>    
                <input type="text" class="form-control" placeholder="<spring:message code="fieldhdr.prob"/>" name="abb" value="${filter.pid}" size="8"/>                

                <select name="status" class="form-control" style="text-transform: lowercase;">
                    <option value="judgment"><spring:message code="fieldhdr.judgment"/></option>
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

                <select class="form-control" name="planguage" style="text-transform: lowercase;">
                    <option value="lang"><spring:message code="fieldhdr.lang"/></option>
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

                <input class="btn btn-primary" id="filter-button" type="submit" value="<spring:message code="button.filter"/>"/>
            </div>            
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
    $(initStandardFilterForm);
</script>