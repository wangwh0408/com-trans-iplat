package com.allscore.trans.iplat.control;




import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.concurrent.handle.ConcurrentServiceQueue;
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
 * @Date 2014-5-21 下午12:44:02
 */
public abstract class AbstractNoFinaTransHandler implements TransHandleStrategy
{

	private static final Logger logger =Logger.getLogger(AbstractNoFinaTransHandler.class);

	

	/**
	 * 业务交易前预处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract void beforeBizHandle(TransObj transObj)throws TransException;


	/**
	 * 业务交易处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract void bizHandle(TransObj transObj)throws TransException;
	@Override
	public Object handleProcess(TransObj transObj) throws TransException
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

		commRetInfo = this.returnHandleMsg(transObj);
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

	/**
	 * 返回订单处理结果
	 * @param transObj
	 * @throws QuickPayException
	 */
	public abstract CommonRetInfo returnHandleMsg(TransObj transObj)throws TransException;

}
