package cu.uci.coj.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.model.SharedFile;
import cu.uci.coj.model.User;
import cu.uci.coj.utils.HandlerInterceptorImpl;
import cu.uci.coj.utils.SessionsRecord;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

@Controller
@RequestMapping(value = "/")
public class SharedFilesController extends BaseController {

	@RequestMapping(value = "/general/downloads.xhtml", method = RequestMethod.POST)
	public String downloads(Model model, @RequestParam(defaultValue = "1", value = "page", required = false) int page,
			@RequestParam(defaultValue = "", value = "search", required = false) String search) {

		IPaginatedList<SharedFile> files = null;
		if (StringUtils.isEmpty(search)) {
			files = baseDAO.paginated("list.shared.files", SharedFile.class,  30,new PagingOptions(page));
		} else {
			files = baseDAO.paginated("list.shared.files.pattern", SharedFile.class, 30,new PagingOptions(page),"%" + search + "%");
		}
		model.addAttribute("files", files);
		return "/general/downloads";
	}

	@RequestMapping(value = "/general/downloads.xhtml", method = RequestMethod.GET)
	public String downloads(Model model) {
		return "/general/downloads";
	}

	
	@RequestMapping(value = "/general/pagedownloads.xhtml", method = RequestMethod.POST)
	public String pageFollowers(Model model,@RequestParam("page") int page) {
		IPaginatedList<SharedFile> files = baseDAO.paginated("list.shared.files", SharedFile.class,  30,new PagingOptions(page));
		model.addAttribute("files", files);
		return "/general/pagedownloads";
	}

}
