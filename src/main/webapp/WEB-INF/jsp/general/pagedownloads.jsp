<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<ul class="pager">
		<c:if test="${files.pageNumber != 1}">
			<li class="previous"><a
				href="javascript:downloadsPage(${files.pageNumber - 1});">&larr;</a></li>
		</c:if>
		<c:if test="${files.pageNumber < files.totalPages}">
			<li class="next"><a
				href="javascript:downloadsPage(${files.pageNumber + 1});">&rarr;</a></li>
		</c:if>
	</ul>
	<c:forEach items="${files.list}" var="f" step="2" varStatus="loop">
		<div class="row postcontent"> 
			<div class="col-xs-offset-1 col-xs-5 panel panel-primary">
				<div class="col-xs-2 pull-left">
					<div class="fileicon text-primary fa-3x ext${files.list[loop.index].extension}"></div>
				</div>
				<div class="col-xs-10 filename">
					<a download
						href="<c:url value='/downloads/${files.list[loop.index].path}'/>">
						<c:out value="${files.list[loop.index].name}" />
					</a>
				</div>
			</div>
		<c:if test="${loop.index+1 < files.list.size()}">
			<div class="col-xs-5 margin-left-02 panel panel-primary">
				<div class="col-xs-2 pull-left">
					<div
						class="fileicon text-primary fa-3x ext${files.list[loop.index+1].extension}"></div>
				</div>
				<div class="col-xs-10 filename">
					<a download
						href="<c:url value='/downloads/${files.list[loop.index+1].path}'/>">
						<c:out value="${files.list[loop.index+1].name}" />
					</a>
				</div>
			</div>
		</c:if>
	</div>
	</c:forEach>