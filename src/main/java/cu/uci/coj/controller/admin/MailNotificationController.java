package cu.uci.coj.controller.admin;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.mail.MailService;
import cu.uci.coj.model.MailNotification;
import cu.uci.coj.utils.enums.NotificationMode;
import cu.uci.coj.validator.MailNotificationValidator;

@Controller("MailNotificationController")
@RequestMapping
public class MailNotificationController extends BaseController {

    @Resource
    private UtilDAO utilDAO;
    @Resource
    private MailNotificationValidator validator;
    @Resource
    private MailService mailService;

    private List<String> emailsList(NotificationMode mode, Integer id) {
        switch (mode) {
            case COJ:
                return utilDAO.strings("select.emails.by.notification");
            case COUNTRY:
                return utilDAO.strings("select.emails.by.notification.country", id);
            case INSTITUTION:
                return utilDAO.strings("select.emails.by.notification.institution", id);
            case CONTEST:
                return utilDAO.strings("select.emails.by.notification.contest", id);
        }
        return Collections.emptyList();
    }

    @RequestMapping(value = "/admin/notify.xhtml", method = RequestMethod.GET)
    public String notify(Model model) {
        model.addAttribute("notification", new MailNotification());
        return "/admin/notify";
    }

    @RequestMapping(value = "/admin/notify.xhtml", method = RequestMethod.POST)
    public String notify(Locale locale, Model model, MailNotification mailNotification, BindingResult errors) {
        validator.validate(mailNotification, errors);
        if (errors.hasErrors()) {
            model.addAttribute("notification", mailNotification);
            return "/admin/notify";
        } else {
            List<String> emails = emailsList(NotificationMode.COJ, null);
            mailService.sendBulkMessage(emails,100, mailNotification.getSubject(), mailNotification.getText(), false);
            return "redirect:/admin/index.xhtml";
        }
    }
}
