package com.jp.main.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.imageio.stream.FileImageInputStream;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import com.jp.main.entity.CarTakeVo;
import com.jp.main.service.ConvertHelper;
import com.jp.system.ApplicationLogging;

/**
 * 网络字节序  -> 大端
 * @author zh.h
 *
 */
public class ConvertFixPackageNetOrder extends ApplicationLogging implements ConvertHelper{
	private final byte START = (byte)0xAA;
	private final byte END = (byte)0x55;
	private final byte ZERO = (byte)0; //填充0
	private final byte VERSION= (byte)1;
	private final byte TYPE = (byte)2;
	private final String SIGNATURE = "JINPENG2011.KT-ITMS-CX.ZHU.HAI.KA.KOU.201309302O112O1220130O0O01";
	private final short PROTOCOLTYPE = 1101;
	private final short CRC = 2;
	private final byte[] xxbh =new byte[15];
	private final byte[] kkbh =new byte[18];
	private final byte[] sbbh =new byte[18];
	private final byte[] jgsj =new byte[24];
	private final byte[] cdbh = new byte[10];
	private final byte[] hphm = new byte[15];
	private final byte[] hpys = new byte[1];
	private final byte[] cwhphm = new byte[15];
	private final byte[] cwhpys = new byte[1];
	private final byte[] hpyz = new byte[1];
	private final byte[] xszt = new byte[4];
	private final byte[] clpp = new byte[4];
	private final byte[] clwx = new byte[4];
	private final byte[] csys = new byte[5];
	private final byte[] cb = new byte[2];
	private final byte[] cllx = new byte[4];
	private final byte[] hpzl = new byte[2];
	private final byte[] ssdq = new byte[10];
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private final Charset charset=Charset.forName("UTF-8");
	
	public byte[] pack(IoSession session,Object obj){
		return pack(session, obj, true);
	}

	private byte[] pack(IoSession session, Object obj, boolean b) {
		return null;
	}

	public IoBuffer pack(IoBuffer buff, Object message ) {
		return pack(buff, message, true);
	}

	private IoBuffer pack(IoBuffer buff, Object message, boolean b) {
		CarTakeVo vo = null;
		if(message instanceof CarTakeVo){
			vo = (CarTakeVo) message; 
			this.convert(buff,vo);
		}
		return buff;
	}

	private void convert(IoBuffer buff, CarTakeVo vo) {
		log.debug("convert starting "+vo.toString());
		try{
			int index = 0;
			buff.put(START);
			buff.put(START);
			buff.put(SIGNATURE.getBytes(charset));
			buff.put(VERSION);
			buff.put(TYPE);
			buff.putShort(PROTOCOLTYPE);
			IoBuffer ioBuff = IoBuffer.allocate(1024);
			ioBuff.setAutoExpand(true);
			if(null !=vo.getXxbh() && !"".equals(vo.getXxbh())){//信息编号
				if(vo.getXxbh().getBytes().length>16){
					fillArray(ioBuff, ZERO, xxbh);
				}else{
					fillArray(ioBuff,ZERO, vo.getXxbh().getBytes(charset), 0, xxbh, LenDifference(xxbh,vo.getXxbh()));
				}
			}else{
				fillArray(ioBuff, ZERO, xxbh);
			}
			index += 15;
			if(null !=vo.getKkbh() && !"".equals(vo.getKkbh())){//卡口编号
				if(vo.getKkbh().getBytes().length>19){
					fillArray(ioBuff, ZERO, kkbh);
				}else{
					fillArray(ioBuff,ZERO, vo.getKkbh().getBytes(charset), 0, kkbh, LenDifference(kkbh,vo.getKkbh()));
				}
			}else{
				fillArray(ioBuff, ZERO, kkbh);
			}
			index += 18;
			if(null !=vo.getSbbh() && !"".equals(vo.getSbbh())){//设备名称
				if(vo.getSbbh().getBytes().length>19){
					fillArray(ioBuff, ZERO, sbbh);
				}else{
					fillArray(ioBuff,ZERO, vo.getSbbh().getBytes(charset), 0, sbbh, LenDifference(sbbh,vo.getSbbh()));
				}
			}else{
				fillArray(ioBuff, ZERO, sbbh);
			}
			index += 18;
			if(null !=vo.getJgsj() && !"".equals(vo.getJgsj())){//经过时间
				if(sdf.format(vo.getJgsj()).getBytes().length>25){
					fillArray(ioBuff, ZERO, jgsj);
				}else{
					fillArray(ioBuff,ZERO, sdf.format(vo.getJgsj()).getBytes(charset), 0, jgsj, LenDifference(jgsj,sdf.format(vo.getJgsj())));
				}
				
			}else{
				ioBuff.put("0000-00-00 00:00:00.000".getBytes(charset));
			}
			index += 24;
			if(null !=vo.getCdbh() && !"".equals(vo.getCdbh())){//车道编号
				if(vo.getCdbh().getBytes().length>11){
					fillArray(ioBuff, ZERO, cdbh);
				}else{
					fillArray(ioBuff,ZERO, vo.getCdbh().getBytes(charset), 0, cdbh, LenDifference(cdbh,vo.getCdbh()));
				}
			}else{
				fillArray(ioBuff, ZERO, cdbh);
			}
			index += 10;
			if(null !=vo.getHphm() && !"".equals(vo.getHphm())){//号牌号码
				if(vo.getHphm().getBytes().length>11){
					fillArray(ioBuff, ZERO, hphm);
				}else{
					fillArray(ioBuff,ZERO, vo.getHphm().getBytes(charset), 0, hphm, LenDifference(hphm,vo.getHphm()));
				}
			}else{
				fillArray(ioBuff, ZERO, hphm);
			}
			index += 15;
			if(null !=vo.getHpys() && !"".equals(vo.getHpys())){//号牌颜色
				if(vo.getHpys().getBytes().length>2){
					fillArray(ioBuff, ZERO, hpys);
				}else{
					fillArray(ioBuff,ZERO, vo.getHpys().getBytes(charset), 0, hpys, LenDifference(hpys,vo.getHpys()));
				}
			}else{
				ioBuff.put("4".getBytes(charset));
			}
			index += 1;
			if(null !=vo.getCwhphm() && !"".equals(vo.getCwhphm())){//车尾号牌号码
				if(vo.getCwhphm().getBytes().length>16){
					fillArray(ioBuff, ZERO, cwhphm);
				}else{
					fillArray(ioBuff,ZERO, vo.getCwhphm().getBytes(charset), 0, cwhphm, LenDifference(cwhphm,vo.getCwhphm()));
				}
			}else{
				fillArray(ioBuff, ZERO, cwhphm);
			}
			index += 15;
			if(null !=vo.getCwhpys() && !"".equals(vo.getCwhpys())){//车尾号牌颜色
				if(vo.getCwhpys().getBytes().length>2){
					fillArray(ioBuff, ZERO, cwhpys);
				}else{
					fillArray(ioBuff,ZERO, vo.getCwhpys().getBytes(charset), 0, cwhpys, LenDifference(cwhpys,vo.getCwhpys()));
				}
			}else{
				ioBuff.put("4".getBytes(charset));
			}
			index += 1;
			if(null !=vo.getHpyz() && !"".equals(vo.getHpyz())){//号牌一致
				if(vo.getHpyz().getBytes().length>2){
					fillArray(ioBuff, ZERO, hpyz);
				}else{
					fillArray(ioBuff,ZERO, vo.getHpyz().getBytes(charset), 0, hpyz, LenDifference(hpyz,vo.getHpyz()));
				}
			}else{
				ioBuff.put("0".getBytes(charset));
			}
			index += 1;
			if(null !=vo.getClsd() && !"".equals(vo.getClsd())){//车辆速度
				ioBuff.putFloat(vo.getClsd());
			}else{
				ioBuff.putFloat(0.0f);
			}
			index += 4;
			if(null !=vo.getClxs() && !"".equals(vo.getClxs())){//车辆限速
				ioBuff.putFloat(vo.getClxs());
			}else{
				ioBuff.putFloat(0.0f);
			}
			index += 4;
			if(null !=vo.getCscd() && !"".equals(vo.getCscd())){//车身长度
				ioBuff.putInt(vo.getCscd());
			}else{
				ioBuff.putInt(0);
			}
			index += 4;
			if(null !=vo.getXszt() && !"".equals(vo.getXszt())){//行驶状态
				if(vo.getXszt().getBytes().length>5){
					fillArray(ioBuff, ZERO, xszt);
				}else{
					fillArray(ioBuff,ZERO, vo.getXszt().getBytes(charset), 0, xszt, LenDifference(xszt,vo.getXszt()));
				}
			}else{
				fillArray(ioBuff, ZERO, xszt);
			}
			index += 4;
			if(null !=vo.getClpp() && !"".equals(vo.getClpp())){//车辆品牌
				if(vo.getClpp().getBytes().length>5){
					fillArray(ioBuff, ZERO, clpp);
				}else{
					fillArray(ioBuff,ZERO, vo.getClpp().getBytes(charset), 0, clpp, LenDifference(clpp,vo.getClpp()));
				}
			}else{
				fillArray(ioBuff, ZERO, clpp);
			}
			index += 4;
			if(null !=vo.getClwx() && !"".equals(vo.getClwx())){//车辆外型
				if(vo.getClwx().getBytes().length>5){
					fillArray(ioBuff, ZERO, clwx);
				}else{
					fillArray(ioBuff,ZERO, vo.getClwx().getBytes(charset), 0, clwx, LenDifference(clwx,vo.getClwx()));
				}
			}else{
				fillArray(ioBuff, ZERO, clwx);
			}
			index += 4;
			if(null !=vo.getCsys() && !"".equals(vo.getCsys())){//车身颜色
				if(vo.getCsys().getBytes().length>6){
					fillArray(ioBuff, ZERO, csys);
				}else{
					fillArray(ioBuff,ZERO, vo.getCsys().getBytes(charset), 0, csys, LenDifference(csys,vo.getCsys()));
				}
			}else{
				fillArray(ioBuff, ZERO, csys);
			}
			index += 5;
			if(null !=vo.getCb() && !"".equals(vo.getCb())){//车标
				if(vo.getCb().getBytes().length>3){
					fillArray(ioBuff, ZERO, cb);
				}else{
					fillArray(ioBuff,ZERO, vo.getCb().getBytes(charset), 0, cb, LenDifference(cb,vo.getCb()));
				}
			}else{
				fillArray(ioBuff, ZERO, cb);
			}
			index += 2;
			if(null !=vo.getCllx() && !"".equals(vo.getCllx())){//车辆类型
				if(vo.getCllx().getBytes().length>5){
					fillArray(ioBuff, ZERO, cllx);
				}else{
					fillArray(ioBuff,ZERO, vo.getCllx().getBytes(charset), 0, cllx, LenDifference(cllx,vo.getCllx()));
				}
			}else{
				fillArray(ioBuff, ZERO, cllx);
			}
			index += 4;
			if(null !=vo.getHpzl() && !"".equals(vo.getHpzl())){//号牌种类
				if(vo.getHpzl().getBytes().length>3){
					fillArray(ioBuff, ZERO, hpzl);
				}else{
					fillArray(ioBuff,ZERO, vo.getHpzl().getBytes(charset), 0, hpzl, LenDifference(hpzl,vo.getHpzl()));
				}
			}else{
				fillArray(ioBuff, ZERO, hpzl);
			}
			index += 2;
			if(null !=vo.getSsdq() && !"".equals(vo.getSsdq())){//所属地区
				if(vo.getSsdq().getBytes().length>11){
					fillArray(ioBuff, ZERO, ssdq);
				}else{
					fillArray(ioBuff,ZERO, vo.getSsdq().getBytes(charset), 0, ssdq, LenDifference(ssdq,vo.getSsdq()));
				}
			}else{
				fillArray(ioBuff, ZERO, ssdq);
			}
			index += 10;
			if(null !=vo.getTpsl() && !"".equals(vo.getTpsl())){//图片数量
				ioBuff.putInt(vo.getTpsl());
			}else{
				ioBuff.putInt(0);
			}
			index += 4;
			if(null !=vo.getTx1() && !"".equals(vo.getTx1())){//图片URI
				ioBuff.putInt(getPicLength(vo.getTx1()));
				index += 4;
				byte[] picByte=getPic2byteArray(vo.getTx1());
				ioBuff.put(picByte);
				index += picByte.length;
			}else{
				ioBuff.putInt(0);
				ioBuff.put(new byte[0]);
			}
			byte[] data = Arrays.copyOfRange(ioBuff.array(), 0,ioBuff.position());
//			System.out.println(index+":数据包总长度");
//			System.out.println(ioBuff.position()+":ioBuff.position()");
//			System.out.println(data.length+":Arrays.copyOfRange");
//			System.out.println("---------------------------------------------------------");
			buff.putInt(index);
//			System.out.println(buff.position()+":buff未加数据包长度");
			buff.put(data);
			buff.putShort(CRC);
//			System.out.println(buff.position()+":未加结尾长度");
			buff.put(END);
			buff.put(END);
//			System.out.println(buff.position()+":总包实际长度");
//			System.out.println(buff.array().length+":总包长度");
		}catch(Exception e){
			log.error("ConvertFixPackage Convert error ! "+e.getMessage());
		}
	}
	
	public int LenDifference(byte[] arr,String str){
		return arr.length-str.getBytes(charset).length;
	}
	
	public void fillArray(IoBuffer buff,byte b ,byte[] a , int x , byte[] arr , int y){
		Arrays.fill(arr, b);
		System.arraycopy(a, x, arr, y, a.length);
		buff.put(arr);
	}
	
	public void fillArray(IoBuffer buff , byte b ,byte[] arr){
		Arrays.fill(arr, b);
		buff.put(arr);
	}
	
	public int getPicLength(String picPath){
		File file =new File(picPath);
		if (file.exists()){
			return (int) file.length();
		}else{
			return 0;
		}
	}
	
	public byte[] getPic2byteArray(String picPath){
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
			log.error(picPath +" is not exists!");
		}finally{
			try {
				out.close();
				in.close();
			} catch (IOException e1) {
				log.error(" I/O can not been closed! "+e1);
			}
		}
		return data;
	}
}
