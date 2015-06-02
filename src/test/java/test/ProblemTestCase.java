package test;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import cu.uci.coj.controller.ProblemController;
import cu.uci.coj.model.Problem;
 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations = {"classpath:/WEB-INF/dispatcher-servlet.xml","classpath:applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback=true)
public class ProblemTestCase {

	private MockMvc mockMvc;
	
	@Autowired
	private ProblemController problemController;
	@Autowired
	private cu.uci.coj.controller.admin.ProblemController problemAdminController;
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
	public void setUp() throws Exception {
		 
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@After
	public void tearDown() throws Exception {
	}
	

	@Test
	@Transactional
	public final void testProblem() throws Exception{
		
		Principal mockPrincipal = mock(Principal.class);
	    when(mockPrincipal.getName()).thenReturn("jasr");
	    Locale mockLocale = new Locale("en");
	    Problem problem = new Problem();
	    problem.setPid(1000);
	    
		mockMvc.perform(get("/24h/problem.xhtml").param("pid", "1000").locale(mockLocale).principal(mockPrincipal))
		.andExpect(view().name("/24h/problem")).andExpect(status().isOk())
		.andExpect(model().attribute("problem",hasProperty("pid",is(1000))));
		
	}
	
}
