package cu.uci.coj.dao;

import java.util.List;

import cu.uci.coj.exceptions.PlagiCOJUnprocessedSubmissionException;
import cu.uci.coj.model.PlagiCOJJudgeRevision;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.utils.enums.PlagiCOJDetectorType;
import cu.uci.plagicoj.PlagiCOJResult;
import cu.uci.plagicoj.entities.PlagicojResultDto;
import cu.uci.plagicoj.entities.PlagicojSubmissionDto;

/**
 * @version 2.0
 * @since 2010-09-01
 */
public interface PlagiCOJDAO extends BaseDAO {

    public void insertPlagiCOJDetectorResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, String userId, int evaluation, String comment, PlagiCOJDetectorType plagiCOJDetectorType);

    public List<PlagiCOJJudgeRevision> getPlagiCOJDetectorResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, PlagiCOJDetectorType plagiCOJDetectorType);

    public PlagiCOJResult getPlagiCOJResult(int sourceSubmissionId, int destinationSubmissionId) throws PlagiCOJUnprocessedSubmissionException;

    public int countSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAc, boolean userprincipal, boolean admin, String username);

    public void insertPlagiCOJResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, String userId, int evaluation, String comment);

    public List<PlagiCOJJudgeRevision> getPlagiCOJResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId);

    public void deletePlagiCOJResultJudgeRevisions(int revisionId);

    public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, boolean userprincipal, boolean admin, String username);

    public SubmissionJudge getSubmission(int submitId1);

    public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Integer offset, Integer limit, boolean userprincipal, boolean admin, String username, boolean noSource);

    public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, boolean userprincipal, boolean admin, String username);

    public List<SubmissionJudge> getSubmissionsWithoutSource(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, boolean userprincipal, boolean admin, String username);

    public List<SubmissionJudge> getSubmissionsWithoutSource(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, boolean userprincipal, boolean admin, String username);

    public List<Integer> getSubmissionsId(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC);

    public void updatePlagiCOJUserProfile(int userId, int cantDictums, double dictum);

    public double getAverageDictums(int sourceUserId, int destinationUserId);

    public List<PlagiCOJResult> getPlagiCOJHistory(int userId);

    //New
    public List<PlagicojSubmissionDto> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC);

    public int countDictums(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Boolean matchLanguage, Boolean ownSubmission, Boolean sameUser);

    public List<PlagicojResultDto> getDictums(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Boolean matchLanguage, Boolean ownSubmission, Boolean sameUser, Integer offset, Integer limit);
}
