package test.board.controller;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cu.uci.coj.board.controller.WbBoardController;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PaginatedListImpl;
import cu.uci.coj.utils.paging.PagingOptions;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class WbBoardControllerTest {
	
	@InjectMocks
	WbBoardController wbBoardController;
	
	private MockMvc mockMvc;
	
	@Autowired
	@Mock
	private WbContestService wbContestServiceMock;
	
	@Autowired
	@Mock
	private UserDAO userDAOMock;
	
	@Autowired
	@Mock
	private WbSiteDAO wbSiteDAOMock;
	
	WbSite site1, site2;
	WbContest contest1, contest2;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(wbBoardController).build();
		
		site1 = new WbSite(1,"site1","url1","code1",false,false,1,"zone1");
		site2 = new WbSite(2,"site2","url2","code2",false,false,2,"zone2");
		
		contest1 = new WbContest("url1", "name1", 1, new Date(1), new Date(1));
		contest2 = new WbContest("url2", "name2", 2, new Date(2), new Date(2));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void listContests() throws Exception {		
		
		when( wbSiteDAOMock.getSiteList() ).thenReturn(Arrays.asList(site1, site2));
		
		mockMvc.perform(get ("/wboard/contests.xhtml"))
				.andExpect(status().isOk())
				.andExpect(view().name("/wboard/contests"))
				.andExpect(model().attribute("sites", hasSize(2)))
				.andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(1)),
                                hasProperty("site", is("site1")),
                                hasProperty("url", is("url1")),
                                hasProperty("code", is("code1")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(1)),
                                hasProperty("timezone", is("zone1"))
                        )
                )))
                .andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(2)),
                                hasProperty("site", is("site2")),
                                hasProperty("url", is("url2")),
                                hasProperty("code", is("code2")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(2)),
                                hasProperty("timezone", is("zone2"))
                        )
                )));
		verify(wbSiteDAOMock, times(1)).getSiteList();
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void tablesListContests() throws Exception {
		
		when( wbSiteDAOMock.getSiteList() ).thenReturn(Arrays.asList(site1, site2));
		
		IPaginatedList<WbContest> contests = new PaginatedListImpl<WbContest>();
		contests.setList(Arrays.asList(contest1, contest2));
		
		when( wbContestServiceMock.getContestList(eq(0), Matchers.any(PagingOptions.class) , eq(0), isNull(Integer.class) ) ).thenReturn(contests);		
		
		mockMvc.perform(get("/tables/wbcontests.xhtml"))
				.andExpect(status().isOk())
				.andExpect(view().name("/tables/wbcontests"))
				.andExpect(model().attribute("mapsites", hasKey(1)))
				.andExpect(model().attribute("mapsites", hasValue(site1)))
				.andExpect(model().attribute("mapsites", hasKey(2)))
				.andExpect(model().attribute("mapsites", hasValue(site2)))
				.andExpect(model().attribute("contests", hasProperty("list", hasSize(2))))
				.andExpect(model().attribute("contests", hasProperty("list", hasItem(
                        allOf(
                                hasProperty("name", is("name1")),
                                hasProperty("url", is("url1")),
                                hasProperty("sid", is(1)),
                                hasProperty("startDate", hasProperty("time", is(1L))),
                                hasProperty("endDate", hasProperty("time", is(1L)))
                        )
                ))))
				.andExpect(model().attribute("contests", hasProperty("list", hasItem(
				        allOf(
				                hasProperty("name", is("name2")),
				                hasProperty("url", is("url2")),
				                hasProperty("sid", is(2)),
				                hasProperty("startDate", hasProperty("time", is(2L))),
				                hasProperty("endDate", hasProperty("time", is(2L)))
				        )
				))));
			
		
		verify( wbSiteDAOMock, times(1) ).getSiteList();
		verifyNoMoreInteractions(wbSiteDAOMock);
		
		verify( wbContestServiceMock, times(1) ).getContestList(eq(0), Matchers.any(PagingOptions.class) , eq(0), isNull(Integer.class) );
		verifyNoMoreInteractions(wbContestServiceMock);
	}
	
	@Test
	public void followSite() throws Exception {
		int uid = 10;
		int sid = 1;
		
		when( userDAOMock.idByUsername( anyString() ) ).thenReturn(uid);
		
		mockMvc.perform(post("/wboard/follow.json").
				param("sid", String.valueOf(sid))
			)			
			.andExpect(status().isNoContent());
		
		verify(userDAOMock, times(1) ).idByUsername( anyString() );
		verifyNoMoreInteractions(userDAOMock);
		
		verify( wbSiteDAOMock, times(1) ).followSite(uid, sid);
	}
	
	@Test
	public void unfollowSite() throws Exception {
		int uid = 10;
		int sid = 1;
		
		when( userDAOMock.idByUsername( anyString() ) ).thenReturn(uid);
		
		
		mockMvc.perform(post("/wboard/unfollow.json").
				param("sid", String.valueOf(sid))
			)			
			.andExpect(status().isNoContent());
		
		verify(userDAOMock, times(1) ).idByUsername( anyString() );
		verifyNoMoreInteractions(userDAOMock);
		
		verify( wbSiteDAOMock, times(1) ).unfollowSite(uid, sid);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void loadComboboxAuthenticatedFollow() throws Exception {
		int uid = 100;
		
		when( userDAOMock.idByUsername( anyString() ) ).thenReturn(uid);
		when( wbSiteDAOMock.getSiteListFollowed(uid) ).thenReturn(Arrays.asList(site1, site2));
		
		mockMvc.perform(get("/wboard/combobox.xhtml?followed=1"))
			.andExpect(status().isOk())
			.andExpect(view().name("/wboard/combobox"))
			.andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(1)),
                                hasProperty("site", is("site1")),
                                hasProperty("url", is("url1")),
                                hasProperty("code", is("code1")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(1)),
                                hasProperty("timezone", is("zone1"))
                        )
                )))
                .andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(2)),
                                hasProperty("site", is("site2")),
                                hasProperty("url", is("url2")),
                                hasProperty("code", is("code2")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(2)),
                                hasProperty("timezone", is("zone2"))
                        )
                )))
                .andExpect(model().attribute("followed", is(1)));
		
		verify( userDAOMock, times(1) ).idByUsername( anyString() );
		verifyNoMoreInteractions(userDAOMock);
		
		verify( wbSiteDAOMock, times(1) ).getSiteListFollowed(uid);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void loadComboboxAuthenticatedNoFollow() throws Exception {
		int uid = 100;
		
		when( userDAOMock.idByUsername( anyString() ) ).thenReturn(uid);
		when( wbSiteDAOMock.getSiteList() ).thenReturn(Arrays.asList(site1, site2));
		when( wbSiteDAOMock.getIdSiteListFollowed(uid) ).thenReturn(Arrays.asList(1, 2));
		
		mockMvc.perform(get("/wboard/combobox.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/wboard/combobox"))
			.andExpect(model().attribute("mapIds", hasKey(1)))
			.andExpect(model().attribute("mapIds", hasKey(2)))
			.andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(1)),
                                hasProperty("site", is("site1")),
                                hasProperty("url", is("url1")),
                                hasProperty("code", is("code1")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(1)),
                                hasProperty("timezone", is("zone1"))
                        )
                )))
                .andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(2)),
                                hasProperty("site", is("site2")),
                                hasProperty("url", is("url2")),
                                hasProperty("code", is("code2")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(2)),
                                hasProperty("timezone", is("zone2"))
                        )
                )))
                .andExpect(model().attribute("followed", is(0)));
		
		verify( userDAOMock, times(1) ).idByUsername( anyString() );
		verifyNoMoreInteractions(userDAOMock);
		
		verify( wbSiteDAOMock, times(1) ).getIdSiteListFollowed(uid);
		
		verify( wbSiteDAOMock, times(1) ).getSiteList();
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void loadComboboxNoAuthenticatedNoFollow() throws Exception {
		
		when( userDAOMock.idByUsername( anyString() ) ).thenReturn(null);
		when( wbSiteDAOMock.getSiteList() ).thenReturn(Arrays.asList(site1, site2));
		
		mockMvc.perform(get("/wboard/combobox.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/wboard/combobox"))
			.andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(1)),
                                hasProperty("site", is("site1")),
                                hasProperty("url", is("url1")),
                                hasProperty("code", is("code1")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(1)),
                                hasProperty("timezone", is("zone1"))
                        )
                )))
                .andExpect(model().attribute("sites", hasItem(
                        allOf(
                                hasProperty("sid", is(2)),
                                hasProperty("site", is("site2")),
                                hasProperty("url", is("url2")),
                                hasProperty("code", is("code2")),
                                hasProperty("completed", is(false)),
                                hasProperty("enabled", is(false)),
                                hasProperty("timeanddateid", is(2)),
                                hasProperty("timezone", is("zone2"))
                        )
                )))
                .andExpect(model().attribute("followed", is(0)));
		
		verify( userDAOMock, times(1) ).idByUsername( anyString() );
		verifyNoMoreInteractions(userDAOMock);
		
		verify( wbSiteDAOMock, times(1) ).getSiteList();
		verifyNoMoreInteractions(wbSiteDAOMock);
	}	
}
