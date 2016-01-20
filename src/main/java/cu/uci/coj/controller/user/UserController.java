package cu.uci.coj.controller.user;

import java.security.Principal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import atg.taglib.json.util.JSONArray;
import atg.taglib.json.util.JSONObject;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Achievement;
import cu.uci.coj.model.CompareUsers;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Institution;
import cu.uci.coj.model.Locale;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.User;
import cu.uci.coj.model.UserClassificationStats;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.forgottenValidator;
import cu.uci.coj.validator.registrationValidator;
import cu.uci.coj.validator.userValidator;

@Controller
@RequestMapping(value = "/user")
@Scope("session")
public class UserController extends BaseController {

    @Resource
    private UserDAO userDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private InstitutionDAO institutionDAO;
    @Resource
    private CountryDAO countryDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private registrationValidator regValidator;
    @Resource
    private forgottenValidator forgottenValidator;
    @Resource
    private MailNotificationService mailNotificationService;
    @Resource
    private userValidator userValidator;
    @Resource
    private WbSiteDAO wbSiteDAO;
    @Resource
    WbContestService wbContestService;


    private String passcode = null;

    @RequestMapping(value = "/accountactivation.xhtml", method = RequestMethod.GET)
    public String accountActivation(HttpServletRequest request, Model model, @RequestParam("key") String key) {
        boolean ok = false;
        try {
            User user = baseDAO.object("select.user.activation.key", User.class, key);
            baseDAO.dml("enable.user", true, user.getUsername());
            baseDAO.dml("delete.activationrecord.by.username", user.getUsername());
            ok = true;
        } catch (EmptyResultDataAccessException e) {
            return "redirect:/error/error.xhtml?error=8";
        }
        model.addAttribute("ok", ok);
        return "/user/accountactivation";
    }

    @RequestMapping(value = "/useraccount.xhtml", method = RequestMethod.GET)
    public String userAccount(HttpServletRequest request, Model model, Principal principal, @RequestParam("username") String username) {
        User user = null;

        if (!userDAO.bool("is.user.enabled", username))
            return "redirect:/error/error.xhtml?error=4";

        Integer uid = userDAO.idByUsername(username);
        if (uid != null && userDAO.isUser(username)) {
            user = userDAO.loadUserData(username);
            List<Problem> solved = userDAO.objects("problems.solved.1", Problem.class, user.getUid());
            List<Problem> unsolved = userDAO.getProblemsTryied(user.getUid());
            List<Achievement> achievements = userDAO.objects("achievements.by.user", Achievement.class, user.getUid());

            model.addAttribute("achievements", achievements);

            if (solved.isEmpty()) {
                user.setLast_accepted("***");
            }
            if (unsolved.isEmpty() && solved.isEmpty()) {
                user.setLast_submission("***");
            }
            if (solved.size() > 0) {
                model.addAttribute("solved", solved);
            }
            if (unsolved.size() > 0) {
                model.addAttribute("unsolved", unsolved);
            }
            user.setSolved(solved.size());
            user.setUnsolved(unsolved.size());
            user.setRanking(userDAO.integer("ranking.position", username));
            user.setTot_ranking(userDAO.countEnabledUsers(null, false));
            user.setRankingbycountry(userDAO.integer("ranking.position.country", user.getCountry_id(), username));
            user.setTot_rankingbycountry(userDAO.countEnabledUsersByCountry(null, false, user.getCountry_id()));
            user.setRankingbyinstitution(userDAO.integer("ranking.position.institution", user.getInstitution_id(), username));
            user.setTot_rankingbyinstitution(userDAO.countEnabledUsersByInstitutions(null, false, user.getInstitution_id()));

            PagingOptions options = new PagingOptions(1, "asc", "startDate");
            IPaginatedList<WbContest> contests = wbContestService.getContestList(0, options, 1, user.getUid());
            model.addAttribute("contests", contests);

            List<WbSite> list = wbSiteDAO.getSiteList();
            HashMap<Integer, WbSite> map = new HashMap<Integer, WbSite>();
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i).getSid(), list.get(i));
            }
            model.addAttribute("mapsites", map);
        } else if (userDAO.bool("is.team", username)) {
            user = userDAO.loadContestUserData(username);
        } else {
            return "redirect:/error/error.xhtml?error=2";
        }
        model.addAttribute("user", user);
        model.addAttribute("currentUsername", principal != null && user != null ? principal.getName().equals(user.getUsername()) : false);
        model.addAttribute("uid", user.getUid());

        if (principal != null) {
            int pUid = userDAO.idByUsername(principal.getName());
            if (pUid == uid) {
                model.addAttribute("isfollowing", null);
            } else {
                model.addAttribute("isfollowing", userDAO.bool("user.is.following", uid, userDAO.idByUsername(principal.getName())));
            }
        } else {
            model.addAttribute("isfollowing", null);
        }

        model.addAttribute("lastEntry", userDAO.object("last.entry.by.user", Entry.class, uid));
        model.addAttribute("count.entries", userDAO.integer(0, "count.entries", uid));
        // restar uno porque todos los usuarios se siguen a si mismos.
        model.addAttribute("followers", userDAO.integer(0, "count.followers", uid));
        model.addAttribute("following", userDAO.integer(0, "count.following", uid));
        model.addAttribute("userclassif", userDAO.getUserClassifications(uid));
        return "/user/useraccount";
    }

/*    @RequestMapping(value = "/compareusers.xhtml", method = RequestMethod.GET)
    public String compareUsers(Principal principal, Model model, @RequestParam(required = false, value = "uid1") String user1, @RequestParam(required = false, value = "uid2") String user2) {
        model.addAttribute("compare", new CompareUsers("", ""));
        model.addAttribute("error", true);
        return "/user/compareusers";
    }*/

    @RequestMapping(value = "/compareusers.xhtml", method = RequestMethod.GET)
    public String compareUsersPost(Principal principal, Model model, @RequestParam(required = false, value = "uid1") String user1, @RequestParam(required = false, value = "uid2") String user2) {
        int error = 1;
        CompareUsers cmp = new CompareUsers(user1, user2);
        if (user1 != null && user2 != null && user1.length() > 0 && user2.length() > 0) {
            int uid1 = -1, uid2 = -1;
            try {
                uid1 = userDAO.integer("select.uid.by.username", user1);
                uid2 = userDAO.integer("select.uid.by.username", user2);
            } catch (Exception e) {
                error = 3;
                model.addAttribute("compare", cmp);
                model.addAttribute("error", error);
                model.addAttribute("errormessage", "general.error.userproblem");
                return "/user/compareusers";
            }

            if (uid1 != -1 && uid2 != -1) {
                List<Problem> solved1 = userDAO.objects("problems.solved", Problem.class, user1);
                List<Problem> solved2 = userDAO.objects("problems.solved", Problem.class, user2);
                List<Problem> s1 = new LinkedList<Problem>();
                List<Problem> s2 = new LinkedList<Problem>();
                List<Problem> f1 = new LinkedList<Problem>();
                List<Problem> f2 = new LinkedList<Problem>();
                while (solved1.size() > 0 && solved2.size() > 0) {
                    if (solved1.get(0).getPid() < solved2.get(0).getPid()) {
                        cmp.getFacc().add(solved1.remove(0));
                    } else if (solved2.get(0).getPid() < solved1.get(0).getPid()) {
                        cmp.getSacc().add(solved2.remove(0));
                    } else {
                        cmp.getBacc().add(solved2.remove(0));
                        solved1.remove(0);
                    }
                }

                while (solved1.size() > 0) {
                    cmp.getFacc().add(solved1.remove(0));
                }

                while (solved2.size() > 0) {
                    cmp.getSacc().add(solved2.remove(0));
                }

                solved1 = userDAO.getProblemsTryied(user1);
                solved2 = userDAO.getProblemsTryied(user2);
                f1.addAll(solved1);
                f2.addAll(solved2);
                while (solved1.size() > 0 && solved2.size() > 0) {
                    if (solved1.get(0).getPid() < solved2.get(0).getPid()) {
                        cmp.getFf().add(solved1.remove(0));
                    } else if (solved2.get(0).getPid() < solved1.get(0).getPid()) {
                        cmp.getSf().add(solved2.remove(0));
                    } else {
                        cmp.getBf().add(solved2.remove(0));
                        solved1.remove(0);
                    }
                }

                while (solved1.size() > 0) {
                    cmp.getFf().add(solved1.remove(0));
                }

                while (solved2.size() > 0) {
                    cmp.getSf().add(solved2.remove(0));
                }

                while (s1.size() > 0 && f2.size() > 0) {
                    if (s1.get(0).getPid() < f2.get(0).getPid()) {
                        s1.remove(0);
                        continue;
                    }

                    if (f2.get(0).getPid() < s1.get(0).getPid()) {
                        f2.remove(0);
                        continue;
                    }
                    cmp.getFaccs().add(s1.remove(0));
                    f2.remove(0);
                }

                while (s2.size() > 0 && f1.size() > 0) {
                    if (s2.get(0).getPid() < f1.get(0).getPid()) {
                        s2.remove(0);
                        continue;
                    }

                    if (f1.get(0).getPid() < s2.get(0).getPid()) {
                        f1.remove(0);
                        continue;
                    }
                    cmp.getSaccf().add(s2.remove(0));
                    f1.remove(0);
                }

                cmp.putCants();
                error = 2;
                model.addAttribute("compare", cmp);
                model.addAttribute("error", error);
                return "/user/compareusers";
            } else {
                /*Mi arreglo*/
            }
        } else if (user1 == null && principal != null) {
            cmp.setUser1(getUsername(principal));
        } else if (user1 != null || user2 != null){
            error = 3;
        }
        model.addAttribute("compare", cmp);
        model.addAttribute("error", error);
        model.addAttribute("errormessage", "general.error.userproblem");
        return "/user/compareusers";
    }

    @RequestMapping(value = "/updateaccount.xhtml", method = RequestMethod.GET)
    public String updateAccount(Principal principal, Model model) {
        User user;
        if (userDAO.bool("is.user", getUsername(principal))) {
            user = userDAO.loadAllUserData(getUsername(principal));

            Integer uid = userDAO.idByUsername(getUsername(principal));
            user.setSitesFollowing(wbSiteDAO.getIdSiteListFollowed(uid));

            model.addAttribute("sites", wbSiteDAO.getSiteList());
        } else {
            user = userDAO.loadAllTeamData(getUsername(principal));
        }
        model.addAttribute("countries", countryDAO.objects("enabled.countries", Country.class));
        model.addAttribute("planguages", utilDAO.getEnabledProgramingLanguages());
        model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
        model.addAttribute("classifications", problemDAO.getClassifications());
        String codifiedProblemClass = userDAO.loadTags(user.getUsername());

        if (codifiedProblemClass != null) {
            int[] preferedIds = new int[codifiedProblemClass.length()];
            int cant = 0;
            for (int i = 0; i < codifiedProblemClass.length(); i++) {
                if (codifiedProblemClass.charAt(i) == '1') {
                    preferedIds[cant++] = i;
                }
            }

            user.setPreferedClassificationsIds(Arrays.copyOf(preferedIds, cant));
        }
        if (user.getCountry_id() != 0) {
            List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(user.getCountry_id());
            institutions.add(0, new Institution(-1, "NONE", "NONE"));
            model.addAttribute("institutions", institutions);
        }
        model.addAttribute("user", user);
        return "/user/updateaccount";
    }

    @RequestMapping(value = "/updateaccount.xhtml", method = RequestMethod.POST)
    public String updateAccount(SecurityContextHolderAwareRequestWrapper requestWrapper, Model model, Principal principal, User user, @RequestParam(value = "avatar", required = false) MultipartFile avatar, BindingResult bindingResult) {

        // si no es admin ni es el mismo usuario logueado el que se quiere
        // cambiar, entonces que no deje hacer nada.
        if (!principal.getName().equals(user.getUsername())) {
            return "error/accessdenied";
        }

        user.setUid(userDAO.integer("select.uid.by.username", user.getUsername()));
        user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
        boolean is_team = !userDAO.bool("is.user", user.getUsername());
        user.setTeam(is_team);
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            Integer uid = userDAO.idByUsername(getUsername(principal));
            user.setSitesFollowing(wbSiteDAO.getIdSiteListFollowed(uid));

            user.setTeam(userDAO.bool("is.team", user.getUsername()));
            user.setUpdate_nick(userDAO.bool("is.update.nick.username", user.getUsername()));
            model.addAttribute("sites", wbSiteDAO.getSiteList());
            model.addAttribute("countries", countryDAO.objects("enabled.countries", Country.class));
            model.addAttribute("planguages", utilDAO.getEnabledProgramingLanguages());
            model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
            model.addAttribute("classifications", problemDAO.getClassifications());
            if (user.getCountry_id() != 0) {
                List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(user.getCountry_id());
                institutions.add(0, new Institution(-1, "NONE", "NONE"));
                model.addAttribute("institutions", institutions);
            }
            model.addAttribute("user", user);
            return "/user/updateaccount";
        }

        if (user.getPreferedClassificationsIds() != null) {
            char[] temp = new char[1000];
            Arrays.fill(temp, '0');
            int max = 0;
            for (int c : user.getPreferedClassificationsIds()) {
                temp[c] = '1';
                max = Math.max(c, max);
            }
            userDAO.updateTags(user.getUsername(), String.copyValueOf(temp, 0, max + 1));
        }

        if (!StringUtils.isEmpty(user.getPassword())) {
            Md5PasswordEncoder md5 = new Md5PasswordEncoder();
            user.setPassword(md5.encodePassword(user.getPassword(), "ABC123XYZ789"));
        }

        userDAO.updateUser(user);
        if (!user.isTeam()) {
            Integer uid = userDAO.idByUsername(getUsername(principal));
            wbSiteDAO.updateSiteListFollowed(uid, user.getSitesFollowing());
        }

        if (avatar != null && !avatar.isEmpty())
            FileUtils.saveToFile(avatar, Config.getProperty("base.upload.dir.avatars"), user.getUsername());

        if (!(user.isSee_solutions() && userDAO.bool("user.seesolution", user.getUid()))) {
            userDAO.updateChangeTime(user.getUid());
        }
        return "redirect:/user/useraccount.xhtml?username=" + user.getUsername();
    }

    @RequestMapping(value = "/createnewaccount.xhtml", method = RequestMethod.GET)
    public String createNewAccount(Model model, HttpServletRequest request) {
        if (request.isUserInRole(Roles.ROLE_USER) || request.isUserInRole(Roles.ROLE_TEAM)) {
            return "redirect:/index.xhtml";
        }
        model.addAttribute("countries", countryDAO.objects("enabled.countries", Country.class));
        model.addAttribute("planguages", utilDAO.getEnabledProgramingLanguages());
        model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
        model.addAttribute("institutions", new LinkedList<Institution>());
        model.addAttribute(new User());
        return "/user/createnewaccount";
    }

    @RequestMapping(value = "/createnewaccount.xhtml", method = RequestMethod.POST)
    public String createNewAccount(Model model, User user, BindingResult bindingResult) {
        user.setDob(new Date(user.getYear() - 1900, user.getMonth() - 1, user.getDay()));
        regValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", countryDAO.objects("enabled.countries", Country.class));
            model.addAttribute("planguages", utilDAO.getEnabledProgramingLanguages());
            model.addAttribute("locales", utilDAO.objects("enabled.locale", Locale.class));
            if (user.getCountry_id() != 0) {
                List<Institution> institutions = institutionDAO.getEnabledInstitutionsByCountry_id(user.getCountry_id());
                institutions.add(0, new Institution(-1, "NONE", "NONE INSTITUTION"));
                model.addAttribute("institutions", institutions);
            }
            return "/user/createnewaccount";
        }
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        user.setPassword(md5.encodePassword(user.getPassword(), "ABC123XYZ789"));
        userDAO.InsertUser(user);
        String token = md5.encodePassword(user.getUsername(), "ABC123XYZ789");
        userDAO.dml("insert.account.activation", user.getUsername(), token, true);

        mailNotificationService.sendAccountVerification(user, token);
        return "redirect:/user/registrationsuccess.xhtml";
    }

    @RequestMapping(value = "/forgottenpassword.xhtml", method = RequestMethod.GET)
    public String forgottenPassword(Model model, Principal principal, @RequestParam(required = false, value = "code") String code) {

        boolean flag = false;
        this.passcode = code;
        User user = null;

        if (principal != null) {
            return "redirect:/index.xhtml";
        }

        if (code != null) {

            Integer uid = userDAO.integer("userid.by.passcode", code);
            String email = userDAO.string("select.email.by.passcode", code);
            flag = uid != null;
            user = userDAO.object("user.by.id", User.class, uid);
            user.setEmail(email);
        } else {
            user = new User();
        }

        model.addAttribute(user);
        model.addAttribute("showpassword", flag);
        return "/user/forgottenpassword";
    }

    @RequestMapping(value = "/forgottenpassword.xhtml", method = RequestMethod.POST)
    public String forgottenPassword(Model model, User user, java.util.Locale locale, BindingResult bindingResult) {
        forgottenValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/user/forgottenpassword";
        }
        int change = 0;
        if (this.passcode != null) {

            ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "password", "judge.register.error.password");
            ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "confirmPassword", "judge.register.error.password");
            if (!StringUtils.isEmpty(user.getPassword()) && !StringUtils.isEmpty(user.getConfirmPassword()) && !(user.getPassword()).equals(user.getConfirmPassword())) {
                bindingResult.rejectValue("confirmPassword", "judge.register.error.confirmpassword");
            }

            if (bindingResult.hasErrors()) {
                // model.addAttribute("code",this.passcode);
                model.addAttribute("showpassword", true);
                return "/user/forgottenpassword";
            }
            try {
                Md5PasswordEncoder md5 = new Md5PasswordEncoder();
                String password = md5.encodePassword(user.getPassword(), "ABC123XYZ789");

                userDAO.dml("update.password.bypasscode", password, this.passcode);
                userDAO.dml("update.passcode", null, user.getEmail());
                this.passcode = null;
                change = 1;
            } catch (Exception e) {
                return "redirect:/error/error.xhtml";
            }
        } else {
            try {
                this.passcode = generateRandomPassword(30);
                userDAO.dml("update.passcode", this.passcode, user.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/error/error.xhtml";
            }
            String subject = messageSource.getMessage("mail.pass.recover.subject", new Object[0], java.util.Locale.ENGLISH);
            String msg = messageSource.getMessage("forgotten.password", new Object[]{this.passcode}, locale);
            mailNotificationService.sendForgottenPasswordEmail(user.getEmail(), subject, msg);
        }
        model.addAttribute(user);
        return "redirect:/user/forgottensuccess.xhtml?change=" + change;
    }

    @RequestMapping(value = "/forgottensuccess.xhtml", method = RequestMethod.GET)
    public String forgottenSuccess(Model model, @RequestParam(required = false, value = "change", defaultValue = "0") Integer change) {
        model.addAttribute("change", change);
        return "/user/forgottensuccess";
    }

    @RequestMapping(value = "/registrationsuccess.xhtml", method = RequestMethod.GET)
    public String registrationSuccess() {

        return "/user/registrationsuccess";
    }

    @RequestMapping(value = "/getInstitution.xhtml", method = RequestMethod.POST)
    public String getInstitutions(Model model, HttpServletRequest request) throws Exception {
        int country_id = new Integer(request.getParameter("country"));
        List<Institution> insts = institutionDAO.getEnabledInstitutionsByCountry_id(country_id);
        JSONArray result = new JSONArray();
        for (Iterator<Institution> it = insts.iterator(); it.hasNext(); ) {
            Institution object = (Institution) it.next();
            JSONObject obj = new JSONObject();
            obj.accumulate("id", object.getId());
            obj.accumulate("institution", object.getName());
            result.add(obj);
        }
        model.addAttribute("institutions", result.toString());
        return "/user/getInstitution";
    }

    private String generateRandomPassword(int length) {
        String password = "";
        for (int i = 0; i < length; i++) {
            Random r = new Random();
            password += (char) ('A' + r.nextInt(26));
        }
        password = password.toLowerCase();
        return password;
    }
}
