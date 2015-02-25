/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.api.json.controller;

import cu.uci.coj.controller.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/services")
public class ServicesControllers  extends BaseController{
        
    @RequestMapping(value = "/specification.xhtml", method = RequestMethod.GET)
    public String servicesSpecification() {        
        return "/services/specification";
    }
    
}
