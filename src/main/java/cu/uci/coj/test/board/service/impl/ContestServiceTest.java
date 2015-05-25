package cu.uci.coj.test.board.service.impl;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.utils.paging.PagingOptions;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class ContestServiceTest {
	
	@Autowired
	@Mock
	WbContestDAO WbContestDAOMock;
	
	@Autowired
	@Mock
	WbSiteDAO wbSiteDAOMOck;
	
	@Autowired
	@InjectMocks
	WbContestService wbContestService;
	
	WbContest contest;
	PagingOptions pagingOptions;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		contest = new WbContest("url1", "name1", 1, new Date(1), new Date(1));
		pagingOptions = new PagingOptions();
	}
	
	@Test
	public void addToDatabaseAndCreateNotificationNewContest() {
		when( WbContestDAOMock.getContestByName(contest.getName()) ).thenReturn( null );
		
		wbContestService.addToDatabaseAndCreateNotification(contest);
		
		verify(WbContestDAOMock, times(1)).getContestByName(contest.getName());
		verify(WbContestDAOMock, times(1)).insertContest(contest);
		verifyNoMoreInteractions(WbContestDAOMock);
	}
	
	@Test
	public void addToDatabaseAndCreateNotificationUpdateContest() {
		WbContest old = new WbContest("url1", "name1", 1, new Date(2), new Date(2));
		old.setId(100);
	
		when( WbContestDAOMock.getContestByName(contest.getName()) ).thenReturn( old );
		
		wbContestService.addToDatabaseAndCreateNotification(contest);
		
		verify(WbContestDAOMock, times(1)).getContestByName(contest.getName());
		verify(WbContestDAOMock, times(1)).updateContest(contest);
		
		assertTrue(contest.getId() == old.getId());
		verifyNoMoreInteractions(WbContestDAOMock);
	}
	
	@Test
	public void addToDatabaseAndCreateNotificationNoUpdateContest() {
		WbContest old = new WbContest("url1", "name1", 1, new Date(1), new Date(1));
		old.setId(100);
	
		when( WbContestDAOMock.getContestByName(contest.getName()) ).thenReturn( old );
		
		wbContestService.addToDatabaseAndCreateNotification(contest);
		
		verify(WbContestDAOMock, times(1)).getContestByName(contest.getName());
		verifyNoMoreInteractions(WbContestDAOMock);
	}
	
	@Test
	public void getContestListFollowed() {
		wbContestService.getContestList(1, pagingOptions, 1, 0);
		verify( WbContestDAOMock, times(1) ).getContestListFavorites(anyInt(), eq(pagingOptions), anyInt());
	}
	
	@Test
	public void getContestListNoFollowed() {
		wbContestService.getContestList(1, pagingOptions, 0, 0);
		verify( WbContestDAOMock, times(1) ).getContestList(anyInt(), eq(pagingOptions));
	}
}
