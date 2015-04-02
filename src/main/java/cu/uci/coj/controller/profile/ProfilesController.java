package cu.uci.coj.controller.profile;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Institution;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/profile")
public class ProfilesController  extends BaseController {

    @Resource
    private CountryDAO countryDAO;
    @Resource
    private InstitutionDAO institutionDAO;

    @RequestMapping(value = "/country.xhtml", method = RequestMethod.GET)
    public String CountryProfile(Model model, HttpServletRequest request, @RequestParam("country_id") Integer country_id) {
        Country country = countryDAO.object("country.by.id", Country.class, country_id);
        country.setInstitutions(countryDAO.integer("count.institutions", country_id));
        country.setPoints(countryDAO.real("user.score", country_id));
        country.setRank(countryDAO.integer("country.rank", country_id));
        country.setUsers(countryDAO.integer("count.users", country_id));
        model.addAttribute("country", country);
        return "/profile/country";
    }

    @RequestMapping(value = "/institution.xhtml", method = RequestMethod.GET)
    public String InstitutionProfile(Model model, HttpServletRequest request, @RequestParam("inst_id") Integer inst_id) {
        Institution institution = institutionDAO.object("institution.id", Institution.class, inst_id);
        institution.setCount(institutionDAO.integer("institution.profile.count.users", inst_id));
        institution.setPoints(institutionDAO.real(0.0,"user.score.enabled.inst", inst_id));
        institution.setRank(institutionDAO.integer(-1,"insitutions.rank", inst_id));
        institution.setRankincountry(institutionDAO.integer(-1,"institution.rank.incountry" , institution.getCountry_id(),inst_id));
        model.addAttribute("institution", institution);
        model.addAttribute("country", countryDAO.object("country.by.id", Country.class, institution.getCountry_id()));
        return "/profile/institution";
    }
}
