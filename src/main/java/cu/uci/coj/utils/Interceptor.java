package cu.uci.coj.utils;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.handler.DispatcherServletWebRequest;

import cu.uci.coj.dao.AnnouncementDAO;
import cu.uci.coj.dao.ClarificationDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.MailDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Roles;

@Component("interceptor")
public class Interceptor implements WebRequestInterceptor {

    @Resource
    private MailDAO mailDAO;
    @Resource
    private ContestDAO contestDAO;
    @Resource
    private ClarificationDAO clarificationDAO;
    @Resource
    private AnnouncementDAO announcementDAO;
    @Resource
    private UserDAO userDAO;

    private String getUsername(Principal principal){
        return principal == null ? null : principal.getName();
    }
    
    @Override
    public void preHandle(WebRequest request) throws Exception {
        
        if (!((DispatcherServletWebRequest) request).getRequest().getMethod().equals("POST") 
                && SessionsRecord.isOverLoad(((DispatcherServletWebRequest) request).getSessionId())) {
            throw new SessionExceeded();
        }
        Utils.addIpUrl(((DispatcherServletWebRequest) request).getRequest());
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        if (model != null && model.containsKey("contest")) {
            try {
                int total = 0;
                int unread = 0;
                int unreply = 0;
                Contest contest = (Contest) model.get("contest");
                if (request.getUserPrincipal() != null) {
                    int uid = userDAO.integer("select.uid.by.username", getUsername(request.getUserPrincipal()));
                    boolean isjudge = false;
                    if ((isjudge = contestDAO.isJudgeInContest(contest.getCid(), uid)) == true || contestDAO.isInContest(contest.getCid(), uid)) {
                        if (isjudge) {
                            model.addAttribute("isjudge", isjudge);
                        }
                        if ((request.isUserInRole(Roles.ROLE_ADMIN)  || request.isUserInRole(Roles.ROLE_SUPER_PSETTER) || request.isUserInRole(Roles.ROLE_PSETTER)) && isjudge) {
                            total = clarificationDAO.integer("count.judges.clarification", contest.getCid());
                            unreply = clarificationDAO.integer("count.judges.unreply.clarifications", contest.getCid());
                        }
                        total += clarificationDAO.integer("count.user.clarification", uid, contest.getCid());
                        total += clarificationDAO.integer("count.general.contest.clarification", contest.getCid(), uid);
                        unread += clarificationDAO.integer("count.user.unread.clarifications", uid, contest.getCid());
                    }
                } else {
                    total = clarificationDAO.integer("count.contest.public.clarification", contest.getCid());
                }
                try {
                    List<String> ann = announcementDAO.strings("load.contest.announcements", contest.getCid());
                    for (String a : ann) {
                        a = a.replaceAll("<br>", "");
                    }
                    if (ann.size() > 0) {
                        model.addAttribute("hasann", true);
                        model.addAttribute("ann", ann);
                    }
                } catch (Exception e) {
                }
                model.addAttribute("unreply", unreply);
                model.addAttribute("unread", unread);
                model.addAttribute("totalmsg", total);
            } catch (Exception e) {
                System.out.println("aki " + e.getMessage());
            }
        } else {
            try {
                List<String> ann = announcementDAO.strings("load.announcements");
                for (String a : ann) {
                    a = a.replaceAll("<br>", "");
                }
                if (ann.size() > 0) {
                    model.addAttribute("hasann", true);
                    model.addAttribute("ann", ann);
                }
            } catch (Exception e) {
            }
            model.addAttribute("com", contestDAO.integer("count.coming.contests"));
            model.addAttribute("run", contestDAO.integer("count.running.contests"));
            model.addAttribute("past", contestDAO.integer("count.past.contests"));
        }
        if (request.getUserPrincipal() != null && request.isUserInRole(Roles.ROLE_USER)) {
            model.addAttribute("mailunread", mailDAO.integer("count.unread.mail", getUsername(request.getUserPrincipal())));
            model.addAttribute("mailtotal", mailDAO.integer("count.user.inbox",getUsername(request.getUserPrincipal())));
            model.addAttribute("mailout", mailDAO.integer("count.user.outbox",getUsername(request.getUserPrincipal())));
        }
    }

    @Override
    public void afterCompletion(WebRequest wr, Exception excptn) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
