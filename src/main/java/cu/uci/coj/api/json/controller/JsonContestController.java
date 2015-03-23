package cu.uci.coj.api.json.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.api.json.dao.JsonContestDAO;
import cu.uci.coj.api.json.model.JsonContest;
import cu.uci.coj.controller.BaseController;

@Controller
@RequestMapping(value = "/json/contests")
@Scope("session")
@Deprecated
public class JsonContestController extends BaseController {

    @Resource
    private JsonContestDAO jsonContestDAO;
   
    @RequestMapping(produces = "application/json",value = "/current.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody List<JsonContest> runningAndComingContests() {
        return jsonContestDAO.runningAndComingContest();
    }

    
}
