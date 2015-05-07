package cu.uci.coj.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;

@Controller
@RequestMapping(value = "/poll")
public class PollsController extends BaseController {
	
	private static final long serialVersionUID = 65992684603339499L;
	@Resource
	PollDAO pollDAO;
	
	private String doList(Model model) {
		List<Poll> polls = pollDAO.listEnabled();
		model.addAttribute("polls", polls);
		return "/poll/list";
	}
	
	@RequestMapping(value = "/vote.xhtml",method = RequestMethod.POST)
	public String vote(Model model,Principal principal,@RequestParam Integer option,@RequestParam Integer pid){
		pollDAO.vote(pid,getUid(principal), option);
		return doList(model);
	}
	
	@RequestMapping(value = "/list.xhtml", method = RequestMethod.GET)
	public String list(Model model, Principal principal) {
		return doList(model);
	}
}
