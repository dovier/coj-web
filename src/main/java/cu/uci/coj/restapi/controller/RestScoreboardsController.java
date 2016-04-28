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
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.User;
import cu.uci.coj.restapi.templates.CountryDescriptionRest;
import cu.uci.coj.restapi.templates.CountryRest;
import cu.uci.coj.restapi.templates.InstitutionDescriptionRest;
import cu.uci.coj.restapi.templates.InstitutionRest;
import cu.uci.coj.restapi.templates.UserRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
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
    
    
    @ApiOperation(value = "Obtener tabla de posiciones por usuarios",  
            notes = "Devuelve las posiciones de todos los usuarios del COJ.",
            response = UserRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método") })
    @RequestMapping(value = "/byuser", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<UserRest> getRankingByUser(
            @ApiParam(value = "Filtrar por nombre de usuario") @RequestParam(required = false, value = "pattern") String pattern, 
            @ApiParam(value = "Filtrar por seguidos") @RequestParam(required = false, defaultValue = "false", value = "following") Boolean following, 
            @ApiParam(value = "Filtrar por conectados") @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online) {
        
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
    
    @ApiOperation(value = "Obtener tabla de posiciones por instituciones",  
            notes = "Devuelve las posiciones de todos las instituciones registradas en el COJ.",
            response = InstitutionRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método") })
    @RequestMapping(value = "/byinstitution", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<InstitutionRest> getRankingByInstitucions(
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
            InstitutionRest ir = new InstitutionRest(i.getId(),i.getRank(), i.getCname(),i.getName(), i.getUsers(), i.getAcc(),i.getPoints());
            listInstitucionRest.add(ir);
        }
        
        return listInstitucionRest;
    }
    
    
    @ApiOperation(value = "Obtener tabla de posiciones por países",  
            notes = "Devuelve las posiciones de todos los países registrados en el COJ.",
            response = CountryRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método") })
    @RequestMapping(value = "/bycountry", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<CountryRest> getRankingByCountry(@RequestParam(required = false, value = "pattern") String pattern) {
        
        int found = countryDAO.countEnabledCountries(pattern);
     
        List<Country> listCountry = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<Country> pages = countryDAO.getEnabledCountries(pattern, found, options);
            listCountry.addAll(pages.getList());
        }        
            
        List<CountryRest> listCountryRest = new LinkedList();

        for(Country c:listCountry){
            CountryRest cr = new CountryRest(c.getId(),c.getRank(), c.getName(),c.getInstitutions(),c.getUsers(), c.getAcc(), c.getPoints());
            listCountryRest.add(cr);
        }
        
        return listCountryRest;
    }
    
    @ApiOperation(value = "Obtener tabla de posiciones de las instituciones por país",  
            notes = "Dado el identificador de un país, devuelve las posiciones de todas sus instituciones registradas en el COJ.",
            response = InstitutionRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad country id") })
    @RequestMapping(value = "/institutionbycountry/{country_id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingInstitutionByCountry(
            @ApiParam(value = "Identificador del pais") @PathVariable int country_id) {
        
        Country c = countryDAO.object("country.by.id", Country.class, country_id);
        if(c == null)
            return new ResponseEntity<>(ErrorUtils.BAD_COUNTRY_ID, HttpStatus.NOT_FOUND);
        
        int found = institutionDAO.countEnabledInstitutionsByCountry("", country_id);
     
        List<Institution> listInstitution = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<Institution> pages = institutionDAO.getEnabledInstitutionsByCountry("", found, options, country_id);
            listInstitution.addAll(pages.getList());
        }        
            
        List<InstitutionRest> listInstitucionRest = new LinkedList();

        for(Institution i:listInstitution){
            InstitutionRest ir = new InstitutionRest(i.getId(),i.getRank(), i.getCname(),i.getName(), i.getUsers(), i.getAcc(),i.getPoints());
            listInstitucionRest.add(ir);
        }
        
        return new ResponseEntity<>(listInstitucionRest, HttpStatus.OK);
    }
    
    @ApiOperation(value = "Obtener tabla de posiciones de los usuarios por país",  
            notes = "Dado el identificador de un país, devuelve las posiciones de todos sus usuarios registrados en el COJ.",
            response = InstitutionRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad country id") })
    @RequestMapping(value = "/usersbycountry/{country_id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingUsersByCountry(@PathVariable int country_id) {
        
        Country c = countryDAO.object("country.by.id", Country.class, country_id);
        if(c == null)
            return new ResponseEntity<>(ErrorUtils.BAD_COUNTRY_ID, HttpStatus.BAD_REQUEST);

        int found = userDAO.countEnabledUsersByCountry("",false, country_id);

        if(found>2000)
            found = 2000;

        List<User> listUsers = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<User> pages = userDAO.getCountryUsersRanking("", found, false, options, country_id);
            listUsers.addAll(pages.getList());
        }        

        List<UserRest> listUsersRest = new LinkedList();
        
        for(User u:listUsers){
            UserRest ur = new UserRest(u.getRank(), u.getCountry_desc(), u.getUsername(), u.getStatus(),u.getTotal(), u.getAccu(),u.getPercent(),u.getPoints());
            listUsersRest.add(ur);
        }

        return new ResponseEntity<>(listUsersRest, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/usersbyinstitution/{inst_id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingUsersByInstitution(@PathVariable int inst_id) {
        
        Institution ins = institutionDAO.object("institution.id", Institution.class, inst_id);
        if(ins == null)
            return new ResponseEntity<>(ErrorUtils.BAD_INSTITUTION_ID, HttpStatus.BAD_REQUEST);
  
        int found = userDAO.countEnabledUsersByInstitutions("", false, inst_id);

        if(found>2000)
            found = 2000;

        List<User> listUsers = new LinkedList();   
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<User> pages = userDAO.getInstitutionUsersRanking("", found, false, options, inst_id);
            listUsers.addAll(pages.getList());
        }        

        List<UserRest> listUsersRest = new LinkedList();
        
        for(User u:listUsers){
            UserRest ur = new UserRest(u.getRank(), u.getCountry_desc(), u.getUsername(), u.getStatus(),u.getTotal(), u.getAccu(),u.getPercent(),u.getPoints());
            listUsersRest.add(ur);
        }

        return new ResponseEntity<>(listUsersRest, HttpStatus.OK);
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
    
    @RequestMapping(value = "/description/institution/{inst_id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getDescriptionInstitutionByInstID(@PathVariable int inst_id) {
        
        Institution i = institutionDAO.object("institution.id", Institution.class, inst_id);
        if(i == null)
            return new ResponseEntity<>(ErrorUtils.BAD_INSTITUTION_ID, HttpStatus.BAD_REQUEST);
        
        i.setCount(institutionDAO.integer("institution.profile.count.users", inst_id));
        i.setPoints(institutionDAO.real(0.0,"user.score.enabled.inst", inst_id));
        i.setRank(institutionDAO.integer(-1,"insitutions.rank", inst_id));
        i.setRankincountry(institutionDAO.integer(-1,"institution.rank.incountry" , i.getCountry_id(),inst_id));
        Country c  = countryDAO.object("country.by.id", Country.class, i.getCountry_id());      
        
        String logo = "http://coj.uci.cu/images/school/"+i.getZip()+".png";
        InstitutionDescriptionRest idescrip = new InstitutionDescriptionRest(i.getName(),i.getZip(), logo, i.getWebsite(),c.getName(), i.getCount(), i.getPoints(),i.getRank(), i.getRankincountry());
        return new ResponseEntity<>(idescrip, HttpStatus.OK);
    }    
    
    @RequestMapping(value = "/description/country/{country_id}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getDescriptionCountryByCountryID(@PathVariable int country_id) {
        
        Country c = countryDAO.object("country.by.id", Country.class, country_id);
        if(c == null)
            return new ResponseEntity<>(ErrorUtils.BAD_COUNTRY_ID, HttpStatus.BAD_REQUEST);
        
        c.setInstitutions(countryDAO.integer("count.institutions", country_id));
        c.setPoints(countryDAO.real("user.score", country_id));
        c.setRank(countryDAO.integer("country.rank", country_id));
        c.setUsers(countryDAO.integer("count.users", country_id));   
                
        String flag = "http://coj.uci.cu/images/countries/"+c.getZip()+".png";
        CountryDescriptionRest cdescrip = new CountryDescriptionRest(c.getName(),c.getZip_two(), c.getZip(), flag, c.getWebsite(), c.getInstitutions(), c.getUsers(), c.getPoints(), c.getRank());
        return new ResponseEntity<>(cdescrip, HttpStatus.OK);
    }
    
    
    
    
    @RequestMapping(value = "/byuser/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingByUserFromTo(@PathVariable int page) {       
        Integer uid = null;
        int found = userDAO.countEnabledUsersForScoreboard("", false, uid);
        
        if (page > 0 && page <= end(found)) {
            PagingOptions options = new PagingOptions(page);            
            IPaginatedList<User> pages = userDAO.getUserRanking("", found, false, uid, options);
            
            List<UserRest> listUsersRest = new LinkedList();
            for(User u:pages.getList()){
                UserRest ur = new UserRest(u.getRank(), u.getCountry_desc(), u.getUsername(), u.getStatus(),u.getTotal(), u.getAccu(),u.getPercent(),u.getPoints());
                listUsersRest.add(ur);
            }

            return new ResponseEntity<>(listUsersRest, HttpStatus.OK);
        }
        else 
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);        
    }     
    
    @RequestMapping(value = "/byinstitution/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getRankingByInstitutionFromTo(@PathVariable int page) {
       
        int found = institutionDAO.countEnabledInstitutions(""); 
        
        if (page > 0 && page <= end(found)) {     
            PagingOptions options = new PagingOptions(page);            
            IPaginatedList<Institution> pages = institutionDAO.getEnabledInstitutions("", found, options);
            
            List<InstitutionRest> listInstitucionRest = new LinkedList();
            for(Institution i:pages.getList()){
                InstitutionRest ir = new InstitutionRest(i.getId(),i.getRank(), i.getCname(),i.getName(), i.getUsers(), i.getAcc(),i.getPoints());
                listInstitucionRest.add(ir);
            }
            
            return new ResponseEntity<>(listInstitucionRest, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
   
    } 
    
    @RequestMapping(value = "/bycountry/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public  ResponseEntity<?>  getRankingByCountryFromTo(@PathVariable int page) {
        
        int found = countryDAO.countEnabledCountries("");         
        if (page > 0 && page <= end(found)) { 
            PagingOptions options = new PagingOptions(page);            
            IPaginatedList<Country> pages = countryDAO.getEnabledCountries("", found, options);
            
            List<CountryRest> listCountryRest = new LinkedList();
            for(Country c:pages.getList()){
                CountryRest cr = new CountryRest(c.getId(),c.getRank(), c.getName(),c.getInstitutions(),c.getUsers(), c.getAcc(), c.getPoints());
                listCountryRest.add(cr);
            }
            return new ResponseEntity<>(listCountryRest, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
    }
  
    @RequestMapping(value = "/bycontest/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public  ResponseEntity<?>  getRankingBybycontestFromTo(@PathVariable int page) {
        int found = contestDAO.countContestGeneralScoreboard("");         
        if (page > 0 && page <= end(found)) { 
            PagingOptions options = new PagingOptions(page);            
            IPaginatedList<User> pages = contestDAO.getContestGeneralScoreboard(found, "", options);
            
            List<UserRest> listUsersRest = new LinkedList();        
            for(User u:pages.getList()){
                UserRest ur = new UserRest(u.getRank(), u.getCountry_desc(), u.getUsername(), u.getStatus(),u.getTotal(), u.getAccu(),u.getPercent(),u.getPoints());
                listUsersRest.add(ur);
            }
            return new ResponseEntity<>(listUsersRest, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
    }
    
    private int end(int found){
        if(found%30==0)
            return found/30;
        else
            return (found/30)+1;
    }
    
}
