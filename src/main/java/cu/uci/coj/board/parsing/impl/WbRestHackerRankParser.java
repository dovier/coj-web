package cu.uci.coj.board.parsing.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.WbParser;
import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.config.Config;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.ws.models.HackerRankContest;
import cu.uci.coj.ws.models.WsContest;

/**
*
* @author Eddy Roberto Morales Perez
*/
@Component("wbRestHackerRankParser")
@DependsOn(value={"proxy", "config"})
public class WbRestHackerRankParser extends WbParser {
	private String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
			
	//2015-07-19T16:00:00.000Z
	
	@Resource
	WbSiteDAO wbSiteDAO;
	
	@Resource
	RestTemplate restTemplate;
	
    public WbRestHackerRankParser() {
        this.siteUrl = Config.getProperty("hackerrank.parsing.url");
    }    
    
    @PostConstruct
    public void init() {
    	site = wbSiteDAO.getSiteByUrl(Config.getProperty("hackerrank.url"));
    	if(site == null) {
    		site = new WbSite(0, Config.getProperty("hackerrank.name"), Config.getProperty("hackerrank.url"), Config.getProperty("hackerrank.code"),
    				false, true, Integer.parseInt(Config.getProperty("hackerrank.timeanddateid")), Config.getProperty("hackerrank.timeregion"));
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
        dateEnd.setTime((long) 1e18);
        
        String preview = "hackerrank_new.png";
        List<WbContest> contests = new LinkedList<WbContest>();
        
        String restUrl = siteUrl + "start=" + dateBegin.getTime()/1000 + "&end=" + dateEnd.getTime();
        
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        HackerRankContest[] response = restTemplate.getForObject(restUrl, HackerRankContest[].class);
        
        try {        
	        for(int i = 0;i<response.length;i++) 
	        	if(response[i].getPreview().equals(preview)) {
		        	strUrl = response[i].getUrl();
		        	strName = response[i].getTitle();
		        	
					dateBegin = format.parse(transform(response[i].getStart()));
					dateEnd = format.parse(transform(response[i].getEnd()));
		        	
		        	WbContest contest = new WbContest(strUrl, strName, site.getSid(), dateBegin, dateEnd);
		            contests.add(contest);
		        }    
	        
    	} catch (ParseException e) {
			e.printStackTrace();
    	}   
        
        return contests;
    } 
    
    //2015-07-19T16:00:00.000Z
    private String transform(String ss) {
    	return ss.substring(0, 10) + " " + ss.substring(11, 19);
    }
}
