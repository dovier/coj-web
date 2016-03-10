/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.controller;

import cu.uci.coj.dao.EntryDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.utils.EntryHelper;
import cu.uci.coj.validator.entryValidator;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
