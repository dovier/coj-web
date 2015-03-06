package cu.uci.coj.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.EntryHelper;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.entryValidator;

@Controller("EntryController")
public class EntryController extends BaseController {

	@Resource
	private EntryDAO entryDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private entryValidator entriesValidator;
	@Resource
	private EntryHelper entryHelper;

	private Map<String, String> emoties = new HashMap<String, String>();

	@PostConstruct
	public void init() {
		emoties.put(":(", "<i class=\"fa fa-frown-o\"></i>");
		emoties.put(":|", "<i class=\"fa fa-meh-o\"></i>");
		emoties.put(":)", "<i class=\"fa fa-smile-o\"></i>");
		/*
		 * Esto fue codigo necesario para cuando adicionamos los emoticones,
		 * reconvertir todas las entries anteriores de manera adecuada. Cuando
		 * se compruebe que los emoticones funcionan, hay que eliminarlo
		 * List<Entry> entries =
		 * entryDAO.objects("select e.eid as id,e.* from entries e",
		 * Entry.class); for (Entry e : entries) { preProcessEntry(e);
		 * entryDAO.updateEntry(e); }
		 */
	}

	private void preProcessEntry(Entry entry) {
		// adicionar html para vinculos a paginas y a usuarios.
		String text = entry.getText();
		String[] tokens = text.split(" ");
		Set<String> tokenSet = new HashSet<String>(Arrays.asList(tokens));
		for (String token : tokenSet) {
			token = token.trim();
			if (token.startsWith("p#")) {
				if (NumberUtils.isNumber(token.substring(2))) {
					entryDAO.bool("select exists(select pid from problem where pid=?)", Integer.valueOf(token.substring(2)));
					text = text.replace(token, "<a target=\"new\" href=\"24h/problem.xhtml?pid=" + token.substring(2) + "\" >" + token.substring(2) + "</a>");
				}
			} else if (token.startsWith("c#")) {
				if (NumberUtils.isNumber(token.substring(2))) {
					entryDAO.bool("select exists(select pid from contest where pid=?)", Integer.valueOf(token.substring(2)));
					text = text.replace(token, "<a target=\"new\" href=\"contest/contestview.xhtml?cid=" + token.substring(1) + "\" >" + token.substring(1) + "</a>");
				}
			} else if (token.startsWith("@")) {
				boolean reply = entryDAO.bool("select exists (select uid from users where username=?)", token.substring(1));
				entry.setReply(true);
				entry.setHasUsers(true);
				if (reply)
					text = text.replace(token, "<a target=\"new\" href=\"user/useraccount.xhtml?username=" + token.substring(1) + "\" >" + token + "</a>");
			} else if (token.startsWith("http://") || token.startsWith("https://")) {
				text = text.replace(token, "<a target=\"new\" href=\"" + token + "\">" + token + "</a>");
				entry.setHasLinks(true);
			} else if (emoties.keySet().contains(token)) {
				text = text.replace(token, emoties.get(token));
			}
		}
		entry.setText(text);
	}

	@RequestMapping(value = "/index.xhtml", method = RequestMethod.POST)
	public String addEntry(HttpServletRequest request, Principal principal, Locale locale, Model model, PagingOptions options, Entry entry, BindingResult errors) {
		entriesValidator.validate(entry, errors);
		if (errors.hasErrors()) {
			model.addAttribute("entry", entry);
			return "index";
		} else {
			preProcessEntry(entry);
			entryDAO.addEntry(entry, request.isUserInRole(Roles.ROLE_ADMIN), principal.getName());
		}
		return "redirect:/index.xhtml";
	}

	@RequestMapping(value = "/forwardentry.xhtml", method = RequestMethod.GET)
	public String forwardEntry(Principal principal, HttpServletRequest request, @RequestParam("id") int id) {
		entryDAO.forward(id, request.isUserInRole(Roles.ROLE_ADMIN), getUsername(principal));
		return "redirect:/index.xhtml";
	}

	@RequestMapping(value = "/user/{username}/following.xhtml", method = RequestMethod.GET)
	public String following(Model model, @PathVariable("username") String username, @RequestParam(required = false, value = "page", defaultValue = "1") Integer page,
			@RequestParam(required = false, value = "pagef", defaultValue = "1") Integer pagef) {

		Integer uid = entryDAO.integer("select.uid.by.username", username);

		if (uid == null)
			return "redirect:/error/error.xhtml?error=7";

		model.addAttribute("username", username);
		return "/user/following";
	}

	@RequestMapping(value = "/user/{username}/pagefollowers.xhtml", method = RequestMethod.POST)
	public String pageFollowers(Model model, @PathVariable("username") String username, @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {

		int uid = entryDAO.integer("select.uid.by.username", username);

		IPaginatedList<User> followers = entryDAO.paginated("followers", User.class, 15, new PagingOptions(page), uid);

		model.addAttribute("user", username);
		model.addAttribute("followers", followers);
		return "/user/pagefollowers";
	}

	@RequestMapping(value = "/user/{username}/pagefollowing.xhtml", method = RequestMethod.POST)
	public String pageFollowing(Model model, @PathVariable("username") String username, @RequestParam(required = false, value = "page", defaultValue = "1") Integer page) {

		int uid = entryDAO.integer("select.uid.by.username", username);

		IPaginatedList<User> following = entryDAO.paginated("following", User.class, 15, new PagingOptions(page), uid);

		model.addAttribute("user", username);
		model.addAttribute("following", following);
		return "/user/pagefollowing";
	}

	@RequestMapping(value = "/user/{username}/follow.xhtml", method = RequestMethod.GET)
	public String follow(Principal principal, @PathVariable("username") String username, Model model) {

		if (principal == null || !userDAO.isUser(username)) {
			return "/error/accessdenied";
		}

		// esto es para que un usuario no pueda seguirse a si mismo
		int pUid = userDAO.idByUsername(principal.getName());
		int uid = userDAO.idByUsername(username);
		if (pUid != uid) {
			entryDAO.dml("follow.user", userDAO.idByUsername(username), userDAO.idByUsername(principal.getName()));
		}

		return "redirect:/user/useraccount.xhtml?username=" + username;
	}

	@RequestMapping(value = "/user/{username}/unfollow.xhtml", method = RequestMethod.GET)
	public String unfollow(Principal principal, @PathVariable("username") String username, Model model) {

		if (!userDAO.isUser(username)) {
			return "/error/accessdenied";
		}

		Integer found = entryDAO.integer("count.entries");
		if (found == null) {
			found = 0;
		}
		entryDAO.dml("unfollow.user", userDAO.idByUsername(username), userDAO.idByUsername(principal.getName()));

		return "redirect:/user/useraccount.xhtml?username=" + username;
	}

	@RequestMapping(value = "/user/{username}/listentries.xhtml", method = RequestMethod.GET)
	public String n(Principal principal, @PathVariable("username") String username, Model model, PagingOptions options) {

		if (!userDAO.isUser(username)) {
			return "/error/accessdenied";
		}
		model.addAttribute("username", username);
		return "/user/listentries";
	}

	@RequestMapping(value = "/user/{username}/tables/listentries.xhtml", method = RequestMethod.GET)
	public String tables(Principal principal, @PathVariable("username") String username, Model model, PagingOptions options) {

		int uid = userDAO.idByUsername(username);

		IPaginatedList<Entry> entries = entryDAO.paginated("enabled.entries.list.by.user", Entry.class, 10, options, uid);
		model.addAttribute("entries", entries);
		model.addAttribute("found", entries.getFullListSize());

		return "/tables/listentries";
	}
	
	@RequestMapping(value = "/index.xhtml", method = RequestMethod.GET)
	public String listEntries(Principal principal, Model model, PagingOptions options) {

		model.addAttribute("entry", new Entry());
		model.addAttribute("emoties", entryHelper.getEmoties().entrySet());


		return "index";
	}
	
	@RequestMapping(value = "/tables/entries.xhtml", method = RequestMethod.GET)
	public String tableEntries(Principal principal, Model model, PagingOptions options, @RequestParam(value = "entries", required = false, defaultValue = "all") String entryFilter) {

		IPaginatedList<Entry> entries;
		if (principal != null) {
			if ("all".equals(entryFilter))
				entries = entryDAO.paginated("enabled.entries.list", Entry.class, 10, options);
			else
				entries = entryDAO.paginated("select.followed.entries", Entry.class, 10, options, userDAO.idByUsername(principal.getName()));

		} else {
			entries = entryDAO.paginated("enabled.entries.list", Entry.class, 10, options);
		}
		model.addAttribute("entries", entries);

		return "tables/entries";
	}

	@RequestMapping(produces = "application/json", value = "/user/like.json", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody() String like(Principal principal, @RequestParam Integer id, Model model, PagingOptions options) {

		int userId = userDAO.idByUsername(principal.getName());
		boolean canRate = entryDAO.bool("check.entries.already.rated", userId, id,userId, id);
		if (canRate) {
			entryDAO.dml("insert.user.entries.rate", userId, id);
			entryDAO.dml("rate.up.entries", id);
		}
		return JSONArray.fromObject(entryDAO.integer(0, "rate.by.id", id)).toString();
	}

	@RequestMapping(produces = "application/json", value = "/user/dislike.json", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody() String dislike(Principal principal, @RequestParam Integer id, Model model, PagingOptions options) {

		int userId = userDAO.idByUsername(principal.getName());
		boolean canRate = entryDAO.bool("check.entries.already.rated", userId, id,userId, id);

		if (canRate) {
			entryDAO.dml("insert.user.entries.rate", userId, id);
			entryDAO.dml("rate.down.entries", id);
		}
		return JSONArray.fromObject(entryDAO.integer(0, "rate.by.id", id)).toString();
	}

	@RequestMapping(value = "/admin/deleteentry.xhtml", method = RequestMethod.GET)
	public String delete(Model model, @RequestParam Integer id) {
		entryDAO.deleteEntry(id);
		return "redirect:/admin/manageentries.xhtml";
	}

	@RequestMapping(value = "/admin/enableentry.xhtml", method = RequestMethod.GET)
	public String enableEntry(HttpServletRequest request, Principal principal, @RequestParam("id") int id) {
		if (request.isUserInRole(Roles.ROLE_ADMIN)) {
			entryDAO.enableEntry(id, getUsername(principal));
		}
		return "redirect:/admin/manageentries.xhtml";
	}

	@RequestMapping(produces = "application/json", value = "/admin/disableentry.xhtml", method = RequestMethod.POST, headers = { "Accept=application/json" })
	public @ResponseBody() String disableEntry(HttpServletRequest request, Locale locale, Principal principal, @RequestParam Integer id) {
		if (request.isUserInRole(Roles.ROLE_ADMIN)) {
			entryDAO.disableEntry(id);
		}
		Entry entry = new Entry();
		entry.setText(getMessageSource().getMessage("entry.pending.approval", null, locale));
		entry.setId(id);
		return JSONObject.fromObject(entry).toString();
	}

	@RequestMapping(value = "/admin/manageentries.xhtml", method = RequestMethod.GET)
	public String manageEntries(Locale locale, Principal principal, Model model, PagingOptions options) {
		model.addAttribute("entry", new Entry());
		return "/admin/manageentries";
	}
	
	@RequestMapping(value = "/admin/tables/manageentries.xhtml", method = RequestMethod.GET)
	public String tableManageEntries(Locale locale, Principal principal, Model model, PagingOptions options) {
		model.addAttribute("disabled", entryDAO.paginated("entries.list.total.disabled", Entry.class, 10, options));
		return "/admin/tables/manageentries";
	}

	@RequestMapping(value = "/admin/manageentries.xhtml", method = RequestMethod.POST)
	public void manageEntries(Entry entry, HttpServletResponse response, Locale locale, Principal principal, Model model) {
		entryDAO.updateEntry(entry);
	}

}
