package com.allscore.trans.iplat.proxy.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.domain.header.req.CommonReqInfo;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.proxy.TransAction;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-19 下午7:58:46
 */
public class ProxyTransAction implements TransAction
{
	private static final Logger logger = Logger.getLogger(ProxyTransAction.class);

    
    
    private Map<String,TransAction> subTransActionMap;
	@Override
	public CommonRetInfo invokeTrans(CommonReqInfo reqPara)throws TransException
	{
		CommonRetInfo commRetInfo =null;
		try
		{
			
		
		this.validTransInfo(reqPara);
		
		TransAction subTransAction = (TransAction)subTransActionMap.get(reqPara.getTransType());
		if(subTransAction == null)
		{
			//TODO
			logger.equals("");
			throw new TransException("no Exist","no Exist");
		}

		commRetInfo =subTransAction.invokeTrans(reqPara);
		}catch(Exception e)
		{
			//TODO
			e.printStackTrace();
			throw new TransException("","");
		}
        return commRetInfo;
	}
	
	
	public void validTransInfo(CommonReqInfo reqPara)throws TransException
	{
		
	}


	   public CommonRetInfo preTrans(CommonReqInfo reqPara)
	   {
		   CommonRetInfo commRetInfo =null;
			try
			{		
			TransAction subTransAction = (TransAction)subTransActionMap.get(reqPara.getTransType());
			if(subTransAction == null)
			{

				logger.equals("");
				throw new TransException("no Exist","no Exist");
			}

			commRetInfo =subTransAction.invokeTrans(reqPara);
			}catch(Exception e)
			{
				e.printStackTrace();
				throw new TransException("","");
			}
	        return commRetInfo;
	   }
	


	public Map<String, TransAction> getSubTransActionMap()
	{
		return subTransActionMap;
	}


	public void setSubTransActionMap(Map<String, TransAction> subTransActionMap)
	{
		this.subTransActionMap = subTransActionMap;
	}
	
	

}
