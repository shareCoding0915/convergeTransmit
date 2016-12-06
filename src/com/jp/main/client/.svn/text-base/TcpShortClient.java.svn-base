package com.jp.main.client;

import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.jp.main.protocolFiter.ProcessCodecFactory;
import com.jp.main.service.ConvertHelper;
import com.jp.system.ApplicationLogging;
import com.jp.system.ConvergeTransmitConstants;

/**
 * TCP短连接
 * @author zh.h
 *
 */
public class TcpShortClient extends ApplicationLogging{
	
	private IoConnector connector = null;
	private ConnectFuture future = null;
	private IoSession session = null;
	
	private ConvertHelper helper;
	
	public TcpShortClient(){
		
	}
	public TcpShortClient(ConvertHelper helper){
		this.helper = helper;
	}
	
	public void config(){
		log.info(">>>>>>>>>>>>>>>>>>>>>>>TcpShortClient contextInitialized config<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
	    connector.getSessionConfig().setReadBufferSize(2048);
		connector.getFilterChain().addLast("logger", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ProcessCodecFactory(helper)));
		connector.setHandler(new IoHandlerAdapter(){
			@Override
			public void exceptionCaught(IoSession session, Throwable cause)throws Exception {
				super.exceptionCaught(session, cause);
				log.warn("TcpShortClient session Caught Exception! : "+cause+"->"+cause.getMessage());
				 session.close(true);
			}
			@Override
			public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
				session.close(true);
			}
			@Override
			public void sessionClosed(IoSession session) throws Exception {
				InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();
				log.info("close current_sessinId :=[ "+String.valueOf(session.getId())+" ]");
			    log.info("sessionClosed from :=[ "+remoteAddress.getAddress().getHostAddress()+" and port:="+remoteAddress.getPort()+" ]");
				
			}
			@Override
			public void sessionOpened(IoSession session) throws Exception {
				super.sessionOpened(session);
				InetSocketAddress remoteAddress = (InetSocketAddress)session.getRemoteAddress();
			    log.info("getSession from :=[ "+remoteAddress.getAddress().getHostAddress()+" and port:="+remoteAddress.getPort()+" ]");
			    log.info("current_sessinId :=[ "+String.valueOf(session.getId())+" ]");
			}
		});
	}
	
	public void start(){
		if(null != session && !session.isConnected()){
			session.getCloseFuture().awaitUninterruptibly(ConvergeTransmitConstants.SessionExit);
		}
		config();
		for (;;) {  
			try {  
				future = connector.connect(new InetSocketAddress(ConvergeTransmitConstants.IP,ConvergeTransmitConstants.PORT));  
				future.awaitUninterruptibly(); // 等待连接创建成功    
				session = future.getSession(); // 获取会话     
				log.info("connect server ==> [" + ConvergeTransmitConstants.IP + ":" + ConvergeTransmitConstants.PORT + "] sucessfully ! " + ",time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  
				break;  
			} catch (Exception e) {  
				log.error("connect server ==> [" + ConvergeTransmitConstants.IP + ":" + ConvergeTransmitConstants.PORT + "] failed ! " + ", Client will reconnect after 10s ! ,time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"\n" + e.getMessage(), e);  
				try {
					Thread.sleep(30000);// 连接失败后,重连间隔3s  
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}  
		}
		long start = System.currentTimeMillis();
		while(!ConvergeTransmitConstants.queue.isEmpty()){
			session.write(ConvergeTransmitConstants.queue.poll());
			ConvergeTransmitConstants.Count ++;
		}
		session.getCloseFuture().awaitUninterruptibly(ConvergeTransmitConstants.SessionExit);
		connector.dispose();
		log.debug("#########################datas in queue convert.......count=:"+ConvergeTransmitConstants.Count+",castTime:="+(System.currentTimeMillis()-start)+" ms");
	}
}
