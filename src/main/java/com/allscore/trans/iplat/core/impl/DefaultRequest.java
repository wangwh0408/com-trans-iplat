package com.allscore.trans.iplat.core.impl;

import org.apache.log4j.Logger;

import com.allscore.trans.iplat.core.CoreTransRequest;
import com.allscore.trans.iplat.vo.CoreTransRequestBean;
import com.allscore.trans.iplat.vo.TransObj;


public class DefaultRequest implements CoreTransRequest {

	private static final Logger logger = Logger.getLogger(DefaultRequest.class);
	private TransObj transObj;
	
//	@Autowired
//	private BankGateTestSpi bankGateTestSpi;
	@Override
	public Object getPropertyParams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void execute() {
		//TODO finance-common
		
		//TODO bank-chn
		logger.info("============begin exec core trans=====================");
		try
		{
		CoreTransRequestBean transReq = new CoreTransRequestBean();
		transReq.setCoreSessionid(transObj.getTranSessionId());
		transObj.setTransObj(transReq);
//		bankGateTestSpi.receiptTest(transReq);
		}catch(Exception ex)
		{
			logger.error(ex.getMessage());
		}
		logger.info("============end exec core trans=====================");
	}

	public TransObj getTransObj() {
		return transObj;
	}

	public void setTransObj(TransObj transObj) {
		this.transObj = transObj;
	}

	
}
