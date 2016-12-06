package com.jp.main.task;

import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import com.jp.db.DBMoudle;
import com.jp.main.client.TcpShortClient;
import com.jp.main.thread.DBWorker;
import com.jp.system.ConvergeTransmitConstants;

public class DBMoudleTimerTask extends TimerTask {

	private DBMoudle[] conns;
	private TcpShortClient client;
	
	public DBMoudleTimerTask(DBMoudle[] conns , TcpShortClient client){
		this.conns = conns;
		this.client = client;
	}
	
	@Override
	public void run() {
		CountDownLatch statuLatch = new CountDownLatch(conns.length);
		for(DBMoudle conn : conns){
			new DBWorker(statuLatch,conn).run();
		}
		try {
			statuLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(!ConvergeTransmitConstants.queue.isEmpty()){
			client.start();
		}
	}
}
