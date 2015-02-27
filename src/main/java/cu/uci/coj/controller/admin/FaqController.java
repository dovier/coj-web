package cu.uci.coj.controller.admin;

import cu.uci.coj.model.Faq;
import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.validator.FaqValidator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("FaqController")
public class FaqController extends BaseController {

    @Resource
    private UtilDAO utilDao;
    @Resource
    private FaqValidator validator;

    @RequestMapping(value = "/admin/addfaq.xhtml", method = RequestMethod.GET)
    public String addFaq(Model model, @RequestParam(value = "id",required = false) Integer id) {
        
        model.addAttribute("faq", id == null?new Faq():utilDao.object("select.faq.by.id", Faq.class, id));
        return "/admin/addfaq";
    }

    @RequestMapping(value = "/admin/addfaq.xhtml", method = RequestMethod.POST)
    public String addFaq(Locale locale,Model model, Faq faq, BindingResult errors) {
        validator.validate(faq, errors);
        if (errors.hasErrors()){
            model.addAttribute("faq",faq);
            return "/admin/addfaq";
        }
        else{
            utilDao.dml("insert.faq", faq.getAnswer(),faq.getQuestion());
            return "redirect:/admin/faqs.xhtml";
        }
    }

    @RequestMapping(value = "/general/faqs.xhtml", method = RequestMethod.GET)
    public String adminListFaqs(Locale locale,Model model) {
        
        List<Faq> faqs = utilDao.objects("list.faq", Faq.class);
        
        model.addAttribute("faqs", faqs);
        return "/general/faqs";
    }
    
    @RequestMapping(value = "/admin/faqs.xhtml", method = RequestMethod.GET)
    public String listFaqs(Locale locale,Model model) {
        
        return "/admin/faqs";
    }
    
    @RequestMapping(value = "/admin/tables/faqs.xhtml", method = RequestMethod.GET)
    public String tablesListFaqs(Locale locale,Model model) {
        
List<Faq> faqs = utilDao.objects("list.faq", Faq.class);
        
        model.addAttribute("faqs", faqs);
        return "/admin/tables/faqs";
    }
    
    @RequestMapping(value = "/admin/deletefaq.xhtml", method = RequestMethod.GET)
    public String deleteFaq(@RequestParam("id") Integer id) {
        
        utilDao.dml("delete.faq", id);
        return "redirect:/admin/faqs.xhtml";
    }

}
