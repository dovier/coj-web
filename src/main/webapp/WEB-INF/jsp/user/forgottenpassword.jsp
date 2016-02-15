<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>


<h2 class="postheader">
    <spring:message code="pagehdr.rupassword"/>
</h2>

<div class="postcontent container">
    <div class="row">
        <div class="col-xs-6">
            <%--Mensaje después de operación del correo--%>
            <c:if test="${message != null}">
                <c:choose>
                    <c:when test="${errorcreate != null}">
                        <div class="alert alert-danger alert-dismissable fade in">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-check"></i><spring:message code="${message}"/>
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="alert alert-success alert-dismissable fade in">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <i class="fa fa-check"></i><spring:message code="${message}"/>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <form:form method="post" commandName="user"
                       cssClass="form-horizontal">

                <c:if test="${showpassword != true}">
                    <div class="form-group">
                        <spring:message code="text.rupassword.1"/>
                    </div>
                </c:if>

                <div class="form-group">
                    <spring:message code="fieldhdr.email" var="fieldhdrEmail"/>
                    <div class="col-xs-7">
                        <form:input path="email" cssClass="form-control"
                                    placeholder="${fieldhdrEmail}" readonly="${showpassword == true}"/>
                    </div>
                    <div class="error col-xs-7">
                        <span class="label label-danger"><form:errors path="email"/></span>
                    </div>
                    <a><i data-toggle="tooltip" class="fa fa-asterisk"
                          title="<spring:message code="mandatory.field"/>"></i></a>
                </div>

                <c:if test="${showpassword == true}">
                    <div class="form-group">
                        <spring:message code="fieldhdr.npassword" var="newPassword"/>
                        <div class="col-xs-7">
                            <form:password path="password" cssClass="form-control" placeholder="${newPassword}"
                                           maxlength="100"/>
                        </div>
                        <div class="error col-xs-7">
                            <span class="label label-danger"><form:errors path="password"/></span>
                        </div>
                        <a><i data-toggle="tooltip" class="fa fa-info-circle"
                              title="<spring:message code="infomsg.8"/>"></i></a>
                    </div>

                    <div class="form-group">
                        <spring:message code="fieldhdr.cpassword" var="confirmPassword"/>
                        <div class="col-xs-7">
                            <form:password path="confirmPassword" cssClass="form-control"
                                           placeholder="${confirmPassword}" maxlength="100"/>
                        </div>
                        <div class="error col-xs-7">
                            <span class="label label-danger"><form:errors path="confirmPassword"/></span>
                        </div>
                        <a><i data-toggle="tooltip" class="fa fa-info-circle"
                              title="<spring:message code="infomsg.8"/>"></i></a>
                    </div>

                </c:if>

                <input type="submit" name="submit" id="submit" class="btn btn-primary"
                       value="<spring:message code="button.recover"/>"/>
                <input type="reset" class="btn btn-primary" value="<spring:message code="button.reset"/>"/>

            </form:form>
        </div>
    </div>

</div>
<script>
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
    });
</script>
