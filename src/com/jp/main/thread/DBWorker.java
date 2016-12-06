package com.jp.main.thread;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.jp.db.DBMoudle;
import com.jp.db.MysqlDBMoudle;
import com.jp.db.OracleDBMoudle;
import com.jp.db.SQLServerDBMoudle;
import com.jp.main.entity.CarTakeVo;
import com.jp.main.utils.DBUtils;
import com.jp.system.ApplicationLogging;
import com.jp.system.ConvergeTransmitConstants;

public class DBWorker extends ApplicationLogging implements Runnable {
	
	private DBMoudle dbMoudle;
	private CountDownLatch statuLatch;
	
	public DBWorker(CountDownLatch statuLatch,DBMoudle dbMoudle){
		this.dbMoudle = dbMoudle;
	}

	public void run() {
		List<CarTakeVo> list = null;
		try{
			if(dbMoudle instanceof SQLServerDBMoudle){
				list = DBUtils.queryAndBatchDelete(((SQLServerDBMoudle) dbMoudle).getConnection(),dbMoudle.tableName);
			}else if(dbMoudle instanceof MysqlDBMoudle){
				list = DBUtils.queryAndBatchDelete(((MysqlDBMoudle) dbMoudle).getConnection(),dbMoudle.tableName);
			}else{
				list = DBUtils.queryAndBatchDelete(((OracleDBMoudle) dbMoudle).getConnection(),dbMoudle.tableName);
			}
			
			if(null != list && list.size()>0){
				synchronized (ConvergeTransmitConstants.queue) {
					ConvergeTransmitConstants.queue.addAll(list);
				}
			}
		}catch (Exception e) {
			log.error("ERRORS happened in DBWorkers !"+e+":"+e.getMessage());
		}finally{
			statuLatch.countDown();
		}
	}

}
