<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:choose>
    <c:when test="${notid}">
        <div class="error col-xs-12 col-md-offset-6">
            <span class="label label-danger"><spring:message code="errormsg.25"/></span>
        </div>
    </c:when>
    <c:when test="${notint}">
        <div class="error col-xs-12 col-md-offset-3">
            <span class="label label-danger"><spring:message code="errormsg.57"/></span>
        </div>
    </c:when>
</c:choose>

<display:table id="submission" name="submissions" class="volume"
               requestURI="" defaultsort="0" defaultorder="ascending">
    <display:column property="sid" titleKey="tablehdr.id"
                    headerClass="headid" href="/24h/submission.xhtml" paramId="id"
                    paramProperty="sid" />
     <display:column titleKey="tablehdr.date"  headerClass="headdate">
        <c:set value="${submission.date}" var="dateString" />
        <fmt:parseDate value="${dateString}" var="dateObject"
                        pattern="yyyy-MM-dd HH:mm:ss" />
        <fmt:formatDate value="${dateObject }" pattern="yyyy-MM-dd HH:mm:ss" />
    </display:column>
    <display:column property="username" titleKey="tablehdr.user"
                    headerClass="headuser" href="/user/useraccount.xhtml" paramId="username"
                    paramProperty="username" style="text-transform:none" />
    <display:column property="pid" titleKey="tablehdr.prob"
                    headerClass="headprob" href="/24h/problem.xhtml" paramId="pid"
                    paramProperty="pid" />
    <display:column titleKey="tablehdr.judgment" headerClass="headjudgment">
        <c:if test="${!empty submission.statusName}">
            <c:choose>
                <c:when test="${submission.authorize==false}">
                    <label class="sub${submission.statusClass}">${submission.status}</label>
                </c:when>
                <c:when test="${submission.authorize==true}">
                    <a href="/24h/compileinfo.xhtml?sid=${submission.sid}">${submission.status}</a>
                </c:when>
            </c:choose>
        </c:if>
        <c:if test="${empty submission.statusName}">
            <label class="sub${submission.statusClass}">${submission.status}</label>
        </c:if>
    </display:column>
    <display:column titleKey="tablehdr.time" headerClass="headtime">
        <c:if test="${submission.timeUsed == -1}">
            ...
        </c:if>
        <c:if test="${submission.timeUsed != -1}">
            ${submission.timeUsed}
        </c:if>
    </display:column>
    <display:column property="memoryMB" titleKey="tablehdr.mem"
                    headerClass="headmem" />
    <display:column property="fontMB" titleKey="tablehdr.size"
                    headerClass="headsize" />
    <display:column property="lang" titleKey="tablehdr.lang"
                    headerClass="headlang" />
    <display:column titleKey="tablehdr.enabled">
        <c:choose>
            <c:when test="${submission.enabled == true}">
                <span class="label label-success label-${submission.sid}"><fmt:message
                        key="page.general.yes" /></span>
                </c:when>
                <c:otherwise>
                <span class="label label-danger label-${submission.sid}"><fmt:message
                        key="page.general.no" /></span>
                </c:otherwise>
            </c:choose>
        </display:column>
    <display:column titleKey="tablehdr.actions" headerClass="headrejudge">
        <a
                href="<c:url value="managesubmission.xhtml?sid=${submission.sid}"/>"
                title="<fmt:message key="messages.general.edit"/>" data-toggle="tooltip"><i
                class="fa fa-edit"></i></a>
        <c:if test="${submission.cid == 0}">
			<a title="<spring:message code="tablehdr.rejudge"/>"
				href="<c:url value="javascript:rejudge24h(${submission.sid})"/>" data-toggle="tooltip"><i
				class="fa fa-repeat"></i></a>&nbsp;

            <c:choose>
                <c:when test="${submission.enabled}">
                    <a class="toogle24h" title="<spring:message  code="tablehdr.todisable"/>"
                    data-value="${submission.sid}" data-toggle="tooltip">
                    <i class="fa fa-eye-slash"></i>
                </c:when>
                <c:otherwise>
                    <a class="toogle24h" title="<spring:message  code="tablehdr.toenable"/>"
                    data-value="${submission.sid}" data-toggle="tooltip">
                    <i class="fa fa-eye"></i>
                </c:otherwise>
            </c:choose>
            <%--<a class="toogle24h" title="<spring:message  code="tablehdr.toenable"/>"
               data-value="${submission.sid}" data-toggle="tooltip">
                <c:if test="${submission.enabled}">
                    <i class="fa fa-eye-slash"></i>
                </c:if> <c:if test="${not submission.enabled}">
					<i class="fa fa-eye"></i>
                </c:if>
            </a>--%>
        </c:if>
        <c:if test="${submission.cid!=0 }">
            <a title="<spring:message code="tablehdr.rejudge"/>"
               href="<c:url value="javascript:rejudgeContest(${submission.sid})"/>" data-toggle="tooltip"><i
                    class="fa fa-repeat"></i></a>&nbsp;

            <c:choose>
                <c:when test="${submission.enabled}">
                    <a class="toogle24h" title="<spring:message  code="tablehdr.todisable"/>"
                    href="<c:url value="javascript:toggleContest(${submission.sid})"/>" data-toggle="tooltip">
                    <i class="fa fa-eye-slash"></i>
                </c:when>
                <c:otherwise>
                    <a class="toogle24h" title="<spring:message  code="tablehdr.toenable"/>"
                    data-value="${submission.sid}" data-toggle="tooltip">
                    <i class="fa fa-eye"></i>
                </c:otherwise>
            </c:choose>
            <%--<a title="<spring:message code="tablehdr.toenable"/>"
               href="<c:url value="javascript:toggleContest(${submission.sid})"/>" data-toggle="tooltip">
                <c:if test="${submission.enabled}">
                    <i class="fa fa-eye-slash"></i>
                </c:if> <c:if test="${not submission.enabled}">
                    <i class="fa fa-eye"></i>
                </c:if>
            </a>--%>
        </c:if>
    </display:column>
</display:table>

<script>

    var i18nlocal = {};
    i18nlocal.toenable = "<spring:message code="tablehdr.toenable"/>";
    i18nlocal.todisable = "<spring:message code="tablehdr.todisable"/>";
    i18nlocal.todisable = "<spring:message code="tablehdr.todisable"/>";
    i18nlocal.yes = "<spring:message code="page.general.yes"/>";
    i18nlocal.no = "<spring:message code="page.general.no"/>";

    $("[data-toggle='tooltip']").tooltip();

    $(".toogle24h").click(function () {
        var sid = $(this).attr("data-value");
        var _this = this;
        $.ajax({
            type: "GET",
            url: "/admin/24h/togglesub.json",
            data: "sid=" + sid,
            dataType: 'text',
            success: function (data) {
                var remove_class = "";
                var add_class = "";
                var tooltip = "";
                var add_class_otro = "";
                var remove_class_otro = "";
                var text_enable_disabled = "";
                if (data == "false") {
                    remove_class = "fa-eye-slash";
                    add_class = "fa-eye";
                    tooltip = i18nlocal.toenable;
                    add_class_otro = "label-danger";
                    remove_class_otro = "label-success";
                    text_enable_disabled = i18nlocal.no;
                } else {
                    remove_class = "fa-eye";
                    add_class = "fa-eye-slash";
                    tooltip = i18nlocal.todisable;
                    add_class_otro = "label-success";
                    remove_class_otro = "label-danger";
                    text_enable_disabled = i18nlocal.yes;
                }

                $("i", $(_this)).addClass(add_class).removeClass(remove_class);
                $(_this).attr("data-original-title", tooltip);
                $(".label-"+sid).addClass(add_class_otro).removeClass(remove_class_otro);
                $(".label-"+sid).html(text_enable_disabled);
            }
        });
    });
</script>
