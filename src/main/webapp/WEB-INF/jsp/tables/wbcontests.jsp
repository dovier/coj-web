<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="contest" name="contests" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending"
	decorator="cu.uci.coj.board.util.WbContestTableDecorator">
	<display:column titleKey="tablehdr.name" class="headfirst-remaining"
		style="text-transform:none">
		<div class="textcontestblock">
			<a href="${contest.url}">${contest.name}</a>
			<div class="textbelow">
				<a href="${mapsites.get(contest.sid).url}">${mapsites.get(contest.sid).site}</a>
			</div>
		</div>
	</display:column>
	<display:column titleKey="tablehdr.timeremaining">
		<div class="countdown2"
			data-start-date="${contest.startDate.time + mapsites.get(contest.sid).offset}"
			data-end-date="${contest.endDate.time + mapsites.get(contest.sid).offset}"></div>
	</display:column>
	<display:column titleKey="tablehdr.status">
		<c:choose>
			<c:when test="${contest.startDate.time <= now.time}">
				<span class="label label-success"><spring:message
						code="contest.status.rn" /></span>
			</c:when>
			<c:otherwise>
				<span class="label label-danger"><spring:message
						code="contest.status.com" /></span>
			</c:otherwise>
		</c:choose>
	</display:column>
	<display:column titleKey="tablehdr.start" sortable="true"
		sortProperty="startdate" sortName="startdate" class="date"
		headerClass="headacc">
		<a
			href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.startDate.date}&month=${contest.startDate.month + 1}&year=${contest.startDate.year + 1900}&hour=${contest.startDate.hours}&min=${contest.startDate.minutes}&sec=${contest.startDate.seconds}&p1=${mapsites.get(contest.sid).timeanddateid}">
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
				value="${contest.startDate}" />
		</a>
	</display:column>

	<display:column titleKey="tablehdr.end" sortable="true"
		sortProperty="enddate" sortName="enddate" class="date"
		headerClass="headacc">
		<a
			href="http://timeanddate.com/worldclock/fixedtime.html?day=${contest.endDate.date}&month=${contest.endDate.month + 1}&year=${contest.endDate.year + 1900}&hour=${contest.endDate.hours}&min=${contest.endDate.minutes}&sec=${contest.endDate.seconds}&p1=${mapsites.get(contest.sid).timeanddateid}">
			<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
				value="${contest.endDate}" />
		</a>
	</display:column>
</display:table>

<script>
	var server_time = ${now.time};
	var time_access = (new Date().getTime());
	
	Countdown(server_time, time_access).countdown2
	setInterval(Countdown(server_time, time_access).countdown2, 1000);
</script>