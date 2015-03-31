package cu.uci.coj.board.parsing;

import java.util.List;

/**
*
* @author Eddy Roberto Morales Perez
*/



import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

public abstract class WbParser {

	protected WbSite site;
	protected String siteUrl;

	public abstract void init();
	
	public abstract List<WbContest> parse()  throws ConnectionErrorException;
	
	public WbSite getSite() {
		return site;
	}

	public void setSite(WbSite site) {
		this.site = site;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}	
}