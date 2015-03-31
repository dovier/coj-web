/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.mail;

import java.util.List;

public interface MailService {
	public void sendBulkMessage(List<String> emails, int bulkSize, String subject, String message, boolean html);	
	public void sendMessage(String userMail, String subject, String message, boolean html);
	public void sendMessageWithAttachment(String userMail, String subject, String message, String filename, String attachment, boolean html);
	public void sendBulkMessage(String[] userMail, String subject, String message, boolean html);
}
