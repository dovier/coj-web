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
import cu.uci.coj.api.json.model.JsonProblem;
import cu.uci.coj.model.Problem;
import java.util.Date;

/**
 *
 * @author jasoria
 */
public interface JsonProblemDAO extends BaseDAO{
    
    public JsonProblem problem(int id);    
    public List<JsonProblem> problems(int id1,int id2);    
    public List<JsonProblem> newproblems(Date startDate, Date endDate, String textbox, String states);
    public List<JsonProblem> problemsClassifications();
    
         
}
