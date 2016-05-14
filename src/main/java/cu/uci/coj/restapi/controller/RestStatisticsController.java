/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.CompareUsers;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Problem;
import cu.uci.coj.restapi.templates.CompareUserRest;
import cu.uci.coj.restapi.templates.StadisticsRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cesar
 */
@Controller
@RequestMapping("/statistic")
public class RestStatisticsController {

    @Resource
    private BaseDAO baseDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private ProblemDAO problemDAO;

    
    @ApiOperation(value = "Obtener las estadísticas del archivo 24 horas",  
            notes = "Devuelve todos las estadísticas de envíos por lenguajes en el archivo 24 horas.",
            response = StadisticsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método") })
    @RequestMapping(value = "/24h", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<StadisticsRest> getStadidistic24h() {

        List<Language> listLanguages = baseDAO.objects("training.statistics", Language.class);
        List<StadisticsRest> listRestStadistics = new LinkedList();

        for (Language l : listLanguages) {
            int ac = l.getAcc();
            int ce = l.getCe();
            int ivf = l.getIvf();
            int mle = l.getMle();
            int ole = l.getOle();
            int pe = l.getPe();
            int rte = l.getRte();
            int tle = l.getTle();
            int wa = l.getWa();
            int total = l.getTotal();
            listRestStadistics.add(new StadisticsRest(l.getLanguage(), ac, ce, ivf, mle, ole, pe, rte, tle, wa, total));
        }

        return listRestStadistics;
    }

    
    @ApiOperation(value = "Obtener las estadísticas de las competencias",  
            notes = "Devuelve todos las estadísticas de envíos por lenguajes en las competencias.",
            response = StadisticsRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método") })
    @RequestMapping(value = "/contest", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<StadisticsRest> getStadidisticContest() {

        List<Language> listLanguages = baseDAO.objects("statistics.global", Language.class);
        List<StadisticsRest> listRestStadistics = new LinkedList();

        for (Language l : listLanguages) {
            int ac = l.getAcc();
            int ce = l.getCe();
            int ivf = l.getIvf();
            int mle = l.getMle();
            int ole = l.getOle();
            int pe = l.getPe();
            int rte = l.getRte();
            int tle = l.getTle();
            int wa = l.getWa();
            int total = l.getTotal();
            listRestStadistics.add(new StadisticsRest(l.getLanguage(), ac, ce, ivf, mle, ole, pe, rte, tle, wa, total));
        }

        return listRestStadistics;
    }

    
    @ApiOperation(value = "Comparar Usuarios",  
            notes = "Compara dos usuarios registrados del COJ.",
            response = CompareUserRest.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad user") })
    @RequestMapping(value = "/compare/{user1}/{user2}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getCompareUsers(
            @ApiParam(value = "Usuario 1") @PathVariable String user1, 
            @ApiParam(value = "Usuario 2") @PathVariable String user2) {
        boolean error = true;
        CompareUsers cmp = new CompareUsers(user1, user2);
        List<Problem> solved1;
        List<Problem> solved2;
        if (user1 != null && user2 != null && user1.length() > 0 && user2.length() > 0) {
            int uid1, uid2;
            try{
                uid1 = userDAO.integer("select.uid.by.username", user1);
                uid2 = userDAO.integer("select.uid.by.username", user2);
            }catch(NullPointerException ne){
                return new ResponseEntity<>(ErrorUtils.BAD_USER, HttpStatus.NOT_FOUND);
            }
            if (uid1 != -1 && uid2 != -1) {
                solved1 = userDAO.objects("problems.solved", Problem.class, user1);
                solved2 = userDAO.objects("problems.solved", Problem.class, user2);
                List<Problem> s1 = new LinkedList();
                List<Problem> s2 = new LinkedList();
                List<Problem> f1 = new LinkedList();
                List<Problem> f2 = new LinkedList();
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
                error = false;
            }
        } else {
            return new ResponseEntity<>(ErrorUtils.BAD_USER, HttpStatus.NOT_FOUND);
        }

        List<Integer> solvedOnlyByUser1 = ExtraerPID(cmp.getFacc());
        List<Integer> solvedOnlyByBoth = ExtraerPID(cmp.getBacc());
        List<Integer> solvedOnlyByUser2 = ExtraerPID(cmp.getSacc());

        List<Integer> triedOnlyByUser1 = ExtraerPID(cmp.getFf());
        List<Integer> triedOnlyByBoth = ExtraerPID(cmp.getBf());
        List<Integer> triedOnlyByUser2 = ExtraerPID(cmp.getSf());

        CompareUserRest compareUserRest = new CompareUserRest(solvedOnlyByUser1, solvedOnlyByBoth, solvedOnlyByUser2, triedOnlyByUser1, triedOnlyByBoth, triedOnlyByUser2);

        return new ResponseEntity<>(compareUserRest, HttpStatus.OK);
    }

    public List<Integer> ExtraerPID(List<Problem> lis) {
        List<Integer> lisPID = new LinkedList();
        for (Problem p : lis) {
            lisPID.add(p.getPid());
        }

        return lisPID;
    }
    
     
    @ApiOperation(value = "Obtener las estadísticas de un problema",  
            notes = "Dado el identificador de un problema, devuelve todos las estadísticas asociadas a este.",
            response = StadisticsRest.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad pid") })    
    @RequestMapping(value = "/problem/{pid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getStadisticByProblem(
            @ApiParam(value = "Identificador del problema") @PathVariable int pid) {
        
        if (!problemDAO.exists(pid) )
            return new ResponseEntity<>(ErrorUtils.BAD_PID, HttpStatus.NOT_FOUND);     

        Problem p = problemDAO.getStatistics("en", pid);
        StadisticsRest stadistic = new StadisticsRest(""+pid, p.getAc(), p.getCe(), p.getIvf(), p.getMle(), p.getOle(), p.getPe(), p.getRte(), p.getTle(), p.getWa(), p.getSubmitions());
        return new ResponseEntity<>(stadistic, HttpStatus.OK);
        
    }

}
