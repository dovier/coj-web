package test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
	    DependencyInjectionTestExecutionListener.class
	   })
public class AuthenticatedTestCase {

	private static String SEC_CONTEXT_ATTR = HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

	private MockMvc mockMvc;
	
	@Mock
	private ContestDAO contestDAOMock;
	@Mock
	private cu.uci.coj.validator.problemValidator problemValidator;
	@Mock
	private cu.uci.coj.validator.contestValidator contestValidator;
	@Mock
	private ProblemDAO problemDAOMock;
	
	@InjectMocks
	private cu.uci.coj.controller.admin.ProblemController problemAdminController;
	
	@InjectMocks
	private cu.uci.coj.controller.admin.ContestController contestAdminController;
	
	
	@Autowired
	private FilterChainProxy springSecurityFilterChain;
	
	private ArgumentCaptor<Problem> problemToManage;
	private ArgumentCaptor<Contest> contestToManage;
	MvcResult result;
	
	String[] users = new String[] {"1000","1001","1002"};
	String[] judges = new String[] {"2000","2001","2002"};
	int[] intJudges = new int[] {2000,2001,2002};
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contestAdminController,problemAdminController).addFilter(springSecurityFilterChain).build();
        
        doNothing().when(spy(problemAdminController)).handleFiles(Matchers.any(Problem.class), Matchers.any(MultipartHttpServletRequest.class));
        problemToManage  = ArgumentCaptor.forClass(Problem.class);
        contestToManage  = ArgumentCaptor.forClass(Contest.class);
        
        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
        		.andReturn();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void userAuthenticates() throws Exception {
		mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
			.andExpect(new ResultMatcher() {
				public void match(MvcResult mvcResult) throws Exception {
					HttpSession session = mvcResult.getRequest().getSession();
					SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
					Assert.assertEquals(securityContext.getAuthentication().getName(), "ybroche");
				}
			});
	}

	@Test
	public void userAuthenticateFails() throws Exception {
		MvcResult res = mockMvc.perform(post("/j_spring_security_check").param("j_username", "user").param("j_password", ""))
			.andExpect(redirectedUrl("/index.xhtml?login_error=1"))
			.andExpect(new ResultMatcher() {
				public void match(MvcResult mvcResult) throws Exception {
					HttpSession session = mvcResult.getRequest().getSession();
					SecurityContext securityContext = (SecurityContext) session.getAttribute(SEC_CONTEXT_ATTR);
					Assert.assertNull(securityContext);
				}
			}).andReturn();
		System.out.println(res.getResponse().getRedirectedUrl());
	}
	/**@Test
	public void validator_source() throws Exception {
		MvcResult res = mockMvc.perform(post("/admin/updatesource.xhtml")
				.param("idSource", "14")
				.param("name", "2008/2009 ACM-ICPC Mexico and Central Americaa")
				.param("author", "")
		).andReturn();

		System.out.println("ertert "+res.getResponse().getRedirectedUrl());
	}*/

	@Test
	public void addProblem() throws Exception {

		MvcResult result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
		.andReturn();
		
		when( problemDAOMock.getPidByTitle("Test Title")).thenReturn( 10000 );

		mockMvc.perform(post("/admin/manageproblem.xhtml")
				.param("title", "Test Title")
				.session((MockHttpSession) result.getRequest().getSession()))
		
		.andExpect(status().isFound());
		verify( problemDAOMock,times(1)).getPidByTitle("Test Title");
		verify( problemDAOMock,times(1)).addProblem(problemToManage.capture());
		Assert.assertEquals("Test Title", problemToManage.getValue().getTitle());
	}
	
	@Test
	public void editProblem() throws Exception {

		MvcResult result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
				.andReturn();


		mockMvc.perform(post("/admin/manageproblem.xhtml").param("pid","1000").param("disable_24h","false").param("title", "A+B modified").session((MockHttpSession) result.getRequest().getSession()))
		.andExpect(status().isFound());
		verify( problemDAOMock,times(1)).updateProblem(problemToManage.capture());
		Assert.assertEquals("A+B modified", problemToManage.getValue().getTitle());
		Assert.assertEquals(false, problemToManage.getValue().isDisable_24h());
	}
	
	@Test
	public void translateProblem() throws Exception {
		
		mockMvc.perform(post("/admin/manageproblemI18N.xhtml").param("pid","1000").param("titleEs", "A+B modificado").session((MockHttpSession) result.getRequest().getSession()))
		.andExpect(status().isFound());
		verify( problemDAOMock,times(1)).updateProblemI18N(problemToManage.capture());
		Assert.assertEquals("A+B modificado", problemToManage.getValue().getTitleEs());
	}
	
	@Test
	public void addContest() throws Exception {
		
		mockMvc.perform(post("/admin/createcontest.xhtml").param("name", "Test Contest").session((MockHttpSession) result.getRequest().getSession()))
		.andExpect(status().isFound());
		verify( contestDAOMock,times(1)).InsertContest(contestToManage.capture());
		Assert.assertEquals("Test Contest", contestToManage.getValue().getName());
	}
	
	@Test
	public void addUserContest() throws Exception {
		MvcResult result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "alison").param("j_password", "adminpass123"))
				.andReturn();
		
		mockMvc.perform(post("/admin/contestusers.xhtml").param("usersids", users).param("judgesids",judges).session((MockHttpSession) result.getRequest().getSession()))
		.andExpect(status().isFound());
		verify( contestDAOMock,times(1)).insertUsersContest(contestToManage.capture());
		Assert.assertArrayEquals(users, contestToManage.getValue().getUsersids());
		verify( contestDAOMock,times(1)).insertJudgesContest(contestToManage.capture());
		Assert.assertArrayEquals(intJudges, contestToManage.getValue().getJudgesids());
	}
	
	
}
