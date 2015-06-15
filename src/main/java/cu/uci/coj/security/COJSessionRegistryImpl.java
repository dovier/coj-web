/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
package cu.uci.coj.security;

import cu.uci.coj.dao.UserDAO;
import cu.uci.coj.utils.SessionsRecord;
import javax.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
@Component("cojSessionRegistryImpl")
public class COJSessionRegistryImpl extends SessionRegistryImpl {

    @Resource
    private UserDAO userDAO;
    

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public synchronized void registerNewSession(String sessionId, Object principal) {
        
        super.registerNewSession(sessionId, principal);
    }

    @Override
    public void removeSessionInformation(String sessionId) {
        SessionsRecord.removeSession(sessionId);
        try {
            userDAO.dml("update.user.status", false, ((User) this.getSessionInformation(sessionId).getPrincipal()).getUsername());
        } catch (Exception e) {
        }
        
        super.removeSessionInformation(sessionId);
    }
}
