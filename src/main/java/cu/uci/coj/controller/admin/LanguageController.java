package cu.uci.coj.controller.admin;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.utils.enums.EventType;
import cu.uci.coj.validator.languageValidator;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String manageLanguages(Model model, @RequestParam("lid") Integer lid) {
        Language language = baseDAO.object("load.language", Language.class, lid);
        language.setLid(lid);
        model.addAttribute(language);
        return "/admin/managelanguage";
    }

    @RequestMapping(value = "/managelanguage.xhtml", method = RequestMethod.POST)
    public String manageLanguages(Model model, Principal principal, Language language, BindingResult result) {
        validator.validate(language, result);
        if (result.hasErrors()) {
            model.addAttribute(language);
            return "/admin/managelanguage";
        }
        baseDAO.dml("update.language",language.getName_bin(),language.isEnabled(),language.getDescripcion(),language.getLid());
   //     eventManager.publish(EventType.LOGGABLE_ACTION, "msg:editing language " + language.getName_bin() + "username:" +getUsername(principal));
        return "redirect:/admin/managelanguage.xhtml?lid="+ language.getLid();
    }
}
