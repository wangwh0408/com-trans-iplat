package com.allscore.trans.iplat.control;



import org.apache.log4j.Logger;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.vo.CoreTransReponseBean;
import com.allscore.trans.iplat.vo.TransObj;



public abstract class AbstractAnsyHandle implements IAnsyRetMsg {

	private static final Logger logger = Logger.getLogger(AbstractAnsyHandle.class);
	
	

	@Override
	public CommonRetInfo getAnysyRetMsg(String orderId) throws TransException {
		try
		{
		if(this.currHashMap != null && this.currHashMap.get(orderId) != null)
		{
		return (CommonRetInfo)this.currHashMap.get(orderId);
		}
		else
		{
			return null;
		}
		}catch(Exception e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			
			return null;
		}
		
		
	}


	@Override
	public  void putAnsyRetMsg(String orderId,CommonRetInfo retCode) throws TransException {
		try
		{
			if(this.currHashMap != null)
			{
			this.currHashMap.put(orderId, retCode);
			}
			
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}

	}
	
	


	@Override
	public void removeAnsyRetMsg(String orderId) throws TransException {
		try
		{
		if(this.currHashMap != null && this.currHashMap.get(orderId) != null)
		{
		this.currHashMap.remove(orderId);
		}
		
		}catch(Exception e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			
			
		}
		
	}
	

	public abstract  CommonRetInfo returnHandleMsg(TransObj transObj)
			throws TransException ;


	@Override
	public void putCoreCallbackMsg(String coreSessionId, CoreTransReponseBean repInfo) throws TransException
	{
		try
		{
			if(this.coreResultHashMap != null)
			{
			this.coreResultHashMap.put(coreSessionId, repInfo);
			}
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
	}


	@Override
	public CoreTransReponseBean getCoreCallbackMsg(String coreSessionId) throws TransException
	{
		try
		{
		if(this.coreResultHashMap != null && this.coreResultHashMap.get(coreSessionId) != null)
		{
		return (CoreTransReponseBean)this.coreResultHashMap.get(coreSessionId);
		}
		else
		{
			return null;
		}
		}catch(Exception e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			
			return null;
		}
	}


	@Override
	public void removeCoreCallbackMsg(String coreSessionId) throws TransException
	{
		try
		{
		if(this.coreResultHashMap != null && this.coreResultHashMap.get(coreSessionId) != null)
		{
		this.coreResultHashMap.remove(coreSessionId);
		}
		
		}catch(Exception e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			
			
		}
		
	}

	@Override
	public Object getAnsyLock(String transSessionId) throws TransException
	{
		try
		{
		if(this.lockHashMap != null && this.lockHashMap.get(transSessionId) != null)
		{
		return this.lockHashMap.get(transSessionId);
		}
		else
		{
			logger.error("============lockObject is null===============");
			return null;
		}
		}catch(Exception e)
		{
			if(logger.isDebugEnabled())
			{
				e.printStackTrace();
			}
			
			return null;
		}
	}


	@Override
	public void putAnsyLock(String transSessionId, Object lock) throws TransException
	{
		try
		{
			if(this.lockHashMap != null)
			{
			this.lockHashMap.put(transSessionId, lock);
			}
		}catch(Exception e)
		{
			logger.error(e.getMessage());
		}
		
	}
	
	
	
}
