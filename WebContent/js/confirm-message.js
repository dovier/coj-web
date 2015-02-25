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
		}

		$("#action-title-container").addClass(containerClass);
		$("#action-title-container > i").addClass(containerIcon);
		$("#action-yes").addClass(containerYes);
		$("#action-no").addClass("btn btn-primary");
	}

	$("#action-yes").click(function() {
		window.location = url;
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

	$("#effect").hide();
});