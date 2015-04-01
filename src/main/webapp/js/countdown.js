function Countdown(server_time, time_access) {

	function getFormatTime(timer)	{
	    var h = parseInt(timer / 3600);
	    var m = parseInt(timer % 3600 / 60);
	    var s = parseInt((timer % 60).toFixed(0));
	    var c = parseInt(timer % 1 * 10);
	    var d = parseInt((h + 12) / 24);
	    if (h < 10) h = '0' + h;
	    if (m < 10) m = '0' + m;
	    if (s < 10 && h + m > 0) s = '0' + s;

	    if (d > 2) return d + ' d';
	    return h + ':' + m + ':' + s;
	}
	
	return {
		//Countdown till data-start-date and then countdown till data-end-date
		countdown2: function() {
			var s;
			$(".countdown2").each(function(){
				var start = parseInt($(this).data("start-date"));
				var end = parseInt($(this).data("end-date"));
		    	var timer = start - (server_time + (new Date().getTime() - time_access));
		        
		    	if (timer < 0) {
		    		if(end - start + timer > 0) {
		    			s = getFormatTime((end - start + timer)/1000); 
		    		} else {
		    			s = "--:--:--";
		    		}
		        }
		        else {
		            s = getFormatTime(timer / 1000);
		        }

		        $(this).text(s);		
			}); 
		},
		
		//Countdown till data-date
		countdown: function() {
			var s;
			$(".countdown").each(function(){
				var date = parseInt($(this).data("date"));
		    	var timer = date - (server_time + (new Date().getTime() - time_access));
		        
		    	if (timer < 0) {
		    		s = "--:--:--";
		        }
		        else {
		            s = getFormatTime(timer / 1000);
		        }

		        $(this).text(s);		
			}); 
		},
		
		//Countup from data-start-date till data-end-date
		countup: function() {
			var s;			
			$(".countup").each(function(){
				var start = parseInt($(this).data("start-date"));
				var end = parseInt($(this).data("end-date"));
		    	var timer = (server_time + (new Date().getTime() - time_access)) - start;		        
		    	
		    	if (timer > end - start) {
		    		s = "--:--:--";
		        }
		        else {
		            s = getFormatTime(timer / 1000);
		        }

		        $(this).text(s);		
			}); 
		}
	};	
}