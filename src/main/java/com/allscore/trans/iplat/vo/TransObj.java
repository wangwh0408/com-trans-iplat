package com.allscore.trans.iplat.vo;

import java.io.Serializable;

public class TransObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3948470482150992727L;
	
	
	private String transCode;   //交易码
	private Object transObj;   //交易业务bean
	private String tranSessionId; //交易会话Id
	
	private Object ansyLock;
	
	/**
	 * 服务过程数据
	 */
	private Object spiResultObj;
	
	//TODO
	public void accept()
	{
		
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public Object getTransObj() {
		return transObj;
	}
	public void setTransObj(Object transObj) {
		this.transObj = transObj;
	}
	public String getTranSessionId() {
		return tranSessionId;
	}
	public void setTranSessionId(String tranSessionId) {
		this.tranSessionId = tranSessionId;
	}
	public Object getAnsyLock() {
		return ansyLock;
	}
	public void setAnsyLock(Object ansyLock) {
		this.ansyLock = ansyLock;
	}
	public Object getSpiResultObj()
	{
		return spiResultObj;
	}
	public void setSpiResultObj(Object spiResultObj)
	{
		this.spiResultObj = spiResultObj;
	}
	
	

}
