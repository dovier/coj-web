/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.controller.practice;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Problem;
import cu.uci.coj.model.User;
import java.security.Principal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public class vscoreboardController  extends BaseController {

    private ContestDAO contestDAO;
    private UserDAO userDAO;
    private ProblemDAO problemDAO;

    public ProblemDAO getproblemDAO() {
        return problemDAO;
    }

    public void setproblemDAO(ProblemDAO problemDAO) {
        this.problemDAO = problemDAO;
    }

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ModelAndView handleRequest(Locale locale,Principal principal,HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("/virtual/vscoreboard");

        int number = 1;
        try {
            number = new Integer(request.getParameter("start"));
            if (number <= 0) {
                number = 1;
            }
        } catch (Exception ex) {
        }
        try {
            int cid = contestDAO.integer("virtual.running",getUsername(principal));
            String interval1 = "", interval2 = "";
            Contest contest = contestDAO.loadVirtualContest(cid);
            mv.addObject("contest", contest);
            if (cid > 0) {
                switch (contest.getStyle()) {
                    case 1: {
                        List<Problem> problems = problemDAO.getContestProblems(locale.getLanguage(),cid);
                        List<User> users = contestDAO.getRankingACMVirtual(cid, getUsername(principal), problems);
                        Map<Integer, Integer> uidPos = new HashMap<Integer, Integer>();
                        for (Iterator<User> it = users.iterator(); it.hasNext();) {
                            User user = it.next();
                            uidPos.put(user.getUid(), user.getRank() - 1);
                        }
                        contestDAO.buildRankingACMVirtual(problems, users, uidPos, contest, getUsername(principal),1);
                        for (int i = 0; i < problems.size(); i++) {
                            problems.get(i).stats();
                        }
                        int i = 0;
                        while (i < users.size() && i < contest.getGold()) {
                            if (users.get(i).getAcc() == 0) {
                                break;
                            }
                            users.get(i).setMedal(User.GOLD);
                            i++;
                        }

                        while (i < users.size() && (i - contest.getGold()) < contest.getSilver()) {
                            if (users.get(i).getAcc() == 0) {
                                break;
                            }
                            users.get(i).setMedal(User.SILVER);
                            i++;
                        }

                        while (i < users.size() && (i - contest.getGold() - contest.getSilver()) < contest.getBronze()) {
                            if (users.get(i).getAcc() == 0) {
                                break;
                            }
                            users.get(i).setMedal(User.BRONZE);
                            i++;
                        }
                        //Collections.so
                        shellSortByAcceptedAndPenalty(users);
                        mv.addObject("users", users);
                        mv.addObject("problems", problems);
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        break;
                    }

                }
            }
        } catch (EmptyResultDataAccessException ex) {            
        } catch (Exception e) {            
            mv = new ModelAndView("/error/error");
        }
        return mv;
    }
    public void shellSortByAcceptedAndPenalty(List<User> a) {
        for (int increment = a.size() / 2;
                increment > 0;
                increment = (increment == 2 ? 1 : (int) Math.round(increment / 2.2))) {
            for (int i = increment; i < a.size(); i++) {
                for (int j = i; j >= increment && ((a.get(j - increment).getAcc() < a.get(j).getAcc()) || (a.get(j - increment).getAcc() == a.get(j).getAcc() && a.get(j - increment).getPenalty() > a.get(j).getPenalty())); j -= increment) {
                    User temp = a.get(j);
                    temp.setRank(j - increment + 1);
                    User temp1 = a.get(j - increment);
                    temp1.setRank(j + 1);
                    a.set(j, temp1);
                    a.set(j - increment, temp);
                }
            }
        }
    }
}
