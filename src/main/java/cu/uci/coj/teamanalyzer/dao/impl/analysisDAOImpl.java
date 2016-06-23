/*
* analysisDAOImpl.java
*
* v1.0
*
* 14/05/2016
*/

package cu.uci.coj.teamanalyzer.dao.impl;

import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.query.Order;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.teamanalyzer.dao.analysisDAO;
import cu.uci.coj.teamanalyzer.models.analysis;
import cu.uci.coj.teamanalyzer.models.analyzerTeam;
import cu.uci.coj.teamanalyzer.utils.teamStats;
import cu.uci.coj.teamanalyzer.utils.userStats;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    public analyzerTeam getTeamById(int tid) {
        String table = "team_analyzer_team";
        Query query = new Query(table);
        query.where(
                Where.eq("team_analyzer_team.id", tid));
        analyzerTeam team = object(
                query.select("*"),
                analyzerTeam.class, query.arguments());
        return team;
    }

    @Override
    public IPaginatedList<analysis> getPaginatedList(int found, int uid, PagingOptions options) {
        String table = "team_analyzer_analysis";

        Query query = new Query(table);
        query.where(
                Where.eq("team_analyzer_analysis.coach", uid));
        query.order(Order.asc("id"));
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
        return object("select.analysis.by.id", analysis.class, aid);
    }

    @Override
    public void updateContestsAnalysis(analysis analysis) {
        dml("clear.contests.analysis", analysis.getId());
        saveContestsAnalysis(analysis);
    }

    @Override
    public void updateUsersAnalysis(analysis analysis) {
        dml("clear.users.analysis", analysis.getId());
        saveUsersAnalysis(analysis);
    }

    @Override
    public void updateNameAnalysis(analysis analysis) {
        dml("update.analysis.name", analysis.getName(), analysis.getId());
    }

    @Override
    public void registerNewAnalysis(String username) {
        dml("insert.log", "new analysis created", username);
    }

    @Override
    public void registerEditedAnalysis(String username, int analysis) {
        dml("insert.log", "analysis " + analysis + " edited", username);
    }

    @Override
    public void registerDeletedAnalysis(String username, int analysis) {
        dml("insert.log", "analysis " + analysis + " deleted", username);
    }


    @Override
    public int[][] getAnalysisStats(analysis analysis) {
        List<Map<String, Object>> maps = maps("select.analysis.stats", analysis.getId());
        int[][] top = new int[maps.size()][2];
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> row = maps.get(i);
            top[i][0] = Integer.valueOf(String.valueOf(row.get("id_classification")));
            top[i][1] = Integer.valueOf(String.valueOf(row.get("top")));
        }
        return top;
    }

    @Override
    public int[] getIdClassifications() {
        List<Map<String, Object>> maps = maps("select.classifications.id");
        int[] ids = new int[maps.size()];
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> row = maps.get(i);
            ids[i] = Integer.valueOf(String.valueOf(row.get("id_classification")));
        }
        return ids;
    }

    public List<userStats> getUsersStats(analysis analysis, int[] idsClassifications) {
        int[] usersIds = analysis.getUsersids();
        LinkedList<userStats> usersStats = new LinkedList<>();
        for (int i = 0; i < usersIds.length; i++) {
            List<Map<String, Object>> maps = maps("select.user.stats", usersIds[i], analysis.getId());
            int[][] stats = new int[idsClassifications.length][2];
            int k = 0;
            for (int j = 0; j < idsClassifications.length; j++) {
                Map<String, Object> row = maps.get(k);
                if (idsClassifications[j] == Integer.valueOf(String.valueOf(row.get("id_classification")))) {
                    stats[j][0] = idsClassifications[j];
                    stats[j][1] = Integer.valueOf(String.valueOf(row.get("top")));
                    if (k < maps.size() - 1) {
                        k++;
                    }
                } else {
                    stats[j][0] = idsClassifications[j];
                    stats[j][1] = 0;
                }
            }
            usersStats.add(new userStats(usersIds[i], stats));
        }
        return usersStats;
    }

    @Override
    public void saveTeam(int user1, int user2, int user3, int taid) {
        dml("insert.team", user1, user2, user3, taid);
    }

    @Override
    public void clearTeamData(analysis analysis) {
        dml("clear.team.data", analysis.getId());
    }

    @Override
    public List<analyzerTeam> getTeamsByAnalysisId(int taid) {
        String table = "team_analyzer_team";
        Query query = new Query(table);
        query.where(
                Where.eq("team_analyzer_team.taid", taid));
        List<analyzerTeam> analyzerTeams = objects(
                query.select("*"),
                analyzerTeam.class, query.arguments());
        return analyzerTeams;
    }

    @Override
    public IPaginatedList<analyzerTeam> getPaginatedTeamList(int found, int taid, PagingOptions options) {
        String table = "team_analyzer_team";

        Query query = new Query(table);
        query.where(
                Where.eq("team_analyzer_team.taid", taid));
        query.order(Order.asc("id"));
        query.paginate(options, 30);
        List<analyzerTeam> analyzerTeams = objects(
                query.select("*"),
                analyzerTeam.class, query.arguments());
        setTeamsUserNames(analyzerTeams);
        return getPaginatedList(options, analyzerTeams, 30, found);
    }

    @Override
    public void setTeamsUserNames(List<analyzerTeam> analyzerTeamList) {
        for (int i = 0; i < analyzerTeamList.size(); i++) {
            Map<String, Object> map = map("select.team.username", analyzerTeamList.get(i).getUid1());
            analyzerTeamList.get(i).setUsername1(String.valueOf(map.get("username")));
            map = map("select.team.username", analyzerTeamList.get(i).getUid2());
            analyzerTeamList.get(i).setUsername2(String.valueOf(map.get("username")));
            map = map("select.team.username", analyzerTeamList.get(i).getUid3());
            analyzerTeamList.get(i).setUsername3(String.valueOf(map.get("username")));
        }
    }

    @Override
    public teamStats getTeamStats(int tid) {
        analyzerTeam team = getTeamById(tid);
        List<Map<String, Object>> maps1 = maps("select.user.stats", team.getUid1(), team.getTaid());
        List<Map<String, Object>> maps2 = maps("select.user.stats", team.getUid2(), team.getTaid());
        List<Map<String, Object>> maps3 = maps("select.user.stats", team.getUid3(), team.getTaid());
        List<Map<String, Object>> probs = maps("select.analysis.stats", team.getTaid());
        List<Map<String, Object>> classifications = maps("select.classifications.id");
        return new teamStats(maps1, maps2, maps3, probs, classifications);
    }

    private void insertContestAnalysis(int taid, int cid) {
        dml("insert.contest.analysis", taid, cid);
    }

    private void insertUserAnalysis(int taid, int uid) {
        dml("insert.user.analysis", taid, uid);
    }
}
