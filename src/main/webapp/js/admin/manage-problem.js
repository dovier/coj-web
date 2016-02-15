function addInput() {
    var idx = $(".model-dataset").length;
    var string =  "";
    var html = "<div class=\"model-dataset margin-top-05 col-xs-12\">"
            + "<label class=\"control-label col-xs-3\">"
            + string
            + "</label>"
            + "<div class=\"col-xs-4\"><input id = \"in" + idx + "\" name= \"in" + idx + "\" type=\"file\" class=\"dataset file\" data-show-upload=\"false\" /></div>"
            + "<div class=\"col-xs-4\"><input id = \"out" + idx + "\" name= \"out" + idx + "\" type=\"file\" class=\"dataset file\" data-show-upload=\"false\" /></div>"
            + "</div>";
    $("div#datasets").append(html);
    $(".model-dataset:last .file").fileinput({
        maxFileSize: 5000000,
        allowedFileTypes: ['text'],
        removeClass: "btn btn-default",
        removeLabel: "Delete",
        previewFileType: 'text',
        msgProgress: 'Loading {percent}%',
        browseClass: "btn btn-primary",
        browseLabel: i18n.browseLabel,
        browseIcon: '<i class="fa fa-file"></i>&nbsp;',
        removeIcon: '<i class="fa fa-trash"></i>'
    }).on('fileloaded', function(event, file, previewId) {
        if (file.name.search(/\d{3}\.(in|out)/) == -1) {
            alert("Error in file name: " + file.name + "; must be in the form 001.in, 002.out, etc.");
            $(this).fileinput('reset');
        }
    });
}
;

$(function() {
    $(".file").fileinput({
        maxFileSize: 5000000,
        allowedFileTypes: ['text'],
        removeClass: "btn btn-default",
        removeLabel: "Delete",
        previewFileType: 'text',
        showPreview: 0,
        msgProgress: 'Loading {percent}%',
        browseClass: "btn btn-primary",
        browseLabel: i18n.browseLabel,
        browseIcon: '<i class="fa fa-file-o"></i>&nbsp;',
        removeIcon: '<i class="fa fa-trash"></i>'
    });

    $(".dataset").fileinput({
        maxFileSize: 5000000,
        allowedFileTypes: ['text'],
        removeClass: "btn btn-default",
        removeLabel: "Delete",
        previewFileType: 'text',
        showPreview: 0,
        msgProgress: 'Loading {percent}%',
        browseClass: "btn btn-primary",
        browseLabel: i18n.browseLabel,
        browseIcon: '<i class="fa fa-file-o"></i>&nbsp;',
        removeIcon: '<i class="fa fa-trash"></i>'
    });

    $("#zipfile").fileinput({
        maxFileSize: 50000000,
        allowedFileTypes: ['object'],
        removeClass: "btn btn-default",
        msgProgress: 'Loading {percent}%',
        browseClass: "btn btn-danger",
        browseLabel: i18n.browseLabel,
        browseIcon: '<i class="fa fa-file-o"></i>&nbsp;',
        removeIcon: '<i class="fa fa-trash"></i>'
    });


    $('.fa-chevron-up').click(function() {
        $(this).toggleClass('fa-chevron-up');
        $(this).toggleClass('fa-chevron-down');
    });

    addInput();
    $("[data-toggle='tooltip']").tooltip();

    $("#deleteall").click(deleteall);
});

function deleteall() {
    var pid = $("#pid").val();
    $.ajax({
        type: "GET",
        url: "/admin/removealldatasets.json",
        data: {
            "pid": pid
        },
        dataType: 'text',
        success: function(data) {
            $('#datasets-container').empty();
        }
    });

}
;

function removedataset(pid, dataset) {
    $.ajax({
        type: "GET",
        url: "/admin/removedataset.json",
        data: {
            "pid": pid,
            "dataset": dataset
        },
        dataType: 'text',
        success: function(data) {
            $('#' + dataset + '-container').remove();
        }
    });

}
;

function applyLimit(sender, limit) {
    var limitValue = $(sender).parents('.input-group').children('input').val(),
            useMultipliers = $("#use-multipliers").is(':checked'),
            multipliers = $("#multipliers");

    $('#allLimits .language-container').each(function() {
        if (useMultipliers) {
            var language = $(this).attr('language');

            var multiplier = $('.mult-cnt[language="' + language + '"] ' + limit, multipliers).val();

            $(limit, this).val(limitValue * multiplier);
        }
        else
            $(limit, this).val(limitValue);
    });
}

function applyAllLimit() {
    applyLimit($('#btn-max-memory'), '.max-memory');
    applyLimit($('#btn-max-case-execution-time'), '.max-case-execution-time');
    applyLimit($('#btn-max-total-execution-time'), '.max-total-execution-time');
    applyLimit($('#btn-max-source-code-lenght'), '.max-source-code-lenght');
}

