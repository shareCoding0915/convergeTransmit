package com.jp.main.protocolFiter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import com.jp.main.service.ConvertHelper;


public class ProcessCodecFactory implements ProtocolCodecFactory {
	
	public ProcessCodecFactory(){
		
	}
	public ProcessCodecFactory(ConvertHelper helper){
		this.helper = helper;
	}

	private ConvertHelper helper;
	
	public ProtocolDecoder getDecoder(IoSession arg0) throws Exception {
		return new ProcessCodecDecoder();
	}

	public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
		return new ProcessCodecEncoder(helper);
	}

}
