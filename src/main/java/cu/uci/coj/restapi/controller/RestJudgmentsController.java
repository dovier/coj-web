/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.restapi.templates.JudgmentsRest;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.submitValidator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
        String st = Config.getProperty("judge.status." + status);
        
        List<SubmissionJudge> listSubmitions = new LinkedList(); 
        
        for(int i=1;i<=end(found);i++){
            PagingOptions options = new PagingOptions(i);            
            IPaginatedList<SubmissionJudge> pages = submissionDAO.getSubmissions(filter_user, found, lang, pid,st,options,false,false,null);
            listSubmitions.addAll(pages.getList());
        }  
        
        List<JudgmentsRest> listJudgmentsRest = new LinkedList();
        
        for(SubmissionJudge s:listSubmitions){
            JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),s.getTestcase(), s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
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
                JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),s.getTestcase(), s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
                listJudgmentsRest.add(jud);
            }
            
            return new ResponseEntity<>(listJudgmentsRest, HttpStatus.OK);
        }else
            return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
        
        
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
            JudgmentsRest jud = new JudgmentsRest(s.getSid(),""+s.getDate().toString(),s.getUsername(), s.getPid(), s.getStatus(),s.getTestcase(), s.getTimeUsed(), s.getMemoryMB(), s.getFontMB(), s.getLang());
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
    
    
    public int end(int found){
        if(found%20==0)
            return found/20;
        else
            return (found/20)+1;
    }
    
}
