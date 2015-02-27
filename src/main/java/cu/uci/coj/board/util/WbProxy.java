package cu.uci.coj.board.util;

import java.net.Authenticator;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Component
public class WbProxy {
	
	final static String proxy = "10.0.0.1", port = "8080",
			username = "uci.cu\\", password = "";
	
	final static String noProxy = "localhost|*.uci.cu";
	
	@PostConstruct
    public static void setProxy() {    
    	Authenticator.setDefault(new SimpleAuthenticator(username, password));
		System.setProperty("http.proxyHost", proxy);
		System.setProperty("http.proxyPort", port);
		System.setProperty("http.nonProxyHosts", noProxy);
    }
}
