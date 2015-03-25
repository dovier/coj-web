package cu.uci.coj.utils;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.Roles;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public class HandlerInterceptorImpl extends HandlerInterceptorAdapter {

	@Resource
	private BaseDAO baseDAO;
	@Resource
	private ContestDAO contestDAO;
	@Resource
	private UserDAO userDAO;

	private static boolean maintenanceMode = false;

	public static void setMaintenanceMode(boolean maintenanceMode) {
		HandlerInterceptorImpl.maintenanceMode = maintenanceMode;
	}

	public static boolean isMaintenanceMode() {
		return HandlerInterceptorImpl.maintenanceMode;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		String sessionId = request.getSession().getId();

		request.setAttribute("maintenanceMode", maintenanceMode);
		if (maintenanceMode && !SessionsRecord.sessionExists(sessionId) && !request.getRequestURL().toString().contains("/admin/")
				&& !request.getRequestURL().toString().contains("general/maintenance")) {
			response.sendRedirect("/general/maintenance.xhtml");
			return false;
		}

		if (!(request.getRequestURL().toString().contains("/css/") || request.getRequestURL().toString().contains("/js/"))) {

			if (!request.getRequestURL().toString().contains("general/maintenance")) {
				if (!request.getMethod().equals("POST") && SessionsRecord.isOverLoad(sessionId)) {
					throw new SessionExceeded();
				}
			}
			Utils.addIpUrl(request);
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate,max-age=0"); // HTTP
																									// 1.1
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0
			response.setDateHeader("Expires", 0); // Proxies.
			
			request.setAttribute("now",new Date());
			
			request.setAttribute("com", baseDAO.integer("count.coming.contests"));
			request.setAttribute("run", baseDAO.integer("count.running.contests"));
			request.setAttribute("attpast", baseDAO.integer("count.past.contests"));

			if (request.getRequestURL().toString().contains("/contest/") && request.getParameter("cid") != null) {
				int total = 0;
				int unread = 0;
				int unreply = 0;
				boolean showSaris = false;
				Contest contest = null;
				Integer cid = new Integer(request.getParameter("cid").toString());
				if (cid != null) {
					contest = contestDAO.loadContest(cid);
					//si el contest no esta bloqueado y no es de estilo progresivo
					showSaris = contest.getStyle()== 1 && !contest.isBlocked();
				}

				if (request.getUserPrincipal() != null) {
					String user = request.getUserPrincipal().getName();
					int uid = baseDAO.integer("select.uid.by.username", user);
					boolean isjudge = baseDAO.bool("exist.judge.contest", uid, cid);

					request.setAttribute("isjudge", isjudge);
					showSaris = showSaris || request.isUserInRole(Roles.ROLE_ADMIN) || isjudge;

					if (isjudge || baseDAO.bool("exist.user.in.contest", cid, uid)) {

						// if ((hsr.isUserInRole(Roles.ROLE_ADMIN) ||
						// hsr.isUserInRole(Roles.ROLE_SUPER_PSETTER) ||
						// hsr.isUserInRole(Roles.ROLE_PSETTER)) && isjudge) {
						// unreply =
						// baseDAO.integer("count.judges.unreply.clarifications",
						// cid);
						// }
						total += baseDAO.integer("count.user.clarification", cid, uid, uid);
						total += baseDAO.integer("count.general.clarification", cid, uid);
						unread += baseDAO.integer("count.user.unread.clarifications", cid, uid);
					}
				} else {
					total = baseDAO.integer("count.public.clarification", cid);
				}
			
				
				List<String> ann = baseDAO.strings("load.contest.announcements", cid);
				if (ann.size() > 0) {
					request.setAttribute("hasann", true);
					request.setAttribute("ann", ann);
				}
				// hsr.setAttribute("unreply", unreply);
				request.setAttribute("unread", unread);
				request.setAttribute("totalmsg", total);

				request.setAttribute("showSaris", contest.getStyle() == Contest.ACM_ICPC_STYLE?showSaris:false);
			} else {
				List<String> ann = baseDAO.strings("load.announcements");
				if (ann.size() > 0) {
					request.setAttribute("hasann", true);
					request.setAttribute("ann", ann);
				}
			}
			if (request.getUserPrincipal() != null && request.isUserInRole(Roles.ROLE_USER)) {
				String user = request.getUserPrincipal().getName();
				request.setAttribute("loggedUser", user);
				// FIXME no se porque, el mailunread puede dar null, pero el
				// draft no. probablemente tenga algo que ver con valores por
				// defecto en la bd
				Integer mailunread = baseDAO.integer("count.unread.mail.byuser", request.getUserPrincipal().getName());
				request.setAttribute("mailunread", mailunread == null ? 0 : mailunread);
				request.setAttribute("drafts", baseDAO.integer("count.user.draft", request.getUserPrincipal().getName()));
			}
		}

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse hsr1, Object o, ModelAndView model) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
	}
}
