package com.ocean.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ocean.proxy.api.helper.ZookeeperRegister;
import com.ocean.proxy.server.ProxyServer;

/** * @author Alex & E-mail:569246607@qq.com
      @date   2017年1月16日 
      @version 1.0 
 */
public class ProxyServerListener implements ServletContextListener {
    private ProxyServer server=null;
	protected final Logger logger = LoggerFactory.getLogger(ProxyServerListener.class);
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		logger.info("ProxyServer shutdown......");
		if (null != server) {
			server.shutdownServer();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			logger.error("ProxyServer shutdown error!",e);
		}
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		server = new ProxyServer();
		logger.info("start the ProxyServer......");
		server.startServer();
		
		//register to zookeeper
/*		logger.info("register zookeeper node......");
		ZookeeperRegister regist=new ZookeeperRegister();
		regist.regist();*/
	}
}
