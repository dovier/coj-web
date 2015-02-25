/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao.impl;

import cu.uci.coj.api.json.dao.JsonNewsDAO;
import cu.uci.coj.dao.impl.BaseDAOImpl;
import cu.uci.coj.api.json.model.JsonNews;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jasoria
 */
@Repository
public class JsonNewsDAOImpl extends BaseDAOImpl implements JsonNewsDAO{
    public List<JsonNews> enabledNews(){
        return objects("json.news",JsonNews.class);
    }
}
