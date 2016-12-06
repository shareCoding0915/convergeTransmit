package com.jp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.logicalcobwebs.proxool.ProxoolFacade;


/**
 * mysql数据库连接管理
 * @author zh.h
 *
 */
public class MysqlDBMoudle extends DBMoudle{
	
    public MysqlDBMoudle(String DB_DRIVER, String DB_URL,String USER,
    		String PASSWORD,String minConnections, String maxConnections
    		,String JNDI_DATASOURCE_NAME,String tableName) {
		
    	super(DB_DRIVER, DB_URL, USER, PASSWORD,minConnections,maxConnections, JNDI_DATASOURCE_NAME,tableName);
	}

	public Connection getConnection() {
        if (pool != null) {
            try {
                Connection connect = DriverManager.getConnection(pool);
                try {
                    Statement statement = connect.createStatement();
                    statement.close();
                } catch (SQLException ex) {
                	pool = getPoolConnection();
                    connect = DriverManager.getConnection(pool);
                }
                return connect;
            } catch (Exception ex1) {
                ex1.printStackTrace();
                return null;
            }
        } else {
            try {
            	pool = getPoolConnection();
                return  DriverManager.getConnection(pool);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }

        }
    }

    private String getPoolConnection() {
        String alias = JNDI_DATASOURCE_NAME;
        try{
            ProxoolFacade.removeConnectionPool(alias);
            Class.forName("org.logicalcobwebs.proxool.ProxoolDriver");
        }catch(Exception ex){
        	ex.printStackTrace();
        }

        Properties info = new Properties();
        info.setProperty("proxool.minimum-connection-count", minConnections);
        info.setProperty("proxool.maximum-connection-count", maxConnections);
        info.setProperty("proxool.house-keeping-test-sql", "select 1 from dual");
        info.setProperty("proxool.house-keeping-sleep-time", "90000");
        info.setProperty("proxool.prototype-count", "4");
        info.setProperty("proxool.trace", "true");
        info.setProperty("proxool.verbose","true");
        info.setProperty("proxool.simultaneous-build-throttle", maxConnections);
        info.setProperty("user",USER);
        info.setProperty("password", PASSWORD);
        String driverClass = DB_DRIVER;//"oracle.jdbc.driver.OracleDriver";
        String driverUrl = DB_URL;//"jdbc:oracle:thin:@172.31.100.149:1521:SICSDB";
        String url = "proxool." + alias + ":" + driverClass + ":" + driverUrl;
        try{
            ProxoolFacade.registerConnectionPool(url, info);
        }catch(Exception ex){
        	ex.printStackTrace();
        }
        return url;
    }

}
