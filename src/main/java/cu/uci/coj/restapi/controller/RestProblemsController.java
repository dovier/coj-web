/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.RecommenderDAO;
import cu.uci.coj.dao.SubmissionDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Limits;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.recommender.Recommender;
import cu.uci.coj.restapi.templates.ProblemDescriptionRest;
import cu.uci.coj.restapi.templates.ProblemRest;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/problem")
public class RestProblemsController {

    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private RecommenderDAO recommenderDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    private SubmissionDAO submissionDAO;
    @Resource
    private Utils utils;
    

    @RequestMapping(value = "", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<ProblemRest> getAllProblemsOrFiltrerProblems(
            String username,
            @RequestParam(required = false, value = "pattern") String pattern,
            Integer filterby,
            @RequestParam(required = false, value = "classification", defaultValue = "-1") Integer idClassification,
            @RequestParam(required = false, value = "complexity", defaultValue = "-1") Integer complexity) {

        try {
            Long l = new Long(15 * 60 * 1000);
            System.out.println(TokenUtils.CreateTokenUser("dovier"));
            l = new Long(15 * 60 * 1000);
            System.out.println(TokenUtils.CreateAPIKey("dovier", "cesar"));
        } catch (Exception e) {
        }

        if (filterby == null) {
            filterby = 0;
        }

        if (filterby == 5) {
            PagingOptions options = new PagingOptions(1);
            Recommender recommender = new Recommender(userDAO, problemDAO, recommenderDAO);
            List<Problem> recommendations = recommender.recommendations(username, username == null ? 0 : problemDAO.integer(
                    "select.uid.by.username", username), options.getSort(), options.getDirection());
            int found = recommendations.size();
            if (found != 0) {
                List<ProblemRest> listProblemsRest = new LinkedList();
                for (Problem p : recommendations) {
                    ProblemRest pr = new ProblemRest(p.getPid(), p.getTitle(), p.getSubmissions(), p.getAc(), p.getAvgs(), p.getPoints(), p.isFavorite(), ResolveStatus(p, username));
                    listProblemsRest.add(pr);
                }

                return listProblemsRest;
            }
        }

        int found = problemDAO.countProblem(pattern, filterby, username, idClassification, complexity);

        List<Problem> listProblems = new LinkedList();
        for (int i = 1; i <= end(found); i++) {
            PagingOptions options = new PagingOptions(i);
            int x = username == null ? 0 : problemDAO.integer("select.uid.by.username", username);
            IPaginatedList<Problem> pages = problemDAO.findAllProblems("en", pattern, found, options, username, x, filterby, idClassification, complexity);
            listProblems.addAll(pages.getList());
        }

        List<ProblemRest> listProblemsRest = new LinkedList();

        for (Problem p : listProblems) {
            ProblemRest pr = new ProblemRest(p.getPid(), p.getTitle(), p.getSubmissions(), p.getAc(), p.getAvgs(), p.getPoints(), p.isFavorite(), ResolveStatus(p, username));
            listProblemsRest.add(pr);
        }

        return listProblemsRest;

    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllProblemsOrderByPage(@PathVariable int page, String username) {

        int found = problemDAO.countProblem(null, 0, username, -1, -1);
        if (page > 0 && page <= end(found)) {
            PagingOptions options = new PagingOptions(page);

            int idUsername = username == null ? 0 : problemDAO.integer("select.uid.by.username", username);

            IPaginatedList<Problem> pages = problemDAO.findAllProblems("en", null, found, options, username, idUsername, 0, -1, -1);

            List<Problem> listProblems = pages.getList();
            List<ProblemRest> listProblemsRest = new LinkedList();

            for (Problem p : listProblems) {
                ProblemRest pr = new ProblemRest(p.getPid(), p.getTitleEN(), p.getSubmissions(), p.getAc(), p.getAvgs(), p.getPoints());
                listProblemsRest.add(pr);
            }

            return new ResponseEntity<>(listProblemsRest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/{pid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getProblemDescriptionsByID(
            @PathVariable int pid,
            @RequestParam(required = false, value = "locale", defaultValue = "en") String locale) {
        
        if (!problemDAO.exists(pid) )
            return new ResponseEntity<>("bad pid", HttpStatus.BAD_REQUEST);
        
        
        
        Problem p = null;
        try {
            p = problemDAO.getProblemByCode(locale, pid, false);
        } catch (NullPointerException ne) {
            return new ResponseEntity<>("bad pid", HttpStatus.BAD_REQUEST);
        }
        p.setDate(p.getDate().split(" ")[0]);
        problemDAO.fillProblemLanguages(p);
        problemDAO.fillProblemLimits(p);
        Recommender recommender = new Recommender(userDAO, problemDAO, recommenderDAO);
        List<Problem> recommendations = recommender.findSimilarProblems(p);
 
        List<String> languages = new LinkedList();
        List<Long> totaltime = new LinkedList();
        List<Long> testtime = new LinkedList();
        List<String> memory = new LinkedList();
        List<String> size = new LinkedList();
        for (Language leng : p.getLanguages()) {
            for (Limits limit : p.getLimits()) {
                if (leng.getLid() == limit.getLanguageId()) {
                    totaltime.add(limit.getMaxTotalExecutionTime());
                    testtime.add(limit.getMaxCaseExecutionTime());
                    memory.add(FileUtils.byteCountToDisplaySize(limit.getMaxMemory()));
                    size.add(FileUtils.byteCountToDisplaySize(limit.getMaxSourceCodeLenght()));
                }

            }
            languages.add(leng.getLanguage());
        }

        List<String> recomended = new LinkedList();

        for (Problem pro : recommendations) {
            recomended.add("" + pro.getPid());
        }

        String[] arreglo = author_source(pid);
        ProblemDescriptionRest problemDescrptions;
        problemDescrptions = new ProblemDescriptionRest(p.getAuthor(),arreglo[0].trim(),arreglo[1].trim(), p.getUsername(), p.getDate(), totaltime, testtime, memory, "64 MB", size, languages, p.getDescription(), p.getInput(), p.getOutput(), p.getInputex(), p.getOutputex(), p.getComments(), recomended);

        return new ResponseEntity<>(problemDescrptions, HttpStatus.OK);

    }
    
    private String[] author_source(int pid){
        String[] arreglo = new String[2];
        
        String s = problemDAO.string("select.author.problem.id",pid);
        if(s.contains("[")){
            s = s.replace("[", "!");
            arreglo = s.split("!");
            arreglo[1] = arreglo[1].replace("]", "");
        }
        else
        {
            arreglo[0] = s;
            arreglo[1] = "";
        }
        return arreglo;
    }
            
    
    private int end(int found) {
        if (found % 50 == 0) {
            return found / 50;
        } else {
            return (found / 50) + 1;
        }
    }

    private String ResolveStatus(Problem p, String username) {
        String s = "not send";
        if (p.isSolved()) {
            s = "solved";
        }
        if (p.isUnsolved()) {
            s = "unsolved";
        }
        if (username == null) {
            s = "not logged";
        }
        return s;
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
            Logger.getLogger(RestProblemsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username;
    }  

    //------------------------------PRIVATE METHODS (TOKEN NECESSARY)-------------------------------
    /*
     { 
     "apikey":"asdahsd32234gajagfagfafaf".
     "token":"asdas3244",
     ...
     }    
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllProblemsOrFiltrerProblemsPrivate(@RequestBody String bodyjson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username = null;
            String pattern = null;
            Integer filterby = 0;
            Integer classification = -1;
            Integer complexity = -1;

            String token = node.get("token").textValue();
            username = ExtractUser(token);

            if (node.has("pattern")) {
                pattern = node.get("pattern").textValue();
            }
            if (node.has("filterby")) {
                filterby = node.get("filterby").intValue();
            }
            if (node.has("classification")) {
                classification = node.get("classification").intValue();
            }
            if (node.has("complexity")) {
                complexity = node.get("complexity").intValue();
            }

            List<ProblemRest> listproblemrest = getAllProblemsOrFiltrerProblems(username, pattern, filterby, classification, complexity);

            return new ResponseEntity<>(listproblemrest, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllProblemsOrderByPagePrivate(@PathVariable int page,@RequestBody String bodyjson) {
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

            return getAllProblemsOrderByPage(page, username);

        } catch (IOException ex) {
           return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
   
    @RequestMapping(value = "/togglefavorite", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> togglefavorite(@RequestBody String bodyjson) {
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
            
            if(!TokenUtils.ValidatePropertiesinJson(node,"favorite","pid"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);
           
            boolean favorite = node.get("favorite").asBoolean();
            int pid = node.get("pid").asInt();

            try {
                int uid = problemDAO.integer("select.uid.by.username", username);
                if (!problemDAO.bool("problem.ismark.asfavorite.byuser", uid, pid)
                        && problemDAO.bool("exist.problem.bypid", pid) && favorite) {
                    problemDAO.dml("problem.mark.asfavorite.byuser", uid, pid);
                    
                }
                
                if (problemDAO.bool("problem.ismark.asfavorite.byuser", uid, pid) && !favorite) {
                    problemDAO.dml("problem.unmark.favorite.byuser", uid, pid);
                }
                
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (EmptyResultDataAccessException e) {
                return new ResponseEntity<>("Problem not exists", HttpStatus.BAD_REQUEST);
            }
           

        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }
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
                return new ResponseEntity<>("bad pid",HttpStatus.BAD_REQUEST);
            
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
                return new ResponseEntity<>("bad language",HttpStatus.BAD_REQUEST);
			
            
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
    
    
   
    
    
   
}
