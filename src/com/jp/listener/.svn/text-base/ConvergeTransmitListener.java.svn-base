package com.jp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.jp.main.app.ApplicationConvergeTransmit;
import com.jp.system.ApplicationLogging;

/**
 * 监听器
 * @author zh.h
 *
 */
public class ConvergeTransmitListener extends ApplicationLogging implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("ConvergeTransmitListener is Destroyed..............");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		log.info("ConvergeTransmitListener is contextInitialized..............");
		ApplicationConvergeTransmit application = new ApplicationConvergeTransmit();
		application.start();
	}
}
