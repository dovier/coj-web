package cu.uci.coj.controller.admin;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.utils.enums.EventType;
import cu.uci.coj.validator.languageValidator;
import cu.uci.coj.utils.Notification;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value = "/admin")
public class LanguageController  extends BaseController {

    @Resource
    private BaseDAO baseDAO;
    @Resource
    private languageValidator validator;

    @RequestMapping(value = "/programminglanguages.xhtml", method = RequestMethod.GET)
    public String listLanguages(Model model) {
        model.addAttribute("languages", baseDAO.objects("get.all.languages", Language.class));
        return "/admin/programminglanguages";
    }

    @RequestMapping(value = "/managelanguage.xhtml", method = RequestMethod.GET)
    public String manageLanguages(Model model, @RequestParam(value="lid",required=false) Integer lid) {
    	Language language = null;
    	if (lid == null)
    		language = new Language();
    	else {
         language = baseDAO.object("load.language", Language.class, lid);
    	}
        model.addAttribute(language);
        return "/admin/managelanguage";
    }

    @RequestMapping(value = "/managelanguage.xhtml", method = RequestMethod.POST)
    public String manageLanguages(Model model, Principal principal, Language language, BindingResult result, RedirectAttributes redirectAttributes) {
        validator.validate(language, result);
        if (result.hasErrors()) {
            model.addAttribute(language);
            return "/admin/managelanguage";
        }
        
        boolean update = false;
        
        if(language.getLid() > 1 ){
            update = true;
        }
        baseDAO.dml("upsert.language",language.getLanguage(),language.getKey(),language.getName_bin(),language.isEnabled(),language.getDescripcion(),language.getLid(),language.getLanguage(),language.getKey(),language.getName_bin(),language.isEnabled(),language.getDescripcion());
      
       redirectAttributes.addFlashAttribute("message",update ? Notification.getSuccesfullUpdate(): Notification.getSuccesfullCreate());
      
        
        return "redirect:/admin/programminglanguages.xhtml";
    }
}
