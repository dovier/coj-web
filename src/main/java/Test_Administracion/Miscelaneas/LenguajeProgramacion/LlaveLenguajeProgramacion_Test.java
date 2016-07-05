package Test_Administracion.Miscelaneas.LenguajeProgramacion;

import cu.uci.coj.controller.admin.LanguageController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.validator.languageValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
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
public class LlaveLenguajeProgramacion_Test {

    private MockMvc mockMvc;


    @Mock
    private BaseDAO baseDAO;
    @Spy
    private languageValidator validator;
    @InjectMocks
    private LanguageController languageController;



    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionLenguajeProgramacion utilesTestAdministracionLenguajeProgramacion;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utilesTestAdministracionLenguajeProgramacion = new Utiles_TestAdministracionLenguajeProgramacion();
        this.mockMvc = MockMvcBuilders.standaloneSetup(languageController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utilesTestAdministracionLenguajeProgramacion.getUsuario()).param("j_password", utilesTestAdministracionLenguajeProgramacion.getContrasenna()))
                .andReturn();

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {
        mockMvc=null;
        result=null;
        System.gc();

    }


    //Validando llave
    @Test
    public void validarLlave() throws Exception {

        ArrayList<String> lista1 = utilesTestAdministracionLenguajeProgramacion.getLlavesCorrectosLenguajeProgramacion();
        ArrayList<String> lista2 = utilesTestAdministracionLenguajeProgramacion.getLlavesIncorrectosLenguajeProgramacion();


       String direccionAux="/admin/managelanguage.xhtml?lid="+ utilesTestAdministracionLenguajeProgramacion.getIdLenguajeProgramacion();
        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                    .param("language", utilesTestAdministracionLenguajeProgramacion.getLenguajeCorrecto())
                    .param("key", a)
                    .param("descripcion", utilesTestAdministracionLenguajeProgramacion.getDescripcionCorrecta())
                    .param("name_bin", utilesTestAdministracionLenguajeProgramacion.getNombreBinCorrecto())
                    .param("enabled", utilesTestAdministracionLenguajeProgramacion.getActivadoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de llave incorrecta", "/admin/programminglanguages.xhtml",o );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("language",  utilesTestAdministracionLenguajeProgramacion.getLenguajeCorrecto())
                    .param("key",a)
                    .param("descripcion", utilesTestAdministracionLenguajeProgramacion.getDescripcionCorrecta())
                    .param("name_bin", utilesTestAdministracionLenguajeProgramacion.getNombreBinCorrecto())
                    .param("enabled", utilesTestAdministracionLenguajeProgramacion.getActivadoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de llave incorrecta", null, r.getResponse().getRedirectedUrl());

        }


    }





}
