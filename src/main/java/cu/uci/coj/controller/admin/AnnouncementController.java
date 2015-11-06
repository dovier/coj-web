package cu.uci.coj.controller.admin;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.AnnouncementDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.model.Announcement;
import cu.uci.coj.model.Contest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.annValidator;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cu.uci.coj.utils.Notification;



@Controller
@RequestMapping(value = "/admin")
public class AnnouncementController extends BaseController {

	@Resource
	private AnnouncementDAO announcementDAO;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private annValidator validator;

	@RequestMapping(value = "/listann.xhtml", method = RequestMethod.GET)
	public String listAnnouncements(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		model.addAttribute("pattern", pattern);
		return "/admin/listann";
	}

	@RequestMapping(value = "/tables/listann.xhtml", method = RequestMethod.GET)
	public String tablesListAnnouncements(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		IPaginatedList<Announcement> announcements = announcementDAO.loadAnnouncements(pattern, options);
		model.addAttribute("announcements", announcements);
		return "/admin/tables/listann";
	}

	@RequestMapping(value = "/deleteann.xhtml", method = RequestMethod.GET)
	public String deleteAnnouncement(Model model, Principal principal, @RequestParam("aid") Integer aid,   RedirectAttributes redirectAttributes) {
		announcementDAO.dml("delete.announcement", aid);
		// eventManager.publish(EventType.LOGGABLE_ACTION,
		// "msg:deleting announcement"+aid +";username:" +principal.getName());
                
                redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
		return "redirect:/admin/listann.xhtml";
	}

	@RequestMapping(value = "/addann.xhtml", method = RequestMethod.GET)
	public String addAnnouncement(Model model, Principal principal) {
		List<Contest> contests = new LinkedList<Contest>();
		contests.add(new Contest(0, "none"));
		contests.addAll(contestDAO.loadContest());
		model.addAttribute("contests", contests);
		Announcement announcement = new Announcement();
		announcement.setUsername(principal.getName());
		model.addAttribute(announcement);
		return "/admin/addann";
	}

	@RequestMapping(value = "/addann.xhtml", method = RequestMethod.POST)
	public String addAnnouncement(Model model, Principal principal, Announcement announcement, BindingResult result,   RedirectAttributes redirectAttributes) {
		validator.validate(announcement, result);
		if (result.hasErrors()) {
			List<Contest> contests = new LinkedList<Contest>();
			contests.add(new Contest(0, "none"));
			contests.addAll(contestDAO.loadContest());
			model.addAttribute("contests", contests);
			announcement.setUsername(principal.getName());
			model.addAttribute(announcement);
			return "/admin/addann";
		}
		announcementDAO.dml("add.announcement", announcement.getContent(), announcement.isEnabled(), principal.getName(), announcement.getContest());
		// eventManager.publish(EventType.LOGGABLE_ACTION,"msg:adding announcement;username:"
		// +principal.getName());
                            
                redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
		return "redirect:/admin/listann.xhtml";
	}

	@RequestMapping(value = "/manageann.xhtml", method = RequestMethod.GET)
	public String editAnnouncement(Model model, Principal principal, @RequestParam("aid") Integer aid) {
		List<Contest> contests = new LinkedList<Contest>();
		contests.add(new Contest(0, "none"));
		contests.addAll(contestDAO.loadContest());
		model.addAttribute("contests", contests);
		Announcement announcement = announcementDAO.object("find.announcement", Announcement.class, aid);
		announcement.setUsername(principal.getName());
		announcement.setAid(aid);
		model.addAttribute(announcement);
		return "/admin/manageann";
	}

	@RequestMapping(value = "/manageann.xhtml", method = RequestMethod.POST)
	public String editAnnouncement(Model model, Principal principal, Announcement announcement, BindingResult result,   RedirectAttributes redirectAttributes) {
		validator.validate(announcement, result);
		if (result.hasErrors()) {
			List<Contest> contests = new LinkedList<Contest>();
			contests.add(new Contest(0, "none"));
			contests.addAll(contestDAO.loadContest());
			model.addAttribute("contests", contests);
			announcement.setUsername(principal.getName());
			model.addAttribute(announcement);
			return "/admin/addann";
		}
		announcementDAO.dml("update.announcement", announcement.getContent(), announcement.isEnabled(), principal.getName(), announcement.getContest(), announcement.getAid());
		// eventManager.publish(EventType.LOGGABLE_ACTION,
		// "msg:adding announcement;username:" +principal.getName());
                redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullUpdate());
		return "redirect:/admin/listann.xhtml";
	}

}
