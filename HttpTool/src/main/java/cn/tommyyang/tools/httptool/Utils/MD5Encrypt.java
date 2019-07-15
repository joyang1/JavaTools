package cn.tommyyang.tools.httptool.Utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by TommyYang on 2018/2/24.
 */
public class MD5Encrypt {

    private static String md5Encode(String str) throws NoSuchAlgorithmException {
        StringBuilder sign = new StringBuilder();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(str.getBytes());
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex);
        }
        return sign.toString();
    }

    public static String encrypt(String data, String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        key = md5Encode(key);
        Integer x = 0;
        Integer len = data.length();
        Integer l = key.length();
        String cha = "";
        String str = "";
        for (Integer i = 0; i < len; i++) {
            if (x == l) {
                x = 0;
            }
            cha += key.toCharArray()[x];
            x++;
        }
        for (Integer i = 0; i < len; i++) {
            str += (char)((int)data.toCharArray()[i] + ((int)cha.toCharArray()[i] % 256));
        }
        return Base64.encodeBase64String(str.getBytes("ISO-8859-1"));
    }

    public static String decrypt(String data, String key) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        key = md5Encode(key);
        Integer x = 0;
        data = new String(Base64.decodeBase64(data.getBytes()), "ISO-8859-1");
        Integer len = data.length();
        Integer l = key.length();
        String cha = "";
        String str = "";
        for (Integer i = 0; i < len; i++) {
            if (x == l) {
                x = 0;
            }
            cha += key.substring(x, x + 1);
            x++;
        }
        for (Integer i = 0; i < len; i++) {
            if(data.substring(i, i+1).toCharArray()[0] < cha.substring(i, i+1).toCharArray()[0]){
                str += (char)((int)data.substring(i, i+1).toCharArray()[0] + 256 - (int)cha.substring(i, i+1).toCharArray()[0]);
            }else {
                str += (char)((int)data.substring(i, i+1).toCharArray()[0] - (int)cha.substring(i, i+1).toCharArray()[0]);
            }
        }
        return new String(str.getBytes("ISO-8859-1"));
    }

}
