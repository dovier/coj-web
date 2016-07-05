package Test_Administracion.Miscelaneas.Encuesta;

import Test_Administracion.Miscelaneas.Faq.Utiles_TestAdministracionFaq;
import cu.uci.coj.controller.admin.FaqController;
import cu.uci.coj.controller.admin.PollsController;
import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.validator.FaqValidator;
import cu.uci.coj.validator.pollValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class PreguntaEncuesta_Test {

    private MockMvc mockMvc;


    @Mock
    private PollDAO pollDAO;
    @Spy
    private pollValidator pollValidator;
    @InjectMocks
    private PollsController pollsController;



    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionEncuesta utiles_testAdministracionEncuesta;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionEncuesta = new Utiles_TestAdministracionEncuesta();
        this.mockMvc = MockMvcBuilders.standaloneSetup(pollsController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionEncuesta.getUsuario()).param("j_password", utiles_testAdministracionEncuesta.getContrasenna()))
                .andReturn();

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {
        mockMvc=null;
        result=null;
        System.gc();

    }


    //Validando pregunta
    @Test
    public void validarPregunta() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionEncuesta.getPreguntasCorrectosEncuesta();
        ArrayList<String> lista2 = utiles_testAdministracionEncuesta.getPreguntasIncorrectosEncuesta();


       String direccionAux="/admin/poll/manage.xhtml?pid="+ utiles_testAdministracionEncuesta.getIdEncuesta();
        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                    .param("question", a)
                    .param("answer1", utiles_testAdministracionEncuesta.getRespuesta1Correcto())
                    .param("answer2", utiles_testAdministracionEncuesta.getRespuesta2Correcto())
                    .param("answer3", utiles_testAdministracionEncuesta.getRespuesta3Correcto())
                    .param("answer4", utiles_testAdministracionEncuesta.getRespuesta4Correcto())
                    .param("answer5", utiles_testAdministracionEncuesta.getRespuesta5Correcto())
                    .param("enabled", utiles_testAdministracionEncuesta.getHabilitadoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de pregunta incorrecta", "/admin/poll/list.xhtml",o );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("question", a)
                    .param("answer1", utiles_testAdministracionEncuesta.getRespuesta1Correcto())
                    .param("answer2", utiles_testAdministracionEncuesta.getRespuesta2Correcto())
                    .param("answer3", utiles_testAdministracionEncuesta.getRespuesta3Correcto())
                    .param("answer4", utiles_testAdministracionEncuesta.getRespuesta4Correcto())
                    .param("answer5", utiles_testAdministracionEncuesta.getRespuesta5Correcto())
                    .param("enabled", utiles_testAdministracionEncuesta.getHabilitadoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de pregunta incorrecta", null, r.getResponse().getRedirectedUrl());

        }


    }





}
