package cu.uci.coj.board.parsing.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.WbParser;
import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.config.Config;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Component("wbCodechefParser")
public class WbCodechefParser extends WbParser {
    
    private String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
    
	@Resource
	WbSiteDAO wbSiteDAO;

    public WbCodechefParser() {
        this.siteUrl = Config.getProperty("codechef.parsing.url");
    }
    
    @PostConstruct
    public void init() {
     	site = wbSiteDAO.getSiteByUrl(Config.getProperty("codechef.url"));
    	if(site == null) {
    		site = new WbSite(0, Config.getProperty("codechef.name"), Config.getProperty("codechef.url"), Config.getProperty("codechef.code"),
    				false, true, Integer.parseInt(Config.getProperty("codechef.timeanddateid")), Config.getProperty("codechef.timeregion"));
    		int sid = wbSiteDAO.insertSite(site);
    		site.setSid(sid);
    	}    	
    }
    

    @Override
    public List<WbContest> parse() throws ConnectionErrorException {
        Document doc = null;
        String strName = "";
        String strUrl = "";
        String strBegin = "";
        String strEnd = "";  
        
        Date dateBegin = new Date();
        Date dateEnd = new Date();
        
        List<WbContest> contests = new LinkedList<WbContest>();        
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);        
                
        try {
            doc = Jsoup.connect(siteUrl).timeout(20 * 1000).get();
        } catch (IOException ex) {
            throw new ConnectionErrorException(site.getSite());
        }       
        
        Elements tables = doc.getElementsByClass("table-questions");   
               
        for(int k = 0;k<2;k++) {        
	        Elements entries = tables.get(k).getElementsByTag("tr");        
	                
	        for(int i = 1;i<entries.size();i++) {
	            Element entry = entries.get(i); 
	            
	            strName = entry.child(1).getElementsByTag("a").get(0).text();
	            strUrl = siteUrl + entry.child(1).getElementsByTag("a").get(0).attr("href");
	            strBegin = entry.child(2).text();
	            strEnd = entry.child(3).text();
	
	            try {
	                dateBegin = format.parse(strBegin);
	                dateEnd = format.parse(strEnd);
	            } catch (ParseException ex) {
	                return null;
	            }                   
	            
	            
	            WbContest contest = new WbContest(strUrl, strName, site.getSid(), dateBegin, dateEnd);
	            contests.add(contest);
	        }
        }
        
        return contests;
    }    
}