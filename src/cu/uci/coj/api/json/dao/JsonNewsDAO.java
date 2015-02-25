/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.dao;

import cu.uci.coj.api.json.model.JsonNews;
import cu.uci.coj.dao.BaseDAO;
import java.util.List;

/**
 *
 * @author jasoria
 */
public interface JsonNewsDAO extends BaseDAO{
 
    public List<JsonNews> enabledNews();
}
