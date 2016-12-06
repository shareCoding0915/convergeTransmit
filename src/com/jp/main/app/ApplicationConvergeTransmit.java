package com.jp.main.app;

import java.util.Timer;

import com.jp.db.DBMoudle;
import com.jp.db.MysqlDBMoudle;
import com.jp.db.OracleDBMoudle;
import com.jp.db.SQLServerDBMoudle;
import com.jp.main.client.TcpShortClient;
import com.jp.main.service.impl.ConvertFixPackageLocalOrder;
import com.jp.main.service.impl.ConvertFixPackageNetOrder;
import com.jp.main.task.DBMoudleTimerTask;
import com.jp.main.task.LoopTimer;
import com.jp.main.utils.ArraysUtils;
import com.jp.system.ApplicationLogging;
import com.jp.system.ConvergeTransmitConstants;
import com.jp.utils.DBResourceBundleConfigUtils;

public class ApplicationConvergeTransmit extends ApplicationLogging {
	
	/*
	 * 必须对应工程目录下三个数据库配置文件名
	 */
	private final String SQlSERVER="sqLServer";
	private final String MYSQL="mysql";
	private final String ORACLE="oracle";
	
	private TcpShortClient client;
	private DBMoudle[] conns ;
	
	private Timer timer;
	
	private void config(){
		log.info(">>>>>>>>>>>>>>>>>>>>>>>>>> ApplicationConvergeTransmit config <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		if(ConvergeTransmitConstants.DbMoudle){
			SQLServerDBMoudle[] sqlServerMoudle = null;
			MysqlDBMoudle[] mysqlMoudle = null;
			OracleDBMoudle[] oracleMoudle = null;
			try{
				if(ConvergeTransmitConstants.SqlServer){
					sqlServerMoudle = DBResourceBundleConfigUtils.getSqlServerArrayNewInstance(SQlSERVER)
									== null ? new SQLServerDBMoudle[0] : DBResourceBundleConfigUtils.getSqlServerArrayNewInstance(SQlSERVER);
				}
				if(ConvergeTransmitConstants.Mysql){
					mysqlMoudle = DBResourceBundleConfigUtils.getMysqlArrayNewInstance(MYSQL)
								== null ? new MysqlDBMoudle[0] : DBResourceBundleConfigUtils.getMysqlArrayNewInstance(MYSQL);;
				}
				if(ConvergeTransmitConstants.Oracle){
					oracleMoudle = DBResourceBundleConfigUtils.getOracleArrayNewInstance(ORACLE)
								== null ? new OracleDBMoudle[0] : DBResourceBundleConfigUtils.getOracleArrayNewInstance(ORACLE);
				}
			}catch (Exception e) {
				log.error(" [DB getConnection ERROR! ] "+e+":"+e.getMessage());
			}
			conns =new DBMoudle[sqlServerMoudle.length + mysqlMoudle.length + oracleMoudle.length];
			conns=(DBMoudle[]) ArraysUtils.togetherArrays(conns,sqlServerMoudle,mysqlMoudle,oracleMoudle);
			
		}
		if(ConvergeTransmitConstants.TODABIGATE){
			if(!ConvergeTransmitConstants.ByteOrder){
				client = new TcpShortClient(new ConvertFixPackageLocalOrder());
			}else{
				client = new TcpShortClient(new ConvertFixPackageNetOrder());
			}
		}
	}
	
	public void start(){
		config();
		if(conns.length>0){
			timer = new LoopTimer();
			timer.schedule(new DBMoudleTimerTask(conns,client),0,ConvergeTransmitConstants.Interval);
		}
	}
}