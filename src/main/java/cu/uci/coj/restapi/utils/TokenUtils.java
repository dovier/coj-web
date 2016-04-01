/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.utils;

import com.fasterxml.jackson.databind.JsonNode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.crypto.codec.Base64;

/**
 *
 * @author lucy
 */
public class TokenUtils {
    
    //TOKEN = UsernameMD5:hash:expirity:username
    //usernameMD5:username:hash:expiry
    //hash=MD5(username+magickey)
    //expiry=current_timestamp+mins_to_expiry
    static String userKey = "token%of%user";
    static String developedKey = "token%of%develop%api%key";
    static String secretKey = "MiSuperClaveParaLaSeguridadDelCOJ123458%$^&&^^%%Yatusabes";
    
    public static Long expirityToken = new Long(100*60*1000);
    public static Long expirityAPIKey = new Long(30 * 24 * 60 * 60 *1000);
    
    public static String CreateTokenUser(String username){
        String token;
        String usernameMD5 = EncryptToMD5(username);
        String hash = EncryptToMD5(username+userKey);
        Long tokentime = Calendar.getInstance().getTimeInMillis()+expirityToken;
        token = usernameMD5+":"+hash+":"+tokentime+":"+username;
        return Encriptar(token);
    }
    //TOKEN = UsernameMD5:hash:expirity:username
    public static int ValidateTokenUser(String token) throws Exception{
        token=Desencriptar(token);
        
        String[] parts = token.split(":");
        
        String usernameMD5 = EncryptToMD5(parts[3]);
        if(!usernameMD5.equals(parts[0]))
            return 1;
        
        String hash = EncryptToMD5(parts[3]+userKey);
        if(!hash.equals(parts[1]))
            return 2;
        
        Long tokentime = new Long(parts[2]);
        if(tokentime < Calendar.getInstance().getTimeInMillis())
            return 3;
        
        
        return 0;
    }
    
    
    /*
    API Key = usernameMD5 : hash : expirity : Base64(secret+secretkey) : username
    */
    public static String CreateAPIKey(String username,String secret){
        String apikey;
        String usernameMD5 = EncryptToMD5(username);
        String hash = EncryptToMD5(username+developedKey);
        Date date = new Date(Calendar.getInstance().getTimeInMillis()+expirityAPIKey);
        String secretEncripted = Encriptar(secret);
        apikey = usernameMD5+":"+hash+":"+date.getTime()+":"+secretEncripted+":"+username;
        return Encriptar(apikey);
    }
    
    public static int ValidateAPIKey(String apikey) throws Exception{
        apikey = Desencriptar(apikey);
        String[] parts = apikey.split(":");
        
        String usernameMD5 = EncryptToMD5(parts[4]);
        if(!usernameMD5.equals(parts[0]))
            return 4;
        
        String hash = EncryptToMD5(parts[4]+developedKey);
        if(!hash.equals(parts[1]))
            return 5;
        
        Date date = new Date(Long.parseLong(parts[2]));
        if(date.getTime() > Calendar.getInstance().getTimeInMillis() + expirityAPIKey)
            return 6;
        
        String secretDesencriptado = Desencriptar(parts[3]);
        if(!true)  //Metodo que se puede agregar para validar el secret
            return 7;
     
        return 0;
    }
    
    public static String getUsernameFromToken(String token) throws Exception{
        token = Desencriptar(token);
        return token.split(":")[3];
    }
    
    public static String EncryptToMD5(String text){
        try{
            final char[] HEXADECIMALES = { '0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f' };
            MessageDigest msgdgt = MessageDigest.getInstance("MD5");
            byte[] bytes = msgdgt.digest(text.getBytes());
            StringBuilder strCryptMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++){
                int low = (int)(bytes[i]&0x0f);
                int high = (int)((bytes[i] & 0xf0) >> 4);
                strCryptMD5.append(HEXADECIMALES[high]);
                strCryptMD5.append(HEXADECIMALES[low]);
            }
            return strCryptMD5.toString();
        }catch(NoSuchAlgorithmException e) 
        {return null;}
    }
    
    
   public static String Encriptar(String texto) {
 
        
        String base64EncryptedString = "";
 
        try {
 
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encode(buf);
            base64EncryptedString = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
   
   
   public static String Desencriptar(String textoEncriptado) throws Exception {
 
      
        String base64EncryptedString = "";
 
        try {
            byte[] message = Base64.decode(textoEncriptado.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
 
            byte[] plainText = decipher.doFinal(message);
 
            base64EncryptedString = new String(plainText, "UTF-8");
 
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
   
   public static boolean ValidatePropertiesinJson(JsonNode node,String ... prop){
        for(int i=0;i<prop.length;i++){
            if(!node.has(prop[i]))
                return false;
        }
        return true;
    }
   
   
   public static String ErrorMessage(int error) {
        switch (error) {
            case 1:
                return ErrorUtils.USERNAME_TOKEN_MISMATCH;
            case 2:
                return ErrorUtils.HASH_INCORRECT;
            case 3:
                return ErrorUtils.TOKEN_EXPIRED;
            case 4:
                return ErrorUtils.USERNAME_APIKEY_MISMATCH;
            case 5:
                return ErrorUtils.HASH_INCORRECT_APIKEY;
            case 6:
                return ErrorUtils.APIKEY_EXPIRED;
            case 7:
                return ErrorUtils.APIKEY_SECRET_INCORRECT;
            case 8:
                return ErrorUtils.INCORRECT_JSON;
            case 9:
                return ErrorUtils.INCORRECT_TOKEN_OR_APIKEY;
            case 10:
                return ErrorUtils.MISSING_PROPERTIES;
        }

        return "";
    }
    
}
