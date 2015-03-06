<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

					<select id="site-select" class="form-control" name="site">
						<option value="0"><spring:message code="fieldhdr.all"/></option>
						<c:forEach items="${sites}" var="current_site">
							<option value="${current_site.sid}">${current_site.site}</option>
						</c:forEach>
					</select>

		<c:if test="${followed  == 0}">		
			<authz:authorize ifAllGranted="ROLE_USER">
				<c:forEach items="${sites}" var="current_site">
					<c:choose>
						<c:when test="${mapIds.get( current_site.sid )}">
							<button data-site-id="${current_site.sid}"
								 class="btn btn-danger hide btn-follow"><spring:message code="button.unfollow"/></button>
						</c:when>
						<c:otherwise>
							<button data-site-id="${current_site.sid}"
								 class="btn btn-primary hide btn-follow"><spring:message code="button.follow"/></button>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</authz:authorize>
		</c:if>
