package com.allscore.trans.iplat.control.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.control.AbstractFinaTransHandler;
import com.allscore.trans.iplat.control.IOrderHandle;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.util.ConstantUtil;
import com.allscore.trans.iplat.vo.TransObj;

public class OrderHandlerCenter extends AbstractFinaTransHandler {
	
	private static final Logger logger = Logger.getLogger(OrderHandlerCenter.class);
	
	private static final long threadExecTime =30000;

	@Autowired
	private Map<String,IOrderHandle> bizTransObj;
	

	@Autowired
	private PooledExecutor threadPoolTaskExecutor;
	
	
	
	

	@Override
	public void beforeBizHandle(TransObj transObj) throws TransException {
		IOrderHandle orderHandle = bizTransObj.get(transObj.getTransCode());
		
		if(orderHandle == null)
		{
			throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObj.getTransCode()+"]");
		}
		
        try
        {
        	orderHandle.preHandle(transObj);
        }catch(TransException ex)
        {
        	throw ex;
        }
	}

	@Override
	public void afterBizHandle(TransObj transObj) throws TransException {
		logger.info("begin handle trans afterHanler "+"交易码: "+transObj.getTransCode());		
		final TransObj transObjPool = transObj;
		
        try
        {
        	threadPoolTaskExecutor.waitWhenBlocked();
        	threadPoolTaskExecutor.execute(new Runnable(){
				@Override
				public void run() {
					IOrderHandle orderHandle = bizTransObj.get(transObjPool.getTransCode());
					if(orderHandle == null)
					{
						throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObjPool.getTransCode()+"]");
					}
				   long startTime =System.currentTimeMillis();
					while(true){
					try
					{
					logger.info("============="+transObjPool.getTranSessionId()+" 该交易处理结果更新中！===============");
					orderHandle.transResultHanle(transObjPool);
					logger.info("============="+transObjPool.getTransCode()+" 该交易处理结果更新完毕！===============");
					break;
		        }catch(TransException ex)
		        {
		        	try
					{
						Thread.sleep(5000);
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
		        	logger.error(ConstantUtil.RetInfo.TRANS_RESULT_ERROR.getValue()+" "+ConstantUtil.RetInfo.TRANS_RESULT_ERROR.getDesc()+" [业务交易码:"+transObjPool.getTransCode()+"]"+"充值结果处理异常");
		        	long endTime = System.currentTimeMillis();
		        	if(endTime-startTime >= threadExecTime)
		        	{
		        		break;
		        	}
//		        	throw new QuickPayException(ConstantUtil.RetInfo.TRANS_ERROR.getValue,ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObjPool.getTransCode()+"]");

		        }
					try
					{
//						orderHandle.syncDataHandle(transObjPool);	
						logger.info("============清理缓存数据成功,业务交易码:"+transObjPool.getTransCode()+"===========");
					}catch(Exception ex)
					{
						if(logger.isDebugEnabled())
						{
							ex.printStackTrace();
						}
						logger.error(ConstantUtil.RetInfo.TRANS_ERROR.getValue()+" "+ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+", 清理缓存数据成功");
						throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc());
					}
				
				}
				}
        		
        	});
        	
        	}catch(InterruptedException e)
        	{
        		if(logger.isDebugEnabled())
        		{
        			e.printStackTrace();
        		}
        		logger.error(e.getMessage());
        	}
	}

	@Override
	public void bizHandle(TransObj transObj) throws TransException {
		
		final TransObj transObjThead = transObj;			
		try {
			threadPoolTaskExecutor.waitWhenBlocked();
			threadPoolTaskExecutor.execute(new Runnable(){
				@Override
				public void run() {
					logger.info("=========begin handle trans : "+transObjThead.getTransCode()+"===========");
					IOrderHandle orderHandle = bizTransObj.get(transObjThead.getTransCode());
			// TODO 线程池处理业务交易
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

	public Map<String, IOrderHandle> getBizTransObj() {
		return bizTransObj;
	}

	public void setBizTransObj(Map<String, IOrderHandle> bizTransObj) {
		this.bizTransObj = bizTransObj;
	}

	@Override
	public CommonRetInfo returnHandleMsg(TransObj transObj) throws TransException {
		CommonRetInfo retCode = null;
IOrderHandle orderHandle = bizTransObj.get(transObj.getTransCode());
		
		if(orderHandle == null)
		{
			throw new TransException(ConstantUtil.RetInfo.TRANS_ERROR.getValue(),ConstantUtil.RetInfo.TRANS_ERROR.getDesc()+" [业务交易码:"+transObj.getTransCode()+"]");
		}
		
        try
        {
        	AnsyResultHandle ansyResultHandler = new AnsyResultHandle();
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
    	
		
	}

	

	
}
