package cu.uci.coj.controller;

import cu.uci.coj.dao.PollDAO;
import cu.uci.coj.model.Poll;
import java.security.Principal;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"/poll"})
public class PollsController
  extends BaseController
{
  private static final long serialVersionUID = 65992684603339499L;
  @Resource
  PollDAO pollDAO;
  
  private String doList(Integer uid, Model model)
  {
    List<Poll> polls = this.pollDAO.listEnabled(uid);
    model.addAttribute("polls", polls);
    return "/poll/list";
  }
  
  @RequestMapping(value={"/vote.xhtml"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public String vote(Model model, Principal principal, @RequestParam Integer option, @RequestParam Integer pid)
  {
    this.pollDAO.vote(pid.intValue(), getUid(principal).intValue(), option.intValue());
    return doList(getUid(principal), model);
  }
  
  @RequestMapping(value={"/list.xhtml"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public String list(Model model, Principal principal)
  {
    return doList(getUid(principal), model);
  }
}
