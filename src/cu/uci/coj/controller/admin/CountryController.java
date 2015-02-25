package cu.uci.coj.controller.admin;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.dao.CountryDAO;
import cu.uci.coj.model.Country;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import cu.uci.coj.validator.countryValidator;

@Controller
@RequestMapping(value = "/admin")
public class CountryController extends BaseController {

	@Resource
	private CountryDAO countryDAO;
	@Resource
	private countryValidator countryValidator;

	@RequestMapping(value = "/managecountries.xhtml", method = RequestMethod.GET)
	public String listCountries(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		model.addAttribute("pattern", pattern);
		return "/admin/managecountries";
	}
	
	@RequestMapping(value = "/tables/managecountries.xhtml", method = RequestMethod.GET)
	public String tablesListCountries(Model model, PagingOptions options, @RequestParam(required = false, value = "pattern") String pattern) {
		IPaginatedList<Country> countries = countryDAO.getCountry(pattern, options);
		model.addAttribute("countries", countries);
		model.addAttribute("found", countries.getFullListSize());
		return "/admin/tables/managecountries";
	}

	@RequestMapping(value = "/addcountry.xhtml", method = RequestMethod.GET)
	public String addCountry(Model model) {
		model.addAttribute(new Country());
		return "/admin/addcountry";
	}

	@RequestMapping(value = "/addcountry.xhtml", method = RequestMethod.POST)
	public String addCountry(Model model, Principal principal, Country country, BindingResult result) {
		countryValidator.validate(country, result);
		if (result.hasErrors()) {
			model.addAttribute(country);
			return "/admin/addcountry";
		}
		countryDAO.dml("add.country.2", country.getName(), country.getZip(), country.getZip_two(), country.getWebsite());
		countryDAO.dml("insert.log", "inserting country " + country.getName(), getUsername(principal));
		return "redirect:/admin/managecountries.xhtml?pattern=" + country.getName();
	}

	@RequestMapping(value = "/managecountry.xhtml", method = RequestMethod.GET)
	public String editCountry(Model model, @RequestParam("country_id") Integer country_id) {
		Country country = countryDAO.object("country.by.id.enabled", Country.class, country_id);
		country.setId(country_id);
		model.addAttribute(country);
		return "/admin/managecountry";
	}

	@RequestMapping(value = "/managecountry.xhtml", method = RequestMethod.POST)
	public String editCountry(Model model, Principal principal, Country country, BindingResult result) {
		countryValidator.validateUpdate(country, result);
		if (result.hasErrors()) {
			model.addAttribute(country);
			return "/admin/managecountry";
		}
		countryDAO.dml("update.country.2", country.getName(), country.getZip(), country.getZip_two(), country.getWebsite(), country.isEnabled(), country.getId());
		countryDAO.dml("insert.log", "updating country " + country.getId(), getUsername(principal));
		return "redirect:/admin/managecountries.xhtml?pattern=" + country.getName();
	}
}
