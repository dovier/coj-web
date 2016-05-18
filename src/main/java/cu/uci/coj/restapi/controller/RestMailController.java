/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.dao.MailDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.mail.MailNotificationService;
import cu.uci.coj.model.Mail;
import cu.uci.coj.restapi.templates.InputMailSend;
import cu.uci.coj.restapi.templates.MailRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.sendMailValidator;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
        
       
    
    
    @ApiOperation(value = "Obtener los correos entrantes",  
            notes = "Devuelve los correos de la bandeja de entrada (inbox).",
            response = MailRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect"),
                            @ApiResponse(code = 400, message = "incorrect request")  })
    @RequestMapping(value = "/inbox", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getMailInbox(
             @ApiParam(value = "Llave de desarrollador") @RequestHeader(value = "apikey", required = false) String apikey,
             @ApiParam(value = "Token de usuario") @RequestHeader(value = "token", required = false) String token   ) {
        try {

            int error = ValidateApiAndToken(apikey, token);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username;
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
    
    
    @ApiOperation(value = "Obtener los correos salientes",  
            notes = "Devuelve los correos de la bandeja de salida (outbox).",
            response = MailRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect"),
                            @ApiResponse(code = 400, message = "incorrect request")  })
    @RequestMapping(value = "/outbox", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getMailSend(
            @ApiParam(value = "Llave de desarrollador") @RequestHeader(value = "apikey", required = false) String apikey,
            @ApiParam(value = "Token de usuario") @RequestHeader(value = "token", required = false) String token   ) {
        try {
            
            int error = ValidateApiAndToken(apikey,token);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username;
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
    
    @ApiOperation(value = "Obtener los borradores",  
            notes = "Devuelve los correos de la bandeja de borradores (draft).",
            response = MailRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect"),
                            @ApiResponse(code = 400, message = "incorrect request")  })
    @RequestMapping(value = "/draft", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getMailDraft(
            @ApiParam(value = "Llave de desarrollador") @RequestHeader(value = "apikey", required = false) String apikey,
            @ApiParam(value = "Token de usuario") @RequestHeader(value = "token", required = false) String token   ) {
        try {

            int error = ValidateApiAndToken(apikey,token);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username;
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
    
    
    @ApiOperation(value = "Enviar un correo",  
            notes = "Envía un correo a un usuario registrado del COJ.")
    @ApiResponses(value = { @ApiResponse(code = 412, message = "receiver inbox overflow<br> inbox_overflow<br> message body or subject required<br> there must be at least one recipient<br> at least one recipient doesn't exist<br> at most 10 recipients<br> no valid mail<br> quote overflow"),
                            @ApiResponse(code = 400, message = "incorrect request"),
                            @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect") } )
    @RequestMapping(value = "/send", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> SendEmail(
            @ApiParam(value = "JSON para enviar") @RequestBody InputMailSend bodyjson)  {
        try {
            int error = ValidateApiAndToken(bodyjson.getApikey(),bodyjson.getToken());
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username;
            
            String token = bodyjson.getToken();
            String to = bodyjson.getTo();
            String subject = bodyjson.getSubject();
            String content = bodyjson.getContent();
            username = ExtractUser(token);
            
            Mail mail = new Mail();
            mail.setTitle(subject);
            mail.setContent(content);
            mail.setUsernameTo(to);            
            mail.setId_from(username);
            String errors = validateEmail(mail);
            if (errors != null) 
                return new ResponseEntity<>("{\"error\":\""+errors+"\"}", HttpStatus.PRECONDITION_FAILED);
            
            
            // result = null se mando el mensaje, "" no habia espacio en el
            // inbox del remitente, o si no hay espacio en el inbox de algun
            // receptor, se devuelve el nombre de ese receptor
            String result = sendMail(mail);
            
            if (result != null) {
                if (result.equals(""))
				return new ResponseEntity<>(ErrorUtils.INBOX_OVERFLOW, HttpStatus.PRECONDITION_FAILED);
			else
				return new ResponseEntity<>(ErrorUtils.RECEIVER_INBOX_OVERFLOW, HttpStatus.PRECONDITION_FAILED);
			//return new ResponseEntity<>("no 3", HttpStatus.BAD_REQUEST);
		}

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    
    @ApiOperation(value = "Marcar como leído/no leído",
            notes = "Marca el correo a leído/no leído dado el identificador del mismo.")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect"),
                            @ApiResponse(code = 404, message = "invalid email"),
                            @ApiResponse(code = 400, message = "incorrect request")})
    @RequestMapping(value = "/toggle/status/{email_id}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> DeleteMailByID(
            @ApiParam(value = "Identificador del correo", required = true) @PathVariable Integer email_id,           
            @ApiParam(value = "Llave de desarrollador") @RequestHeader(value = "apikey", required = true) String apikey,
            @ApiParam(value = "Token de usuario") @RequestHeader(value = "token", required = true) String token           ) {
        try{
           
            int error = ValidateApiAndToken(apikey,token);
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }

            String username;            
            username = ExtractUser(token);               
           
            if (email_id > 0 && mailDAO.bool("mail.belong.to", email_id, username)){ 
                Mail mail = mailDAO.object("get.user.mail", Mail.class, email_id, username);
                if(mail == null)
                    return new ResponseEntity<>(ErrorUtils.INVALID_EMAIL,HttpStatus.NOT_FOUND);
                
                if (!mail.isIsread()) 
                    mailDAO.dml("change.read.state", true, email_id);
                else     
                    mailDAO.dml("change.read.state", false, email_id);            
            } 
            else
                return new ResponseEntity<>(ErrorUtils.INVALID_EMAIL,HttpStatus.NOT_FOUND);
            
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }
    
    
    
    @ApiOperation(value = "Eliminar un correo",
            notes = "Elimina un correo espesificando la bandeja donde se encuentra.")
    @ApiResponses(value = { @ApiResponse(code = 401, message = "username token mismatch<br> hash incorrect<br> token expirated<br> username apikey mismatch<br> apikey hash incorrect<br> apikey expirated<br> apikey secret incorrect<br> token or apikey incorrect"),
                            @ApiResponse(code = 400, message = "incorrect request")  })
    @RequestMapping(value = "/delete/{where}/{email_id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> DeleteMailByID(
            @ApiParam(value = "Identificador del correo a eliminar", required = true) @PathVariable Integer email_id,
            @ApiParam(value = "Bandeja donde se encuentra el correo", required = true, allowableValues = "inbox,outbox,draft") @PathVariable String where,            
            @ApiParam(value = "Llave de desarrollador") @RequestHeader(value = "apikey", required = false) String apikey,
            @ApiParam(value = "Token de usuario") @RequestHeader(value = "token", required = false) String token           ) {
        try {
           
           int error = ValidateApiAndToken(apikey,token);
           if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
           }

           String username;            
           username = ExtractUser(token);               
           
           deleteMail(email_id, where, username);  
            
           return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException ex) {
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }

    }

    private void deleteMail(int idmail,String where,String username) {

        switch (where) {
            case "inbox":
                mailDAO.dml("update.quote", username, username, username, username, userDAO.integer("select.uid.by.username", username));
                mailDAO.dml("delete.user.mail.idmail", idmail, username);
                break;
            case "outbox":
                mailDAO.dml("update.quote", username, username, username, username, userDAO.integer("select.uid.by.username", username));
                mailDAO.dml("delete.send.mail.idmail", idmail, username);
                break;
            case "draft":
                mailDAO.dml("update.quote", username, username, username, username, userDAO.integer("select.uid.by.username", username));
                mailDAO.dml("delete.draft.mail.iddraft", idmail, username);
                break;
        }
    }


    
    private String sendMail(Mail mail) {
		int size = mail.getContent().getBytes().length + mail.getTitle().getBytes().length;

		// metemos el arreglo de remitentes en un set para eliminar los
		// duplicados.
		Set<String> toSet = new HashSet(Arrays.asList(mail.getUsernameTo().split(";")));

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
                return r.getMessage("errormsg.39",null, new Locale("en")).toLowerCase();
            
            if(mail.getUsernameTo().equals("") || mail.getUsernameTo() == null)
                return r.getMessage("errormsg.40",null, new Locale("en")).toLowerCase();
            
            String[] to = mail.getUsernameTo().split(";");
               if (to.length <= 10) {
                for (String to1 : to) {
                    String string = to1.replaceAll(" ", "");
                    if (!userDAO.isUser(string)) {
                        return r.getMessage("errormsg.41",null, new Locale("en")).toLowerCase();
                    }
                }
                } else 
                   return r.getMessage("errormsg.42",null, new Locale("en")).toLowerCase();
                            
                Mail m1 = mailDAO.getMailValues(mail.getId_from());
                int max = m1.getMail_quote();
                int consumed = m1.getConsumed_quote();
                int msgSize = mail.getContent().getBytes().length + mail.getTitle().getBytes().length;
                if (consumed + msgSize > max) 
                    return "quote overflow";
          
                return errors;
        } catch (Exception e) {
            errors = "no valid mail";
        }
        
        return errors;
    }
    
    private int ValidateApiAndToken(String apikey, String token) throws IOException {
        

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
            
        } catch (Exception exp) {
            Logger.getLogger(RestProblemsController.class.getName()).log(Level.SEVERE, null, exp);
        }

        return username;
    }  
    
}
