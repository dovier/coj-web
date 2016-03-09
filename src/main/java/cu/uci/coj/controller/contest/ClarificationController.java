package cu.uci.coj.controller.contest;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.controller.admin.ProblemController;
import cu.uci.coj.dao.ClarificationDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Clarification;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;
import cu.uci.coj.validator.clarificationValidator;
import cu.uci.coj.validator.sendClarificationValidator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cu.uci.coj.utils.Notification;


@Controller
@RequestMapping(value = "/contest")
public class ClarificationController extends BaseController {

    @Resource
    private ClarificationDAO clarificationDAO;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private clarificationValidator validator;
    @Resource
    private sendClarificationValidator sendValidator;

    /*
    * RF128 Listar aclaraciones del concurso
    * */
    @RequestMapping(value = "/myclarifications.xhtml", method = RequestMethod.GET)
    public String ClarificationsList(Model model, Principal principal, @RequestParam("cid") Integer cid, @RequestParam(required = false, value = "pattern") String pattern) {
        String username = getUsername(principal);
        int uid = -1;
        if (username != null) {
            uid = clarificationDAO.integer("select.uid.by.username", username);
        }

        Integer judgeId = contestDAO.integer("select.uid.by.username", getUsername(principal));
        model.addAttribute("isContestJudge", clarificationDAO.bool("is.judge.in.contest", judgeId, cid));

        List<Clarification> clarifications;
        if (uid != -1) {
            clarifications = clarificationDAO.getMyClarifications(cid, uid, pattern);
        } else {
            clarifications = clarificationDAO.getPublicClarifications(cid);
        }
        model.addAttribute("clarifications", clarifications);
        model.addAttribute("contest", contestDAO.loadContest(cid));
        model.addAttribute("pattern", pattern);
        return "/contest/myclarifications";
    }

    /*
    * RF129 Mostrar aclaración del concurso
    * */
    @RequestMapping(value = "/clarificationview.xhtml", method = RequestMethod.GET)
    public String ClarificationView(Model model, Principal principal, @RequestParam("cid") Integer cid, @RequestParam("ccid") Integer ccid) {
        int uid = 0;
        if (principal != null) {
            uid = clarificationDAO.integer("select.uid.by.username", principal.getName());
        }
        Clarification clarification;
        if (uid > 0) {
            clarification = clarificationDAO.getClarification(cid, ccid, uid);
        } else {
            clarification = clarificationDAO.getPublicClarification(cid, ccid);
        }
        boolean see = clarification != null ? clarification.isPublics() : false;
        if (!see && uid != 0) {
            if (clarificationDAO.bool("exists.team.clarification", clarification.getId()) || userDAO.isJudgeInContest(uid, cid) || (userDAO.isInContest(uid, cid) && clarification.isForall())) {
                see = true;
            }
        }
        model.addAttribute("clarification", clarification);
        model.addAttribute("available", see);
        model.addAttribute("contest", contestDAO.loadContest(cid));
        return "/contest/clarificationview";
    }

    /*
    * RF7 Solicitar aclaración para el concurso
    * */
    @RequestMapping(value = "/clarification.xhtml", method = RequestMethod.GET)
    public String RequestClarification(Locale locale, Model model, @RequestParam("cid") Integer cid, @RequestParam(defaultValue = "-1", required = false, value = "ccid") Integer ccid) {
        List<Problem> problems = new LinkedList<Problem>();
        problems.add(new Problem(0, "General"));
        problems.addAll(problemDAO.getContestProblems(locale.getLanguage(), cid));
        Contest contest = contestDAO.loadContest(cid);
        if (contest.getStyle() == 1) {
            for (int i = 1; i < problems.size(); i++) {
                problems.get(i).setLetter(i - 1);
                problems.get(i).setTitle(problems.get(i).getLetter() + " - " + problems.get(i).getTitle());
            }
        }
        model.addAttribute("problems", problems);
        model.addAttribute("contest", contestDAO.loadContest(cid));
        Clarification clarification = new Clarification();
        clarification.setCid(cid);
        model.addAttribute(clarification);
        return "/contest/clarification";
    }

    @RequestMapping(value = "/clarification.xhtml", method = RequestMethod.POST)
    public String RequestClarification(Locale locale, Model model, Principal principal, Clarification clarification, BindingResult result) {
        clarification.setTitle("");
        clarification.setUsername(getUsername(principal));
        validator.validate(clarification, result);
        if (result.hasErrors()) {
            List<Problem> problems = new LinkedList<Problem>();
            problems.add(new Problem(0, "General"));
            problems.addAll(problemDAO.getContestProblems(locale.getLanguage(), clarification.getCid()));
            model.addAttribute("problems", problems);
            model.addAttribute("contest", contestDAO.loadContest(clarification.getCid()));
            model.addAttribute(clarification);
            return "/contest/clarification";
        }
        clarification.setIdteam(userDAO.integer("select.uid.by.username", principal.getName()));
        clarificationDAO.insertClarification(clarification);
        return "redirect:/contest/myclarifications.xhtml?cid=" + clarification.getCid();
    }

    /*
    * RF83 Enviar aclaración para el concurso
    * RF93 Responder aclaración del concurso
    * */
    @RequestMapping(value = "/sendclarification.xhtml", method = RequestMethod.GET)
    public String SendClarification(Locale locale, Model model, Principal principal, @RequestParam(required = false, defaultValue = "", value = "uid") String uid, @RequestParam("cid") Integer cid,
                                    @RequestParam(defaultValue = "-1", required = false, value = "ccid") Integer ccid, @RequestParam(defaultValue = "0", required = false, value = "pid") Integer pid) {

        Integer judgeId = contestDAO.integer("select.uid.by.username", getUsername(principal));
        if (!clarificationDAO.bool("is.judge.in.contest", judgeId, cid))
            return "/error/accessdenied";

        List<Problem> problems = new LinkedList<Problem>();
        problems.add(new Problem(0, "General"));
        problems.addAll(problemDAO.getContestProblems(locale.getLanguage(), cid));
        if (contestDAO.getStyle(cid) == Contest.ACM_ICPC_STYLE) {
            for (int i = 1; i < problems.size(); i++) {
                problems.get(i).setLetter(i - 1);
                problems.get(i).setTitle(problems.get(i).getLetter() + " - " + problems.get(i).getTitle());
            }
        }
        model.addAttribute("problems", problems);
        model.addAttribute("contest", contestDAO.loadContest(cid));
        Clarification clarification = new Clarification();
        clarification.setId(ccid);
        clarification.setPid(pid);
        if (ccid != -1) {
            clarification.setOriginalMSG(getOriginalMSG(ccid));
        }
        clarification.setCid(Integer.valueOf(cid));
        clarification.setUsername(getUsername(principal));
        clarification.setUsernameto(uid);
        clarification.setCid(cid);
        model.addAttribute(clarification);
        return "/contest/sendclarification";
    }

    public String getOriginalMSG(int ccid) {
        Map<String, Object> mapa = clarificationDAO.map("original.msg", new Object[]{ccid});
        String originaMSG = null;
        try {
            originaMSG = "<br/><br/>----- Original Message ----- <br/> From: " + mapa.get("username").toString() + "<br/>To: Judges <br/>Received: " + mapa.get("date").toString().substring(0, 19)
                    + "<br/>Subject: " + new String(mapa.get("title").toString().getBytes("8859_1"), "UTF8") + "<br/><br/>" + new String(mapa.get("text").toString().getBytes("8859_1"), "UTF8");
        } catch (UnsupportedEncodingException ex) {
        }
        return originaMSG;
    }

    /*
   * RF83 Enviar aclaración para el concurso
   * RF93 Responder aclaración del concurso
   * */
    @RequestMapping(value = "/sendclarification.xhtml", method = RequestMethod.POST)
    public String SendClarification(Locale locale, Model model, Principal principal, Clarification clarification, BindingResult result, @RequestParam("modes") Integer mode, RedirectAttributes redirectAttributes) {

        clarification.setTitle("");
        switch (mode) {
            case 1: {
                clarification.setDescription("NO COMMENTS");
                break;
            }
            case 2: {
                clarification.setDescription("YES");
                break;
            }
            case 3: {
                clarification.setDescription("NO");
                break;
            }
        }
        sendValidator.validate(clarification, result);
        if (result.hasErrors()) {
            List<Problem> problems = new LinkedList<Problem>();
            problems.add(new Problem(0, "General"));
            problems.addAll(problemDAO.getContestProblems(locale.getLanguage(), clarification.getCid()));
            model.addAttribute("problems", problems);
            model.addAttribute("contest", contestDAO.loadContest(clarification.getCid()));
            model.addAttribute(clarification);
            return "/contest/sendclarification";
        }
        if (clarification.getOriginalMSG() == null) {
            clarification.setOriginalMSG("");
        }

        clarification.setIdteam(userDAO.integer("select.uid.by.username", getUsername(principal)));
        clarification.setDescription(clarification.getDescription() + "<br/><br/>" + clarification.getOriginalMSG());


        clarificationDAO.insertClarification(clarification);
        if (clarification.getId() != 0) {
            clarificationDAO.updateReplyClarification(clarification.getId());
        }
        List<String> users = new LinkedList<String>();
        if (clarification.isForall() || clarification.isPublics()) {
            users.addAll(userDAO.getUserInContest(clarification.getCid()));
        }
        String[] list = clarification.getUsernameto().split(";");
        for (int i = 0; i < list.length; i++) {
            String string = list[i];
            if (string != null && !string.isEmpty()) {
                users.add(string);
            }
        }
        Map<String, String> sended = new HashMap<String, String>();
        int ccid = clarificationDAO.integer("max.id.clarification", clarification.getIdteam());
        int uid = 0;
        for (int i = 0; i < users.size(); i++) {
            String string = users.get(i);


            if (userDAO.integer("select.uid.by.username", string) == null) {
                result.rejectValue("usernameto", "sendclarification.error.usernotexist");
                List<Problem> problems = new LinkedList<Problem>();
                problems.add(new Problem(0, "General"));
                problems.addAll(problemDAO.getContestProblems(locale.getLanguage(), clarification.getCid()));
                model.addAttribute("problems", problems);
                model.addAttribute("contest", contestDAO.loadContest(clarification.getCid()));
                model.addAttribute(clarification);
                return "/contest/sendclarification";
            }

            if (!sended.containsKey(string) && (uid = userDAO.integer("select.uid.by.username", string)) != -1) {
                if (clarification.getId() > 0) {
                    clarificationDAO.dml("delete.user.clarification", uid, clarification.getId());
                    clarificationDAO.dml("delete.user.clarification", uid, ccid);
                }
                sended.put(string, string);
                clarificationDAO.dml("insert.user.clarification", ccid, uid);
            }
        }

        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
        return "redirect:/contest/myclarifications.xhtml?cid=" + clarification.getCid();
    }

    /*
    * RF130 Marcar aclaración del concurso como respondido
    * */
    @RequestMapping(value = "/markanswered.xhtml", method = RequestMethod.GET)
    public String markAnswered(Model model, Principal principal, @RequestParam("cid") Integer cid, @RequestParam("ccid") Integer ccid) {
        clarificationDAO.dml("update.clarification.read", ccid, clarificationDAO.integer("select.uid.by.username", getUsername(principal)));
        return "redirect:/contest/myclarifications.xhtml?cid=" + cid;
    }
}
