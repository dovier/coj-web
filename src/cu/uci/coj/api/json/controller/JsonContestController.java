package cu.uci.coj.api.json.controller;

import cu.uci.coj.api.json.dao.JsonContestDAO;
import cu.uci.coj.controller.BaseController;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import cu.uci.coj.api.json.model.JsonContest;
import java.util.List;
import net.sf.json.JSONArray;

@Controller
@RequestMapping(value = "/json/contests")
@Scope("session")
public class JsonContestController extends BaseController {

    @Resource
    private JsonContestDAO jsonContestDAO;
   
    @RequestMapping(produces = "application/json",value = "/current.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody() String runningAndComingContests() {
        List<JsonContest> mapa = jsonContestDAO.runningAndComingContest();
        
        return JSONArray.fromObject(mapa).toString();
    }

    
}
