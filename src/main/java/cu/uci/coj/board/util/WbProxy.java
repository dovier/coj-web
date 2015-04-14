package cu.uci.coj.board.util;



import java.net.Authenticator;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import cu.uci.coj.config.Config;

/**
*
* @author Eddy Roberto Morales Perez
*/


@Component("proxy")
@DependsOn("config")
public class WbProxy {	
	final static String proxy = Config.getProperty("system.proxy");
	final static String port = Config.getProperty("system.port");
	final static String noProxy = Config.getProperty("system.noproxyfor");
	final static String username = Config.getProperty("system.username");
	final static String password = Config.getProperty("system.password");	
	
	
	@PostConstruct
    public void setProxy() {    
    	Authenticator.setDefault(new SimpleAuthenticator(username, password));
    	System.setProperty("http.proxyHost", proxy);
		System.setProperty("http.proxyPort", port);
		System.setProperty("http.nonProxyHosts", noProxy);
    }
}
