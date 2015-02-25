package cu.uci.coj.api.json.controller;

import cu.uci.coj.api.json.dao.JsonUserDAO;
import cu.uci.coj.api.json.model.JsonUserRank;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UserDAO;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/json/user")
@Scope("session")
public class JsonUserController extends BaseController {

    @Resource
    private JsonUserDAO jsonUserDAO;
    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping(produces = "application/json",value = "/{user}/{password}/authenticate.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody() String authenticateUser(@PathVariable("user") String username, @PathVariable("password") String password) {

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        try {
            return authenticationManager.authenticate(authRequest).isAuthenticated() ? "OK" : "FALSE";
        } catch (AuthenticationException e) {
            return "FALSE";
        }
    }

    @RequestMapping(produces = "application/json",value = "/ranking/top.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody() String userTopRanking() {
        List<JsonUserRank> mapa = jsonUserDAO.ranks();
         return JSONArray.fromObject(mapa).toString();
    }

    
}
