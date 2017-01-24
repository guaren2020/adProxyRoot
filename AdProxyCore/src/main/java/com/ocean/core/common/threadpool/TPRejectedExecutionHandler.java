package com.ocean.core.common.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TPRejectedExecutionHandler implements RejectedExecutionHandler  {
	protected final Logger log = LoggerFactory.getLogger(TPRejectedExecutionHandler.class);
	public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
		// TODO Auto-generated method stub
		log.error("the thread pool regject to accept the thread,pool full exception!");
	}

}
