//START GLOBALS
var inirow;
var endrow;
var inicol;
var endcol;
//END GLOBALS


function showEqualUserVisibility(){                  
    $("a.btnPlagicoj#btnToggleEqualUserVisibility").html("Hide Equal User Visibility");
    
    var indexSourceUserColumn = $("#resultlist thead th").index($("#resultlist thead th#sourceUser")[0]);  
    var indexDestinationUserColumn = $("#resultlist thead th").index($("#resultlist thead th#destinationUser")[0]); 
    
    $("#resultlist tr:gt(0)").each(function(){            
        var p = $(this).children("td:eq("+indexSourceUserColumn+")").find("a");       
        var s = $(this).children("td:eq("+indexDestinationUserColumn+")").find("a");            
        var pi = p.html();
        var si = s.html();
        if (pi==si)
        {
            $(this).show();         
        }            
    });                            
}

function hideEqualUserVisibility(){   
    $("a.btnPlagicoj#btnToggleEqualUserVisibility").html("Show Equal User Visibility");
    
    var indexSourceUserColumn = $("#resultlist thead th").index($("#resultlist thead th#sourceUser")[0]);  
    var indexDestinationUserColumn = $("#resultlist thead th").index($("#resultlist thead th#destinationUser")[0]); 
    
    $("#resultlist tr:gt(0)").each(function(){            
        var p = $(this).children("td:eq("+indexSourceUserColumn+")").find("a");       
        var s = $(this).children("td:eq("+indexDestinationUserColumn+")").find("a");            
        var pi = p.html();
        var si = s.html();
        if (pi==si)
        {
            $(this).hide();         
        }            
    });                            
}

function showEqualSubmissionVisibility(){    
    $("a.btnPlagicoj#btnToggleEqualSubmissionVisibility").html("Hide Equal SubmissionJudge Visibility");
    
    var indexSourceSubmissionColumn = $("#resultlist thead th").index($("#resultlist thead th#sourceSubmission")[0]);  
    var indexDestinationSubmissionColumn = $("#resultlist thead th").index($("#resultlist thead th#destinationSubmission")[0]); 
    
    $("#resultlist tr:gt(0)").each(function(){            
        var p = $(this).children("td:eq("+indexSourceSubmissionColumn+")").find("a");            
        var s = $(this).children("td:eq("+indexDestinationSubmissionColumn+")").find("a");
        
        var pi = parseInt(p.html(), 10);
        var si = parseInt(s.html(), 10);
        if (pi==si)
        {         
            $(this).show();             
        }        
    });          
}

function hideEqualSubmissionVisibility(){   
    $("a.btnPlagicoj#btnToggleEqualSubmissionVisibility").html("Show Equal SubmissionJudge Visibility");
    
    var indexSourceSubmissionColumn = $("#resultlist thead th").index($("#resultlist thead th#sourceSubmission")[0]);  
    var indexDestinationSubmissionColumn = $("#resultlist thead th").index($("#resultlist thead th#destinationSubmission")[0]); 
    
    $("#resultlist tr:gt(0)").each(function(){            
        var p = $(this).children("td:eq("+indexSourceSubmissionColumn+")").find("a");            
        var s = $(this).children("td:eq("+indexDestinationSubmissionColumn+")").find("a");
        
        var pi = parseInt(p.html(), 10);
        var si = parseInt(s.html(), 10);
        if (pi==si)
        {         
            $(this).hide();             
        }        
    });  
    
}

function changeEnabledStatus(username, value){
    $.ajax({ 
        type: "POST", 
        url: "/plagicoj/plagicojresult.xhtml", 
        data: "username="+username+"&value="+value, 
        success: function(msg){    
            if (value)
            {
                $("#btnenableuser").replaceWith("<a id=\"btnenableuser\" onclick=\"changeEnabledStatus('"+username+"',false)\"><i class=\"green fa fa-unlock\"></i></a>");               
                alert("The user has been Enabled.");
            }    
            else
            {
                $("#btndisableuser").replaceWith("<a id=\"btnenableuser\" onclick=\"changeEnabledStatus('"+username+"',true)\"><i class=\"green fa fa-lock fa-lg\"></i></a>");               
                alert("The user has been Disabled.");
            }
        
        }
    });
}

function changeEnabledDestStatus(username, value){
    $.ajax({ 
        type: "POST", 
        url: "/plagicoj/plagicojresult.xhtml", 
        data: "username="+username+"&value="+value, 
        success: function(msg){    
            if (value)
            {
                $("#btnenabledestuser").replaceWith("<a class=\"btnPlagicoj\" id=\"btndisabledestuser\" onclick=\"changeEnabledDestStatus('"+username+"',false)\" ><i class=\"fa fa-unlock fa-lg\"></i></a>");               
                alert("The user has been Enabled.");
            }    
            else
            {
                $("#btndisabledestuser").replaceWith("<a id=\"btnenabledestuser\" onclick=\"changeEnabledDestStatus('"+username+"',true)\"><i class=\"fa fa-lock fa-lg\"></i></a>");               
                alert("The user has been Disabled.");
            }
        
        }
    });
}

function addFunctions()
{
    $("a.btnPlagicoj#btnToggleEqualSubmissionVisibility").toggle( hideEqualSubmissionVisibility,showEqualSubmissionVisibility);
    $("a.btnPlagicoj#btnToggleEqualUserVisibility").toggle( hideEqualUserVisibility,showEqualUserVisibility);

}

var url = window.location.toString().split("#")[0];
function diffUsingJS(sourceCode,destinationCode,outId) {     
    var base = difflib.stringAsLines($("#"+sourceCode).html());    
    var newtxt = difflib.stringAsLines($("#"+destinationCode).html());
    var sm = new difflib.SequenceMatcher(base, newtxt);    
    var opcodes = sm.get_opcodes();    
    var diffoutputdiv = $("#"+outId);        
    diffoutputdiv.html(diffview.buildView({
        baseTextLines:base,
        newTextLines:newtxt,
        opcodes:opcodes,
        baseTextName:"Base Text",
        newTextName:"New Text",
        viewType: 0
    }));
    $("th.author").remove();
    window.location = url + "#diff";
}

function checkDetectionParameters(h,m,s){
    $("form:first").submit(function () {         
        addMsg("<img id=\"plagicojload\" title=\"Loading\" alt=\"Loading...\" src=\"/images/plagicoj/cargando.gif\">");
        var segundosTarda = 0;  
        
        $.ajax({ 
            type: "POST", 
            url: "/plagicoj/plagicojinspectionresult.xhtml",     
            data: "submitid1="+$("input[name='submitid1']").attr("value")+
            "&submitid2="+$("input[name='submitid2']").attr("value")+
            "&sid1="+$("input[name='sid1']").attr("value")+
            "&sid2="+$("input[name='sid2']").attr("value")+
            "&pid="+$("input[name='pid']").attr("value")+
            "&submitid="+$("input[name='submitid']").attr("value")+
            "&onlyac="+$("input[name='onlyac']").attr("checked")+
            "&matchlanguage="+$("input[name='matchlanguage']").attr("checked")+
            "&ownsubmission="+$("input[name='ownsubmission']").attr("checked")+
            "&sameuser="+$("input[name='sameuser']").attr("checked")+
            "&testcant"
            ,
            async: false 
            ,
            success: function(msg){ 
                var cantSub = jQuery("div.cantsub", msg).html();                 
                if (cantSub <= 0){  
                    segundosTarda = 1000;
                    $("#checkerror").removeAttr("hidden");                    
                    $( "#checkerror" ).dialog({
                        height: 140,
                        modal: true
                    });                                            
                    return false;
                }
                segundosTarda = (parseInt((cantSub/10), 10) + 5);
                if (segundosTarda < 10)return true;
                $("#confirm").removeAttr("hidden");
                $("#confirm #cantSub").html(cantSub);
                $( "#dialog:ui-dialog" ).dialog( "destroy" );  
                
                var now = new Date();                    
                var dif = new Date(segundosTarda*1000);                    
                var nextD = new Date(now.getTime()+ dif.getTime());                     
                
                if (segundosTarda < 60)
                    $("#tiempo").html(segundosTarda+" "+s);
                else
                if (segundosTarda < 3600)    
                    $("#tiempo").html(parseInt(segundosTarda/60)+" "+m+" y "+parseInt(segundosTarda%60)+" "+s);
                else
                if (segundosTarda < 86400)    
                    $("#tiempo").html(parseInt(segundosTarda/3600)+ " "+h+", " +parseInt((segundosTarda%3600)/60)+" "+m+", "+parseInt(segundosTarda%60)+" "+s);
                else
                    $("#tiempo").html(": "+nextD);
                
                $( "#confirm" ).dialog({
                    resizable: false,
                    height:250,                        
                    modal: true,
                    buttons: {
                        Detect: function() {                                         
                            mform = $("form:first");                                
                            mform.unbind("submit");                            
                            mform.submit();
                            $( this ).dialog( "close" );                                
                        },
                        Cancel: function() {                            
                            $( this ).dialog( "close" );
                        }
                    }
                }); 
                return false;
            }
        });         
        removeMsg("plagicojload");
        return segundosTarda < 10; 
    });
}

function addMsg(code){        
    var body  = $(".post-body"); 
    var msgZone = body.children(".msgZone");
    if (!msgZone.length){               
        body.append("<div class=\"msgZone\"></div>");
        msgZone = body.children(".msgZone");                    
        msgZone.css("position","fixed");
        msgZone.css("width","200px");
        msgZone.css("top","0px");
        msgZone.css("right","0px");        
    }        
    msgZone.html(code);    
    msgZone.children(":last").css("width","100%");    
}

function removeMsg(id){       
    $(".msgZone #"+id).remove();  
}

function ajaxNavigating(vid){
    addMsg("<img id=\"plagicojload\" title=\"Loading\" alt=\"Loading...\" src=\"/images/plagicoj/cargando.gif\">");
    $.ajax({ 
        type: "POST", 
        url: "plagicojinspectionresultlist.xhtml", 
        data: "vid="+vid, 
        success: function(msg){    
            var newBody = $("#resultListDiv",msg);
            $("#resultListDiv").html(newBody.html());           
            removeMsg("plagicojload");            
        }
    });
              
}


function ajaxMcNavigating(vid){
    addMsg("<img id=\"plagicojload\" title=\"Loading\" alt=\"Loading...\" src=\"/images/plagicoj/cargando.gif\">");
    $.ajax({ 
        type: "POST", 
        url: "plagicojinspectionresultmatrix.xhtml", 
        data: "mcnav&vid="+vid, 
        success: function(msg){    
            var newBody = $(".plagMatrix",msg);
            $(".plagMatrix").replaceWith(newBody);
            removeMsg("plagicojload");           
             wsocketSubscribe('plagicoj', $(".plagMatrix").attr("response-socket-name"),onMatrixSocketMessage);
        }
    });
}
function ajaxMrNavigating(vid){
    addMsg("<img id=\"plagicojload\" title=\"Loading\" alt=\"Loading...\" src=\"/images/plagicoj/cargando.gif\">");
    $.ajax({ 
        type: "POST", 
        url: "plagicojinspectionresultmatrix.xhtml", 
        data: "mrnav&vid="+vid, 
        success: function(msg){    
            var newBody = $(".plagMatrix",msg);
            $(".plagMatrix").replaceWith(newBody);           
            removeMsg("plagicojload");           
            wsocketSubscribe('plagicoj', $(".plagMatrix").attr("response-socket-name"),onMatrixSocketMessage);
        }
    });
              
}
    function getHTMLcolorPerDictum(dictum ) {
        
        var index = parseInt(dictum * 10);
        if (index >= 10) {
            index = 9;
        }
        if (index < 0) {
            return "#AAAA00";
        }
        colorPerDictum = ["#00FF00", "#00FF55", "#00FFAA", "#00FFFF", "#0080FF", "#0000FF", "#8000FF", "#FF00FF", "#FF0080", "#FF0000"];
        return colorPerDictum[index];
    }


function onMatrixSocketMessage(response){    

            var message = response.responseBody;
            try {
                var json = atmosphere.util.parseJSON(message);
            } catch (e) {
                console.log('This doesn\'t look like a valid JSON: ', message);
                return;
            }

            if (json.message != undefined) {
                
                console.log(json.message);
                
                try {
                    var detectionAnswer = $.parseJSON(json.message);                   
                } catch (e) {
                    console.log('this is not a valid json ', message);
                    return;
                }
                
                var item = $( "<a/>", {
                    "class": "pcojresultlink",
                    "href": "/plagicoj/plagicojresult.xhtml?ssid="+detectionAnswer.ssid+"&didd="+detectionAnswer.dsid,                                       
                }).append($( "<b/>", {
                    text: parseInt(detectionAnswer.dictum * 100)+"%"
                }));
               var container =  $('.pcojResult[ssid="' +detectionAnswer.ssid + '"][dsid="' + detectionAnswer.dsid + '"] > a');
                   
                var color =getHTMLcolorPerDictum(detectionAnswer.dictum);                   
                container.parent().css("background-color",color);

                container.replaceWith(item);                   
            }
}

function formattedDictum(){    
    return Math.ceil(this.dictum*100.0)+"%";
}

function onListSocketMessage(response){
            var message = response.responseBody;
            try {
                var json = atmosphere.util.parseJSON(message);
            } catch (e) {
                console.log('This doesn\'t look like a valid JSON: ', message);
                return;
            }

            if (json.message !== undefined) {
                
                
                try {
                    var detectionAnswer = $.parseJSON(json.message);                   
                } catch (e) {
                    console.log('this is not a valid json ', message);
                    return;
                }
                var tableBody = $('#resultlist tbody');
                var elements = $('.dictumValue',tableBody);
               
               if (elements.size() === 49){
                    ajaxNavigating(1);                    
                    return;
               }
                
                var template = $('#listItemTpl').html();
                Mustache.parse(template);
                
                detectionAnswer.formattedDictum = formattedDictum;
                
                var rendered = Mustache.render(template, detectionAnswer);
                var inserted = false;
                
                elements.each(function(){
                    var dictumValue = $(this);
                    
                    if (parseFloat(dictumValue.attr("dictum")) < parseFloat(detectionAnswer.dictum)){
                        dictumValue.parents("tr").before(rendered);
                        inserted=true;
                        return false;
                    }
                });                
                if (!inserted && elements.size() < 50){
                   tableBody.append(rendered);
                }
                if (elements.size() >= 51){    
                    elements.last().parents("tr").remove();                    
                }
                
            }            
}

function wsocketSubscribe(category, responseSocketName , onMessage){
    var myName = false;
    var client = null;
    var logged = false;
    var socket = atmosphere;
    var subSocket;
    var transport = 'websocket';
    var fallbackTransport = 'long-polling';
    var connected = false;
    var uuid = 0;

    connect(category);

    function connect(category) {
        var request = { url: document.location.protocol+"//"+document.location.host + '/websocket/'+category,
            contentType: "application/json",
            logLevel: 'debug',
            transport: transport,
            trackMessageLength: true,
            reconnectInterval: 500,
            fallbackTransport: fallbackTransport};

        request.onOpen = function (response) {
            console.log('Atmosphere connected using ' + response.transport );
            transport = response.transport;
            uuid = response.request.uuid;
            
             subSocket.push(atmosphere.util.stringifyJSON({ client: responseSocketName, message: responseSocketName, uuid: uuid }));
        };

        request.onReopen = function (response) {
            console.log('Atmosphere re-connected using ' + response.transport);            
        };

        request.onMessage = onMessage;

        request.onClose = function (response) {
            subSocket.push(atmosphere.util.stringifyJSON({ client: client, message: 'disconnecting' }));
        };

        request.onError = function (response) {
            console.log('Sorry, but there\'s some problem with your socket or the server is down');
            logged = false;
        };

        request.onReconnect = function (request, response) {
            console.log('Connection lost, trying to reconnect. Trying to reconnect ' + request.reconnectInterval);            
        };

        subSocket = socket.subscribe(request);
    }

}