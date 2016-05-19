package Test_Administracion.Miscelaneas.Anuncio;

import Test_Administracion.COJBoard.Competencia.Utiles_TestAdministracionCompetencia;
import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.controller.admin.AnnouncementController;
import cu.uci.coj.controller.admin.WbBoardAdminController;
import cu.uci.coj.dao.AnnouncementDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.validator.WbContestValidator;
import cu.uci.coj.validator.WbSiteValidator;
import cu.uci.coj.validator.annValidator;
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
public class ContenidoAnuncio_Test {

    private MockMvc mockMvc;


    @Mock
    private AnnouncementDAO announcementDAO;
    @Mock
    private ContestDAO contestDAO;
    @Spy
    private annValidator validator;
    @InjectMocks
    private AnnouncementController announcementController;
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
        mockMvc = MockMvcBuilders.standaloneSetup(announcementController).addFilter(springSecurityFilterChain).build();

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


    //Validando contenido
    @Test
    public void validarContenidoAnuncio() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionAnuncio.getContenidosCorrectosSitio();
        ArrayList<String> lista2 = utiles_testAdministracionAnuncio.getContenidosIncorrectosSitio();


       String direccionAux="/admin/manageann.xhtml?aid="+ utiles_testAdministracionAnuncio.getIdAnuncio();
        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                   .param("aid",utiles_testAdministracionAnuncio.getIdAnuncio())
                    .param("content", a)
                    .param("enabled", utiles_testAdministracionAnuncio.getHabilitadoCorrecto())
                    .param("contest", utiles_testAdministracionAnuncio.getSeleccionarConcursoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de contenido incorrecta", "/admin/listann.xhtml",o );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("aid",utiles_testAdministracionAnuncio.getIdAnuncio())
                    .param("content", a)
                    .param("enabled", utiles_testAdministracionAnuncio.getHabilitadoCorrecto())
                    .param("contest", utiles_testAdministracionAnuncio.getSeleccionarConcursoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de contenido incorrecta", null, r.getResponse().getRedirectedUrl());

        }


    }





}
