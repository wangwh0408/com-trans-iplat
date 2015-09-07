package com.allscore.trans.iplat.control;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.iplat.exception.TransException;
import com.allscore.trans.iplat.vo.TransObj;


public interface IOrderHandle{

	/**
	 * 订单业务处理接口
	 * @param transObj
	 * @throws Exception
	 */
	public void execHandle(TransObj transObj)throws TransException;


	/**
	 * 订单异常处理接口
	 * @param transObj
	 * @throws QuickPayException
	 */
	public void exceptionHandle(TransObj transObj,String msg)throws TransException;

	/**
	 * 订单业务预处理
	 * @param transObj
	 * @throws QuickPayException
	 */
	public void preHandle(TransObj transObj)throws TransException;

	/**
	 *  订单结果处理更新
	 * @param transObj
	 * @throws QuickPayException
	 */
	public void transResultHanle(TransObj transObj)throws TransException;


	/**
	 * 返回订单处理结果
	 * @param transObj
	 * @throws QuickPayException
	 */
	public CommonRetInfo returnHandleMsg(TransObj transObj, AbstractAnsyHandle ansyRetMsg)throws TransException;


	/**
	 * 同步订单数据(hashmap,database)
	 * @param transObj
	 * @throws QuickPayException
	 */
	public void syncDataHandle(TransObj transObj)throws TransException;



}
