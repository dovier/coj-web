/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.uci.coj.utils;

/**
 *
 * @author dae
 */
public class Notification {
    public static String getSuccesfullCreateEntry() {return "entry.pending.approval";}
    public static String getSuccesfullCreate() { return "infomsg.create.success";  }
    public static String getSuccesfullUpdate() {
        return "infomsg.update.success";
    }
    public static String getNotSuccesfullUpdate() {
        return "infomsg.notupdate.success";
    }
    public static String getSuccesfull(){return "infomsg.success";}
    public static String getNotSuccesfull(){return "infomsg.notsuccess";}
    public static String getSuccesfullDelete() {
        return "infomsg.delete.success";
    }
    public static String getSuccesfullDeleteAll() {
        return "infomsg.deleteall.success";
    }
    public static String getSuccesfullRejudge() {
        return "infomsg.rejudge.success";
    }
    public static String getInvalidService() {return  "infomsg.invalidservice.sucess";}
    public static String getNotSuccesfullCreate() {
        return "infomsg.notcreate.success";
    }
    public static String getSuccesfullTranslate() {return  "infomsg.translate.sucess";}
    
    public static String getAcceptedTranslate() {return  "infomsg.translate.acepted";}
    public static String getRechazedTranslate() {return  "infomsg.translate.reject";}
    
    public static String getSuccesfullSendMail() {return  "infomsg.sendmessage.sucess";}
    public static String getNotSuccesfullSendMail() {return  "infomsg.notsendmessage.sucess";}
    public static String getNormalizeProblem() {return "infomsg.normalize.success";}
}
