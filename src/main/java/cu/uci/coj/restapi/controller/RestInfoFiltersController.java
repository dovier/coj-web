/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.restapi.templates.FilterLanguageRest;
import cu.uci.coj.restapi.templates.FiltersCOJBoardRest;
import cu.uci.coj.restapi.utils.ErrorUtils;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author cesar
 */

@Controller
@RequestMapping("/utils")
public class RestInfoFiltersController {
    
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    WbSiteDAO wbSiteDAO;
    
    //Devuelve los lenguajes disponibles para hacer un envio dado un problema.
    @RequestMapping(value = "/submit/language/{pid}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getLanguagesEnabledByProblem(@PathVariable Integer pid) {
        if (problemDAO.exists(pid) ){ 
            List<FilterLanguageRest> filters = new LinkedList();
            List<Language> languages = new LinkedList();
            languages.addAll(utilDAO.getEnabledLanguagesByProblem(pid));
            for(Language lan:languages){
                FilterLanguageRest f = new FilterLanguageRest(lan.getLid(),lan.getLanguage(),lan.getDescripcion(),lan.getKey());
                filters.add(f);
            }
            return new ResponseEntity<>(filters,HttpStatus.OK);
        }
        return new ResponseEntity<>(ErrorUtils.BAD_PID,HttpStatus.BAD_REQUEST);
    }
    
    //Devuelve todos los lenguajes disponibles disponibles del COJ.
    @RequestMapping(value = "/language", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllLanguage() {

        List<FilterLanguageRest> filters = new LinkedList();
        List<Language> languages = new LinkedList();
        languages.addAll(utilDAO.getEnabledProgramingLanguages());
        for(Language lan:languages){
            FilterLanguageRest f = new FilterLanguageRest(lan.getLid(),lan.getLanguage(),lan.getDescripcion(),lan.getKey());
            filters.add(f);
        }
        return new ResponseEntity<>(filters,HttpStatus.OK);
    }
    
    @RequestMapping(value = "/cojboard/filters", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<?> getAllCOJboardFilters() {
        List<WbSite> listsites = wbSiteDAO.getSiteList();
        List<FiltersCOJBoardRest> listFilters = new LinkedList();
        for (WbSite site : listsites) {
            FiltersCOJBoardRest filter = new FiltersCOJBoardRest(site.getSid(), site.getSite());
            listFilters.add(filter);
        }
        return new ResponseEntity<>(listFilters,HttpStatus.OK);
    }
}
