/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import cu.uci.coj.restapi.utils.Rate;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.stereotype.Service;

/**
 *
 * @author cesar
 */
@Service
public class RestRateLimitInterceptor extends HandlerInterceptorAdapter {


    Rate rate;
    Calendar calendar;
    
    public static Long time_between = new Long(60*1000);   //1 minuto por ahora.
    public static Integer cant_submit = 30;   //30 envios de solucion.

    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler)
            throws Exception {
        
        rate = Rate.getIsntance();
        calendar = Calendar.getInstance();
        
        if ( request.getRequestURL().toString().contains("/judgment/submit") || request.getRequestURL().toString().contains("/private/login")){
            //Resetear el contador si lo lleva
            ResetCount(request,rate);
            
            Integer cant = rate.getHost_contador().get(request.getRemoteAddr());
            if(cant == null)
                cant = 0;    
            rate.getHost_contador().put(request.getRemoteAddr(),cant+1);
            
            
            if(cant > cant_submit){
                response.setStatus(429);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"error\":\"Rate limit exceeded, wait one minute\"}");
                //response.sendError(429, "{\"error\":\"Rate limit exceeded, wait one minute\"}");                
                return false;
            }  
            
            //Ultima entrada de ese host;
            rate.getHost_time().put(request.getRemoteAddr(), calendar.getTimeInMillis());
            
            System.out.println(rate.getHost_contador().size());
            System.out.println(rate.getHost_contador());
            
            
        }
        
        
        
      
        return true;
    }
    
            
        
        public void ResetCount(HttpServletRequest request, Rate rate){
            Long time_old = rate.getHost_time().get(request.getRemoteAddr());
            if(time_old == null)
                return;
            if(time_old + time_between <= Calendar.getInstance().getTimeInMillis())
                rate.getHost_contador().put(request.getRemoteAddr(),0);
        }

}