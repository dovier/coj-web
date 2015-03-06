package cu.uci.coj.board.parsing.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.WbParser;
import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.config.Config;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.ws.models.WsContest;

/**
*
* @author Eddy Roberto Morales Perez
*/


@Component("wbRestCojParser")
public class WbRestCojParser extends WbParser {

	@Resource
	WbSiteDAO wbSiteDAO;
    
	@Resource
	RestTemplate restTemplate;
	
    public WbRestCojParser() {
        this.siteUrl = Config.getProperty("coj.parsing.url");
    }    
    
    @PostConstruct
    public void init() {
    	site = wbSiteDAO.getSiteByUrl(Config.getProperty("coj.url"));
    	if(site == null) {
    		site = new WbSite(0, Config.getProperty("coj.name"), Config.getProperty("coj.url"), Config.getProperty("coj.code"),
    				false, true, Integer.parseInt(Config.getProperty("coj.timeanddateid")), Config.getProperty("coj.timeregion"));
    		int sid = wbSiteDAO.insertSite(site);
    		site.setSid(sid);
    	} 
    }
    
    @Override
    public List<WbContest> parse() throws ConnectionErrorException {
    	String strName = "";
        String strUrl = "";
    	
    	Date dateBegin = new Date();
        Date dateEnd = new Date();
        List<WbContest> contests = new LinkedList<WbContest>();
        
        WsContest[] response = restTemplate.getForObject(siteUrl, WsContest[].class);       
   
        for(int i = 0;i<response.length;i++) {
        	strUrl = site.getUrl() + "/contest/contestview.xhtml?cid=" + String.valueOf(response[i].getCid());
        	strName = response[i].getName();
        	dateBegin = response[i].getInitdate();
        	dateEnd = response[i].getEnddate();
        	
        	WbContest contest = new WbContest(strUrl, strName, site.getSid(), dateBegin, dateEnd);
            contests.add(contest);
        }               
        
        return contests;
    }  
}
