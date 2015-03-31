/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.controller.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import cu.uci.coj.model.Problem;
import cu.uci.coj.model.Roles;
import cu.uci.coj.model.SubmissionJudge;

@Deprecated 
//FIXME esta clase esta mal no deberia usar requestWrapper para nada
public class IBestSolutions implements RowMapper<SubmissionJudge> {

    private Integer pid;
    private int number;
    private SecurityContextHolderAwareRequestWrapper requestWrapper;
    private Problem problem;    

    public IBestSolutions(Integer pid, int number, SecurityContextHolderAwareRequestWrapper requestWrapper, Problem problem) {
        this.pid = pid;
        this.number = number;
        this.requestWrapper = requestWrapper;
        this.problem = problem;
    }    

    public IBestSolutions() {
    }

    public SubmissionJudge mapRow(ResultSet rs, int i) throws SQLException {
        SubmissionJudge submission = new SubmissionJudge();
        submission.setUsername(rs.getString("USERNAME"));
        submission.setTimeUsed(rs.getInt("TIME"));
        submission.setMemoryUsed(rs.getInt("MEMORY"));
        submission.setFont(rs.getString("FONTSIZE"));
        submission.setDate(rs.getDate("DATE"));
        submission.setLang(rs.getString("LANGUAGE"));
        submission.setStatus("Accepted");
        submission.setPid(pid);
        submission.setRank(number * 20 + i + 1);
        submission.setSid(rs.getInt("SUBMIT_ID"));

       
        if ((problem.isSolved() && problem.isLocked()) || (requestWrapper.getUserPrincipal() != null && (requestWrapper.isUserInRole(Roles.ROLE_ADMIN) || submission.getUsername().equals(requestWrapper.getUserPrincipal().getName())))) {
            submission.setAuthorize(true);
        }
        return submission;
    }
}
