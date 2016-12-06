package com.jp.db;

public abstract class DBMoudle {

	protected DBMoudle(String DB_DRIVER, String DB_URL, String USER,
			String PASSWORD, String minConnections, String maxConnections,
			String JNDI_DATASOURCE_NAME,String tableName) {

		this.DB_DRIVER = DB_DRIVER;
		this.DB_URL = DB_URL;
		this.USER = USER;
		this.PASSWORD = PASSWORD;
		this.minConnections = minConnections;
		this.maxConnections = maxConnections;
		this.JNDI_DATASOURCE_NAME = JNDI_DATASOURCE_NAME;
		this.tableName = tableName;
	}

	protected String pool = null;
	protected String DB_DRIVER;
	protected String DB_URL;
	protected String USER;
	protected String PASSWORD;
	protected String minConnections;
	protected String maxConnections;
	protected String JNDI_DATASOURCE_NAME;
	public String tableName;
	
}
