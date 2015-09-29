package cu.uci.coj.controller.admin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestAwardDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.ContestAwardsFlags;
import cu.uci.coj.model.Level;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Rejudge;
import cu.uci.coj.model.RepointUser;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.User;
import cu.uci.coj.model.VirtualContest;
import cu.uci.coj.service.ContestService;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.contestValidator;

@Controller("AdminContestController")
@RequestMapping(value = "/admin")
public class ContestController extends BaseController {

    @Resource
    private Utils utils;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private ContestAwardDAO contestAwardDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private contestValidator validator;
    @Resource
    private MailNotificationService mailNotificationService;
    @Resource
    private ContestService contestService;

    @RequestMapping(value = "/virtualcontests.xhtml", method = RequestMethod.GET)
    public String listVirtuals(Model model, PagingOptions options) {

        return "/admin/virtualcontests";
    }

    @RequestMapping(value = "/tables/virtualcontests.xhtml", method = RequestMethod.GET)
    public String tablesListVirtuals(Model model, PagingOptions options) {

        IPaginatedList<VirtualContest> contests = contestDAO
                .loadVirtualContests(options);
        model.addAttribute("contests", contests);
        model.addAttribute("found", contests.getFullListSize());
        return "/admin/tables/virtualcontests";
    }

    @RequestMapping(value = "/deletevirtual.xhtml", method = RequestMethod.GET)
    public String deleteVirtual(Model model, @RequestParam Integer vid,
            @RequestParam("uid") String username) {
        contestDAO.deleteVirtualContest(userDAO.idByUsername(username));
        return "redirect:/admin/virtualcontests.xhtml";
    }

    @RequestMapping(value = "/admincontests.xhtml", method = RequestMethod.GET)
    public String listContests(Model model, PagingOptions options) {
        return "/admin/admincontests";
    }

    @RequestMapping(value = "/tables/admincontests.xhtml", method = RequestMethod.GET)
    public String tablesListContests(Model model, PagingOptions options, @RequestParam(required = false) String access,
            @RequestParam(required = false) String enabled, @RequestParam(required = false, defaultValue = "all") String status) {
        IPaginatedList<Contest> contests = contestDAO.loadContests(options, access, enabled, status);
        model.addAttribute("contests", contests);
        return "/admin/tables/admincontests";
    }

    @RequestMapping(value = "/managecontest.xhtml", method = RequestMethod.GET)
    public String manageContest(Model model, @RequestParam Integer cid) {
        Contest contest = contestDAO.loadContestManage(cid);
        model.addAttribute("styles", contestDAO.loadEnabledScoringStyles());
        model.addAttribute(contest);
        return "/admin/managecontest";
    }

    @RequestMapping(value = "/managecontest.xhtml", method = RequestMethod.POST)
    public String manageContest(Model model, Contest contest,
            BindingResult result) {
        validator.manageContest(contest, result);
        if (result.hasErrors()) {
            model.addAttribute("styles", contestDAO.loadEnabledScoringStyles());
            model.addAttribute(contest);
            List<ObjectError> err = result.getAllErrors();
            return "/admin/managecontest";
        }
        contestDAO.updateContestManage(contest);
        return "redirect:/admin/managecontest.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(value = "/createcontest.xhtml", method = RequestMethod.GET)
    public String createContest(Model model) {
        Contest contest = new Contest();
        if (contest.getCid() == 0) {
            contest.setCid(contestDAO.integer("max.available.cid") + 1);
        }
        model.addAttribute("contests", contestDAO.loadContestToImport());
        model.addAttribute(contest);
        return "/admin/createcontest";
    }

    @RequestMapping(value = "/createcontest.xhtml", method = RequestMethod.POST)
    public String createContest(Model model, Contest contest,
            BindingResult result) {
        validator.validate(contest, result);
        if (result.hasErrors()) {
            model.addAttribute("contests", contestDAO.loadContestToImport());
            model.addAttribute(contest);
            return "/admin/createcontest";
        }
        contestDAO.InsertContest(contest);

        if (contest.isIs_public()) {

        }
        if (contest.getImportCid() != 0) {
            contestDAO.importContestData(contest);
        }
        return "redirect:/admin/managecontest.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(value = "/globalsettings.xhtml", method = RequestMethod.GET)
    public String globalSettings(Model model, @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.loadContestGlobalSettings(cid);
        model.addAttribute("style", contestDAO.loadScoringStyle(cid));
        model.addAttribute(contest);
        return "/admin/globalsettings";
    }

    @RequestMapping(value = "/globalsettings.xhtml", method = RequestMethod.POST)
    public String globalSettings(Model model, Contest contest,
            BindingResult result) {
        validator.validateGlobalSettings(contest, result);
        if (result.hasErrors()) {
            model.addAttribute(contest);
            return "/admin/globalsettings";
        }

        contestDAO.updateContestGlobalSettings(contest);
        return "redirect:/admin/globalsettings.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(produces = "application/json", value = "/removeimage.json", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDataset(
            @RequestParam(value = "cid") int cid,
            @RequestParam(value = "image") String image) {

        utils.removeImage(cid, image);
    }

    @RequestMapping(value = "/contestimg.xhtml", method = RequestMethod.GET)
    public String contestImages(Model model, Principal principal,
            SecurityContextHolderAwareRequestWrapper requestWrapper,
            @RequestParam(value = "cid") Integer cid) {
        return doContestImages(model, principal, requestWrapper, cid);
    }

    public String doContestImages(Model model, Principal principal,
            SecurityContextHolderAwareRequestWrapper requestWrapper,
            @RequestParam(value = "cid") Integer cid) {
        Contest contest = new Contest();
        contest.setCid(cid);
        model.addAttribute("contest", contest);
        model.addAttribute("images", utils.getContestImages(cid));
        return "/admin/contestimg";
    }

    @RequestMapping(value = "/contestimg.xhtml", method = RequestMethod.POST)
    public String contestImages(Model model,
            SecurityContextHolderAwareRequestWrapper requestWrapper,
            Principal principal, Contest contest, HttpServletRequest request,
            BindingResult result) {

        handleFiles(contest.getCid(), request);

        return doContestImages(model, principal, requestWrapper,
                contest.getCid());
    }

    private void handleFiles(Integer cid, HttpServletRequest request) {
        Assert.state(request instanceof MultipartHttpServletRequest,
                "request !instanceof MultipartHttpServletRequest");
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map<String, MultipartFile> files = multiRequest.getFileMap();

        boolean mkdirs = true;
        File dir = new File(Config.getProperty("base.contest.images"),
                String.valueOf(cid));
        dir.mkdirs();
        for (MultipartFile filex : files.values()) {
            if (filex.getOriginalFilename() != null
                    && !filex.getOriginalFilename().equals("")
                    && filex.getSize() > 0) {
                String filename = filex.getOriginalFilename();
                File file = new File(dir, filename);
                if (file.exists() && !file.isDirectory()) {
                    file.delete();
                }
                if (mkdirs) {
                    file.mkdirs();
                    mkdirs = false;
                }
                try {
                    filex.transferTo(file);
                    String ext = filex.getOriginalFilename().substring(
                            filex.getOriginalFilename().lastIndexOf("."));
                    if (ext.equals(".zip") || ext.equals(".rar")) {
                        FileUtils.decompressFile(file.getAbsolutePath(),
                                dir.getAbsolutePath(), true);
                    }
                } catch (IOException | IllegalStateException ex) {
                    Logger.getLogger(ProblemController.class.getName()).log(
                            java.util.logging.Level.SEVERE, null, ex);
                }
            }
        }
    }

    @RequestMapping(value = "/contestproblems.xhtml", method = RequestMethod.GET)
    public String contestProblems(Model model, Locale locale,
            @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.loadContestForProblems(cid);
        List<Problem> problems = contestDAO.loadContestProblems(contest
                .getCid());
        HashMap<Integer, Level> levelT = new HashMap<Integer, Level>();
        if (contest.getStyle() == 4) {
            for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
                Problem problem = it.next();
                if (levelT.containsKey(problem.getLevel())) {
                    levelT.get(problem.getLevel()).getProblems().add(problem);
                } else {
                    Level lv = new Level(problem.getLevel(),
                            new LinkedList<Problem>());
                    lv.getProblems().add(problem);
                    levelT.put(problem.getLevel(), lv);
                }
            }
        }

        switch (contest.getStyle()) {
            case 4: {
                LinkedList<Level> levels = new LinkedList<Level>();
                Set<Integer> set = levelT.keySet();
                for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
                    Integer integer = it.next();
                    levels.add(levelT.get(integer));
                }
                contest.setPlevels(levels);
                break;
            }
            default: {
                contest.setProblems(problems);
                break;
            }
        }
        model.addAttribute(contest);
        model.addAttribute(
                "problems",
                problemDAO.getProblemsOffContest(locale.getLanguage(),
                        contest.getCid()));
        List<Integer> integers = new LinkedList<Integer>();
        int levels = contestDAO.integer(1, "contest.level", contest.getCid());
        for (int i = 0; i < levels; i++) {
            integers.add(i + 1);
        }
        model.addAttribute("levels", integers);
        return "/admin/contestproblems";
    }

    @RequestMapping(value = "/contestproblems.xhtml", method = RequestMethod.POST)
    public String contestProblems(Model model, Locale locale, Contest contest,
            BindingResult result) {
        contest.setStyle(contestDAO.loadScoringStyle(contest.getCid()).getSid());
        validator.contestProblems(contest, result);
        if (result.hasErrors()) {
            List<Problem> problems = contestDAO.loadContestProblems(contest
                    .getCid());
            HashMap<Integer, Level> levelT = new HashMap<Integer, Level>();
            if (contest.getStyle() == 4) {
                for (Iterator<Problem> it = problems.iterator(); it.hasNext();) {
                    Problem problem = it.next();
                    if (levelT.containsKey(problem.getLevel())) {
                        levelT.get(problem.getLevel()).getProblems()
                                .add(problem);
                    } else {
                        Level lv = new Level(problem.getLevel(),
                                new LinkedList<Problem>());
                        lv.getProblems().add(problem);
                        levelT.put(problem.getLevel(), lv);
                    }

                }
            }

            switch (contest.getStyle()) {
                case 4: {
                    LinkedList<Level> levels = new LinkedList<Level>();
                    Set<Integer> set = levelT.keySet();
                    for (Iterator<Integer> it = set.iterator(); it.hasNext();) {
                        Integer integer = it.next();
                        levels.add(levelT.get(integer));
                    }
                    contest.setPlevels(levels);
                    break;
                }
                default: {
                    contest.setProblems(problems);
                    break;
                }
            }
            model.addAttribute(contest);
            model.addAttribute("problems", problemDAO.getProblemsOffContest(
                    locale.getLanguage(), contest.getCid()));
            List<Integer> integers = new LinkedList<Integer>();
            int levels = contestDAO.integer(1, "contest.level",
                    contest.getCid());
            for (int i = 0; i < levels; i++) {
                integers.add(i + 1);
            }
            model.addAttribute("levels", integers);
            return "/admin/contestproblems";
        }
        if (contest.getStyle() != 4) {
            try {
                problemDAO.clearProblemsContest(contest.getCid());
            } catch (Exception e) {
            }
        }
        if (contest.getProblemids().length != 0) {
            try {
                for (int i = 0; i < contest.getProblemids().length; i++) {
                    contestDAO.insertProblemContest(contest.getProblemids()[i],
                            contest.getCid(), contest.getLevels(), i + 1);
                }
            } catch (Exception e) {
            }
        }
        return "redirect:/admin/contestproblems.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(value = "/importicpcusers.xhtml", method = RequestMethod.GET)
    public String importICPCUsers(Model model, @RequestParam Integer cid) {
        model.addAttribute("cid", cid);
        return "/admin/importicpcusers";
    }

    @RequestMapping(value = "/importicpcusers.xhtml", method = RequestMethod.POST)
    public @ResponseBody
    byte[] importICPCUsers(
            Model model,
            HttpServletResponse response,
            Contest contest,
            Locale locale,
            @RequestParam("cid") Integer cid,
            @RequestParam(value = "warmupCid", required = false) Integer warmupCid,
            @RequestParam("prefix") String prefix,
            @RequestParam MultipartFile personFile,
            @RequestParam MultipartFile schoolFile,
            @RequestParam MultipartFile siteFile,
            @RequestParam MultipartFile teamFile,
            @RequestParam MultipartFile teamPersonFile) throws IOException {

        List<String> messages = contestService.importICPCUsers(prefix,
                new String(personFile.getBytes()).split("\r\n"), new String(
                        schoolFile.getBytes(), "UTF-8").split("\r\n"),
                new String(siteFile.getBytes(), "UTF-8").split("\r\n"),
                new String(teamFile.getBytes(), "UTF-8").split("\r\n"),
                new String(teamPersonFile.getBytes(), "UTF-8").split("\r\n"),
                cid, warmupCid);

        StringBuilder builder = new StringBuilder();
        for (String msg : messages) {
            builder.append(msg);
            builder.append("\n");
        }

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + prefix + "users");
        return builder.toString().getBytes("UTF-8");
    }

    @RequestMapping(value = "/contestproblemcolors.xhtml", method = RequestMethod.GET)
    public String contestProblemColors(Model model, Locale locale,
            @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.loadContestForProblems(cid);
        List<Problem> problems = contestDAO.loadContestProblems(contest
                .getCid());
        contest.setProblems(problems);
        model.addAttribute(contest);
        model.addAttribute("problems", problems);
        return "/admin/contestproblemcolors";
    }

    @RequestMapping(value = "/baylorxml.xhtml", method = RequestMethod.GET)
    public String baylorxml(Model model, Locale locale,
            @RequestParam("cid") Integer cid) {
        model.addAttribute("cid", cid);
        return "/admin/baylorxml";
    }

    @RequestMapping(value = "/baylorxml.xhtml", method = RequestMethod.POST)
    public @ResponseBody
    byte[] baylorXml(Model model, Locale locale,
            HttpServletResponse response, MultipartFile xml,
            @RequestParam("cid") Integer cid) throws IOException {
        List<Map<String, Object>> maps = contestDAO.baylorXMLData(cid);

        maps.get(0).put("rank", 1);
        Map<String, Object> cMap = maps.get(0);
        Integer zeroRank = null;
        for (int i = 1; i < maps.size(); i++) {
            // triple empate, se queda el mismo ranking que la posicion
            // anterior, pero se adelanta igual (ej, A y B son iguales, 1er
            // lugar para los dos, C que viene detras, tiene el rango 3. Se
            // brinca el 2, desierto)
            if (maps.get(i).get("accepted").equals(0)) {
                if (zeroRank == null) {
                    zeroRank = i + 1;
                }
                maps.get(i).put("rank", zeroRank);
            } else if (cMap.get("accepted").equals(maps.get(i).get("accepted"))
                    && cMap.get("penalty").equals(maps.get(i).get("penalty"))
                    && cMap.get("last_time").equals(
                            maps.get(i).get("last_time"))) {
                maps.get(i).put("rank", cMap.get("rank"));
            } else {
                maps.get(i).put("rank", i + 1);
            }

            cMap = maps.get(i);
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                xml.getInputStream(), "UTF-8"));
        StringBuilder builder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            int i = 0;
            while (i < maps.size()
                    && !(line.contains("TeamName=\""
                            + HtmlUtils.htmlEscape(maps.get(i).get("nick")
                                    .toString()) + "\"") || line
                    .contains("TeamName=\""
                            + maps.get(i).get("nick").toString()
                            + "\""))) {
                i++;
            }

            if (i < maps.size()) {
                if (line.contains("gBa")) {
                    System.out.println(line);
                }
                line = line.replace("TotalTime=\"0\"", "TotalTime=\""
                        + maps.get(i).get("penalty") + "\"");
                line = line.replace("Rank=\"\"",
                        "Rank=\"" + maps.get(i).get("rank") + "\"");
                line = line.replace("ProblemsSolved=\"0\"", "ProblemsSolved=\""
                        + maps.get(i).get("accepted") + "\"");
                line = line.replace("LastProblemTime=\"0\"",
                        "LastProblemTime=\"" + maps.get(i).get("last_time")
                        + "\"");
                line = line + "\n";
            }
            builder.append(line);
        }

        response.setHeader("Content-Disposition", "attachment; filename=\""
                + xml.getOriginalFilename());
        return builder.toString().getBytes("UTF-8");
    }

    @RequestMapping(value = "/contestproblemcolors.xhtml", method = RequestMethod.POST)
    public String contestProblemColors(Model model, Locale locale,
            String[] colors, Contest contest) {

        int idx = 0;
        List<Problem> problems = contestDAO.loadContestProblems(contest
                .getCid());
        for (Problem problem : problems) {
            contestDAO.insertProblemColor(problem.getPid(), contest.getCid(),
                    colors[idx++]);
        }
        return "redirect:/admin/contestproblemcolors.xhtml?cid="
                + contest.getCid();
    }

    @RequestMapping(value = "/contestlanguages.xhtml", method = RequestMethod.GET)
    public String contestLanguages(Model model, @RequestParam("cid") Integer cid) {

        Contest contest = new Contest(cid);
        contest.setStyle(contestDAO.getStyle(cid));
        model.addAttribute(contest);
        contest.setLanguages(contestDAO.getContestLanguages(contest.getCid()));
        contest.initCurrlang();
        model.addAttribute("languages", utilDAO.getEnabledProgramingLanguages());

        return "/admin/contestlanguages";
    }

    @RequestMapping(value = "/contestlanguages.xhtml", method = RequestMethod.POST)
    public String contestLanguages(Model model, Contest contest) {

        contestDAO
                .insertLanguages(contest.getCid(), contest.getCurrlanguages());

        return "redirect:/admin/contestlanguages.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(value = "/contestusers.xhtml", method = RequestMethod.GET)
    public String contestUsers(Model model, @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.object("contest.manage.users",
                Contest.class, cid);
        contest.setUsers(userDAO.loadContestUsers(contest.getCid()));
        contest.setBalloontrackers(userDAO.loadContestBalloonTrackers(contest
                .getCid()));
        contest.setJudges(userDAO.loadContestJudges(contest.getCid()));
        model.addAttribute(contest);
        model.addAttribute("allusers", userDAO.loadUsersOffContest(contest));
        model.addAttribute("btusers", userDAO.loadBalloonUsersOffContest(contest));
        model.addAttribute("alljudges",
                userDAO.loadJudgesOffContest(contest.getCid()));
        return "/admin/contestusers";
    }

    @RequestMapping(value = "/contestawards.xhtml", method = RequestMethod.GET)
    public String contestAwards(Model model, @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.object("contest.manage.users",
                Contest.class, cid);
        ContestAwardsFlags flags = contestAwardDAO.loadContestAwardsFlags(cid);
        model.addAttribute(contest);
        model.addAttribute("contestawardsflags", flags);
        return "/admin/contestawards";
    }

    @RequestMapping(value = "/contestawards.xhtml", method = RequestMethod.POST)
    public String contestAwards(Model model,
            ContestAwardsFlags contestawardsflags) {
        contestAwardDAO.insertContestAwardsFlags(contestawardsflags);

        return contestAwards(model, contestawardsflags.getCid());
    }

    @RequestMapping(value = "/contestusers.xhtml", method = RequestMethod.POST)
    public String contestUsers(Model model, Contest contest) {
        if (StringUtils.isEmpty(contest.getGroupd())) {
            contest.setGroupd("Overall");
        }

        contestDAO.guestGroup(contest);

        if (contest.getUsersids() != null) {
            contestDAO.insertUsersContest(contest);
        }

        if (contest.getBalloontrackerids() != null) {
            contestDAO.insertBalloonTrackerContest(contest);
        }

        if (contest.getJudgesids() != null) {
            contestDAO.dml("clear.contest.judges", contest.getCid());
            contestDAO.insertJudgesContest(contest);
        }
        return "redirect:/admin/contestusers.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(value = "/deleteusercontest.xhtml", method = RequestMethod.GET)
    public String deleteContestUsers(Model model,
            @RequestParam("cid") Integer cid,
            @RequestParam(required = false, value = "all") String all,
            @RequestParam(required = false, value = "uid") Integer uid) {
        if (all != null) {
            contestDAO.clearUsersContest(cid);
        } else {
            contestDAO.deleteUserContest(cid, new Integer(uid));
        }
        return "redirect:/admin/contestusers.xhtml?cid=" + cid;
    }

    @RequestMapping(value = "/contestoverview.xhtml", method = RequestMethod.GET)
    public String contestOverview(Model model, @RequestParam("cid") Integer cid) {
        Contest contest = contestDAO.getContestForOverview(cid);
        model.addAttribute(contest);
        return "/admin/contestoverview";
    }

    @RequestMapping(value = "/contestoverview.xhtml", method = RequestMethod.POST)
    public String contestOverview(Model model, Contest contest) {
        contestDAO.dml("update.contest.overview", contest.getOverview(),
                contest.getCid());
        return "redirect:/admin/contestoverview.xhtml?cid=" + contest.getCid();
    }

    @RequestMapping(value = "/repoint.xhtml", method = RequestMethod.GET)
    public String repointContest(Model model, @RequestParam("cid") Integer cid)
            throws IOException {

        Contest contest = contestDAO.loadContest(cid);
        contestDAO.repointContest(contest, contest.isFrozen());
        // si es progresivo
        // FIXME cuando se pase el repoint del motor al coj, analizar si esto
        // sigue teniendo sentido
        if (contest.getStyle() == 4) {
            int levels = contestDAO.integer(1, "contest.level",
                    contest.getCid());
            List<User> users = contestDAO.objects("correct.user.level",
                    User.class, cid);
            Set<Integer> usersFull = new HashSet<Integer>(contestDAO.integers(
                    "correct.user.top.level", cid));
            for (int i = 0; i < users.size(); i++) {
                int maxLevel = contestDAO.integer("max.level.pass.by.uid", cid,
                        users.get(i).getUid());
                contestDAO
                        .dml("update.user.level",
                                (usersFull.contains(users.get(i).getUid()) || maxLevel > levels) ? levels
                                : (maxLevel + 1),
                                users.get(i).getUid(), cid);
            }
        }
        return "redirect:/contest/cscoreboard.xhtml?cid=" + cid;
    }

    @RequestMapping(value = "/unlock.xhtml", method = RequestMethod.GET)
    public String unfreezeContest(Model model,
            SecurityContextHolderAwareRequestWrapper requestWrapper,
            @RequestParam("cid") Integer cid) throws IOException {
        Contest contest = contestDAO.loadContest(cid);
        if (requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && contest.isPast()) {
            // se cambian las banderas block y unfreeze auto del contest a
            // valores complementarios
            contestDAO.dml("update.freeze.blocked.contest", false, true, cid);
            contestDAO.repointContest(contest, false);
        }
        return "redirect:/contest/cscoreboard.xhtml?cid=" + cid;
    }

    @RequestMapping(value = "/lock.xhtml", method = RequestMethod.GET)
    public String freezeContest(Model model,
            SecurityContextHolderAwareRequestWrapper requestWrapper,
            @RequestParam("cid") Integer cid) throws IOException {
        Contest contest = contestDAO.loadContest(cid);
        if (requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && contest.isPast()) {
            // se cambian las banderas block y unfreeze auto del contest a
            // valores complementarios
            contestDAO.freezeContest(contest);
        }
        return "redirect:/contest/cscoreboard.xhtml?cid=" + cid;
    }

    @RequestMapping(value = "/removeproblemcontest.xhtml", method = RequestMethod.GET)
    public String removeContestProblem(Model model,
            @RequestParam("cid") Integer cid, @RequestParam("pid") Integer pid) {
        contestDAO.removeProblemContest(cid, pid);
        return "redirect:/admin/contestproblems.xhtml?cid=" + cid;
    }

    @RequestMapping(value = "/contestrejudge.xhtml", method = RequestMethod.GET)
    public String rejudgeContest(Model model, @RequestParam("cid") String cid)
            throws IOException {
        Rejudge rejudge = new Rejudge(cid);
        Contest contest = contestDAO.loadContest(new Integer(cid));
        model.addAttribute("problems",
                contestDAO.loadContestProblems(new Integer(cid)));
        model.addAttribute(rejudge);
        model.addAttribute(contest);
        return "/admin/contestrejudge";
    }

}
