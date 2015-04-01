package cu.uci.coj.controller.admin;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
@RequestMapping(value = "/admin")
public class ClassificationController extends BaseController {

	@Resource
	private ProblemDAO problemDAO;

	@RequestMapping(value = "/manageclassifications.xhtml", method = RequestMethod.GET)
	public String allClassifications(Model model, @RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, PagingOptions options) {
		return "/admin/manageclassifications";
	}
	
	@RequestMapping(value = "/tables/manageclassifications.xhtml", method = RequestMethod.GET)
	public String tablesAllClassifications(Model model, @RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, PagingOptions options) {
		IPaginatedList<ProblemClassification> classifications = problemDAO.getClassifications(options);

		model.addAttribute("classifications", classifications);

		return "/admin/tables/manageclassifications";
	}

	@RequestMapping(value = "/addclassifications.xhtml", method = RequestMethod.POST)
	public String addClassifications(Model model, @RequestParam(required = false, value = "username") String filter_user, @RequestParam(required = false, value = "pid") Integer pid,
			@RequestParam(required = false, value = "status") String status, @RequestParam(required = false, value = "planguage") String language, PagingOptions options,
			@RequestParam(required = true, value = "name") String name) {
		problemDAO.insertClassification(name);
		IPaginatedList<ProblemClassification> classifications = problemDAO.getClassifications(options);

		model.addAttribute("classifications", classifications);

		return "redirect:/admin/manageclassifications.xhtml";
	}

	@RequestMapping(value = "/updateclassifications.xhtml", method = RequestMethod.GET)
	public String updateClassifications(Model model, @RequestParam(required = true, value = "classid") Integer classid, @RequestParam(required = true, value = "name") String name) {
		problemDAO.updateClassification(classid, name);

		return "/admin/manageclassifications";
	}

	@RequestMapping(value = "/deleteclassifications.xhtml", method = RequestMethod.GET)
	public String deleteClassifications(Model model, @RequestParam(required = true, value = "classid") Integer classid) {
		problemDAO.deleteClassification(classid);

		return "/admin/manageclassifications";
	}

	@RequestMapping(value = "/manageproblemclassification.xhtml", method = RequestMethod.GET)
	public String manageProblemClassification(Model model, Locale locale, @RequestParam(required = false, value = "add") String add, @RequestParam(required = false, value = "nc") String nc,
			@RequestParam(required = false, value = "rmb") String rmb, @RequestParam(required = false, value = "pid") Integer pid, @RequestParam(required = false, value = "cid") Integer cid,
			PagingOptions options) {
		if (add != null) {
			problemDAO.insertProblemClassification(pid, cid);
		} else if (rmb != null) {
			problemDAO.deleteProblemClassification(pid, cid);
		} else {
			if (nc != null) {
				try {
					problemDAO.insertClassification(nc);
				} catch (DataIntegrityViolationException error) {
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("alreadyExistsError", "errormsg.problemclass.alreadyexists");
					model.addAttribute("errors", errors);
				}
			}
			List<ProblemClassification> classifications = problemDAO.getClassifications();
			if (pid != null) {
				List<ProblemClassification> problemClassifications = problemDAO.getProblemClassifications(pid);
				for (Iterator<ProblemClassification> it = problemClassifications.iterator(); it.hasNext();) {
					ProblemClassification problemClassification = it.next();
					for (ProblemClassification classification : classifications) {
						if (classification.getIdClassification() == problemClassification.getIdClassification()) {
							classifications.remove(classification);
							break;
						}
					}
				}
				model.addAttribute("problemClassifications", problemClassifications);
			}
			model.addAttribute("classifications", classifications);
		}

		model.addAttribute("problems", problemDAO.findAllProblemsWithoutClassification(locale.getLanguage(), options));
		return "/admin/manageproblemclassification";
	}

	@RequestMapping(value = "/tables/manageproblemclassification.xhtml", method = RequestMethod.GET)
	public String tablesManageProblemClassification(Model model, Locale locale, @RequestParam(required = false, value = "add") String add, @RequestParam(required = false, value = "nc") String nc,
			@RequestParam(required = false, value = "rmb") String rmb, @RequestParam(required = false, value = "pid") Integer pid, @RequestParam(required = false, value = "cid") Integer cid,
			PagingOptions options) {
		List<ProblemClassification> classifications = problemDAO.getClassifications();
		if (pid != null) {
			List<ProblemClassification> problemClassifications = problemDAO.getProblemClassifications(pid);
			for (Iterator<ProblemClassification> it = problemClassifications.iterator(); it.hasNext();) {
				ProblemClassification problemClassification = it.next();
				for (ProblemClassification classification : classifications) {
					if (classification.getIdClassification() == problemClassification.getIdClassification()) {
						classifications.remove(classification);
						break;
					}
				}
			}
			model.addAttribute("problemClassifications", problemClassifications);
		}
		model.addAttribute("classifications", classifications);

		model.addAttribute("problems", problemDAO.findAllProblemsWithoutClassification(locale.getLanguage(), options));
		return "/admin/tables/manageproblemclassification";
	}

	@RequestMapping(value = "/manageproblemclassification.xhtml", method = RequestMethod.POST)
	public String manageProblemClassification1(Model model, @RequestParam(required = false, value = "add") String add, @RequestParam(required = false, value = "nc") String nc,
			@RequestParam(required = false, value = "rmb") String rmb, @RequestParam(required = false, value = "pid") Integer pid, @RequestParam(required = false, value = "cid") Integer cid,
			@RequestParam(required = false, value = "cpx") Integer complexity) {
		if (add != null) {
			problemDAO.insertProblemClassification(pid, cid, complexity);
		} else if (rmb != null) {
			problemDAO.deleteProblemClassification(pid, cid);
		} else {
			if (nc != null) {
				try {
					problemDAO.insertClassification(nc);
				} catch (DataIntegrityViolationException error) {
					Map<String, String> errors = new HashMap<String, String>();
					errors.put("alreadyExistsError", "errormsg.problemclass.alreadyexists");
					model.addAttribute("errors", errors);
				}
			}
			List<ProblemClassification> classifications = problemDAO.getClassifications();
			if (pid != null) {
				List<ProblemClassification> problemClassifications = problemDAO.getProblemClassifications(pid);
				for (Iterator<ProblemClassification> it = problemClassifications.iterator(); it.hasNext();) {
					ProblemClassification problemClassification = it.next();
					for (Iterator<ProblemClassification> it1 = classifications.iterator(); it1.hasNext();) {
						ProblemClassification classification = it1.next();
						if (classification.getIdClassification() == problemClassification.getIdClassification()) {
							classifications.remove(classification);
							break;
						}
					}
				}
				model.addAttribute("problemClassifications", problemClassifications);
			}
			model.addAttribute("classifications", classifications);
		}
		return "/admin/manageproblemclassification";
	}

}
