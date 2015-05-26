package cu.uci.coj.test.board.parsing.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.parsing.impl.WbCodechefParser;
import cu.uci.coj.board.util.ConnectionErrorException;
import cu.uci.coj.config.Config;
import cu.uci.coj.model.WbContest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class WbCodechefParserTest extends AbstractTransactionalJUnit4SpringContextTests {

	final static String CONTEST_NAME = "testname";
	final static String CONTEST_URL = "http://testurl.com";
	
	@Resource
	WbCodechefParser wbCodechefParser; 
	
	@Resource
	WbContestDAO wbContestDAO;
	
	@Resource
	WbSiteDAO siteDAO;
	
	@Test
	public void init() {
		Integer site = siteDAO.getSiteId( Config.getProperty("codechef.name") );
		assertTrue(site != null);
	}
	
	@Test
	public void checkParsing() {		
		try {
			Integer site = siteDAO.getSiteId( Config.getProperty("codechef.name") );
			
			List<WbContest> list = wbCodechefParser.parse();
			
			assertTrue(list != null);
			
			for(int i = 0;i<list.size();i++) {
				assert( list.get(i).getSid() == site );
			}
			
		} catch (ConnectionErrorException e) {
			assertTrue(false);
		}
	}	
}
