package com.allscore.trans.iplat.strategy;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.strategy.impl.FinaTransHandleCenter;
import com.allscore.trans.iplat.strategy.impl.FinaTransPreHandleCenter;
import com.allscore.trans.iplat.strategy.impl.NoFinaTransHandleCenter;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-21 下午1:43:52
 */
public class HandleStrategyProvider implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2541299251665375849L;
	
	@Autowired
	private FinaTransHandleCenter finaHandleCenter;
	
	@Autowired
	private NoFinaTransHandleCenter noFinaHandleCenter;
	
	@Autowired
	private FinaTransPreHandleCenter  finaTransPreHandleCenter;
	
	private TransHandleStrategy transHandleStrategy;
	
	
	public TransHandleStrategy finaTransStrategy()throws TransException
	{
		transHandleStrategy = finaHandleCenter;
		
		return transHandleStrategy;
	}
	
	public TransHandleStrategy noFinaTransStrategy()throws TransException
	{
		transHandleStrategy = noFinaHandleCenter;
		
		return transHandleStrategy;
	} 
	
	public TransHandleStrategy finaPreTransStrategy()throws TransException
	{
		transHandleStrategy =finaTransPreHandleCenter;
		return transHandleStrategy;
	}

}
