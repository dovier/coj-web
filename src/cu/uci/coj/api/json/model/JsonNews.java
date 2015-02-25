/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.api.json.model;

import cu.uci.coj.model.BaseBean;
import java.util.Date;

/**
 *
 * @author jasoria
 */
public class JsonNews extends JsonBaseBean{
    
    private String title;
    private String text;
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
}
