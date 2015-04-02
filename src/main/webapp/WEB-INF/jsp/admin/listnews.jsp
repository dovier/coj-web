<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	: News
</h2>
<div class="postcontent">
	<br />
	<form action="listnews.xhtml" method="post">
		<table>
			<tr>
				<td>

					<div>
						<label>Search in title and overview:</label> <input type="text" size="25" name="pattern" value="${pattern}"> <input type="submit" value="<fmt:message key="page.24h.problemsearch.submit"/>">
					</div>
				</td>
			</tr>

		</table>
	</form>
	<c:choose>
		<c:when test="${search == true}">
			<label><fmt:message key="fieldhdr.totalfound" />: ${found}</label>
		</c:when>
	</c:choose>
	<br />
	<table class="navigating" style="align: center; width: 100%">
		<tr>
			<c:if test="${not empty nfirst.active}">
				<td style="align:right" width="15%"><c:choose>
						<c:when test="${nfirst.active==true}">
							<a href="listnews.xhtml?vid=1${get}"><i>&lt;&lt;</i></a>
							<a href="<c:url value="listnews.xhtml?vid=${nfirst.value}${get}"/>"><i><fmt:message key="page.navigating.previous" /></i></a>
						</c:when>
						<c:when test="${nfirst.active==false}">
							<fmt:message key="page.navigating.previous" />
						</c:when>
					</c:choose></td>
			</c:if>

			<c:forEach items="${links}" var="navigating">
				<td width="4%"><c:if test="${not empty navigating.active}">
						<c:choose>
							<c:when test="${navigating.active==true}">
								<a href="<c:url value="listnews.xhtml?vid=${navigating.value}${get}"/>"><c:out value="${navigating.value}" /></a>
							</c:when>
							<c:when test="${navigating.active==false}">
								<c:out value="${navigating.value}" />
							</c:when>
						</c:choose>
					</c:if></td>
			</c:forEach>
			<c:if test="${not empty nend.active}">
				<td style="align:left" width="15%"><c:choose>
						<c:when test="${nend.active==true}">
							<a href="<c:url value="listnews.xhtml?vid=${nend.value}${get}"/>"><i><fmt:message key="page.navigating.next" /></i></a>
							<a href="<c:url value="listnews.xhtml?vid=${end.value}${get}"/>"><i>&gt;&gt;</i></a>
						</c:when>
						<c:when test="${nend.active==false}">
							<fmt:message key="page.navigating.next" />
						</c:when>
					</c:choose></td>
			</c:if>
		</tr>
	</table>

	<a href="<c:url value="/admin/addnews.xhtml" />">Add News</a>

	<table class="volume" border="1px">
		<thead class="orderby">
			<th>ID</th>
			<th>rate</th>
			<th width="50%">Title</th>
			<th>Date</th>
			<th>Enabled</th>
			<th>Edit</th>
			<th>Delete</th>
		</thead>

		<c:forEach items="${news}" var="nws">
			<tr class="<c:out value="${nws.even}"/>">
				<td><c:out value="${nws.nid}" /></td>
				<td><c:out value="${nws.rate}" /></td>
				<td><a href="<c:url value="/general/newsview.xhtml?nid=${nws.nid}"/>">${nws.title}</a></td>
				<td><c:out value="${nws.date}" /></td>
				<td><c:choose>
						<c:when test="${nws.enabled == true}">
							<span class="label label-success"><fmt:message key="page.general.yes" /></span>
						</c:when>
						<c:otherwise>
							<span class="label label-danger"><fmt:message key="page.general.no" /></span>
						</c:otherwise>
					</c:choose></td>
				<td><a href="<c:url value="/admin/managenews.xhtml?nid=${nws.nid}"/>" title="<fmt:message key="messages.general.go"/>"><i class="fa fa-edit"></i></a></td>
				<td><a href="<c:url value="/admin/deletenews.xhtml?nid=${nws.nid}"/>" title="<fmt:message key="messages.general.go"/>"><i class="fa fa-trash"></i></a></td>
			</tr>
		</c:forEach>
	</table>
	<table class="navigating" style="align: center; width: 100%">
		<tr>
			<c:if test="${not empty nfirst.active}">
				<td style="align:right" width="15%"><c:choose>
						<c:when test="${nfirst.active==true}">
							<a href="listnews.xhtml?vid=1${get}"><i>&lt;&lt;</i></a>
							<a href="<c:url value="listnews.xhtml?vid=${nfirst.value}${get}"/>"><i><fmt:message key="page.navigating.previous" /></i></a>
						</c:when>
						<c:when test="${nfirst.active==false}">
							<fmt:message key="page.navigating.previous" />
						</c:when>
					</c:choose></td>
			</c:if>

			<c:forEach items="${links}" var="navigating">
				<td width="4%"><c:if test="${not empty navigating.active}">
						<c:choose>
							<c:when test="${navigating.active==true}">
								<a href="<c:url value="listnews.xhtml?vid=${navigating.value}${get}"/>"><c:out value="${navigating.value}" /></a>
							</c:when>
							<c:when test="${navigating.active==false}">
								<c:out value="${navigating.value}" />
							</c:when>
						</c:choose>
					</c:if></td>
			</c:forEach>
			<c:if test="${not empty nend.active}">
				<td style="align:left" width="15%"><c:choose>
						<c:when test="${nend.active==true}">
							<a href="<c:url value="listnews.xhtml?vid=${nend.value}${get}"/>"><i><fmt:message key="page.navigating.next" /></i></a>
							<a href="<c:url value="listnews.xhtml?vid=${end.value}${get}"/>"><i>&gt;&gt;</i></a>
						</c:when>
						<c:when test="${nend.active==false}">
							<fmt:message key="page.navigating.next" />
						</c:when>
					</c:choose></td>
			</c:if>
		</tr>
	</table>

</div>