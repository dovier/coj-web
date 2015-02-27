/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.security;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import cu.uci.coj.dao.UserDAO;

@Component("cojAuthenticationSuccessHandler")
public class COJAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    
    @Resource
    private UserDAO userDAO;
    
    @PostConstruct
    protected void init() {
        setDefaultTargetUrl("/");
        setAlwaysUseDefaultTargetUrl(false);
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
        String username = ((User)authentication.getPrincipal()).getUsername();
        String locale = userDAO.getUserLocale(username);
        userDAO.dml("update.last.ip",request.getRemoteAddr(), username);
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setLocale(request, response, new Locale(locale));
        setDefaultTargetUrl(determineTargetUrl(request));
        
        userDAO.dml("insert.log", "user logged in",username);
        super.onAuthenticationSuccess(request, response, authentication);
    }
    
    
    protected String determineTargetUrl(HttpServletRequest request) {
        String uri = request.getParameter("rurl");
        if (!uri.contains(".xhtml")) {
            return "/index.xhtml";
        }
        return uri;
    }
}
