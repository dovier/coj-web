/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @version Caribbean Online Judge(COJ) v2.0
 * @author Juan Carlos Lobaina Guzman & Jorge Luis Roque Alvarez
 * @since 2010-09-01
 * @see \http://coj.uci.cu
 */

public class IpAddress {

    private static final String IPADDRESS_PATTERN = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|\\*)$";

    private static final String IPADDRESS_PATTERN2 =
            "^([01]?\\d\\d?|2[0-4]\\d|25[0-5]) | \\*\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5]) | \\*\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5]) | \\*\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5]) | \\*$";

    public IpAddress() {
       // pattern = Pattern.compile(IPADDRESS_PATTERN);
    }

    /**
     * Validate ip address with regular expression
     * @param ip ip address for validation
     * @return true valid ip address, false invalid ip address
     */
    public static boolean validate(final String ip) {
        if(ip.equals("*"))
            return true;
        String[] ar = ip.split("\\.");
        if (ar.length != 4 && ar.length != 6) {
            return false;
        }
        for (int i = 0; i < ar.length; i++) {
            if(ar[i].equals("*"))
                continue;
            int val = -1;
            try {
                val = Integer.valueOf(ar[i]);
            } catch (Exception e) {
            }
            if(val < 0 || val > 254)
                return false;
        }
        return true;
    }

    public static boolean vallidateIP(final String ip) {
        if (!ip.equals("*")) {
            Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
            Matcher matcher = pattern.matcher(ip);
            return matcher.matches();
        }

        return true;
    }
}
