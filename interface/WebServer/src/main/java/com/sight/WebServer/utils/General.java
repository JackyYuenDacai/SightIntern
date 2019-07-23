package com.sight.WebServer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Random;
public class General {
	static String TimeZoneCode = "Asia/Shanghai";
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
}
