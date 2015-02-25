package cu.uci.coj.controller.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import cu.uci.coj.controller.BaseController;
import cu.uci.coj.model.Contest;
import cu.uci.coj.model.SubmissionJudge;

public class IVirtualStatusSolutions  extends BaseController implements RowMapper<SubmissionJudge> {

	private static final long serialVersionUID = 5744065082955618187L;
	String username;
    boolean isAdmin;
    Contest contestC;

    public IVirtualStatusSolutions(String username, boolean isAdmin, Contest contestC) {
        this.username = username;
        this.isAdmin = isAdmin;
        this.contestC = contestC;
    }

    public IVirtualStatusSolutions() {
    }

    public SubmissionJudge mapRow(ResultSet rs, int i) throws SQLException {
        SubmissionJudge submission = new SubmissionJudge();
        submission.setUid(rs.getInt("UID"));
        submission.setUsername(rs.getString("USERNAME"));
        submission.setPid(rs.getInt("PID"));
        submission.setSid(rs.getInt("SUBMIT_ID"));
        submission.setTimeUsed(rs.getInt("TIME"));
        submission.setMemoryUsed(rs.getInt("MEMORY"));
        submission.setFont(rs.getString("FONTSIZE"));
        submission.setDate(rs.getDate("DATE"));
        submission.setStatus(rs.getString("STATUS"));
        submission.setLang(rs.getString("LANGUAGE"));
        submission.setCode(String.valueOf(rs.getInt("PID")));
        submission.setTestcase(rs.getInt("TESTCASE"));
        submission.setAvgTimeUsed(rs.getInt("AVERAGE_CASE"));
        submission.setVirtual(rs.getBoolean("VIRTUAL"));
        if (username != null && (isAdmin || username.equals(submission.getUsername()))) {
            submission.setAuthorize(true);
        }
        
        submission.initialize();
        if (!submission.getStatus().equals("Accepted") && !submission.getStatus().equals("Compilation Error") && !submission.getStatus().equals("Unqualified") && !submission.getStatus().equals("Judging") && contestC.isShow_ontest()) {
            submission.setOntest(true);
        }
        Date chd = new Date(rs.getInt("YEAR"), rs.getInt("MONTH"), rs.getInt("DAY"), rs.getInt("HOUR"), rs.getInt("MIN"), rs.getInt("SEC"));
        if (contestC.isFrozen() && (chd.getTime() >= (contestC.getEnddate().getTime() - 60000 * contestC.getFrtime())) && !isAdmin) {
            if (username == null || (contestC.isFull_frozen() && chd.getTime() >= contestC.getEnddate().getTime() - 60000 * contestC.getDeadtime()) || !submission.getUsername().equals(username)) {
                submission.froze();
                submission.setOntest(false);
            }
        }
        return submission;
    }
}
