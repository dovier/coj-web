package cu.uci.coj.api.json.controller;

import cu.uci.coj.api.json.dao.JsonContestDAO;
import cu.uci.coj.api.json.dao.JsonProblemDAO;
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
import cu.uci.coj.api.json.model.JsonProblem;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(value = "/json/problems")
@Scope("session")
public class JsonProblemController extends BaseController {

    @Resource
    private JsonProblemDAO jsonProblemDAO;
   
    @RequestMapping(produces = "application/json",value = "/{id}.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody() String problem(@PathVariable("id") int id) {
        JsonProblem problem = jsonProblemDAO.problem(id);
        
        return JSONObject.fromObject(problem).toString();
    }

    @RequestMapping(produces = "application/json",value = "/range.json", method = RequestMethod.GET,headers = {"Accept=application/json"})
    public @ResponseBody() String problems(int from,int to) {
        List<JsonProblem> problems = jsonProblemDAO.problems(from,to);
        
        return JSONArray.fromObject(problems).toString();
    }
}
