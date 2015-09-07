package com.allscore.trans.iplat.strategy.impl;

import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.trans.iplat.concurrent.handle.ConcurrentServiceQueue;
import com.allscore.trans.iplat.control.AbstractFinaTransHandler;
import com.allscore.trans.iplat.control.IOrderHandle;
import com.allscore.trans.iplat.control.impl.AnsyResultHandle;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.strategy.TransHandleStrategy;
import com.allscore.trans.iplat.util.ConstantUtil;
import com.allscore.trans.iplat.vo.TransObj;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-21 下午12:38:13
 */
public class FinaTransHandleCenter extends AbstractFinaTransHandler implements TransHandleStrategy
{

	private static final Logger logger = Logger.getLogger(FinaTransHandleCenter.class);

	private static final long threadExecTime =30000;

	private static final long internalTime =1000;

	@Autowired
	private Map<String,IOrderHandle> bizTransObj;


	@Autowired
	private PooledExecutor threadPoolTaskExecutor;
	
 
	@Autowired
	private AnsyResultHandle ansyResultHandler;
	
	
	
//	@Autowired
//	private PlatNoticeService platNoticeService;

	@Override
	public void beforeBizHandle(TransObj transObj) throws TransException
	{
		logger.info("=========begin beforeBizHandle trans : "+transObj.getTransCode()+"===========");
        IOrderHandle orderHandle = bizTransObj.get(transObj.getTransCode());

		if(orderHandle == null)
		{
			logger.info("=========end beforeBizHandle trans : "+transObj.getTransCode()+"===========");
			throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObj.getTransCode()+"]");
		}

        try
        {
        	orderHandle.preHandle(transObj);
        }catch(TransException ex)
        {
        	logger.info("=========end beforeBizHandle trans : "+transObj.getTransCode()+"===========");
        	throw ex;
        }
    	logger.info("=========end beforeBizHandle trans : "+transObj.getTransCode()+"===========");
	}

	@Override
	public void afterBizHandle(TransObj transObj) throws TransException
	{
		logger.info("begin handle trans afterHanler "+"交易码: "+transObj.getTransCode());
		final TransObj transObjPool = transObj;
		
        try
        {
        	
        	ConcurrentServiceQueue.resultHandleExecutor.execute(new Runnable(){
				@Override
				public void run() {
					IOrderHandle orderHandle = bizTransObj.get(transObjPool.getTransCode());
					if(orderHandle == null)
					{
						throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObjPool.getTransCode()+"]");
					}


					logger.info("=============begind afterBizHandle handle "+transObjPool.getTranSessionId()+" 该交易处理结果更新中！===============");
					try
					{
					orderHandle.transResultHanle(transObjPool);
				}catch(TransException ex)
				{
					if(logger.isDebugEnabled())
					{
						ex.printStackTrace();
					}

					logger.error(ex.getErrorCode()+" "+ex.getErrorMsg()+" "+ex.getMessage());
					orderHandle.exceptionHandle(transObjPool, ex.getMessage());
//					throw ex;
				}
					logger.info("=============end afterBizHandle handle "+transObjPool.getTranSessionId()+" 该交易处理结果更新完毕！===============");


				}});

        	}catch(InterruptedException e)
        	{
        		if(logger.isDebugEnabled())
        		{
        			e.printStackTrace();
        		}
        		logger.error(e.getMessage());
        	}catch(RejectedExecutionException e)
    		{
    			if(logger.isDebugEnabled())
    			{
    				e.printStackTrace();
    			}
    			logger.error("已超过最大处理线程数"+e.getMessage());
    		}

	}

	@Override
	public void bizHandle(TransObj transObj) throws TransException
	{
		final TransObj transObjThead = transObj;
		
		try {
			
			ConcurrentServiceQueue.execHandleExecutor.execute(new Runnable(){
				@Override
				public void run() {
					logger.info("=========begin bizHandle trans : "+transObjThead.getTransCode()+"===========");
					IOrderHandle orderHandle = bizTransObj.get(transObjThead.getTransCode());

			try
			{

				orderHandle.execHandle(transObjThead);
			}catch(TransException ex)
			{
				if(logger.isDebugEnabled())
				{
					ex.printStackTrace();
				}

				logger.error(ex.getErrorCode()+" "+ex.getErrorMsg()+" "+ex.getMessage());
				orderHandle.exceptionHandle(transObjThead, ex.getMessage());
			}
			logger.info("=========end bizHandle trans : "+transObjThead.getTransCode()+"===========");

				}

			});
		} catch (InterruptedException e) {
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			logger.error(e.getMessage());
		}
		catch(RejectedExecutionException e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			logger.error("已超过最大处理线程数"+e.getMessage());
		}


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

	@Override
	public void notifyHandle(TransObj transObj) throws TransException
	{
		final TransObj transObjThead = transObj;
		try {
			threadPoolTaskExecutor.waitWhenBlocked();
			threadPoolTaskExecutor.execute(new Runnable(){
				@Override
				public void run() {
					logger.info("=========begin handle trans : "+transObjThead.getTransCode()+"===========");
					IOrderHandle orderHandle = bizTransObj.get(transObjThead.getTransCode());

			try
			{

//				CommonRetInfo commRetInfo = returnHandleMsg(transObjThead);
//				BizNoticeObj noticeObj = new BizNoticeObj();
//				noticeObj.setMerchantId(commRetInfo.getMerchId());
////				JsonObject
//				noticeObj.setContent("");
//				platNoticeService.notifyHandle(noticeObj);
				orderHandle.syncDataHandle(transObjThead);
			}catch(TransException ex)
			{
				if(logger.isDebugEnabled())
				{
					ex.printStackTrace();
				}

				logger.error(ex.getErrorCode()+" "+ex.getErrorMsg()+" "+ex.getMessage());
				throw ex;
			}
			logger.info("=========end handle trans : "+transObjThead.getTransCode()+"===========");

				}

			});
		} catch (InterruptedException e) {
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			logger.error(e.getMessage());
		}

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
