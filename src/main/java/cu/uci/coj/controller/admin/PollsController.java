package cu.uci.coj.controller.admin;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.pollValidator;
import cu.uci.coj.utils.Notification;

@Controller("AdminPollsController")
@RequestMapping(value = "/admin")
public class PollsController extends BaseController {

    private static final long serialVersionUID = -4816284094278968854L;
    @Resource
    private PollDAO pollDAO;
    @Resource
    private pollValidator pollValidator;

    @RequestMapping(value = "/poll/enable.xhtml", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void enableParsers(@RequestParam Integer pid) {
        pollDAO.toggleEnable(pid);
    }

    @RequestMapping(value = "/poll/list.xhtml", method = RequestMethod.GET)
    public String listAnnouncements(Model model, PagingOptions options) {
        model.addAttribute("polls", pollDAO.list());
        return "/admin/poll/list";
    }

    @RequestMapping(value = "/poll/delete.xhtml", method = RequestMethod.GET)
    public String delete(Model model, Principal principal,
            @RequestParam("pid") Integer pid, RedirectAttributes redirectAttributes) {
        pollDAO.delete(pid);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
        return "redirect:/admin/poll/list.xhtml";
    }

    @RequestMapping(value = "/poll/manage.xhtml", method = RequestMethod.GET)
	public String manage(Model model, Principal principal,
			@RequestParam(required = false) Integer pid) {

        if (pid == null) {
            model.addAttribute(new Poll());
        } else {
            model.addAttribute(pollDAO.get(pid));
        }
        return "/admin/poll/manage";
    }

    @RequestMapping(value = "/poll/manage.xhtml", method = RequestMethod.POST)
    public String manage(Model model, Principal principal, Poll poll,
            BindingResult result, RedirectAttributes redirectAttributes) {
        // aqui va el PollValidator, el que no hice porque no quise. Intentemos
        // no equivocarnos en esto.
        pollValidator.validate(poll, result);
        
        if (result.hasErrors()) {            
            model.addAttribute(poll);
            return "/admin/poll/manage";
        }

        if (poll.getPid() == null) {
            pollDAO.add(poll);
            redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
            
        } else {
            pollDAO.update(poll);
            redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullUpdate());
        }
        return "redirect:/admin/poll/list.xhtml";
    }

}
