package com.allscore.trans.iplat.proxy.impl;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.domain.header.req.CommonReqInfo;
import com.allscore.trans.iplat.control.impl.AnsyResultHandle;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.proxy.TransAction;
import com.allscore.trans.iplat.strategy.HandleStrategyProvider;
import com.allscore.trans.iplat.strategy.TransHandleStrategy;
import com.allscore.trans.iplat.util.DateUtil;
import com.allscore.trans.iplat.vo.TransObj;





/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-19 下午7:59:25
 */
public class FinaSubjectTransAction implements TransAction
{
	private static final Logger logger = Logger.getLogger(FinaSubjectTransAction.class);

	private static AtomicLong atoLong = new AtomicLong(1);

	@Autowired
	private HandleStrategyProvider handleStrategyProvider;
	
	@Autowired
	private AnsyResultHandle ansyResultHandler;
	@Override
	public CommonRetInfo invokeTrans(CommonReqInfo reqPara)
	{
		if(logger.isInfoEnabled())
		{
			logger.info("==================invokeTrans(CommonReqInfo reqPara) --start=====================");
			logger.info("==================invokeTrans(CommonReqInfo reqPara) "+reqPara+"====================");
		}
		CommonRetInfo commRetInfo  = null;
//		commRetInfo.setRetCode(ConstantUtil.RetInfo.OPERATION_COMPLETED.getValue());
//		commRetInfo.setRetMsg(ConstantUtil.RetInfo.OPERATION_COMPLETED.getDesc());
		TransObj transObj = new TransObj();
		transObj.setTransCode(reqPara.getTransCode());
		transObj.setTransObj(reqPara);
		Object mutexObj = new Object();
		transObj.setAnsyLock(mutexObj);
		String transSessionId =this.getQueryId();
		transObj.setTranSessionId(transSessionId);
		ansyResultHandler.putAnsyLock(transSessionId, mutexObj);
		try
		{
			TransHandleStrategy transHandleStrategy =handleStrategyProvider.finaTransStrategy();
			commRetInfo = (CommonRetInfo)transHandleStrategy.handleProcess(transObj);	

		}catch(TransException ex)
		{
			logger.error(ex.getErrorCode()+" "+ex.getErrorMsg()+" ["+ex.getMessage()+"]");
			commRetInfo  =new CommonRetInfo();
			commRetInfo.setReturnCode(ex.getErrorCode());
			commRetInfo.setReturnDesc(ex.getErrorMsg());
		}
		
		logger.info("==================invokeTrans(CommonReqInfo reqPara) --end=====================");
		return commRetInfo;

	}
	private String getQueryId()
	{
		String dateStr =DateUtil.format(new Date(), "yyyyMMddHHmmss");
		return dateStr+String.valueOf(atoLong.getAndIncrement());
	}

}
