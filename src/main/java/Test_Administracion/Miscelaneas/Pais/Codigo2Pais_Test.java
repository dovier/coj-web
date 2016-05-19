package Test_Administracion.Miscelaneas.Pais;

import cu.uci.coj.controller.admin.CountryController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.validator.countryValidator;
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

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class Codigo2Pais_Test {

    private MockMvc mockMvc;

    @Mock
    protected BaseDAO baseDAO;
    @Mock
    protected MessageSource messageSource;
    @Mock
    private CountryDAO countryDAO;
    @Spy
    private countryValidator countryValidator;
    @InjectMocks
    private CountryController countryController;




    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionPais utiles_testAdministracionPais;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionPais = new Utiles_TestAdministracionPais();

        mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionPais.getUsuario()).param("j_password", utiles_testAdministracionPais.getContrasenna()))
                .andReturn();
        countryValidator.setCountryDAO(countryDAO);

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {
        mockMvc=null;
        result=null;
        System.gc();


    }


    //Validando codigo 2
    @Test
    public void validarCodigo2() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionPais.getCodigo2CorrectosPais();
        ArrayList<String> lista2 = utiles_testAdministracionPais.getCodigo2IncorrectosPais();

           String direccionAux="/admin/addcountry.xhtml";

     for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                    .param("name",utiles_testAdministracionPais.getNombreCorrecto() )
                    .param("zip", utiles_testAdministracionPais.getCodigo3Correcta())
                    .param("zip_two", a)
                    .param("website", utiles_testAdministracionPais.getSitioWebCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de codigo 2 incorrecta", "/admin/managecountries.xhtml",o );
        }

        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("name",utiles_testAdministracionPais.getNombreCorrecto() )
                    .param("zip", utiles_testAdministracionPais.getCodigo3Correcta())
                    .param("zip_two", a)
                    .param("website", utiles_testAdministracionPais.getSitioWebCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de codigo 2 incorrecta", null, o);

        }


    }



    }
