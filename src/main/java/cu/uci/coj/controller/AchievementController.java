package cu.uci.coj.controller;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Achievement;
import java.security.Principal;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/general")
@Scope("session")
public class AchievementController extends BaseController {

    
    @Resource
    private UserDAO userDAO;

    @RequestMapping(value = "/achievements.xhtml", method = RequestMethod.GET)
    public String userAccount(HttpServletRequest request, Model model, Principal principal, @RequestParam(required = false, value="username") String username) {
        List<Achievement> trophies = null;
        if (userDAO.isUser(username)) {
            int uid = userDAO.integer("user.uid", username);
            trophies = userDAO.objects("achievements.by.user",Achievement.class,uid);
        }
        else{
        	//FIXME Esta consulta no existe!
            trophies = userDAO.objects("achievements.all",Achievement.class);
        }
        
        model.addAttribute("achievements",trophies);
        return "/general/achievements";
    }

}
