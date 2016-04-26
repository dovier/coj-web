/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.User;
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
import javax.mail.AuthenticationFailedException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private InstitutionDAO institutionDAO;
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {

        User user = null;

        if (!userDAO.bool("is.user.enabled", username))
            return new ResponseEntity<>(ErrorUtils.USERNAME_DISABLED, HttpStatus.BAD_REQUEST);
      
        Integer uid = userDAO.idByUsername(username);
        if (uid != null && userDAO.isUser(username)) {
            user = userDAO.loadUserData(username);
            if (user.isTeam())
                return new ResponseEntity<>(ErrorUtils.BAD_USER, HttpStatus.BAD_REQUEST);
            
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
        
        UserProfileRest userRest = new UserProfileRest(avatar,user.getName(), user.getLastname(), username, gender, user.getCountry_desc(), user.getInstitution_desc(), user.getPlanguage(), user.getRgdate(), user.getLast_submission(), user.getLast_accepted(), user.getScore(), user.getRanking(),user.getRankingbyinstitution(),user.getRankingbycountry(), lastentryText,lastentryDate,followers,following);
 
        return new ResponseEntity<>(userRest, HttpStatus.OK);
}
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> UpdateProfile(@RequestBody String bodyjson) {
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            if(!TokenUtils.ValidatePropertiesinJson(node,"year","month","day","nick","name","lastname","email","country_id","institution_id","lid","locale","gender"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);
            
            String username = null;
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            int year = node.get("year").asInt();
            int month = node.get("month").asInt();
            int day = node.get("day").asInt();
            String nick = node.get("nick").asText();
            String name = node.get("name").asText();
            String lastname = node.get("lastname").asText();
            String email = node.get("email").asText();
            int country_id = node.get("country_id").asInt();
            int institution_id = node.get("institution_id").asInt();
            int lid = node.get("lid").asInt();
            int locale = node.get("locale").asInt();
            int gender = node.get("gender").asInt();
            
            User user = userDAO.loadAllUserData(username);
            user.setYear(year);
            user.setMonth(month);
            user.setDay(day);
            user.setUsername(username);
            user.setNick(nick);
            user.setName(name);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setCountry_id(country_id);
            user.setInstitution_id(institution_id);
            user.setLid(lid);
            user.setLocale(locale);
            user.setGender(gender);            
                    
            user.setUid(userDAO.integer("select.uid.by.username", username));
            user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
            
            
       
            boolean is_team = !userDAO.bool("is.user", user.getUsername());            
            if(is_team)
                return new ResponseEntity<>(ErrorUtils.BAD_USER,HttpStatus.BAD_REQUEST);        
            
            user.setTeam(false);
            
            String errors = ValidateUser(user);
            if(!errors.equals("0"))
                return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
            
            if(!ValidateInstitutionID(country_id, institution_id))
                return new ResponseEntity<>(ErrorUtils.BAD_INSTITUTION_ID, HttpStatus.BAD_REQUEST);

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
    
    
    
    
    private int ValidateApiAndToken(String bodyjson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

        if (!node.has("apikey") || !node.has("token")) {
            return 8;
        }

        String apikey = node.get("apikey").textValue();
        String token = node.get("token").textValue();

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
