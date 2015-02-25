package cu.uci.coj.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Authority;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Group;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.Locale;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.Team;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.userValidator;

@Controller("userAdminController")
@RequestMapping(value = "/admin")
public class UserController extends BaseController {

	@Resource
	private UserDAO userDAO;
	@Resource
	private UtilDAO utilDAO;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private InstitutionDAO institutionDAO;
	@Resource
	private userValidator validator;

	@RequestMapping(value = "/manageusers.xhtml", method = RequestMethod.GET)
	public String listUsers(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		model.addAttribute("pattern", pattern);
		return "/admin/manageusers";
	}
	
	@RequestMapping(value = "/tables/manageusers.xhtml", method = RequestMethod.GET)
	public String tableListUsers(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		IPaginatedList<User> users = userDAO.loadUsersAdmin(pattern, options);
		model.addAttribute("users", users);
		return "/admin/tables/manageusers";
	}

	@RequestMapping(value = "/manageuser.xhtml", method = RequestMethod.GET)
	public String manageUsers(Model model, @RequestParam("username") String username) {
		User user;
		if (userDAO.isUser(username)) {
			user = userDAO.loadAllUserData(username);
			user.setAuthorities(userDAO.getUserAuthorities(user.getUsername()));
		} else {
			user = userDAO.loadAllTeamData(username);
		}
		model.addAttribute("countries", userDAO.objects("enabled.countries", Country.class));
		model.addAttribute("planguages", utilDAO.getEnabledProgramingLanguages());
		model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
		if (user.getCountry_id() != 0) {
			List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(user.getCountry_id());
			institutions.add(0, new Institution(-1, "NONE", "NONE INSTITUTION"));
			model.addAttribute("institutions", institutions);
		}
		model.addAttribute("authorities", utilDAO.objects("load.authorities", Authority.class));
		model.addAttribute(user);
		return "/admin/manageuser";
	}

	@RequestMapping(value = "/manageuser.xhtml", method = RequestMethod.POST)
	public String manageUsers(Model model, User user, BindingResult result) {
		user.setUid(userDAO.integer("user.uid", user.getUsername()));
		user.setTeam(userDAO.bool("is.team", user.getUsername()));
		validator.validateManageAdmin(user, result);
		if (result.hasErrors()) {
			model.addAttribute("countries", userDAO.objects("enabled.countries", Country.class));
			model.addAttribute("planguages", utilDAO.getEnabledProgramingLanguages());
			model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
			if (user.getCountry_id() != 0) {
				List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(user.getCountry_id());
				institutions.add(0, new Institution(-1, "NONE", "NONE INSTITUTION"));
				model.addAttribute("institutions", institutions);
			}
			model.addAttribute("authorities", utilDAO.objects("load.authorities", Authority.class));
			model.addAttribute(user);
			return "/admin/manageuser";
		}
		if (user.getPassword() != null && user.getPassword().length() > 0) {
			Md5PasswordEncoder md5 = new Md5PasswordEncoder();
			user.setPassword(md5.encodePassword(user.getPassword(), "ABC123XYZ789"));
		}
		userDAO.updateUserByAdmin(user);
		if (!user.isTeam()) {
			userDAO.clearUserAuthorities(user.getUsername());

			String[] authorities = userDAO.grantUserAuthority(user.getUsername(), user.getRole());
			boolean deletePsetters = true;
			for (int i = 0; authorities != null && i < authorities.length; i++) {
				if (deletePsetters && (authorities[i].equals(Roles.ROLE_PSETTER) || authorities[i].equals(Roles.ROLE_SUPER_PSETTER)))
					deletePsetters = false;
			}

			// si no tiene el rol PSETTER, eliminar cualquier referencia del
			// usuario en la tabla psetter, para evitar inconsistencia
			if (deletePsetters)
				userDAO.dml("clear.psetters.uid", user.getUid());
		}
		return "redirect:/admin/manageuser.xhtml?username=" + user.getUsername();
	}

	@RequestMapping(value = "/manageteams.xhtml", method = RequestMethod.GET)
	public String listTeams(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		model.addAttribute("pattern", pattern);
		return "/admin/manageteams";
	}
	
	@RequestMapping(value = "/tables/manageteams.xhtml", method = RequestMethod.GET)
	public String tablesListTeams(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		IPaginatedList<User> users = userDAO.loadTeamsAdmin(pattern, options);
		model.addAttribute("users", users);
		model.addAttribute("found", users.getFullListSize());
		return "/admin/tables/manageteams";
	}

	@RequestMapping(value = "/createteams.xhtml", method = RequestMethod.GET)
	public String createTeams(Model model) {
		model.addAttribute("contests", contestDAO.getComingAndRunningContests());
		model.addAttribute("countries", userDAO.objects("enabled.countries", Country.class));
		model.addAttribute("groups", utilDAO.objects("get.groups", Group.class));
		model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
		Team team = new Team();
		List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(team.getCountry());
		institutions.add(0, new Institution(-1, "NONE", "NONE INSTITUTION"));
		model.addAttribute("institutions", institutions);
		model.addAttribute(team);
		return "/admin/createteams";
	}

	@RequestMapping(value = "/createteams.xhtml", method = RequestMethod.POST)
	public String createTeams(Model model, Team team, BindingResult result) {
		validator.validateTeam(team, result);
		if (result.hasErrors()) {
			model.addAttribute("contests", contestDAO.getComingAndRunningContests());
			model.addAttribute("countries", userDAO.objects("enabled.countries", Country.class));
			model.addAttribute("groups", utilDAO.objects("get.groups", Group.class));
			model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
			List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(team.getCountry());
			institutions.add(0, new Institution(-1, "NONE", "NONE INSTITUTION"));
			model.addAttribute("institutions", institutions);
			model.addAttribute(team);
			return "/admin/createteams";
		}
		userDAO.createTeams(team);
		return "redirect:/admin/createteams.xhtml";
	}
}
