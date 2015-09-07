package com.allscore.trans.iplat.control;

import java.util.concurrent.ConcurrentHashMap;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.vo.CoreTransReponseBean;


public interface IAnsyRetMsg {
	
	

	public static final ConcurrentHashMap<String,CommonRetInfo> currHashMap = new ConcurrentHashMap<String,CommonRetInfo>(1024);
	
	public static final ConcurrentHashMap<String,CoreTransReponseBean> coreResultHashMap = new ConcurrentHashMap<String, CoreTransReponseBean>(1024);
	
	public static final ConcurrentHashMap<String, Object> lockHashMap = new ConcurrentHashMap<String, Object>();
	/**
	 * 同步存储返回结果消息
	 * @param transObj
	 * @throws QuickPayException
	 */
	public void putAnsyRetMsg(String orderId,CommonRetInfo retCode)throws TransException;
	
	/**
	 * 同步获取返回结果消息
	 * @param transObj
	 * @throws QuickPayException
	 */
	public CommonRetInfo getAnysyRetMsg(String orderId)throws TransException;
	
	/**
	 * 移出返回对象
	 * @param orederId
	 * @throws QuickPayException
	 */
	public void removeAnsyRetMsg(String orderId)throws TransException;
	
	
	public void putCoreCallbackMsg(String coreSessionId,CoreTransReponseBean repInfo)throws TransException;
	
	
	public CoreTransReponseBean getCoreCallbackMsg(String coreSessionId)throws TransException;
	
	public void removeCoreCallbackMsg(String coreSessionId)throws TransException;
	
	public Object getAnsyLock(String transSessionId)throws TransException;
	
	public void putAnsyLock(String transSessionId,Object lock)throws TransException;

}
