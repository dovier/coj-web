<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>

<h2 class="postheader">
	<fmt:message key="page.header.admin.overview" />
</h2>

<div class="postcontent">
	<ul class="nav nav-pills">
		<li><a
			href="<c:url value="managecontest.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mc" /></a></li>
		<li><a
			href="<c:url value="contestglobalflags.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gf" /></a></li>
		<li><a
			href="<c:url value="globalsettings.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.gs" /></a></li>
		<li><a
			href="<c:url value="contestproblems.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mp" /></a></li>
		<li><a
			href="<c:url value="contestproblemcolors.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mpc" /></a></li>
		<li><a
			href="<c:url value="importicpcusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.iiu" /></a></li>
		<li><a href="<c:url value="baylorxml.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.bx" /></a></li>
		<li><a
			href="<c:url value="contestlanguages.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.ml" /></a></li>
		<li><a
			href="<c:url value="contestusers.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.mu" /></a></li>
		<li><a
			href="<c:url value="contestoverview.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.ov" /></a></li>
		<li class="active"><a
			href="<c:url value="contestimg.xhtml?cid=${contest.cid}"/>"><fmt:message
					key="page.managecontest.link.img" /></a></li>
	</ul>
	<form:form method="post" command="contest"
		enctype="multipart/form-data">
		<input name="cid" class="hidden" value="${contest.cid}">
		<div class="form-group col-xs-12">
			<div class="form-group">
					<c:forEach items="${images}" varStatus="loop">
						<div id="${loop.index}-container" class="margin-top-05 col-xs-3">
							<div class="img-thumbnail">
								<span class="pull-right"> <a
									href="javascript:removeimage(${contest.cid},${loop.index},'${images[loop.index]}');">
										<i class="fa fa-close"></i>
								</a>
								</span> <img class="img-responsive"
									src="/images/${contest.cid}/${images[loop.index]}" />
							</div>
						</div>
					</c:forEach>
					<div id="images" class="form-group margin-top-05"></div>
					<div class="form-group col-xs-12">
						<div class="margin-top-05 pull-right form-actions">
							<input class="btn btn-primary" type="button"
								style="margin: right" id="add" onclick="javascript:addInput();"
								value='Add' />
						</div>
					</div>
			</div>
		</div>

		<br />
		<input type="submit" name="but"
			value="<spring:message code="button.update"/>" />
		<br />
	</form:form>
</div>

<script type="text/javascript">
	function addInput() {
		var idx = $(".model-image").length;
		var string = (idx > 1) ? "" : "New Images";
		var html = "<div class=\"model-image margin-top-05 col-xs-12\">"
				+ "<label class=\"control-label col-xs-3\">"
				+ string
				+ "</label>"
				+ "<div class=\"col-xs-4\"><input id = \"img"+idx + "\" name= \"img"+idx + "\" type=\"file\" class=\"image file\" data-show-upload=\"false\" /></div>"
				+ "</div>";
		$("div#images").append(html);
		$(".model-image:last .file").fileinput({
			maxFileSize : 5000000,
			allowedFileTypes : [ 'image' ],
			removeClass : "btn btn-default",
			removeLabel : "Delete",
			previewFileType : 'image',
			msgProgress : 'Loading {percent}%',
			browseClass : "btn btn-primary",

			browseLabel : "Pick",
			browseIcon : '<i class="fa fa-file"></i>&nbsp;',
			removeIcon : '<i class="fa fa-trash"></i>'
		});
	};

	$(function() {
		$(".dataset,.file").fileinput({
			maxFileSize : 500,
			allowedFileExtensions : [ 'png', 'jpg' ],
			removeClass : "btn btn-default",
			removeLabel : "Delete",
			previewFileType : 'image',
			msgProgress : 'Loading {percent}%',
			browseClass : "btn btn-primary",
			browseLabel : "Pick",
			browseIcon : '<i class="fa fa-file-o"></i>&nbsp;',
			removeIcon : '<i class="fa fa-trash"></i>'
		});

		addInput();
	});

	$("[data-toggle='tooltip']").tooltip();

	$(function() {
		$("[data-toggle='tooltip']").tooltip();
	});

	function removeimage(cid, containerId, image) {
		$.ajax({
			type : "GET",
			url : "/admin/removeimage.json",
			data : {
				"cid" : cid,
				"image" : image
			},
			dataType : 'text',
			success : function(data) {

				$('#' + containerId + '-container').remove();
			}
		});

	};
</script>