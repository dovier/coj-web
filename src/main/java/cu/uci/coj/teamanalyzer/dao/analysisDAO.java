package cu.uci.coj.teamanalyzer.dao;


import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.teamanalyzer.models.analysis;
import cu.uci.coj.teamanalyzer.models.analyzerTeam;
import cu.uci.coj.teamanalyzer.utils.teamStats;
import cu.uci.coj.teamanalyzer.utils.userStats;
import cu.uci.coj.utils.paging.IPaginatedList;
import cu.uci.coj.utils.paging.PagingOptions;

import java.util.List;


public interface analysisDAO extends BaseDAO {


    List<analysis> getAnalysisesByCoach(int coach);

    IPaginatedList<analysis> getPaginatedList(int found, int uid, PagingOptions options);

    void deleteAnalysis(int aid);

    void saveAnalysis(analysis analysis);

    void saveUsersAnalysis(analysis analysis);

    void saveContestsAnalysis(analysis analysis);

    analysis getAnalysisById(int aid);

    void updateContestsAnalysis(analysis analysis);

    void updateUsersAnalysis(analysis analysis);

    void updateNameAnalysis(analysis analysis);

    void registerNewAnalysis(String username);

    void registerEditedAnalysis(String username, int analysis);

    void registerDeletedAnalysis(String username, int analysis);

    int[][] getAnalysisStats(analysis analysis);

    int[] getIdClassifications();

    List<userStats> getUsersStats(analysis analysis, int[] idsClassifications);

    void saveTeam(int user1, int user2, int user3, int taid);

    void clearTeamData(analysis analysis);

    List<analyzerTeam> getTeamsByAnalysisId(int taid);

    IPaginatedList<analyzerTeam> getPaginatedTeamList(int found, int taid, PagingOptions options);

    void setTeamsUserNames(List<analyzerTeam> analyzerTeamList);

    teamStats getTeamStats(int tid);
}
