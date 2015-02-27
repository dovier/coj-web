<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<display:table id="faq" name="faqs" class="volume" requestURI=""
	decorator="cu.uci.coj.utils.tabledecorator.problemsTableDecorator">
	<display:column property="id" titleKey="tablehdr.id"
		headerClass="headid" />
	<display:column property="question" titleKey="tablehdr.question"
		paramId="question" paramProperty="question" />
	<display:column titleKey="tablehdr.edit">
		<a href="<c:url value="/admin/addfaq.xhtml?id=${faq.id}"/>"> <i
			class="fa fa-edit"></i></a>
	</display:column>
	<display:column titleKey="tablehdr.delete">
		<a href="<c:url value="/admin/deletefaq.xhtml?id=${faq.id}"/>"> <i
			class="fa fa-trash"></i>
		</a>
	</display:column>
</display:table>