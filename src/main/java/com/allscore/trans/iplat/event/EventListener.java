package com.allscore.trans.iplat.event;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-29 下午2:33:26
 */
public class EventListener implements QueryEvent, ReturnEvent
{

	private Object eventObj;
	@Override
	public void objWait()throws Exception
	{
		synchronized (eventObj)
		{
			eventObj.wait();
		}

	}

	@Override
	public void objNotifyAll()throws Exception
	{
		synchronized (eventObj)
		{
			eventObj.notifyAll();
		}
		
	}

	public Object getEventObj()
	{
		return eventObj;
	}

	public void setEventObj(Object eventObj)
	{
		this.eventObj = eventObj;
	}

}
