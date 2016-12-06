package com.jp.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jp.main.client.TcpShortClient;
import com.jp.main.entity.CarTakeVo;
import com.jp.main.service.impl.ConvertFixPackageNetOrder;
import com.jp.system.ConvergeTransmitConstants;

public class TestOutObject {
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		List<CarTakeVo> arr=new ArrayList<CarTakeVo>();
		for(int i=0;i<200;i++){
			CarTakeVo vo = new CarTakeVo();
			vo.setXxbh("20120502000000"+2);
			vo.setKkbh("440400140000000012");
			vo.setSbbh("440400140000019902");
			vo.setJgsj(sdf.parse("2011-03-18 21:33:10.100"));
			vo.setCdbh("01");
			vo.setHphm("桂MBL159");
			vo.setHpys("0");
			vo.setCwhphm("桂MBL159");
			vo.setCwhpys("0");
			vo.setHpyz("1");
			vo.setClsd(45.0f);
			vo.setClxs(120.0f);
			vo.setCscd(100);
			vo.setXszt("0");
			vo.setClpp("0585");
			vo.setClwx("02");
			vo.setCsys("0");
			vo.setCb("0");
			vo.setCllx("00");
			vo.setHpzl("0");
			vo.setSsdq("09");
			vo.setTpsl(1);
			vo.setTx1("C:/Users/JinPeng/Desktop/22.png");
			arr.add(vo);
		}
		
		
//		IoBuffer buff =IoBuffer.allocate(1024);
//		buff.setAutoExpand(true);
//		ConvertFixPackage fix = new ConvertFixPackage();
//		System.out.println(fix.pack(buff, vo).array().length+"--------------------");
//		System.out.println(Arrays.toString(fix.pack(buff, vo).array()));
		
//		TcpShortClient client = new TcpShortClient(new ConvertFixPackageLocalOrder());
		TcpShortClient little = new TcpShortClient(new ConvertFixPackageNetOrder());
		ConvergeTransmitConstants.queue.addAll(arr);
		little.start();
	}
}
