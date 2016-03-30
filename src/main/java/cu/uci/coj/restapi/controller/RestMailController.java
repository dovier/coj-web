/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.uci.coj.controller.mail.MailController;
import cu.uci.coj.dao.MailDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Mail;
import cu.uci.coj.restapi.templates.MailRest;
import cu.uci.coj.restapi.templates.ProblemRest;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.sendMailValidator;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cesar
 */
@Controller
@RequestMapping("/mail")
public class RestMailController {
    
        
    @Resource
	private MailDAO mailDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private sendMailValidator sendmailValidator;
	@Resource
	private MailNotificationService MailNotificationService;
        
       
    
    
    
    @RequestMapping(value = "/inbox", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getMailInbox(@RequestBody String bodyjson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username = null;
            
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            
            PagingOptions options = new PagingOptions(1);             
            IPaginatedList<Mail> mails = mailDAO.paginated("user.inbox", Mail.class, 500, options, username);
            
            List<Mail> lismails = mails.getList();
            List<MailRest> listMailRest = new LinkedList();
            
            for (Mail m : lismails) {
                Mail mailcontent = mailDAO.object("get.user.mail", Mail.class, m.getIdmail(), username);
                MailRest mailrest = new MailRest(m.getIdmail(),m.getTitle(),mailcontent.getContent(),m.getId_from(),m.getTo(), m.getDate(), m.isIsread(), m.getCclass());
                listMailRest.add(mailrest);
            }

            return new ResponseEntity<>(listMailRest, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    
    @RequestMapping(value = "/outbox", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getMailSend(@RequestBody String bodyjson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username = null;
            
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            
            PagingOptions options = new PagingOptions(1);             
            IPaginatedList<Mail> mails = mailDAO.paginated("user.outbox", Mail.class,500, options, username);
            
            List<Mail> lismails = mails.getList();
            List<MailRest> listMailRest = new LinkedList();
            
            for (Mail m : lismails) {
                Mail mailcontent = mailDAO.object("get.send.mail", Mail.class, m.getIdmail(), username);
                MailRest mailrest = new MailRest(m.getIdmail(),m.getTitle(),mailcontent.getContent(),m.getId_from(),m.getTo(), m.getDate(), m.isIsread(), m.getCclass());
                listMailRest.add(mailrest);
            }

            return new ResponseEntity<>(listMailRest, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    @RequestMapping(value = "/draft", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getMailDraft(@RequestBody String bodyjson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username = null;
            
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            
            PagingOptions options = new PagingOptions(1);             
            IPaginatedList<Mail> mails = mailDAO.paginated("user.draft", Mail.class,500, options, username);
            
            List<Mail> lismails = mails.getList();
            List<MailRest> listMailRest = new LinkedList();
            
            for (Mail m : lismails) {
                Mail mailcontent = mailDAO.object("get.draft.mail", Mail.class, m.getIdmail(), username);
                MailRest mailrest = new MailRest(m.getIdmail(),m.getTitle(),mailcontent.getContent(),m.getId_from(),m.getTo(), m.getDate(), m.isIsread(), m.getCclass());
                listMailRest.add(mailrest);
            }

            return new ResponseEntity<>(listMailRest, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    
    
    @RequestMapping(value = "/send", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> SendEmail(@RequestBody String bodyjson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }
            
            if(!TokenUtils.ValidatePropertiesinJson(node,"to","subject","content"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);

            String username = null;
            
            String token = node.get("token").textValue();
            String to = node.get("to").textValue();
            String subject = node.get("subject").textValue();
            String content = node.get("content").textValue();
            username = ExtractUser(token);
            
            Mail mail = new Mail();
            mail.setTitle(subject);
            mail.setContent(content);
            mail.setUsernameTo(to);            
           
            String errors = validateEmail(mail);
            if (errors != null) 
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            
            mail.setId_from(username);
            // result = null se mando el mensaje, "" no habia espacio en el
            // inbox del remitente, o si no hay espacio en el inbox de algun
            // receptor, se devuelve el nombre de ese receptor
            String result = sendMail(mail);
            
            if (result != null) {
                if (result.equals(""))
				return new ResponseEntity<>("inbox overflow", HttpStatus.BAD_REQUEST);
			else
				return new ResponseEntity<>("receiver inbox overflow", HttpStatus.BAD_REQUEST);
			//return new ResponseEntity<>("no 3", HttpStatus.BAD_REQUEST);
		}

		/*if (mail.isRedirectInbox()) {
			return "redirect:/mail/inbox.xhtml";
		}
		return "redirect:/mail/outbox.xhtml";*/

            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> DeleteMailByID(@RequestBody String bodyjson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

            int error = ValidateApiAndToken(bodyjson);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username = null;
            
            String token = node.get("token").textValue();
            username = ExtractUser(token);
            
            
            if(!TokenUtils.ValidatePropertiesinJson(node,"listid","where"))
                return new ResponseEntity<>(TokenUtils.ErrorMessage(10), HttpStatus.BAD_REQUEST);

           Iterator<JsonNode> it = node.get("listid").elements();
           String where=node.get("where").asText();
           
           if(!it.hasNext())
               return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
           
           try{
                while(it.hasNext()){
                    JsonNode js = it.next();
                    int idmail = js.asInt();
                    deleteMail(idmail, where, username);
                }
           }catch(Exception e){
               return new ResponseEntity<>(TokenUtils.ErrorMessage(11), HttpStatus.BAD_REQUEST);
           }
            
           return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    

    private void deleteMail(int idmail,String where,String username) {

        if (where.equals("inbox")) {
            mailDAO.dml("update.quote", username, username, username, username, userDAO.integer("select.uid.by.username", username));
            mailDAO.dml("delete.user.mail.idmail", idmail, username);
        } else if (where.equals("outbox")) {
            mailDAO.dml("update.quote", username, username, username, username, userDAO.integer("select.uid.by.username", username));
            mailDAO.dml("delete.send.mail.idmail", idmail, username);
        } else if (where.equals("draft")) {
            mailDAO.dml("update.quote", username, username, username, username, userDAO.integer("select.uid.by.username", username));
            mailDAO.dml("delete.draft.mail.iddraft", idmail, username);
        }
    }
    
    private String sendMail(Mail mail) {
		int size = mail.getContent().getBytes().length + mail.getTitle().getBytes().length;

		// metemos el arreglo de remitentes en un set para eliminar los
		// duplicados.
		Set<String> toSet = new HashSet<String>(Arrays.asList(mail.getUsernameTo().split(";")));

		if (mailDAO.bool("check.quota", size, mail.getId_from())) {
			for (String to : toSet) {
				String string = to.replaceAll(" ", "");

				if (mailDAO.bool("check.quota", size, to)) {
					int idmail = mailDAO.insertInternalEmail(mail, string, size);
					mail.setIdmail(idmail);
					MailNotificationService.sendNewPrivateMessageNotification(mail, string);
				} else
					return to;
			}
			return null;
		}
		return "";
	}
    
    
    private String validateEmail(Mail mail) {
        String errors = null;
        ResourceBundleMessageSource r=new ResourceBundleMessageSource();
        r.setBasename("messages_en");
        try {
            if((mail.getTitle() == null || mail.getTitle().length() == 0) && (mail.getContent() == null || mail.getContent().length() == 0)) 
                return r.getMessage("errormsg.39",null, new Locale("en"));
            
            if(mail.getUsernameTo().equals("") || mail.getUsernameTo() == null)
                return r.getMessage("errormsg.40",null, new Locale("en"));
            
            String[] to = mail.getUsernameTo().split(";");
               if (to.length <= 10) {
                  for (int i = 0; i < to.length; i++) {
                      String string = to[i].replaceAll(" ", "");
                      if (!userDAO.isUser(string)) {
                          r.getMessage("errormsg.40",null, new Locale("en"));
                      }
                  }
                } else 
                   r.getMessage("errormsg.41",null, new Locale("en"));
                            
                Mail m1 = mailDAO.getMailValues(mail.getId_from());
                int max = m1.getMail_quote();
                int consumed = m1.getConsumed_quote();
                int msgSize = mail.getContent().getBytes().length + mail.getTitle().getBytes().length;
                if (consumed + msgSize > max) 
                    r.getMessage("errormsg.42",null, new Locale("en"));
          
                return errors;
        } catch (Exception e) {
            errors = "No valid mail";
        }
        
        return errors;
    }
    
    private int ValidateApiAndToken(String bodyjson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readValue(bodyjson, JsonNode.class);

        if (!node.has("apikey") || !node.has("token")) {
            return 8;
        }

        String apikey = node.get("apikey").textValue();
        String token = node.get("token").textValue();

        try {
            int error = TokenUtils.ValidateAPIKey(apikey);
            if (error > 0) {
                return error;
            }

            int error2 = TokenUtils.ValidateTokenUser(token);
            if (error2 > 0) {
                return error2;
            }
        } catch (Exception e) {
            return 9;
        }

        return 0;
    }
    
    private String ExtractUser(String token) {
        String username = null;
        try {
            username = TokenUtils.getUsernameFromToken(token);
        } catch (Exception ex) {
            Logger.getLogger(RestProblemsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return username;
    }  
    
}
