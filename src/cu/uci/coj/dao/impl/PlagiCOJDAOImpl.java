package cu.uci.coj.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.dao.PlagiCOJDAO;
import cu.uci.coj.exceptions.PlagiCOJUnprocessedSubmissionException;
import cu.uci.coj.model.PlagiCOJJudgeRevision;
import cu.uci.coj.model.SubmissionJudge;
import cu.uci.coj.query.Query;
import cu.uci.coj.query.Where;
import cu.uci.coj.utils.enums.PlagiCOJDetectorType;
import cu.uci.coj.utils.enums.PlagiCOJEvaluation;
import cu.uci.plagicoj.PlagiCOJResult;
import cu.uci.plagicoj.entities.PlagicojResultDto;
import cu.uci.plagicoj.entities.PlagicojSubmissionDto;
import cu.uci.plagicoj.impl.PlagiCOJResultImpl;

/**
 * @version 2.0
 * @since 2012-02-01
 */
@Repository("plagiCOJDAO")
@Transactional
public class PlagiCOJDAOImpl extends BaseDAOImpl implements PlagiCOJDAO {

	@Transactional(readOnly = true)
	@Override
	public double getAverageDictums(int sourceUserId, int destinationUserId) {
		return real("get.average.dictums", sourceUserId, destinationUserId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PlagiCOJJudgeRevision> getPlagiCOJResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId) {
		return getPlagiCOJDetectorResultJudgeRevisions(sourceSubmissionId, destinationSubmissionId, -1);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PlagiCOJJudgeRevision> getPlagiCOJDetectorResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, PlagiCOJDetectorType plagiCOJDetectorType) {
		return getPlagiCOJDetectorResultJudgeRevisions(sourceSubmissionId, destinationSubmissionId, plagiCOJDetectorType.ordinal());
	}

	@Transactional(readOnly = true)
	private List<PlagiCOJJudgeRevision> getPlagiCOJDetectorResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, int plagiCOJDetectorType) {

		RowMapper<PlagiCOJJudgeRevision> mapper = new RowMapper<PlagiCOJJudgeRevision>() {

			public PlagiCOJJudgeRevision mapRow(ResultSet rs, int rowNum) throws SQLException {
				Timestamp timestamp = rs.getTimestamp("DATE");
				Date date = new Date(timestamp.getTime());
				PlagiCOJJudgeRevision plagiCOJRevision = new PlagiCOJJudgeRevision(rs.getInt("ID_PLAGICOJ_RESULT_JUDGE_REVISION"), rs.getString("ID_USER"), date,
						PlagiCOJEvaluation.values()[rs.getInt("EVALUATION")], rs.getString("COMMENT"));
				return plagiCOJRevision;
			}
		};
		return jdbcTemplate.query(getSql("get.plagicoj.detector.res.judge.rev"), mapper, sourceSubmissionId, destinationSubmissionId, plagiCOJDetectorType);
	}

	@Override
	public void insertPlagiCOJResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, String userId, int evaluation, String comment) {
		dml("insert.plagicoj.result.judge.rev", sourceSubmissionId, destinationSubmissionId, userId, evaluation, comment);
	}

	@Override
	public void insertPlagiCOJDetectorResultJudgeRevisions(int sourceSubmissionId, int destinationSubmissionId, String userId, int evaluation, String comment, PlagiCOJDetectorType plagiCOJDetectorType) {
		dml("insert.plagicoj.detector.judge.rev", sourceSubmissionId, destinationSubmissionId, userId, evaluation, comment, plagiCOJDetectorType.ordinal());
	}

	@Transactional(readOnly = true)
	@Override
	public PlagiCOJResult getPlagiCOJResult(int sourceSubmissionId, int destinationSubmissionId) throws PlagiCOJUnprocessedSubmissionException {
		return object("get.plagicoj.result", PlagiCOJResultImpl.class, sourceSubmissionId, destinationSubmissionId);
	}

	@Transactional(readOnly = true)
	@Override
	public SubmissionJudge getSubmission(int submitId1) {
		SubmissionJudge res = object("get.plagicoj.submission", SubmissionJudge.class, submitId1);
		res.initialize();
		return res;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, boolean userprincipal, boolean admin,
			String username) {
		return getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, Boolean.FALSE, userprincipal, admin, username, false);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, boolean userprincipal,
			boolean admin, String username) {
		return getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, Boolean.FALSE, userprincipal, admin, username, false);
	}

	@Transactional(readOnly = true)
	public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, boolean userprincipal,
			boolean admin, String username, boolean noSource) {
		return getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, null, null, userprincipal, admin, username, noSource);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubmissionJudge> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Integer offset,
			Integer limit, boolean userprincipal, boolean admin, String username, boolean noSource) {
		List<String> parameters = new ArrayList<>();
		List<String> from = new ArrayList<>();
		List<String> where = new ArrayList<>();
		List<String> orderBy = new ArrayList<>();

		parameters
				.add("submition.uid,username,submition.pid,submit_id as submitid,submition.time as timen,submition.memory,submition.fontsize as font,submition.date,status,language,testcase,average_case as average");
		from.add("submition");

		if (!noSource) {
			parameters.add("code");
			from.add("join source on source.sid = submition.submit_id");
		}

		List<Object> list = new LinkedList<>();

		if (submitId1 != null && submitId1.compareTo("") != 0) {
			where.add("OR submit_id = ?");
			list.add(Integer.parseInt(submitId1));
		}

		if (submitId2 != null && submitId2.compareTo("") != 0) {
			where.add("OR submit_id = ?");
			list.add(Integer.parseInt(submitId2));
		}

		if (submitIdRangeStart != null && submitIdRangeEnd != null && submitIdRangeStart.compareTo("") != 0 && submitIdRangeEnd.compareTo("") != 0) {
			where.add("OR submit_id BETWEEN ? AND ?");
			list.add(Integer.parseInt(submitIdRangeStart));
			list.add(Integer.parseInt(submitIdRangeEnd));
		}

		if (problemId != null && problemId.compareTo("") != 0) {
			where.add("OR submition.pid = ?");
			list.add(problemId);
		}

		if (onlyAC) {
			// TODO: creo que aqui deberia ser AND no OR
			where.add("AND status = 'Accepted'");
		}

		where.add("AND submition.enabled = true");
		orderBy.add("(submit_id)");
		String sql = buildSql(parameters, from, where, orderBy, offset, limit);

		List<SubmissionJudge> res = objects(sql, SubmissionJudge.class, list.toArray());

		for (SubmissionJudge submission : res) {
			if (userprincipal && (admin || username.equals(submission.getUsername()))) {
				submission.setAuthorize(true);
			}
			submission.initialize();
		}
		return res;
	}

	@Transactional(readOnly = true)
	@Override
	public List<PlagicojSubmissionDto> getSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC) {
		List<String> parameters = new ArrayList<>();
		List<String> from = new ArrayList<>();
		List<String> where = new ArrayList<>();
		List<String> orderBy = new ArrayList<>();

		parameters.add("submit_id, uid AS userId, language");
		from.add("submition");

		List<Object> list = new LinkedList<>();

		if (submitId1 != null && submitId1.compareTo("") != 0) {
			where.add("OR submit_id = ?");
			list.add(Integer.parseInt(submitId1));
		}

		if (submitId2 != null && submitId2.compareTo("") != 0) {
			where.add("OR submit_id = ?");
			list.add(Integer.parseInt(submitId2));
		}

		if (submitIdRangeStart != null && submitIdRangeEnd != null && submitIdRangeStart.compareTo("") != 0 && submitIdRangeEnd.compareTo("") != 0) {
			where.add("OR submit_id BETWEEN ? AND ?");
			list.add(Integer.parseInt(submitIdRangeStart));
			list.add(Integer.parseInt(submitIdRangeEnd));
		}

		if (problemId != null && problemId.compareTo("") != 0) {
			where.add("OR submition.pid = ?");
			list.add(problemId);
		}

		if (onlyAC) {
			where.add("AND status = 'Accepted'");
		}

		where.add("AND submition.enabled = true");

		// TODO: @Lan - Should be tested if is necesary to order submitions
		// orderBy.add("(submit_id)");

		String sql = buildSql(parameters, from, where, orderBy, null, null);
		List<PlagicojSubmissionDto> res = objects(sql, PlagicojSubmissionDto.class, list.toArray());
		return res;
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubmissionJudge> getSubmissionsWithoutSource(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, boolean userprincipal,
			boolean admin, String username) {
		return getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, Boolean.FALSE, userprincipal, admin, username, true);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SubmissionJudge> getSubmissionsWithoutSource(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC,
			boolean userprincipal, boolean admin, String username) {
		return getSubmissions(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, userprincipal, admin, username, true);
	}

	private void getDictumsBasicSQL(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Boolean matchLanguage,
			Boolean ownSubmission, Boolean sameUser, List<String> parameters, List<String> from, List<String> where, List<String> orderBy, List<Object> list) {

		if (parameters != null) {
			parameters.add("id_source_submission AS sourceSubmissionId, id_destination_submission AS destinationSubmissionId, dictum");
		}

		from.add("plagicoj_result");

		if (submitId1 != null && submitId1.compareTo("") != 0 && submitId2 != null && submitId2.compareTo("") != 0) {
			where.add("OR (id_source_submission = ? AND id_destination_submission = ?)");
			list.add(Integer.parseInt(submitId1));
			list.add(Integer.parseInt(submitId2));
		}

		if (submitIdRangeStart != null && submitIdRangeEnd != null && submitIdRangeStart.compareTo("") != 0 && submitIdRangeEnd.compareTo("") != 0) {
			where.add("OR ((id_source_submission BETWEEN ? AND ?) AND (id_destination_submission BETWEEN ? AND ?))");
			list.add(Integer.parseInt(submitIdRangeStart));
			list.add(Integer.parseInt(submitIdRangeEnd));
			list.add(Integer.parseInt(submitIdRangeStart));
			list.add(Integer.parseInt(submitIdRangeEnd));
		}

		if (problemId != null && problemId.compareTo("") != 0) {
			// TODO: @Lan - This duplicated subquery can be unified
			where.add("OR (id_source_submission in (select submit_id from submition where pid = ?) AND id_source_submission in (select id_destination_submission from submition where pabb = ?))");
			list.add(problemId);
		}

		if (onlyAC || matchLanguage || ownSubmission || sameUser) {
			if (onlyAC) {
				from.add(",(SELECT submit_id AS ssid, language, uid FROM submition WHERE status ='Accepted') AS s,(SELECT submit_id AS dsid, language, uid FROM submition WHERE status ='Accepted') AS d");
			} else {
				from.add(",(SELECT submit_id AS ssid, language, uid FROM submition) AS s,(SELECT submit_id AS dsid, language, uid FROM submition) AS d");
			}
			where.add("AND (s.ssid = id_source_submission AND d.dsid = id_destination_submission)");
		}

		if (matchLanguage) {
			where.add("AND (s.language = d.language)");
		}
		if (ownSubmission) {
			where.add("AND (s.ssid != d.dsid)");
		}
		if (sameUser) {
			where.add("AND (s.uid != d.uid)");
		}

		if (orderBy != null) {
			orderBy.add("dictum DESC");
		}
	}

	@Override
	public int countDictums(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Boolean matchLanguage, Boolean ownSubmission,
			Boolean sameUser) {
		List<String> parameters = new ArrayList<>();
		List<String> from = new ArrayList<>();
		List<String> where = new ArrayList<>();
		List<Object> list = new LinkedList<>();

		getDictumsBasicSQL(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, matchLanguage, ownSubmission, sameUser, null, from, where, null, list);

		parameters.add("count(*)");

		String sql = buildSql(parameters, from, where, null, null, null);

		return integer(sql, list.toArray());
	}

	@Transactional(readOnly = true)
	@Override
	public List<Integer> getSubmissionsId(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC) {
		List<String> parameters = new ArrayList<>();
		List<String> from = new ArrayList<>();
		List<String> where = new ArrayList<>();
		List<String> orderBy = new ArrayList<>();

		parameters.add("submit_id");
		from.add("submition");

		List<Object> list = new LinkedList<>();

		if (submitId1 != null && submitId1.compareTo("") != 0) {
			where.add("OR submit_id = ?");
			list.add(Integer.parseInt(submitId1));
		}

		if (submitId2 != null && submitId2.compareTo("") != 0) {
			where.add("OR submit_id = ?");
			list.add(Integer.parseInt(submitId2));
		}

		if (submitIdRangeStart != null && submitIdRangeEnd != null && submitIdRangeStart.compareTo("") != 0 && submitIdRangeEnd.compareTo("") != 0) {
			where.add("OR submit_id BETWEEN ? AND ?");
			list.add(Integer.parseInt(submitIdRangeStart));
			list.add(Integer.parseInt(submitIdRangeEnd));
		}

		if (problemId != null && problemId.compareTo("") != 0) {
			where.add("OR submition.pid = ?");
			list.add(problemId);
		}

		if (onlyAC) {
			// TODO: creo que aqui deberia ser AND no OR
			where.add("OR status = 'Accepted'");
		}

		where.add("AND submition.enabled = true");

		// TODO: @Lan - Should be tested if is necesary to order submitions
		// orderBy.add("(submit_id)");

		String sql = buildSql(parameters, from, where, orderBy, null, null);
		List<Integer> res = integers(sql, list.toArray());
		return res;
	}

	@Transactional(readOnly = true)
	@Override
	public int countSubmissions(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAc, boolean userprincipal, boolean admin,
			String username) {
		Query query = new Query("submition");
		query.where(Where.and(
				Where.eq("status", onlyAc ? "Accepted":null),
				Where.yes("enabled"),
				Where.or(Where.ne("1", "1"), Where.eq("submit_id", submitId1), Where.eq("submit_id", submitId2), Where.eq("pid", problemId), Where.yes("enabled"),
						Where.bt("submit_id", submitIdRangeStart, submitIdRangeEnd))));
		return integer(query.count(), query.arguments());
	}

	@Override
	public void deletePlagiCOJResultJudgeRevisions(int revisionId) {
		dml("delete.plagicoj.result.judge.rev", revisionId);
	}

	@Override
	public void updatePlagiCOJUserProfile(int userId, int cantDictums, double dictum) {
		if (dml("update.plagicoj.user.profile", cantDictums, dictum, userId) == 0)
			dml("update.plagicoj.user.profile.1", userId, cantDictums, dictum);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PlagiCOJResult> getPlagiCOJHistory(int userId) {
		return objects("get.plagicoj.history", PlagiCOJResult.class, userId, userId);
	}

	public String buildSql(List<String> parameters, List<String> from, List<String> where, List<String> orderBy, Integer offset, Integer limit) {
		StringBuilder builder = new StringBuilder("SELECT ");

		Iterator<String> itParameters = parameters.iterator();
		builder.append(itParameters.next());
		while (itParameters.hasNext()) {
			builder.append(",").append(itParameters.next());
		}

		Iterator<String> itFrom = from.iterator();
		builder.append(" FROM ").append(itFrom.next());
		while (itFrom.hasNext()) {
			builder.append(" ").append(itFrom.next());
		}

		if (where != null) {
			Iterator<String> itWhere = where.iterator();
			builder.append(" WHERE 1<>1");
			while (itWhere.hasNext()) {
				builder.append(" ").append(itWhere.next());
			}
		}

		if (orderBy != null && !orderBy.isEmpty()) {
			Iterator<String> itOrderBy = orderBy.iterator();
			builder.append(" ORDER BY");
			while (itOrderBy.hasNext()) {
				builder.append(" ").append(itOrderBy.next());
			}

			if (offset != null)
				builder.append(" OFFSET ").append(offset);
		}
		if (limit != null) {
			builder.append(" LIMIT ").append(limit);
		}
		return builder.toString();
	}

	// New

	@Override
	public List<PlagicojResultDto> getDictums(String submitId1, String submitId2, String submitIdRangeStart, String submitIdRangeEnd, String problemId, Boolean onlyAC, Boolean matchLanguage,
			Boolean ownSubmission, Boolean sameUser, Integer offset, Integer limit) {
		List<String> parameters = new ArrayList<>();
		List<String> from = new ArrayList<>();
		List<String> where = new ArrayList<>();
		List<String> orderBy = new ArrayList<>();
		List<Object> list = new LinkedList<>();

		getDictumsBasicSQL(submitId1, submitId2, submitIdRangeStart, submitIdRangeEnd, problemId, onlyAC, matchLanguage, ownSubmission, sameUser, parameters, from, where, orderBy, list);

		String sql = buildSql(parameters, from, where, orderBy, offset, limit);

		List<PlagicojResultDto> res = objects(sql, PlagicojResultDto.class, list.toArray());
		return res;
	}

}
