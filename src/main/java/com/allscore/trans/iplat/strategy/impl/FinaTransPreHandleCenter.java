package com.allscore.trans.iplat.strategy.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.control.AbstractFinaTransPreHandler;
import com.allscore.trans.iplat.control.IOrderHandle;
import com.allscore.trans.iplat.control.impl.AnsyResultHandle;
import com.allscore.trans.iplat.control.impl.OrderHandlerCenter;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.util.ConstantUtil;
import com.allscore.trans.iplat.vo.TransObj;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-8-10 下午4:16:01
 */
public class FinaTransPreHandleCenter extends AbstractFinaTransPreHandler
{

	private static final Logger logger = Logger.getLogger(FinaTransPreHandleCenter.class);
	
	private static final long threadExecTime =30000;

	@Autowired
	private Map<String,IOrderHandle> bizTransObj;
	

	@Autowired
	private PooledExecutor threadPoolTaskExecutor;
	
	@Autowired
	private AnsyResultHandle ansyResultHandler;
	@Override
	public void beforeBizHandle(TransObj transObj) throws TransException
	{
		logger.info("=========begin handle trans : "+transObj.getTransCode()+"===========");
IOrderHandle orderHandle = bizTransObj.get(transObj.getTransCode());
		
		if(orderHandle == null)
		{
			throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObj.getTransCode()+"]");
		}
		
		final TransObj transObjThead = transObj;	
        try
        {
        	
        	threadPoolTaskExecutor.waitWhenBlocked();
			threadPoolTaskExecutor.execute(new Runnable(){
				@Override
				public void run() {
					IOrderHandle orderHandle = bizTransObj.get(transObjThead.getTransCode());
			
			try
			{
				
				orderHandle.preHandle(transObjThead);
			}catch(TransException ex)
			{
				if(logger.isDebugEnabled())
				{
					ex.printStackTrace();
				}
				
				logger.error(ex.getErrorCode()+" "+ex.getErrorMsg()+" "+ex.getMessage());
				orderHandle.exceptionHandle(transObjThead, ex.getMessage());
				throw ex;
			}
			logger.info("=========end handle trans : "+transObjThead.getTransCode()+"===========");
			
				}
				
			});
        
        }catch(TransException ex)
        {
        	throw ex;
        } catch (InterruptedException e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			
		}
    	logger.info("=========end handle trans : "+transObj.getTransCode()+"===========");
	}

	@Override
	public CommonRetInfo returnHandleMsg(TransObj transObj) throws TransException
	{
		CommonRetInfo retCode = null;
		IOrderHandle orderHandle = bizTransObj.get(transObj.getTransCode());
				
				if(orderHandle == null)
				{
					throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObj.getTransCode()+"]");
				}
				
		        try
		        {
		        	
		        	retCode =orderHandle.returnHandleMsg(transObj,ansyResultHandler);
		        }catch(TransException ex)
		        {
		        	ex.printStackTrace();
		        	throw ex;
		        }
		        return retCode;
	}

	public Map<String, IOrderHandle> getBizTransObj()
	{
		return bizTransObj;
	}

	public void setBizTransObj(Map<String, IOrderHandle> bizTransObj)
	{
		this.bizTransObj = bizTransObj;
	}



}
