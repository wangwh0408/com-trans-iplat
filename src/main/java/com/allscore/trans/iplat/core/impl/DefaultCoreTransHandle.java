package com.allscore.trans.iplat.core.impl;



import org.apache.log4j.Logger;

import com.allscore.trans.iplat.core.AbstracCoreTransHandle;
import com.allscore.trans.iplat.core.CoreTransRequest;


public class DefaultCoreTransHandle extends AbstracCoreTransHandle {
	
	private static final Logger logger = Logger.getLogger(DefaultCoreTransHandle.class);
	@Override
	public void handleRequest(CoreTransRequest transReq) {
		//TODO 增加是否走下个处理判断,需求未定
		try
		{
			transReq.execute();
			if(this.getNextCoreTransHandle() != null)
			{
			this.getNextCoreTransHandle().handleRequest(transReq);
			}
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}


	}

}
