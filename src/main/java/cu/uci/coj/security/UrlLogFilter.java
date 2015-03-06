package cu.uci.coj.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import cu.uci.coj.dao.BaseDAO;

public class UrlLogFilter extends GenericFilterBean {

	private BaseDAO baseDAO;

	public UrlLogFilter(BaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
		final HttpServletRequest httpRequest = (HttpServletRequest) req;
		String url = httpRequest.getRequestURL().toString();
		if (url.endsWith("xhtml")) {
			// System.out.println(url + " - " +
			// httpRequest.getSession().getId());
			baseDAO.log(url + " - " + httpRequest.getSession().getId(), "navigation");
		}
		chain.doFilter(req, res);
	}
}
