/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.uci.coj.model.User;
import cu.uci.coj.restapi.templates.TokenRest;
import cu.uci.coj.restapi.utils.TokenUtils;
import java.io.IOException;
import javax.annotation.Resource;
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
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> createToken(@RequestBody String bodyjson){
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
                return  new ResponseEntity<>("bad user or password", HttpStatus.UNAUTHORIZED);
            
            
            }catch(Exception e){
                return  new ResponseEntity<>("bad user or password", HttpStatus.UNAUTHORIZED);
            }
           

        } catch (IOException ex) {
           return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

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

    
    
}
