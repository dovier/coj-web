package cu.uci.coj.dao.impl;

import cu.uci.coj.dao.ClarificationDAO;
import cu.uci.coj.model.Clarification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("clarificationDAO")
@Transactional
public class ClarificationDAOImpl extends BaseDAOImpl implements ClarificationDAO {

    public void insertClarification(Clarification clarification) {
        dml("insert.clarification", clarification.getCid(), clarification.getIdteam(), clarification.getPid(), clarification.getTitle(), clarification.getDescription());

        Integer id_clarification = integer("max.id.clarification", clarification.getIdteam());

        List<String> users = strings("judge.in.contest", clarification.getCid());

        HashMap<String, String> send = new HashMap<String, String>();
        int uid = 0;
        for (int i = 0; i < users.size(); i++) {
            String string = users.get(i);
            if (!send.containsKey(string) && (uid = integer("user.uid", string)) != -1) {
                send.put(string, string);
                dml("insert.user.clarification.read", id_clarification, uid);
            }
        }


    }

    public void sendClarification(Clarification clarification) {

        dml("insert.clarification.public", clarification.getCid(), clarification.getIdteam(), clarification.getPid(), clarification.getTitle(), clarification.getDescription(), Boolean.valueOf(clarification.isPublics()));

        int uid = 0;
        List<String> users = strings("judge.in.contest", clarification.getCid());

        if (clarification.isForall() || clarification.isPublics()) {
            if (users == null) {
                users = new ArrayList<String>();
            }
            users.addAll(strings("users.in.contest", clarification.getCid()));
        }

        String[] list = clarification.getUsername().split(";");
        for (int i = 0; i < list.length; i++) {
            String string = list[i];
            users.add(string);
        }
        HashMap<String, String> send = new HashMap<String, String>();
        Integer id_clarification = integer("max.id.clarification", clarification.getIdteam());
        for (int i = 0; i < users.size(); i++) {
            String string = users.get(i);
            if (!send.containsKey(string) && (uid = integer("user.uid", string)) != -1) {
                if (clarification.getId() > 0) {
                    dml("delete.clarification", uid, clarification.getId());
                }
                send.put(string, string);
                dml("insert.user.clarification", id_clarification, uid);
            }
        }

        if (clarification.getId() != 0) {
            dml("update.clarification", clarification.getId());
        }
    }

    @Transactional(readOnly = true)
    public List<Clarification> getMyClarifications(int cid, int uid, String pattern) {
        List<Clarification> clarifs;
        if (pattern == null) {
            clarifs = objects(getSql("my.clarifications"), Clarification.class, cid, cid, uid, uid);
        }
        else{
            clarifs = objects(getSql("my.clarifications.pattern"), Clarification.class, cid, cid, uid, uid,"%"+pattern+"%");
        }
        for (Clarification clarif : clarifs) {
            clarif.setDate(clarif.getDate().split("\\.")[0]);            
        }
        return clarifs;
    }

    @Transactional(readOnly = true)
    public List<Clarification> getPublicClarifications(int cid) {
        List<Clarification> clarifications = objects("public.clarifications", Clarification.class, cid);
        for (Clarification clarification : clarifications) {
            clarification.setForall(true);
            clarification.setPublics(true);
            clarification.setCclass("general");
        }
        return clarifications;
    }

    @Transactional(readOnly = true)
    public Clarification getPublicClarification(int id_contest) {
        return object("public.clarification", Clarification.class, id_contest);
    }

    @Transactional(readOnly = true)
    public Clarification getClarification(int cid, int ccid, int teamfor) {
        return object("get.clarification", Clarification.class, ccid, cid, teamfor);
    }

    @Transactional(readOnly = true)
    public Clarification getPublicClarification(int cid, int ccid) {
        return object("get.public.clarification", Clarification.class, ccid, cid);
    }

    public void updateReplyClarification(int ccid) {
        dml("update.reply.clarification", ccid);
        dml("update.reply.clarification.1", ccid);
        dml("update.reply.clarification.2", ccid);
    }
}
