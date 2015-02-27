<%@include file="/WEB-INF/jsp/include/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page buffer="16kb" autoFlush="true"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xml:lang="en" dir="ltr" xmlns="http://www.w3.org/1999/xhtml" lang="en-US">
    <head>
        <title>The 2013 Caribbean Finals of the ACM-ICPC - Live ScoreBoard</title>
        <script type="text/javascript" src="/js/lsb/animator.js"></script>
        <script type="text/javascript" src="/js/lsb/jquery-1.4.3.js"></script>
        <script type="text/javascript" src="/js/lsb/scoreboard.js"></script>
        <script type="text/javascript" src="/js/lsb/rankingTableUpdate.js"></script>
        <script type="text/javascript" src="/js/lsb/jquery.scrollTo-min.js" ></script>
        <link rel="stylesheet" type="text/css" href="/css/lsb/style.css"/>         
    </head>
    <body>

        <div id="aqui" style="display: none;"></div>
        <div id="top"></div>
        <script type="text/javascript">
            <!--$("#aqui").load("/boca/score/score.php?lsb=1 table#myscoretable");-->
            setCid("${cid}");
            $("#aqui").load("/contest/cscoreboard.xhtml?cid=${cid} table#myscoretable");            
        </script>
        <div id="stocks" style="width: 100%">
            <div id="caribe" style="clear: both;float: left;">
                <img alt="" src="/images/lsb/Carib.png" style="width:200px;height:90px"/>
            </div>
            <div style="float: left; position:absolute; width: 99%;">
                <center>
                    <h3 style="color: #039;">The 2013 Caribbean Finals of the ACM-ICPC</h3>
                    <h3 style="color: #039">Live ScoreBoard</h3>
                </center>
            </div>
            <div id="icpc" style="text-align: right;"><img alt="" src="/images/lsb/icpc.png" style="width:200px;height:110px"/></div>
            <br/>
            <p class="status"> </p>
            <div class="tableHolder" style="clear: both;">
                <a href="#" class="get">Get LSB</a>
                <a href="#" class="triggerUpdates">Start "live" updates</a>
            </div>
        </div>
        <div><p class="bottom"> </p></div>
    </body>
</html>


<script type="text/javascript">
    var loop = null;
    var flag = true;
    var interval = 60000;

       var getFunc = function(){
           loop = setInterval(loopFunc,interval);
       }

        var loopFunc = function(){
            if(flag){
                $.scrollTo($('div#top'),{duration:14000});
                flag = false;
                interval =43000;
               clearInterval(loop);
               getFunc();
            }else{
                $.scrollTo($('p.bottom'),{duration:14000});
                flag = true;
               interval =10000;
               clearInterval(loop);
               getFunc();
            }
       }
    if(!loop){
//        loopFunc();
        loop = setInterval(loopFunc, interval);
    }     
</script>
