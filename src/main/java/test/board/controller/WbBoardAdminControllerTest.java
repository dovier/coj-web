package test.board.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.controller.admin.WbBoardAdminController;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PaginatedListImpl;
import cu.uci.coj.utils.paging.PagingOptions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml", "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml"})
@WebAppConfiguration
public class WbBoardAdminControllerTest {
	
	@InjectMocks
	WbBoardAdminController wbBoardAdminController;
	
	private MockMvc mockMvc;
	
	@Autowired
	@Mock
	private UserDAO userDAOMock;
	
	@Autowired
	@Mock
	private WbSiteDAO wbSiteDAOMock;
	
	@Autowired
	@Mock
	private WbContestDAO wbContestDAOMock;
	
	WbSite site1, site2;
	WbContest contest1, contest2;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(wbBoardAdminController).build();
		
		site1 = new WbSite(1,"site1","url1","code1",false,false,1,"zone1");
		site2 = new WbSite(2,"site2","url2","code2",false,false,2,"zone2");
		
		contest1 = new WbContest("url1", "name1", 1, new Date(1), new Date(1));
		contest2 = new WbContest("url2", "name2", 2, new Date(2), new Date(2));
	}
	
	@Test
	public void manageParsers() throws Exception {
		mockMvc.perform(get("/admin/manageparsers.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/manageparsers"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void tablesManageParsers() throws Exception {
		when( wbSiteDAOMock.getSiteList() ).thenReturn( Arrays.asList(site1, site2) );
		
		mockMvc.perform(get ("/admin/tables/manageparsers.xhtml"))
				.andExpect(status().isOk())
				.andExpect(view().name("/admin/tables/manageparsers"))
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
	public void enableParsers() throws Exception {
		int sid = 5;
		boolean enabled = true;
		
		when( wbSiteDAOMock.isSiteEnabled(sid) ).thenReturn(enabled);
		
		mockMvc.perform(post("/admin/manageparsers/enable.xhtml").param("sid", String.valueOf(sid)))
				.andExpect(status().isNoContent());				
		
		verify( wbSiteDAOMock, times(1) ).isSiteEnabled(sid);
		verify( wbSiteDAOMock, times(1) ).setSiteEnabled(sid, enabled ^ true);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	
	public void parseParsers() throws Exception {
		
	}
	
	@Test
	public void listSites() throws Exception {
		mockMvc.perform(get("/admin/wboard/site/list.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbsite/list"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void tablesListSites() throws Exception {
		IPaginatedList<WbSite> sites = new PaginatedListImpl<WbSite>();
		sites.setList(Arrays.asList(site1, site2));
		
		when( wbSiteDAOMock.getPaginatedSiteList( Matchers.any(PagingOptions.class) ) ).thenReturn(sites);		
		
		mockMvc.perform(get("/admin/tables/wbsites.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/tables/wbsites"))
			.andExpect(model().attribute("sites", hasProperty("list", hasSize(2))))
			.andExpect(model().attribute("sites", hasProperty("list", hasItem(
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
            ))))
			.andExpect(model().attribute("sites", hasProperty("list", hasItem(
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
			))));
		
		verify( wbSiteDAOMock, times(1) ).getPaginatedSiteList( Matchers.any(PagingOptions.class));
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void detailsSiteSiteNotFound() throws Exception {
		int sid = 5;
		when( wbSiteDAOMock.getSiteById(sid) ).thenReturn(null);  
		
		mockMvc.perform(get("/admin/wboard/site/details.xhtml?sid=" + String.valueOf(sid)))
			.andExpect(status().isMovedTemporarily())
			.andExpect(view().name("redirect:/error/error.xhtml?error=2"))
			.andExpect(redirectedUrl("/error/error.xhtml?error=2"));
		
		verify( wbSiteDAOMock, times(1) ).getSiteById(sid);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void detailsSiteSite() throws Exception {
		int sid = 5;
		when( wbSiteDAOMock.getSiteById(sid) ).thenReturn(site1);  
		
		mockMvc.perform(get("/admin/wboard/site/details.xhtml?sid=" + String.valueOf(sid)))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbsite/details"))
			.andExpect(model().attribute("site", is(site1)
			));
		
		verify( wbSiteDAOMock, times(1) ).getSiteById(sid);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void createSite() throws Exception {
		mockMvc.perform(get("/admin/wboard/site/create.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbsite/create"))
			.andExpect(model().attribute("wbSite", org.hamcrest.Matchers.any(WbSite.class)));
	}
	
	public void createSitePost() throws Exception {		
		mockMvc.perform(post("/admin/wboard/site/create.xhtml")
				.param("site", site1.getSite())
				.param("url", site1.getUrl())
				.param("code", site1.getCode())
				.param("enabled", String.valueOf(site1.isEnabled()))
				.param("completed", String.valueOf(site1.isCompleted()))
				.param("timeanddateid", String.valueOf(site1.getTimeanddateid()))
				.param("timezone",site1.getTimezone())
				)
				.andExpect(status().isMovedTemporarily())
				.andExpect(view().name("redirect:/admin/wboard/site/list.xhtml"))
				.andExpect(redirectedUrl("/admin/wboard/site/list.xhtml"));
		
		
		verify( wbSiteDAOMock, times(1)).insertSite( Matchers.any(WbSite.class) );
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void editSite() throws Exception {
		int sid = 1;
		when( wbSiteDAOMock.getSiteById(sid) ).thenReturn(site1);
		
		mockMvc.perform(get("/admin/wboard/site/edit.xhtml?sid=" + String.valueOf(sid)))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbsite/edit"))
			.andExpect(model().attribute("wbSite", is(site1)));
		
		verify( wbSiteDAOMock, times(1) ).getSiteById(sid);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void editSiteNotFound() throws Exception {
		int sid = 1;
		when( wbSiteDAOMock.getSiteById(sid) ).thenReturn(null);
		
		mockMvc.perform(get("/admin/wboard/site/edit.xhtml?sid=" + String.valueOf(sid)))
			.andExpect(status().isMovedTemporarily())
			.andExpect(view().name("redirect:/error/error.xhtml?error=2"))
			.andExpect(redirectedUrl("/error/error.xhtml?error=2"));
		
		verify( wbSiteDAOMock, times(1) ).getSiteById(sid);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	public void editSitePost() throws Exception {		
		mockMvc.perform(post("/admin/wboard/site/edit.xhtml")
				.param("site", site1.getSite())
				.param("url", site1.getUrl())
				.param("code", site1.getCode())
				.param("enabled", String.valueOf(site1.isEnabled()))
				.param("completed", String.valueOf(site1.isCompleted()))
				.param("timeanddateid", String.valueOf(site1.getTimeanddateid()))
				.param("timezone",site1.getTimezone())
				)
				.andExpect(status().isTemporaryRedirect())
				.andExpect(view().name("redirect:/admin/wboard/site/list.xhtml"))
				.andExpect(redirectedUrl("/admin/wboard/site/list.xhtml"));
		
		
		verify( wbSiteDAOMock, times(1)).updateSite( Matchers.any(WbSite.class) );
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void deleteSite() throws Exception {
		int sid = 1;
		
		mockMvc.perform(get("/admin/wboard/site/delete.xhtml?sid=" + String.valueOf(sid)))
			.andExpect(status().isMovedTemporarily())
			.andExpect(view().name("redirect:/admin/wboard/site/list.xhtml"))
			.andExpect(redirectedUrl("/admin/wboard/site/list.xhtml"));
		
		verify( wbSiteDAOMock, times(1)).deleteSite(sid);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	
	
	
	
	@Test
	public void listContests() throws Exception {
		mockMvc.perform(get("/admin/wboard/contest/list.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbcontest/list"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void tablesListContests() throws Exception {
		IPaginatedList<WbContest> contests = new PaginatedListImpl<WbContest>();
		contests.setList(Arrays.asList(contest1, contest2));
		
		when( wbContestDAOMock.getPaginatedContestList( Matchers.any(PagingOptions.class) ) ).thenReturn(contests);		
		
		mockMvc.perform(get("/admin/tables/wbcontests.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/tables/wbcontests"))
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
		
		verify( wbContestDAOMock, times(1) ).getPaginatedContestList( Matchers.any(PagingOptions.class));
		verifyNoMoreInteractions(wbContestDAOMock);
	}
	
	@Test
	public void detailsContestContestNotFound() throws Exception {
		int id = 5;
		when( wbContestDAOMock.getContestById(id) ).thenReturn(null);  
		
		mockMvc.perform(get("/admin/wboard/contest/details.xhtml?id=" + String.valueOf(id)))
			.andExpect(status().isMovedTemporarily())
			.andExpect(view().name("redirect:/error/error.xhtml?error=2"))
			.andExpect(redirectedUrl("/error/error.xhtml?error=2"));
		
		verify( wbContestDAOMock, times(1) ).getContestById(id);
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void detailsContest() throws Exception {
		int id = 5;
		when( wbContestDAOMock.getContestById(id) ).thenReturn(contest1);
		when( wbSiteDAOMock.getSiteList() ).thenReturn(Arrays.asList(site1, site2));
		
		mockMvc.perform(get("/admin/wboard/contest/details.xhtml?id=" + String.valueOf(id)))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbcontest/details"))
			.andExpect(model().attribute("wbcontest", is(contest1)))
			.andExpect(model().attribute("mapsites", hasKey(1)))
			.andExpect(model().attribute("mapsites", hasValue(site1)))
			.andExpect(model().attribute("mapsites", hasKey(2)))
			.andExpect(model().attribute("mapsites", hasValue(site2))
			);
		
		verify( wbSiteDAOMock, times(1) ).getSiteList();
		
		verify( wbContestDAOMock, times(1) ).getContestById(id);
		verifyNoMoreInteractions(wbContestDAOMock);
	}
	
	@Test
	public void creatContest() throws Exception {
		mockMvc.perform(get("/admin/wboard/contest/create.xhtml"))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbcontest/create"))
			.andExpect(model().attribute("wbContest", org.hamcrest.Matchers.any(WbContest.class)));
	}
	
	public void createContestPost() throws Exception {		
		mockMvc.perform(post("/admin/wboard/contest/create.xhtml")
				.param("name", contest1.getName())
				.param("url", contest1.getUrl())
				.param("sid", String.valueOf(contest1.getSid()))
				//.param("startDate", "10/10/2010 12:10:00" ))
				//.param("endDate", "10/10/2010 12:10:00" ))
				)
				.andExpect(status().isMovedTemporarily())
				.andExpect(view().name("redirect:/admin/wboard/contest/list.xhtml"))
				.andExpect(redirectedUrl("/admin/wboard/contest/list.xhtml"));
		
		
		verify( wbSiteDAOMock, times(1)).insertSite( Matchers.any(WbSite.class) );
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void editContest() throws Exception {
		int id = 1;
		when( wbContestDAOMock.getContestById(id) ).thenReturn(contest1);
		
		mockMvc.perform(get("/admin/wboard/contest/edit.xhtml?id=" + String.valueOf(id)))
			.andExpect(status().isOk())
			.andExpect(view().name("/admin/wbcontest/edit"))
			.andExpect(model().attribute("wbContest", is(contest1)));
		
		verify( wbContestDAOMock, times(1) ).getContestById(id);
		verifyNoMoreInteractions(wbContestDAOMock);
	}
	
	@Test
	public void editContestNotFound() throws Exception {
		int id = 1;
		when( wbContestDAOMock.getContestById(id) ).thenReturn(null);
		
		mockMvc.perform(get("/admin/wboard/contest/edit.xhtml?id=" + String.valueOf(id)))
			.andExpect(status().isMovedTemporarily())
			.andExpect(view().name("redirect:/error/error.xhtml?error=2"))
			.andExpect(redirectedUrl("/error/error.xhtml?error=2"));
		
		verify( wbContestDAOMock, times(1) ).getContestById(id);
		verifyNoMoreInteractions(wbContestDAOMock);
	}
	
	public void editContestPost() throws Exception {		
		mockMvc.perform(post("/admin/wboard/contest/create.xhtml")
				.param("name", contest1.getName())
				.param("url", contest1.getUrl())
				.param("sid", String.valueOf(contest1.getSid()))
				//.param("startDate", "10/10/2010 12:10:00" ))
				//.param("endDate", "10/10/2010 12:10:00" ))
				)
				.andExpect(status().isMovedTemporarily())
				.andExpect(view().name("redirect:/admin/wboard/contest/list.xhtml"))
				.andExpect(redirectedUrl("/admin/wboard/contest/list.xhtml"));
		
		
		verify( wbSiteDAOMock, times(1)).updateSite( Matchers.any(WbSite.class) );
		verifyNoMoreInteractions(wbSiteDAOMock);
	}
	
	@Test
	public void deleteContest() throws Exception {
		int id = 1;
		
		mockMvc.perform(get("/admin/wboard/contest/delete.xhtml?id=" + String.valueOf(id)))
			.andExpect(status().isMovedTemporarily())
			.andExpect(view().name("redirect:/admin/wboard/contest/list.xhtml"))
			.andExpect(redirectedUrl("/admin/wboard/contest/list.xhtml"));
		
		verify( wbContestDAOMock, times(1)).deleteContest(id);
		verifyNoMoreInteractions(wbContestDAOMock);
	}
}
