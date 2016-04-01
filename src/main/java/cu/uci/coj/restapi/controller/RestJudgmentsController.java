/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.restapi.templates.JudgmentsRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.submitValidator;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	private submitValidator submitValidator;
	@Resource
	private Utils utils;
    
    
    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<JudgmentsRest> getAllJudgmentsOrFiltrerJudgments(
                        @RequestParam(required = false, value = "user") String filter_user,
			@RequestParam(required = false, value = "prob") Integer pid,
                        @RequestParam(required = false, value = "status") String status,
			@RequestParam(required = false, value = "lang") String language) {
        
        String lang = submissionDAO.string("select language from language where key=?", language);        
	int found = submissionDAO.countSubmissions(filter_user, lang, pid,Config.getProperty("judge.status." + status));	
        
        if(found>500)
            found = 500;
        
        String st = Config.getProperty("judge.status." + status);
        
        List<SubmissionJudge> listSubmitions = new LinkedList(); 
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<SubmissionJudge> pages = submissionDAO.getSubmissions(filter_user, found, lang, pid,st,options,false,false,null);
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
    
    
    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllJudgmentsOrderByPage(@PathVariable int page) {
        
        String lang = null;        
        String status = null;
	int found = submissionDAO.countSubmissions(null, lang, null,Config.getProperty("judge.status." + status));	
        if (page > 0 && page <= end(found)) {
            String st = Config.getProperty("judge.status." + status);

            List<SubmissionJudge> listSubmitions = new LinkedList(); 

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
    
    @RequestMapping(value = "/lastpage", method = RequestMethod.GET)
    @ResponseBody
    public String getNumberOfLastPage()  {
        
        String filter_user = null;
	Integer pid = null;
        String status = null;
	String language = null;
        
        String lang = submissionDAO.string("select language from language where key=?", language);        
	int found = submissionDAO.countSubmissions(filter_user, lang, pid,Config.getProperty("judge.status." + status));
        
        String response = "{\"lastpage\":"+end(found)+"}";
        
        return response;
    }
    
    
    @RequestMapping(value = "/best/{pid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getBestJudgmentsByProblem(@PathVariable int pid,SecurityContextHolderAwareRequestWrapper requestWrapper) {
        
        if (!problemDAO.exists(pid) )
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
        
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
    
    
    @RequestMapping(value = "/submit", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> SubmitProblem(@RequestBody String bodyjson) {
        int sid = -1;  
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username = null;
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            
            if(!TokenUtils.ValidatePropertiesinJson(node,"pid","language","source"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);
     
            int pid = node.get("pid").asInt();
            String language = node.get("language").asText();
            String code = node.get("source").asText();
                     
            
            
            if (!problemDAO.exists(pid) )
                return new ResponseEntity<>(ErrorUtils.BAD_PID,HttpStatus.BAD_REQUEST);
            
            SubmissionJudge submit = new SubmissionJudge();
            submit.setPid(pid);
            submit.setCode(code);
            submit.setKey(language);
            
            List<Language> languages = new LinkedList<Language>();
            Integer uid = userDAO.integer("select.uid.by.username",username);
            
            if (problemDAO.exists(submit.getPid())) 
                languages.addAll(utilDAO.getEnabledLanguagesByProblem(pid));
            
            int cont=0;
            for (Iterator<Language> it = languages.iterator(); it.hasNext();) {
                Language lang = it.next();
                if (lang.getKey().equals(language)) 
                    cont++;
            }
            if(cont == 0)
                return new ResponseEntity<>(ErrorUtils.BAD_LANGUAGE,HttpStatus.BAD_REQUEST);
			
            
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
        String response = "{idsubmission:"+sid+"}";
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
    
    
   
    
    
    public int end(int found){
        if(found%20==0)
            return found/20;
        else
            return (found/20)+1;
    }
    
    private int ValidateApiAndToken(String bodyjson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

        if (!node.has("apikey") || !node.has("token")) {
            return 8;
        }

        String apikey = node.get("apikey").textValue();
        String token = node.get("token").textValue();

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
