package cu.uci.coj.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.interfaces.CommonScoreboardInterface;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class ScoreboardsController  extends BaseController{

    @Resource
    private UserDAO userDAO;
    @Resource
    private InstitutionDAO institutionDAO;
    @Resource
    private CountryDAO countryDAO;
    @Resource
    private ProblemDAO problemDAO;
    

    @RequestMapping(value = "/24h/usersrank.xhtml", method = RequestMethod.GET)
    public String UsersRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern,@RequestParam(required = false, defaultValue = "false", value = "following") Boolean following, @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online) {
        model.addAttribute("pattern", pattern);
        model.addAttribute("following", following);
        model.addAttribute("logged", userDAO.integer("count.logged.users"));
        model.addAttribute("online", online);
        return "/24h/usersrank";
    }
    
    @RequestMapping(value = "/tables/usersrank.xhtml", method = RequestMethod.GET)
    public String tablesUsersRank(Model model, Principal principal,PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "false", value = "following") Boolean following, @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online) {
    	Integer uid = null;
    	if (following)
    		uid = getUid(principal);
        int found = userDAO.countEnabledUsersForScoreboard(pattern, online,uid);
        if (found != 0) {
            IPaginatedList<User> users = userDAO.getUserRanking(pattern, found,online,uid, options);
            if (options.getPage() == 1) {
                assigMedals(users.getList());
            }
            model.addAttribute("users", users);
        }
        return "/tables/usersrank";
    }
    
   

    @RequestMapping(value = "/24h/usersinstitutionrank.xhtml", method = RequestMethod.GET)
    public String UsersInstitutionRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online, @RequestParam("inst_id") Integer inst_id) {
        model.addAttribute("inst_id",inst_id);
        model.addAttribute("pattern", pattern);        
        model.addAttribute("institution", institutionDAO.object("institution.id", Institution.class, inst_id));
        model.addAttribute("logged", userDAO.integer("count.logged.users.byinstitution",inst_id));
        model.addAttribute("online", online);
        return "/24h/usersinstitutionrank";
    }
    
    @RequestMapping(value = "/tables/usersinstitutionrank.xhtml", method = RequestMethod.GET)
    public String tablesUsersInstitutionRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online, @RequestParam("inst_id") Integer inst_id) {
        int found = userDAO.countEnabledUsersByInstitutions(pattern, online, inst_id);
        if (found != 0) {
            IPaginatedList<User> users = userDAO.getInstitutionUsersRanking(pattern, found,online,options,inst_id);
            if (options.getPage() == 1) {
                assigMedals(users.getList());
            }
            model.addAttribute("users", users);
        }
        return "/tables/usersinstitutionrank";
    }
    
    @RequestMapping(value = "/24h/userscountryrank.xhtml", method = RequestMethod.GET)
    public String UsersCountryRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online, @RequestParam("country_id") Integer country_id) {
        model.addAttribute("country_id",country_id);
        model.addAttribute("pattern", pattern);        
        model.addAttribute("country", countryDAO.object("country.by.id", Country.class,country_id));
        model.addAttribute("logged", userDAO.integer("count.logged.users.bycountry",country_id));
        model.addAttribute("online", online);
        return "/24h/userscountryrank";
    }
    
    @RequestMapping(value = "/tables/userscountryrank.xhtml", method = RequestMethod.GET)
    public String tablesUsersCountryRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern, @RequestParam(required = false, defaultValue = "false", value = "online") Boolean online, @RequestParam("country_id") Integer country_id) {
        int found = userDAO.countEnabledUsersByCountry(pattern, online, country_id);
        if (found != 0) {
            IPaginatedList<User> users = userDAO.getCountryUsersRanking(pattern, found,online, options,country_id);
            if (options.getPage() == 1) {
                assigMedals(users.getList());
            }
            
            model.addAttribute("users", users);
        }
        return "/tables/userscountryrank";
    }
    
    @RequestMapping(value = "/24h/countriesrank.xhtml", method = RequestMethod.GET)
    public String CountriesRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {        
        return "/24h/countriesrank";
    }
    
    @RequestMapping(value = "/tables/countriesrank.xhtml", method = RequestMethod.GET)
    public String tableCountriesRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {        
        int found = countryDAO.countEnabledCountries(pattern);        
        if (found != 0) {
            IPaginatedList<Country> countries = countryDAO.getEnabledCountries(pattern, found,options);
            if (options.getPage() == 1) {
                assigMedals(countries.getList());
            }
            
            model.addAttribute("countries", countries);
        }        
        return "/tables/countriesrank";
    }
    
    @RequestMapping(value = "/24h/institutionsrank.xhtml", method = RequestMethod.GET)
    public String InstitutionsRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {        
        return "/24h/institutionsrank";
    }
    
    @RequestMapping(value = "/tables/institutionsrank.xhtml", method = RequestMethod.GET)
    public String tableInstitutionsRank(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {        
        model.addAttribute("pattern", pattern);                
        int found = institutionDAO.countEnabledInstitutions(pattern);        
        if (found != 0) {
            IPaginatedList<Institution> institutions = institutionDAO.getEnabledInstitutions(pattern, found, options);
            if (options.getPage() == 1) {
                assigMedals(institutions.getList());
            }
            
            model.addAttribute("institutions", institutions);
        }        
        return "/tables/institutionsrank";
    }
    
    @RequestMapping(value = "/24h/institutionsrankbycountry.xhtml", method = RequestMethod.GET)
    public String InstitutionsRankByCountry(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern,@RequestParam("country_id") Integer country_id) {        
        model.addAttribute("pattern", pattern);                
        model.addAttribute("country_id", country_id);
        model.addAttribute("country", countryDAO.object("country.by.id", Country.class,country_id));
        return "/24h/institutionsrankbycountry";
    }
    @RequestMapping(value = "/tables/institutionsrankbycountry.xhtml", method = RequestMethod.GET)
    public String tableInstitutionsRankByCountry(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern,@RequestParam("country_id") Integer country_id) {        
        int found = institutionDAO.countEnabledInstitutionsByCountry(pattern,country_id);        
        if (found != 0) {
            IPaginatedList<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry(pattern, found,options,country_id);
            if (options.getPage() == 1) {
                assigMedals(institutions.getList());
            }
            
            model.addAttribute("institutions", institutions);
        }        
        return "/tables/institutionsrankbycountry";
    }
    
    private <T> void assigMedals(List<T> medals) {
        for (int i = 0; i < 3 && i < medals.size(); i++) {
            if (((CommonScoreboardInterface)medals.get(i)).getPoints() == 0) {
                break;
            }
            switch (i) {
                case 0: {
                    ((CommonScoreboardInterface)medals.get(i)).setMedal(User.GOLD);
                    break;
                }
                case 1: {
                    ((CommonScoreboardInterface)medals.get(i)).setMedal(User.SILVER);
                    break;
                }
                case 2: {
                    ((CommonScoreboardInterface)medals.get(i)).setMedal(User.BRONZE);
                    break;
                }
            }
        }        
    }
    
}
