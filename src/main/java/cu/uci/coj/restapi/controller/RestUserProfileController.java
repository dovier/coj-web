/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Achievement;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.User;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.restapi.templates.UserProfileRest;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.util.HashMap;
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
@RequestMapping("/userprofile")
public class RestUserProfileController {
    
    @Resource
    private UserDAO userDAO;
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getUserProfile(@PathVariable String username) {

        User user = null;

        if (!userDAO.bool("is.user.enabled", username))
            return new ResponseEntity<>("disabled username", HttpStatus.BAD_REQUEST);
      

        Integer uid = userDAO.idByUsername(username);
        if (uid != null && userDAO.isUser(username)) {
            user = userDAO.loadUserData(username);
            if (user.isTeam())
                return new ResponseEntity<>("bad username", HttpStatus.BAD_REQUEST);
            
            List<Problem> solved = userDAO.objects("problems.solved.1", Problem.class, user.getUid());
            List<Problem> unsolved = userDAO.getProblemsTryied(user.getUid());

            if (solved.isEmpty()) {
                user.setLast_accepted("***");
            }
            if (unsolved.isEmpty() && solved.isEmpty()) {
                user.setLast_submission("***");
            }            
            
            user.setSolved(solved.size());
            user.setUnsolved(unsolved.size());
            user.setRanking(userDAO.integer("ranking.position", username));
            user.setTot_ranking(userDAO.countEnabledUsers(null, false));
            user.setRankingbycountry(userDAO.integer("ranking.position.country", user.getCountry_id(), username));
            user.setTot_rankingbycountry(userDAO.countEnabledUsersByCountry(null, false, user.getCountry_id()));
            user.setRankingbyinstitution(userDAO.integer("ranking.position.institution", user.getInstitution_id(), username));
            user.setTot_rankingbyinstitution(userDAO.countEnabledUsersByInstitutions(null, false, user.getInstitution_id()));

            //PagingOptions options = new PagingOptions(1, "asc", "startDate");
            //IPaginatedList<WbContest> contests = wbContestService.getContestList(0, options, 1, user.getUid());
            //model.addAttribute("contests", contests);

            //List<WbSite> list = wbSiteDAO.getSiteList();
            //HashMap<Integer, WbSite> map = new HashMap<Integer, WbSite>();
            //for (int i = 0; i < list.size(); i++) {
            //    map.put(list.get(i).getSid(), list.get(i));
            //}
            //model.addAttribute("mapsites", map);
        }

        Entry lastentry = userDAO.object("last.entry.by.user", Entry.class, uid);
       
        //model.addAttribute("count.entries", userDAO.integer(0, "count.entries", uid));
        int followers = userDAO.integer(0, "count.followers", uid);
        int following = userDAO.integer(0, "count.following", uid);
        
        String avatar = "http://coj.uci.cu/images/avatars/"+username;
        String gender = "female";
        if(user.getGender() == 1)
            gender = "male";
            
        UserProfileRest userRest = new UserProfileRest(avatar,user.getName(), user.getLastname(), username, gender, user.getCountry(), user.getInstitution(), user.getPlanguage(), user.getRgdate(), user.getLast_submission(), user.getLast_accepted(), user.getScore(), user.getRanking(),user.getRankingbyinstitution(),user.getRankingbycountry(), lastentry.getText(),lastentry.getDate().toString(),followers,following);
 
        return new ResponseEntity<>(userRest, HttpStatus.OK);
}
    
    
}
