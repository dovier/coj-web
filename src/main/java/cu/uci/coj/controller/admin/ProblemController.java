package cu.uci.coj.controller.admin;

import cu.uci.coj.config.Config;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Limits;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.FileUtils;
import cu.uci.coj.utils.RsyncThread;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.problemValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cu.uci.coj.utils.Notification;

@Controller("AdminProblemController")
@RequestMapping(value = "/admin")
public class ProblemController extends BaseController {

    private static final long serialVersionUID = 3173338737803234523L;
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private problemValidator validator;
    @Resource
    private Utils utils;
    
    
    
    @PostConstruct
	public void init() {
		
	}

    @RequestMapping(value = "/adminproblems.xhtml", method = RequestMethod.GET)
    public String listProblems(Principal principal, HttpServletRequest request, Model model, Locale locale, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
        model.addAttribute("pattern", pattern);
        return "/admin/adminproblems";
    }

    @RequestMapping(value = "/tables/adminproblems.xhtml", method = RequestMethod.GET)
    public String tablesListProblems(Principal principal, HttpServletRequest request, Model model, Locale locale, PagingOptions options,
            @RequestParam(required = false, value = "pattern") String pattern) {
        Integer uid = contestDAO.integer("select.uid.by.username", getUsername(principal));
        String role = Roles.ROLE_USER;
        if (request.isUserInRole(Roles.ROLE_ADMIN)) {
            role = Roles.ROLE_ADMIN;
        } else if (request.isUserInRole(Roles.ROLE_SUPER_PSETTER)) {
            role = Roles.ROLE_SUPER_PSETTER;
        } else if (request.isUserInRole(Roles.ROLE_PSETTER)) {
            role = Roles.ROLE_PSETTER;
        }

        int found = problemDAO.countProblemAdmin(uid, role, pattern);
        if (found != 0) {
            model.addAttribute("found", found);
            IPaginatedList<Problem> problems = problemDAO.loadProblemsAdmin(found, uid, role, locale.getLanguage(), pattern, options);
            model.addAttribute("problems", problems);
        }
        return "/admin/tables/adminproblems";
    }

    public String addProblem(Model model, Principal principal, HttpServletRequest request, Problem problem, BindingResult result) {
        validator.validate(problem, result);
        if (result.hasErrors()) {
            model.addAttribute("psetters", utilDAO.objects("select.psetters.pid", User.class, 0));
            model.addAttribute("showpsetters", true);
            model.addAttribute("languages", utilDAO.getEnabledProgramingLanguages());
            model.addAttribute("contests", contestDAO.getComingAndRunningContests());
            model.addAttribute("sources", problemDAO.getProblemSources());
            model.addAttribute(problem);
            return "/admin/manageproblem";
        }

        if (!problem.isMultidata()) {
            problem.setCasetimelimit(problem.getTime());
        }

        Integer uid = contestDAO.integer("select.uid.by.username", getUsername(principal));
        problem.setUid(uid);

        problemDAO.addProblem(problem);
        problem.setPid(problemDAO.getPidByTitle(problem.getTitle()));
        problemDAO.dml("insert.psetter.problem", uid, problem.getPid());
        problemDAO.insertProblemStats(problem.getPid());
        if (problem.getLanguageids() != null) {
            for (int i = 0; i < problem.getLanguageids().length; i++) {
                problemDAO.insertProblemLanguage(problem.getPid(), problem.getLanguageids()[i]);
            }
        }
        if (problem.getContest() != 0) {
            int pOrder = contestDAO.integer("contest.problem.order", problem.getContest());
            contestDAO.insertProblemContest(problem.getPid(), problem.getContest(), 1, pOrder + 1);
        }
        if (problem.getLimits() != null) {
            for (Limits limits : problem.getLimits()) {
                limits.setProblemId(problem.getPid());
                problemDAO.insertLimit(limits);
            }
        }

        handleFiles(problem, request);
        problemDAO.log("adding problem " + problem.getPid(), getUsername(principal));
        
        return "redirect:/admin/adminproblems.xhtml";
        //return "redirect:/24h/problem.xhtml?pid=" + problem.getPid();
    }

    @RequestMapping(value = "/manageproblem.xhtml", method = RequestMethod.GET)
    public String manageProblem(Model model, Locale locale, Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, @RequestParam(required = false, value = "pid") Integer pid) {
        Integer uid = contestDAO.integer("select.uid.by.username", getUsername(principal));

        final List<Language> languages = utilDAO.getEnabledProgramingLanguages();

        if (!(requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER) || (requestWrapper.isUserInRole(Roles.ROLE_PSETTER) && (pid == null || (problemDAO
                .bool("is.psetter.problem", uid, pid)))))) {
            return "/admin/adminproblems";
        }

        if (pid != null) {
            Problem problem = problemDAO.getProblemByPid(locale.getLanguage(), pid);
            problemDAO.fillProblemLanguages(problem);
            problemDAO.fillProblemSetters(problem);
            problemDAO.fillProblemLimits(problem);
            problem.initProblemsetters();
            problem.initLanguages();

            model.addAttribute("pid", pid);
            model.addAttribute("psetters", utilDAO.objects("select.psetters.pid", User.class, pid));
            model.addAttribute("datasets", utils.getDatasetsNames(pid));
            model.addAttribute("showpsetters", (requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && requestWrapper.isUserInRole(Roles.ROLE_ADMIN)) || principal.getName().equals(problem.getUsername()));
            model.addAttribute(problem);
            model.addAttribute("create", false);
        } else {
            model.addAttribute("psetters", utilDAO.objects("select.psetters.pid", User.class, 0));
            model.addAttribute("showpsetters", true);

            final Problem problem = new Problem();
            problem.initLimits(languages);

            model.addAttribute("problem", problem);
            model.addAttribute("create", true);
        }

        model.addAttribute("languages", languages);
        model.addAttribute("contests", contestDAO.getComingAndRunningContests());
        model.addAttribute("sources", problemDAO.getProblemSources());
        model.addAttribute("multipliers", getMultipliers());
        return "/admin/manageproblem";
    }

    @RequestMapping(value = "/manageproblem.xhtml", method = RequestMethod.POST)
    public String manageProblem(Model model, SecurityContextHolderAwareRequestWrapper requestWrapper, Principal principal, HttpServletRequest request, Problem problem, BindingResult result, RedirectAttributes redirectAttributes) {

        // adicionar. el resto es modificar.
        if (problem.getPid() == null || problem.getPid() == 0) {
            String data  = addProblem(model, principal, request, problem, result);
            //chequeo que haya insertado correctamente y valla a redireccionar para el listar para mostrar los flash
            if(data.equals("redirect:/admin/adminproblems.xhtml")){
               redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());              
            }
            return data;
        }

        Integer uid = contestDAO.integer("select.uid.by.username", getUsername(principal));
        if (!(requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER) || (problemDAO.bool("is.psetter.problem", uid, problem.getPid()) && requestWrapper
                .isUserInRole(Roles.ROLE_PSETTER)))) {
            return "/admin/adminproblems";
        }

        validator.validate(problem, result);
        if (result.hasErrors()) {
            problemDAO.fillProblemLanguages(problem);
            problem.initLanguages();
            model.addAttribute("languages", utilDAO.getEnabledProgramingLanguages());
            model.addAttribute("contests", contestDAO.getComingAndRunningContests());
            model.addAttribute("sources", problemDAO.getProblemSources());
            model.addAttribute("psetters", utilDAO.objects("select.psetters.pid", User.class, problem.getPid()));
            model.addAttribute(problem);
            return "/admin/manageproblem";
        }

        if (!problem.isMultidata()) {
            problem.setCasetimelimit(problem.getTime());
        }

        problemDAO.updateProblem(problem);
        problemDAO.clearProgrammingLanguages(problem.getPid());
        if (problem.getLanguageids() != null) {
            for (int i = 0; i < problem.getLanguageids().length; i++) {
                problemDAO.insertProblemLanguage(problem.getPid(), problem.getLanguageids()[i]);
            }
        }
        // solo los admins pueden asignar nuevos problemsetters
        if (requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER) || (requestWrapper.isUserInRole(Roles.ROLE_PSETTER) && principal.getName().equals(problemDAO.creatorUsernameByPid(problem.getPid())))) {
            problemDAO.clearPsetters(problem.getPid());
            if (problem.getPsettersids() != null) {
                for (int i = 0; i < problem.getPsettersids().length; i++) {
                    problemDAO.dml("insert.psetter.pid", problem.getPsettersids()[i], problem.getPid());
                }
            }
        }

        if (problem.getContest() != 0) {
            int pOrder = contestDAO.integer("contest.problem.order", problem.getContest());
            contestDAO.insertProblemContest(problem.getPid(), problem.getContest(), 1, pOrder + 1);
        }

        if (problem.getLimits() != null) {
            problemDAO.clearLimits(problem.getPid());
            for (Limits limits : problem.getLimits()) {
                limits.setProblemId(problem.getPid());
                problemDAO.insertLimit(limits);
            }
        }
        handleFiles(problem, request);

        problemDAO.log("editing problem " + problem.getPid(), getUsername(principal));
        //return "redirect:/24h/problem.xhtml?pid=" + problem.getPid();
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullUpdate());   
        return "redirect:/admin/adminproblems";
    }

    public void handleFiles(Problem problem, HttpServletRequest request) {
  //      Assert.state(request instanceof MultipartHttpServletRequest, "request !instanceof MultipartHttpServletRequest");
       if (!( request instanceof MultipartHttpServletRequest))return;
        final MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        final Map<String, MultipartFile> files = multiRequest.getFileMap();

        boolean mkdirs = true;
        File dir = new File(Config.getProperty("problems.directory") + problem.getPid());
        dir.mkdirs();
        boolean exec = false;
        for (MultipartFile filex : files.values()) {
            if (filex.getOriginalFilename() != null && !filex.getOriginalFilename().equals("") && filex.getSize() > 0) {
                String filename = filex.getOriginalFilename();
                if (filex.getName().equals("inputgen")) {
                    String ext = filex.getOriginalFilename().substring(filex.getOriginalFilename().lastIndexOf("."));
                    filename = "datagen" + ext;
                } else if (filex.getName().equals("modelsol")) {
                    String ext = filex.getOriginalFilename().substring(filex.getOriginalFilename().lastIndexOf("."));
                    filename = "Model" + ext;
                }

                File file = new File(Config.getProperty("problems.directory") + problem.getPid(), filename);
                if (file.exists() && !file.isDirectory()) {
                    file.delete();
                }
                if (mkdirs) {
                    file.mkdirs();
                    mkdirs = false;
                }
                try {
                    filex.transferTo(file);
                    String ext = filex.getOriginalFilename().substring(filex.getOriginalFilename().lastIndexOf("."));
                    if (ext.equals(".zip") || ext.equals(".rar")) {
                        FileUtils.decompressFile(file.getAbsolutePath(), dir.getAbsolutePath(), true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(ProblemController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    Logger.getLogger(ProblemController.class.getName()).log(Level.SEVERE, null, ex);
                }
                exec = true;
            }
        }

        if (exec) {
            utils.normalizeProblem(problem.getPid());
            RsyncThread rsync = new RsyncThread();
            rsync.start();
        }

    }

    @RequestMapping(value = "/manageproblemI18N.xhtml", method = RequestMethod.GET)
    public String manageProblemI18N(Model model, Locale locale, Principal principal, SecurityContextHolderAwareRequestWrapper requestWrapper, @RequestParam("pid") Integer pid) {
        Integer uid = problemDAO.integer("select.uid.by.username", getUsername(principal));

        if (!requestWrapper.isUserInRole(Roles.ROLE_ADMIN) && !requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER)
                && !(problemDAO.bool("is.psetter.problem", uid, pid) && requestWrapper.isUserInRole(Roles.ROLE_PSETTER))) {
            return "/admin/adminproblems";
        }

        Problem problem = problemDAO.object("select.problem.i18n", Problem.class, pid);

        model.addAttribute("pid", pid);
        model.addAttribute(problem);
        return "/admin/manageproblemI18N";
    }

    @RequestMapping(value = "/manageproblemI18N.xhtml", method = RequestMethod.POST)
    public String manageProblemI18N(Model model, SecurityContextHolderAwareRequestWrapper requestWrapper, Principal principal, HttpServletRequest request, Problem problem, BindingResult result) {
        Integer uid = problemDAO.integer("select.uid.by.username", getUsername(principal));
        if (!(requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || requestWrapper.isUserInRole(Roles.ROLE_SUPER_PSETTER) || (problemDAO.bool("is.psetter.problem", uid, problem.getPid()) && requestWrapper
                .isUserInRole(Roles.ROLE_PSETTER)))) {
            return "/admin/adminproblems";
        }

        if (result.hasErrors()) {
            model.addAttribute(problem);
            return "/admin/manageproblemI18N";
        }

        problemDAO.updateProblemI18N(problem);

        problemDAO.log("editing problem I18N " + problem.getPid(), getUsername(principal));
        return "redirect:/24h/problem.xhtml?pid=" + problem.getPid();
    }

    @RequestMapping(produces = "application/json", value = "/removealldatasets.json", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDatasets(@RequestParam(value = "pid") int pid) {

        utils.removeDatasets(pid);
    }

    @RequestMapping(produces = "application/json", value = "/removedataset.json", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDataset(@RequestParam(value = "pid") int pid, @RequestParam(value = "dataset") String dataset) {
        utils.removeDataset(pid, dataset);
    }
    
     private Map<String, double[]> getMultipliers(){
        //TODO: Refactorizar esto, ponerlo en configuraciones o como sea, pero no así
        Map<String, double[]> multipliers = new HashMap<>();
        
        String[] language = {"Text","C++","Java","C","C#","Perl","Python","Pascal","Ruby","PHP","Bash","C++11","Haskell","Prolog","JavaScript-NodeJS","VBasic"};
        double[] memoryMult = {1,1  ,60,1,20,1,2,1,2,5,1,1,1,25,15,20};
        double[] caseTimeMult = {3,1,3,1 ,2,3 ,6,3,3,6,3,1,3,3,6,2};
        double[] totalTimeMult = {3,1,3,1 ,2,3 ,6,3,3,6,3,1,3,3,6,2};
        double[] sourceCodeLenghtMult = {1,1,1,1 ,1,1 ,1,1,1,1,1,1,1,1,1,1};
        
        for (int i = 0; i < language.length; i++) {
            double[] limits = new double[4];
            limits[0] = memoryMult[i];
            limits[1] = caseTimeMult[i];
            limits[2] = totalTimeMult[i];
            limits[3] = sourceCodeLenghtMult[i];
            
            multipliers.put(language[i], limits);
        }
        return multipliers;
    }
}
