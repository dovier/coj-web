/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.security;

import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author jasoria
 */
@Component("cojAuthenticationFailureHandler")
public class COJAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @PostConstruct
    protected void init(){
         setDefaultFailureUrl("/user/login.xhtml?login_error=1");
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        setDefaultFailureUrl(determineFailureUrl(request,exception));
        super.onAuthenticationFailure(request, response, exception);
    }
    
     protected String determineFailureUrl(HttpServletRequest request, AuthenticationException failed) {
        String uri = request.getParameter("rurl");
        if (uri == null || "null".equals(uri))
            uri =  "/index.xhtml";
        
        if (!uri.contains("?")) {
            uri += "?login_error=1";
        } else {
            uri += "&login_error=1";
        }
        return uri;
    }
}
