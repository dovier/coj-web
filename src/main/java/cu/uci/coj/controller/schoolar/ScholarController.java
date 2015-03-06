package cu.uci.coj.controller.schoolar;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.CourseDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Chapter;
import cu.uci.coj.model.ChapterContent;
import cu.uci.coj.model.Course;
import cu.uci.coj.model.Filter;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Log;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.CourseValidator;
import cu.uci.coj.validator.submitValidator;

@Controller
public class ScholarController extends BaseController {

	private static final long serialVersionUID = -3288807782629649781L;
	@Resource
	private UserDAO userDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	CourseDAO courseDAO;
	@Resource
	private CourseValidator courseValidator;
	@Resource
	private UtilDAO utilDAO;
	@Resource
	private submitValidator submitValidator;
	@Resource
	private SubmissionDAO submissionDAO;

	@Resource
	private Utils utils;

	@RequestMapping(value = "/schoolar/banuser.xhtml", method = RequestMethod.GET)
	public String banuser(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id, @RequestParam("username") String username) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		courseDAO.banUser(username, course_id);
		return "redirect:/schoolar/editcourseusers.xhtml?course_id=" + course_id;
	}

	@RequestMapping(value = "/schoolar/removecourseuser.xhtml", method = RequestMethod.GET)
	public String removeuser(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id, @RequestParam("username") String username) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		courseDAO.removeCourseUser(username, course_id);
		return "redirect:/schoolar/editcourseusers.xhtml?course_id=" + course_id;
	}

	@RequestMapping(value = "/schoolar/mycourses.xhtml", method = RequestMethod.GET)
	public String myCourses(Model model, HttpServletRequest request, Principal principal) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR"))) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		return "/schoolar/mycourses";
	}
	
	@RequestMapping(value = "/tables/mycourses.xhtml", method = RequestMethod.GET)
	public String tablesMyCourses(Model model, HttpServletRequest request, Principal principal) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR"))) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		model.addAttribute("courses", courseDAO.loadAdminCourses(principal.getName()));
		return "/tables/mycourses";
	}

	@RequestMapping(value = "/schoolar/createcourse.xhtml", method = RequestMethod.GET)
	public String createCourse(Model model, Principal principal) {
		model.addAttribute("course", new Course());
		return "/schoolar/createcourse";
	}

	@RequestMapping(value = "/schoolar/createcourse.xhtml", method = RequestMethod.POST)
	public String createCourseBuild(Model model, Course course, Principal principal, BindingResult result) {
		courseValidator.validate(course, result);
		if (result.hasErrors()) {
			model.addAttribute("course", course);
			return "/schoolar/createcourse";
		}
		course.setUsername(principal.getName());
		courseDAO.insertCourse(course);
		return "redirect:/schoolar/editcourse.xhtml?course_id=" + courseDAO.getLastCreatedCourse(principal.getName());
	}

	@RequestMapping(value = "/schoolar/editcourse.xhtml", method = RequestMethod.GET)
	public String editCourse(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course course = courseDAO.loadCourseEdit(course_id);
		model.addAttribute("course", course);
		return "/schoolar/editcourse";
	}

	@RequestMapping(value = "/schoolar/editcourse.xhtml", method = RequestMethod.POST)
	public String editCourse(Model model, Principal principal, Course course, BindingResult result) {
		courseValidator.validateEditCourse(course, result);
		model.addAttribute("course", course);
		if (result.hasErrors()) {
			return "/schoolar/editcourse";
		}
		courseDAO.saveCourse(course);
		return "/schoolar/editcourse";
	}

	@RequestMapping(value = "/schoolar/editcoursechapters.xhtml", method = RequestMethod.GET)
	public String editCourseChapters(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course course = courseDAO.loadCourseEdit(course_id);
		course.setTotal_chapters(1);
		Chapter chap = courseDAO.loadChapterData(course_id, 1);
		course.setChapter_name(chap.getName());
		if (chap != null && chap.getInitdate() != null) {
			course.setInitdate(chap.getInitdate());
		}
		course.setProblems(chap.getProblems());
		course.setFiles(chap.getMaterials());
		model.addAttribute("course", course);
		model.addAttribute("problems", courseDAO.loadAvailableProblems(course_id));
		return "/schoolar/editcoursechapters";
	}

	@RequestMapping(value = "/schoolar/editcoursechapters.xhtml", method = RequestMethod.POST)
	public String editCourseChapters(Model model, Course course, Principal principal, BindingResult result) {
		courseValidator.validateUpdateAddChapter(course, result);
		if (result.hasErrors()) {
			Course course1 = courseDAO.loadCourseEdit(course.getCourse_id());
			if (course.getTotal_chapters() == 0) {
				course.setTotal_chapters(1);
			}
			course.setChapter(course1.getChapter());
			Chapter chap = courseDAO.loadChapterData(course.getCourse_id(), course.getTotal_chapters());
			if (chap != null && chap.getInitdate() != null) {
				course.setInitdate(chap.getInitdate());
			}
			course.setProblems(chap.getProblems());
			course.setFiles(chap.getMaterials());
			model.addAttribute("course", course);
			model.addAttribute("problems", courseDAO.loadAvailableProblems(course.getCourse_id()));
			return "/schoolar/editcoursechapters";
		}
		courseDAO.updateAddChapter(course);
		if (course.getUploadfile() != null && !course.getUploadfile().isEmpty()) {
			try {
				File f = new File(Config.getProperty("courses.directory") + course.getCourse_id() + "/" + course.getTotal_chapters() + "/" + course.getUploadfile().getOriginalFilename());
				f.mkdirs();
				course.getUploadfile().transferTo(f);
			} catch (IOException ex) {
				Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalStateException ex) {
				Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		return "redirect:/schoolar/editcoursechapters.xhtml?course_id=" + course.getCourse_id();
	}

	@RequestMapping(value = "/schoolar/editcourseusers.xhtml", method = RequestMethod.GET)
	public String editCourseUsers(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course course = courseDAO.loadCourseEdit(course_id);
		// course.setUsers(courseDAO.getUsersInCourse(course_id));
		model.addAttribute("course", course);
		model.addAttribute("users", courseDAO.getUsersOffCourse(course_id));
		model.addAttribute("usersin", courseDAO.getUsersInCourse(course_id));
		return "/schoolar/editcourseusers";
	}

	@RequestMapping(value = "/tables/editcourseusers.xhtml", method = RequestMethod.GET)
	public String tablesEditCourseUsers(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course course = courseDAO.loadCourseEdit(course_id);
		model.addAttribute("course", course);
		model.addAttribute("users", courseDAO.getUsersOffCourse(course_id));
		model.addAttribute("usersin", courseDAO.getUsersInCourse(course_id));
		return "/tables/editcourseusers";
	}

	
	@RequestMapping(value = "/schoolar/editcourseusers.xhtml", method = RequestMethod.POST)
	public String editCourseUsers(Model model, Course course) {
		if (course.getProblemids() != null && course.getProblemids().length > 0) {
			courseDAO.insertUsers(course.getProblemids(), course.getCourse_id());
		}
		course.setUsers(courseDAO.getUsersInCourse(course.getCourse_id()));
		model.addAttribute("course", course);
		model.addAttribute("users", courseDAO.getUsersOffCourse(course.getCourse_id()));
		model.addAttribute("usersin", courseDAO.getUsersInCourse(course.getCourse_id()));
		return "/schoolar/editcourseusers";
	}

	@RequestMapping(value = "/schoolar/editcourseginfo.xhtml", method = RequestMethod.GET)
	public String editCourseGeneralInformation(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course course = courseDAO.loadCourseEdit(course_id);
		Chapter chap = courseDAO.loadChapterData(course.getCourse_id(), 0);
		course.setFiles(chap.getMaterials());
		course.setTotal_chapters(0);
		model.addAttribute("course", course);
		return "/schoolar/editcourseginfo";
	}

	@RequestMapping(value = "/schoolar/editcourseginfo.xhtml", method = RequestMethod.POST)
	public String editCourseGeneralInformation(Model model, Principal principal, Course course, BindingResult result) {
		courseValidator.validateUpdateGeneralInformation(course, null);
		Chapter chap = courseDAO.loadChapterData(course.getCourse_id(), 0);
		course.setFiles(chap.getMaterials());
		if (result.hasErrors()) {
			model.addAttribute("course", course);
			return "/schoolar/editcourseginfo";
		}
		courseDAO.updateCourseOverview(course);
		course.setTotal_chapters(0);
		course.setInitdate(new Date());
		course.setName("Support Materials");
		courseDAO.updateAddChapter(course);
		if (course.getUploadfile() != null && !course.getUploadfile().isEmpty()) {
			try {
				File f = new File(Config.getProperty("courses.directory") + course.getCourse_id() + "/" + course.getTotal_chapters() + "/" + course.getUploadfile().getOriginalFilename());
				f.mkdirs();
				course.getUploadfile().transferTo(f);
				courseDAO.insertLog(principal.getName(), course.getCourse_id(), "just add " + course.getUploadfile().getOriginalFilename() + " to the complementary materials section");
			} catch (IOException ex) {
				Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IllegalStateException ex) {
				Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
			}

		}
		model.addAttribute("course", course);
		return "/schoolar/editcourseginfo";
	}

	@RequestMapping(value = "/schoolar/coursesboard.xhtml", method = RequestMethod.GET)
	public String coursesboard(Model model, HttpServletRequest request, PagingOptions options, @RequestParam(required = false, value = "creator") String username,
			@RequestParam(required = false, defaultValue = "0", value = "access") Integer access, @RequestParam(required = false, defaultValue = "0", value = "status") Integer status,
			@RequestParam(required = false, defaultValue = "", value = "name") String name) {
		Filter filter = new Filter();
		filter.setUsername(username);
		// filter.setName(name);
		filter.setAccess(access);
		filter.setVstatus(status);
		model.addAttribute("filter", filter);
		model.addAttribute("isadmin", request.isUserInRole(Roles.ROLE_ADMIN));
		return "/schoolar/coursesboard";
	}

	@RequestMapping(value = "/tables/coursesboard.xhtml", method = RequestMethod.GET)
	public String tablesCoursesboard(Model model, HttpServletRequest request, PagingOptions options, @RequestParam(required = false, value = "creator") String username,
			@RequestParam(required = false, defaultValue = "0", value = "access") Integer access, @RequestParam(required = false, defaultValue = "0", value = "status") Integer status,
			@RequestParam(required = false, defaultValue = "", value = "name") String name) {
		int found = courseDAO.countAvailableCoursesFilter(status, username, access, name);
		if (found != 0) {
			IPaginatedList<Course> courses = courseDAO.getAvailableCoursesFilter(found, status, username, access, options, name);
			model.addAttribute("courses", courses);
		}
		model.addAttribute("isadmin", request.isUserInRole(Roles.ROLE_ADMIN));
		return "/tables/coursesboard";
	}

	
	@RequestMapping(value = "/schoolar/joincourse.xhtml", method = RequestMethod.GET)
	public String joincourse(Model model, HttpServletRequest request, Principal principal, @RequestParam("course_id") Integer course_id) {
		if ((courseDAO.canJoin(course_id, principal.getName()) || request.isUserInRole(Roles.ROLE_ADMIN)) && !courseDAO.isUserInCourse(course_id, principal.getName())) {
			courseDAO.joinCourse(course_id, principal.getName());
			courseDAO.insertLog(principal.getName(), course_id, "has join the course");
		}
		return "redirect:/schoolar/course.xhtml?course_id=" + course_id;
	}

	@RequestMapping(value = "/schoolar/course.xhtml", method = RequestMethod.GET)
	public String course(Model model, HttpServletRequest request, Principal principal, @RequestParam("course_id") Integer course_id) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)
				&& !courseDAO.isUserInCourse(course_id, principal.getName())) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course c = courseDAO.loadCourseEdit(course_id);
		model.addAttribute("course", c);
		model.addAttribute("chapters", courseDAO.loadAllChapters(c));
		model.addAttribute("allpoints", courseDAO.totalPointsInCourse(course_id));
		try {
			model.addAttribute("userpoints", courseDAO.userPointsInCourse(course_id, principal.getName()));
		} catch (Exception e) {
			model.addAttribute("userpoints", 0);
		}

		return "/schoolar/course";
	}

	@RequestMapping(value = "/schoolar/viewproblem.xhtml", method = RequestMethod.GET)
	public String viewproblem(Model model, Locale locale, HttpServletRequest request, Principal principal, @RequestParam("course_id") Integer course_id, @RequestParam Integer pid) {
		if ((!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id) && !courseDAO.isUserInCourse(course_id,
				principal.getName())) || !courseDAO.isProblemAvailableInCourse(course_id, pid)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course c = courseDAO.loadCourseEdit(course_id);
		model.addAttribute("course", c);
		Problem problem = problemDAO.getProblemByCode(locale.getLanguage(), pid,
				request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole(Roles.ROLE_PSETTER) || request.isUserInRole(Roles.ROLE_SUPER_PSETTER));
		problem.setDate(problem.getDate().split(" ")[0]);
		problemDAO.fillProblemLanguages(problem);
		model.addAttribute("problem", problem);
		if (request.isUserInRole(Roles.ROLE_USER)) {
			boolean view_pinfo = problemDAO.bool("get.pinfo.byuser", getUsername(principal));
			model.addAttribute("view_pinfo", view_pinfo);
			if (view_pinfo) {
				model.addAttribute("classifications", problemDAO.objects("get.problem.classifications", ProblemClassification.class, problem.getPid()));
			}
		}
		return "/schoolar/viewproblem";
	}

	@RequestMapping(value = "/schoolar/coursesubmit.xhtml", method = RequestMethod.GET)
	public String courseSubmit(Model model, HttpServletRequest request, Principal principal, @RequestParam("course_id") Integer course_id, @RequestParam Integer pid) {
		if ((!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id) && !courseDAO.isUserInCourse(course_id,
				principal.getName())) || !courseDAO.isProblemAvailableInCourse(course_id, pid)) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		Course c = courseDAO.loadCourseEdit(course_id);
		model.addAttribute("course", c);

		SubmissionJudge submit = new SubmissionJudge();
		Language language = userDAO.getProgrammingLanguageByUsername(getUsername(principal));
		submit.setPid(pid);

		submit.setLid(language.getLid());
		submit.setKey(language.getKey());
		List<Language> languages = new LinkedList<Language>();

		try {
			if (submit.getPid() != 0 && problemDAO.exists(submit.getPid())) {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(submit.getPid()));
			} else {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(null));
			}
		} catch (EmptyResultDataAccessException e) {
		}
		submit.setLanguages(languages);
		model.addAttribute("languages", languages);
		if (getUsername(principal) != null) {
			model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
		}
		model.addAttribute("submit",submit);
		return "/schoolar/coursesubmit";
	}

	@RequestMapping(value = "/schoolar/coursesubmit.xhtml", method = RequestMethod.POST)
	public String coursesubmit(SecurityContextHolderAwareRequestWrapper requestWrapper, Locale locale, Principal principal, Model model, SubmissionJudge submit, BindingResult bindingResult,
			@RequestParam("course_id") Integer course_id) {
		List<Language> languages = new LinkedList<Language>();
		try {
			if (submit.getPid() != 0 && problemDAO.exists(submit.getPid())) {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(submit.getPid()));
			} else {
				languages.addAll(utilDAO.getEnabledLanguagesByProblem(null));
			}
		} catch (EmptyResultDataAccessException e) {
		}
		submit.setCourse_id(course_id);
		submit.setLanguages(languages);
		submit.setHaspriviligeForProblem(requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || requestWrapper.isUserInRole(Roles.ROLE_PSETTER) || requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER));
		submitValidator.validate(submit, bindingResult);
		Course c = courseDAO.loadCourseEdit(course_id);
		model.addAttribute("course", c);
		model.addAttribute("enableadveditor", userDAO.bool("is.adved.enable", getUsername(principal)));
		model.addAttribute("languages", languages);
		if (bindingResult.hasErrors()) {
			return "/schoolar/coursesubmit";
		}
		int iduser = userDAO.integer("select.uid.by.username", getUsername(principal));
		Problem problem = problemDAO.getProblemSubmitDataByAbb(locale.getLanguage(), submit.getPid());
		boolean locked = problemDAO.bool("issolved.byuser", iduser, problem.getPid()) && problemDAO.isLocked(iduser, problem.getPid());
		int sid = submissionDAO.insertSubmission(iduser, getUsername(principal), problem.getPid(), submit.getCode(), submit.getLanguageByLid(), locked, course_id);
		SubmissionJudge submission = new SubmissionJudge(sid, iduser, submit.getCode(), problem.getPid(), problem.getTime(), problem.getCasetimelimit(), problem.getMemory(), submit.getLanguageByLid());
		submission.setSpecialJudge(problem.isSpecial_judge());
		try {
			utils.startCalification(submission);
		} catch (Exception e) {
			submissionDAO.changeStatus(sid, "Unqualified");
		}
		courseDAO.insertLog(principal.getName(), course_id, "make a SubmissionJudge to problem " + submit.getPid());
		return "/schoolar/coursesubmit";
	}

	@RequestMapping(value = "/schoolar/viewfile.xhtml", method = RequestMethod.GET)
	public String viewfile(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal, OutputStream os, @RequestParam("course_id") Integer course_id,
			@RequestParam("content") Integer content) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)
				&& !courseDAO.isUserInCourse(course_id, principal.getName())) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		response.setContentType("application/octet-stream");
		String dir = courseDAO.getContentAddress(content);
		String dirDl = dir.replace(" ", "_");
		response.setHeader("Content-disposition", "inline; filename=" + dirDl);
		String currentDir = Config.getProperty("courses.directory") + course_id + "/" + courseDAO.getChapterByContentId(content);
		try {
			FileUtils.redirectStreams(new FileInputStream(currentDir + "/" + dir), os);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	@RequestMapping(value = "/schoolar/getchapterinfo.xhtml", method = RequestMethod.POST)
	public String getChapterInfo(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id, @RequestParam("chapter") Integer chapter) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
			return "redirect:/errors/accessdenied.xhtml";
		}

		Chapter chap = courseDAO.loadChapterData(course_id, chapter);
		JSONArray result = new JSONArray();

		JSONObject obj = new JSONObject();
		try {
			obj.accumulate("name", chap.getName() == null ? "" : chap.getName());
			if (chap.getInitdate() == null) {
				chap.setInitdate(new Date());
			}
			obj.accumulate("date", chap.getInitdate());
			JSONArray problems = new JSONArray();
			for (Iterator<Problem> it = chap.getProblems().iterator(); it.hasNext();) {
				Problem problem = it.next();
				JSONObject pobj = new JSONObject();
				pobj.accumulate("pid", problem.getPid());
				pobj.accumulate("title", problem.getTitle());
				problems.add(pobj);
			}
			obj.accumulate("problems", problems);
			JSONArray materials = new JSONArray();
			for (Iterator<ChapterContent> it = chap.getMaterials().iterator(); it.hasNext();) {
				ChapterContent mat = it.next();
				JSONObject pobj = new JSONObject();
				pobj.accumulate("content_address", mat.getContent_address());
				pobj.accumulate("content_id", mat.getContent_id());
				materials.add(pobj);
			}
			obj.accumulate("materials", materials);
			obj.accumulate("iyear", chap.getInitdate().getYear() + 1900);
			obj.accumulate("imonth", chap.getInitdate().getMonth() + 1);
			obj.accumulate("iday", chap.getInitdate().getDate());
			obj.accumulate("ihours", chap.getInitdate().getHours());
			obj.accumulate("iminutes", chap.getInitdate().getMinutes());
			obj.accumulate("iseconds", chap.getInitdate().getSeconds());

		} catch (JSONException ex) {
			Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
		}

		result.add(obj);

		model.addAttribute("chapter", result.toString());

		return "/schoolar/getchapterinfo";
	}

	@RequestMapping(value = "/schoolar/updateboard.xhtml", method = RequestMethod.POST)
	public String updateBoard(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id, @RequestParam("last") Integer last) {
		if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)
				&& !courseDAO.isUserInCourse(course_id, principal.getName())) {
			return "redirect:/errors/accessdenied.xhtml";
		}
		JSONArray result = new JSONArray();
		int tmp = last;
		JSONObject obj = new JSONObject();
		try {
			JSONArray logs = new JSONArray();
			List<Log> ls = last == 0 ? courseDAO.lastLogs(course_id) : courseDAO.lastLogs(course_id, last);
			for (Iterator<Log> it = ls.iterator(); it.hasNext();) {
				Log lg = it.next();
				JSONObject pobj = new JSONObject();
				pobj.accumulate("username", lg.getUsername());
				pobj.accumulate("id", lg.getId());
				pobj.accumulate("message", lg.getLog());
				logs.add(pobj);
				if (lg.getId() > tmp) {
					tmp = lg.getId();
				}
			}
			obj.accumulate("logs", logs);
			obj.accumulate("lastlog", tmp);

		} catch (JSONException ex) {
			Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
		}
		result.add(obj);

		model.addAttribute("dashboard", result.toString());

		return "/schoolar/updateboard";
	}

	@RequestMapping(value = "/schoolar/removematerial.xhtml", method = RequestMethod.POST)
	public String removeMaterial(Model model, Principal principal, HttpServletRequest request, @RequestParam("course_id") Integer course_id, @RequestParam("chapter") Integer chapter,
			@RequestParam("content_id") Integer content_id) {
		try {
			if (!(request.isUserInRole(Roles.ROLE_ADMIN) || request.isUserInRole("ROLE_PROFESOR")) && !courseDAO.createdBy(principal.getName(), course_id)) {
				return "redirect:/errors/accessdenied.xhtml";
			}
			String material = courseDAO.getContentAddress(content_id);
			courseDAO.removeMaterial(course_id, chapter, content_id);
			File f = new File(Config.getProperty("courses.directory") + course_id + "/" + chapter + "/" + material);
			if (f.exists()) {
				f.delete();
			}
			JSONObject obj = new JSONObject();
			obj.accumulate("result", "success");
			model.addAttribute("chapter", obj.toString());

		} catch (JSONException ex) {
			Logger.getLogger(ScholarController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return "/schoolar/removematerial";
	}
}
