package test;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import cu.uci.coj.controller.ProblemController;
import cu.uci.coj.model.Problem;
 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration 
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
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
	public final void showProblem() throws Exception{
		
		Principal mockPrincipal = mock(Principal.class);
	    when(mockPrincipal.getName()).thenReturn("jasr");
	    Locale mockLocale = new Locale("en");
	    Problem problem = new Problem();
	    problem.setPid(1000);

		MvcResult res=mockMvc.perform(get("/manageuser.xhtml")
				.param("nick","loka")
				.param("update_nick","true")
				.param("country_id","13")
				.param("institution_id","129")
				.param("locale","1")
				.param("lid","43")
				.param("password","1234")
				.param("confirmPassword","1234")
				.param("access_rule","*")
				.param("enabled","true")
				.param("banReason","")
				.param("name","Franly")
				.param("lastname","eso")
				.param("gender","1")
				.param("year","1930")
				.param("month","1")
				.param("day","1")
				.param("showdob","true")
				.param("email","asd @asd.cu")
				.param("contestNotifications","true")
				.param("problemNotifications","true")
				.param("submissionNotifications","true")
				.param("authorities","ROLE_ADMIN")
				.param("mail_quote","400000")).andReturn();

		System.out.println(res.getModelAndView().getViewName());

		/*MvcResult re= mockMvc.perform(get("/24h/problem.xhtml").param("pid", "1000").locale(mockLocale).principal(mockPrincipal)).andReturn();

		System.out.println(re.getModelAndView().getViewName());*/
		/*.andExpect(view().name("/24h/problem")).andExpect(status().isOk())
		.andExpect(model().attribute("problem",hasProperty("pid",is(1000))));*/
	}
	
}
