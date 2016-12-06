package com.jp.main.service;


import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

public interface ConvertHelper {
	public byte[] pack(IoSession session,Object message);

	public IoBuffer pack(IoBuffer buff, Object message);
	
}
