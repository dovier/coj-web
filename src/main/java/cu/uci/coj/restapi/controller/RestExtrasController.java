/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.board.service.WbContestService;
import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Faq;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.restapi.templates.CojBoardRest;
import cu.uci.coj.restapi.templates.EntriesRest;
import cu.uci.coj.restapi.templates.InputEntryRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.restapi.utils.TokenUtils;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cesar
 */
@Controller
@RequestMapping("/extras")
public class RestExtrasController {
    
    
    @Resource
    private EntryDAO entryDAO;
    @Resource
    private UserDAO userDAO;        
    @Resource
    WbContestService wbContestService;
    @Resource
    WbSiteDAO wbSiteDAO;
    @Resource
    private UtilDAO utilDao;
        
        
    @ApiOperation(value = "Obtener últimas entradas de los usuarios",  
            notes = "Devuelve los últimos comentarios realizados por los usuarios paginados como en el sitio COJ.",
            response = EntriesRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "page out of index")  })    
    @RequestMapping(value = "/entry/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllEntries(
            @ApiParam(value = "Número de la página") @PathVariable int page) {
        
        if(page<1)
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
        
        PagingOptions options = new PagingOptions(page);
        IPaginatedList<Entry> entries = entryDAO.paginated("enabled.entries.list", Entry.class, 10, options, 0);
      
        if(entries.getList().isEmpty())
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
        
        List<Entry> listEntry = entries.getList();
        List<EntriesRest> listEntriesRest = new LinkedList();

        for (Entry e : listEntry) {
            String dirAvatar = "http://coj.uci.cu/images/avatars/"+e.getUsername();
            EntriesRest er = new EntriesRest(dirAvatar,e.getUsername(),e.getDate().toString(),e.getText(), e.getRate());
            listEntriesRest.add(er);
        }

        return new ResponseEntity<>(listEntriesRest, HttpStatus.OK);

    }

    @ApiOperation(value = "Obtener próximas competencia de diferentes jueces en línea",  
            notes = "Devuelve las próximas competencias de diferentes jueces en línea posteados alrededor del mundo.",
            response = CojBoardRest.class,
            responseContainer = "List")  
    @RequestMapping(value = "/cojboard", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllCOJboard(@ApiIgnore Integer sid) {
        
        if(sid == null)
            sid = 0;
        
        if(sid<0)
            return new ResponseEntity<>(ErrorUtils.BAD_SID, HttpStatus.NOT_FOUND);
        
        PagingOptions options = new PagingOptions(1);
        options.setDirection("asc");
        options.setSort("startDate");
 
        Integer uid = null;
        Integer followed = 0;
        
        IPaginatedList<WbContest> contests = wbContestService.getContestList(sid, options, followed, uid);

        List<WbSite> list = wbSiteDAO.getSiteList();
        HashMap<Integer, WbSite> map = new HashMap();

        for (WbSite site : list) {
            map.put(site.getSid(), site);
        }
        
        List<WbContest> listContest = contests.getList();
        List<CojBoardRest> listContestRest = new LinkedList();

        for (WbContest c : listContest) {
            String nombre = c.getName();
            String site  = map.get(c.getSid()).getUrl();
            String urlcontest = c.getUrl();
            String start = c.getStartDate().toString();
            String end = c.getEndDate().toString();
            CojBoardRest cr = new CojBoardRest(nombre,site,urlcontest,start,end);
            listContestRest.add(cr);
        }

        return new ResponseEntity<>(listContestRest, HttpStatus.OK);
       
    }
    
    @ApiOperation(value = "Obtener próximas competencia dado el juez en línea",  
            notes = "Dado el identificador de un juez en línea, devuelve todas sus próximas competencias.",
            response = CojBoardRest.class,
            responseContainer = "List")  
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad sid")  })    
    @RequestMapping(value = "/cojboard/{sid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllCOJboardbySID(
            @ApiParam(value = "Identificador del juez en línea (Ver filter)") @PathVariable Integer sid) {
        return getAllCOJboard(sid);
    }    
    
    
    @ApiOperation(value = "Preguntas frecuentes (FAQs)",  
            notes = "Preguntas y respuestas frecuentes del COJ.",
            response = Faq.class,
            responseContainer = "List")  
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/faq", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Faq>  getFAQs() {
        return utilDao.objects("list.faq", Faq.class);
    }
    
    
    

    @ApiOperation(value = "Postear Entrada",  
            notes = "Permite postear una entrada.",
            response = String.class)  
    @ApiResponses(value = { @ApiResponse(code = 400, message = "incorrect request"),
                            @ApiResponse(code = 401, message = "username token mismatch, hash incorrect, token expirated, username apikey mismatch, apikey hash incorrect, apikey expirated, apikey secret incorrect, token or apikey incorrect"),
                            @ApiResponse(code = 412, message = "text must not be empty, entry text too long") })
    @RequestMapping(value = "/entry", method = RequestMethod.POST, headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> addEntry(
            @ApiParam(value = "JSON para enviar") @RequestBody InputEntryRest bodyjson ) {
        try{
            int error = ValidateApiAndToken(bodyjson.getApikey(),bodyjson.getToken());
            if (error > 0) {
                return new ResponseEntity<>(TokenUtils.ErrorMessage(error), HttpStatus.UNAUTHORIZED);
            }
            
            Entry entry = new Entry(bodyjson.getEntryText(), Calendar.getInstance().getTime());
            if(ValidateEntry(entry) != null)
                return new ResponseEntity<>("{\"error\":\""+ValidateEntry(entry)+"\"}", HttpStatus.PRECONDITION_FAILED);
            
            preProcessEntry(entry);
            String username = ExtractUser(bodyjson.getToken());
            entryDAO.addEntry(entry,isAdmin(username),username);
            
        }catch(Exception e){
            return new ResponseEntity<>(TokenUtils.ErrorMessage(8), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    

    //@ApiIgnore
    @ApiOperation(value = "Convertir correo a nombre de usuario",  
            notes = "Dado el correo de un usuario, devuelve el nombre de usuario del mismo.",
            response = String.class)  
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/convert", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getUsername(
            @ApiParam(value = "Correo electrónico") @RequestParam(required = true, value = "email") String email) {
        String username = userDAO.userByMail(email);
        return "{\"username\":\""+username+"\"}";
    }
    
    private boolean isAdmin(String username){
        List<String> roles = userDAO.getUserAuthorities(username);
        return roles.contains("ROLE_ADMIN");
    }
    
    
    private String ValidateEntry(Entry entry){
        String error = null;
        if(entry.getText().isEmpty())
            error = "text must not be empty";
        
        if ( entry.getText().length() > 255)
            error = "entry text too long";
        
        return error;        
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
    
    private void preProcessEntry(Entry entry) {
        // adicionar html para vinculos a paginas y a usuarios.
        String text = entry.getText();
        String[] tokens = text.split(" ");
        Set<String> tokenSet = new HashSet(Arrays.asList(tokens));
        for (String token : tokenSet) {
            token = token.trim();
            if (token.startsWith("p#")) {
                if (NumberUtils.isNumber(token.substring(2))) {
                    entryDAO.bool("select exists(select pid from problem where pid=?)", Integer.valueOf(token.substring(2)));
                    text = text.replace(token, "<a target=\"new\" href=\"24h/problem.xhtml?pid=" + token.substring(2) + "\" >" + token.substring(2) + "</a>");
                }
            } else if (token.startsWith("c#")) {
                if (NumberUtils.isNumber(token.substring(2))) {
                    entryDAO.bool("select exists(select pid from contest where pid=?)", Integer.valueOf(token.substring(2)));
                    text = text.replace(token, "<a target=\"new\" href=\"contest/contestview.xhtml?cid=" + token.substring(1) + "\" >" + token.substring(1) + "</a>");
                }
            } else if (token.startsWith("@")) {
                boolean reply = entryDAO.bool("select exists (select uid from users where username=?)", token.substring(1));
                entry.setReply(true);
                entry.setHasUsers(true);
                if (reply)
                    text = text.replace(token, "<a target=\"new\" href=\"user/useraccount.xhtml?username=" + token.substring(1) + "\" >" + token + "</a>");
            } else if (token.startsWith("http://") || token.startsWith("https://")) {
                text = text.replace(token, "<a target=\"new\" href=\"" + token + "\">" + token + "</a>");
                entry.setHasLinks(true);
            }
        }
        entry.setText(text);
    }
    
    private String ExtractUser(String token) {
        String username = null;
        try {
            username = TokenUtils.getUsernameFromToken(token);
            
        } catch (Exception exp) {
            
        }

        return username;
    }  
    
}
