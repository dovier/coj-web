<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>"/>${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.requestclarif"/>
</h2>
<div class="postcontent">
    <form:form action="clarification.xhtml?cid=${contest.cid}" method="post" commandName="clarification">
        <c:choose>
            <c:when test="${contest.running eq true}">                
                <table class="userinfo" style="width: 100%;">
                    <tr>
                        <td colspan="2"><span class="label label-danger"><form:errors path="general" /></span></td>
                    </tr>
                    <tr>
                        <td><spring:message code="fieldhdr.to"/>:</td>
                        <td><input value="<spring:message code="fieldval.judges"/>" readonly/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="fieldhdr.subject" />:</td>
                        <td>                            
                            <form:select path="pid">
                                <form:options items="${problems}" itemValue="pid" itemLabel="title"/>
                            </form:select>
                        </td>
                        <td><span class="label label-danger"><form:errors path="pid" /></span></td>
                    </tr>
                    <tr>
                        <td width="15%"></td>
                        <td style="width: 70%;">
                            <form:textarea path="description"/>
                        </td>
                        <td><span class="label label-danger"><form:errors path="description" /></span></td>
                    </tr>
                </table>
                <input type="submit" value="<spring:message code="button.send" />" />
                <input type="reset" value="<spring:message code="button.reset" />" />
            </c:when>
            <c:otherwise>
                <div class="error"><spring:message code="text.requestclarif.1"/></div>
            </c:otherwise>
        </c:choose>
    </form:form>
</div>
