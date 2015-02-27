/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Loader---------------
var cSpeed=10;
	var cWidth=128;
	var cHeight=128;
	var cTotalFrames=12;
	var cFrameWidth=128;
	var cImageSrc='/images/sprites.png';
	
	var cImageTimeout=false;
	var cIndex=0;
	var cXpos=0;
	var cPreloaderTimeout=false;
	var SECONDS_BETWEEN_FRAMES=0;
	
	function startAnimation(){
		document.getElementById('loaderImage').style.backgroundImage='url('+cImageSrc+')';
		document.getElementById('loaderImage').style.backgroundImage='url('+cImageSrc+')';
		document.getElementById('loaderImage').style.width=cWidth+'px';
		document.getElementById('loaderImage').style.height=cHeight+'px';
		
		//FPS = Math.round(100/(maxSpeed+2-speed));
		FPS = Math.round(100/cSpeed);
		SECONDS_BETWEEN_FRAMES = 1 / FPS;
		
		cPreloaderTimeout=setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES/1000);
		
	}
	
	function continueAnimation(){
		
		cXpos += cFrameWidth;
		//increase the index so we know which frame of our animation we are currently on
		cIndex += 1;
		 
		//if our cIndex is higher than our total number of frames, we're at the end and should restart
		if (cIndex >= cTotalFrames) {
			cXpos =0;
			cIndex=0;
		}
		
		if(document.getElementById('loaderImage'))
			document.getElementById('loaderImage').style.backgroundPosition=(-cXpos)+'px 0';
		
		cPreloaderTimeout=setTimeout('continueAnimation()', SECONDS_BETWEEN_FRAMES*1000);
	}
	
	function stopAnimation(){//stops animation
		clearTimeout(cPreloaderTimeout);
		cPreloaderTimeout=false;
	}
	        
	//The following code starts the animation
        
	//new imageLoader(cImageSrc, 'startAnimation()');
        //*****************

//combobox
        function evaluate() {
            if($('#states').val()==="User"){
              // $("#textbox").width(150);
               $("#textbox").fadeIn();
           }
           else if ($('#states').val()==="Nationality"){
            $.ajax({
            url:"/json/xtats/getcountries.json",
            type: 'GET',
            dataType: 'json',
            success:function(datos){
                var nor = new Array();
                for (var i = 0; i < datos.length; i++) {                    
                    nor.push({label: datos[i][0], value: datos[i][1]});
                
                }    
           // $("#textbox").width(200);    
            $("#textbox").fadeIn();
            $("#textbox").wijcombobox({data:nor});            
            }
            });
               
           }
           else if ($('#states').val()==="Institution"){
            $.ajax({
            url:"/json/xtats/getinstitution.json",
            type: 'GET',
            dataType: 'json',
            success:function(datos){
                var nor = new Array();
                for (var i = 0; i < datos.length; i++) {                    
                    nor.push({label: datos[i][0], value: datos[i][1]});
                
                } 
            
            $("#textbox").fadeIn();
            $("#textbox").wijcombobox({data:nor});            
            }
            });
           }
           else{
               $("#textbox").empty();
               $("#textbox").fadeOut();
           }
           
        }
   
   function generateClasif(){
            var cid=$('#textbox1').val();
                  $.ajax({
            url:"/json/xtats/getproblemclasif.json",
            type: 'GET',
            data:{"textbox1":cid},
            dataType: 'json',
            success:function(datos){
                var nor = new Array();
                for (var i = 0; i < datos.length; i++) {                    
                    nor.push({label: datos[i][0], value: datos[i][1]});
                
                }    
            
            $("#textbox2").wijcombobox({data:nor});            
            }
            });
        
        }
        
        function generateUsers(){
            var cid=parseInt($('#textbox1').val());
            $.ajax({
            url:"/json/xtats/usersincontest.json",
            type: 'GET',
            data:{"textbox1":cid},
            dataType: 'json',
            success:function(datos){
                var nor = new Array();
                for (var i = 0; i < datos.length; i++) {                    
                    nor.push({label: datos[i][0], value: datos[i][1]});
                
                }    
            
            $("#textbox").wijcombobox({data:nor});            
            }
            });
            
        }
        
function ValidateDate(x,y){
     var idate = new Date(x.toString());
     var edate = new Date(y.toString());           
    try{        
        if(edate > idate){
            if((edate.getTime()-idate.getTime())/(1000 * 60 * 60 * 24) > 30 && (edate.getTime()-idate.getTime())/(1000 * 60 * 60 * 24) < 730){
                return 'yyyy month';
            }
            else if((edate.getTime()-idate.getTime())/(1000 * 60 * 60 * 24) < 30){
                return 'yyyy/mm/dd';
            }
            else if((edate.getTime()-idate.getTime())/(1000 * 60 * 60 * 24) > 730){
                return 'yyyy';
            }
        }
    }
    catch(e){
        e= new Error("Error en intervalo de fechas");
        return e;
    }
}



