/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cu.uci.coj.api.json.dao.JsonSubmissionDAO;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.SubmissionJudge;

/**
 *
 * @author Z
 */
@Repository("jsonSubmissionDAO")
@Transactional
public class JsonSubmissionDAOImpl extends BaseDAOImpl implements JsonSubmissionDAO{

    /*Statistic module of COJ*/
    @Override
    public List<SubmissionJudge> veredictstl(Date startDate, Date endDate, String textbox, String states){
        String sql= getSql("xtats.submission.veredicts.timeline");
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and country.name = ? group by to_char(submition.date, 'YYYY/MM/DD '),status order by to_char(submition.date, 'YYYY/MM/DD ') limit 150";
                 return objects(sql, SubmissionJudge.class, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and institution.name = ? group by to_char(submition.date, 'YYYY/MM/DD '),status order by to_char(submition.date, 'YYYY/MM/DD ') limit 150";
                return objects(sql, SubmissionJudge.class, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and users.username = ? group by to_char(submition.date, 'YYYY/MM/DD '),status order by to_char(submition.date, 'YYYY/MM/DD ') limit 150";
                return objects(sql, SubmissionJudge.class, startDate, endDate,textbox);
            }
            else{
                sql+=" group by to_char(submition.date, 'YYYY/MM/DD '),status order by to_char(submition.date, 'YYYY/MM/DD ') limit 150";
                return objects(sql, SubmissionJudge.class, startDate, endDate);
            }
    }
    
    @Override
    public List<SubmissionJudge> problemcompare1(int textbox1, Date startDate, Date endDate, String textbox, String states){
                String sql= getSql("xtats.compare.problems");
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and pid = ? and submition.date between ? and ? and country.name = ? group by status";
                 return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and pid = ? and submition.date between ? and ? and institution.name = ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and pid = ? and submition.date between ? and ? and users.username = ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate,textbox);
            }
            else{
                sql+=" and pid = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate);
            }
        
    }
    
    @Override
    public List<SubmissionJudge> problemcompare2(int textbox2, Date startDate, Date endDate, String textbox, String states){
                String sql= getSql("xtats.compare.problems");
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and pid = ? and submition.date between ? and ? and country.name = ? group by status";
                 return objects(sql, SubmissionJudge.class, textbox2, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and pid = ? and submition.date between ? and ? and institution.name = ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and pid = ? and submition.date between ? and ? and users.username = ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, startDate, endDate,textbox);
            }
            else{
                sql+=" and pid = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, startDate, endDate);
            }
        
    }
    
    //returns a list of user's submissions
    @Override
    public List<SubmissionJudge> usersubmissions(String textbox1, Date startDate, Date endDate) {
        String sql= getSql("xtats.submission.users");
        sql+=" and users.username = ? and submition.date between ? and ? group by status";
        return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate);
            
    }   

    @Override
    public List<SubmissionJudge> usersubmissions(String user) {
        return objects(getSql("xtats.submission.users.all"),SubmissionJudge.class, user);
    }   
    
    @Override
    public List<SubmissionJudge> problemsubmissions(int textbox1, Date startDate, Date endDate, String textbox, String states) {
        String sql= getSql("xtats.submission.problems");
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and pid = ? and submition.date between ? and ? and country.name = ? group by status";
                 return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and pid = ? and submition.date between ? and ? and institution.name = ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and pid = ? and submition.date between ? and ? and users.username = ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate,textbox);
            }
            else{
                sql+=" and pid = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate);
            }
    }
    
    @Override
    public List<SubmissionJudge> veredictsproblemstl(int textbox1, Date startDate, Date endDate, String prefix){
        String sql= getSql("xtats.problems.veredicts.timeline");
        return objects(sql, SubmissionJudge.class,prefix, textbox1, startDate, endDate);
            
    }
    
    @Override
    public List<SubmissionJudge> userscompare1(String textbox1, int textbox, String states, Date startDate, Date endDate){
        String sql= getSql("xtats.compare.users");
        if(!states.isEmpty() && textbox > 0){
                sql+="  and users.username = ? and submition.date between ? and ? group by status";
                 return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate);
                }
        else if(states.isEmpty() && textbox != 0){
                sql+="  and users.username = ? and cs.complexity = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, textbox, startDate, endDate);
                }
        else if(textbox != 0 && states.isEmpty()){
                sql+="  and users.username = ? and cs.complexity = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, textbox, startDate, endDate);
                }
        else if(!states.isEmpty() && textbox == 0){
                sql+="  and users.username = ? and c.name = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, states, startDate, endDate);
                }
        else if(textbox == 0 && !states.isEmpty()){
                sql+="  and users.username = ? and c.name = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, states, startDate, endDate);
                }
        else if(textbox != 0 && !states.isEmpty()){
                sql+="  and users.username = ? and c.name = ? and cs.complexity = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, states, textbox, startDate, endDate);
                }
        else{
                sql+="  and users.username = ?  and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox1, startDate, endDate);
                }
        
    }
    
    
    @Override
    public List<SubmissionJudge> userscompare2(String textbox2, int textbox, String states, Date startDate, Date endDate){
        String sql= getSql("xtats.compare.users");
        if(states.isEmpty() && textbox == 0){
                sql+="  and users.username = ? and submition.date between ? and ? group by status";
                 return objects(sql, SubmissionJudge.class, textbox2, startDate, endDate);
                }
        else if(states.isEmpty() && textbox != 0){
                sql+="  and users.username = ? and cs.complexity = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, textbox, startDate, endDate);
                }
        else if(textbox != 0 && states.isEmpty()){
                sql+="  and users.username = ? and cs.complexity = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, textbox, startDate, endDate);
                }
        else if(!states.isEmpty() && textbox == 0){
                sql+="  and users.username = ? and c.name = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, states, startDate, endDate);
                }
        else if(textbox == 0 && !states.isEmpty()){
                sql+="  and users.username = ? and c.name = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, states, startDate, endDate);
                }
        else if(textbox != 0 && !states.isEmpty()){
                sql+="  and users.username = ? and c.name = ? and cs.complexity = ? and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, states, textbox, startDate, endDate);
                }
        else{
                sql+="  and users.username = ?  and submition.date between ? and ? group by status";
                return objects(sql, SubmissionJudge.class, textbox2, startDate, endDate);
                }
    }
}
