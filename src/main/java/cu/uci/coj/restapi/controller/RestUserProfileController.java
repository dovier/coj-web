/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;


import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.User;
import cu.uci.coj.restapi.templates.CountryRest;
import cu.uci.coj.restapi.templates.UserProfileRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.restapi.utils.TokenUtils;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cesar
 */
@Controller
@RequestMapping("/userprofile")
public class RestUserProfileController {
    
    @Resource
    private UserDAO userDAO;
    @Resource
    protected JdbcTemplate jdbcTemplate;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private CountryDAO countryDAO;
    @Resource
    private InstitutionDAO institutionDAO;
    
    
    @ApiOperation(value = "Obtener el perfil de un usuario",  
            notes = "Dado el nombre de usuario, devuelve el perfil de este.",
            response = UserProfileRest.class)
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username disabled"),
                            @ApiResponse(code = 404, message = "bad user")   })
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getUserProfile(
            @ApiParam(value = "Nombre  de usuario") @PathVariable String username) {

        User user = null;

        if (!userDAO.bool("is.user.enabled", username))
            return new ResponseEntity<>(ErrorUtils.USERNAME_DISABLED, HttpStatus.UNAUTHORIZED);
      
        Integer uid = userDAO.idByUsername(username);
        if (uid != null && userDAO.isUser(username)) {
            user = userDAO.loadUserData(username);
            if (user.isTeam())
                return new ResponseEntity<>(ErrorUtils.BAD_USER, HttpStatus.NOT_FOUND);
            
            List<Problem> solved = userDAO.objects("problems.solved.1", Problem.class, user.getUid());
            List<Problem> unsolved = userDAO.getProblemsTryied(user.getUid());

            if (solved.isEmpty()) {
                user.setLast_accepted("***");
            }
            if (unsolved.isEmpty() && solved.isEmpty()) {
                user.setLast_submission("***");
            }            
            
            user.setSolved(solved.size());
            user.setUnsolved(unsolved.size());
            user.setRanking(userDAO.integer("ranking.position", username));
            user.setTot_ranking(userDAO.countEnabledUsers(null, false));
            user.setRankingbycountry(userDAO.integer("ranking.position.country", user.getCountry_id(), username));
            user.setTot_rankingbycountry(userDAO.countEnabledUsersByCountry(null, false, user.getCountry_id()));
            user.setRankingbyinstitution(userDAO.integer("ranking.position.institution", user.getInstitution_id(), username));
            user.setTot_rankingbyinstitution(userDAO.countEnabledUsersByInstitutions(null, false, user.getInstitution_id()));

            //PagingOptions options = new PagingOptions(1, "asc", "startDate");
            //IPaginatedList<WbContest> contests = wbContestService.getContestList(0, options, 1, user.getUid());
            //model.addAttribute("contests", contests);

            //List<WbSite> list = wbSiteDAO.getSiteList();
            //HashMap<Integer, WbSite> map = new HashMap<Integer, WbSite>();
            //for (int i = 0; i < list.size(); i++) {
            //    map.put(list.get(i).getSid(), list.get(i));
            //}
            //model.addAttribute("mapsites", map);
        }

        Entry lastentry = userDAO.object("last.entry.by.user", Entry.class, uid);
        
        String lastentryText = "";
        String lastentryDate = "";        
        if(lastentry != null){
            lastentryText = lastentry.getText();
            lastentryDate = lastentry.getDate().toString();
        }
               
        //model.addAttribute("count.entries", userDAO.integer(0, "count.entries", uid));
        int followers = userDAO.integer(0, "count.followers", uid);
        int following = userDAO.integer(0, "count.following", uid);
        
        String avatar = "http://coj.uci.cu/images/avatars/"+username;
        String gender = "female";
        if(user.getGender() == 1)
            gender = "male";
        
        UserProfileRest userRest = new UserProfileRest(avatar,user.getName(), user.getLastname(), username, gender, user.getCountry(), user.getCountry_desc(), user.getInstitution_desc(), user.getPlanguage(), user.getRgdate(), user.getLast_submission(), user.getLast_accepted(), user.getScore(), user.getRanking(),user.getRankingbyinstitution(),user.getRankingbycountry(), lastentryText,lastentryDate,followers,following);
        userRest.setNickname(user.getNick());
        
        return new ResponseEntity<>(userRest, HttpStatus.OK);
}

    
    @ApiOperation(value = "Modificar Perfil de Usuario (Privado)",  
            notes = "Modifica el perfil de usuario con los datos enviados.")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch, hash incorrect, token expirated, username apikey mismatch, apikey hash incorrect, apikey expirated, apikey secret incorrect, token or apikey incorrect"),
                            @ApiResponse(code = 400, message = "institution witout country, incorrect request"),
                            @ApiResponse(code = 412, message = "Nick must not more than 25 characters, Nick must not less than 3 characters, The first name is too short, The first name is too long, The first name contains invalid characters, The last name is too long, The last name is too short, The last name contains invalid characters, Required field, This e-mail already exists, Invalid email."),
                            @ApiResponse(code = 404, message = "bad user, bad institution id, bad language, bad locale, bad gender, bad country id"),
                            @ApiResponse(code = 500, message = "failed send email"),})
    @RequestMapping(value = "/update", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<?> UpdateProfile(
            @ApiParam(value = "Llave de desarrollador") @RequestParam(value = "apikey") String apikey,
            @ApiParam(value = "Token de usuario") @RequestParam(value = "token") String token,
            @ApiParam(value = "Año de nacimiento") @RequestParam(value = "year", required = false) Integer year,
            @ApiParam(value = "Mes de nacimiento") @RequestParam(value = "month", required = false) Integer month,
            @ApiParam(value = "Día de nacimiento") @RequestParam(value = "day", required = false) Integer day,
            @ApiParam(value = "Apodo")  @RequestParam(value = "nick", required = false) String nick,
            @ApiParam(value = "Nombre") @RequestParam(value = "name", required = false) String name,
            @ApiParam(value = "Apellido") @RequestParam(value = "lastname", required = false) String lastname,
            @ApiParam(value = "Correo") @RequestParam(value = "email", required = false) String email,
            @ApiParam(value = "Identificador del País") @RequestParam(value = "country_id", required = false) Integer country_id,
            @ApiParam(value = "Identificador de la Institución") @RequestParam(value = "institution_id", required = false) Integer institution_id,
            @ApiParam(value = "Identificador del lenguaje favorito (Ver filters)") @RequestParam(value = "lid", required = false) Integer lid,
            @ApiParam(value = "Identificador del idioma favorito (Ver filters)") @RequestParam(value = "locale", required = false) Integer locale,
            @ApiParam(value = "Sexo:  (1)Hombre (2) Mujer", allowableValues = "1,2") @RequestParam(value = "gender", required = false) Integer gender) {
        
        try {
           

            int error = ValidateApiAndToken(apikey, token);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }
            
            String username = null;
            username = ExtractUser(token);
            
            User user = userDAO.loadAllUserData(username);
            if(year!=null)
                user.setYear(year);
            if(month!=null)
                user.setMonth(month);
            if(day!=null)
                user.setDay(day);
            user.setUsername(username);
            if(nick!=null)
                user.setNick(nick);
            if(name!=null)
             user.setName(name);
            if(lastname!=null)
                user.setLastname(lastname);
            if(email!=null)
                user.setEmail(email);
            if(country_id!=null)
                user.setCountry_id(country_id);
            if(institution_id!=null)
                user.setInstitution_id(institution_id);
            if(lid!=null)
                user.setLid(lid);
            if(locale!=null)
                user.setLocale(locale);
            if(gender!=null)
                user.setGender(gender);            
                    
            user.setUid(userDAO.integer("select.uid.by.username", username));
            user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
            
            
       
            boolean is_team = !userDAO.bool("is.user", user.getUsername());            
            if(is_team)
                return new ResponseEntity<>(ErrorUtils.BAD_USER,HttpStatus.NOT_FOUND);        
            
            user.setTeam(false);
            
            String errors = ValidateUser(user);
            if(!errors.equals("0"))
                return new ResponseEntity<>(errors,HttpStatus.PRECONDITION_FAILED);
            
            if(country_id != null && !ValidateCountry(country_id))
                return new ResponseEntity<>(ErrorUtils.BAD_COUNTRY_ID, HttpStatus.NOT_FOUND);
            
            if(country_id != null && institution_id != null && !ValidateInstitutionID(country_id, institution_id))
                return new ResponseEntity<>(ErrorUtils.BAD_INSTITUTION_ID, HttpStatus.NOT_FOUND);
            
            if(country_id == null && institution_id!=null)
                return new ResponseEntity<>(ErrorUtils.INSTITUTION_WITHOUT_COUNTRY,HttpStatus.BAD_REQUEST);
            
            if(lid != null && !ValidateLanguage(lid))
                return new ResponseEntity<>(ErrorUtils.BAD_LANGUAGE,HttpStatus.NOT_FOUND);
            
            if(locale !=null && !ValidateLocale(locale))
                return new ResponseEntity<>(ErrorUtils.BAD_LOCALE,HttpStatus.NOT_FOUND);
            
            if(gender != null && gender!=1 && gender!=2)
                return new ResponseEntity<>(ErrorUtils.BAD_GENDER,HttpStatus.NOT_FOUND);
            
            try{
                userDAO.updateUser(user);
            }catch(Exception e){
                return new ResponseEntity<>(ErrorUtils.FAILED_SEND_EMAIL, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (IOException ex) {
           return new ResponseEntity<>(ErrorUtils.INCORRECT_JSON, HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    public boolean ValidateCountry(Integer country_id){
        String sql = "SELECT * FROM public.country WHERE country_id = ?";
        try{
        Country country =  (Country) jdbcTemplate.queryForObject(sql,new Object[]{country_id},new BeanPropertyRowMapper(Country.class));
        }catch(Exception e){return false;}
        
        return true;
    }
    
    public boolean ValidateLocale(Integer locale){
        List<cu.uci.coj.model.Locale> listlocale = utilDAO.objects("enabled.locale", cu.uci.coj.model.Locale.class);
        for(cu.uci.coj.model.Locale l:listlocale){
            if(locale == l.getLid())
                return true;
        }
        
        return false;
    }
    
    public boolean ValidateLanguage(Integer lid){
        List<Language> listlanguages = utilDAO.getEnabledProgramingLanguages();
        for(Language l:listlanguages){
            if(lid == l.getLid())
                return true;
        }
        
        return false;
    }
    
    
    private boolean ValidateInstitutionID(int country_id, int institution_id){
        List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(country_id);
        
        if(institution_id == -1)
            return true;
        
        for(Institution ins:institutions){
            if(ins.getId() == institution_id)
                return true;
        }
        
        return false;
    }
    

   

    private String ValidateUser(User user){
        ResourceBundleMessageSource r=new ResourceBundleMessageSource();
        r.setBasename("messages_en");   

        user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
        
        if(user.getNick().length()==0)
            return r.getMessage("judge.register.error.nick",null, new Locale("en"));
       
        if ((user.getNick().length()) > 15)
            return r.getMessage("judge.register.error.long25charact",null, new Locale("en"));
        
        if (user.getNick().length() < 3)
            return r.getMessage("judge.register.error.less3charact",null, new Locale("en"));
        
        if (user.getName().length() < 1) 
            return r.getMessage("errormsg.7",null, new Locale("en"));
        
        if (user.getName().length() > 30) 
            return r.getMessage("errormsg.6",null, new Locale("en"));
        
        if (!user.getName().matches("[a-zA-Z\\.\\-\\'\\s]+"))
            return r.getMessage("errormsg.8",null, new Locale("en"));        

        if (user.getLastname().length() < 1) 
            return r.getMessage("errormsg.10",null, new Locale("en"));

        if (user.getLastname().length() > 50) 
            return r.getMessage("errormsg.9",null, new Locale("en"));
        
        if (!user.getLastname().matches("[a-zA-Z\\.\\-\\'\\s]+"))
            return r.getMessage("errormsg.11",null, new Locale("en"));
        
        // si el correo ha sido cambiado y esta en uso por otra persona en el
        // COJ
        if(user.getEmail().length() == 0)
            return r.getMessage("errormsg.51",null, new Locale("en"));        
 
        if (!StringUtils.isEmpty(user.getEmail()) && userDAO.bool("email.changed", user.getEmail(), user.getUid()) && userDAO.emailExistUpdate(user.getEmail().trim(), user.getUsername())) 
            return r.getMessage("judge.register.error.emailexist",null, new Locale("en"));  
        
    
        EmailValidator emailValidator = EmailValidator.getInstance(); //ver como inyectar este objeto
        if (!emailValidator.isValid(user.getEmail())) 
            return r.getMessage("judge.register.error.bademail",null, new Locale("en")); 
     
        if (user.getCountry_id() == 0) 
            return r.getMessage("judge.register.error.country",null, new Locale("en")); 
        
        if (user.getInstitution_id() == 0) 
            return r.getMessage("judge.register.error.institution",null, new Locale("en"));        

        if (user.getLid() == 0) 
            return r.getMessage("judge.register.error.planguage",null, new Locale("en"));         

        if (user.getLocale() == 0) 
            return r.getMessage("judge.register.error.locale",null, new Locale("en"));        

        if(user.getName().length() == 0)
            return r.getMessage("judge.register.error.name",null, new Locale("en"));        
    
        if (user.getGender() == 0) 
            return r.getMessage("judge.register.error.gender",null, new Locale("en"));
              
        return "0";
    }
    
    
    
    
    private int ValidateApiAndToken(String apikey, String token) throws IOException {
        

        try {
            int error = TokenUtils.ValidateAPIKey(apikey);
            if (error > 0) {
                return error;
            }

            int error2 = TokenUtils.ValidateTokenUser(token);
            if (error2 > 0) {
                return error2;
            }
        } catch (Exception e) {
            return 9;
        }

        return 0;
    }
    
    private String ExtractUser(String token) {
        String username = null;
        try {
            username = TokenUtils.getUsernameFromToken(token);
        } catch (Exception ex) {
            Logger.getLogger(RestProblemsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username;
    }  
    
    
    
}
