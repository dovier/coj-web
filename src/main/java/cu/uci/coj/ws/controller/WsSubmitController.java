package cu.uci.coj.ws.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cu.uci.coj.ws.dao.WsSubmitDAO;
import cu.uci.coj.ws.models.WsSubmit;

@Controller
@RequestMapping(value="/services")
public class WsSubmitController {
	
	@Resource
	WsSubmitDAO wsSubmitDAO;	
	
	@RequestMapping(value="/submits.json", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	List<WsSubmit> getComingContests(@RequestParam(value="from")int from, @RequestParam(value="to")int to ) {
		return wsSubmitDAO.getSubmits(from,to);
	}
	
	@RequestMapping(value="/submit.json", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	WsSubmit getComingContests(@RequestParam(value="sid")int sid) {
		return wsSubmitDAO.getSubmit(sid);
	}
}
