/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import cu.uci.coj.config.Config;
import cu.uci.coj.dao.ContestDAO;
import cu.uci.coj.model.Stats;
import cu.uci.coj.model.SubmissionJudge;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Semaphore;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author CHALLENGER ONLINE JUDGE
 */
public class Store {

    private static int port;
    private static HashMap<String, Boolean> servers = null;
    private static HashMap<String, LinkedList<String>> charge;
    private static Socket socket;
    private String registerMSG = "Dear <user> ";
    public static final String[] MALICIOUS = {
        "fputc", "fgetc", "fgets", "fread", "fwrite", "rewind", "fseek", "ftell",
        "ferror", "fopen", "freopen", "fclose", "fprintf", "fscanf", "FILE",
        "opendir", "readdir", "seekdir", "telldir", "getenv", "system", "execl", "execlp",
        "execv", "execvp	access", "chdir", "fchdir", "readdir", "remove",
        "rename", "open", "fork"};
    public static HashMap<String, Float> statistics;
    public static HashMap<String, Float> cstatistics;
    public static HashMap<String, String> ip_url;
    public static HashMap<String, String> onlineusers;
    public static HashMap<String, String> sessionIdUsers;
    public static LinkedList<SubmissionJudge> submissions = new LinkedList<SubmissionJudge>();
    private static Semaphore sem = new Semaphore(1);
    public static Semaphore semJudge = new Semaphore(1);
    public static Semaphore semPool = new Semaphore(1);
    public static boolean is_judge_running = false;

        public static double formula(int ac) {
        return (double) Integer.valueOf(Config.getProperty("formula.value")) / (double) (40 + ac);
    }

    public static double formulaFreeContest(int ac, int points) {
        return (double) points - points / ac;
    }

    
    public static void startCalification(ContestDAO contestDAO) {
        if (!is_judge_running) {
            try {
                is_judge_running = true;
//                Judge judge = new Judge(contestDAO);
//                judge.start();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static boolean isONline(String username) {
        if (onlineusers != null) {
            if (onlineusers.containsKey(username)) {
                return true;
            }
        }
        return false;
    }

    public static void putUser(String username, String sessionId) {
        if (onlineusers == null) {
            onlineusers = new HashMap<String, String>();
        }
        onlineusers.put(username, username);
        if (sessionIdUsers == null) {
            sessionIdUsers = new HashMap<String, String>();
        }
        sessionIdUsers.put(sessionId, username);
    }

    public static void removeUser(String sessionID) {
        if (sessionIdUsers != null && onlineusers != null) {
            if (sessionIdUsers.containsKey(sessionID) && onlineusers.containsKey(sessionIdUsers.get(sessionID))) {
                onlineusers.remove(sessionIdUsers.get(sessionID));
                sessionIdUsers.remove(sessionID);
            }
        }
    }

    public static void addIpUrl(HttpServletRequest request) {
        try {
            if (request.getQueryString() == null) {
                //ip_url.put(request.getRequestedSessionId(), request.getRequestURI());
                request.setAttribute("rurl", request.getRequestURI());
            } else {
                //ip_url.put(request.getRequestedSessionId(), request.getRequestURI() + "?" + request.getQueryString());
                request.setAttribute("rurl", request.getRequestURI() + "?" + request.getQueryString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String addParameter(String url, String parameter) {
        url = url.replace("?lang=en", "");
        url = url.replace("&lang=en", "");
        url = url.replace("?lang=es", "");
        url = url.replace("&lang=es", "");
        if (url.contains("?")) {
            url += "&" + parameter;
        } else {
            url += "?" + parameter;
        }
        return url;
    }

    public static String getUrl(String ip) {
        if (ip_url.containsKey(ip)) {
            String uri = ip_url.get(ip);
            uri = uri.replace("/chjudge", "");
            return uri;
        }
        return "/";
    }

    public static void loadServers() {
        if (servers == null) {
            port = new Integer(Config.getProperty("port"));
            servers = new HashMap<String, Boolean>();
            String[] serversL = Config.getProperty("servers").split(",");
            for (int i = 0; i < serversL.length; i++) {
                String string = serversL[i];
                servers.put(string, Boolean.FALSE);
            }
        }
    }

    public static void loadStatistics(Stats stats) {
        statistics = new HashMap<String, Float>();
        statistics.put("ACC", stats.getAcc_percent());
        statistics.put("WA", stats.getWa_percent());
        statistics.put("PE", stats.getPe_percent());
        statistics.put("TLE", stats.getTle_percent());
        statistics.put("MLE", stats.getMle_percent());
        statistics.put("FLE", stats.getFle_percent());
        statistics.put("OLE", stats.getOle_percent());
        statistics.put("CE", stats.getCe_percent());
        statistics.put("RTE", stats.getRte_percent());
        statistics.put("UQ", stats.getUq_percent());
        statistics.put("IVF", stats.getIvf_percent());
    }

    public static void loadCStatistics(Stats stats) {
        cstatistics = new HashMap<String, Float>();
        cstatistics.put("ACC", stats.getAcc_percent());
        cstatistics.put("WA", stats.getWa_percent());
        cstatistics.put("PE", stats.getPe_percent());
        cstatistics.put("TLE", stats.getTle_percent());
        cstatistics.put("MLE", stats.getMle_percent());
        cstatistics.put("FLE", stats.getFle_percent());
        cstatistics.put("OLE", stats.getOle_percent());
        cstatistics.put("CE", stats.getCe_percent());
        cstatistics.put("RTE", stats.getRte_percent());
        cstatistics.put("UQ", stats.getUq_percent());
        cstatistics.put("IVF", stats.getIvf_percent());
    }

    public static Boolean getState(String server) {
        if (servers == null) {
            loadServers();
        }
        return servers.get(server);
    }

    public static void switchState(String server) {
        if (servers.get(server)) {
            servers.put(server, Boolean.FALSE);
        } else {
            servers.put(server, Boolean.TRUE);
        }
    }

    public static String getFreeServer() {
        int off = 0;
        if (servers == null) {
            loadServers();
        }
        Set<String> set = servers.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext();) {
            String string = it.next();
            if (!servers.get(string)) {
                if (checkServer(string)) {
                    servers.put(string, Boolean.TRUE);
                    return string;
                } else {
                    off++;
                }
            }
        }
        if (off == servers.size()) {
            return "off";
        }
        return null;
    }

    private static boolean checkServer(String server) {
        socket = new Socket();
        boolean state = false;
        try {
            SocketAddress address = new InetSocketAddress(server, 5000);
            socket.connect(address, 1500);
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject("chk");
            String msg = input.readObject().toString();
            input.close();
            output.close();
            socket.close();
            state = true;
        } catch (Exception e) {
            state = false;
        }
        return state;
    }

    public static int getPort() {
        if (servers == null) {
            loadServers();
        }
        return port;
    }

    public static void removeId(String id) {
        charge.remove(id);
    }

    public static void removeView(String id, String view) {
        LinkedList<String> list = charge.get(id);
        list.remove(view);
        charge.put(id, list);
    }

    public static void Mark(String id, String view) {
        if (charge == null) {
            charge = new HashMap<String, LinkedList<String>>();
        }
        if (charge.size() > 150) {
            charge.clear();
        }
        if (charge.containsKey(id)) {
            LinkedList<String> list = charge.get(id);
            list.add(view);
            charge.put(id, list);

        } else {
            LinkedList<String> list = new LinkedList<String>();
            list.add(view);
            charge.put(id, list);

        }

    }

    public static boolean checkFlush(String id, String view) {
        if (charge == null) {
            return false;
        }
        if (charge.containsKey(id)) {
            LinkedList<String> list = charge.get(id);
            if (list.contains(view)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    

    public static LinkedList<String> getCommandList(String cmdline) {
        String[] array = cmdline.split(" ");
        LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            list.add(string);
        }
        return list;
    }
    
    public static String Cript(String pass) {
        int stop = 0;
        String cryptpass = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] convert = md.digest();

            for (int i = 0; i < convert.length; i++) {
                cryptpass += convert[i];

            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(cryptpass);
        return cryptpass;
    }

}
