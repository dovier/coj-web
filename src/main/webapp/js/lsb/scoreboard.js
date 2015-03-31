//JQUERY CODE..
//acts like document.onload..

var cid;

function setCid(cid1) {
	cid = cid1;
}

$(function() {

	// it is currently updating..
	var updating = false;

	$('.triggerUpdates').hide();

	// Updates the status with the current time..
	var updateStatusTime = function() {
		$('#stocks .status').html("Updated at: " + new Date());
	};

	var getTable = function() {
		var acm = $('#aqui table#myscoretable').clone();
		return acm;
	}

	var getNewTable = function(sad) {
		$("#aqui")
				.load(
						"/contest/cscoreboard.xhtml?cid=" + cid
								+ " table#myscoretable");
	}

	var getTimeTable = function() {
		$('.contestlanguages').load("cscoreboard.html .contestlanguages tbody");
	}

	var table = $('<table/>');
	$('#stocks .tableHolder').append($(table));
	updateStatusTime();

	// Updates the table..
	var updateTable = function(updateStatus) {
		var newTable = getTable();
		table.rankingTableUpdate(newTable, {
			duration : [ 3000, 0, 2500, 0, 3000 ],
			onComplete : function() {
				updating = false;
				if (updateStatus) {
					updateStatusTime();
				}
			},
			animationSettings : {
				up : {
					left : 0,
					backgroundColor : '#338833'
				},
				down : {
					left : 0,
					backgroundColor : '#FFCCCC'
				},
				fresh : {
					left : 0,
					backgroundColor : '#CCFFCC'
				},
				drop : {
					left : 0,
					backgroundColor : '#FFCCCC'
				}
			}
		});
		table = newTable;
	}

	var loopFunc = function() {
		if (!updating) {
			updating = true;
			$('#stocks .status').html("Updating..");
			getTimeTable();
			getNewTable();
			updateTable(true);
		}
	}

	// reference to the infinite loop..
	var loop = null;
	var loop1 = null;
	var flag = false;
	var interval = 60000;

	var getFunc = function() {
		loop = setInterval(loopFunction, interval);
	}

	var loopFunction = function() {
		if (flag) {
			$.scrollTo($('div#top'), {
				duration : 14000
			});
			loop1 = setInterval(loopFunc, 17000);
			flag = false;
			interval = 60000;
			clearInterval(loop);
			getFunc();
		} else {
			$.scrollTo($('p.status'), {
				duration : 14000
			});
			clearInterval(loop1);
			flag = true;
			interval = 20000;
			clearInterval(loop);
			getFunc();
		}
	}

	$('#stocks .get').click(function(event) {
		table = $('#aqui table#myscoretable').clone();
		$('#stocks .tableHolder').append($(table));
		$('#stocks .get').css({
			'display' : 'none'
		});
		loop1 = setInterval(loopFunc, 30000);
		event.preventDefault();
	});

});

var modifyClassName = function(elem, add, string) {
	var s = (elem.className) ? elem.className : "";
	var a = s.split(" ");
	if (add) {
		for (var i = 0; i < a.length; i++) {
			if (a[i] == string) {
				return;
			}
		}
		s += " " + string;
	} else {
		s = "";
		for (var i = 0; i < a.length; i++) {
			if (a[i] != string)
				s += a[i] + " ";
		}
	}
	elem.className = s;
}
function toggleGroup(n) {

	var currentClass = document.getElementById("myscoretable");
	for (var i = 1; i < 999; i++) {
		modifyClassName(currentClass, true, "sitehide" + i);
	}
	modifyClassName(currentClass, false, "sitehide" + n);
}
