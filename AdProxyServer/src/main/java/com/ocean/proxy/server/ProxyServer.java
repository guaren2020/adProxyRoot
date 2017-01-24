package com.ocean.proxy.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.core.common.system.SystemContext;
import com.ocean.proxy.api.base.ProxyConstants;
import com.ocean.proxy.thrift.entity.ApiProxy;

public class ProxyServer {
	protected final Logger log = LoggerFactory.getLogger(ProxyServer.class);
	private static final int DEFAULT_MAX_WORKER = 8;
	private static final int DEFAULT_MIN_WORKER = 2;
	private static final int DEFAULT_IO_SELECTOR_COUNT = 2;
	private TServer server;
    public void startServer(){
 		new Thread()
        {
            public void run()
            {
            	
            	try {
            		int port=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_PORT,9091);
            		int timeOut=SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_TIME_OUT,100);
/*            		int queueBufferSize = SystemContext.getDynamicPropertyHandler().getInt(Constants.QUEUE_BUFFER_SIZE_KEY, 1000);
            		*/
            		
            		int maxWorker = SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_MAX_WORKER, DEFAULT_MAX_WORKER);
            		int minWorker = SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_MIN_WORKER, DEFAULT_MIN_WORKER);
            		int selectorCount = SystemContext.getDynamicPropertyHandler().getInt(ProxyConstants.THRIFT_IO_SELECTOR_COUNT, DEFAULT_IO_SELECTOR_COUNT);

            		
            		TProcessor tProcessor=new ApiProxy.Processor<ProxyInvoker>(new ProxyInvoker());
					TNonblockingServerSocket serverTransport=new TNonblockingServerSocket(port,timeOut);
					
	                //多线程半同步半异步  (IO thread blocking,worker thread nonblocking)
	                TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);  
	                tArgs.processor(tProcessor);  
	                tArgs.transportFactory(new TFramedTransport.Factory());  
	               
	                //二进制协议  
	                tArgs.protocolFactory(new TBinaryProtocol.Factory()); 
/*	    			tArgs.queueBufferSize(queueBufferSize);// 设置缓冲队列大小，用于削峰(突发的并发请求高峰)
*/			        tArgs.corePoolSize(minWorker);
                    tArgs.maximumPoolSize(maxWorker);
                    tArgs.selectorThreads(selectorCount);// IO线程 
	    			tArgs.preStartCoreThread(true);// 预先启动核心池
	                server=new TThreadedSelectorServer(tArgs);
	                server.serve();
	                
					
	                
				} catch (TTransportException e) {
					// TODO Auto-generated catch block
					log.error("rpc ProxyServer startup fired TTransportException!",e);
				} catch (Exception e) {
 					// TODO Auto-generated catch block
 					log.error("rpc ProxyServer startup fired Exception!",e);
 				}
            }
        }.start();
 	}
    public void shutdownServer(){
		try {
			if (null != server) {
				server.stop();
				log.info("rpc ProxyServer stop success");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
    }
}
