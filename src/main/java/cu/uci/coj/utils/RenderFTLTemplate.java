package cu.uci.coj.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;

@Component
public class RenderFTLTemplate {
	@Autowired
	private ServletContext servletContext;	
	private Configuration cfg = new Configuration();
	
	
	@PostConstruct
	private void init(){
		String baseFile = servletContext.getRealPath("WEB-INF/ftl");
		try {
			cfg.setDirectoryForTemplateLoading(new File(baseFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String render(String template, Map<String, Object> model) {
		String result = "";		
		try {			
			result = FreeMarkerTemplateUtils.processTemplateIntoString(cfg.getTemplate(template), model);
		} catch (TemplateException | IOException e) {			
			e.printStackTrace();
		}		
		return result;
	}
}
