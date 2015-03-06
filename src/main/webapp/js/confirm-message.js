$(function() {
	var url = "";
	
	$(".confirm-message").click(function(e) {
		e.preventDefault();
		loadAttributes(this);
		$("#effect").fadeIn("slow");
		$("#background").fadeIn("slow");
		disablescroll();
	});

	function loadAttributes(item) {
		url = $(item).attr('href');
		if(url == null) {
			url = $(item).data("redirect"); 
		}
		
		$("#action-title").html($(item).attr('data-confirm-title'));
		$("#action-message").html($(item).attr('data-confirm-message'));

		var type = $(item).attr('data-confirm-type');
		$("#action-title-container").removeClass();
		$("#action-title-container > i").removeClass();
		$("#action-yes").removeClass();
		$("#action-no").removeClass();

		var containerClass = "";
		var containerIcon = "";
		var containerYes = "";

		if (type == "delete") {
			containerClass = "label-danger ui-corner-all";
			containerIcon = "fa fa-warning";
			containerYes = "btn btn-danger";
		} else if (type == "warning") {
			containerClass = "label-warning ui-corner-all";
			containerIcon = "fa fa-warning";
			containerYes = "btn btn-warning";
		} else if (type == "confirm") {
			containerClass = "label-success ui-corner-all";
			containerIcon = "fa fa-check";
			containerYes = "btn btn-success";
		} else if (type == "message") {
			containerClass = "label-success ui-corner-all";
			containerIcon = "fa fa-check";
			containerYes = "btn btn-success";
			$("#action-no").addClass("hide");
			
			if($(item).is("input[type=submit]")) {
				$(item).submit();
			}
		}

		$("#action-title-container").addClass(containerClass);
		$("#action-title-container > i").addClass(containerIcon);
		$("#action-yes").addClass(containerYes);
		$("#action-no").addClass("btn btn-primary");
		
		$("#action-yes").focus();
	}

	$("#action-yes").click(function() {
		if(url) {
			window.location = url;
		} else {
			$("#effect").fadeOut("slow");
			$("#background").fadeOut("slow");
			enablescroll();
		}
	});

	$("#action-no").click(function() {
		$("#effect").fadeOut("slow");
		$("#background").fadeOut("slow");
		enablescroll();
	});

	$("#background").click(function() {
		$("#background").fadeOut("slow");
		$("#effect").fadeOut("slow");
		enablescroll();
	});

	function disablescroll() {
		$('html').css({
			'overflow' : 'hidden'
		});
	}

	function enablescroll() {
		$('html, body').css({
			'overflow' : 'auto'
		});
	}
});