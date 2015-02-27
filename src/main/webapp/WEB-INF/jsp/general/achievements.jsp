<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="postheader">
	<spring:message code="pagehdr.achievements" />
</h2>
<div class="postcontent">
	<c:forEach items="${achievements}" var="achievement" step="2"
		varStatus="loop">
		<div class="row postcontent">
			<div class="col-xs-5 panel panel-primary panel-achievements">
				<c:choose>
					<c:when test="${achievements[loop.index].level > 0}">
						<div class="no-gutters col-xs-3">
							<img class="shadow white achievement-image margin-top-05 margin-right-02"
								src='<c:out value="/images/achievements/${achievements[loop.index].id}.${achievements[loop.index].level}.png" />' />
							<div class="margin-top-05 margin-right-02 text-count-achievements">
								<small>
									${achievements[loop.index].count}
									<c:if test="${achievements[loop.index].count <= achievements[loop.index].maxCount}">
										/ ${achievements[loop.index].maxCount}	
									</c:if>
								</small>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="no-gutters col-xs-3">
							<img
								class="shadow achievement-image blur grayed margin-top-05 margin-right-02"
								src='<c:out value="/images/achievements/${achievements[loop.index].id}.1.png" />' />
							<div class="margin-top-05 text-count-achievements">
								<small>
									${achievements[loop.index].count}
									<c:if test="${achievements[loop.index].count <= achievements[loop.index].maxCount}">
										/ ${achievements[loop.index].maxCount}	
									</c:if>
								</small>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="col-xs-9 inner-panel-achievements">
					<b>${achievements[loop.index].name} Lv${achievements[loop.index].level + 1} </b>
					<p class="text-muted">
						<sub>${achievements[loop.index].condition}</sub>
					</p>
					<p>
						<small>${achievements[loop.index].description}</small>
					</p>

				</div>
				<div class="col-xs-12">
					<c:choose>
						<c:when test="${achievements[loop.index].count >= achievements[loop.index].maxCount}">
							<div class="progress progress-striped">
								<div class="progress-bar"
									style="width:100%"></div>
							</div>
						</c:when>
						<c:otherwise>
							<c:if test="${achievements[loop.index].progress <= 33}">
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-success"
										style="width:${achievements[loop.index].progress}%"></div>
								</div>
							</c:if>
							<c:if test="${achievements[loop.index].progress > 33 && achievements[loop.index].progress <= 66}">
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-warning"
										style="width:${achievements[loop.index].progress}%"></div>
								</div>
							</c:if>
							<c:if test="${achievements[loop.index].progress > 66}">
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-danger"
										style="width:${achievements[loop.index].progress}%"></div>
								</div>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<c:if test="${achievements[loop.index+1] ne null}">
				<div class="col-xs-5 col-xs-offset-1 panel panel-primary panel-achievements">
					<c:choose>
						<c:when test="${achievements[loop.index+1].level > 0}">
						<div class="no-gutters col-xs-3">
							<img class="shadow white achievement-image margin-top-05 margin-right-02"
								src='<c:out value="/images/achievements/${achievements[loop.index+1].id}.${achievements[loop.index+1].level}.png" />' />
							<div class="margin-top-05 margin-right-02 text-count-achievements">
								<small>
									${achievements[loop.index+1].count}
									<c:if test="${achievements[loop.index+1].count <= achievements[loop.index+1].maxCount}">
										/ ${achievements[loop.index+1].maxCount}	
									</c:if>
								</small>
							</div>
						</div>
					</c:when>
					<c:otherwise>
						<div class="no-gutters col-xs-3">
							<img
								class="shadow achievement-image blur grayed margin-top-05 margin-right-02"
								src='<c:out value="/images/achievements/${achievements[loop.index+1].id}.1.png" />' />
							<div class="margin-top-05 text-count-achievements">
								<small>
									${achievements[loop.index+1].count}
									<c:if test="${achievements[loop.index+1].count <= achievements[loop.index+1].maxCount}">
										/ ${achievements[loop.index+1].maxCount}	
									</c:if>
								</small>
							</div>
						</div>
					</c:otherwise>
					</c:choose>
					<div class="col-xs-9 inner-panel-achievements">
						<b>${achievements[loop.index+1].name} Lv${achievements[loop.index+1].level + 1}</b>
						<p class="text-muted">
							<sub>${achievements[loop.index+1].condition}</sub>
						</p>
						<p>
							<small>${achievements[loop.index+1].description}</small>
						</p>
						<c:if test="${!achievements[loop.index+1].maxedOut}">
						</c:if>
					</div>
					<div class="col-xs-12">
						<c:choose>
						<c:when test="${achievements[loop.index+1].count >= achievements[loop.index+1].maxCount}">
							<div class="progress progress-striped">
								<div class="progress-bar"
									style="width:100%"></div>
							</div>
						</c:when>
						<c:otherwise>
							<c:if test="${achievements[loop.index+1].progress <= 33}">
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-success"
										style="width:${achievements[loop.index+1].progress}%"></div>
								</div>
							</c:if>
							<c:if test="${achievements[loop.index+1].progress > 33 && achievements[loop.index+1].progress <= 66}">
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-warning"
										style="width:${achievements[loop.index+1].progress}%"></div>
								</div>
							</c:if>
							<c:if test="${achievements[loop.index+1].progress > 66}">
								<div class="progress progress-striped">
									<div class="progress-bar progress-bar-danger"
										style="width:${achievements[loop.index+1].progress}%"></div>
								</div>
							</c:if>
						</c:otherwise>
					</c:choose>
					</div>
				</div>
			</c:if>
		</div>
	</c:forEach>
</div>
