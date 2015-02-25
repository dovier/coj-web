/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.controller.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see http://coj.uci.cu
 */
public class IForIntegerMap implements RowMapper<Object> {

    Map<Integer, Integer> mymap;

    public IForIntegerMap() {
        mymap = new HashMap<Integer, Integer>();
    }

    public Map<Integer, Integer> getMymap() {
        return mymap;
    }

    public void setMymap(Map<Integer, Integer> mymap) {
        this.mymap = mymap;
    }

    public Object mapRow(ResultSet rs, int i) throws SQLException {
        mymap.put(rs.getInt(1), rs.getInt(1));
        return null;
    }

}
