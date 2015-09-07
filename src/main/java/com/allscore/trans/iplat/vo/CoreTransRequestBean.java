package com.allscore.trans.iplat.vo;

import java.io.Serializable;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-28 下午2:49:49
 */
public class CoreTransRequestBean implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4022067827541577265L;

	private String coreSessionid;

	public String getCoreSessionid()
	{
		return coreSessionid;
	}

	public void setCoreSessionid(String coreSessionid)
	{
		this.coreSessionid = coreSessionid;
	}
	
	
}
