/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.utils;

import cu.uci.coj.config.Config;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2012-03-22
 * @see http://coj.uci.cu
 */
public class RsyncThread extends Thread {

    public RsyncThread() {
    }    

    @Override
    public void run() {
        String cmd = Config.getProperty("rsync.command");
        String exec = "";
        String[] servers = Config.getProperty("rsync.servers").split(",");
        if (servers != null) {
            for (int i = 0; i < servers.length; i++) {
                exec = cmd.replace("<ip>", servers[i]);
                Process process = null;
                try {
                    process = Runtime.getRuntime().exec(exec);
                    process.waitFor();
                } catch (Exception e) { 
                    System.out.println(e.getMessage());
                } finally {
                    try {
                        process.getErrorStream().close();
                    } catch (Exception e) {
                    }
                    try {
                        process.getInputStream().close();
                    } catch (Exception e) {
                    }
                    try {
                        process.getOutputStream().close();
                    } catch (Exception e) {
                    }
                    try {
                        process.destroy();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }
}
