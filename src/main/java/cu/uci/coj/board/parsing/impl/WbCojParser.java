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
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;


/**
*
* @author Eddy Roberto Morales Perez
*/

//@Component("wbCojParser")
public class WbCojParser extends WbParser {
	private String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	@Resource
	WbSiteDAO wbSiteDAO;
    
    public WbCojParser() {
        this.siteUrl = "http://coj.uci.cu/tables/coming.xhtml";
    }    
    
    @PostConstruct
    public void init() {
    	site = wbSiteDAO.getSiteByUrl(siteUrl);
    	if(site == null) {
    		site = new WbSite(0, "Caribbean Online Judge", siteUrl, "COJ", false, true, 99, "America/Havana");
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
        
        Elements entries = doc.select("#contest > tbody > tr");      
        
        for(int i = 0;i<entries.size();i++) {
            Element entry = entries.get(i);
            strName = entry.child(2).getElementsByTag("a").get(0).text();
            strUrl = entry.child(2).getElementsByTag("a").get(0).attr("abs:href");
            strBegin = entry.child(3).getElementsByTag("a").text();
            strEnd = entry.child(4).getElementsByTag("a").text();
            
            try {
                dateBegin = format.parse(strBegin);
                dateEnd = format.parse(strEnd);
            } catch (ParseException ex) {
            	System.out.println("Wrong");
                return null;
            }          
 

            WbContest contest = new WbContest(strUrl, strName, site.getSid(), dateBegin, dateEnd);
            contests.add(contest);            
        }
        
        return contests;
    }  
}