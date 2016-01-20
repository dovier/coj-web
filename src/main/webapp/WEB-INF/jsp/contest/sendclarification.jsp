<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer = "16kb" autoFlush="true" %>
<script  type="text/javascript" src="<c:url value="/js/jquery.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script  type="text/javascript" src="<c:url value="/js/coj.js" />"></script>


<h2 class="postheader" style="clear: both">
    <a class="linkheader" href="<c:url value="contestview.xhtml?cid=${contest.cid}"/>">${contest.name}</a>
    <br/>
    <spring:message code="pagehdr.sendclarif"/>
</h2>
<div class="postcontent">
    <form:form action="sendclarification.xhtml?cid=${contest.cid}" method="post" commandName="clarification">


        <table class="userinfo" style="width: 100%;">
            <tr>
                <td colspan="2"><span class="label label-danger"><form:errors path="general" /></span></td>
            </tr>
            <tr>
            <tr>
                <td></td>
                <td><form:hidden path="id"/></td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.to" />:</td>
                <td><form:input cssClass="form-control" path="usernameto" size="40"/></td>
                <td><span class="label label-danger"><form:errors path="usernameto"/></span></td>
            </tr>
            <tr>                
                <td>                    
                </td>
                <td>
                    <form:checkbox path="forall" id="contestants"/>
                    <spring:message code="fieldhdr.contestants" />
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>
                    <input type="checkbox" name="judge" checked readonly disabled/>
                    <spring:message code="fieldhdr.judges" />
                </td>
            </tr>
            <tr>                
                <td>                  

                </td>
                <td>
                    <form:checkbox path="publics" onclick="selectContestants()" id="all"/>
                    <spring:message code="fieldhdr.all" />
                </td>
            </tr>
            <tr>
                <td><spring:message code="fieldhdr.subject" />:</td>
                <td>
                    <form:select cssClass="form-control" path="pid">
                        <form:options items="${problems}" itemValue="pid" itemLabel="title"/>
                    </form:select>
                </td>
                <td><span class="label label-danger"><form:errors path="pid" /></span></td>
            </tr>
            <tr>
                <td>
                    <spring:message code="fieldhdr.response"/>:
                </td>
                <td>
                    <select class="form-control" name="modes">
                        <option value="4">CUSTOMIZED</option>
                        <option value="1">NO COMMENTS</option>
                        <option value="2">YES</option>
                        <option value="3">NO</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td width="15%"></td>
                <td style="width: 70%;">
                    <form:textarea path="description"/>
                </td>
                <td><span class="label label-danger"><form:errors path="description" /></span></td>
            </tr>
            <c:if test="${not empty clarification.originalMSG}">
                <tr>
                    <td width="15%"><spring:message code="fieldhdr.originalmsg" />:</td>
                    <td style="width: 70%;">
                        <form:textarea readonly="true" path="originalMSG"/>
                    </td>                    
                </tr>
            </c:if>

        </table>
        <input class="btn btn-primary" type="submit" value="<spring:message code="button.send" />" />
        <input class="btn btn-primary" type="reset" value="<spring:message code="button.reset" />" />
    </form:form>
</div>
    