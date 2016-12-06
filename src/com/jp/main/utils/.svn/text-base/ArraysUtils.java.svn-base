package com.jp.main.utils;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.mina.core.buffer.IoBuffer;

public class ArraysUtils {
	
	/**
	 * 支持多个不同类型数组合并
	 * @param obj
	 * @param objects
	 * @return
	 */
	public static Object[] togetherArrays(Object[] obj,Object[]...objects){
		int len=0;
		for(Object[] arr:objects){
			System.arraycopy(arr, 0, obj, len, arr.length);
			len += arr.length;
		}
		return obj;
	}
	
	public static int LenDifference(byte[] arr,String str,Charset charset){
		return arr.length-str.getBytes(charset).length;
	}
	
	public static void fillArray(IoBuffer buff , byte b ,byte[] arr){
		Arrays.fill(arr, b);
		buff.put(arr);
	}
	
	public static void fillArray(IoBuffer buff,byte b ,byte[] a , int x , byte[] arr , int y){
		Arrays.fill(arr, b);
		System.arraycopy(a, x, arr, y, a.length);
		buff.put(arr);
	}
}
