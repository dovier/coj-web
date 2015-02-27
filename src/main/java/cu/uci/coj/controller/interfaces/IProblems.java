/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.controller.interfaces;

import cu.uci.coj.model.Problem;
import cu.uci.coj.config.Config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
public class IProblems implements RowMapper<Object> {

    private Map<Integer, Integer> solved, tryied;

    public Map<Integer, Integer> getSolved() {
        return solved;
    }

    public void setSolved(Map<Integer, Integer> solved) {
        this.solved = solved;
    }

    public Map<Integer, Integer> getTryied() {
        return tryied;
    }

    public void setTryied(Map<Integer, Integer> tryied) {
        this.tryied = tryied;
    }

    public IProblems() {
    }

    public IProblems(Map<Integer, Integer> solved, Map<Integer, Integer> tryied) {
        this.solved = solved;
        this.tryied = tryied;
    }

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Problem problem = new Problem();
        problem.setPid(rs.getInt("PID"));
        problem.setSubmitions(rs.getInt("TOTAL"));
        problem.setAccepted(rs.getInt("AC"));
        problem.setAccp(rs.getDouble("ACCP"));
        problem.setPoints((double) Integer.valueOf(Config.getProperty("formula.value")) / (double) (40 + rs.getInt("ACCU")));
        problem.setTitle(rs.getString("TITLE"));
        if (rowNum % 2 == 0) {
            problem.setEven("odd");
        } else {
            problem.setEven("even");
        }
        if (solved != null) {
            if (solved.containsKey(problem.getPid())) {
                problem.setSolved(true);
            } else if (tryied.containsKey((Integer) problem.getPid())) {
                problem.setUnsolved(true);
            }
        }
        return problem;
    }
}
