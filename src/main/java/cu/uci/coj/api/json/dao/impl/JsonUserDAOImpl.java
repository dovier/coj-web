/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao.impl;

import cu.uci.coj.api.json.dao.JsonUserDAO;
import cu.uci.coj.api.json.model.JsonUserRank;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.model.User;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jasoria
 */
@Repository("jsonUserDAO")
@Transactional
public class JsonUserDAOImpl extends BaseDAOImpl implements JsonUserDAO{
    
    @Override
    public List<JsonUserRank> ranks(){
        return objects("json.user.top.ranking",JsonUserRank.class);
    }

    //returns a user list 
    

    //returns a user list
    @Override
    public List<User> offusers(Date startDate, Date endDate, String textbox, String states) {
        String sql= getSql("xtats.users.off.records");
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and country.name = ?";
                 return objects(sql, User.class, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and institution.name = ?";
                return objects(sql, User.class, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and users.username = ?";
                return objects(sql, User.class, startDate, endDate,textbox);
            }
            else{
                return objects(sql, User.class, startDate, endDate);
            }
        
    }

    @Override
    public List<User> newusersgraphic(Date startDate, Date endDate, String textbox, String states, String prefix) {
        String sql= "SELECT count(\"public\".users.username) as y, to_char(\"public\".users.registration_date, ? ) as rgdate FROM  \"public\".users join \"public\".country on \"public\".users.country_id=\"public\".country.country_id join \"public\".institution on \"public\".users.inst_id=\"public\".institution.inst_id where \"public\".users.registration_date between ? and ?";
        
            
            if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" and country.name = ? group by rgdate order by rgdate";
                 return objects(sql, User.class,prefix, startDate, endDate,textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" and institution.name = ? group by rgdate order by rgdate";
                return objects(sql, User.class,prefix, startDate, endDate,textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" and users.username = ? group by rgdate order by rgdate";
                return objects(sql, User.class,prefix, startDate, endDate,textbox);
            }
            else{
                sql+=" group by rgdate order by rgdate";
                return objects(sql, User.class,prefix, startDate, endDate);
            }
         
    }
    
     @Override
    public List<User> distsubmissions(String textbox, String states) {
        String sql=getSql("xtats.all.submissions.veredicts");
        if(states.equals("Nationality") && textbox != null && !textbox.isEmpty() ){
                sql+=" where country.name = ?";
                 return objects(sql, User.class, textbox);
                }
            else if(states.equals("Institution") && textbox != null && !textbox.isEmpty()){
                sql+=" where institution.name = ?";
                return objects(sql, User.class, textbox);
            }
            else if(states.equals("User") && textbox != null && !textbox.isEmpty()){
                sql+=" where users.username = ?";
                return objects(sql, User.class, textbox);
            }
            else{
                return objects(sql, User.class);
            }        
                  
     }
}
