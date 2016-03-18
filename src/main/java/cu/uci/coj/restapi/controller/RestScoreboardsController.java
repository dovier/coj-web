/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.User;
import cu.uci.coj.restapi.templates.CountryRest;
import cu.uci.coj.restapi.templates.InstitutionRest;
import cu.uci.coj.restapi.templates.UserRest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author lucy
 */

@Controller
@RequestMapping("/ranking")
public class RestScoreboardsController{
    
    @Resource
    private UserDAO userDAO;
    @Resource
    private InstitutionDAO institutionDAO;
    @Resource
    private CountryDAO countryDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private ContestDAO contestDAO;
    
    @RequestMapping(value = "/byuser", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<UserRest> getRankingByUser(Principal principal,
            @RequestParam(required = false, value = "pattern") String pattern, 
            @RequestParam(required = false, defaultValue = "false", value = "following") Boolean following, 
            @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online) {
        
        Integer uid = null;
        int found = userDAO.countEnabledUsersForScoreboard(pattern, online, uid);
        if(found>2000)
            found = 2000;
     
        List<User> listUsers = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<User> pages = userDAO.getUserRanking(pattern, found, online, uid, options);
            listUsers.addAll(pages.getList());
        }        
            
        List<UserRest> listUsersRest = new LinkedList();
        
        for(User u:listUsers){
            UserRest ur = new UserRest(u.getRank(), u.getCountry_desc(), u.getUsername(), u.getStatus(),u.getTotal(), u.getAccu(),u.getPercent(),u.getPoints());
            listUsersRest.add(ur);
        }
        
        return listUsersRest;
    }  
    
    @RequestMapping(value = "/byuser/show/{from}/{to}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingByUserFromTo(@PathVariable int from, @PathVariable int to) {
        if(from<1 || to<1 || from>to)
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        List<UserRest> listUserRest = getRankingByUser(null, null, Boolean.FALSE, Boolean.FALSE);
        if(from>listUserRest.size() || to>listUserRest.size())
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(listUserRest.subList(from-1, to), HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/byinstitution", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<InstitutionRest> getRankingByInstitucions(Principal principal,
            @RequestParam(required = false, value = "pattern") String pattern) {
        
      
        int found = institutionDAO.countEnabledInstitutions(pattern); 
     
        List<Institution> listInstitution = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<Institution> pages = institutionDAO.getEnabledInstitutions(pattern, found, options);
            listInstitution.addAll(pages.getList());
        }        
            
        List<InstitutionRest> listInstitucionRest = new LinkedList();

        for(Institution i:listInstitution){
            InstitutionRest ir = new InstitutionRest(i.getRank(), i.getCname(),i.getName(), i.getUsers(), i.getAcc(),i.getPoints());
            listInstitucionRest.add(ir);
        }
        
        return listInstitucionRest;
    }
    
    
    
    @RequestMapping(value = "/byinstitution/show/{from}/{to}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingByInstitutionFromTo(@PathVariable int from, @PathVariable int to) {
        if(from<1 || to<1 || from>to)
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        List<InstitutionRest> listInstitutionRest = getRankingByInstitucions(null, null);
        if(from>listInstitutionRest.size() || to>listInstitutionRest.size())
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(listInstitutionRest.subList(from-1, to), HttpStatus.OK);
    } 
    
    @RequestMapping(value = "/bycountry", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<CountryRest> getRankingByCountry(Principal principal,
            @RequestParam(required = false, value = "pattern") String pattern) {
        
        int found = countryDAO.countEnabledCountries(pattern);
     
        List<Country> listCountry = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<Country> pages = countryDAO.getEnabledCountries(pattern, found, options);
            listCountry.addAll(pages.getList());
        }        
            
        List<CountryRest> listCountryRest = new LinkedList();

        for(Country c:listCountry){
            CountryRest cr = new CountryRest(c.getRank(), c.getName(),c.getInstitutions(),c.getUsers(), c.getAcc(), c.getPoints());
            listCountryRest.add(cr);
        }
        
        return listCountryRest;
    }
    
    @RequestMapping(value = "/bycountry/show/{from}/{to}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public  ResponseEntity<?>  getRankingByCountryFromTo(@PathVariable int from, @PathVariable int to) {
        if(from<1 || to<1 || from>to)
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        List<CountryRest> listCountryRest = getRankingByCountry(null, null);
        if(from>listCountryRest.size() || to>listCountryRest.size())
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(listCountryRest.subList(from-1, to), HttpStatus.OK);
    }
    
    
    
    @RequestMapping(value = "/bycontest", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<UserRest> getRankingByContest(Principal principal,
            @RequestParam(required = false, value = "pattern") String pattern) {
        
        int found = contestDAO.countContestGeneralScoreboard(pattern);
     
        List<User> listUsers = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<User> pages = contestDAO.getContestGeneralScoreboard(found, pattern, options);
            listUsers.addAll(pages.getList());
        }        
            
        List<UserRest> listUsersRest = new LinkedList();
        
        for(User u:listUsers){
            UserRest ur = new UserRest(u.getRank(), u.getCountry_desc(), u.getUsername(), u.getStatus(),u.getTotal(), u.getAccu(),u.getPercent(),u.getPoints());
            listUsersRest.add(ur);
        }
        
        return listUsersRest;
    }
    
    @RequestMapping(value = "/bycontest/show/{from}/{to}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public  ResponseEntity<?>  getRankingBybycontestFromTo(@PathVariable int from, @PathVariable int to) {
        if(from<1 || to<1 || from>to)
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        List<UserRest> listUserRest = getRankingByContest(null, null);
        if(from>listUserRest.size() || to > listUserRest.size())
            return new ResponseEntity<>("bad from to", HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(listUserRest.subList(from-1, to), HttpStatus.OK);
    } 
    
    
    
  
    
    public int end(int found){
        if(found%30==0)
            return found/30;
        else
            return (found/30)+1;
    }
    
}
