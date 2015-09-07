package com.allscore.trans.iplat.proxy;

import com.allscore.trans.domain.header.rep.CommonRetInfo;
import com.allscore.trans.domain.header.req.CommonReqInfo;




/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2007 All Rights Reserved. com.allscore 版权所有</p>
 * <p>Company: com.allscore</p>
 * @author zjf
 * @version 1.0
 * @Date 2014-5-19 下午7:56:18
 */
public interface TransAction
{
   public CommonRetInfo invokeTrans(CommonReqInfo  reqPara );
   

}
