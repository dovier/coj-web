$('#startDate').datetimepicker({
	dateFormat: 'yy-m-d',
	timeFormat: 'HH:mm:ss.0',
	stepMinute: 5,
	showSecond: false,
	onClose: function(dateText, inst) {
		var endDateTextBox = $('#endDate');
		if (endDateTextBox.val() != '') {
			var testStartDate = new Date(dateText);
			var testEndDate = new Date(endDateTextBox.val());
			if (testStartDate > testEndDate)
				endDateTextBox.val(dateText);
		}
		else {
			endDateTextBox.val(dateText);
		}
	},
	onSelect: function (selectedDateTime){
		var start = $(this).datetimepicker('getDate');
		$('#endDate').datetimepicker('option', 'minDate', new Date(start.getTime()));
		$('#endDate').datetimepicker('option', 'dateFormat', 'yy-m-d');
		$('#endDate').datetimepicker('option', 'timeFormat', 'HH:mm:ss.0');
	}
});
$('#endDate').datetimepicker({
	dateFormat: 'yy-m-d',
	timeFormat: 'HH:mm:ss.0',
	stepMinute: 5,
	showSecond: false,
	onClose: function(dateText, inst) {
		var startDateTextBox = $('#startDate');
		if (startDateTextBox.val() != '') {
			var testStartDate = new Date(startDateTextBox.val());
			var testEndDate = new Date(dateText);
			if (testStartDate > testEndDate)
				startDateTextBox.val(dateText);
		}
		else {
			startDateTextBox.val(dateText);
		}
	},
	onSelect: function (selectedDateTime){
		var end = $(this).datetimepicker('getDate');
		$('#startDate').datetimepicker('option', 'maxDate', new Date(end.getTime()) );
		$('#startDate').datetimepicker('option', 'dateFormat', 'yy-m-d');
		$('#startDate').datetimepicker('option', 'timeFormat', 'HH:mm:ss.0');
	}
});