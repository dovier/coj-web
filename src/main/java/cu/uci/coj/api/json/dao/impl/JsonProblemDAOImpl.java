/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import cu.uci.coj.api.json.dao.JsonProblemDAO;
import cu.uci.coj.api.json.model.JsonProblem;
import cu.uci.coj.dao.impl.BaseDAOImpl;

/**
 *
 * @author jasoria
 */
@Repository
public class JsonProblemDAOImpl extends BaseDAOImpl implements JsonProblemDAO {
    
    public JsonProblem problem(int id){
        JsonProblem problem = object("json.problem",JsonProblem.class,id);
        List<String> langs = strings("json.problem.languages",id);
        problem.setLanguages(langs);
        return problem;
    }
    
    public List<JsonProblem> problems(int id1,int id2){
        List<JsonProblem> problems = objects("json.problems",JsonProblem.class,id1,id2);
        for(JsonProblem problem:problems){
            List<String> langs = strings("json.problem.languages",problem.getId());
            problem.setLanguages(langs);
        }
        
        return problems;
    }
@Override
public List<JsonProblem> newproblems(Date startDate, Date endDate, String textbox, String states){
    String sql= getSql("xtats.new.problems");
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and country.name = ? group by date(problem.date) order by date(problem.date) limit 25";
                 return objects(sql, JsonProblem.class, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and institution.name = ? group by date(problem.date) order by date(problem.date) limit 25";
                return objects(sql, JsonProblem.class, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and users.username = ? group by date(problem.date) order by date(problem.date) limit 25";
                return objects(sql, JsonProblem.class, startDate, endDate,textbox);
            }
            else{
                sql+=" group by date(problem.date) order by date(problem.date) limit 25";
                return objects(sql, JsonProblem.class, startDate, endDate);
            }
}   

@Override
public List<JsonProblem> problemsClassifications(){
    return objects("xtats.problems.classifications", JsonProblem.class);
}
   
}
