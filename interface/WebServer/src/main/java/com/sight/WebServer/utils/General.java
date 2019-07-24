package com.sight.WebServer.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.util.Random;
public class General {
	static String TimeZoneCode = "Asia/Shanghai";
	static Map<String,Boolean> tokenList = new HashMap<String,Boolean>();
	public static String getDateTime() {
	    Date d = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
	    sdf.setTimeZone(TimeZone.getTimeZone(TimeZoneCode));
	    return sdf.format(d);
	}
	public static String RandomPostfix() {
		Random rnd = new Random();
		String ret = getDateTime() + rnd.nextInt(10) + rnd.nextInt(10) + rnd.nextInt(10) ;
        return ret;
	}
	public static String RandomToken() {
		String token = null;
		try {
			do{
				token = encodeByMd5(RandomPostfix());
			}while(tokenList.containsKey(token)==false);
			tokenList.put(token,true);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	public static void EraseToken(String token) {
		tokenList.remove(token);
	}
	public static String encodeByMd5(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        // 加密字符串
        return md5.digest(Base64.encode(string.getBytes("utf-8")).getBytes()).toString();
       
    }
	public static Date getTokenExpireDate() {
		 
		GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(new Date()); 
		gc.add(5,1); 
		return gc.getTime();
	}
}
