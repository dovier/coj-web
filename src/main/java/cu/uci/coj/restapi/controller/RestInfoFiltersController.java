/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.dao.InstitutionDAO;
import cu.uci.coj.dao.ProblemDAO;
import cu.uci.coj.dao.UtilDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.model.Language;
import cu.uci.coj.model.Locale;
import cu.uci.coj.model.ProblemClassification;
import cu.uci.coj.model.WbSite;
import cu.uci.coj.restapi.templates.ContestRest;
import cu.uci.coj.restapi.templates.CountryRest;
import cu.uci.coj.restapi.templates.FilterClassificationRest;
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
@RequestMapping("/filter")
public class RestInfoFiltersController {
    
    @Resource
    private ProblemDAO problemDAO;
    @Resource
    private UtilDAO utilDAO;
    @Resource
    WbSiteDAO wbSiteDAO;
    
    
    @ApiOperation(value = "Obtener lenguajes habilitados dado un problema",  
            notes = "Dado el identificador de un problema, devuelve una lista de todos los lenguajes en los que se le peude hacer un envío.",
            response = FilterLanguageRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 404, message = "bad pid")  })
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
        return new ResponseEntity<>(ErrorUtils.BAD_PID,HttpStatus.NOT_FOUND);
    }
    
    @ApiOperation(value = "Obtener todos los lenguajes habilitados",  
            notes = "Devuelve todos los lenguajes disponibles del COJ.",
            response = FilterLanguageRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/language", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<FilterLanguageRest> getAllLanguage() {

        List<FilterLanguageRest> filters = new LinkedList();
        List<Language> languages = new LinkedList();
        languages.addAll(utilDAO.getEnabledProgramingLanguages());
        for(Language lan:languages){
            FilterLanguageRest f = new FilterLanguageRest(lan.getLid(),lan.getLanguage(),lan.getDescripcion(),lan.getKey());
            filters.add(f);
        }
        return filters;
    }
    
    @ApiOperation(value = "Obtener todos los jueces en línea",  
            notes = "Devuelve todos los jueces en línea para poder utilizarlos como filtro en otros servicios.",
            response = FiltersCOJBoardRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/cojboard", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<FiltersCOJBoardRest> getAllCOJboardFilters() {
        List<WbSite> listsites = wbSiteDAO.getSiteList();
        List<FiltersCOJBoardRest> listFilters = new LinkedList();
        for (WbSite site : listsites) {
            FiltersCOJBoardRest filter = new FiltersCOJBoardRest(site.getSid(), site.getSite());
            listFilters.add(filter);
        }
        return listFilters;
    }
    
    @ApiOperation(value = "Obtener todos los idiomas habilitados",  
            notes = "Devuelve todos los idiomas disponibles en el COJ.",
            response = Locale.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/locales", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<Locale> getAllLocales() {    
        return utilDAO.objects("enabled.locale", Locale.class);   
    }
    
    
     @ApiOperation(value = "Obtener las diferentes clasificaciones de los problemas",  
            notes = "Devuelve todos las clasificaciones que tiene un problema para utilizarlas como filtro en otros servicios.",
            response = FilterClassificationRest.class,
            responseContainer = "List")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Ejemplo de respuesta del método")  })
    @RequestMapping(value = "/classification", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public List<FilterClassificationRest> getAllClassification() {  
        List<ProblemClassification> classifications = problemDAO.objects("problem.classifications", ProblemClassification.class);
        List<FilterClassificationRest> classificationsRest = new LinkedList();
        
        for(ProblemClassification p:classifications)
            classificationsRest.add(new FilterClassificationRest(p.getIdClassification(), p.getName()));
        
        return classificationsRest;   
    }
    
    
    
}
