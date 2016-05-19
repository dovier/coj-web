package Test_Administracion.COJBoard.Competencia;

import cu.uci.coj.board.dao.WbContestDAO;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.controller.admin.WbBoardAdminController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.validator.WbContestValidator;
import cu.uci.coj.validator.WbSiteValidator;
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
public class SeleccionarSitioCompetencia_Test {

    private MockMvc mockMvc;


    @Mock
    private ContestDAO contestDAOMock;
    @Mock
    private WbSiteDAO WbSiteDAOMock;
    @Mock
    private WbSiteValidator wbSiteValidator;
    @Mock
    private WbContestDAO WbContestDAOMock;
    @Spy
    private WbContestValidator validator;
    @InjectMocks
    private WbBoardAdminController wbBoardAdminController;
    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionCompetencia utiles_testAdministracionCompetencia;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionCompetencia = new Utiles_TestAdministracionCompetencia();
        this.mockMvc = MockMvcBuilders.standaloneSetup(wbBoardAdminController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionCompetencia.getUsuario()).param("j_password", utiles_testAdministracionCompetencia.getContrasenna()))
                .andReturn();

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {

    }


    //Validando seleccionar sitio de competencia
    @Test
    public void seleccionarSitioCompetencia() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionCompetencia.getSeleccionarSitiocorrectoCompetencia();
        ArrayList<String> lista2 = utiles_testAdministracionCompetencia.getSeleccionarSitioincorrectoCompetencia();

        String direccionAux="/admin/wboard/contest/edit.xhtml?id="+ utiles_testAdministracionCompetencia.getIdCOmpetencia();

        for (String a : lista1) {
            r = mockMvc.perform(post(direccionAux)
                    .param("name", utiles_testAdministracionCompetencia.getNombreCorrecto())
                    .param("url", utiles_testAdministracionCompetencia.getUrlCorrecta())
                    .param("startDate", utiles_testAdministracionCompetencia.getFechaInicioCorrecta())
                    .param("endDate", utiles_testAdministracionCompetencia.getFechaFinCorrecta())
                    .param("notifCreated", utiles_testAdministracionCompetencia.getNotifCreated())
                    .param("notifChanged", utiles_testAdministracionCompetencia.getNotifChanged())
                    .param("sid", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            String o1;
            o1=r.getResponse().getRedirectedUrl();
            String comparar1="/admin/wboard/contest/list.xhtml";
            if(o1!=null)
            {
                if(o1.equals("http://localhost/index.xhtml"))
                {
                   o1=comparar1;
                }
            }
            else
            {
                o1=null;
            }
            Assert.assertEquals("Validacion de seleccionar sitio incorrecta", "/admin/wboard/contest/list.xhtml",o1 );
        }


        for (String a : lista2) {
            r = mockMvc.perform(post(direccionAux)
                    .param("name", utiles_testAdministracionCompetencia.getNombreCorrecto())
                    .param("url", utiles_testAdministracionCompetencia.getUrlCorrecta())
                    .param("startDate", utiles_testAdministracionCompetencia.getFechaInicioCorrecta())
                    .param("endDate", utiles_testAdministracionCompetencia.getFechaFinCorrecta())
                    .param("notifCreated", utiles_testAdministracionCompetencia.getNotifCreated())
                    .param("notifChanged", utiles_testAdministracionCompetencia.getNotifChanged())
                    .param("sid", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            String o2;
            o2=r.getResponse().getRedirectedUrl();
            String comparar2="/admin/wboard/contest/list.xhtml";
            if(o2!=null)
            {
             if(o2.equals("http://localhost/index.xhtml"))
             {
                 o2=null;
             }
            }


            Assert.assertEquals("Validacion de seleccionar sitio incorrecta", null, o2);

        }


    }





}
