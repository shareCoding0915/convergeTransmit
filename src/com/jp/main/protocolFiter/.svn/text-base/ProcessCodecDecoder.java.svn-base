package com.jp.main.protocolFiter;


import java.nio.ByteOrder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.jp.system.ApplicationLogging;

public class ProcessCodecDecoder extends ApplicationLogging implements ProtocolDecoder{

	
	public ProcessCodecDecoder(){
		
	}
	
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true); 
		long start = System.currentTimeMillis();
		while(in.hasRemaining()){  
			in.order(ByteOrder.LITTLE_ENDIAN);  
			buf.put(in.get());  
		}  
		buf.flip();  
		out.write(buf);  
		log.debug(">>>>>>>>>>>>> decode costTime-----------:"+(System.currentTimeMillis()-start)+" ms");
	}

	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		// TODO Auto-generated method stub
		
	}


}
