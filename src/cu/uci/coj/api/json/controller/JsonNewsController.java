package cu.uci.coj.api.json.controller;

import cu.uci.coj.api.json.dao.JsonNewsDAO;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UserDAO;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
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
import cu.uci.coj.api.json.model.JsonNews;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/json/news")
@Scope("session")
public class JsonNewsController extends BaseController {

    @Resource
    private JsonNewsDAO jsonNewsDAO;
   
    @RequestMapping(produces = "application/json",value = "/current.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody() String currentNews() {
        List<JsonNews> mapa = jsonNewsDAO.enabledNews();
        
        return JSONArray.fromObject(mapa).toString();
    }

    
}
