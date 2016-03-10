/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Entry;
import cu.uci.coj.model.Problem;
import cu.uci.coj.recommender.Recommender;
import cu.uci.coj.restapi.templates.EntriesRest;
import cu.uci.coj.restapi.templates.ProblemRest;
import cu.uci.coj.restapi.utils.TokenUtils;
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
        
        
    @RequestMapping(value = "/welcome/{page}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllEntries(@PathVariable int page) {
        
        if(page<1)
            return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
        
        PagingOptions options = new PagingOptions(page);
        IPaginatedList<Entry> entries = entryDAO.paginated("enabled.entries.list", Entry.class, 10, options, 0);
      
        if(entries.getList().size() == 0)
            return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
        
        List<Entry> listEntry = entries.getList();
        List<EntriesRest> listEntriesRest = new LinkedList();

        for (Entry e : listEntry) {
            String dirAvatar = "http://coj.uci.cu/images/avatars/"+e.getUsername();
            EntriesRest er = new EntriesRest(dirAvatar,e.getUsername(),e.getDate().toString(),e.getText(), e.getRate());
            listEntriesRest.add(er);
        }

        return new ResponseEntity<>(listEntriesRest, HttpStatus.OK);

    }
    
    @RequestMapping(value = "/cojboard", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllCOJboard() {
        
        if(true)
            return new ResponseEntity<>("offline", HttpStatus.BAD_REQUEST);
        
        /*PagingOptions options = new PagingOptions(page);
        IPaginatedList<Entry> entries = entryDAO.paginated("enabled.entries.list", Entry.class, 10, options, 0);
      
        if(entries.getList().size() == 0)
            return new ResponseEntity<>("page out of index", HttpStatus.BAD_REQUEST);
        
        List<Entry> listEntry = entries.getList();
        List<EntriesRest> listEntriesRest = new LinkedList();

        for (Entry e : listEntry) {
            String dirAvatar = "http://coj.uci.cu/images/avatars/"+e.getUsername();
            EntriesRest er = new EntriesRest(dirAvatar,e.getUsername(),e.getDate().toString(),e.getText(), e.getRate());
            listEntriesRest.add(er);
        }*/

      //  return new ResponseEntity<>(listEntriesRest, HttpStatus.OK);
        
        return null;

    }
}
