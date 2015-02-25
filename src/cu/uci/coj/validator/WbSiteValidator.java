package cu.uci.coj.validator;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cu.uci.coj.board.dao.WbSiteDAO;
import cu.uci.coj.model.WbContest;
import cu.uci.coj.model.WbSite;

@Component
public class WbSiteValidator implements Validator {
	
	@Resource
	WbSiteDAO wbSiteDAO;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return WbSite.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "site", "wboard.error.nosite", "Field is empy.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeanddateid", "wboard.error.notimeanddate", "Field is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "wboard.error.nocode", "Field is empty.");
		
		
		if(((WbSite)o).getTimeanddateid() < 1) {
			errors.rejectValue("timeanddateid", "wboard.error.timeanddateid.invalid", "Select a valid value greater than 0.");
		}
		
		if(wbSiteDAO.getSiteId(((WbSite)o).getSite()) != null) {
			errors.rejectValue("site", "wboard.error.site.exists", "This site already exists.");
		}
		
		if(((WbSite) o).getTimezone().equals("0")) {
			errors.rejectValue("timezone", "wboard.error.notimezone", "Select a timezone.");
		}		
		
		try {
			new URL(((WbSite) o).getUrl());
		} catch (MalformedURLException e) {
			errors.rejectValue("url", "wboard.error.url.invalid", "Invalid url.");
		}
	}
	
	public void validateUpdate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "site", "wboard.error.empty", "Field is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "timeanddateid", "wboard.error.empty", "Field is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code", "wboard.error.empty", "Field is empty.");
		
		
		if(((WbSite)o).getTimeanddateid() < 1) {
			errors.rejectValue("timeanddateid", "wboard.error.timeanddateid.invalid", "Select a valid value greater than 0.");
		}
		
		if(((WbSite) o).getTimezone().equals("0")) {
			errors.rejectValue("timezone", "wboard.error.notimezone", "Select a timezone.");
		}
		
		try {
			new URL(((WbSite) o).getUrl());
		} catch (MalformedURLException e) {
			errors.rejectValue("url", "wboard.error.url.invalid", "Invalid url.");
		}
	}

}
