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
import cu.uci.coj.restapi.templates.ContestDescriptionRest;
import cu.uci.coj.restapi.templates.EntriesRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import cu.uci.coj.utils.EntryHelper;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.entryValidator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	private entryValidator entriesValidator;
	@Resource
	private EntryHelper entryHelper;

	private Map<String, String> emoties = new HashMap<String, String>();
        
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
    @RequestMapping(value = "/welcome/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllEntries(
            @ApiParam(value = "Número de la página") @PathVariable int page) {
        
        if(page<1)
            return new ResponseEntity<>(ErrorUtils.PAGE_OUT_OF_INDEX, HttpStatus.BAD_REQUEST);
        
        PagingOptions options = new PagingOptions(page);
        IPaginatedList<Entry> entries = entryDAO.paginated("enabled.entries.list", Entry.class, 10, options, 0);
      
        if(entries.getList().size() == 0)
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
            notes = "Devuelve las próximas competencias de diferentes jueces en línea posteados alrededor del del mundo.",
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
        HashMap<Integer, WbSite> map = new HashMap<Integer, WbSite>();

        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i).getSid(), list.get(i));
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
    

    @ApiIgnore
    @ApiOperation(value = "Convertir correo a nombre de usuario",  
            notes = "Dado el correo de un usuario, devuelve el nombre de usuario del mismo.",
            response = String.class)  
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/convert", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getUsername(
            @ApiParam(value = "Correo electrónico") @RequestParam(required = true, value = "email") String email) {
        String username = userDAO.userByMail(email);
        return username;
    }
    
}
