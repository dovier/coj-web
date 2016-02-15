package cu.uci.coj.controller;

import java.security.Principal;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cu.uci.coj.utils.Notification;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.Translation;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
*
* @author Eddy Roberto Morales Perez
*/

@Controller("translationController")
@RequestMapping(value = "/24h")
public class TranslationController extends BaseController {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5888832307164147894L;
	@Resource
	ProblemDAO problemDAO;
	
	@RequestMapping(value = "/translation.xhtml", method = RequestMethod.GET)
	public String manageProblemI18N(HttpServletRequest request, Model model, @RequestParam("pid") Integer pid) {
            Problem problem = null;
            if (request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole(Roles.ROLE_SUPER_PSETTER) || request.isUserInRole(Roles.ROLE_PSETTER)) {
		 problem = problemDAO.object("select.problem.i18n.1", Problem.class, pid);                
            }else{
		 problem = problemDAO.object("select.problem.i18n.2", Problem.class, pid);                                
            }
		
		Translation translation = new Translation();
		
		model.addAttribute("problem", problem);
		model.addAttribute(translation);              
                if (problem == null ){
                    return "redirect:/error/error.xhtml?error=2";
                }
		return "/24h/translation";
	}

	@RequestMapping(value = "/translation.xhtml", method = RequestMethod.POST)
	public String manageProblemI18N(Principal principal, Model model, Translation translation, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		problemDAO.insertTranslation(getUsername(principal), translation);
		redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullTranslate());
		return "redirect:/24h/problems.xhtml";
	}
}
