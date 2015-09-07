package com.allscore.trans.iplat.concurrent.handle;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentServiceQueue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final BlockingQueue<Runnable> execHandleQueue = new SynchronousQueue<Runnable>();
	
	private static final BlockingQueue<Runnable> resultHandleQueue = new SynchronousQueue<Runnable>();
	
	public static final ThreadPoolExecutor execHandleExecutor = new ThreadPoolExecutor(100, 1000, 300, TimeUnit.SECONDS, execHandleQueue,Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
	
	public static final ThreadPoolExecutor resultHandleExecutor = new ThreadPoolExecutor(100, 1000, 300, TimeUnit.SECONDS, execHandleQueue,Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
	
	public static final AtomicInteger currentTaskCount = new AtomicInteger(0);
	
	public static final int maxProcessCount = 600;
	
	
}
