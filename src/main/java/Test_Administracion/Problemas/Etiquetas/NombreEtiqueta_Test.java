package Test_Administracion.Problemas.Etiquetas;

import Test_Administracion.Problemas.Fuentes.Utiles_TestAdministracionFuentes;
import cu.uci.coj.controller.admin.ClassificationController;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class NombreEtiqueta_Test {

    private MockMvc mockMvc;


    @Mock
    private ProblemDAO problemDAO;

    @InjectMocks
    private ClassificationController classificationController;

    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionEtiquetas utiles_testAdministracionEtiquetas;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionEtiquetas = new Utiles_TestAdministracionEtiquetas();
        this.mockMvc = MockMvcBuilders.standaloneSetup(classificationController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionEtiquetas.getUsuario()).param("j_password", utiles_testAdministracionEtiquetas.getContrasenna()))
                .andReturn();

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {

    }


    //Validando nombre
    @Test
    public void validarNombreEtiqueta() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionEtiquetas.getNombreCorrectosEtiqueta();
        ArrayList<String> lista2 = utiles_testAdministracionEtiquetas.getNombreIncorrectosEtiqueta();


       String direccionAux="/admin/addclassifications.xhtml";
        for (String a : lista1) {

            String newname = a.replace(" ", "");
            if (newname.length() == 0)  Assert.assertEquals("Validacion de nombre incorrecta", "/admin/manageclassifications.xhtml",null );

            r = mockMvc.perform(post(direccionAux)
                    .param("name", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();


            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de autor incorrecta", "/admin/manageclassifications.xhtml",o );
        }


        for (String a : lista2) {
            String newname = a.replace(" ", "");
            if (!(newname.length() == 0))  Assert.assertEquals("Validacion de nombre incorrecta", direccionAux,null );

            r = mockMvc.perform(post(direccionAux)
                    .param("name", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de nombre incorrecta", "/admin/manageclassifications.xhtml", r.getResponse().getRedirectedUrl());

        }


    }





}
