package com.allscore.trans.iplat.util;

public class ConstantUtil {
	
	public static enum RetInfo {
		OPERATION_COMPLETED("0000","操作完成"),
		DATA_NOEXITS("1066","记录不存在"),
		OPERATION_FAIL("9999","操作失败"),
		REQUIRED_PARAMETER_MISSING("1001","缺少必选的参数"),
		ILLEGAL_ARGUMENT("1002","非法参数"),
		SERVICE_UNAVAILABLE("1003","服务不可用"),
		SERVICE_BUSY("1004","服务繁忙"),
		PERSIST_ERROR("1005","数据层操作异常"),
		NO_PERMISSION("1006","无此权限"),
		CHANNEL_ABNORMAL("1007","渠道异常"),
		TIMEOUT_UNTREATED("1008","超时未处理"),
		LACK_OF_BALANCE("1009","余额不足"),
		PASSWORD_ERROR("1010","密码错误"),
		ORDER_NOT_EXIST("1011","订单不存在"),
		REPEAT_ORDER_NO("1012","订单号重复"),
		ACCOUNT_NOT_EXIST("1013","账号不存在"),
		ACCOUNT_STATE_ABNORMAL("1014","账号状态异常"),
		ACCOUNT_EXIST("1015","账号已存在"),
		REMOTE_ERROR("1016","远程调用异常"),
		BEYOND_ATTEMPTS("1017","超出尝试次数"),
		UNAUTHORIZED_ACCESS("1018","非法访问"),
		SIGN_AGRMT_NOT_EXIST("1019","原签约信息不存在"),
		CAPI_ACCOUNT_NOT_EXIST("1020","未找到用户资金账户"),
		TRANS_ERROR("2002","业务交易处理类不存在"),
		TRANS_RESULT_ERROR("2003","交易结果处理异常"),
		TRANS_QUERYRESULT_ERROR("2004","获取交易处理结果处理异常"),
		CANCEL_FAIL("1110","撤单失败"),
		UNKNOWN_ERROR("9999","未知错误"),
		FTP_ACCESS_FAIL("1021","FTP服务访问失败"),
		
		QUERYBANK_FAIL("2001","获取支持银行信息处理异常"),
		
		QUERYSIGNEDBANKCARD_FAIL("3001","获取已签约银行卡信息处理异常"),
		QUICKPAY_FAIL("4001","支付处理异常"),
		SENDVAILDNUMBER_FAIL("5001","发送验证码处理异常"),
		
		
		VERIFY_NOEXISTS("4002","验证码已失效"),
		VERIFY_FAIL("4003","验证码不正确"),
		VERIFY_TIMEOUT("4004","验证码已超时失效"),
		MOBILE_FAIL("5002","对不起,该手机号不是您预留的手机号"),
		
		MERCHANTID_VAILD("4004","无效的商户号"),
		
		EXPORTFILES_FAIL("8001","导出对帐文件失败"),
		EXPORTFILES_SUCC("8002","导出队长文件成功");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		RetInfo(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
   
	public static enum Trans{
		TRANS_applyRefundHandle("T101","退款处理"),
		TRANS_ifApplyRefundHandle("T102","退款处理成功"),
		TRANS_onlineOrderHandle("T103","在线订单处理"),		
		TRANS_getAllSeatHandle("T100","票据基础信息"),
		TRANS_queryAllCategoryHandle("T104","查询所有票品目录信息"),
		TRANS_queryAllFconfigHandle("T105","查询所有分站信息"),
		TRANS_queryOrderHandle("T106","查询单笔订单信息"),
		TRANS_queryProductHandle("T107","查询票品信息"),
		TRANS_queryProductPriceHandle("T108","查询票品价格信息"),
		TRANS_queryProductStatusHandle("T109","查询票品状态信息"),
		TRANS_queryRecommendProductHandle("T110","查询推荐票品信息"),
		TRANS_onlineBookingHandle("T112","订单下定"),
		TRANS_queryVenueInfoHandle("T111","查询场馆信息");
		
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		Trans(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	
	public static enum Banks{
		BANK_CMB("CMB","招商银行"),
		BANK_CCB("CCB","建设银行");
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		Banks(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
		
	}
	
	public static enum PayOrderState{
		NOHANDLE("1","待处理"),
		PAY_SUCC("2","消费成功"),
		BACK_SUCC("3","退货"),
		PAY_FAIL("4","消费失败");
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		PayOrderState(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	
		
	}
	
	
	public static enum FeeType{
		FEE_RATIO("1","百分比"),
		FEE_FIXED("2","固定方式");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		FeeType(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
		
	}
	
	
	public static enum IsBackAmt{
		NO("0","不可退货"),
		YES("1","可退货");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		IsBackAmt(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	
	public static enum IsBackFee{
		NO("0","不可退"),
		YES("1","可退");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		IsBackFee(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum BackOrderState{
		NOHANDLE("1","待处理"),
		BACK_SUCC("2","退货成功"),
		BACK_FAIL("3","退货失败");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		BackOrderState(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum MerchantState{
		ACTIVATE("0","正常"),
		FORRBIDEN("1","下线");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		MerchantState(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum AMTRELATE{
		GREATERANDEQUAL(">=","大于等于"),
		EQUAL("=","等于"),
		LESSANDEQUAL("<=","小于等于");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		AMTRELATE(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
		
	}
	
	public static enum TransActionType{
		FINATRANS("1","金融交易"),
		NOFINATRANS("2","非金融交易");
	
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		TransActionType(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
		
	}

	public static final String PAYORDERCOUNT ="0601";
	public static final String OPERCOMPLETED ="0000";
	public static final String PAYORDERLIST ="0602";
	public static final String REFUNDCOUNT ="0604";
	public static final String REFUNDLIST = "0605";
	public static final String REFUNDFAIL ="0606";
	public static final String REFUNDNOEXIST = "0607";
	public static final String OPERCOMPLETFAIL="108";
	public static final String THIRDTICKETFAIL ="9991";
	public static final String UNIONIDENCODE = "0900";
	public static final String UNIONCODE ="0901";
	
	public static final String XMLHEADERINFO="<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	public static enum EffectStatus{
		online("0","下架"),
		offline("1","上架");
	
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		EffectStatus(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
		
	}
	
	public static enum ConsumeStatus{
		retSale("0","热卖"),
		booking("1","预售"),
		noVaild("2","无效"),
		waitHandle("3","待定"),
		finishedSale("4","售完"),
		delayDate("5","延期"),
		cancel("6","取消"),
		endSale("7","已结束");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		ConsumeStatus(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum PriceState{
		saling("1","可售"),
		saled("2","售完"),
		hidden("3","隐藏"),
		booking("4","预定"),
		noVaild("5","无效");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		PriceState(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum PriceType{
		normal("1","正常票"),
		sets("2","套票"),
		special("3","特殊");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		PriceType(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
			
	}
	
	
	public static enum AddressType{
		company("1","公司"),
		family("2","家庭"),
		school("3","学校");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		AddressType(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	
	public static enum SendTimeType{
		noLimited("1","时间不限"),
		workday("2","工作日"),
		holiday("3","节假日");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		SendTimeType(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum InvoiceType{
		person("0","个人"),
		union("1","单位");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		InvoiceType(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum DispatchWay{
		quickSend("1","快递配送"),
		homeSend("2","送货上门"),
		ericSend("4","电子票");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		DispatchWay(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	
	public static enum PayState{
		noPay("0","未支付"),
		noFullPay("1","部分支付"),
		payed("2","已支付"),
		refunded("3","已退款"),
		refundwaited("4","等待退款");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		PayState(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum OrderState{
		noExam("1","未审核"),
		Examed("2","已审核"),
		dispatchTicking("3","配票中"),
		sending("4","送货中"),
		sended("5","已发货"),
		finished("6","已完成"),
		noVaild("7","无效"),
		returned("8","已退票"),
		handled("9","已处理"),
		noSeat("12","无票"),
		returning("13","退票中"),
		refunding("15","退款中"),
		refundExam("16","退款审核"),
		refundFinish("17","完成退款"),
		returnBacking("18","退票,退款中"),
		returnedBacking("19","已退票，退款中"),
		returnedRefundExamed("20","已退票，退款已审核"),
		returnBacked("21","已退票，已退款");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		OrderState(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
		
	}
	
	
	public static enum  HandleWay{
		    single("1","单笔"),
		    group("2","团购");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		HandleWay(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum  IfPay{
	    yes("1","有退款"),
	    no("2","无退款");
	
	private final String value;
	private final String desc;
	public String getValue() {
		return value;
	}
	
	public String getDesc() {
		return desc;
	}

	IfPay(String value,String desc) {
		this.value = value;
		this.desc= desc;
	}
	

}
	
	public static enum YongleAction{
		onlineOrder("onlineOrder ","onlineOrder"),
		findProductInfo("findProductInfo","findProductInfo"),
		findProductpriceInfo("findProductpriceInfo","findProductpriceInfo"),
		findOrderByUnionidAndOrderid("findOrderByUnionidAndOrderid","findOrderByUnionidAndOrderid"),
		getProduct("getProduct ","getProduct"),
		allCategory("allCategory","allCategory"),
		getRecommendProductInfo("getRecommendProductInfo","getRecommendProductInfo"),
		getAllFconfig("getAllFconfig","getAllFconfig"),
		applyRefund("applyRefund","applyRefund"),
		ifapplyRefund("ifapplyRefund","ifapplyRefund"),
		getVenueInfo("getVenueInfo","getVenueInfo");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		YongleAction(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
}
