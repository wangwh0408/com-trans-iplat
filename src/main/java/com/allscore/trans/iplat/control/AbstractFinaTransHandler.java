package com.allscore.trans.iplat.control;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.concurrent.handle.ConcurrentServiceQueue;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.strategy.TransHandleStrategy;
import com.allscore.trans.iplat.util.ConstantUtil;
import com.allscore.trans.iplat.vo.TransObj;


public abstract class AbstractFinaTransHandler implements TransHandleStrategy{

	private static final Logger logger =Logger.getLogger(AbstractFinaTransHandler.class);




	/**
	 * 业务交易前预处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract void beforeBizHandle(TransObj transObj)throws TransException;

	/**
	 * 业务交易后处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract void afterBizHandle(TransObj transObj)throws TransException;

	/**
	 * 业务交易处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract void bizHandle(TransObj transObj)throws TransException;


	/**
	 * 返回订单处理结果
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract CommonRetInfo returnHandleMsg(TransObj transObj)throws TransException;


	public abstract void notifyHandle(TransObj transObj)throws TransException;

	/**
	 *   业务交易处理模板方法
	 * @param transObj
	 * @return
	 * @throws QuickPayException
	 */
	@Override
	public Object handleProcess(TransObj transObj)throws TransException
	{

		logger.info("=========begin handle trans : "+transObj.getTransCode()+"===========");
		CommonRetInfo commRetInfo = null;
		if(ConcurrentServiceQueue.currentTaskCount.getAndIncrement() >=concurrentServiceQueue.maxProcessCount)
		{
			commRetInfo.setReturnCode(ConstantUtil.OPERCOMPLETFAIL);
			commRetInfo.setReturnDesc("超过最大请求数，请稍候！");
		}
		else
		{
				try
				{
					this.beforeBizHandle( transObj);
					this.bizHandle( transObj);
					this.afterBizHandle( transObj);
					commRetInfo = this.returnHandleMsg(transObj);
//					this.notifyHandle(transObj);
				}catch(TransException e)
				{
					logger.error(e.getErrorCode()+" "+e.getErrorMsg()+" "+e.getMessage());
					commRetInfo = new CommonRetInfo();
					commRetInfo.setReturnCode(e.getErrorCode());
					commRetInfo.setReturnDesc(e.getErrorMsg());
				}
				ConcurrentServiceQueue.currentTaskCount.getAndDecrement();
				logger.info("=========end handle trans : "+transObj.getTransCode()+"===========");
		}
		
		return commRetInfo;
	}


}
