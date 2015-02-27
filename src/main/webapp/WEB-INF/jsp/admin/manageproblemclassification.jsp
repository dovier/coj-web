<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Styles -->
<link type="text/css" href="<c:url value="/css/ui/jquery.ui.min.css"/>"
	rel="stylesheet" />

<!-- Scripts -->
<script type="text/javascript" src="<c:url value="/js/coj.js" />"> </script>

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
	<fmt:message key="pclassifi.title" />
</h2>
<div class="postcontent">

	<form action="manageproblemclassification.xhtml">
		<label for="problemid"> <fmt:message
				key="pclassifi.msgproblemid" /> <input type="text" id="problemid"
			name="pid" />
		</label> <input type="submit"
			value="<fmt:message key="pclassifi.btnclassify"/>" />
	</form>

	<c:if test="${problemClassifications ne null}">
		<div class="sourceDropableList">
			<h4>
				<fmt:message key="pclassifi.classificationsheader" />
			</h4>
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
		<div class="destinationDropableList">
			<h4>
				<fmt:message key="pclassifi.problemclassheader.1" />
				<a href="/24h/problem.xhtml?pid=<%=request.getParameter("pid")%>"><%=request.getParameter("pid")%></a>
				<fmt:message key="pclassifi.problemclassheader.2" />
			</h4>
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
	</c:if>
	<div class="cleared"></div>
	<h4>
		<spring:message code="pclassifi.problemswithoutclassification" />
	</h4>
	<div id="display-table-container"
		data-reload-url="/admin/tables/manageproblemclassification.xhtml"></div>
</div>
<script>
$(document).ready(displayTableReload(" "));
</script>