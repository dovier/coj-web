package cu.uci.coj.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Roles;

public class COJAuthenticationProcessingFilter extends UsernamePasswordAuthenticationFilter {

	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	private boolean belongsTo(String from, String rule) {
		String[] first = from.split("\\.");
		String[] second = rule.split("\\.");
		for (int i = 0; i < 4; i++) {
			if (second[i].equals("*")) {
				return true;
			} else if (!second[i].equals(first[i])) {
				return false;
			}
		}
		return true;
	}

	private boolean canAccess(String ip, List<String> acl) {
		for (int i = 0; i < acl.size(); i++) {
			if (belongsTo(ip, acl.get(i))) {
				return true;
			}
		}
		return false;
	}

	private boolean isAdmin(Collection<? extends GrantedAuthority> credentials) {

		Iterator<? extends GrantedAuthority> it = credentials.iterator();
		while (it.hasNext()) {
			if (it.next().getAuthority().equals(Roles.ROLE_ADMIN)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String obtainUsername(HttpServletRequest request) {
		String usernameMail = super.obtainUsername(request);
		String username = userDAO.userByMail(usernameMail);

		return (username == null) ? usernameMail : username;
	}

	public Authentication doAttemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		username = username.trim();

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		Authentication auth = doAttemptAuthentication(request, response);
		boolean success = false;
		if (auth.isAuthenticated()) {
			List<String> acl = userDAO.strings("rule");
			success = canAccess(request.getRemoteAddr(), acl);
			if (success) {
				success = belongsTo(request.getRemoteAddr(), userDAO.getUserRule(auth.getName()));
			}
		}
		if (auth.isAuthenticated() && !success && !isAdmin(auth.getAuthorities())) {
			throw new AuthenticationException("Ip Access Restriction") {
			};
		}
		return auth;
	}
}
