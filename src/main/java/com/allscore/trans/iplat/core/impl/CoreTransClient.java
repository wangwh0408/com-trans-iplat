package com.allscore.trans.iplat.core.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.allscore.trans.iplat.core.AbstracCoreTransHandle;
import com.allscore.trans.iplat.core.CoreTransHandle;
import com.allscore.trans.iplat.core.TransFactory;


public class CoreTransClient implements Serializable {
	
	@Autowired
	private TransFactory transFactory;
	
	private CoreTransHandle firstCoreTransHandle;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3622156575119986816L;

	public void bulidTransChain()
	{
		
		List<CoreTransHandle> coreTransHandleList =transFactory.getCoreTransHandleList();
	
		AbstracCoreTransHandle transHandleObj =(AbstracCoreTransHandle)coreTransHandleList.get(0);
		firstCoreTransHandle =transHandleObj;
		for(int i=0;i<coreTransHandleList.size();i++)
		{
			
			if(i<coreTransHandleList.size() -1 && coreTransHandleList.get(i+1) != null)
			{
				
				transHandleObj.setNextCoreTransHandle(coreTransHandleList.get(i+1));
			}
			else
			{
				transHandleObj.setNextCoreTransHandle(null);
				break;
			}
						
			transHandleObj = (AbstracCoreTransHandle) transHandleObj.getNextCoreTransHandle();
			
		}
	}

	public CoreTransHandle getFirstCoreTransHandle() {
		return firstCoreTransHandle;
	}

	public void setFirstCoreTransHandle(CoreTransHandle firstCoreTransHandle) {
		this.firstCoreTransHandle = firstCoreTransHandle;
	}
	
	

}
