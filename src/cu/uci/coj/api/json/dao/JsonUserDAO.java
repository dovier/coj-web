/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao;

import cu.uci.coj.api.json.model.JsonUserRank;
import cu.uci.coj.dao.BaseDAO;
import cu.uci.coj.model.User;
import java.util.Date;
import java.util.List;


/**
 *
 * @author jasoria
 */
public interface JsonUserDAO extends BaseDAO{
    public List<JsonUserRank> ranks();
    public List<User> newusersgraphic(Date startDate, Date endDate, String textbox,String states, String prefix);
    public List<User> offusers(Date startDate, Date endDate, String textbox,String states);
    public List<User> distsubmissions(String textbox, String states);
}
