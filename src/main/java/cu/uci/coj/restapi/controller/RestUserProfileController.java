/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

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
@RequestMapping("/userprofile")
public class RestUserProfileController {
    
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllProblemsOrderByPage(@PathVariable String username) {

        /*int found = problemDAO.countProblem(null, 0, username, -1, -1);
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
        }*/
 
        return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
}
    
    
}
