package cu.uci.coj.board.parsing.impl;

import java.io.File;
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
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.WbParser;
import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.config.Config;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

@Component("wbTimusParser")
@DependsOn(value={"proxy", "config"})
public class WbTimusParser  extends WbParser  {
	 private String DATE_FORMAT = "dd MMM yyyy hh:mm";
	    
		@Resource
		WbSiteDAO wbSiteDAO;
		
		private String contestUrl;
		private String url;

	    public WbTimusParser() {
	    	this.url = Config.getProperty("timus.url");
	    	this.siteUrl = Config.getProperty("timus.parsing.url");
	        this.contestUrl = Config.getProperty("timus.parsing.url.contest");;
	    }
	    
	    @PostConstruct
	    public void init() {
	    	site = wbSiteDAO.getSiteByUrl(Config.getProperty("timus.url"));
	    	if(site == null) {
	    		site = new WbSite(0, Config.getProperty("timus.name"), Config.getProperty("timus.url"), Config.getProperty("timus.code"),
	    				false, true, Integer.parseInt(Config.getProperty("timus.timeanddateid")), Config.getProperty("timus.timeregion"));
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
	        	System.out.println("pagina: "+doc.html());
	        } catch (IOException ex) {
	            throw new ConnectionErrorException(site.getSite());
	        }        
	               
	        Elements entries = doc.getElementsByAttributeValueStarting("href", contestUrl +  "?id=");        
	                
	        for(int i = 0;i<entries.size();i++) {
	            Element entry = entries.get(i);	            
	                        
	            strName = entry.html();
	            strUrl = url + "/" + entry.attr("href");
	            
	            Document doc2 = null;	            
	            try {     
	            	doc2 = Jsoup.connect(siteUrl).timeout(20 * 1000).get();
		        	doc2 = Jsoup.connect(strUrl).timeout(20 * 1000).get();
		        } catch (IOException ex) {
		        	ex.printStackTrace();
		            throw new ConnectionErrorException(site.getSite());
		        }
	            
	            Elements elements = doc2.getElementsByTag("b");
	            strBegin = elements.get(0).html();
	            strEnd = elements.get(1).html();
	            
	            try {
	            	System.out.println("strBegin " + strBegin);
	                dateBegin = format.parse(strBegin);
	                dateEnd = format.parse(strEnd);
	            } catch (ParseException ex) {
	            	ex.printStackTrace();
	                return null;
	            }                   
	            
	            
	            WbContest contest = new WbContest(strUrl, strName, site.getSid(), dateBegin, dateEnd);
	            contests.add(contest);
	        }
	        
	        return contests;
	    }    
}
