package Test_Administracion.Competencias.Equipo;

import cu.uci.coj.controller.admin.UserController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.validator.userValidator;
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
public class IdiomaCompetencia_Test {

    private MockMvc mockMvc;


    @Mock
    private UserDAO userDAO;
    @Mock
    private UtilDAO utilDAO;
    @Mock
    private ContestDAO contestDAO;
    @Mock
    private InstitutionDAO institutionDAO;
    @Spy
    private userValidator validator;
    @InjectMocks
    private UserController userController;

    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionEquipo utiles_testAdministracionEquipo;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionEquipo = new Utiles_TestAdministracionEquipo();
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionEquipo.getUsuario()).param("j_password", utiles_testAdministracionEquipo.getContrasenna()))
                .andReturn();

        validator.setUserDAO(userDAO);

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {

    }


    //Validando idioma
    @Test
    public void validarIdioma() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionEquipo.getIdiomaCorrectosEquipo();
        ArrayList<String> lista2 = utiles_testAdministracionEquipo.getIdiomaIncorrectosEquipo();


       String direccionAux="/admin/createteams.xhtml";
        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                      .param("username",utiles_testAdministracionEquipo.getUserNameEquipo())
                      .param("nick",utiles_testAdministracionEquipo.getApodoCorrecto())
                      .param("update_nick", "false")
                      .param("password", "12345678")
                      .param("confirmPassword", "12345678")
                      .param("total",utiles_testAdministracionEquipo.getTotalCorrecto())
                      .param("country", utiles_testAdministracionEquipo.getPaisCorrecta())
                      .param("institution",utiles_testAdministracionEquipo.getInstitucionCorrecta() )
                      .param("locale", a)
                      .param("contest", utiles_testAdministracionEquipo.getConcursoCorrecto())
                      .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de idioma incorrecta", "/admin/manageteams.xhtml",o );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("username",utiles_testAdministracionEquipo.getUserNameEquipo())
                    .param("nick",utiles_testAdministracionEquipo.getApodoCorrecto())
                    .param("update_nick", "false")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("total",utiles_testAdministracionEquipo.getTotalCorrecto())
                    .param("country", utiles_testAdministracionEquipo.getPaisCorrecta())
                    .param("institution",utiles_testAdministracionEquipo.getInstitucionCorrecta() )
                    .param("locale", a)
                    .param("contest", utiles_testAdministracionEquipo.getConcursoCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();


            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de idioma incorrecta", null, o);

        }


    }





}
