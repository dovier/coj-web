/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SessionsRecord {
	// TODO Investigar si no es necesario que esto sea un mapa sincronizado
	static Map<String, Long> sessions = new HashMap<String, Long>();

	public static boolean isOverLoad(String sessionId) {
		if (sessions.size() > 1000) {
			sessions.clear();
		}
		long curr = (new Date()).getTime();
		if (!sessions.containsKey(sessionId)) {
			sessions.put(sessionId, curr);
			return false;
		}

		long before = sessions.get(sessionId);
		if ((curr - before) < 20) {
			return true;
		}
		sessions.put(sessionId, curr);
		return false;
	}

	public static boolean sessionExists(String sessionId) {
		return sessions.containsKey(sessionId);
	}

	public static void removeSession(String sessionId) {
		if (sessions.containsKey(sessionId)) {
			sessions.remove(sessionId);
		}
	}

    public static int countSessions(){
        return sessions.size();
    }
}
