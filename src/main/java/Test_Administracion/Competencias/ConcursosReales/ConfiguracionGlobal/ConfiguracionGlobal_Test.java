package Test_Administracion.Competencias.ConcursosReales.ConfiguracionGlobal;

import cu.uci.coj.controller.admin.ContestController;
import cu.uci.coj.dao.*;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;
import cu.uci.coj.service.ContestService;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.validator.contestValidator;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class ConfiguracionGlobal_Test {

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

    @InjectMocks
    private ContestController contestController;


    MvcResult r;
    MvcResult result;


    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(contestController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
                .andReturn();
        //validator.setUserDAO(userDAO);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Validando si el campo "Puntos por problema" de la clase Configuracion global en Competencias se completa correctamente
     */
    @Test
    public void validar_puntosPorProblema_Test() throws Exception {

        ArrayList<String> valores_puntosPorProblemasCorrectos= new ArrayList<>();
        ArrayList<String> valores_puntosPorProblemasIncorrectos= new ArrayList<>();


        valores_puntosPorProblemasCorrectos.add("1");
        valores_puntosPorProblemasCorrectos.add("12313");
        valores_puntosPorProblemasCorrectos.add("123456789");
        valores_puntosPorProblemasCorrectos.add(" 12313 ");

        valores_puntosPorProblemasIncorrectos.add("12345678910");
        valores_puntosPorProblemasIncorrectos.add("asdasd");
        valores_puntosPorProblemasIncorrectos.add("#");
        valores_puntosPorProblemasIncorrectos.add("");
        valores_puntosPorProblemasIncorrectos.add(" ");

        for (String a : valores_puntosPorProblemasCorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", a)//no mayor que 9 y solo acepta numeros
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", "1")
                        .param("silver", "1")
                        .param("bronze", "1")
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de puntos por problema incorrecta", "/admin/globalsettings.xhtml?cid=1474", r.getResponse().getRedirectedUrl());
                         }
            catch (Exception e){
                Assert.assertEquals("Validacion de puntos por problema incorrecta",true,false);

            }
        }

        for (String a : valores_puntosPorProblemasIncorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", a)//no mayor que 9 y solo acepta numeros
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", "1")
                        .param("silver", "1")
                        .param("bronze", "1")
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de puntos por problema incorrecta",false,true);
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de puntos por problema incorrecta",true,true);

            }
        }
    }

    /**
     * Validando si el campo "Oro" de la clase Configuracion global en Competencias se completa correctamente
     */
    @Test
    public void validar_gold_Test() throws Exception {

        ArrayList<String> valores_puntosOroCorrectos= new ArrayList<>();
        ArrayList<String> valores_puntosOroIncorrectos= new ArrayList<>();


        valores_puntosOroCorrectos.add("1");
        valores_puntosOroCorrectos.add("12313");
        valores_puntosOroCorrectos.add("123456789");
        valores_puntosOroCorrectos.add(" 12313 ");

        valores_puntosOroIncorrectos.add("12345678910");
        valores_puntosOroIncorrectos.add("asdasd");
        valores_puntosOroIncorrectos.add("#");
        valores_puntosOroIncorrectos.add("");
        valores_puntosOroIncorrectos.add(" ");

        for (String a : valores_puntosOroCorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", "10")
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", a)
                        .param("silver", "1")
                        .param("bronze", "1")
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de oro incorrecta", "/admin/globalsettings.xhtml?cid=1474", r.getResponse().getRedirectedUrl());
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de oro incorrecta",true,false);

            }
        }

        for (String a : valores_puntosOroIncorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", "10")
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", a)
                        .param("silver", "1")
                        .param("bronze", "1")
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de oro incorrecta",false,true);
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de oro incorrecta",true,true);

            }
        }
    }

    /**
     * Validando si el campo "Plata" de la clase Configuracion global en Competencias se completa correctamente
     */
    @Test
    public void validar_silver_Test() throws Exception {
        ArrayList<String> valores_puntosPlataCorrectos= new ArrayList<>();
        ArrayList<String> valores_puntosPlataIncorrectos= new ArrayList<>();


        valores_puntosPlataCorrectos.add("1");
        valores_puntosPlataCorrectos.add("12313");
        valores_puntosPlataCorrectos.add("123456789");
        valores_puntosPlataCorrectos.add(" 12313 ");

        valores_puntosPlataIncorrectos.add("12345678910");
        valores_puntosPlataIncorrectos.add("asdasd");
        valores_puntosPlataIncorrectos.add("#");
        valores_puntosPlataIncorrectos.add("");
        valores_puntosPlataIncorrectos.add(" ");

        for (String a : valores_puntosPlataCorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", "10")
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", "10")
                        .param("silver", a)
                        .param("bronze", "1")
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de plata incorrecta", "/admin/globalsettings.xhtml?cid=1474", r.getResponse().getRedirectedUrl());
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de plata incorrecta",true,false);

            }
        }

        for (String a : valores_puntosPlataIncorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", "10")
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", "10")
                        .param("silver", a)
                        .param("bronze", "1")
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de plata incorrecta",false,true);
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de plata incorrecta",true,true);

            }
        }
    }
    /**
     * Validando si el campo "Bronce" de la clase Configuracion global en Competencias se completa correctamente
     */
    @Test
    public void validar_bronce_Test() throws Exception {

        ArrayList<String> valores_puntosBronceCorrectos= new ArrayList<>();
        ArrayList<String> valores_puntosBronceIncorrectos= new ArrayList<>();


        valores_puntosBronceCorrectos.add("1");
        valores_puntosBronceCorrectos.add("12313");
        valores_puntosBronceCorrectos.add("123456789");
        valores_puntosBronceCorrectos.add(" 12313 ");

        valores_puntosBronceIncorrectos.add("12345678910");
        valores_puntosBronceIncorrectos.add("asdasd");
        valores_puntosBronceIncorrectos.add("#");
        valores_puntosBronceIncorrectos.add("");
        valores_puntosBronceIncorrectos.add(" ");

        for (String a : valores_puntosBronceCorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", "10")
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", "10")
                        .param("silver", "10")
                        .param("bronze", a)
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de bronce incorrecta", "/admin/globalsettings.xhtml?cid=1474", r.getResponse().getRedirectedUrl());
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de bronce incorrecta",true,false);

            }
        }

        for (String a : valores_puntosBronceIncorrectos) {
            try {
                r = mockMvc.perform(post("/admin/globalsettings.xhtml?cid=1474")
                        .param("ppoints", "10")
                        .param("balloon", "true")
                        .param("gallery", "true")
                        .param("saris", "true")
                        .param("show_stats", "true")
                        .param("show_stats_out", "true")
                        .param("show_status", "true")
                        .param("show_status_out", "true")
                        .param("show_scoreboard", "true")
                        .param("show_scoreboard_out", "true")
                        .param("allow_registration", "true")
                        .param("unfreeze_auto", "true")
                        .param("show_problem_out", "true")
                        .param("show_ontest", "true")
                        .param("gold", "10")
                        .param("silver", "10")
                        .param("bronze", a)
                        .session((MockHttpSession) result.getRequest().getSession()))
                        .andReturn();

                Assert.assertEquals("Validacion de bronce incorrecta",false,true);
            }
            catch (Exception e){
                Assert.assertEquals("Validacion de bronce incorrecta",true,true);

            }
        }
    }


}
