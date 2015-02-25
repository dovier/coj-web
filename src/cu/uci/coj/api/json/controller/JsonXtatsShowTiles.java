/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Z
 */
@Controller
@RequestMapping(value = "/json/stats")
@Scope("session")
public class JsonXtatsShowTiles {
    
    
    @RequestMapping("/records.xhtml")
    public String records(Model model){
        return "/xtats/records";
    }
    
    @RequestMapping("/offrecords.xhtml")
    public String offrecords(Model model){
        return "/xtats/offrecords";
    }
    
    @RequestMapping("/problems.xhtml")
    public String newproblems(Model model){
        return "/xtats/problems";
    }
    
    @RequestMapping("/distsubmitions.xhtml")
    public String submitdistribution(Model model){
        return "/xtats/distsubmitions";
    }
    
    @RequestMapping("/distsubuser.xhtml")
    public String distsubuser(Model model,@RequestParam String user){
    	model.addAttribute("username", user);
        return "/xtats/distsubuser";
    }
    
    @RequestMapping("/veredictstl.xhtml")
    public String cojveredicts(Model model){
        return "/xtats/veredictstl";
    }
    
    @RequestMapping("/problemsubmitions.xhtml")
    public String problemsubmitions(Model model){
        return "/xtats/problemsubmitions";
    }
    
    @RequestMapping("/compareproblems.xhtml")
    public String compareproblems(Model model){
        return "/xtats/compareproblems";
    }
    
    @RequestMapping("/veredictsproblems.xhtml")
    public String veredictsproblems(Model model){
        return "/xtats/veredictsproblems";
    }
    
    @RequestMapping("/contestsubmitions.xhtml")
    public String contestsubmitions(Model model){
        return "/xtats/contestsubmitions";
    }
    
     @RequestMapping("subaftercontest.xhtml")
    public String subaftercontest(Model model){
        return "/xtats/subaftercontest";
    }
    
    @RequestMapping("veredictscontest.xhtml")
    public String veredictscontest(Model model){
        return "/xtats/veredictscontest";
    }
    
    @RequestMapping("developmentincontest.xhtml")
    public String developmentincontest(Model model){
        return "/xtats/developmentincontest";
    }
    
    @RequestMapping("problemscontest.xhtml")
    public String problemscontest(Model model){
        return "/xtats/problemscontest";
    }
    
    @RequestMapping("compareusers.xhtml")
    public String compareusers(Model model){
        return "/xtats/compareusers";
    }
    
       
    
}
