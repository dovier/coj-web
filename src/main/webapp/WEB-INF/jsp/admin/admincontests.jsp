<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h2 class="postheader">
    <spring:message code="page.general.admin.header" />: <spring:message code="pagehdr.amcontests" />
</h2>
<div class="postcontent">
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>
    <div class="row">
        <div class="col-xs-12">
            <form action="/admin/tables/admincontests.xhtml" method="get" id="filter-form" class="form-inline">
                <div class="form-group coj_float_left">
                    <a class="btn btn-primary" href="<c:url value="/admin/createcontest.xhtml" />"><spring:message
                            code="link.aaddcontest" /></a>	
                </div>
                <div class="form-group coj_float_rigth">
                    <select class="form-control" name="access" title="<spring:message code="tablehdr.access" />" data-toggle="tooltip">
                        <option value="all"><spring:message code="fieldhdr.all" /></option>
                        <option value="2"><spring:message code="titval.public"/></option>
                        <option value="0"><spring:message code="titval.private"/></option>
                    </select>
                    <select class="form-control" name="enabled" title="<spring:message code="tablehdr.enabled" />" data-toggle="tooltip">
                        <option value="all"><spring:message code="fieldhdr.all" /></option>
                        <option value="true"><spring:message code="fieldhdr.enabled" /></option>
                        <option value="false"><spring:message code="fieldhdr.disabled" /></option>
                    </select>
                    <select class="form-control" name="status" title="<spring:message code="tablehdr.status" />" data-toggle="tooltip">
                        <option value="all"><spring:message code="fieldhdr.all" /></option>
                        <option value="coming"><spring:message code="fieldval.coming" /></option>
                        <option value="running"><spring:message code="fieldval.running" /></option>
                        <option value="past"><spring:message code="fieldval.past" /></option>
                    </select>
                    <input class="btn btn-primary" type="submit" id ="filter-button"
                           value="<spring:message code="button.filter"/>" />
                </div>                
            </form>
        </div>
    </div>	

    <br/>


    <div id="display-table-container" data-reload-url="/admin/tables/admincontests.xhtml"></div>
    <div class="coj_float_rigth">
        <a href="/admin/index.xhtml" class="btn btn-primary">
            <spring:message code="button.close" />
        </a>
    </div>
    <div class="clearfix"></div>
</div>

<script src="/js/admin/utility.js"></script>

<script>
    $(initStandardFilterForm);

    $("[data-toggle='tooltip']").tooltip();
</script>










