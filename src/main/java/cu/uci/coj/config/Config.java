package cu.uci.coj.config;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import cu.uci.coj.model.Status;

@Component
@PropertySource({"classpath:cu/uci/coj/config/config.properties"})
public class Config {

    @Autowired
    protected Environment env;
    @Autowired
    protected static ApplicationContext appCtx;
    protected static Config config = null;
    
    @PostConstruct
    public void init(){
    	Status.keyToStatus = new HashMap<String,String>();
    	Status.statusToKey = new HashMap<String,String>();
		String[] ar = Config.getProperty("judge.status").split(",");
		for (int i = 0; i < ar.length; i++) {
			String string = ar[i];
			Status.keyToStatus.put(Config.getProperty(string.replaceAll(" ", ".")),string);
			Status.keyToStatus.put(string,Config.getProperty(string.replaceAll(" ", ".")));
		}
    }
    
    @Autowired
    public void setApplicationContext(ApplicationContext appCtx){
        this.appCtx = appCtx;
    }
    
    private static Config getConfig(){
        if (config == null) {
            config = appCtx.getBean(Config.class);
        }
        return config;
    }
    
    public static String getProperty(String key) {
        return getConfig().env.getProperty(key);
    }
    
    
}
