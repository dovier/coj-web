package cu.uci.coj.controller;

import java.io.Serializable;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;

import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.model.Roles;

public class BaseController implements ServletContextAware, Serializable{
    
	@Resource
	protected BaseDAO baseDAO;
    @Resource
    protected MessageSource messageSource;
    @Autowired 
    private ServletContext servletContext;
    
    @InitBinder
    public void bindingPreparation(WebDataBinder binder) {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd HH:mm:ss.0");
      CustomDateEditor dateEditor = new CustomDateEditor(dateFormat, true);
      binder.registerCustomEditor(Date.class, dateEditor);
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
     
    public String getUsername(Principal principal){
        return principal == null ? null : principal.getName();
    }
    
    public Integer getUid(Principal principal){
        return principal == null ? null : baseDAO.idByUsername(principal.getName());
    }
    
    public boolean hasAnyPrivileges(HttpServletRequest request){
        return request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole(Roles.ROLE_SUPER_PSETTER) || request.isUserInRole(Roles.ROLE_PSETTER);
    }
    
    public boolean hasAnyPrivileges(HttpServletRequest request,String... roles){
    	for (String role:roles)
    		if (request.isUserInRole(role))
    			return true;
    	return false;
    }

	@Override
	public void setServletContext(ServletContext servletContext) {
		
	}
}
