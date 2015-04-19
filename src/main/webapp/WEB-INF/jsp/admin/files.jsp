<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--link rel="stylesheet" href="../Style/style.css" type="text/css" media="screen" /-->
<script type="text/javascript"
	src="<c:url value="/js/WYSIWYG/source.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/coj.js" />"></script>
<h2 class="postheader">
	<fmt:message key="page.general.admin.header" />
	:
	<fmt:message key="page.menu.admin.upload" />
</h2>
<div class="row">
	<div class="col-xs-offset-1 col-xs-11">
		<c:if test="${downloadable}">
			<span class="label label-warning">&nbsp;<i
				class="fa fa-info-circle"></i>&nbsp;<spring:message
					code="public.files" /></span>
		</c:if>
	</div>
</div>
<div class="row margin-top-05">
	<div class="col-xs-offset-1 col-xs-10">
		<form:form method="post" enctype="multipart/form-data"
			cssClass="form-horizontal" commandName="file">
			<div class="row panel panel-primary">
				<div class="label label-primary col-xs-12">
					<i class="pull-left fa fa-folder-open-o fa-lg"></i>ROOT${currentDir}
				</div>
				<c:if test="${back}">
					<div class="col-xs-12">
						<a href="/admin/files/list.xhtml?b=true"><i
							class="fa fa-level-up fa-lg"></i></a>
					</div>
				</c:if>
				<c:forEach items="${files}" var="file" varStatus="loop">
					<div class="col-xs-12 hover">
						<c:if test="${file.directory}">
							<input type="checkbox" class="checkbox-inline" name="files"
								value="${file.name}" />
							<a href="/admin/files/list.xhtml?f=<c:out value="${file.name}"/>"><i
								class="fa fa-folder-o"></i>&nbsp;<c:out value="${file.name}" /></a>
								&nbsp;
							</c:if>
						<c:if test="${not file.directory}">
							<input id="${file.name}" type="checkbox" class="checkbox-inline"
								name="files" value="${file.name}" />
							<a download
								href="/admin/files/download.xhtml?f=<c:out value="${file.name}"/>"><i
								class="fa fa-file-o"></i>&nbsp;${file.name}</a>
								${coj:fileSizeDisplay(file.length())}
							</c:if>
						<div class="pull-right">
						<c:if test="${not file.directory and downloadable}">
							<c:if test="${downloadables[loop.index]}">
								<a href="#"  data-filename="${file.name}" title="Unshare ${file.name}"><i id="${file.name}"
									class="fa fa-share-alt-square"></i></a>&nbsp;
							</c:if>
							<c:if test="${not downloadables[loop.index]}">
								<a  href="#" data-filename="${file.name}" title="Share ${file.name}"><i id="share${file.name}"
									class="fa fa-share-alt"></i></a>&nbsp;
							</c:if>
						</c:if>
							<a title="Cut ${file.name}"
								href="/admin/files/cut.xhtml?f=<c:out value="${file.name}"/>"><i
								class="fa fa-cut"></i></a>&nbsp;<a title="Copy ${file.name}"
								href="/admin/files/copy.xhtml?f=<c:out value="${file.name}"/>"><i
								class="fa fa-copy"></i></a>&nbsp;<a title="Delete ${file.name}"
								href="/admin/files/delete.xhtml?f=<c:out value="${file.name}"/>"><i
								class="fa fa-trash"></i></a>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:if test="${not empty cutFiles}">
				<div class="margin-top-05 row panel panel-info">
					<div class="label label-info col-xs-12">
						<i class="pull-left fa fa-cut fa-lg"></i>
					</div>
					<c:forEach items="${cutFiles}" var="cutFile">
						<div class="col-xs-10">
							<c:if test="${cutFile.directory}">
								<a
									href="/admin/files/list.xhtml?f=<c:out value="${cutFile.name}"/>"><i
									class="fa fa-folder-o"></i>&nbsp;${cutFile.name}</a>
							</c:if>
							<c:if test="${not cutFile.directory}">
								<a download
									href="/admin/files/download.xhtml?f=<c:out value="${cutFile.name}"/>"><i
									class="fa fa-file-o"></i>&nbsp;${cutFile.name}</a>
							</c:if>
						</div>
						<div class="col-xs-2">
							<a class="pull-right" title="Clear ${cutFile.name}"
								href="/admin/files/clear.xhtml?f=<c:out value="${cutFile.name}"/>"><i
								class="fa fa-close"></i></a>
						</div>
					</c:forEach>
					<div class="col-xs-12">
						<a class="pull-right" title="Paste ${cutFile.name}"
							href="/admin/files/paste.xhtml?f=<c:out value="${cutFile.name}"/>"><i
							class="fa fa-paste"></i></a>
					</div>
				</div>
			</c:if>
			<c:if test="${not empty copiedFiles}">
				<div class="margin-top-05 panel panel-info row">
					<div class="label label-info col-xs-12">
						<i class="pull-left fa fa-copy fa-lg"></i>
					</div>
					<c:forEach items="${copiedFiles}" var="copiedFile">
						<div class="col-xs-10">
							<c:if test="${copiedFile.directory}">
								<a
									href="/admin/files/list.xhtml?f=<c:out value="${copiedFile.name}"/>"><i
									class="fa fa-folder-o"></i>&nbsp;${copiedFile.name}</a>
							</c:if>
							<c:if test="${not copiedFile.directory}">

								<a download
									href="/admin/files/download.xhtml?f=<c:out value="${copiedFile.name}"/>"><i
									class="fa fa-file-o"></i>&nbsp;${copiedFile.name}</a>
							</c:if>
						</div>
						<div class="col-xs-2">
							<a class="pull-right" title="Clear ${copiedFile.name}"
								href="/admin/files/clear.xhtml?f=<c:out value="${copiedFile.name}"/>"><i
								class="fa fa-close"></i></a>
						</div>
					</c:forEach>
					<div class="col-xs-12">
						<a class="pull-right" title="Paste ${cutFile.name}"
							href="/admin/files/paste.xhtml?f=<c:out value="${cutFile.name}"/>"><i
							class="fa fa-paste"></i></a>
					</div>
				</div>
			</c:if>
			<div class="row">
				<div class="form-group margin-top-05">
					<label class="control-label col-xs-3">Folder </label>
					<div class="col-xs-9">
						<input class="form-control" type="text" name="folder" />
					</div>
				</div>
				<div class="form-group margin-top-05">
					<label class="control-label col-xs-3" for="imagefile">File
						to upload</label>
					<div class="col-xs-9">
						<input id="file" name="file" type="file" class="file"
							data-show-upload="false" data-show-caption="true">
					</div>
				</div>
				<div class="form-actions margin-top-05">
					<div class="col-xs-offset-3 col-xs-9">
						<input class="btn btn-primary pull-right" type="submit" name="but"
							id="submit" value="<spring:message code="button.add"/>" />
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>

<script>
	$(function() {
		$("input[type='checkbox']").hide();
		
		$(".fa-share-alt").parent().click(share);
		$(".fa-share-alt-square").parent().click(unshare);
	});
	
	function share(){
		var file = $(this).data("filename");
		var link = $(this);
			$.ajax({
				type : "GET",
				url : "/admin/files/share.xhtml",
				data : "file=" + file,
				success : function(data) {
					link.children(".fa").toggleClass("fa-share-alt fa-share-alt-square");
					link.children(".fa").parent().unbind("click");
					link.children(".fa").parent().attr("title","Unshare "+file);
					link.children(".fa").parent().click(unshare);
					}
			});
	}
	function unshare(){
		var file = $(this).data("filename");
		var link = $(this);
			$.ajax({
				type : "GET",
				url : "/admin/files/unshare.xhtml",
				data : "file=" + file,
				success : function(data) {
					link.children(".fa").toggleClass("fa-share-alt fa-share-alt-square");
					link.children(".fa").parent().unbind("click");
					link.children(".fa").parent().attr("title","Share "+file);
					link.children(".fa").parent().click(share);
					}
			});
	}
</script>