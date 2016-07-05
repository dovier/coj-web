package Test_Administracion.Problemas.Fuentes;

import cu.uci.coj.controller.admin.SourceController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.validator.ProblemSourceValidator;
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
public class AutorFuente_Test {

    private MockMvc mockMvc;


    @Mock
    private ContestDAO contestDAOMock;
    @Spy
    private ProblemSourceValidator validatorMock;

    @Mock
    private ProblemDAO problemDAOMock;

    @InjectMocks
    private SourceController sourceAdminController;
    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionFuentes utiles_testAdministracionFuentes;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionFuentes = new Utiles_TestAdministracionFuentes();
        this.mockMvc = MockMvcBuilders.standaloneSetup(sourceAdminController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionFuentes.getUsuario()).param("j_password", utiles_testAdministracionFuentes.getContrasenna()))
                .andReturn();

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {

    }


    //Validando autor
    @Test
    public void validarAutorFuente() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionFuentes.getAutorCorrectosFuente();
        ArrayList<String> lista2 = utiles_testAdministracionFuentes.getAutorIncorrectosFuente();


       String direccionAux="/admin/updatesource.xhtml";
        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                    .param("idSource", utiles_testAdministracionFuentes.getIdSource())
                    .param("name", utiles_testAdministracionFuentes.getFuenteInCorrecto())
                    .param("author", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();


            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de autor incorrecta", "/admin/managesources.xhtml",o );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("idSource", utiles_testAdministracionFuentes.getIdSource())
                    .param("name", utiles_testAdministracionFuentes.getFuenteInCorrecto())
                    .param("author", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de autor incorrecta", null, r.getResponse().getRedirectedUrl());

        }


    }





}
