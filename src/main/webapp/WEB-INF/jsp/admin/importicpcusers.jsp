<%@include file="/WEB-INF/jsp/include/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<link rel='stylesheet' href="<c:url value="/css/spectrum.css" />"/>
<!--<script type="text/javascript" src="<c:url value="/js/jquery.js"/>"></script>-->
<script type="text/javascript" src="<c:url value="/js/spectrum.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>

<h2 class="postheader">
    <fmt:message key="page.general.admin.header"/>: <fmt:message key="page.managecontest.link.iiu"/>
</h2>

<div class="postcontent">

    <ul class="nav nav-pills">
        <li><a href="<c:url value="managecontest.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.mc"/></a></li>
        <li><a
                href="<c:url value="contestglobalflags.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.gf"/></a></li>
        <li><a href="<c:url value="globalsettings.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.gs"/></a></li>
        <li><a href="<c:url value="contestproblems.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.mp"/></a></li>
        <c:if test="${eproblem}">
            <li><a
                    href="<c:url value="contestproblemcolors.xhtml?cid=${cid}"/>"><fmt:message
                    key="page.managecontest.link.mpc"/></a></li>
        </c:if>
        <li class="active"><a
                href="<c:url value="importicpcusers.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.iiu"/></a></li>
        <li><a href="<c:url value="baylorxml.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.bx"/></a></li>
        <li><a href="<c:url value="contestlanguages.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.ml"/></a></li>
        <li><a href="<c:url value="contestusers.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.mu"/></a></li>
        <li><a href="<c:url value="contestawards.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.aw"/></a></li>
        <li><a href="<c:url value="contestoverview.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.ov"/></a></li>
        <li><a
                href="<c:url value="contestimg.xhtml?cid=${cid}"/>"><fmt:message
                key="page.managecontest.link.img"/></a></li>
        <li><a
                href="<c:url value="downloadsourcezip.xhtml?cid=${contest.cid}"/>"><fmt:message
                key="page.managecontest.link.download.sources"/></a></li>
    </ul>
    <br/>
    <c:if test="${message != null}">
        <div class="alert alert-success alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-check"></i><spring:message code="${message}"/>
        </div>
    </c:if>
    <c:if test="${messagerror != null}">
        <div class="alert alert-danger alert-dismissable fade in">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <i class="fa fa-warning"></i><spring:message code="${messagerror}"/>
        </div>
    </c:if>
    <form:form method="post" enctype="multipart/form-data" cssClass="form-horizontal"
               commandName="contest">

        <input type='hidden' name='cid' value="${cid}"/>

        <p>
            <fmt:message key="page.importicpusers.info"/>
        </p>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <fmt:message key="page.importicpusers.teamnameprefix"/>
            </label>

            <div class="col-xs-5">
                <input type='text' name='prefix' value="" class="form-control"/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <fmt:message key="page.importicpusers.warmupcontestid"/>
            </label>

            <div class="col-xs-5">
                <input id="warmupCid" type='text' name='warmupCid' value="" class="form-control"/>
            </div>
            <a><i
                    data-toggle="tooltip" class="fa fa-asterisk"
                    title="<spring:message code="mandatory.field"/>">
            </i></a>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <i>Person.tab</i>
            </label>

            <div class="col-xs-5">
                <input type="file" id="fileper" data-show-upload="false"
                       data-show-caption="true" name="personFile"/>
            </div>
            <a><i
                    data-toggle="tooltip" class="fa fa-asterisk"
                    title="<spring:message code="mandatory.field"/>">
            </i></a>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <i>School.tab</i>
            </label>

            <div class="col-xs-5">
                <input type="file" id="filesch" data-show-upload="false" data-show-caption="true"
                       name="schoolFile" class="file"/>
            </div>
            <a><i
                    data-toggle="tooltip" class="fa fa-asterisk"
                    title="<spring:message code="mandatory.field"/>">
            </i></a>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <i>Site.tab</i>
            </label>

            <div class="col-xs-5">
                <input type="file" id="filesit" data-show-upload="false" data-show-caption="true"
                       name="siteFile" class="file"/>
            </div>
            <a><i
                    data-toggle="tooltip" class="fa fa-asterisk"
                    title="<spring:message code="mandatory.field"/>">
            </i></a>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <i>Team.tab</i>
            </label>

            <div class="col-xs-5">
                <input type="file" id="filetea" data-show-upload="false" data-show-caption="true"
                       name="teamFile" class="file"/>
            </div>
            <a><i
                    data-toggle="tooltip" class="fa fa-asterisk"
                    title="<spring:message code="mandatory.field"/>">
            </i></a>
        </div>

        <div class="form-group">
            <label class="control-label col-xs-4">
                <i>TeamPerson.tab</i>
            </label>

            <div class="col-xs-5">
                <input type="file" id="filetep" data-show-upload="false" data-show-caption="true"
                       name="teamPersonFile" class="file"/>
            </div>
            <a><i
                    data-toggle="tooltip" class="fa fa-asterisk"
                    title="<spring:message code="mandatory.field"/>">
            </i></a>
        </div>
        <div class="form-actions pull-right">
            <input type="submit" name="but" class="btn btn-primary"
                   value="<spring:message code="button.edit"/>"/>
            <a class="btn btn-primary" href="<c:url value="/admin/admincontests.xhtml"/>"><spring:message
                    code="button.close"/></a>
        </div>
    </form:form>
    <div class="clearfix"></div>
</div>
<script>
    $("#fileper").fileinput({
        maxFileSize: 51200,
        msgProgress: 'Loading {percent}%',
        showPreview: false,
        /*previewClass: 'file_preview',
        previewFileType: "file",*/
        browseClass: "btn btn-primary",
        browseLabel: "<spring:message code="message.filename"/>",
        browseIcon: '<i class="fa fa-file-archive-o"></i>&nbsp;',
        removeClass: "btn btn-default",
        removeLabel: '<spring:message code="tablehdr.delete"/>',
        removeIcon: '<i class="fa fa-trash"></i>',
        msgSizeTooLarge: '<spring:message code="message.filename.largeerror"/>',
        msgValidationError: '<spring:message code="message.files.msgvalidationerror"/>',
        /*allowedFileExtensions:['.tab'],
        msgInvalidFileExtension: '<spring:message code="message.filename.msgInvalidFileExtension"/>'*/
    });

    $("#filesch").fileinput({
        maxFileSize: 51200,
        msgProgress: 'Loading {percent}%',
        showPreview: false,
        /*previewClass: 'file_preview',
        previewFileType: "file",*/
        browseClass: "btn btn-primary",
        browseLabel: "<spring:message code="message.filename"/>",
        browseIcon: '<i class="fa fa-file-archive-o"></i>&nbsp;',
        removeClass: "btn btn-default",
        removeLabel: "<spring:message code="tablehdr.delete"/>",
        removeIcon: '<i class="fa fa-trash"></i>',
        msgSizeTooLarge: '<spring:message code="message.filename.largeerror"/>',
        msgValidationError: "<spring:message code="message.files.msgvalidationerror"/>",
      /*  allowedFileExtensions:['.tab'],
        msgInvalidFileExtension: '<spring:message code="message.filename.msgInvalidFileExtension"/>'*/
    });

    $("#filesit").fileinput({
        maxFileSize: 51200,
        msgProgress: 'Loading {percent}%',
        showPreview: false,
        /*previewClass: 'file_preview',
        previewFileType: "file",*/
        browseClass: "btn btn-primary",
        browseLabel: "<spring:message code="message.filename"/>",
        browseIcon: '<i class="fa fa-file-archive-o"></i>&nbsp;',
        removeClass: "btn btn-default",
        removeLabel: "<spring:message code="tablehdr.delete"/>",
        removeIcon: '<i class="fa fa-trash"></i>',
        msgSizeTooLarge: '<spring:message code="message.filename.largeerror"/>',
        msgValidationError: "<spring:message code="message.files.msgvalidationerror"/>",
     /*   allowedFileExtensions:['.tab'],
        msgInvalidFileExtension: '<spring:message code="message.filename.msgInvalidFileExtension"/>'*/
    });

    $("#filetea").fileinput({
        maxFileSize: 51200,
        msgProgress: 'Loading {percent}%',
       showPreview: false,
        /*previewClass: 'file_preview',
        previewFileType: "file",*/
        browseClass: "btn btn-primary",
        browseLabel: "<spring:message code="message.filename"/>",
        browseIcon: '<i class="fa fa-file-archive-o"></i>&nbsp;',
        removeClass: "btn btn-default",
        removeLabel: "<spring:message code="tablehdr.delete"/>",
        removeIcon: '<i class="fa fa-trash"></i>',
        msgSizeTooLarge: '<spring:message code="message.filename.largeerror"/>',
        msgValidationError: "<spring:message code="message.files.msgvalidationerror"/>",
       /* allowedFileExtensions:['.tab'],
        msgInvalidFileExtension: '<spring:message code="message.filename.msgInvalidFileExtension"/>'*/
    });

    $("#filetep").fileinput({
        maxFileSize: 51200,
        msgProgress: 'Loading {percent}%',
        showPreview: false,
       /* previewClass: 'file_preview',
        previewFileType: "file",*/
        browseClass: "btn btn-primary",
        browseLabel: "<spring:message code="message.filename"/>",
        browseIcon: '<i class="fa fa-file-archive-o"></i>&nbsp;',
        removeClass: "btn btn-default",
        removeLabel: "<spring:message code="tablehdr.delete"/>",
        removeIcon: '<i class="fa fa-trash"></i>',
        msgSizeTooLarge: '<spring:message code="message.filename.largeerror"/>',
        msgValidationError: "<spring:message code="message.files.msgvalidationerror"/>",
      /*  allowedFileExtensions:['.tab'],
        msgInvalidFileExtension: '<spring:message code="message.filename.msgInvalidFileExtension"/>'*/
    });


    $("[data-toggle='tooltip']").tooltip();
</script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#warmupCid').numeric();
    });
</script>

<script src="/js/admin/utility.js"></script>