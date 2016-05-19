package Test_Administracion.Competencias.ConcursosReales.AdicionarConcurso;

import Test_Administracion.Competencias.ConcursosReales.ConfiguracionGeneral.Utiles_TestAdministracionConursoR;
import cu.uci.coj.controller.admin.ContestController;
import cu.uci.coj.dao.*;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.service.ContestService;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.validator.contestValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
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

import javax.servlet.ServletContext;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class AdicionarConcurso_Test {

    private MockMvc mockMvc;

    @Mock
    private Utils utils;
    @Mock
    private ContestDAO contestDAO;
    @Mock
    private ContestAwardDAO contestAwardDAO;
    @Mock
    private ProblemDAO problemDAO;
    @Mock
    private UtilDAO utilDAO;
    @Mock
    private UserDAO userDAO;
    @Spy
    private contestValidator validator;
    @Mock
    private MailNotificationService mailNotificationService;
    @Mock
    private ContestService contestService;

    @Mock
    protected BaseDAO baseDAO;
    @Mock
    protected MessageSource messageSource;
    @Mock
    private ServletContext servletContext;


    @InjectMocks
    private ContestController contestController;





    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionConursoR utiles_testAdministracionConursoR;
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionConursoR = new Utiles_TestAdministracionConursoR();
        this.mockMvc = MockMvcBuilders.standaloneSetup(contestController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionConursoR.getUsuario()).param("j_password", utiles_testAdministracionConursoR.getContrasenna()))
                .andReturn();

        validator.setContestDAO(contestDAO);

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {
        mockMvc=null;
        result=null;
        System.gc();

    }


    //Validando ID
    @Test
    public void validarIDTest() throws Exception {

        ArrayList<String> IDCorrectos = new ArrayList<>();
        IDCorrectos.add("12332141");


        ArrayList<String> IDIncorrectos = new ArrayList<>();
        IDIncorrectos.add("");
        IDIncorrectos.add(" ");
        IDIncorrectos.add("as");
        IDIncorrectos.add("12as");
        IDIncorrectos.add("/*-");


        for (String a : IDCorrectos) {
            r = mockMvc.perform(post("/admin/createcontest.xhtml")
                    .param("cid",a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de id incorrecta", "/admin/admincontests.xhtml",o );
        }


        for (String a : IDIncorrectos) {
            r = mockMvc.perform(post("/admin/createcontest.xhtml")
                            .param("cid",a)
                            .session((MockHttpSession) result.getRequest().getSession()))
                            .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de id incorrecta", null,o);

        }


    }





}
