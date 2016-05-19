package Test_Administracion.Competencias.ConcursosReales.ConfiguracionGeneral;

import cu.uci.coj.controller.admin.ContestController;
import cu.uci.coj.dao.*;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.service.ContestService;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.validator.contestValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

import javax.servlet.ServletContext;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class FechaConcurso_Test {

    private MockMvc mockMvc;

    @Mock
    private Utils utils;
    @Mock
    private ContestDAO contestDAO;
    @Mock
    private ContestAwardDAO contestAwardDAO;
    @Mock
    private ProblemDAO problemDAO;
    @Mock
    private UtilDAO utilDAO;
    @Mock
    private UserDAO userDAO;
    @Spy
    private contestValidator validator;
    @Mock
    private MailNotificationService mailNotificationService;
    @Mock
    private ContestService contestService;

    @Mock
    protected BaseDAO baseDAO;
    @Mock
    protected MessageSource messageSource;
    @Mock
    private ServletContext servletContext;

    @InjectMocks
    private ContestController contestController;





    MvcResult result;
    //Variables auxiliares
    MvcResult r;

   private Utiles_TestAdministracionConursoR utiles_testAdministracionConursoR;
    private FilterChainProxy springSecurityFilterChain;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        utiles_testAdministracionConursoR = new Utiles_TestAdministracionConursoR();
        this.mockMvc = MockMvcBuilders.standaloneSetup(contestController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", utiles_testAdministracionConursoR.getUsuario()).param("j_password", utiles_testAdministracionConursoR.getContrasenna()))
                .andReturn();

        validator.setContestDAO(contestDAO);

        }



    @After
    // tearDown destruye objetos utilizados despues del test
    public void tearDown() throws Exception {
        mockMvc=null;
        result=null;
        System.gc();

    }


    //Validando fechas
    @Test
    public void validarFecha() throws Exception {

        ArrayList<ArrayList<String>> lista1 = utiles_testAdministracionConursoR.getFechasCorrectas();
        ArrayList<ArrayList<String>>lista2 = utiles_testAdministracionConursoR.getFechasIncorrectas();

       String direccionAux="/admin/managecontest.xhtml?cid="+ utiles_testAdministracionConursoR.getIdConcurso();



        for (ArrayList<String> a : lista1) {
            String ryear=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"anno");
            String rmonth=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"mes");
            String rday=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"dia");

            String rhour=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"hora");
            String rminutes=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"minuto");
            String rseconds=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"segundo");

            String iyear=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"anno");
            String imonth=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"mes");
            String iday=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"dia");

            String ihour=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"hora");
            String iminutes=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"minuto");
            String iseconds=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"segundo");

            String eyear=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"anno");
            String emonth=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"mes");
            String eday=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"dia");

            String ehour=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"hora");
            String eminutes=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"minuto");
            String eseconds=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"segundo");

           r = mockMvc.perform(post(direccionAux)
                    .param("cid",utiles_testAdministracionConursoR.getIdConcurso())
                    .param("name", utiles_testAdministracionConursoR.getNombreCorrecto())
                    .param("registration", utiles_testAdministracionConursoR.getRegistroCorrecto())

                    .param("ryear", ryear)
                    .param("rmonth",rmonth)
                    .param("rday", rday)

                    .param("rhour", rhour)
                    .param("rminutes", rminutes)
                    .param("rseconds", rseconds)

                    .param("iyear", iyear)
                    .param("imonth",imonth)
                    .param("iday", iday)

                    .param("ihour", ihour)
                    .param("iminutes",iminutes)
                    .param("iseconds", iseconds)

                    .param("eyear", eyear)
                    .param("emonth", emonth)
                    .param("eday",eday)

                    .param("ehour",ehour)
                    .param("eminutes", eminutes)
                    .param("eseconds", eseconds)

                    .param("style", utiles_testAdministracionConursoR.getTipoConcursoCorrecto())
                    .param("contestant", utiles_testAdministracionConursoR.getUsuarioCorrecto())
                    .param("enabled", utiles_testAdministracionConursoR.getActivadoCorrecto())
                    .param("blocked", utiles_testAdministracionConursoR.getEstadoCorrecto())
                    .param("grouped", utiles_testAdministracionConursoR.getAgrupadosCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de fechas incorrecta", direccionAux,o );
        }


        for (ArrayList<String> a : lista2) {

            String aux=a.get(0);
            String ryear=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"anno");
            String rmonth=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"mes");
            String rday=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"dia");

            String rhour=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"hora");
            String rminutes=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"minuto");
            String rseconds=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(0),"segundo");

            String iyear=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"anno");
            String imonth=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"mes");
            String iday=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"dia");

            String ihour=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"hora");
            String iminutes=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"minuto");
            String iseconds=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(1),"segundo");

            String eyear=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"anno");
            String emonth=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"mes");
            String eday=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"dia");

            String ehour=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"hora");
            String eminutes=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"minuto");
            String eseconds=utiles_testAdministracionConursoR.ExtraerDeFecha(a.get(2),"segundo");

            r = mockMvc.perform(post(direccionAux)
                    .param("cid",utiles_testAdministracionConursoR.getIdConcurso())
                    .param("name", utiles_testAdministracionConursoR.getNombreCorrecto())
                    .param("registration", utiles_testAdministracionConursoR.getRegistroCorrecto())

                    .param("ryear", ryear)
                    .param("rmonth",rmonth)
                    .param("rday", rday)

                    .param("rhour", rhour)
                    .param("rminutes", rminutes)
                    .param("rseconds", rseconds)

                    .param("iyear", iyear)
                    .param("imonth",imonth)
                    .param("iday", iday)

                    .param("ihour", ihour)
                    .param("iminutes",iminutes)
                    .param("iseconds", iseconds)

                    .param("eyear", eyear)
                    .param("emonth", emonth)
                    .param("eday",eday)

                    .param("ehour",ehour)
                    .param("eminutes", eminutes)
                    .param("eseconds", eseconds)

                    .param("style", utiles_testAdministracionConursoR.getTipoConcursoCorrecto())
                    .param("contestant", utiles_testAdministracionConursoR.getUsuarioCorrecto())
                    .param("enabled", utiles_testAdministracionConursoR.getActivadoCorrecto())
                    .param("blocked", utiles_testAdministracionConursoR.getEstadoCorrecto())
                    .param("grouped", utiles_testAdministracionConursoR.getAgrupadosCorrecto())
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Object o =new Object();
            o=r.getResponse().getRedirectedUrl();
            Assert.assertEquals("Validacion de fechas incorrecta", null,o );
        }


    }





}
