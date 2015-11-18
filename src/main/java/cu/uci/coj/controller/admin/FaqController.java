package cu.uci.coj.controller.admin;

import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Faq;
import cu.uci.coj.model.Language;
import cu.uci.coj.validator.FaqValidator;
import cu.uci.coj.utils.Notification;

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
    public String addFaq(Locale locale,Model model, Faq faq, BindingResult errors, RedirectAttributes redirectAttributes) {
        validator.validate(faq, errors);
        if (errors.hasErrors()){
            model.addAttribute("faq",faq);
            return "/admin/addfaq";
        }
        else{
            utilDao.dml("insert.faq", faq.getAnswer(),faq.getQuestion());
            redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullCreate());
            return "redirect:/admin/faqs.xhtml";
        }
    }

    @RequestMapping(value = "/general/faqs.xhtml", method = RequestMethod.GET)
    public String adminListFaqs(Locale locale,Model model) {
        
        List<Faq> faqs = utilDao.objects("list.faq", Faq.class);
        model.addAttribute("languages",baseDAO.objects("select.language.faq", Language.class));
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
    public String deleteFaq(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        
        utilDao.dml("delete.faq", id);
        redirectAttributes.addFlashAttribute("message", Notification.getSuccesfullDelete());
        return "redirect:/admin/faqs.xhtml";
    }

}
