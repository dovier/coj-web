/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.User;
import cu.uci.coj.restapi.templates.TokenRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.validator.forgottenValidator;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.hibernate.validator.constraints.impl.EmailValidator;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cesar
 */
@Controller
@RequestMapping("/private")
public class RestPrivateController {
    
    @Resource
    protected JdbcTemplate jdbcTemplate;
    @Resource
    private forgottenValidator forgottenValidator;
    @Resource
    private UserDAO userDAO;
    @Resource
    protected MessageSource messageSource;
    @Resource
    private MailNotificationService mailNotificationService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> CreateToken(@RequestBody String bodyjson){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApi(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            if(!TokenUtils.ValidatePropertiesinJson(node,"username","password"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);
            
            String username = node.get("username").textValue();
            String password = node.get("password").textValue();
            
            String sql = "SELECT * FROM public.users WHERE username = ?";
            
            try{
                User user =  (User) jdbcTemplate.queryForObject(sql,new Object[]{username},new BeanPropertyRowMapper(User.class));
                PasswordEncoder encoder = new Md5PasswordEncoder();
                password = encoder.encodePassword(password,"ABC123XYZ789");

                if(user.getPassword().equals(password)){
                    TokenRest token = new TokenRest(TokenUtils.CreateTokenUser(username), TokenUtils.expirityToken);
                    return  new ResponseEntity<>(token, HttpStatus.OK);
            }
            else
                return  new ResponseEntity<>(ErrorUtils.BAD_USERNAME_PASSWORD, HttpStatus.UNAUTHORIZED);
            
            
            }catch(Exception e){
                return  new ResponseEntity<>(ErrorUtils.BAD_USERNAME_PASSWORD, HttpStatus.UNAUTHORIZED);
            }
           

        } catch (IOException ex) {
           return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    @RequestMapping(value = "/forgottenpassword", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> ForgottenPassword(@RequestBody String bodyjson, java.util.Locale locale){
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            if(!TokenUtils.ValidatePropertiesinJson(node,"email"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);
            
            String username = null;
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            String email = node.get("email").asText();
            String passcode;
            
            if (!ValidateEmail(email)) 
                return new ResponseEntity<>(ErrorUtils.INVALID_EMAIL,HttpStatus.BAD_REQUEST);
            
            try {
                passcode = generateRandomPassword(30);
                userDAO.dml("update.passcode", passcode, email);
            } catch (Exception e) {
                return new ResponseEntity<>(ErrorUtils.ERROR_UPDATE_CODE,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String subject = messageSource.getMessage("mail.pass.recover.subject", new Object[0], java.util.Locale.ENGLISH);
            String msg = messageSource.getMessage("forgotten.password", new Object[]{passcode}, locale);
            try {
                mailNotificationService.sendForgottenPasswordEmail(email, subject, msg);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(ErrorUtils.FAILED_SEND_EMAIL,HttpStatus.INTERNAL_SERVER_ERROR);
            }
            

        } catch (IOException ex) {
           return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    private boolean ValidateEmail(String email){
        EmailValidator a = new EmailValidator();
        if (!a.isValid(email, null))
            return false; 
        
        try {
            boolean emailExist = userDAO.bool("exist.user.bymail", email);
            if (!emailExist) 
                return false;
                    
        } catch (Exception e) {return false;}
        
        return true;
    }
   
    private int ValidateApi(String bodyjson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

        if (!node.has("apikey")) {
            return 8;
        }
        
        String apikey = node.get("apikey").textValue();

        try {
            int error = TokenUtils.ValidateAPIKey(apikey);
            if (error > 0) {
                return error;
            }          
        } catch (Exception e) {
            return 9;
        }

        return 0;
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
    
    
    private String generateRandomPassword(int length) {
        String password = "";
        for (int i = 0; i < length; i++) {
            Random r = new Random();
            password += (char) ('A' + r.nextInt(26));
        }
        password = password.toLowerCase();
        return password;
    }

    
    
}
