package Test_Administracion.Miscelaneas.Institucion;

import Test_Administracion.COJBoard.Sitio.Utiles_TestAdministracionSitio;
import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.controller.admin.InstitutionController;
import cu.uci.coj.controller.admin.WbBoardAdminController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.validator.WbContestValidator;
import cu.uci.coj.validator.WbSiteValidator;
import cu.uci.coj.validator.institutionValidator;
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
public class NombreInstitucion_Test {

    private MockMvc mockMvc;


    @Spy
    private institutionValidator validator;
    @Mock
    private InstitutionDAO institutionDAO;

    @InjectMocks
    private InstitutionController institutionController;


    MvcResult result;

    //Variables auxiliares
    MvcResult r;

    private Utiles_TestAdministracionInstitucion utiles_testAdministracionInstitucion;


    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionInstitucion = new Utiles_TestAdministracionInstitucion();
        this.mockMvc = MockMvcBuilders.standaloneSetup(institutionController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionInstitucion.getUsuario()).param("j_password", utiles_testAdministracionInstitucion.getContrasenna()))
                .andReturn();
        validator.setInstitutionDAO(institutionDAO);

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {


    }


    //Validando nombre de institucion
    @Test
    public void validarNombreInstitucion() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionInstitucion.getNombresCorrectosInstitucion();
        ArrayList<String> lista2 = utiles_testAdministracionInstitucion.getNombresIncorrectosInstitucion();


        for (String a : lista1) {
            r = mockMvc.perform(post("/admin/manageinstitution.xhtml?inst_id=7978")
                    .param("id", utiles_testAdministracionInstitucion.getIdSitio())
                    .param("name", a)
                    .param("zip", utiles_testAdministracionInstitucion.getCodigoCorrecta())
                    .param("website",utiles_testAdministracionInstitucion.getSitioWebCorrecto())
                    .param("enabled", utiles_testAdministracionInstitucion.getHabilitadoCorrecto())
                    .param("country_id",utiles_testAdministracionInstitucion.getSeleccionarPaisCorrecta())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();


            String o1;
            o1=r.getResponse().getRedirectedUrl();
            String comparar1="/admin/manageinstitutions.xhtml";
            if(o1!=null)
            {
                if(o1.equals("http://localhost/index.xhtml"))
                {
                    o1=null;
                }
            }
            else
            {
                o1=null;
            }
            Assert.assertEquals("Validacion de nombre de institucion incorrecta", "/admin/manageinstitutions.xhtml?pattern="+a,o1 );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post("/admin/manageinstitution.xhtml?inst_id=7978")
                    .param("id", utiles_testAdministracionInstitucion.getIdSitio())
                    .param("name", a)
                    .param("zip", utiles_testAdministracionInstitucion.getCodigoCorrecta())
                    .param("website",utiles_testAdministracionInstitucion.getSitioWebCorrecto())
                    .param("enabled", utiles_testAdministracionInstitucion.getHabilitadoCorrecto())
                    .param("country_id",utiles_testAdministracionInstitucion.getSeleccionarPaisCorrecta())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            String o2;
            o2=r.getResponse().getRedirectedUrl();
            if(o2!=null)
            {
                if(o2.equals("http://localhost/index.xhtml"))
                {
                    o2=null;
                }
            }


            Assert.assertEquals("Validacion de nombre de institucion incorrecta", null, o2);

        }


    }




}
