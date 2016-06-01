package cu.uci.coj.teamanalyzer.dao.impl;

import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.teamanalyzer.dao.analysisDAO;
import cu.uci.coj.teamanalyzer.models.analysis;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("analysisDAO")
@Transactional
public class analysisDAOImpl extends BaseDAOImpl implements analysisDAO {


    @Override
    public List<analysis> getAnalysisesByCoach(int coach) {
        String table = "team_analyzer_analysis";
        Query query = new Query(table);
        query.where(
                Where.eq("team_analyzer_analysis.coach", coach));
        List<analysis> analysis = objects(
                query.select("*"),
                cu.uci.coj.teamanalyzer.models.analysis.class, query.arguments());
        return analysis;
    }

    @Override
    public IPaginatedList<analysis> getPaginatedList(int found, int uid, PagingOptions options) {
        String table = "team_analyzer_analysis";

        Query query = new Query(table);
        query.where(
                Where.eq("team_analyzer_analysis.coach", uid));

        query.paginate(options, 30);
        List<analysis> analysis = objects(
                query.select("*"),
                cu.uci.coj.teamanalyzer.models.analysis.class, query.arguments());

        return getPaginatedList(options, analysis, 30, found);
    }

    @Override
    public void deleteAnalysis(int aid) {
        dml("clear.selected.analysis", aid);
    }

    @Override
    public void saveAnalysis(analysis analysis) {
        dml("insert.analysis", analysis.getName(), analysis.getCoach());
    }

    @Override
    public void saveUsersAnalysis(analysis analysis) {
        for (int i = 0; i < analysis.getUsersids().length; i++) {
            insertUserAnalysis(analysis.getId(), analysis.getUsersids()[i]);
        }
    }

    @Override
    public void saveContestsAnalysis(analysis analysis) {
        for (int i = 0; i < analysis.getContestsids().length; i++) {
            insertContestAnalysis(analysis.getId(), analysis.getContestsids()[i]);
        }
    }

    @Override
    public analysis getAnalysisById(int aid) {
        return  object("select.analysis.by.id",analysis.class,aid);
    }

    @Override
    public void updateContestsAnalysis(analysis analysis) {
        dml("clear.contests.analysis",analysis.getId());
        for (int i = 0; i < analysis.getContestsids().length; i++) {
            insertContestAnalysis(analysis.getId(), analysis.getContestsids()[i]);
        }
    }

    @Override
    public void updateUsersAnalysis(analysis analysis) {
        dml("clear.users.analysis",analysis.getId());
        for (int i = 0; i < analysis.getUsersids().length; i++) {
            insertUserAnalysis(analysis.getId(), analysis.getUsersids()[i]);
        }
    }

    @Override
    public void updateNameAnalysis(analysis analysis) {
        dml("update.analysis.name",analysis.getName(),analysis.getId());
    }

    @Override
    public void registerNewAnalysis(String username) {
        dml("insert.log", "new analysis created",username);
    }

    @Override
    public void registerEditedAnalysis(String username, int analysis) {
        dml("insert.log", "analysis "+analysis+" edited",username);
    }

    @Override
    public void registerDeletedAnalysis(String username, int analysis) {
        dml("insert.log", "analysis "+analysis+" deleted",username);
    }

    private void insertContestAnalysis(int id, int cid) {
        dml("insert.contest.analysis", id, cid);
    }

    private void insertUserAnalysis(int id, int uid) {
        dml("insert.user.analysis", id, uid);
    }
}
