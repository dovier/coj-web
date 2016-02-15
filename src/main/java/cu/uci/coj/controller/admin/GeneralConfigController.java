package cu.uci.coj.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cu.uci.coj.model.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.security.COJSessionRegistryImpl;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.HandlerInterceptorImpl;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.utils.Notification;

@Controller
@RequestMapping(value = "/admin")
public class GeneralConfigController extends BaseController {

    @Resource
    private BaseDAO baseDAO;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private COJSessionRegistryImpl cojSessionRegistryImpl;
    @Resource
    private MailNotificationService mailNotificationService;

    @PostConstruct
    public void init() {
        GlobalFlags flags = baseDAO.object("load.global.flags", GlobalFlags.class);
        HandlerInterceptorImpl.setMaintenanceMode(flags.isMaintenanceMode());
        mailNotificationService.setDisabledMailNotificationFlag(flags.isMailNotificationDisabled());
    }

    @RequestMapping(value = "/listlog.xhtml", method = RequestMethod.GET)
    public String listLogs(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {

        model.addAttribute("pattern", pattern);
        return "/admin/listlog";
    }

    @RequestMapping(value = "/tables/listlog.xhtml", method = RequestMethod.GET)
    public String tabselListLogs(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {

        IPaginatedList<cu.uci.coj.model.Log> logs = null;
        if (pattern != null) {
            logs = baseDAO.paginated("load.logs.pattern", cu.uci.coj.model.Log.class, 30, options, "%" + pattern + "%");
        } else {
            logs = baseDAO.paginated("load.logs", cu.uci.coj.model.Log.class, 30, options);
        }

        model.addAttribute("logs", logs);
        model.addAttribute("found", logs.getFullListSize());
        return "/admin/tables/listlog";
    }

    @RequestMapping(value = "/globalaccessrules.xhtml", method = RequestMethod.GET)
    public String globalAccessRules(Model model) {
        model.addAttribute(new Rule());
        return "/admin/globalaccessrules";
    }

    @RequestMapping(value = "/tables/globalaccessrules.xhtml", method = RequestMethod.GET)
    public String tableGlobalAccessRules(Model model) {
        //List<Rule> rules = baseDAO.objects("load.access.rules", Rule.class);
        model.addAttribute("rules", baseDAO.objects("load.access.rules", Rule.class));
        return "/admin/tables/globalaccessrules";
    }

    @RequestMapping(value = "/globalaccessrules.xhtml", method = RequestMethod.POST)
    public String globalAccessRules(Model model, Rule rule, BindingResult result, RedirectAttributes redirectAttributes) {
        if (!IpAddress.validate(rule.getRule())) {
            result.rejectValue("rule", "errormsg.36");
            model.addAttribute("rules", baseDAO.objects("load.access.rules", Rule.class));
            return "/admin/globalaccessrules";
        }
        baseDAO.dml("access.rule", rule.getRule());
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
        return "redirect:/admin/globalaccessrules.xhtml";
    }

    @RequestMapping(value = "/deleterule.xhtml", method = RequestMethod.GET)
    public String deleteAccessRules(Model model, @RequestParam("rid") Integer rid, RedirectAttributes redirectAttributes) {
        baseDAO.dml("delete.rule", rid);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
        return "redirect:/admin/globalaccessrules.xhtml";
    }

    @RequestMapping(value = "/sessions.xhtml", method = RequestMethod.GET)
    public String Sessions(Model model) {
        return "/admin/sessions";
    }

    @RequestMapping(value = "/tables/sessions.xhtml", method = RequestMethod.GET)
    public String tablesSessions(Model model) {
        Object[] principals = cojSessionRegistryImpl.getAllPrincipals().toArray();
        List<Session> allsessions = new LinkedList<Session>();
        for (int i = 0; i < principals.length; i++) {
            Object[] sessions = (Object[]) cojSessionRegistryImpl.getAllSessions(principals[i], true).toArray();
            for (int j = 0; j < sessions.length; j++) {
                SessionInformation s = (SessionInformation) sessions[j];
                allsessions.add(new Session(((User) s.getPrincipal()).getUsername(), s.getSessionId(), s.getLastRequest(), s.isExpired()));
            }
        }
        model.addAttribute("sessions", allsessions);
        return "/admin/tables/sessions";
    }

    @RequestMapping(value = "/expire.xhtml", method = RequestMethod.GET)
    public String expireSession(Model model, @RequestParam("session") String sessionid, RedirectAttributes redirectAttributes) {
        boolean expire = true;
        Object[] principals = cojSessionRegistryImpl.getAllPrincipals().toArray();
        for (int i = 0; i < principals.length && expire; i++) {
            Object[] sessions = cojSessionRegistryImpl.getAllSessions(principals[i], true).toArray();
            for (int j = 0; j < sessions.length && expire; j++) {
                SessionInformation s = (SessionInformation) sessions[j];
                if (sessionid.equals(s.getSessionId())) {
                    s.expireNow();
                    s.refreshLastRequest();
                    expire = false;
                    cojSessionRegistryImpl.removeSessionInformation(sessionid);
                }
            }
        }
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
        return "redirect:/admin/sessions.xhtml";
    }

    private String currentDir = Config.getProperty("base.upload.dir");

    @RequestMapping(value = "/downloadfile.xhtml", method = RequestMethod.GET)
    public String downloadfile(Model model, HttpServletResponse response, OutputStream os, @RequestParam(value = "dir") String dir) throws Exception {
        response.setContentType("application/octet-stream");
        String dirDl = dir.replace(" ", "_");
        response.setHeader("Content-disposition", "inline; filename=" + dirDl);
        FileUtils.redirectStreams(new FileInputStream(currentDir + "/" + dir), os);
        return null;
    }

    @RequestMapping(value = "/uploadfile.xhtml", method = RequestMethod.GET)
    public String uploadFile(Model model, Principal principal, @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "dir", required = false, defaultValue = "/") String dir) throws Exception {

        boolean backFlag = true;

        if ("back".equals(action)) {
            if (!currentDir.equals(Config.getProperty("base.upload.dir"))) {
                currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"));
            }
            backFlag = !currentDir.equals(Config.getProperty("base.upload.dir"));
        } else if ("delete".equals(action)) {
            File file = new File(currentDir + "/" + dir);
            if (!file.isDirectory()) {
                file.delete();
            } else {
                FileUtils.deleteDirectory(file);
            }
			// eventManager.publish(EventType.LOGGABLE_ACTION,
            // "msg:deleting File " + dir + ";username:"
            // +getUsername(principal));
        } else if ("refresh".equals(action)) {
            if (!currentDir.equals(Config.getProperty("base.upload.dir"))) {
				// currentDir = currentDir.substring(0,
                // currentDir.lastIndexOf("/"));
                backFlag = true;
            } else {
                backFlag = false;
            }
        } else {
            if ("/".equals(dir)) {
                currentDir = Config.getProperty("base.upload.dir");
                backFlag = false;
            } else {
                currentDir = currentDir + "/" + dir;
                backFlag = true;
            }
        }

        File file = new File(currentDir);

        if (file.isDirectory()) {
            model.addAttribute("files", file.listFiles());
        }
        model.addAttribute("currentDir", currentDir.substring(Config.getProperty("base.upload.dir").length()));
        model.addAttribute("backAvail", backFlag);
        model.addAttribute(new UploadFile());
        return "/admin/uploadfile";
    }

    @RequestMapping(value = "/uploadfile.xhtml", method = RequestMethod.POST)
    public String uploadFile(Model model, Principal principal, UploadFile uploadFile, @RequestParam(value = "folder") String folder, HttpServletRequest request) {
        Assert.state(request instanceof MultipartHttpServletRequest, "request !instanceof MultipartHttpServletRequest");
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map<String, MultipartFile> files = multiRequest.getFileMap();
        if (uploadFile.getDir() == null) {
            uploadFile.setDir("default");
        }

        if (!StringUtils.isEmpty(folder)) {
            File file = new File(currentDir, folder);
            if (file.exists() && !file.isDirectory()) {
                file.delete();
            }
            file.mkdirs();
        }

        for (MultipartFile filex : files.values()) {
            if (filex.getOriginalFilename() != null && !filex.getOriginalFilename().equals("") && filex.getSize() > 0) {
                File file = new File(currentDir, filex.getOriginalFilename());
                if (file.exists() && !file.isDirectory()) {
                    file.delete();
                }
                file.mkdirs();
                try {
                    filex.transferTo(file);
                } catch (IOException ex) {
                    Logger.getLogger(GeneralConfigController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    Logger.getLogger(GeneralConfigController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return "redirect:/admin/uploadfile.xhtml?action=refresh";
    }

    @RequestMapping(value = "/globalflags.xhtml", method = RequestMethod.GET)
    public String globalFlags(Model model) {
        GlobalFlags flags = baseDAO.object("load.global.flags", GlobalFlags.class);
        model.addAttribute(flags);
        return "/admin/globalflags";
    }

    @RequestMapping(value = "/contestglobalflags.xhtml", method = RequestMethod.GET)
    public String contestGlobalFlags(Model model, @RequestParam("cid") Integer cid) {
        GlobalFlags flags = baseDAO.object("load.global.flags", GlobalFlags.class);
        Contest contest = contestDAO.loadContestManage(cid);

        //Verificar si el contest tienen asociados problemas (existen problemas = eproblem).
        boolean eproblem = true;
        List<Problem> problems = contestDAO.loadContestProblems(cid);
        if (problems != null)
            eproblem = !problems.isEmpty();

        model.addAttribute("eproblem", eproblem);

        model.addAttribute(contest);
        model.addAttribute(flags);
        return "/admin/contestglobalflags";
    }

    @RequestMapping(value = "/globalflags.xhtml", method = RequestMethod.POST)
    public String globalFlags(Model model, GlobalFlags globalFlags, RedirectAttributes redirectAttributes) {

        HandlerInterceptorImpl.setMaintenanceMode(globalFlags.isMaintenanceMode());
        mailNotificationService.setDisabledMailNotificationFlag(globalFlags.isMailNotificationDisabled());
        baseDAO.dml("update.flags", globalFlags.isMailNotificationDisabled(), globalFlags.isMaintenanceMode(), globalFlags.isEnabled_mail(), globalFlags.isEnabled_source_code_view(),
                globalFlags.isEnabled_submission());
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullUpdate());
        return "redirect:/admin/globalflags.xhtml";
    }

    @RequestMapping(value = "/contestglobalflags.xhtml", method = RequestMethod.POST)
    public String contestGlobalFlags(Model model, @RequestParam Integer cid, GlobalFlags globalFlags, RedirectAttributes redirectAttributes) {

        HandlerInterceptorImpl.setMaintenanceMode(globalFlags.isMaintenanceMode());
        mailNotificationService.setDisabledMailNotificationFlag(globalFlags.isMailNotificationDisabled());
		// lo cargamos de bd para mantener el maintenance mode de la misma
        // manera en la que esta. Esto no se puede configurar deesde contest
        GlobalFlags flags = baseDAO.object("load.global.flags", GlobalFlags.class);

        baseDAO.dml("update.flags", globalFlags.isMailNotificationDisabled(), flags.isMaintenanceMode(), globalFlags.isEnabled_mail(), globalFlags.isEnabled_source_code_view(),
                globalFlags.isEnabled_submission());
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullUpdate());
        return "redirect:/admin/contestglobalflags.xhtml?cid=" + cid;
    }

    @RequestMapping(value = "/resetrejudge.xhtml", method = RequestMethod.GET)
    public String resetrejudge(Model model) {
        baseDAO.dml("reset.rejudge.status");
        return "redirect:/admin/rejudge.xhtml";
    }

    private int countLogs(String pattern) {
        if (pattern != null) {
            return baseDAO.integer("count.load.logs.pattern", "%" + pattern + "%");
        }
        return baseDAO.integer("count.load.logs");
    }

    private IPaginatedList<cu.uci.coj.model.Log> loadLogs(PagingOptions options, String pattern) {
        if (pattern != null) {
            return baseDAO.paginated("load.logs.pattern", cu.uci.coj.model.Log.class, 30, options, "%" + pattern + "%");
        }
        return baseDAO.paginated("load.logs", cu.uci.coj.model.Log.class, 30, options);
    }
}
