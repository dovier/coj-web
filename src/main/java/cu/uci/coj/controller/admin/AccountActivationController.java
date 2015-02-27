package cu.uci.coj.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.AccountActivationDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.AccountActivation;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
public class AccountActivationController extends BaseController {

	@Resource
	private MailNotificationService mailNotificationService;
	@Resource
	private AccountActivationDAO accountActivationDAO;
	
	@RequestMapping(value = "/admin/purgeactivations.xhtml", method = RequestMethod.GET)
	public String purge(Model model){
		accountActivationDAO.purgeOldActivations();
		return "/admin/manageactivations";
	}
	
	@RequestMapping(value = "/admin/resendactivations.xhtml", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public String resend(Model model, @RequestParam(required=false) Integer act_id){
		
		if (act_id == null)
		mailNotificationService.sendBulkAccountVerificationReminder();
		else{
			AccountActivation activation = baseDAO.object("select.unactivated.email", AccountActivation.class,act_id);
			mailNotificationService.sendAccountVerificationReminder(activation);
		}
		
		return "redirect:/admin/manageactivations.xhtml";
	}
	
	@RequestMapping(value = "/admin/manageactivations.xhtml", method = RequestMethod.GET)
	public String listCountries(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		return "/admin/manageactivations";
	}
	
	@RequestMapping(value = "/admin/tables/manageactivations.xhtml", method = RequestMethod.GET)
	public String tablesListCountries(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		IPaginatedList<AccountActivation> activations = null;
		if (StringUtils.isEmpty(pattern)) {
				activations = baseDAO.paginated("select.activations", AccountActivation.class,50,options);
		} else {
				activations = baseDAO.paginated("filtered.activations", AccountActivation.class, 50,options,"%" + pattern + "%");
		}
		if (activations.getFullListSize() != 0) {
			model.addAttribute("activations", activations);
			model.addAttribute("found", activations.getFullListSize());
		}
		return "/admin/tables/manageactivations";
	}

	@RequestMapping(value = "/admin/deleteactivation.xhtml", method = RequestMethod.GET)
	public String delete( @RequestParam("key") String key) {

		try {
			User user = baseDAO.object("select.user.activation.key", User.class, key);
			baseDAO.dml("delete.user.3", user.getUsername());
			baseDAO.dml("delete.user.2", user.getUid());
			baseDAO.dml("delete.user.1", user.getUid());
			baseDAO.dml("delete.user", user.getUid());
			baseDAO.dml("delete.activationrecord", user.getAct_id());
		} catch (EmptyResultDataAccessException e) {
		}
		return "redirect:/admin/manageactivations.xhtml";
	}

	@RequestMapping(value = "/admin/accountactivation.xhtml", method = RequestMethod.GET)
	public String accountActivation(HttpServletRequest request, Model model, @RequestParam("key") String key) {
		boolean ok = false;
		try {
			User user = baseDAO.object("select.user.activation.key", User.class, key);
			baseDAO.dml("enable.user", true, user.getUsername());
			baseDAO.dml("delete.activationrecord", user.getAct_id());
			ok = true;
		} catch (EmptyResultDataAccessException e) {
		}
		model.addAttribute("ok", ok);
		return "redirect:/admin/manageactivations.xhtml";
	}
}
