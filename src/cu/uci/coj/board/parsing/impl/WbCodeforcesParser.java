package cu.uci.coj.board.parsing.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

@Component("wbCodeforcesParser")
public class WbCodeforcesParser extends WbParser {

	private String DATE_FORMAT = "MMM/dd/yyyy HH:mm";
	
	@Resource
	WbSiteDAO wbSiteDAO;

	public WbCodeforcesParser() {
		this.siteUrl = "http://codeforces.com/contests";
	}
	
    @PostConstruct
    public void init() {
    	site = wbSiteDAO.getSiteByUrl(siteUrl);
    	if(site == null) {
    		site = new WbSite(0, "Codeforces", siteUrl, "Codeforces", false, true, 166, "Europe/Moscow");
    		int sid = wbSiteDAO.insertSite(site);
    		site.setSid(sid);
    	} 
    }

	@Override
	public List<WbContest> parse() throws ConnectionErrorException {
		Document doc = null;
		String strName = "";
		String strUrl = "http://www.codeforces.com/contests";
		String strBegin = "";
		String[] strDuration;
		int intDurationHours = 0;
		int intDurationMinutes = 0;

		Date dateBegin = new Date();
		Date dateEnd = new Date();

		List<WbContest> contests = new LinkedList<WbContest>();
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);

		try {
			doc = Jsoup.connect(siteUrl).timeout(20*1000).get();
		} catch (IOException ex) {
			throw new ConnectionErrorException(site.getSite());
		}

		Element content = doc.getElementsByTag("table").first();

		Elements entries = content.getElementsByTag("tr");

		for (int i = 1; i < entries.size(); i++) {
			Element entry = entries.get(i);

			strName = entry.child(0).text();
			strBegin = entry.child(1).getElementsByTag("a").get(0).text();
			strDuration = entry.child(2).text().split(":");

			try {
				dateBegin = format.parse(strBegin);

				intDurationHours = Integer.parseInt(strDuration[0]);
				intDurationMinutes = Integer.parseInt(strDuration[1]);

				Calendar cal = Calendar.getInstance();
				cal.setTime(dateBegin);
				cal.add(Calendar.HOUR_OF_DAY, intDurationHours);
				cal.add(Calendar.MINUTE, intDurationMinutes);

				dateEnd = new Date(cal.getTimeInMillis());

			} catch (ParseException ex) {
				return null;
			}

    
			WbContest contest = new WbContest(strUrl, strName, site.getSid(), dateBegin, dateEnd);
			contests.add(contest);
		}

		return contests;
	}

}