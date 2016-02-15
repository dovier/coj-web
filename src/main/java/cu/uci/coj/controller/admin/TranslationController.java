package cu.uci.coj.controller.admin;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.displaytag.pagination.PaginatedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cu.uci.coj.utils.Notification;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Translation;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller("adminTranslationController")
@RequestMapping(value="/admin")
public class TranslationController extends BaseController {	
	
	@Resource
	ProblemDAO problemDAO;
	
	@RequestMapping(value="/managetranslations.xhtml", method=RequestMethod.GET)
	String manageTranslations(Model model) {
		return "/admin/managetranslations";
	}
	
	@RequestMapping(value="/tables/managetranslations.xhtml", method=RequestMethod.GET)
	String manageTranslationsTable(Model model, PagingOptions options, @RequestParam(required=false) String username, @RequestParam(required=false,defaultValue="0",value="pid") Integer pid,
			@RequestParam(required=false) String locale) {
		if (pid == 0) pid = null;
		IPaginatedList<Translation> translations = problemDAO.getTranslationList(username, pid, locale, options);	
		model.addAttribute("translations", translations);
		
		return "/admin/tables/managetranslations";
	}
	
	@RequestMapping(value="/managetranslations/check.xhtml", method=RequestMethod.GET)
	String checkTranslations(Model model, @RequestParam Integer id) {		
		model.addAttribute("translation", problemDAO.getTranslation(id));	
		
		List<String> locales = new ArrayList<String>();
		locales.add("en");
		locales.add("es");
		locales.add("pt");
		
		model.addAttribute("locales", locales);
		
		return "/admin/checktranslation";
	}
	
	@RequestMapping(value="/managetranslations/check.xhtml", method=RequestMethod.POST)
	String approveTranslations(Model model, Translation translation, Principal principal, BindingResult bindingResult, @RequestParam Integer id, RedirectAttributes redirectAttributes) {	
		problemDAO.approveTranslation(translation, getUsername(principal));
                redirectAttributes.addFlashAttribute("message", Notification.getAcceptedTranslate());
		return "redirect:/admin/managetranslations.xhtml";
	}	
	
	@RequestMapping(value="/managetranslations/delete.xhtml", method=RequestMethod.GET)
	String deleteTranslation(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
		problemDAO.deleteTranslation(id);
                redirectAttributes.addFlashAttribute("message", Notification.getRechazedTranslate());
		return "redirect:/admin/managetranslations.xhtml";
	}
}
