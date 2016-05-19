package Test_Administracion.Configuracion.Reglas;

import cu.uci.coj.controller.admin.GeneralConfigController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.security.COJSessionRegistryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
public class NombreReglas_Test {

    private MockMvc mockMvc;


    @Mock
    private MailNotificationService mailNotificationService;
    @Mock
    private ContestDAO contestDAO;
    @Mock
    private COJSessionRegistryImpl cojSessionRegistryImpl;
    @Mock
    private BaseDAO baseDAO;
   /* @Spy
    private WbContestValidator validator;*/
    @InjectMocks
    private GeneralConfigController generalConfigController;





    private MvcResult result;
    //Variables auxiliares
    private MvcResult r;

   private Utiles_TestAdministracionReglas utiles_testAdministracionReglas;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionReglas=new Utiles_TestAdministracionReglas();
        this.setMockMvc(MockMvcBuilders.standaloneSetup(generalConfigController).build());

        setResult(getMockMvc().perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionReglas.getUsuario()).param("j_password", utiles_testAdministracionReglas.getContrasenna()))
                .andReturn());

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {

    }


    //Validando nombre regla
    @Test
    public void validarNombreRegla() throws Exception {

        ArrayList<String> lista1 = utiles_testAdministracionReglas.getNombresCorrectosReglas();
        ArrayList<String> lista2 = utiles_testAdministracionReglas.getNombresIncorrectosReglas();


       String direccionAux="/admin/globalaccessrules.xhtml";
        for (String a : lista1) {
            setR(getMockMvc().perform(post(direccionAux)
                      .param("rule", a)
                    .session((MockHttpSession) getResult().getRequest().getSession()))
                    .andReturn());

            Object o =new Object();
            o= getR().getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de regla incorrecta", "/admin/globalaccessrules.xhtml",o );
        }


        for (String a : lista2) {
            setR(getMockMvc().perform(post(direccionAux)
                    .param("rule", a)
                    .session((MockHttpSession) getResult().getRequest().getSession()))
                    .andReturn());

            Assert.assertEquals("Validacion de regla incorrecta", null, getR().getResponse().getRedirectedUrl());

        }


    }


    public MockMvc getMockMvc() {
        return mockMvc;
    }

    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public MailNotificationService getMailNotificationService() {
        return mailNotificationService;
    }

    public void setMailNotificationService(MailNotificationService mailNotificationService) {
        this.mailNotificationService = mailNotificationService;
    }

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    public COJSessionRegistryImpl getCojSessionRegistryImpl() {
        return cojSessionRegistryImpl;
    }

    public void setCojSessionRegistryImpl(COJSessionRegistryImpl cojSessionRegistryImpl) {
        this.cojSessionRegistryImpl = cojSessionRegistryImpl;
    }

    public BaseDAO getBaseDAO() {
        return baseDAO;
    }

    public void setBaseDAO(BaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    public GeneralConfigController getGeneralConfigController() {
        return generalConfigController;
    }

    public void setGeneralConfigController(GeneralConfigController generalConfigController) {
        this.generalConfigController = generalConfigController;
    }

    public MvcResult getResult() {
        return result;
    }

    public void setResult(MvcResult result) {
        this.result = result;
    }

    public MvcResult getR() {
        return r;
    }

    public void setR(MvcResult r) {
        this.r = r;
    }

    public Utiles_TestAdministracionReglas getUtiles_testAdministracionReglas() {
        return utiles_testAdministracionReglas;
    }

    public void setUtiles_testAdministracionReglas(Utiles_TestAdministracionReglas utiles_testAdministracionReglas) {
        this.utiles_testAdministracionReglas = utiles_testAdministracionReglas;
    }

    public FilterChainProxy getSpringSecurityFilterChain() {
        return springSecurityFilterChain;
    }

    public void setSpringSecurityFilterChain(FilterChainProxy springSecurityFilterChain) {
        this.springSecurityFilterChain = springSecurityFilterChain;
    }
}
