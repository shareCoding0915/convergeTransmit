package com.jp.system;

import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.jp.main.entity.CarTakeVo;

/**
 * 模块管理类
 * @author zh.h
 *
 */
public class ConvergeTransmitConstants{
	/*
	 * dabigate
	 */
	public final static int PORT;
	public final static String IP;
	public final static boolean TODABIGATE;
	/*
	 * ShortClient
	 */
	public static boolean ByteOrder = false;
	public static long Count = 0;
	public static long SessionExit = 10000;
	public static volatile BlockingQueue<CarTakeVo> queue = new LinkedBlockingQueue<CarTakeVo>();
	
	/*
	 * DB
	 */
	public static boolean DbMoudle = false;
	public static boolean SqlServer = false;
	public static boolean Oracle = false;
	public static boolean Mysql = false;
	
	/*
	 * Timer
	 */
	public static long Interval = 0l;
	
	private static ResourceBundle rb = ResourceBundle.getBundle("system");
	static{
	    PORT =getInt("port");
	    IP = getString("ip");
	    
	    TODABIGATE = getBoolean("toDabigate");
	    ByteOrder = getBoolean("order");
	    
	    DbMoudle = getBoolean("DbMoudle");
	    SqlServer = getBoolean("sqlServer");
	    Oracle = getBoolean("oracle");
	    Mysql = getBoolean("mySql");
	    
	    Interval = getLong("interval");
	    
	}
	
	public static int getInt(String key){
		return Integer.parseInt(rb.getString(key).trim());
	}
	
	public static long getLong(String key){
		return Long.parseLong(rb.getString(key).trim());
	}
	
	public static Float getFloat(String key){
		return Float.parseFloat(rb.getString(key).trim());
	}
	
	public static Double getDouble(String key){
		return Double.parseDouble(rb.getString(key).trim());
	}
	
	public static boolean getBoolean(String key){
		return Boolean.parseBoolean(rb.getString(key).trim());
	}
	
	public static String getString(String key){
		return rb.getString(key).trim();
	}
}
