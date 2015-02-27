/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao;

import cu.uci.coj.api.json.model.JsonContest;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.dao.impl.BaseDAOImpl;

import java.util.List;

/**
 *
 * @author jasoria
 */
public interface JsonContestDAO extends BaseDAO{
    
    public List<JsonContest> runningAndComingContest();
    public List<JsonContest> statusContest();
    public List<JsonContest> contestSubmissions(int textbox1, String textbox, String states);
    public List<JsonContest> contestVeredicts(int textbox1, String veredict, String textbox, String states);
    public List<JsonContest> problemsContest(int textbox1, int textbox2, String veredict, String textbox, String states);
    public List<JsonContest> problemsContestFilter(int textbox1); 
    public List<JsonContest> contestUserDevelopment(int textbox1, String textbox2, String textbox, String veredict);
    public List<JsonContest> filterUsersInContest(int textbox1);
	List<JsonContest> contestSubmissions(int cid, String username);
}
