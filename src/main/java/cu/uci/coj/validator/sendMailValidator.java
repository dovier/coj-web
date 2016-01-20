/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.validator;

import cu.uci.coj.dao.MailDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Mail;
import java.util.Iterator;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class sendMailValidator implements Validator {

    @Resource
    private MailDAO mailDAO;
    @Resource
    private UserDAO userDAO;

    public boolean supports(Class<?> clazz) {
        return Mail.class.isAssignableFrom(clazz);
    }

    public void validate(Object o, Errors errors) {
        Mail mail = (Mail) o;
        try {
            if((mail.getTitle() == null || mail.getTitle().length() == 0) && (mail.getContent() == null || mail.getContent().length() == 0))
            {
                errors.rejectValue("title", "errormsg.39");
            }
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "usernameTo", "errormsg.40");
            if (!errors.hasFieldErrors("usernameTo")) {
                String[] to = mail.getUsernameTo().split(";");
                if (to.length <= 10) {
                    for (int i = 0; i < to.length; i++) {
                        String string = to[i].replaceAll(" ", "");
                        if (!userDAO.isUser(string)) {
                            errors.rejectValue("usernameTo", "general.error.userproblem");
                            break;
                        }
                    }
                } else {
                    errors.rejectValue("usernameTo", "errormsg.41");
                }
            }
            if (!errors.hasErrors()) {
                Mail m1 = mailDAO.getMailValues(mail.getId_from());
                int max = m1.getMail_quote();
                int consumed = m1.getConsumed_quote();
                int msgSize = mail.getContent().getBytes().length + mail.getTitle().getBytes().length;
                if (consumed + msgSize > max) {
                    errors.rejectValue("general", "errormsg.42");
                }
            }
            for (Iterator<?> it = errors.getAllErrors().iterator(); it.hasNext();) {
                Object object = it.next();
                System.out.println(object);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
