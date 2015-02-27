<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="submit" name="submits" class="volume" requestURI=""
	defaultsort="0" defaultorder="ascending">
	<display:column titleKey="tablehdr.prob" headerClass="headid">
		<span class="fa-stack fa-2x"> <span
			style="color:${submit.color}"> <i
				class="fa fa-circle fa-stack-2x"></i>
		</span> <small class="fa fa-stack-1x white shadow">${submit.letter} </small>
		</span>
	</display:column>
	<display:column titleKey="tablehdr.user" headerClass="headdate">
		<a
			href="<c:url value="cuseraccount.xhtml?uid=${submit.username}&cid=${contest.cid}"/>"><c:out
				value="${submit.userNick}" /></a>
	</display:column>
	<display:column property="ddate" titleKey="tablehdr.date"
		headerClass="headdate">
	</display:column>
</display:table>