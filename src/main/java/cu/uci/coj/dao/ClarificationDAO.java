package cu.uci.coj.dao;

import java.util.List;
import cu.uci.coj.model.Clarification;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public interface ClarificationDAO extends BaseDAO {

    public void insertClarification(Clarification clarification);

    public void sendClarification(Clarification clarification);

    public Clarification getPublicClarification(int id_contest);

    public Clarification getClarification(int cid, int ccid, int teamfor);

    public Clarification getPublicClarification(int cid, int ccid);

    public List<Clarification> getMyClarifications(int cid, int uid, String pattern);

    public List<Clarification> getPublicClarifications(int cid);

     
    public void updateReplyClarification(int ccid);
}
