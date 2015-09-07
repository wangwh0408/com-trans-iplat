package com.allscore.trans.iplat.strategy;

import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.vo.TransObj;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-21 下午12:32:43
 */
public interface TransHandleStrategy
{
	
	public Object handleProcess(TransObj transObj)throws TransException;

}
