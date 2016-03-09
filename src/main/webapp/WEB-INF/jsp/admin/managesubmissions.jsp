<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<link rel="stylesheet"
      href="<c:url value="/css/ui-1.11.2/jquery-ui-timepicker-addon.css"/>"
      type="text/css" media="screen"/>
<link rel="stylesheet"
      href="<c:url value="/css/ui-1.11.2/jquery-ui-1.11.2.min.css"/>"
      type="text/css" media="screen"/>
<h2 class="postheader">
    <spring:message code="page.general.admin.header"/>: <spring:message code="pagehdr.submissions"/>
</h2>

<div id="submis" class="alert alert-success" style="display:  none">

</div>
<c:if test="${message != null}">
    <div class="alert alert-success alert-dismissable fade in">
        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
        <i class="fa fa-check"></i><spring:message code="${message}"/>
    </div>
</c:if>
<div class=" row postcontent">
    <div class="col-xs-12">
        <form:form id="filter-form" name="filter-form" commandName="filter" action="/admin/rejudgesubmissions.xhtml"
                   method="post">
            <div class="row form-group">
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.user"/>
                    <form:input cssClass="form-control" path="username"
                                value="${filter.username}" size="10"/>
                </div>
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.startdate"/>
                    <form:input cssClass="form-control" path="startDate" id="startDate"
                                value="${filter.startDate}" size="10"/>
                </div>
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.enddate"/>
                    <form:input cssClass="form-control" path="endDate" id="endDate"
                                value="${filter.endDate}" size="10"/>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.cid"/>
                    <form:input cssClass="form-control" id="cid" path="cid" maxlength="9"
                                value="${filter.cid}" size="8"/>
                </div>
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.startSid"/>
                    <form:input cssClass="form-control" id="startSid" path="startSid" maxlength="9"
                                value="${filter.startSid}" size="8"/>
                </div>
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.endSid"/>
                    <form:input cssClass="form-control" id="endSid" path="endSid" maxlength="9"
                                value="${filter.endSid}" size="8"/>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.prob"/>
                    <form:input cssClass="form-control" id="pid" path="pid" maxlength="9"
                                value="${filter.pid}" size="8"/>
                </div>
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.judgment"/>
                    <form:select cssClass="form-control" path="status">
                        <form:option value="">
                            <spring:message code="fieldhdr.all"/>
                        </form:option>
                        <form:options items="${statuslist}" itemValue="key"
                                      itemLabel="key"></form:options>
                    </form:select>
                </div>
                <div class="col-xs-4">
                    <spring:message code="fieldhdr.lang"/>
                    <form:select cssClass="form-control" path="language" id="language">
                        <form:option value="">
                            <spring:message code="fieldhdr.all"/>
                        </form:option>
                        <form:options items="${languagelist}" itemValue="language"
                                      itemLabel="language"></form:options>
                    </form:select>
                </div>
            </div>
            <div class="row form-group">
                <div class="col-xs-12">
                    <div class="form-actions pull-right">
                        <input id="filter-button"
                               class="btn btn-primary" type="submit"
                               value="<spring:message code="button.filter"/>"
                               name="filter-button"/>
                        <input id="rejudge"
                               class="btn btn-primary" name="rejudge" type="submit"
                               value="<spring:message code="button.rejudge"/>"/>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
    <br/>

    <div class="col-xs-12">
        <div id="display-table-container"
             data-reload-url="/admin/tables/managesubmissions.xhtml"></div>
    </div>
    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close"/>
        </a>
    </div>
    <div class="clearfix"></div>
</div>

<script type="text/javascript"
        src="<c:url value="/js/ui-1.11.2/jquery-ui-timepicker-addon.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="/js/ui-1.11.2/jquery-ui-rangetimepicker-addon.js"/>"></script>
<script type="text/javascript"
        src="<c:url value="/js/ui-1.11.2/jquery-migrate-1.0.0.js"/>"></script>

<link href="/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css"/>
<script src="/js/bootstrap-dialog.min.js"></script>
<%--<script src="/js/admin/utility.js"></script>--%>

<script type="text/javascript">
    $(document).ready(function () {
        $('#cid').numeric();
        $('#pid').numeric();
        $('#startSid').numeric();
        $('#endSid').numeric();
    });
</script>

<script>
    var i18n = {};
    i18n.title = "<spring:message code="message.confirm.rejudge.title"/>";
    i18n.message = "<spring:message code="message.confirm.rejudge.body"/>";
    i18n.btn_cancel = "<spring:message code="btn.text.cancel"/>";
    i18n.btn_accept = "<spring:message code="btn.text.accept"/>";
    i18n.rejudge_succes = "<spring:message code="infomsg.rejudge.success"/>";

    function rejudge24h(sid) {
        $.ajax({
            type: "GET",
            url: "/admin/24h/rejudge.json",
            data: "sid=" + sid,
            dataType: 'text',
            success: function (data) {
                /*window.location = "/admin/managesubmissions.xhtml";*/

                var tmp = "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>"
                tmp += "<i class=\"fa fa-check\"></i>";
                tmp += "<spring:message code="infomsg.success"/>";

                $('#submis').html(tmp);
                $('#submis').show();

                $(".alert-success").fadeTo(2000, 500).slideUp(500, function () {
                    $(".alert-dismissable").alert('close');
                });
            }
        });
    }
    function rejudgeContest(sid) {
        $.ajax({
            type: "GET",
            url: "/admin/contest/rejudge.json",
            data: "sid=" + sid,
            dataType: 'text',
            success: function (data) {
                /*window.location = "/admin/managesubmissions.xhtml";*/
                var tmp = "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>"
                tmp += "<i class=\"fa fa-check\"></i>";
                tmp += "<spring:message code="infomsg.success"/>";

                $('#submis').html(tmp);
                $('#submis').show();

                $(".alert-success").fadeTo(2000, 500).slideUp(500, function () {
                    $(".alert-dismissable").alert('close');
                });
            }
        });
    }

    function toggle24h(sid) {
        $.ajax({
            type: "GET",
            url: "/admin/24h/togglesub.json",
            data: "sid=" + sid,
            dataType: 'text',
            success: function (data) {
            }
        });
    }
    function toggleContest(sid) {
        $.ajax({
            type: "GET",
            url: "/admin/contest/togglesub.json",
            data: "sid=" + sid,
            dataType: 'text',
            success: function (data) {
            }
        });
    }

    /*$('#rejudge').click(function () {
     $('#form').attr('action', '/admin/rejudgesubmissions.xhtml');
     $('#form').attr('method', 'post');
     });*/

    function count() {
        $.ajax({
            type: "GET",
            url: "/admin/queuecount.json",
            dataType: 'text',
            success: function (data) {
                $('#count').html(data);
            },
            complete: function (data) {

            }
        });

    }
    ;

    $(function () {
        setInterval(count, 2000);
    });

    $(initStandardFilterForm);
</script>

<script src="/js/admin/utility.js"></script>