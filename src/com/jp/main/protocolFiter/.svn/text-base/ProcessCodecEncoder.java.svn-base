package com.jp.main.protocolFiter;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.jp.main.service.ConvertHelper;
import com.jp.system.ApplicationLogging;

public class ProcessCodecEncoder extends ApplicationLogging implements ProtocolEncoder{
	
	public ProcessCodecEncoder(){
		
	}
	public ProcessCodecEncoder(ConvertHelper helper){
		this.helper = helper;
	}
	
	private ConvertHelper helper;

	public void dispose(IoSession arg0) throws Exception {
		
	}

	public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
			throws Exception {
        IoBuffer buff = IoBuffer.allocate(1024).setAutoExpand(true);
        long start = System.currentTimeMillis();
        buff = helper.pack(buff, message );
        buff.flip();
        out.write(buff);
        log.debug(">>>>>>>>>>>>> encode costTime-----------:"+(System.currentTimeMillis()-start)+" ms");
	}

}
