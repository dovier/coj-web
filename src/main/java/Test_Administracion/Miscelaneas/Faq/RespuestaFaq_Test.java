package Test_Administracion.Miscelaneas.Faq;

import cu.uci.coj.controller.admin.FaqController;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.validator.FaqValidator;
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

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class RespuestaFaq_Test {

    private MockMvc mockMvc;


    @Mock
    private UtilDAO utilDao;
    @Spy
    private FaqValidator validator;
    @InjectMocks
    private FaqController faqController;


    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionFaq utiles_testAdministracionFaq;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionFaq = new Utiles_TestAdministracionFaq();
        this.mockMvc = MockMvcBuilders.standaloneSetup(faqController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionFaq.getUsuario()).param("j_password", utiles_testAdministracionFaq.getContrasenna()))
                .andReturn();

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {
        mockMvc=null;
        result=null;
        System.gc();

    }


    //Validando respuesta
    @Test
    public void validarRespuesta() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionFaq.getRespuestasCorrectosFaq();
        ArrayList<String> lista2 = utiles_testAdministracionFaq.getRespuestasIncorrectosFaq();


       String direccionAux="/admin/editfaq.xhtml?id="+ utiles_testAdministracionFaq.getIdFaq();
        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                    .param("question", utiles_testAdministracionFaq.getPeguntaCorrecto())
                    .param("answer", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de respuesta incorrecta", "/admin/faqs.xhtml",o );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("question", utiles_testAdministracionFaq.getPeguntaCorrecto())
                    .param("answer", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de respuesta incorrecta", null, r.getResponse().getRedirectedUrl());

        }


    }





}
