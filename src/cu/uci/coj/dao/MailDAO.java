package cu.uci.coj.dao;

import cu.uci.coj.model.Clarification;
import cu.uci.coj.model.Mail;
import java.util.List;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public interface MailDAO extends BaseDAO {

    public void sendMail(Mail mail, String usernameTo, int size);

    public Mail getMailValues(String username);

    public void sendMail(Clarification clarification);
    
    public boolean isEnabled();
    
    public int insertInternalEmail(Mail mail, String to, int size);
  
}
