/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.ContestStyle;
import cu.uci.coj.model.Language;
import cu.uci.coj.restapi.templates.ContestDescriptionRest;
import cu.uci.coj.restapi.templates.ContestRest;
import cu.uci.coj.restapi.templates.ProblemRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.utils.Utils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
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
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
/**
 *
 * @author cesar
 */
//Colega
//asd
//Lol
 
@Controller
@RequestMapping("/contest")
public class  RestContestController {
    
        @Resource
	private ContestDAO contestDAO;
	@Resource
	private Utils utils;
        
        @ApiOperation(value = "Obtener próximas competencias",
            notes = "Devuelve las proximas competencias del COJ.",
            response = ContestRest.class,
            responseContainer = "List")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
        @RequestMapping(value = "/next", method = RequestMethod.GET, headers = "Accept=application/json")
        @ResponseBody
        public List<ContestRest> getAllNextContest() {
               
            PagingOptions options = new PagingOptions(1);
            IPaginatedList<Contest> pages = contestDAO.getComingContests(options);
           
            List<Contest> listContest = pages.getList();        
            List<ContestRest> listContestRest = new LinkedList();

            for(Contest c:listContest){
                String access = c.getRegistration() == 2 ? "private" : "open";
                ContestRest cr = new ContestRest(c.getCid(),access,c.getName(),c.getInitdate().toString(),c.getEnddate().toString());
                listContestRest.add(cr);
            }

            return listContestRest ;
        }    
        

        @ApiOperation(value = "Obtener competencias actualmente en funcionamiento",
            notes = "Devuelve las competencias que se estan efectuando actualmente en el COJ.",
            response = ContestRest.class,
            responseContainer = "List")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
        @RequestMapping(value = "/running", method = RequestMethod.GET, headers = "Accept=application/json")
        @ResponseBody
        public List<ContestRest> getAllRunningContest() {
               
            PagingOptions options = new PagingOptions(1);
            IPaginatedList<Contest> pages = contestDAO.getRunningContests(options);
           
            List<Contest> listContest = pages.getList();        
            List<ContestRest> listContestRest = new LinkedList();

            for(Contest c:listContest){
                String access = c.getRegistration() == 2 ? "private" : "open";
                ContestRest cr = new ContestRest(c.getCid(),access,c.getName(),c.getInitdate().toString(),c.getEnddate().toString());
                listContestRest.add(cr);
            }

            return listContestRest ;
        } 
        

        @ApiOperation(value = "Obtener competencias previas",
            notes = "Devuelve las competencias ya efectuadas del COJ.",
            response = ContestRest.class,
            responseContainer = "List")
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
        @RequestMapping(value = "/past", method = RequestMethod.GET, headers = "Accept=application/json")
        @ResponseBody
        public List<ContestRest> getAllPastContest(
                @ApiParam(value = "Filtrar por nombre o identificador de competencia ") @RequestParam(value = "pattern", required = false) String pattern) {
            
            List<Contest> listContest = new LinkedList();  
            int i=1;
            IPaginatedList<Contest> pages;
            do{            
                PagingOptions options = new PagingOptions(i);            
                pages = contestDAO.getPastContests(options,pattern);
                listContest.addAll(pages.getList());
                i++;
            }while(pages.getList().size()!=0);       
              
            List<ContestRest> listContestRest = new LinkedList();

            for(Contest c:listContest){
                String access = c.getRegistration() == 2 ? "private" : "open";
                ContestRest cr = new ContestRest(c.getCid(),access,c.getName(),c.getInitdate().toString(),c.getEnddate().toString());
                listContestRest.add(cr);
            }

            return listContestRest ;
        }
        
        
        @ApiOperation(value = "Obtener descripción de competencias",
            notes = "Devuelve la descripción de una competencia dado su identificador.",
            response = ContestDescriptionRest.class)
        @ApiResponses(value = { @ApiResponse(code = 404, message = "bad cid")  })
        @RequestMapping(value = "/{cid}", method = RequestMethod.GET, headers = "Accept=application/json")
        @ResponseBody
        public  ResponseEntity<?>  getContestDescriptionByID(
                @ApiParam(value = "Identificador de competencia") @PathVariable int cid) {
            Contest contest = null;
            try{
                 contest = contestDAO.loadContestFull(cid);
            }catch(NullPointerException ne){
                return new ResponseEntity<>(ErrorUtils.BAD_CID, HttpStatus.NOT_FOUND);
            }
            List<Language> planguages = contestDAO.getContestLanguages(cid);
            List<ContestStyle> styles = contestDAO.loadEnabledScoringStyles();
            
            String contestType = null;
            String contestantType;
            String accessType;
            String registrationType = null;
            boolean templateToVirtualContest;
            int penaltyB0EachRejectedSubmission = 0;
            int frozenTime = 0;
            int deadTime = 0;
            int levels = 0;
            int acceptedLimit = 0;
            int acceptedByLevels = 0;
            int pointsByProblem = 0;
            List<String> programmingLanguages = new LinkedList();
            boolean showProblemsToAll = contest.isShow_problem_out();
            boolean showJudgmentsToTheContestants = contest.isShow_status();
            boolean showJudgmentsToAll = contest.isShow_status_out();
            boolean showStandings = contest.isShow_scoreboard();
            boolean showStandingstoall = contest.isShow_scoreboard_out();
            boolean showStatisticsToTheContestants = contest.isShow_stats();
            boolean showStatisticsToAll = contest.isShow_stats_out();
            int goldMedals = contest.getGold();
            int silverMedals = contest.getSilver();
            int bronzeMedals = contest.getBronze();
            String overview = "None";
            if(contest.getOverview() != null)
                overview = contest.getOverview();
                

            for(ContestStyle s:styles){
                if(s.getSid() == contest.getStyle())
                    contestType = s.getName();
            }
            
            if(contest.getContestant() == 1)
                contestantType = "Teams";
            else if(contest.getContestant() == 2)
                contestantType = "Single";
            else
                contestantType = "Teams, Single";
            
            accessType = contest.getRegistration() == 2 ? "Private" : "Public";
            
            if(contest.getRegistration() == 0)
                registrationType = "Free registration";
            else if(contest.getRegistration() == 1)
                registrationType = "Limited registration";
            else if(contest.getRegistration() == 2)
                registrationType = "Registration by admin";
            
            templateToVirtualContest = contest.isVtemplate();

            if(contest.getStyle() == 1){
                penaltyB0EachRejectedSubmission = contest.getPenalty();
                frozenTime = contest.getFrtime();
                deadTime = contest.getDeadtime();
            }
            
            if(contest.getStyle() == 4){
                levels = contest.getLevels();
                acceptedLimit = contest.getAclimit();
                acceptedByLevels = contest.getAcbylevels();
                pointsByProblem = contest.getPpoints();                
            }
            
            for(Language l:planguages){
                programmingLanguages.add(l.getLanguage());
            }         
            
            
            ContestDescriptionRest contentDescription = new ContestDescriptionRest(contestType, contestantType, accessType, registrationType, templateToVirtualContest, penaltyB0EachRejectedSubmission, frozenTime, deadTime, levels, acceptedLimit, acceptedByLevels, pointsByProblem, programmingLanguages, showProblemsToAll, showJudgmentsToTheContestants, showJudgmentsToAll, showStandings, showStandingstoall, showStatisticsToTheContestants, showStatisticsToAll, goldMedals, silverMedals, bronzeMedals, overview);
            return new ResponseEntity<>(contentDescription, HttpStatus.OK);
        } 
}
