/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao;

import java.util.Date;
import java.util.List;

import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.model.SubmissionJudge;

/**
 *
 * @author Z
 */
public interface JsonSubmissionDAO extends BaseDAO {
    /*Statistic module of COJ*/
    public List<SubmissionJudge> veredictstl(Date startDate, Date endDate, String textbox, String states);
    public List<SubmissionJudge> problemcompare1(int textbox1, Date startDate, Date endDate, String textbox, String states);
    public List<SubmissionJudge> problemcompare2(int textbox2, Date startDate, Date endDate, String textbox, String states);
    public List<SubmissionJudge> usersubmissions(String textbox1, Date startDate, Date endDate);
    public List<SubmissionJudge> usersubmissions(String user);
    public List<SubmissionJudge> problemsubmissions(int textbox1, Date startDate, Date endDate, String textbox, String states);
    public List<SubmissionJudge> veredictsproblemstl(int textbox1, Date startDate, Date endDate, String prefix);
    public List<SubmissionJudge> userscompare1(String textbox1, int textbox, String states, Date startDate, Date endDate);
    public List<SubmissionJudge> userscompare2(String textbox2, int textbox, String states, Date startDate, Date endDate);
}
