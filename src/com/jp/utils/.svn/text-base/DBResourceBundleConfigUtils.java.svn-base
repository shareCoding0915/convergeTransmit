package com.jp.utils;

import java.util.ResourceBundle;

import com.jp.db.MysqlDBMoudle;
import com.jp.db.OracleDBMoudle;
import com.jp.db.SQLServerDBMoudle;

public class DBResourceBundleConfigUtils {
	
	public static SQLServerDBMoudle[] sqlServerConn = null;
	public static MysqlDBMoudle[] mysqlConn = null;
	public static OracleDBMoudle[] oracleConn = null;
	
	/**
	 * SQLServer
	 */
	public static SQLServerDBMoudle[] getSqlServerArrayNewInstance(String name){
		ResourceBundle rb= ResourceBundle.getBundle(name);
		String SQLServer_DB_DRIVER = rb.getString("jdbc-0.proxool.driver-class");
        String SQLServer_maxConnections = rb.getString("jdbc-0.proxool.maximum-connection-count");
        String SQLServer_minConnections = rb.getString("jdbc-0.proxool.minimum-connection-count");
        
        String SQLServer_JNDI_DATASOURCE_NAME = rb.getString("jdbc-0.proxool.alias");
        String SQLServer_DB_URL = rb.getString("jdbc-0.proxool.driver-url");
        String SQLServer_USER = rb.getString("jdbc-0.user");
        String SQLServer_PASSWORD = rb.getString("jdbc-0.password");
        String SQLServer_TABLENAME=rb.getString("jdbc-0.proxool.table_name");
        
        String[] arrSource_name=SQLServer_JNDI_DATASOURCE_NAME.trim().split("\\|");
        String[] arrUrl=SQLServer_DB_URL.trim().split("\\|");
        String[] arrUse=SQLServer_USER.trim().split("\\|");
        String[] passwd=SQLServer_PASSWORD.trim().split("\\|");
        String[] tableName=SQLServer_TABLENAME.trim().split("\\|");
        if(arrSource_name.length!=arrUrl.length||arrSource_name.length!=arrUse.length
        		||arrSource_name.length!=passwd.length){
        	throw new RuntimeException("ERROR: ["+name+"-properties is  missing some Key-Config ! ]");
        }else{
        	sqlServerConn = new SQLServerDBMoudle[arrSource_name.length];
	        for(int i=0;i<arrSource_name.length;i++){
	        	sqlServerConn[i]=new SQLServerDBMoudle(SQLServer_DB_DRIVER,arrUrl[i],arrUse[i],passwd[i],SQLServer_minConnections
	        			,SQLServer_maxConnections,arrSource_name[i],tableName[i]);
	        }
	        
        }
        return sqlServerConn;
	}
	
	public static void main(String[] args) {
		getSqlServerArrayNewInstance("oracle");
	}
	
	/**
	 * Mysql
	 */
	public static MysqlDBMoudle[] getMysqlArrayNewInstance(String name){
		ResourceBundle rb= ResourceBundle.getBundle(name);
		String Mysql_DB_DRIVER = rb.getString("jdbc-0.proxool.driver-class");
        String Mysql_maxConnections = rb.getString("jdbc-0.proxool.maximum-connection-count");
        String Mysql_minConnections = rb.getString("jdbc-0.proxool.minimum-connection-count");
        
        String Mysql_JNDI_DATASOURCE_NAME = rb.getString("jdbc-0.proxool.alias");
        String Mysql_DB_URL = rb.getString("jdbc-0.proxool.driver-url");
        String Mysql_USER = rb.getString("jdbc-0.user");
        String Mysql_PASSWORD = rb.getString("jdbc-0.password");
        String Mysql_TABLENAME=rb.getString("jdbc-0.proxool.table_name");
        
        String[] arrSource_name=Mysql_JNDI_DATASOURCE_NAME.trim().split("\\|");
        String[] arrUrl=Mysql_DB_URL.trim().split("\\|");
        String[] arrUse=Mysql_USER.trim().split("\\|");
        String[] passwd=Mysql_PASSWORD.trim().split("\\|");
        String[] tableName=Mysql_TABLENAME.trim().split("\\|");
        if(arrSource_name.length!=arrUrl.length||arrSource_name.length!=arrUse.length
        		||arrSource_name.length!=passwd.length){
        	throw new RuntimeException("ERROR: ["+name+"-properties is  missing some Key-Config ! ]");
        }else{
        	mysqlConn = new MysqlDBMoudle[arrSource_name.length];
	        for(int i=0;i<arrSource_name.length;i++){
	        	mysqlConn[i]=new MysqlDBMoudle(Mysql_DB_DRIVER,arrUrl[i],arrUse[i],passwd[i],Mysql_minConnections
	        			,Mysql_maxConnections,arrSource_name[i],tableName[i]);
	        }
	        
        }
        return mysqlConn;
	}
	
	/**
	 * Oracle
	 */
	public static OracleDBMoudle[] getOracleArrayNewInstance(String name){
		ResourceBundle rb= ResourceBundle.getBundle(name);
		String DB_DRIVER = rb.getString("jdbc-0.proxool.driver-class");
        String maxConnections = rb.getString("jdbc-0.proxool.maximum-connection-count");
        String minConnections = rb.getString("jdbc-0.proxool.minimum-connection-count");
        
        String JNDI_DATASOURCE_NAME = rb.getString("jdbc-0.proxool.alias");
        String DB_URL = rb.getString("jdbc-0.proxool.driver-url");
        String USER = rb.getString("jdbc-0.user");
        String PASSWORD = rb.getString("jdbc-0.password");
        String TABLENAME=rb.getString("jdbc-0.proxool.table_name");
        
        String[] arrSource_name=JNDI_DATASOURCE_NAME.trim().split("\\|");
        String[] arrUrl=DB_URL.trim().split("\\|");
        String[] arrUse=USER.trim().split("\\|");
        String[] passwd=PASSWORD.trim().split("\\|");
        String[] tableName=TABLENAME.trim().split("\\|");
        if(arrSource_name.length!=arrUrl.length||arrSource_name.length!=arrUse.length
        		||arrSource_name.length!=passwd.length){
        	throw new RuntimeException("ERROR: ["+name+"-properties is  missing some Key-Config ! ]");
        }else{
        	oracleConn = new OracleDBMoudle[arrSource_name.length];
	        for(int i=0;i<arrSource_name.length;i++){
	        	oracleConn[i]=new OracleDBMoudle(DB_DRIVER,arrUrl[i],arrUse[i],passwd[i],minConnections
	        			,maxConnections,arrSource_name[i],tableName[i]);
	        }
	        
        }
        return oracleConn;
	}
}
