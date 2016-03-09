package cu.uci.coj.controller.admin;

import cu.uci.coj.config.Config;
import java.security.Principal;

import javax.annotation.Resource;

import cu.uci.coj.dao.impl.InstitutionDAOImpl;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Institution;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.institutionValidator;
import cu.uci.coj.utils.Notification;

@Controller
@RequestMapping(value = "/admin")
public class InstitutionController  extends BaseController {

    @Resource
    private InstitutionDAO institutionDAO;
    @Resource
    private institutionValidator validator;

    @RequestMapping(value = "/manageinstitutions.xhtml", method = RequestMethod.GET)
    public String listInstitutions(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
        model.addAttribute("pattern", pattern);
        return "/admin/manageinstitutions";
    }
    
    @RequestMapping(value = "/tables/manageinstitutions.xhtml", method = RequestMethod.GET)
    public String tablesListInstitutions(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
            IPaginatedList<Institution> institutions = institutionDAO.getAllInstitutions(pattern, options);
            model.addAttribute("institutions", institutions);
        return "/admin/tables/manageinstitutions";
    }

    @RequestMapping(value = "/addinstitution.xhtml", method = RequestMethod.GET)
    public String addInstitution(Model model) {
        model.addAttribute("countries", institutionDAO.objects("enabled.countries", Country.class));
        model.addAttribute(new Institution());
        return "/admin/addinstitution";
    }

    @RequestMapping(value = "/addinstitution.xhtml", method = RequestMethod.POST)
    public String addInstitution(Model model, Principal principal, Institution institution, BindingResult result, @RequestParam(value="logo",required=false) MultipartFile logo, RedirectAttributes redirectAttributes) {
        validator.validate(institution, result);
        if (result.hasErrors()) {
            model.addAttribute("countries", institutionDAO.objects("enabled.countries", Country.class));
            model.addAttribute(institution);
            return "/admin/addinstitution";
        }
        institutionDAO.insertInstitution(institution);
        institutionDAO.dml("insert.log", "inserting institution " + institution.getName(), getUsername(principal));
        
        //TO SAVE THE INSTITUTION LOGO
        
        if (logo!= null && !logo.isEmpty())
			FileUtils.saveToFile(logo, Config.getProperty("base.upload.dir.logo"), institution.getZip() + ".png");
        
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
        return "redirect:/admin/manageinstitutions.xhtml";
    }

    @RequestMapping(value = "/manageinstitution.xhtml", method = RequestMethod.GET)
    public String manageInstitution(Model model, @RequestParam("inst_id") Integer inst_id) {
        model.addAttribute("countries", institutionDAO.objects("enabled.countries", Country.class));
        model.addAttribute(institutionDAO.object("institution.id", Institution.class, inst_id));
        return "/admin/manageinstitution";
    }

    @RequestMapping(value = "/manageinstitution.xhtml", method = RequestMethod.POST)
    public String manageInstitution(Model model, Principal principal, Institution institution, BindingResult result) {
        validator.validateUpdate(institution, result);
        if (result.hasErrors()) {
            model.addAttribute("countries", institutionDAO.objects("enabled.countries", Country.class));
            model.addAttribute(institution);
            return "/admin/manageinstitution";
        }
        institutionDAO.updateInstitution(institution);
        institutionDAO.dml("insert.log", "inserting institution " + institution.getName(), getUsername(principal));
        return "redirect:/admin/manageinstitutions.xhtml?pattern="+institution.getName();
    }
}
