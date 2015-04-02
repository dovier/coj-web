/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.model;

import cu.uci.coj.utils.enums.EventType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Event extends BaseBean {

    private Integer type;
    
    private Date date;
    private Map<String,String> eventArgs = new HashMap<String, String>();

    public Event() {
    }

    
    public Event(EventType type,Integer userId) {
        this.type = type.ordinal();
        
        this.date = new Date();
    }
    
    public Event(EventType type,String eventArgs) {
        this.type = type.ordinal();
        
        this.date = new Date();
        StringToMap(eventArgs);
    }
    
    public Event(EventType type, Date date, Map<String, String> eventArgs) {
        this.type = type.ordinal();
        
        this.date = date;
        this.eventArgs = eventArgs;
    }

    public Event(EventType type, Map<String, String> eventArgs) {
        
        this.eventArgs = eventArgs;
        this.date = new Date();
    }

    public String getArgs(){
        StringBuilder builder=new StringBuilder();
        for (Map.Entry<String,String> ent: eventArgs.entrySet())
            builder.append(ent.getKey()).append(":").append(ent.getValue()).append(";");
        //para quitar el ultimo separador de la cadena
        builder.deleteCharAt(builder.length()-1);
        return builder.toString();
    }
    
    private void StringToMap(String args){
        String[] array = args.split(";");
        eventArgs.clear();
        for(String arg:array){
            String[] entry = arg.split(":");
            eventArgs.put(entry[0], entry[1]);
        }
    }
    
    public void setArgs(String args){
       StringToMap(args);
    }
    
    public void setType(Integer type) {
        //esto solo es posible debido a que el tipo numero es el ordinal del enum
        this.type = type;
    }
    
    public Integer getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type.ordinal();
    }

    public EventType getEventType(){
        return EventType.values()[this.type];
    }
   
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Map<String, String> getEventArgs() {
        return eventArgs;
    }

    public void setEventArgs(Map<String, String> eventArgs) {
        this.eventArgs = eventArgs;
    }
}
