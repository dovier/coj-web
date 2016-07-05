package Test_Administracion.Problemas.Problemas;


import cu.uci.coj.controller.admin.ClassificationController;
import cu.uci.coj.controller.admin.UserController;
import cu.uci.coj.dao.*;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;
import cu.uci.coj.validator.userValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.spy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class Problemas_Test {

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


    MvcResult r;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private ArgumentCaptor<Problem> problemToManage;
    private ArgumentCaptor<Contest> contestToManage;
    MvcResult result;

    String[] users = new String[] {"1000","1001","1002"};
    String[] judges = new String[] {"2000","2001","2002"};
    int[] intJudges = new int[] {2000,2001,2002};

//    @Before
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(problemAdminController).build();
//
//        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
//                .andReturn();
//    }
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

    /**
     * Validando si el campo "Nombre" de la clase Etiquetas se actualiza correctamente
     */
    @Test
    public void addProblem() throws Exception {
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

        mockMvc.perform(post("/admin/manageproblem.xhtml")
                .param("pid","1000").param("disable_24h","false")
                .param("title", "A+B modified")
                .session((MockHttpSession) result.getRequest().getSession()))
                .andExpect(status().isFound());
        verify( problemDAOMock,times(1)).updateProblem(problemToManage.capture());
        Assert.assertEquals("A+B modified", problemToManage.getValue().getTitle());
        Assert.assertEquals(false, problemToManage.getValue().isDisable_24h());
    }

}