package cu.uci.coj.utils;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class COJContextLoaderListener extends ContextLoaderListener {
	private static WebApplicationContext ctx;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);                
		ctx = ContextLoader.getCurrentWebApplicationContext();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		super.contextDestroyed(event);
		ctx=null;
	}

	public static WebApplicationContext getCtx() {
		return ctx;
	}
}
