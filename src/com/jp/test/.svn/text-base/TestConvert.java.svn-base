package com.jp.test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class TestConvert {
	private final static byte START = (byte)0xAA;
	private final static byte FILL = (byte)0; //填充0
	private final static byte VERSION= (byte)1;
	private final static byte TYPE = (byte)2;
	private final static String SIGNATURE = "JINPENG2011.KT-ITMS-CX.ZHU.HAI.KA.KOU.201309302O112O1220130O0O01";
	private final static short PROTOCOLTYPE = 1101;
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(Integer.toHexString(17));
		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//		bytes[0]=START;
//		bytes[1]=START;
		byteBuffer.put(START);
		byteBuffer.put(START);
		byteBuffer = byteBuffer.put(SIGNATURE.getBytes(Charset.forName("UTF-8")));
		byteBuffer.putInt(VERSION);
		byteBuffer.putInt(TYPE);
		byteBuffer.putShort(PROTOCOLTYPE);
		byteBuffer.put("000000000000000".getBytes());
		System.out.println(new String(byteBuffer.array(),"UTF-8"));
		System.out.println(SIGNATURE.getBytes(Charset.forName("UTF-8")).length);
		System.out.println(byteBuffer.position());
		System.out.println(byteBuffer.flip());
		
		System.out.println("S".getBytes(Charset.forName("UTF-8")).length);
		System.out.println("------------------------------------------------");
		byte[] arr = new byte[15];
		Arrays.fill(arr, FILL);
//		System.arraycopy("粤A12345".getBytes(Charset.forName("UTF-8")), 0,arr, 6,  "粤A12345".getBytes(Charset.forName("UTF-8")).length); 
//		System.out.println(Arrays.toString(arr));
		
		System.out.println(Arrays.toString("粤A12345".getBytes(Charset.forName("UTF-8"))));
		copyOf("粤A12345".getBytes(Charset.forName("UTF-8")), 0,arr, 6);
		System.out.println("------------------------------------------------");
		System.out.println(Arrays.toString(arr));
		System.out.println("------------------------------------------------");
		
		System.out.println(sdf.format(new Date()).getBytes().length);
	}
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public static void copyOf (byte[] a , int x , byte[] b , int y){
		System.arraycopy(a, x, b, y, a.length);
	}

}
