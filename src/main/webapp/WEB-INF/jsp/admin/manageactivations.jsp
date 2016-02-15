<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page buffer="16kb" autoFlush="true" %>

<script type="text/javascript"
        src="<c:url value="/js/WYSIWYG/source.js" />"></script>


<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>:
    <fmt:message key="page.general.admin.account.activation"/>

</h2>

<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>
    <div>
        <form id="filter-form" class="form-inline">
            <div class="form-group coj_float_rigth">
                <input type="text" class="form-control" name="pattern"
                       value="${pattern}" placeholder="<spring:message code="fieldhdr.searchactivations" />">
                <input id="filter-button" type="submit" class="btn btn-primary"
                       value="<spring:message code="button.filter"/>">
            </div>
        </form>
    </div>
    <!--<label><spring:message code="fieldhdr.totalfound" />: ${found}</label>-->
    <c:if test="${isactivation != null}">
        <div>
            <button id="resend" class="mybutton btn btn-primary " type="button">
                <fmt:message key="fieldhdr.resendactivations"/>
            </button>
        <span id="resendgood" class="hide label label-success notif"><spring:message
                code="text.good"/></span>
        </div>
    </c:if>
    <br/>
<div class="clearfix"></div>
    <div id="display-table-container"
         data-reload-url="/admin/tables/manageactivations.xhtml"></div>
</div>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css"/>
<script src="/js/bootstrap-dialog.min.js"></script>
<script src="/js/admin/utility.js"></script>

<script>
    $(function () {
        $("#resend").click(function (event) {
            $.post("/admin/resendactivations.json", function (event) {
                $("#resendgood").toggleClass("hide");
            });
            event.preventDefault();
        });
    });

    $(initStandardFilterForm);
</script>