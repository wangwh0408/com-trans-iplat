package com.allscore.trans.iplat.control;

import org.apache.log4j.Logger;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.strategy.TransHandleStrategy;
import com.allscore.trans.iplat.vo.TransObj;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-8-10 下午4:10:43
 */
public abstract class AbstractFinaTransPreHandler implements TransHandleStrategy
{
	
	private static final Logger logger =Logger.getLogger(AbstractFinaTransPreHandler.class);
	
	/**
	 * 业务交易前预处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract void beforeBizHandle(TransObj transObj)throws TransException;

	@Override
	public Object handleProcess(TransObj transObj) throws TransException
	{
		logger.info("=========begin handle trans : "+transObj.getTransCode()+"===========");	
		CommonRetInfo commRetInfo = null;
		try
		{
			this.beforeBizHandle( transObj);
		
			
		
		commRetInfo = this.returnHandleMsg(transObj);
		}catch(TransException e)
		{
			commRetInfo = this.returnHandleMsg(transObj);
		
		}				
		logger.info("=========end handle trans : "+transObj.getTransCode()+"===========");
		return commRetInfo;
	}

	/**
	 * 返回订单处理结果
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract CommonRetInfo returnHandleMsg(TransObj transObj)throws TransException;

}
