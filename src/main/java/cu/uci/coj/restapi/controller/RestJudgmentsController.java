/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.restapi.templates.InputSubmitRest;
import cu.uci.coj.restapi.templates.JudgmentsRest;
import cu.uci.coj.restapi.templates.ResponseSubmitRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author lucy
 */

@Controller
@RequestMapping("/judgment")
public class RestJudgmentsController {
    
        @Resource
	private SubmissionDAO submissionDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private UtilDAO utilDAO;
	@Resource
	private ProblemDAO problemDAO;
	@Resource
	private Utils utils;
        @Resource
	private ContestDAO contestDAO;
        
    
    
    @ApiOperation(value = "Obtener todos los envíos",  
            notes = "Devuelve todos los envíos realizados al COJ (últimos 500 envíos).",
            response = JudgmentsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método") })
    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<JudgmentsRest> getAllJudgmentsOrFiltrerJudgments(
            @ApiParam(value = "Filtrar por usuario") @RequestParam(required = false, value = "username") String filter_username,
            @ApiParam(value = "Filtrar por identificador del problema") @RequestParam(required = false, value = "pid") Integer pid,
            @ApiParam(value = "Filtrar por estado de la solución (Ver filter)") @RequestParam(required = false, value = "status") String status,
            @ApiParam(value = "Filtrar por el lenguaje de envío (Ver filters)(Filtrar por la llave del lenguaje [key])") @RequestParam(required = false, value = "lang") String language) {
        
        String lang = submissionDAO.string("select language from language where key=?", language);        
	int found = submissionDAO.countSubmissions(filter_username, lang, pid,Config.getProperty("judge.status." + status));	
        
        if(found>500)
            found = 500;
        
        String st = Config.getProperty("judge.status." + status);
        
        List<SubmissionJudge> listSubmitions = new LinkedList(); 
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<SubmissionJudge> pages = submissionDAO.getSubmissions(filter_username, found, lang, pid,st,options,false,false,null);
            listSubmitions.addAll(pages.getList());
        }  
        
        List<JudgmentsRest> listJudgmentsRest = new LinkedList();
        
        for(SubmissionJudge s:listSubmitions){
            int testcase = 0;
            if(s.isOntest())
                testcase = s.getFirstWaCase()+1;
            JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),testcase, s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
            listJudgmentsRest.add(jud);
        }
        
        return listJudgmentsRest;
    }  
    
    
    @ApiOperation(value = "Obtener todos los envíos por competencia",  
            notes = "Dado el identificador de una competencia. Devuelve todos los envíos que se realizaron en esta.",
            response = JudgmentsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad cid<br> bad pid") })
    @RequestMapping(value = "/contest/{cid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllJudgmentsInContestByCid(
            @ApiParam(value = "Identificador de la competencia") @PathVariable Integer cid,
            @ApiParam(value = "Filtrar por usuario") @RequestParam(required = false, value = "username") String filter_user,
            @ApiParam(value = "Filtrar por identificador del problema") @RequestParam(required = false, value = "pid") Integer pid,
            @ApiParam(value = "Filtrar por estado de la solución (Ver filter)") @RequestParam(required = false, value = "status") String status,
            @ApiParam(value = "Filtrar por el lenguaje de envío (Ver filters)(Filtrar por la llave del lenguaje [key])") @RequestParam(required = false, value = "lang") String language) {
        
        if(!contestDAO.existsContest(cid))
            return new ResponseEntity<>(ErrorUtils.BAD_CID,HttpStatus.NOT_FOUND);
        
        if(pid != null && !problemDAO.exists(pid))
            return new ResponseEntity<>(ErrorUtils.BAD_PID,HttpStatus.NOT_FOUND);
        
        String username = null;
        Contest contest = contestDAO.loadContest(cid);
        contestDAO.unfreezeIfNecessary(contest);

        if (contest.isComing())
            return new ResponseEntity<>(ErrorUtils.BAD_CID,HttpStatus.NOT_FOUND);
        
        if ( (contest.isRunning() || contest.isPast()) && contest.isShow_status()) {
            contest.setShow_status(false);
            if (contest.isShow_status_out() ) {
                contest.setShow_status(true);
                String lang = submissionDAO.string("select language from language where key=?", language);
                int found = submissionDAO.countSubmissionsContest(filter_user, lang, pid, Config.getProperty("judge.status." + status), contest);
                if(found>500)
                    found = 500;
                String st = Config.getProperty("judge.status." + status);
                
                List<SubmissionJudge> listSubmitions = new LinkedList();         
                for(int i=1;i<=end(found);i++){
                    PagingOptions options = new PagingOptions(i); 
                    IPaginatedList<SubmissionJudge> submissions = submissionDAO.getContestSubmissions(found, username, language, pid, status, options, username, false, false, contest);
                    listSubmitions.addAll(submissions.getList());
                }          
                
                if (contest.getStyle() == 1) {
                    Map<Integer, Problem> pids = contestDAO.loadContestProblemsLetters(cid);
                    for (SubmissionJudge sub : listSubmitions) {
                            if (!sub.isAuthorize() && contest.isInFrozen(sub.getDate().getTime()) && contest.isFrozen())
                                    sub.froze();
                            sub.setLetter(pids.get(sub.getPid()).getLetter());
                            sub.setProblemTitle(pids.get(sub.getPid()).getTitle());
                    }
                }
                 
                List<JudgmentsRest> listJudgmentsRest = new LinkedList();
        
                for(SubmissionJudge s:listSubmitions){
                    int testcase = 0;
                    if(s.isOntest())
                        testcase = s.getFirstWaCase()+1;
                    JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),testcase, s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
                    listJudgmentsRest.add(jud);
                }
                
                return new ResponseEntity<>(listJudgmentsRest,HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<>(ErrorUtils.BAD_CID,HttpStatus.NOT_FOUND);
    }
    
    
    @ApiOperation(value = "Obtener todos los envíos por competencia (Por páginas)",  
            notes = "Dado el identificador de una competencia. Devuelve todos los envíos que se realizaron en esta por páginas (50 envíos).",
            response = JudgmentsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad cid"),
                            @ApiResponse(code = 400, message = "page out of index")})
    @RequestMapping(value = "/contest/{cid}/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllJudgmentsInContestByCidbyPage(
            @ApiParam(value = "Identificador de la competencia")  @PathVariable Integer cid,
            @ApiParam(value = "Número de la página") @PathVariable int page,
            @ApiIgnore @ApiParam(value = "Filtrar por usuario") @RequestParam(required = false, value = "user") String filter_user,
            @ApiIgnore @ApiParam(value = "Filtrar por identificador del problema") @RequestParam(required = false, value = "prob") Integer pid,
            @ApiIgnore @ApiParam(value = "Filtrar por estado de la solución (Sentencia)") @RequestParam(required = false, value = "status") String status,
            @ApiIgnore @ApiParam(value = "Filtrar por el lenguaje de envío") @RequestParam(required = false, value = "lang") String language) {
        
        if(!contestDAO.existsContest(cid))
            return new ResponseEntity<>(ErrorUtils.BAD_CID,HttpStatus.NOT_FOUND);
        
        pid = null;
        
        String username = null;
        Contest contest = contestDAO.loadContest(cid);
        contestDAO.unfreezeIfNecessary(contest);

        if (contest.isComing())
            return new ResponseEntity<>(ErrorUtils.BAD_CID,HttpStatus.NOT_FOUND);
        
        if ( (contest.isRunning() || contest.isPast()) && contest.isShow_status()) {
            contest.setShow_status(false);
            if (contest.isShow_status_out() ) {
                contest.setShow_status(true);
                String lang = submissionDAO.string("select language from language where key=?", language);
                int found = submissionDAO.countSubmissionsContest(filter_user, lang, pid, Config.getProperty("judge.status." + status), contest);
                if(found>500)
                    found = 500;
                if (page < 1 || page > end(found))
                    return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
                   
                String st = Config.getProperty("judge.status." + status);

                PagingOptions options = new PagingOptions(page); 
                IPaginatedList<SubmissionJudge> submissions = submissionDAO.getContestSubmissions(found, username, language, pid, status, options, username, false, false, contest);
         
                
                if (contest.getStyle() == 1) {
                    Map<Integer, Problem> pids = contestDAO.loadContestProblemsLetters(cid);
                    for (SubmissionJudge sub : submissions.getList()) {
                            if (!sub.isAuthorize() && contest.isInFrozen(sub.getDate().getTime()) && contest.isFrozen())
                                    sub.froze();
                            sub.setLetter(pids.get(sub.getPid()).getLetter());
                            sub.setProblemTitle(pids.get(sub.getPid()).getTitle());
                    }
                }
                 
                List<JudgmentsRest> listJudgmentsRest = new LinkedList();
        
                for(SubmissionJudge s:submissions.getList()){
                    int testcase = 0;
                    if(s.isOntest())
                        testcase = s.getFirstWaCase()+1;
                    JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),testcase, s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
                    listJudgmentsRest.add(jud);
                }
                
                return new ResponseEntity<>(listJudgmentsRest,HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<>(ErrorUtils.BAD_CID,HttpStatus.NOT_FOUND);
    }
    
    @ApiOperation(value = "Obtener todos los envíos (Por páginas)",  
            notes = "Devuelve todos los envíos que se han realizado por páginas (50 envíos).",
            response = JudgmentsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "page out of index") })
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllJudgmentsOrderByPage(
            @ApiParam(value = "Número de la página") @PathVariable int page) {
        
        String lang = null;        
        String status = null;
	int found = submissionDAO.countSubmissions(null, lang, null,Config.getProperty("judge.status." + status));	
        if (page > 0 && page <= end(found)) {
            String st = Config.getProperty("judge.status." + status);

            List<SubmissionJudge> listSubmitions; 

            if(page>end(found))
                page=-1;
            PagingOptions options = new PagingOptions(page);         
            IPaginatedList<SubmissionJudge> pages = submissionDAO.getSubmissions(null, found, lang, null,st,options,false,false,null);

            listSubmitions = pages.getList();

            List<JudgmentsRest> listJudgmentsRest = new LinkedList();

            for(SubmissionJudge s:listSubmitions){
                int testcase = 0;
                if(s.isOntest())
                    testcase = s.getFirstWaCase()+1;
                JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),testcase, s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
                listJudgmentsRest.add(jud);
            }
            
            return new ResponseEntity<>(listJudgmentsRest, HttpStatus.OK);
        }else
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
    }    
    
    @ApiOperation(value = "Obtener los n últimos envíos realizados",  
            notes = "Devuelve los n últimos envíos que se han realizado.",
            response = JudgmentsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "page out of index") })
    @RequestMapping(value = "/showfirst/{n}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getFirstNJudgments(@PathVariable int n)  {
        
        String filter_user = null;
	Integer pid = null;
        String status = null;
	String language = null;
        
        String lang = null;        
	int totals = submissionDAO.countSubmissions(null, lang, null,Config.getProperty("judge.status." + status));	
        if(n > totals || n<1)
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
        
        
        lang = submissionDAO.string("select language from language where key=?", language);        
	int found = n;	
        String st = Config.getProperty("judge.status." + status);
        
        List<SubmissionJudge> listSubmitions = new LinkedList(); 
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<SubmissionJudge> pages = submissionDAO.getSubmissions(filter_user, found, lang, pid,st,options,false,false,null);
            listSubmitions.addAll(pages.getList());
        }  
        
        List<JudgmentsRest> listJudgmentsRest = new LinkedList();
        
        for(int i=0;i<n;i++){
            SubmissionJudge s = listSubmitions.get(i);
            int testcase = 0;
            if(s.isOntest())
                testcase = s.getFirstWaCase()+1;
            JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),testcase, s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
            listJudgmentsRest.add(jud);
        }
        
        return new ResponseEntity<>(listJudgmentsRest, HttpStatus.OK);
    }  
    
    @ApiOperation(value = "Obtener la última página de envios",  
            notes = "Devuelve el número de la última página de envíos.",
            response = Integer.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de Respuesta") })
    @RequestMapping(value = "/lastpage", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getNumberOfLastPage()  {
        
        String filter_user = null;
	Integer pid = null;
        String status = null;
	String language = null;
        
        String lang = submissionDAO.string("select language from language where key=?", language);        
	int found = submissionDAO.countSubmissions(filter_user, lang, pid,Config.getProperty("judge.status." + status));
        
        String response = "{\"lastpage\":"+end(found)+"}";
        
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    @ApiOperation(value = "Obtener las mejores soluciones de un problema",  
            notes = "Dado el identificador de un problema, devuelve las mejores soluciones de este.",
            response = JudgmentsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad pid") })
    @RequestMapping(value = "/best/{pid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getBestJudgmentsByProblem(
            @ApiParam(value = "Identificador del problema") @PathVariable int pid,
            @ApiIgnore SecurityContextHolderAwareRequestWrapper requestWrapper) {
        
        if (!problemDAO.exists(pid) )
            return new ResponseEntity<>(ErrorUtils.BAD_PID, HttpStatus.NOT_FOUND);
        
        List<SubmissionJudge> listSubmitions = new LinkedList();
        int found = submissionDAO.countBestSolutions(pid);
        
	if (found != 0){
            PagingOptions options = new PagingOptions(1); 
            Problem problem = problemDAO.getStatistics("en", pid);            
            IPaginatedList<SubmissionJudge> submissions = submissionDAO.bestSolutions(pid, found, options,requestWrapper, problem);
            listSubmitions = submissions.getList();
        }               
        
        List<JudgmentsRest> listJudgmentsRest = new LinkedList();
        
        for(SubmissionJudge s:listSubmitions){
            int testcase = 0;
            if(s.isOntest())
                testcase = s.getFirstWaCase()+1;
            JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),testcase, s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
            listJudgmentsRest.add(jud);
        }
        
        return new ResponseEntity<>(listJudgmentsRest, HttpStatus.OK);
    }  
    
    
    @ApiOperation(value = "Hacer un envío de solución a un problema",  
            notes = "Hacer un envío de solución a un problema dado y Devuelve el identificador del envío para ser buscado posteriormente en los envíos juzgados por el COJ. (Este servicio web no devuelve el veredicto solo lo envía a la cola del juez. Solo se podrán hacer 30 envíos por minuto)",
            response = ResponseSubmitRest.class)
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect"),
                            @ApiResponse(code = 404, message = "bad pid"),
                            @ApiResponse(code = 412, message = "submit disabled temporarily by admin. maybe an important contest is running?<br> the problem id is incorrect.<br> the programming language is not enabled for that problem.<br> the source code is required (by file or text).<br> the source code is too long"),
                            @ApiResponse(code = 429, message = "Rate limit exceeded wait one minute")})
    @RequestMapping(value = "/submit/{keylanguage}/{pid}", method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> submitProblem(
            @ApiParam(value = "Código a enviar", required = true) @RequestBody String code,
            @ApiParam(value = "Identificador del problema") @PathVariable int pid,
            @ApiParam(value = "IIdentificador en forma de cadena de texto del lenguaje (key)") @PathVariable String keylanguage,
            @ApiParam(value = "Llave de desarrollador") @RequestHeader(value = "apikey", required = true) String apikey,
            @ApiParam(value = "Token de usuario") @RequestHeader(value = "token", required = true) String token) {
        int sid;  
        String username;
        try {         

            int error = ValidateApiAndToken(apikey, token);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }
            
            username = ExtractUser(token);

            if (!problemDAO.exists(pid) )
                return new ResponseEntity<>(ErrorUtils.BAD_PID,HttpStatus.NOT_FOUND);
                        
            SubmissionJudge submit = new SubmissionJudge();
            submit.setPid(pid);
            submit.setCode(code);
            submit.setKey(keylanguage); 
            
            List<Language> languages = new LinkedList();
            Integer uid = userDAO.integer("select.uid.by.username",username);
            
            if (problemDAO.exists(submit.getPid())) 
                languages.addAll(utilDAO.getEnabledLanguagesByProblem(pid));
            
            int lid = 0;
            int cont=0;
            for (Language lang : languages) {
                if (lang.getKey().equals(keylanguage)) {
                    lid = lang.getLid();
                    cont++;
                }
            }
            if(cont == 0)
                return new ResponseEntity<>(ErrorUtils.BAD_LANGUAGE,HttpStatus.BAD_REQUEST);
			
            String errors = validateSubmission(submit,lid);
            if (errors != null) 
                return new ResponseEntity<>("{\"error\":\""+errors+"\"}", HttpStatus.PRECONDITION_FAILED);
            
            
            submit.setLanguages(languages);
            submit.getLanguageIdByKey();
            submit.setHaspriviligeForProblem(false);
            
            Problem problem = problemDAO.getProblemSubmitDataByAbb(submit.getPid(),submit.getLid());
            problem.setUserLanguage("en");
            boolean locked = problemDAO.bool("issolved.byuser", uid,problem.getPid()) && problemDAO.isLocked(uid, problem.getPid());
            
            sid = submissionDAO.insertSubmission(uid,username, problem.getPid(), submit.getCode(),submit.getLanguageByLid(), locked, null);
            SubmissionJudge submission = new SubmissionJudge(sid, uid,
					submit.getCode(), problem.getPid(), problem.getTime(),
					problem.getCasetimelimit(), problem.getMemory(),
					submit.getLanguageByLid());
            submission.setSpecialJudge(problem.isSpecial_judge());
            try {                            
                int priority = 6;
                utils.startCalification(submission,priority);
            } catch (Exception e) {
                submissionDAO.changeStatus(sid, "Unqualified");
            }
       

        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(new ResponseSubmitRest(sid, pid,username, keylanguage),HttpStatus.OK);
    }
    

    public String validateSubmission(SubmissionJudge submit, int lid){
        String errors = null;
        ResourceBundleMessageSource r=new ResourceBundleMessageSource();
        r.setBasename("messages_en");               
        submit.getLanguageIdByKey();        
        try{        
            if (!utilDAO.bool("submit.enabled"))
                return r.getMessage("errormsg.43", null, new Locale("en")).toLowerCase();

            if (!problemDAO.exists(submit.getPid()) || !problemDAO.isEnabled(submit.getPid()))
                return r.getMessage("errormsg.25", null, new Locale("en")).toLowerCase();

            if (problemDAO.isDisable24h(submit.getPid())) 
                return r.getMessage("errormsg.25", null, new Locale("en")).toLowerCase();

            int problemSourceLimit = problemDAO.getSourceLimitByPid(submit.getPid(), lid);

            if (submit.getCode().length() == 0)
                return r.getMessage("errormsg.27", null, new Locale("en")).toLowerCase();

            if (submit.getCode().length() > problemSourceLimit) 
                return r.getMessage("errormsg.28", null, new Locale("en")).toLowerCase();

        
        }catch(Exception e){
            errors = r.getMessage("errormsg.43", null, new Locale("en")).toLowerCase();
        }
        return errors;
    }
    
    public int end(int found){
        if(found%20==0)
            return found/20;
        else
            return (found/20)+1;
    }
    
    
    
    private int ValidateApiAndToken(String apikey, String token) throws IOException {
        

        try {
            int error = TokenUtils.ValidateAPIKey(apikey);
            if (error > 0) {
                return error;
            }

            int error2 = TokenUtils.ValidateTokenUser(token);
            if (error2 > 0) {
                return error2;
            }
        } catch (Exception e) {
            return 9;
        }

        return 0;
    }
    
    private String ExtractUser(String token) {
        String username = null;
        try {
            username = TokenUtils.getUsernameFromToken(token);
        } catch (Exception ex) {
            
        }

        return username;
    }  
    
}
