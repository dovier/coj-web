/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.model;

import cu.uci.coj.model.BaseBean;
import net.sf.json.JSONObject;

/**
 *
 * @author jasoria
 */
public class JsonBaseBean extends BaseBean{
    @Override
    public String toString() {
        return JSONObject.fromObject(this).toString();
    }
}
