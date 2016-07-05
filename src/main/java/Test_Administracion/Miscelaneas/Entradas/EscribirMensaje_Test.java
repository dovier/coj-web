package Test_Administracion.Miscelaneas.Entradas;

import Test_Administracion.Miscelaneas.Anuncio.Utiles_TestAdministracionAnuncio;
import cu.uci.coj.controller.admin.AnnouncementController;
import cu.uci.coj.controller.mail.MailController;
import cu.uci.coj.dao.AnnouncementDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.MailDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.validator.annValidator;
import cu.uci.coj.validator.sendMailValidator;
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
public class EscribirMensaje_Test {

    private MockMvc mockMvc;


    @Mock
    private MailDAO mailDAO;
    @Mock
    MailNotificationService MailNotificationService;
    @Mock
    private UserDAO userDAO;
    @Spy
    private sendMailValidator sendmailValidator;
    @InjectMocks
    private MailController mailController;
    MvcResult result;

    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionAnuncio utiles_testAdministracionAnuncio;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionAnuncio = new Utiles_TestAdministracionAnuncio();
        mockMvc = MockMvcBuilders.standaloneSetup(mailController).addFilter(springSecurityFilterChain).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionAnuncio.getUsuario()).param("j_password", utiles_testAdministracionAnuncio.getContrasenna()))
                .andReturn();



        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {

        mockMvc=null;
        result=null;
        System.gc();
    }


    //Validando -para- Mensaje
    @Test
    public void validarPara() throws Exception {

        ArrayList<String> paraCorrectos = new ArrayList<>();
        paraCorrectos.add("luismo");

        ArrayList<String> paraIncorrectos = new ArrayList<>();
        paraIncorrectos.add("");
        paraIncorrectos.add(" ");
        paraIncorrectos.add("8794");
        paraIncorrectos.add("-*-*");


       String direccionAux="/mail/composemail.xhtml";
        for (String a : paraCorrectos) {
            r = mockMvc.perform(post(direccionAux)
                    .param("usernameTo",a)
                    .param("title", "Titulo")
                    .param("content", "Contenido")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de -para- incorrecta o parametros de red mal configurados", "/mail/outbox.xhtml",o );
        }


        for (String a : paraIncorrectos) {
            r = mockMvc.perform(post(direccionAux)
                    .param("usernameTo",a)
                    .param("title", "Titulo")
                    .param("content", "Contenido")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();
            ;

            Assert.assertEquals("Validacion de -para- incorrecta o parametros de red mal configurados", null, r.getResponse().getRedirectedUrl());

        }


    }

    //Validando -asunto- Mensaje
    @Test
    public void validarAsunto() throws Exception {

        ArrayList<String> asuntoCorrectos = new ArrayList<>();
        asuntoCorrectos.add("asunto");

        ArrayList<String> asuntoIncorrectos = new ArrayList<>();
        asuntoIncorrectos.add("");
        asuntoIncorrectos.add(" ");


        String direccionAux="/mail/composemail.xhtml";
        for (String a : asuntoIncorrectos) {
            r = mockMvc.perform(post(direccionAux)
                    .param("usernameTo","luismo")
                    .param("title", a)
                    .param("content", "Contenido")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de -asunto- incorrecta o parametros de red mal configurados", "/mail/outbox.xhtml",o );
        }


        for (String a : asuntoIncorrectos) {
            r = mockMvc.perform(post(direccionAux)
                    .param("usernameTo","luismo")
                    .param("title", a)
                    .param("content", "Contenido")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();
            ;

            Assert.assertEquals("Validacion de -asunto- incorrecta o parametros de red mal configurados", null, r.getResponse().getRedirectedUrl());

        }


    }





}
