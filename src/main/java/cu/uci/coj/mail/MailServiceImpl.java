package cu.uci.coj.mail;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender mailSender;
    
    @Resource
    private SimpleMailMessage mailMessage;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public SimpleMailMessage getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void sendBulkMessage(List<String> emails, int bulkSize, String subject, String message, boolean html) {

        int arrSiz = Math.min(emails.size(), bulkSize);
        String[] bulk = new String[arrSiz];
        int i = 0, j = 0;
        while (i < emails.size()) {
            bulk[j++] = emails.get(i++);
            if (j == arrSiz) {
            	sendBulkMessage(bulk, subject, message, html);            	
            	j = 0;
            }
        }

        if (j != 0) {
            while (j > 0) {
            	sendMessage(bulk[--j], subject, message, html);
            }
        }
    }    
    

	public void sendMessage(String userMail, String subject, String message, boolean html) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
			helper.setBcc(userMail);
			helper.setSubject(subject);
			helper.setText(message, html);
			helper.setFrom(mailMessage.getFrom());
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
        this.mailSender.send(mimeMessage);
	}
	
	public void sendMessageWithAttachment(String userMail, String subject, String message, String filename, String attachment, boolean html){
		MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setBcc(userMail);
			helper.setSubject(subject);
			helper.setText(message, html);
			helper.setFrom(mailMessage.getFrom());
			helper.addAttachment(filename, new ByteArrayResource(attachment.getBytes()));
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
        this.mailSender.send(mimeMessage);
	}
	
	
	public void sendBulkMessage(String[] userMail, String subject, String message, boolean html) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
			helper.setBcc(userMail);
			helper.setSubject(subject);
			helper.setText(message, html);
			helper.setFrom(mailMessage.getFrom());			
		} catch (MessagingException e) {			
			e.printStackTrace();
		}
        mailSender.send(mimeMessage);
	}
}
