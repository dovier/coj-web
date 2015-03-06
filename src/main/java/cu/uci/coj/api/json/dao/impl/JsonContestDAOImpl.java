/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao.impl;

import cu.uci.coj.api.json.dao.JsonContestDAO;
import cu.uci.coj.api.json.model.JsonContest;
import cu.uci.coj.dao.impl.BaseDAOImpl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jasoria
 */
@Repository
public class JsonContestDAOImpl extends BaseDAOImpl implements JsonContestDAO {
    
    public List<JsonContest> runningAndComingContest(){
        return objects("json.coming.running.contests",JsonContest.class);
    }
    
    @Override
    public List<JsonContest> statusContest(){
        String sql="select contest_submition.status as state from contest_submition group by state";
        return objects(sql, JsonContest.class);
    }
    
    @Override
    public List<JsonContest> contestSubmissions(int textbox1, String textbox, String states){
        String sql=getSql("xtats.contest.submissions");
        if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" where cid = ? and country.name = ? group by cs.status";
                 return objects(sql, JsonContest.class, textbox1, textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" where cid = ? and institution.name = ? group by cs.status";
                return objects(sql, JsonContest.class, textbox1, textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" where cid = ? and users.username = ? group by cs.status";
                return objects(sql, JsonContest.class, textbox1, textbox);
            }
            else{
                sql+=" where cid = ? group by cs.status";
                return objects(sql, JsonContest.class, textbox1);
            }
    }
    
    @Override
    public List<JsonContest> contestSubmissions(int cid, String username){
        String sql=getSql("xtats.contest.submissions");
            if(!StringUtils.isEmpty(username)){
                sql+=" and cid = ? and users.username = ? group by cs.status order by state ";
                return objects(sql, JsonContest.class, cid, username);
            }
            else{
                sql+=" and cid = ? group by cs.status order by state ";
                return objects(sql, JsonContest.class, cid);
            }
    }
    
    @Override
    public List<JsonContest> contestVeredicts(int textbox1, String veredict, String textbox, String states){
        String sql=getSql("xtats.contest.veredicts.timeline");
        if(veredict.equals("All Contest")){
                sql+=" and cs.date between c.initdate and c.enddate";
                }
            else if(veredict.equals("First Hour")){
                sql+=" and cs.date between c.initdate and (c.initdate + interval'1 hour')";
            }
            else if(veredict.equals("Last Hour")){
                sql+=" and cs.date between (c.enddate - interval'1 hour') and c.enddate";
            }
            
        
        if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and cid = ? and country.name = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                 return objects(sql, JsonContest.class, textbox1, textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and cid = ? and institution.name = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                return objects(sql, JsonContest.class, textbox1, textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and cid = ? and users.username = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                return objects(sql, JsonContest.class, textbox1, textbox);
            }
            else{
                sql+=" and cid = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                return objects(sql, JsonContest.class, textbox1);
            }
    }
    
    @Override
    public List<JsonContest> problemsContest(int textbox1, int textbox2, String veredict, String textbox, String states){
        String sql=getSql("xtats.contest.problems.stats");
        if(veredict.equals("All Contest")){
                sql+=" where cs.date between c.initdate and c.enddate";
                }
            else if(veredict.equals("First Hour")){
                sql+=" where cs.date between c.initdate and (c.initdate + interval'1 hour')";
            }
            else if(veredict.equals("Last Hour")){
                sql+=" where cs.date between (c.enddate - interval'1 hour') and c.enddate";
            }
            
        
        if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and cid = ? and pid = ?  and country.name = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                 return objects(sql, JsonContest.class, textbox1, textbox2, textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and cid = ? and pid = ?  and institution.name = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                return objects(sql, JsonContest.class, textbox1, textbox2, textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and cid = ? and pid = ?  and users.username = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                return objects(sql, JsonContest.class, textbox1, textbox2, textbox);
            }
            else{
                sql+=" and cid = ?  and pid = ?  group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:min') order by to_char(cs.date, 'yyyy/mm/dd hh:min')";
                return objects(sql, JsonContest.class, textbox1, textbox2);
            }
    }
    
    @Override
    public List<JsonContest> problemsContestFilter(int textbox1){
        return objects("xtats.problem.contest.filter", JsonContest.class, textbox1);
    }
    
    @Override
    public List<JsonContest> contestUserDevelopment(int textbox1, String textbox2, String textbox, String veredict){
        String sql=getSql("xtats.contest.user.development");
        if(veredict.equals("All Contest")){
                sql+=" where cs.date between co.initdate and co.enddate";
                }
            else if(veredict.equals("First Hour")){
                sql+=" where cs.date between co.initdate and (co.initdate + interval'1 hour')";
            }
            else if(veredict.equals("Last Hour")){
                sql+=" where cs.date between (co.enddate - interval'1 hour') and co.enddate";
            }
            
        sql+=" and cs.cid = ? and c.name = ? and cs.username = ? group by cs.status, to_char(cs.date, 'yyyy/mm/dd hh:mi'),c.name order by to_char(cs.date, 'yyyy/mm/dd hh:mi')";
        return objects(sql, JsonContest.class, textbox1,  textbox2, textbox);
    }
    
    @Override
    public List<JsonContest> filterUsersInContest(int textbox1){
        return objects("xtats.users.in.contest", JsonContest.class, textbox1);
    }
}
