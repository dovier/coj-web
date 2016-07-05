package Test_Administracion.Usuarios;


import cu.uci.coj.controller.admin.UserController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.User;
import cu.uci.coj.validator.userValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})
public class Usuarios_Test {

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


    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    MvcResult result;
    MvcResult r;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        result = mockMvc.perform(post("/j_spring_security_check").param("j_username", "ybroche").param("j_password", "adminpass123"))
                .andReturn();
        validator.setUserDAO(userDAO);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Validando si el campo "nick" de la clase Usuarios es correcto
     */
    @Test
    public void validar_nick_Test() throws Exception {

        ArrayList<String> nickCorrectos = new ArrayList<>();
        nickCorrectos.add("asd");
        nickCorrectos.add("12341234");
        nickCorrectos.add("[];'./@#$%^");
        nickCorrectos.add(" asd");
        nickCorrectos.add("asd ");
        nickCorrectos.add("asd asd 333");
        nickCorrectos.add("123123123123123");//15 caracteres

        for (String a : nickCorrectos) {
        r = mockMvc.perform(post("/admin/manageuser.xhtml?username=saulvega")
                    .param("nick", a)
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de nick bien","/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl());
        }

        //Listado de valores incorrectos para -nick- en -Usuarios-
        ArrayList<String> nickIncorrectos= new ArrayList<>();
        nickIncorrectos.add("");
        nickIncorrectos.add("    ");
        nickIncorrectos.add("a");
        nickIncorrectos.add("123456789123456sasdasdGGGs");//mas de 15 caracteres

       for (String a : nickIncorrectos) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml?username=saulvega")
                    .param("nick", a)
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de nick mal", null,r.getResponse().getRedirectedUrl() );
        }
    }

    /**
     * Validando si el campo "country_id" de la clase Usuarios es correcto
     */
    @Test
    public void validar_country_id_Test() throws Exception {

        //ArrayList<String> lista1 = utiles_usuarioTest.getCountry_idCorrectas();
        //ArrayList<String> lista2 = utiles_usuarioTest.getCountry_idIncorrectas();

       ArrayList<String> country_id_Correctos = new ArrayList<>();
       country_id_Correctos.add("14");
       country_id_Correctos.add("-1");

        for (String a : country_id_Correctos) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepeep")
                    .param("update_nick","true")
                    .param("country_id", a)
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de country_id incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> country_id_Incorrectos = new ArrayList<>();
        country_id_Correctos.add("0");

        for (String a : country_id_Incorrectos) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", a)
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de country_id incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "institution_id" de la clase Usuarios es correcto
     */

    @Test
    public void validar_institution_id_Test() throws Exception {

        ArrayList<String> institution_idCorrecto = new ArrayList<>();
        institution_idCorrecto.add("564654888");
        institution_idCorrecto.add("1");
        institution_idCorrecto.add("-4545");

        for (String a : institution_idCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepeep")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", a)
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de country_id incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> institution_idIncorrecto = new ArrayList<>();
        //institution_idIncorrecto.add("-1");

        for (String a : institution_idIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", a)
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de country_id incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "locale" de la clase Usuarios es correcto
     */

    @Test
    public void validar_locale_Test() throws Exception {

        ArrayList<String> localeCorrecto = new ArrayList<>();
        localeCorrecto.add("1");
        localeCorrecto.add("2");
        localeCorrecto.add("-5");
        localeCorrecto.add("687987465");

        for (String a : localeCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", a)
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de locale incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "lid" de la clase Usuarios es correcto
     */

    @Test
    public void validar_lid_Test() throws Exception {

        ArrayList<String> lidCorrecto = new ArrayList<>();
        lidCorrecto.add("1");
        lidCorrecto.add("2");
        lidCorrecto.add("-5");
        //lidCorrecto.add("0");
        lidCorrecto.add("687987465");

        for (String a : lidCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", a)
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de lid incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> lidIncorrecto = new ArrayList<>();
        lidIncorrecto.add("0");

        for (String a : lidIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", a)
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de lid incorrecta1", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "password" de la clase Usuarios es correcto
     */

    @Test
    public void validar_password_Test() throws Exception {

        ArrayList<String> passwordCorrecto = new ArrayList<>();
        passwordCorrecto.add("798654 4565 asdasd asdasd");
        passwordCorrecto.add("798654 4565");
        passwordCorrecto.add("!@#$%^&*()_");
        passwordCorrecto.add("MAMAMAMasas121 +_)*&^%$#");
        passwordCorrecto.add("qwertyui");
        passwordCorrecto.add("qwe45lkjhgasrtyuiopasdfghjklzxcvbnm,qwerftyuiopasdfghjklzxcvbnmqwertyuioasdfghjkxcv bnsdfghjkldvbnm ");//100 caracteres

        for (String a : passwordCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepeep")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", a)
                    .param("confirmPassword", a)
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de password incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> passwordIncorrecto = new ArrayList<>();
        passwordIncorrecto.add("a");//menos de 8 caracteres
        passwordIncorrecto.add("asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdaksjdhakjsbdkjahsdkjahsdkjhakjsdhakjsdhkjasdhakjsdhkajsdh");//mas de 100 caracteres
        passwordIncorrecto.add("798654");
        //passwordIncorrecto.add("");
        //passwordIncorrecto.add("   ");

        for (String a : passwordIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", a)
                    .param("confirmPassword", a)
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de password incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "confirmPassword" de la clase Usuarios es correcto
     */

    @Test
    public void validar_confirmPassword_Test() throws Exception {

        ArrayList<String> confirmPasswordCorrecto = new ArrayList<>();
        confirmPasswordCorrecto.add("798654 4565 asdasd asdasd");
        confirmPasswordCorrecto.add("798654 4565");
        confirmPasswordCorrecto.add("!@#$%^&*()_");
        confirmPasswordCorrecto.add("MAMAMAMasas121 +_)*&^%$#");
        confirmPasswordCorrecto.add("qwertyui");
        confirmPasswordCorrecto.add("qwe45lkjhgasrtyuiopasdfghjklzxcvbnm,qwerftyuiopasdfghjklzxcvbnmqwertyuioasdfghjkxcv bnsdfghjkldvbnm ");//100 caracteres

        for (String a : confirmPasswordCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepeep")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", a)
                    .param("confirmPassword", a)
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de password incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> confirmPasswordIncorrecto = new ArrayList<>();
        confirmPasswordIncorrecto.add("asdasdasd");
//        confirmPasswordIncorrecto.add("798654 4565");
//        confirmPasswordIncorrecto.add("!@#$%^&*()_");
//        confirmPasswordIncorrecto.add("qwertyui");
//        confirmPasswordIncorrecto.add("MAMAMAMasas121 +_)*&^%$#");
//        confirmPasswordIncorrecto.add("  ");
//        confirmPasswordIncorrecto.add("");
//        confirmPasswordIncorrecto.add("a");//menos de 8 caracteres
//        confirmPasswordIncorrecto.add("asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdaksjdhakjsbdkjahsdkjahsdkjhakjsdhakjsdhkjasdhakjsdhkajsdh");//mas de 100 caracteres
//        confirmPasswordIncorrecto.add("798654");

        for (String a : confirmPasswordIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", a)
                    .param("confirmPassword","asdasdasd")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            //Comprueba si el user y el pasword son iguales, es caso contrario el test fallara
            if (!a.equals("asdasdasd")){
                Assert.fail();
            }
                Assert.assertEquals("Validacion de password incorrecta", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );

        }
    }


    /**
     * Validando si el campo "access_rule" de la clase Usuarios es correcto
     */

    @Test
    public void validarNombre_access_rule_Test() throws Exception {

        ArrayList<String> access_ruleCorrecto = new ArrayList<>();
        access_ruleCorrecto.add("*");

        for (String a : access_ruleCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", a)
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de access_rule incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> access_ruleIncorrecto = new ArrayList<>();
        access_ruleIncorrecto.add("0");
        access_ruleIncorrecto.add(",");
        access_ruleIncorrecto.add("qwe");
        access_ruleIncorrecto.add("-54654");
        access_ruleIncorrecto.add("&*");
        access_ruleIncorrecto.add("*65");
        access_ruleIncorrecto.add(" ");
        access_ruleIncorrecto.add("");
        access_ruleIncorrecto.add(" *");
        access_ruleIncorrecto.add("* ");

        for (String a : access_ruleIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", a)
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de access_rule incorrecta1", null,r.getResponse().getRedirectedUrl() );
        }
    }

    /**
     * Validando si el campo "name" de la clase Usuarios es correcto
     */

    @Test
    public void validar_name_Test() throws Exception {

        ArrayList<String> nameCorrecto = new ArrayList<>();
        nameCorrecto.add("a");
        nameCorrecto.add("asdasdasdasdasdasdasdasdasdasd");
        nameCorrecto.add("Maria Fernanda");
        nameCorrecto.add(".-' .-'");

        for (String a : nameCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", a)
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de name incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

//        [.-'] y espacios
        ArrayList<String> nameIncorrecto = new ArrayList<>();
        nameIncorrecto.add(" ");
        nameIncorrecto.add("");
        nameIncorrecto.add("654654654");//deberia pasar el test como juego de datos incorrecto
        nameIncorrecto.add("#$%^&*()%^&*");

        for (String a : nameIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", a)
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();
            Assert.assertEquals("Validacion de name incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }

    /**
     * Validando si el campo "lastname" de la clase Usuarios es correcto
     */

    @Test
    public void validar_lastname_Test() throws Exception {

        ArrayList<String> lastnameCorrecto = new ArrayList<>();
        lastnameCorrecto.add("a");
        lastnameCorrecto.add("asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdas");//50 caracteres
        lastnameCorrecto.add("Maria Fernanda");
        lastnameCorrecto.add(".-' .-'");

        for (String a : lastnameCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", a)
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de lastname incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

//        [.-'] y espacios
        ArrayList<String> lastnameIncorrecto = new ArrayList<>();
        lastnameIncorrecto.add(" ");
        lastnameIncorrecto.add("");
        lastnameIncorrecto.add("654654654");//deberia pasar el test como juego de datos incorrecto
        lastnameIncorrecto.add("#$%^&*()%^&*");

        for (String a : lastnameIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", a)
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            System.out.println(r.getResponse().getRedirectedUrl());
            Assert.assertEquals("Validacion de lastname incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "gender" de la clase Usuarios es correcto
     */

    @Test
    public void validar_gender_Test() throws Exception {

        ArrayList<String> genderCorrecto = new ArrayList<>();
        genderCorrecto.add("1");
        genderCorrecto.add("2");

        for (String a : genderCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", a)
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "fchavez@uci.cu")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de gender incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "email" de la clase Usuarios es correcto
     */

    @Test
    public void validar_email_Test() throws Exception {

        ArrayList<String> emailCorrecto = new ArrayList<>();
        emailCorrecto.add("asd@asd.asd");
        emailCorrecto.add("fchavez@uci.cu");
        emailCorrecto.add("asd132@asd.asd");
        emailCorrecto.add("asd_132@asd.asd");
        emailCorrecto.add("fchavez@uci.cu%");

        for (String a : emailCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", a)
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", a)
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de email incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> emailIncorrecto = new ArrayList<>();
        emailIncorrecto.add("fchavez@uci");
        emailIncorrecto.add("asd@asd.asd.asd.asd.asd.asd.");
        emailIncorrecto.add("asd 123@asd.asd");
        emailIncorrecto.add("");
        emailIncorrecto.add(" ");
        emailIncorrecto.add("fchavez@@uci");
        emailIncorrecto.add("fchavez @uci.cu");
        emailIncorrecto.add("fchavez@ uci.cu");
        emailIncorrecto.add("fchavez @uci. cu");
        emailIncorrecto.add(" chavez@uci.cu");
        emailIncorrecto.add("fchavez @uci.cu ");
        emailIncorrecto.add("fchavez@uci.cu%*a%s^d");

        for (String a : emailIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", a)
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", "400000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de email incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "mail_quote" de la clase Usuarios es correcto
     * Campo debe ser obligatorio y un numero entero menor que 1000000
     */

    @Test
    public void validar_mail_quote_Test() throws Exception {

        ArrayList<String> mail_quoteCorrecto = new ArrayList<>();
        mail_quoteCorrecto.add("1");
        mail_quoteCorrecto.add("0");
        mail_quoteCorrecto.add("-1");
        mail_quoteCorrecto.add("1000000");
        mail_quoteCorrecto.add("-1000000");
        mail_quoteCorrecto.add("  1000000");
        mail_quoteCorrecto.add("1000000   ");

        for (String a : mail_quoteCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", a)
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "asd@asd.asd")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de mail_quote incorrecta1", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
        }

        ArrayList<String> mail_quoteIncorrecto = new ArrayList<>();
        mail_quoteIncorrecto.add("");
        mail_quoteIncorrecto.add("   ");
        mail_quoteIncorrecto.add("asdasd");
        mail_quoteIncorrecto.add("123asd");
        mail_quoteIncorrecto.add("asd123");
        mail_quoteIncorrecto.add("88888888");

        for (String a : mail_quoteIncorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", "hernandez")
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "asd@asd.asd")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", "ROLE_ADMIN")
                    .param("mail_quote", a)
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            Assert.assertEquals("Validacion de mail_quote incorrecta", null,r.getResponse().getRedirectedUrl() );
        }
    }


    /**
     * Validando si el campo "authorities" de la clase Usuarios es correcto
     */

    @Test
    public void validar_authorities_Test() throws Exception {

        ArrayList<String> authoritiesCorrecto = new ArrayList<>();
        authoritiesCorrecto.add("ROLE_ADMIN");
        authoritiesCorrecto.add("ROLE_SUPER_PSETTER");
        authoritiesCorrecto.add("ROLE_PSETTER");
        authoritiesCorrecto.add("ROLE_JUDGE");
        authoritiesCorrecto.add("ROLE_USER");
        authoritiesCorrecto.add("ROLE_TEAM");
        authoritiesCorrecto.add("ROLE_FILE_MANAGER");
        authoritiesCorrecto.add("ROLE_ENTRIES_MANAGER");
        authoritiesCorrecto.add("ROLE_TRANSLATOR");
        authoritiesCorrecto.add("");

        for (String a : authoritiesCorrecto) {
            r = mockMvc.perform(post("/admin/manageuser.xhtml")
                    .param("nick", "pepe")
                    .param("update_nick","true")
                    .param("country_id", "14")
                    .param("institution_id", "14")
                    .param("locale", "2")
                    .param("lid", "44")
                    .param("password", "12345678")
                    .param("confirmPassword", "12345678")
                    .param("access_rule", "*")
                    .param("enabled", "true")
                    .param("banReason", "")
                    .param("name", "franly")
                    .param("lastname", a)
                    .param("gender", "1")
                    .param("year", "1930")
                    .param("month", "1")
                    .param("day", "1")
                    .param("showdob", "true")
                    .param("email", "asd@asd.asd")
                    .param("contestNotifications", "true")
                    .param("problemNotifications", "true")
                    .param("submissionNotifications", "true")
                    .param("authorities", a)
                    .param("mail_quote", "40000")
                    .session((MockHttpSession) result.getRequest().getSession()))
                    .andReturn();

            if(a.equals("")){
                Assert.assertEquals("Validacion de authorities incorrecta1", null ,r.getResponse().getRedirectedUrl() );
            }else{
                Assert.assertEquals("Validacion de authorities incorrecta", "/admin/manageusers.xhtml",r.getResponse().getRedirectedUrl() );
            }
        }
    }

}