/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.utils;

/**
 *
 * @author lucy
 */
public class ErrorUtils {

    public static String USERNAME_TOKEN_MISMATCH = "{\"error\":\"username token mismatch\"}";
    public static String HASH_INCORRECT = "{\"error\":\"hash incorrect\"}";
    public static String TOKEN_EXPIRED = "{\"error\":\"token expirated\"}";
    public static String USERNAME_APIKEY_MISMATCH = "{\"error\":\"username apikey mismatch\"}";
    public static String HASH_INCORRECT_APIKEY = "{\"error\":\"apikey hash incorrect\"}";
    public static String APIKEY_EXPIRED = "{\"error\":\"apikey expirated\"}";
    public static String APIKEY_SECRET_INCORRECT = "{\"error\":\"apikey secret incorrect\"}";
    public static String INCORRECT_JSON = "{\"error\":\"incorrect request\"}";
    public static String INCORRECT_TOKEN_OR_APIKEY = "{\"error\":\"token or apikey incorrect\"}";
    public static String MISSING_PROPERTIES = "{\"error\":\"Missig Properties\"}";
    
    public static String PAGE_OUT_OF_INDEX = "{\"error\":\"page out of index\"}";
    public static String BAD_PID = "{\"error\":\"bad pid\"}";
    public static String BAD_USER = "{\"error\":\"bad user\"}";
    public static String BAD_CID = "{\"error\":\"bad cid or access private\"}";
    public static String BAD_SID = "{\"error\":\"bad sid\"}";
    public static String USERNAME_DISABLED = "{\"error\":\"username is not enabled\"}";
    public static String BAD_COUNTRY_ID = "{\"error\":\"bad country id\"}";
    public static String BAD_INSTITUTION_ID = "{\"error\":\"bad institution id\"}";
    public static String BAD_USERNAME_PASSWORD = "{\"error\":\"bad username or password\"}";
    public static String INBOX_OVERFLOW = "{\"error\":\"inbox_overflow\"}";
    public static String RECEIVER_INBOX_OVERFLOW = "{\"error\":\"receiver inbox overflow\"}";
    public static String BAD_LANGUAGE = "{\"error\":\"bad language\"}";
    public static String INVALID_EMAIL = "{\"error\":\"invalid email\"}";
    public static String ERROR_UPDATE_CODE = "{\"error\":\"error update code\"}";
    public static String FAILED_SEND_EMAIL = "{\"error\":\"failed send email\"}";
    
    
    
    
    
}
