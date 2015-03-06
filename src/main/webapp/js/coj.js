function getInstitution() {
    var id = document.getElementById("country").value;
    if (id == 0) {
        $('#inst').hide();
    } else {
        $('#inst').show();
    }
    var instution = document.getElementById("institution");
    instution.length = 0;
    $.ajax({
        type: "POST",
        url: "/user/getInstitution.xhtml",
        data: "country=" + id,
        dataType: 'json',
        success: function(data) {
            instution.options[0] = new Option("None", -1);
            for (var i = 0; i < data.length; i++) {
                instution.options[i + 1] = new Option(data[i].institution, data[i].id);
            }
        }
    });
}

function like(id){
	voting(id,"/user/like.json");
}

function dislike(id){
	voting(id,"/user/dislike.json");
}

function voting(id,url){
	$.ajax({
        type: "POST",
        url: url,
        data: "id=" + id,
        dataType: 'json',
        success: function(data) {
           var rating  = $("#rating" + id);
           rating.removeClass();
           if (data > 0)
        	   rating.addClass("text-success");
           else if (data < 0)
        	   rating.addClass("text-danger");
           rating.html(data);
           
           $("#thumbs-up"+ id).remove();
           $("#thumbs-down"+ id).remove();
        }
    });
}

function disableEntry(id){
	$.ajax({
        type: "POST",
        url: "/admin/disableentry.xhtml",
        data: "id=" + id,
        dataType: 'json',
        success: function(data) {
           $("#entry"+ data.id).html("<i>" + data.text + "</i>");
           $("#actions" + data.id).remove();
        }
    });
}

function buildboardDiv(message) {
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    td.innerHTML += message;
    tr.appendChild(td);
    return tr;
}

function updateBoard(last, course_id) {
    var board = document.getElementById("schoolarright");
    $.ajax({
        type: "POST",
        url: "/schoolar/updateboard.xhtml",
        data: "last=" + last + "&course_id=" + course_id,
        dataType: 'json',
        success: function(data) {
            var lastl = data[0].lastlog;
            var logs = data[0].logs;
            if (last !== 0) {                
                for (var i = 0; i < logs.length; i++) {
                    if (logs[i].message !== undefined) {
                        board.insertBefore(buildboardDiv(logs[i].username + " - " + logs[i].message), board.childNodes[0]);
                    }
                }
            }
            else{
                for (var i = 0; i < logs.length; i++) {
                    if (logs[i].message !== undefined) {
                        board.appendChild(buildboardDiv(logs[i].username + " - " + logs[i].message), board.childNodes[0]);
                    }
                }
            }
            setTimeout("updateBoard(" + lastl + ", " + course_id + ")", 10000);
        }
    });
}

function createTr(course_id, chapter, name, id)
{
    var tr = document.createElement("tr");
    tr.setAttribute("id", id);

    var tdname = document.createElement("td");
    tdname.innerHTML += name;

    var tdchap = document.createElement("td");
    tdchap.innerHTML += chapter;

    var tdaction = document.createElement("td");
    var lkrem = document.createElement("a");
    lkrem.setAttribute("onClick", "removeMaterial(" + course_id + "," + chapter + "," + id + ")");
    lkrem.innerHTML += "remove";
    tdaction.appendChild(lkrem);

    tr.appendChild(tdname);
    tr.appendChild(tdchap);
    tr.appendChild(tdaction);

    return tr;
}

function changeCourse(course_id) {
    var id = document.getElementById("courses").value;
    var name = document.getElementById("chpname");
    var contests_problem = document.getElementById("contests_problem");
    contests_problem.length = 0;
    var mat_tbody = document.getElementById("all_materials");
    var trs = mat_tbody.getElementsByTagName("tr");
    while (trs.length > 0) {
        mat_tbody.removeChild(trs[0]);
    }

    var iyear = document.getElementById("iyear");
    var imonth = document.getElementById("imonth");
    var iday = document.getElementById("iday");
    var ihours = document.getElementById("ihours");
    var iminutes = document.getElementById("iminutes");
    var iseconds = document.getElementById("iseconds");

    $.ajax({
        type: "POST",
        url: "/schoolar/getchapterinfo.xhtml",
        data: "chapter=" + id + "&course_id=" + course_id,
        dataType: 'json',
        success: function(data) {
            name.value = data[0].name;
            var problems = data[0].problems;
            var materials = data[0].materials;

            for (var i = 0; i < problems.length; i++) {
                if (problems[i].title !== undefined) {
                    contests_problem.options[i] = new Option(problems[i].title, problems[i].pid);
                }
            }

            for (var i = 0; i < materials.length; i++) {
                mat_tbody.appendChild(createTr(course_id, id, materials[i].content_address, materials[i].content_id));
            }

            for (var i = 0; i < iyear.length; i++) {
                if (iyear[i].value == data[0].iyear) {
                    iyear[i].selected = true;
                    break;
                }
            }


            for (var i = 0; i < imonth.length; i++) {
                if (imonth[i].value == data[0].imonth) {
                    imonth[i].selected = true;
                    break;
                }
            }

            for (var i = 0; i < iday.length; i++) {
                if (iday[i].value == data[0].iday) {
                    iday[i].selected = true;
                    break;
                }
            }

            for (var i = 0; i < ihours.length; i++) {
                if (ihours[i].value == data[0].ihours) {
                    ihours[i].selected = true;
                    break;
                }
            }

            for (var i = 0; i < iminutes.length; i++) {
                if (iminutes[i].value == data[0].iminutes) {
                    iminutes[i].selected = true;
                    break;
                }
            }

            for (var i = 0; i < iseconds.length; i++) {
                if (iseconds[i].value == data[0].iseconds) {
                    iseconds[i].selected = true;
                    break;
                }
            }


        }
    });
}

function removeMaterial(courseid, chapter, id) {
    var mat_tbody = document.getElementById("all_materials");
    $.ajax({
        type: "POST",
        url: "/schoolar/removematerial.xhtml",
        data: "chapter=" + chapter + "&course_id=" + courseid + "&content_id=" + id,
        dataType: 'json',
        success: function(data) {
            mat_tbody.removeChild(document.getElementById(id));
            alert("Delete successfully");
        }
    });
}

function getVirtualTemplates(style) {
    var contests = document.getElementById("all_contests");
    contests.length = 0;
    $.ajax({
        type: "GET",
        url: "/practice/getvirtualtemplates.xhtml?style=" + style,
        data: "style=" + style,
        dataType: 'json',
        success: function(data) {
            contests.options[0] = new Option("Select", 0);
            for (var i = 0; i < data.length; i++) {
                contests.options[i + 1] = new Option(data[i].name, data[i].cid);
            }
        }
    });
}

function saveDraft() {
    var notify = document.getElementById("notice");
    $.ajax({
        type: "POST",
        url: "/mail/savedraft.xhtml",
        data: $('#composeform').serialize(),
        dataType: 'json',
        success: function(data) {
            notify.setAttribute("class", "notice");
            notify.innerHTML = data.message;
        }
    });
}

function deleteFileIn(element)
{
    document.getElementById('uploadinput').removeChild(document.getElementById("file_" + element));
}

function addOutputOld(id)
{
    var newid = eval(++id);
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    var td1 = document.createElement("td");
    var span = document.createElement("span");
    span.setAttribute("onClick", "deleteFileOut(" + id + ")");
    span.innerHTML += "Remove";
    var input = document.createElement("input");
    input.setAttribute('type', 'file');
    input.setAttribute('name', "file_" + id);
    td.appendChild(input);
    td1.appendChild(span);
    tr.appendChild(td);
    tr.appendChild(td1);
    var element = document.getElementById('outputfiles');
    var table = document.createElement("table");
    table.appendChild(tr);
    element.appendChild(table);
    var add = document.getElementById('addout');
    var num = eval(id);
    add.setAttribute("onClick", "addOutput(" + num + ")");
}

function deleteFile(element)
{
    var tr = document.getElementById(element).parentNode.parentNode;
    tr.parentNode.removeChild(tr);
}


function addOutput(add)
{
    var div_father = document.getElementById(add);
    var table = div_father.getElementsByTagName("table")[0];
    var cant = countChilds(table);
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    var td1 = document.createElement("td");
    var span = document.createElement("span");
    span.setAttribute("onClick", "deleteFile('" + add + "_" + cant + "')");
    span.innerHTML += "Remove";
    var input = document.createElement("input");
    input.setAttribute('type', 'file');
    input.setAttribute('name', add + "_" + cant);
    input.setAttribute('id', add + "_" + cant);
    td.appendChild(input);
    td1.appendChild(span);
    tr.appendChild(td);
    tr.appendChild(td1);
    table.appendChild(tr);

}

function countChilds(element) {
    return element.childNodes.length;
}

function onCase() {
    var child = document.getElementById("casecheck");
    var dis = document.getElementById("casetime");
    if (child.checked)
    {
        dis.removeAttribute("disabled");
    }
    else
    {
        dis.setAttribute("disabled", "");
    }
}

function setProperties(element)
{

}

function selection(checkbox,elements)
{
    var element = document.getElementById(checkbox);
    var ele = document.getElementsByName(elements);
    if (element.checked)
    {
        for (var i = 0; i < ele.length; i++) {
            ele[i].checked = true;
        }
    }
    else {
        for (var i = 0; i < ele.length; i++) {
            ele[i].checked = false;
        }
    }
}


function removeAll() {
    var one = document.getElementById("rejudge");
    while (one.hasChildNodes()) {
        one.removeChild(one.firstChild);
    }
}
function show(val) {
    removeAll();
    switch (val)
    {
        case 1:
            {
                var one = document.getElementById("rejudge");
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                input.setAttribute("name", "submitid");
                input.setAttribute("type", "text");
                td.appendChild(input);
                tr.appendChild(td);
                one.appendChild(tr);
                break;
            }
        case 2:
            {
                var one = document.getElementById("rejudge");
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                var input1 = document.createElement("input");
                input.setAttribute("name", "sid1");
                input.setAttribute("type", "text");
                input1.setAttribute("name", "sid2");
                input1.setAttribute("type", "text");
                td.appendChild(input);
                td.appendChild(input1);
                tr.appendChild(td);
                one.appendChild(tr);
                break;
            }
        case 3:
            {
                var one = document.getElementById("rejudge");
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                input.setAttribute("name", "pid");
                input.setAttribute("type", "text");
                td.appendChild(input);
                tr.appendChild(td);
                one.appendChild(tr);
                break;
            }
        case 4:
            {
                var array = new Array('Accepted', 'Wrong Answer', 'Presentation Error', 'Memory Limit Exceeded', 'Time Limit Exceeded', 'Output Limit Exceeded', 'Size Limit Exceeded', 'Invalid Function', 'Runtime Error', 'Compilation Error', 'Unqualified', 'Judging');
                var select = document.createElement("select");
                select.setAttribute("name", "status");
                for (var i = 0; i < array.length; i++)
                {
                    var option = document.createElement("option");
                    option.innerHTML += array[i];
                    select.appendChild(option);
                }
                var one = document.getElementById("rejudge");
                one.appendChild(select);
                break;
            }
        case 5:
            {
                var one = document.getElementById("rejudge");

                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                var input1 = document.createElement("input");
                input.setAttribute("name", "sid1");
                input.setAttribute("type", "text");
                input1.setAttribute("name", "sid2");
                input1.setAttribute("type", "text");
                td.appendChild(input);
                td.appendChild(input1);
                tr.appendChild(td);
                one.appendChild(tr);

                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                input.setAttribute("name", "pid");
                input.setAttribute("type", "text");
                td.appendChild(input);
                tr.appendChild(td);
                one.appendChild(tr);

                var array = new Array('Accepted', 'Wrong Answer', 'Presentation Error', 'Memory Limit Exceeded', 'Time Limit Exceeded', 'Output Limit Exceeded', 'Size Limit Exceeded', 'Invalid Function', 'Runtime Error', 'Compilation Error', 'Unqualified', 'Judging');
                var select = document.createElement("select");
                select.setAttribute("name", "status");
                for (var i = 0; i < array.length; i++)
                {
                    var option = document.createElement("option");
                    option.innerHTML += array[i];
                    select.appendChild(option);
                }
                one.appendChild(select);
                break;
            }
    }
}

function updMode(mod) {

    $('#model_output').on('scroll', function() {
        $('#user_output').scrollTop($(this).scrollTop());
    });
    $('#user_output').on('scroll', function() {
        $('#model_output').scrollTop($(this).scrollTop());
    });

    var mode = document.getElementById("mode").options[document.getElementById("mode").selectedIndex].id;

    $('#input').attr('readonly', mode == "inputgen");

    (mode == "modelsol") ? $('#lastdl').show() : $('#lastdl').hide();

    (mode != "inputgen") ? $('#output').show() : $('#output').hide();
    (mode != "inputgen") ? $('#outputlabel').show() : $('#outputlabel').hide();
    (mode != "inputgen") ? $('#inputfile').show() : $('#inputfile').hide();

    (mode == "customsol") ? $('#code').show() : $('#code').hide();
    (mode == "customsol") ? $('#codefile').show() : $('#codefile').hide();
    (mode == "customsol") ? $('#codelabel').show() : $('#codelabel').hide();
    (mode == "customsol") ? $('#comparisonlabel').show() : $('#comparisonlabel').hide();
    (mode == "customsol") ? $('#matchlabel').show() : $('#matchlabel').hide();
    (mode == "customsol") ? $('#comparison_user').show() : $('#comparison_user').hide();
    (mode == "customsol") ? $('#comparison_splitter').show() : $('#comparison_splitter').hide();
    (mode == "customsol") ? $('#comparison_model').show() : $('#comparison_model').hide();
    (mode == "customsol") ? $('#lang').show() : $('#lang').hide();
    (mode == "customsol") ? $('#langlabel').show() : $('#langlabel').hide();

}

function updSelect() {
    var lang = document.getElementById("lang").value;
    if (lang == "pascal") {
        lang = "pas";
    }
    if (lang == "gcc") {
        lang = "c";
    }
    if (lang == "txt") {
        lang = "robotstxt";
    }
    window.frames['frame_code'].editArea.execCommand('change_syntax', lang);
}

function toogle_editable(id)
{
    editAreaLoader.execCommand(id, 'set_editable', !editAreaLoader.execCommand(id, 'is_editable'));
}

function activate_editor(enable) {
    if (enable) {
        var lang = document.getElementById("lang").value;
        if (lang == "pascal") {
            lang = "pas";
        }
        if (lang == "gcc") {
            lang = "c";
        }
        if (lang == "txt") {
            lang = "robotstxt";
        }
        editAreaLoader.init({
            id: "code"	// id of the textarea to transform
                    ,
            start_highlight: true
                    ,
            allow_toggle: true
                    ,
            language: "en"
                    ,
            syntax: lang
                    ,
            toolbar: "search, go_to_line, |, undo, redo, |, select_font, |, change_smooth_selection, highlight, reset_highlight, |, help"
                    ,
            syntax_selection_allow: "c,cpp,csharp,java,pas,perl,php,python,ruby,robotstxt"
                    ,
            show_line_colors: true
        });
    }
}

function SeleccionarRangosContest() {
    SeleccionaRangos('contest_users_clarification');
    SeleccionaRangos('contest_users');
    SeleccionaRangos('balloontrackers');
}

function SeleccionarRangosVirtualContest() {
    SeleccionaRangos('contest_users');
    SeleccionaRangos('contests_problem');
}

function SeleccionarRangosCourses() {
    SeleccionaRangos('contests_problem');
}

function SeleccionaRangos(elemento)
{
    var element = document.getElementById(elemento);
    var n = element.length;
    for (var i = 0; i < n; ++i)
    {
        element[i].selected = 1;
    }
}


function addremove(emisor, receptor) {
    var emisor = document.getElementById(emisor);
    var receptor = document.getElementById(receptor);
    var posicion = receptor.options.length;
    for (var i = 0; i < emisor.options.length; i++) {
        if (emisor.options[i].selected) {
            receptor.options[posicion++] = new Option(emisor.options[i].text, emisor.options[i].value);
            emisor.options[i--] = null;
        }
    }
}
function hide() {
    var chk = document.getElementById("chkprivate");
    if (chk.checked == true) {
        document.getElementById("cuser").style.display = "none";
        document.getElementById("puser").style.display = "block";
    }
    else {
        document.getElementById("cuser").style.display = "block";
        document.getElementById("puser").style.display = "none";
    }
}

function hideProblems() {

    toggleVisibility(id_element)
}

function changeProblemsState() {

    var virtual_style = document.getElementById('virtual_style').checked;
    var free_problems_style = document.getElementById('free_problems_style').checked;
    toggleVisibility('hideproblemlabel1', free_problems_style);
    toggleVisibility('hideproblemlabel2', free_problems_style);
    toggleVisibility('hideallproblems', free_problems_style);
    toggleVisibility('hidebutton1', free_problems_style);
    toggleVisibility('hidecontestproblems', free_problems_style);
    toggleVisibility('hidebutton2', free_problems_style);
    toggleVisibility('hideerror', free_problems_style);
    toggleVisibility('virtual_contest_9', virtual_style);
    getVirtualTemplates(virtual_style == true ? 1 : 2);
}



function toggleVisibility(id_element, show) {
    var elemento = document.getElementById(id_element);
    if (show) {
        elemento.style.display = "block";
    }
    else {
        elemento.style.display = "none";
    }
}

function hidestyle() {
    var chk = document.getElementById("acm");
    if (chk.checked == false) {
        document.getElementById("style").style.display = "none";
    }
    else {
        document.getElementById("style").style.display = "";
    }
}

function selectContestants() {
    var chk = document.getElementById("all");
    if (chk.checked == true) {
        document.getElementById("contestants").checked = true;
    }
    else {
        document.getElementById("contestants").checked = false;
    }
}

function removeAllplagicoj() {
    var one = document.getElementById("plagicoj");
    while (one.hasChildNodes()) {
        one.removeChild(one.firstChild);
    }
}

function unmarkfavorite(pid) {
    $.ajax({
        type: "get",
        url: "/24h/unmarkfavorite.xhtml",
        data: "pid=" + pid,
        success: function(msg) {
            alert("Problem succesfuly marked as non favorite");
        }
    });
}

function markfavorite(pid) {
    $.ajax({
        type: "get",
        url: "/24h/markasfavorite.xhtml",
        data: "pid=" + pid,
        success: function(msg) {
            alert("Problem succesfuly marked as favorite");
        }
    });
}


function loadProblems(pid) {

    $("#contestproblems").sortable({
        cancel: "select",
        connectWith: "ul"
    });

//    $("#classifications, #problemClassifications").disableSelection();
    $("select").unbind();
}


function loadProblemClassification(pid) {

    $("#classifications").sortable({
        cancel: "select",
        connectWith: "ul",
        stop: function(event, ui) {
            var added = ui.item.parent().attr("id") == 'problemClassifications';
            if (added) {
                $.ajax({
                    type: "POST",
                    url: "manageproblemclassification.xhtml",
                    data: "add&pid=" + pid + "&cid=" + ui.item.attr("id") + "&cpx=" + $("input[name='class" + ui.item.attr("id") + "']:checked").attr("value"),
                    success: function(msg) {

                        $("input[name='class" + ui.item.attr("id") + "']").attr("disabled", "true");
                    }
                });
            }
        }
    });

    $("#problemClassifications").sortable({
        connectWith: "ul",
        stop: function(event, ui) {
            var removed = ui.item.parent().attr("id") == 'classifications';
            if (removed) {
                $.ajax({
                    type: "POST",
                    url: "manageproblemclassification.xhtml",
                    data: "rmb&pid=" + pid + "&cid=" + ui.item.attr("id"),
                    success: function(msg) {
                        $("input[name='class" + ui.item.attr("id") + "']").removeAttr("disabled");
                    }
                });
            }
        }
    });
    $("#classifications, #problemClassifications").disableSelection();
    $("select").unbind();
}
function showplagicoj(val) {
    removeAllplagicoj();
    switch (val)
    {
        case 1:
            {
                var one = document.getElementById("plagicoj");
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                var input1 = document.createElement("input");
                input.setAttribute("name", "submitid1");
                input.setAttribute("type", "text");
                input1.setAttribute("name", "submitid2");
                input1.setAttribute("type", "text");
                td.appendChild(input);
                td.appendChild(input1);
                tr.appendChild(td);
                one.appendChild(tr);
                break;
            }
        case 2:
            {
                var one = document.getElementById("plagicoj");
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                var input1 = document.createElement("input");
                input.setAttribute("name", "sid1");
                input.setAttribute("type", "text");
                input1.setAttribute("name", "sid2");
                input1.setAttribute("type", "text");
                td.appendChild(input);
                td.appendChild(input1);
                tr.appendChild(td);
                one.appendChild(tr);
                break;
            }
        case 3:
            {
                var one = document.getElementById("plagicoj");
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var input = document.createElement("input");
                input.setAttribute("name", "pid");
                input.setAttribute("type", "text");
                td.appendChild(input);
                tr.appendChild(td);
                one.appendChild(tr);
                break;
            }

    }
}

function updateClassification(id) {
    $.ajax({
        type: "GET",
        url: "/admin/updateclassifications.xhtml?classid=" + id + "&name=" + $("input[name='class" + id + "']").val(),
        success: function() {
            alert("Classification successfully updated.");
        }
    });
}

function updateSource(id) {
    $.ajax({
        type: "POST",
        url: "/admin/updatesource.xhtml",
        data: "idSource=" + id + "&name=" + $("input[name='name" + id + "']").val()+"&author=" + $("input[name='author" + id + "']").val(),
        success: function() {
        	alert("Source successfully updated.");
        }
    });
}

function deleteSource(id) {
    $.ajax({
        type: "POST",
        url: "/admin/deletesource.xhtml",
        data: "idSource=" + id,
        success: function() {
        	$("input[name='name" + id + "']").parent().parent().remove();
        }
    });
}

function deleteClassification(id) {
    $.ajax({
        type: "GET",
        url: "/admin/deleteclassifications.xhtml?classid=" + id,
        success: function() {
            $("input[name='class" + id + "']").parent().parent().remove();
            alert("Classification successfuly deleted.");
        }
    });
}