package cu.uci.coj.validator;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.User;

@Component
public class virtualcontestValidator implements Validator {

    @Resource
    private ContestDAO contestDAO;
    @Resource
    private UserDAO userDAO;

    /**
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Contest.class.isAssignableFrom(clazz);
    }

    public ContestDAO getContestDAO() {
        return contestDAO;
    }

    public void setContestDAO(ContestDAO contestDAO) {
        this.contestDAO = contestDAO;
    }

    /**
     *
     * @param o
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors) {
        Contest contest = (Contest) o;
        contest.initIDate();
        String invalid = "errormsg.34";
        if (contest.getTemplate() == 0) {
            errors.rejectValue("template",
                    invalid,
                    "You should select a template contest");
        } /*else {
            if (contestDAO.overlapsContest(contest)) {
                errors.rejectValue("initdate",
                        "virtual.error.overlap",
                        "You should select one");
            }
        }*/
        if (contest.getUsersids().length > 60) {
            errors.rejectValue("usersids",
                    "errormsg.35",
                    "Guest Limit = 30");
        }

        if (contest.getStyle() == 2 && contest.getProblemids().length > 12) {
            errors.rejectValue("problemids",
                    "contestproblems.errors.acm",
                    "At most 40 characters.");
        }

        if (contest.getStyle() == 2 && contest.getProblemids().length == 0) {
            errors.rejectValue("problemids",
                    "errormsg.17",
                    "At most 40 characters.");
        }

        if (!errors.hasErrors()) {
            List<User> locked = new LinkedList<User>();
            int cont = 0;
            for (int i = 0; i < contest.getUsersids().length; i++) {
                String username = (String) contest.getUsersids()[i];
                locked.add(new User(0, username));
                if (contestDAO.bool("user.has.practice.created", userDAO.idByUsername(username))) {
                    cont++;
                }
            }
            if (cont != 0) {
                errors.rejectValue("usersids",
                        "errormsg.18",
                        "At least one user has a virtual contest created");
                contest.setPractice_locked(locked);
            }
        }

    }
}
