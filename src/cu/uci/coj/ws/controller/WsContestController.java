package cu.uci.coj.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.ws.dao.WsContestDAO;
import cu.uci.coj.ws.models.WsContest;

@Controller
@RequestMapping(value="/services")
public class WsContestController {
	
	@Resource
	WsContestDAO wsContestDAO;	
	
	@RequestMapping(value="/contests.json", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	List<WsContest> getComingContests() {
		return wsContestDAO.getComingContests();
	}
}
