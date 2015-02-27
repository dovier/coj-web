
package cu.uci.coj.dao.impl;

import cu.uci.coj.dao.MailDAO;
import cu.uci.coj.model.Clarification;
import cu.uci.coj.model.Mail;

import java.util.HashMap;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @version 2.0
 * @since 2010-09-01
 */
@Repository("mailDAO")
@Transactional
public class MailDAOImpl extends BaseDAOImpl implements MailDAO {

	
	
    @Transactional(readOnly=true)
    public Mail getMailValues(String username) {
        Mail mail = object("mail.values", Mail.class,username);
        mail.percent();
        return mail;
    }

    @Transactional
    public void sendMail(Clarification clarification) {
        HashMap<String, String> hash = new HashMap<String, String>();
        
        
        int size = clarification.getDescription().getBytes().length + clarification.getTitle().getBytes().length;
        String[] to = clarification.getUsernameto().split(";");
        for (int i = 0; i < to.length; i++) {
            String string = to[i].replaceAll(" ", "");
            if (hash.containsKey(string)) {
                continue;
            }
            hash.put(string, string);
            dml("send.mail.1", clarification.getUsernamefrom(), string, clarification.getTitle(), clarification.getDescription(), size, clarification.getUsernameto());
            dml("update.quote",string,string, string, string, idByUsername(string));
        }

        dml("send.mail", clarification.getUsernamefrom(), clarification.getUsernameto(), clarification.getTitle(), clarification.getDescription(), clarification.getDescription().getBytes().length + clarification.getTitle().getBytes().length);
        dml("update.quote",clarification.getUsernamefrom(),clarification.getUsernameto(),clarification.getUsernamefrom(),clarification.getUsernamefrom(), idByUsername(clarification.getUsernamefrom()));
    }

    @Transactional
    public void sendMail(Mail mail, String usernameTo, int size) {
        dml("insert.mail", mail.getId_from(), usernameTo, mail.getTitle(), mail.getContent(), size, mail.getUsernameTo());
        dml("insert.mail.2", size, usernameTo);
    }

    @Transactional(readOnly=true)
    public boolean isEnabled() {
        return bool("get.mail.flag.status");
    }
    
    @Transactional
    public int insertInternalEmail(Mail mail, String to, int size) {
    	int idmail = jdbcTemplate.queryForInt(getSql("insert.mail") , mail.getId_from(), to, mail.getTitle(), mail.getContent(), size, mail.getUsernameTo());
		dml("insert.mail.2", size, to);
		dml("register.send.mail", mail.getId_from(), to, mail.getTitle(), mail.getContent(), size);
		dml("update.quote", mail.getId_from(),mail.getTo(),mail.getId_from(),mail.getId_from(),idByUsername(mail.getId_from()));
		return idmail;
    }
    

}
