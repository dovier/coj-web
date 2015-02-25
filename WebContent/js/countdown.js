function getFormatTime(timer)
{
    var h = parseInt(timer / 3600);
    var m = parseInt(timer % 3600 / 60);
    var s = parseInt(timer % 60);
    var c = parseInt(timer % 1 * 10);
    var d = parseInt((h + 12) / 24);
    if (h < 10) h = '0' + h;
    if (m < 10) m = '0' + m;
    if (s < 10 && h + m > 0) s = '0' + s;

    if (d > 2) return d + ' days';
    if (m + h > 0) return h + ':' + m + ':' + s;
    return s + '.' + c;
}

function countDown(server_time, time_access)
{		
	var s;
	var a = document.getElementsByClassName("countdown");
    for (var i = 0; i < a.length; i++)
    {
    	var start = parseInt(a[i].getAttribute("start-data-time"));
    	var end = parseInt(a[i].getAttribute("end-data-time"));
    	var timer = start - (server_time + (new Date().getTime() - time_access));
        
    	if (timer < 0)
        {
    		if(end - start + timer > 0) {
    			s = getFormatTime((end - start + timer)/1000); 
    		} else {
    			s = "--:--:--";
    		}
        }
        else
        {
            s = getFormatTime(timer / 1000);
        }

        if (s != a[i].innerHTML) a[i].innerHTML = s;
    }   
}