<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Styles -->
<link type="text/css" href="<c:url value="/css/ui/jquery.ui.min.css"/>"
      rel="stylesheet" />

<!-- Scripts -->
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<script type="text/javascript"
src="<c:url value="/js/ui/jquery.ui.core.min.js" />"></script>
<script type="text/javascript"
src="<c:url value="/js/ui/jquery.ui.widget.min.js" />"></script>
<script type="text/javascript"
src="<c:url value="/js/ui/jquery.ui.mouse.min.js" />"></script>
<script type="text/javascript"
src="<c:url value="/js/ui/jquery.ui.sortable.min.js" />"></script>

<script>
    $(function() {
        loadProblemClassification('<%=request.getParameter("pid")%>');
    });
</script>

<h2 class="postheader">
    <spring:message code="page.general.admin.header" />
    : <spring:message code="tableval.probclassif" />
</h2>
<div class="col-md-12">
    <form:form action="manageproblemclassification.xhtml" cssClass="form-inline">
        <div class="form-group coj_float_rigth">
            <!--            <label class="control-label" for="problemid"> <fmt:message
                key="pclassifi.msgproblemid" /> 
        </label>-->
            <input class="form-control" type="text" id="problemid"
                   name="pid" placeholder="<fmt:message key="pclassifi.msgproblemid" />"/>
            <input id="filter-button" type="submit" class="btn btn-primary"
                   value="<spring:message code="pclassifi.btnclassify"/>" />                       
        </div>
    </form:form>
</div>
<div class="clearfix"></div>
<div class="postcontent">


    <c:if test="${problemClassifications ne null}">
        <div class="row">
            <div class="sourceDropableList col-md-6">
                <label>
                    <fmt:message key="pclassifi.classificationsheader" />
                </label>
                <ul id="classifications" class='dropable'>
                    <c:forEach items="${classifications}" var="classification">
                        <li id="${classification.idClassification}"
                            class="ui-state-default">${classification.name}<br /> <input
                                type="radio" value="1"
                                name="class${classification.idClassification}"> <input
                                type="radio" value="2"
                                name="class${classification.idClassification}"> <input
                                type="radio" value="3" checked
                                name="class${classification.idClassification}"> <input
                                type="radio" value="4"
                                name="class${classification.idClassification}"> <input
                                type="radio" value="5"
                                name="class${classification.idClassification}">
                        </li>

                    </c:forEach>
                </ul>
            </div>
            <div class="destinationDropableList col-md-6">

                <label>    <fmt:message key="pclassifi.problemclassheader.1" /></label>
                <a href="/24h/problem.xhtml?pid=<%=request.getParameter("pid")%>"><%=request.getParameter("pid")%></a>
                <label><fmt:message key="pclassifi.problemclassheader.2" /></label>

                <ul id="problemClassifications" class='dropable'>
                    <c:forEach items="${problemClassifications}"
                               var="problemClassification">
                        <li id="${problemClassification.idClassification}"
                            class="ui-state-default">${problemClassification.name}<br />
                            <input type="radio" value="1" disabled="true"
                                   <c:if test="${problemClassification.complexity eq 1}" >checked="true"</c:if>
                                   name="class${problemClassification.idClassification}"> <input
                                   type="radio" value="2" disabled="true"
                                   <c:if test="${problemClassification.complexity eq 2}" >checked="true"</c:if>
                                   name="class${problemClassification.idClassification}"> <input
                                   type="radio" value="3" disabled="true"
                                   <c:if test="${problemClassification.complexity eq 3}" >checked="true"</c:if>
                                   name="class${problemClassification.idClassification}"> <input
                                   type="radio" value="4" disabled="true"
                                   <c:if test="${problemClassification.complexity eq 4}" >checked="true"</c:if>
                                   name="class${problemClassification.idClassification}"> <input
                                   type="radio" value="5" disabled="true"
                                   <c:if test="${problemClassification.complexity eq 5}" >checked="true"</c:if>
                                   name="class${problemClassification.idClassification}">
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

    </c:if>
    <div class="cleared"></div>
    <!--    <h4>
    <spring:message code="pclassifi.problemswithoutclassification" />
</h4>-->
    <div id="display-table-container"
         data-reload-url="/admin/tables/manageproblemclassification.xhtml"></div>
</div>
<script>
    $(document).ready(displayTableReload(" "));
</script>