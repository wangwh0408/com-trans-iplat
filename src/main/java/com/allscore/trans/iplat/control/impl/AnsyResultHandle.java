package com.allscore.trans.iplat.control.impl;

import org.apache.log4j.Logger;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.control.AbstractAnsyHandle;
import com.allscore.trans.iplat.event.EventListener;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.vo.TransObj;


public class AnsyResultHandle extends AbstractAnsyHandle {
	private static final Logger logger = Logger.getLogger(AnsyResultHandle.class);
	
	private static final long threadExecTime =10000;
	
	
	private EventListener eventListener;
	
	
	@Override
	public CommonRetInfo returnHandleMsg(TransObj transObj)
			throws TransException {
		
		long startTime =System.currentTimeMillis();
		logger.info("===================查询 交易处理结果数据中....  [transSessionId :"+transObj.getTranSessionId()+"]================");	
			if(this.getAnysyRetMsg(transObj.getTranSessionId()) == null)
			{
				if(this.getAnsyLock(transObj.getTranSessionId()) == null )
				{
					logger.error("mutex Object is null");
					
				}
				else
				{
					eventListener.setEventObj(this.getAnsyLock(transObj.getTranSessionId()));
					try
					{
						eventListener.objWait();
					} catch (Exception e)
					{
						
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				}
				
			}
			
			
			logger.info("===================查询 交易处理结果数据成功....  [transSessionId :"+transObj.getTranSessionId()+"]================");
		
		return (CommonRetInfo)this.getAnysyRetMsg(transObj.getTranSessionId());
	}


	public EventListener getEventListener()
	{
		return eventListener;
	}


	public void setEventListener(EventListener eventListener)
	{
		this.eventListener = eventListener;
	}


	
	
	

}
