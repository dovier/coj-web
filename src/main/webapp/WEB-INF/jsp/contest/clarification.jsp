<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>
<script type="text/javascript" src="<c:url value="/js/WYSIWYG/source.js" />"></script>

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
                        <td colspan="2"><span class="label label-danger"><form:errors path="general"/></span></td>
                    </tr>
                    <tr>
                        <td><spring:message code="fieldhdr.to"/>:</td>
                        <td><spring:message code="fieldval.judges"/></td>
                    </tr>
                    <tr>
                        <td><spring:message code="fieldhdr.subject"/>:</td>
                        <td>
                            <form:select path="pid" cssClass="form-control">
                                <form:options items="${problems}" itemValue="pid" itemLabel="title"/>
                            </form:select>
                        </td>
                        <td><span class="label label-danger"><form:errors path="pid"/></span></td>
                    </tr>
                    <tr>
                        <td width="15%"></td>
                        <td style="width: 70%;">
                            <form:textarea cssClass="form-control" path="description"/>
                        </td>
                        <td><a><i data-toggle="tooltip" class="fa fa-asterisk"
                                  title="<spring:message code="mandatory.field"/>"></i></a></td>
                        <td><span class="label label-danger"><form:errors path="description"/></span></td>
                    </tr>
                </table>
                <div class="clearfix"></div>
                <div class = "pull-right margin-top-05">
                    <input class="btn btn-primary" type="submit" value="<spring:message code="button.send" />"/>
                    <input class="btn btn-primary" type="reset" value="<spring:message code="button.reset" />"/>
                    <a class="btn btn-primary" href="<c:url value="/contest/myclarifications.xhtml?cid=${contest.cid}"/>"><spring:message code="button.close"/></a>
                </div>

            </c:when>
            <c:otherwise>
                <div class="error"><spring:message code="text.requestclarif.1"/></div>
            </c:otherwise>
        </c:choose>
    </form:form>
    <div class="clearfix"></div>
</div>
<script>
    $("[data-toggle='tooltip']").tooltip();
</script>