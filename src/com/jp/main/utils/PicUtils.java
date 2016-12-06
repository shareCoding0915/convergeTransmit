package com.jp.main.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;

public class PicUtils {
	
	public static int getPicLength(String picPath){
		File file =new File(picPath);
		if (file.exists()){
			return (int) file.length();
		}else{
			return 0;
		}
	}
	
	public static byte[] getPic2byteArray(String picPath){
		File file =new File(picPath);
		FileImageInputStream in=null;
		ByteArrayOutputStream out=null;
		byte[] data = null;
		try {
			in = new FileImageInputStream(file);
			out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
		    while ((numBytesRead = in.read(buf)) != -1) {
		      out.write(buf, 0, numBytesRead);
		    }
		    data = out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				out.close();
				in.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return data;
	}
}
