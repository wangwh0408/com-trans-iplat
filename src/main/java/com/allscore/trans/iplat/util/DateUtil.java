package com.allscore.trans.iplat.util;


import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * <p>Title: 日期时间工具类</p>
 * <p>Description: </p>
 * <pre>
 * </pre>
 * @作者 joyson
 * @创建时间 2011-08-17
 * @版本 1.00
 * @修改记录	
 * <pre>
 * 版本   修改人    修改时间    修改内容描述
 * ----------------------------------------
 * 
 * ----------------------------------------
 * </pre>
 */
public class DateUtil {
	
	/**
	 * 字符串转化为日期型
	 * @param strDate String yyyy-MM-dd
	 * @return Date
	 */
	public static Date toDateShort(String strDate) {
		String format;
		int indexOne=strDate.indexOf('/');
		int indexTwo=strDate.indexOf('-');
		int indexThree=strDate.indexOf('年');
		if(indexOne>0){
			format= "dd/MM/yyyy";
		}else if(indexTwo>0){
			format= "yyyy-MM-dd";
		}else if(indexThree>0){
			format= "yyyy年MM月dd日";
		}else {
			format= "yyyyMMdd";
		}
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}
	
	/**
	 * 比较两日期的相差天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {
		if (null == fDate || null == oDate) {
			return -1;
		}
		long day = 24L * 60L * 60L * 1000L;    
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / day);
	}

	/**
	 * 格式化date为字符串格式pattern
	 * @param date 日期
	 * @param pattern 格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null)
			return null;
		return (new SimpleDateFormat(pattern)).format(date);
	}

	/**
	 * 解析格式为pattern的date, 为 Date类型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parse(String date, String pattern) {
		if (date != null && date.length() == 0)
			return null;
		try {
			return (new SimpleDateFormat(pattern).parse(date));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换格式为srcPattern的日期字符串 为 的格式为descPattern的日期
	 * @param date 字符日期
	 * @param srcPattern 源格式
	 * @param descPattern 目标格式
	 * @return
	 */
	public static String format(String date, String srcPattern, String descPattern) {
		return format(parse(date, srcPattern), descPattern);
	}

	/**
	 * 转换格式为YYYYMMDD的日期字符串 为 的格式为YYYY-MM-DD的日期
	 * @param date 字符日期
	 * @return
	 */
	public static String formatDate(String date) {

		StringBuffer strDate = new StringBuffer();
		char[] str = date.toCharArray();
		for (int i = 0; i < str.length; i++) {
			strDate.append(str[i]);
			if (i == 3) {
				strDate.append("-");
			}
			if (i == 5) {
				strDate.append("-");
			}
		}
		return strDate.toString();
	}
	/**
	 * 得到系统时间yyyyMMddHHmmss
	 */
	public static String getCurrentDate() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd" + "HHmmss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}
	/**
	 * 得到系统时间
	 */
	public static String getDate() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " " + "HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}

	/**
	 * 得到系统时间
	 * "HH:mm:ss"
	 */
	public static String getTime() {
		SimpleDateFormat tempDate = new SimpleDateFormat("HH:mm:ss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}

	/**
	 * 得到系统时间
	 */
	public static String getTime(String t) {
		SimpleDateFormat tempDate = new SimpleDateFormat(t);
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}

	/**
	 * 得到系统时间
	 */
	public static String getDate(String str) {
		String datetime;
		if (str != null && !"".equals(str)) {
			SimpleDateFormat tempDate = new SimpleDateFormat(str);
			datetime = tempDate.format(new java.util.Date());
		} else {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd" + " " + "HH:mm:ss");
			datetime = tempDate.format(new java.util.Date());
		}

		return datetime;
	}
	/**
	 * 得到系统当前日期
	 * "yyyyMMdd"
	 */
	public static String getCurentDate() {
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}
	
	/**
	 * 得到系统当前时间
	 * "HHmmss"
	 */
	
	public static String getCurentTime() {
		SimpleDateFormat tempDate = new SimpleDateFormat("HHmmss");
		String datetime = tempDate.format(new java.util.Date());
		return datetime;
	}
	/**
	 * 得到当前时间的前一天
	 * @return
	 */
	public static String getBeginDate(){
		Date sdate=new Date();
		long myTime = (sdate.getTime()/1000)-60*60*24;
		sdate.setTime(myTime*1000);
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
		String datetime = tempDate.format(sdate);
		return datetime;
	}
	/**
	 * 得到当前时间的后一天
	 * @return
	 */
	public static String getEndDate(){
		Date sdate=new Date();
		long myTime = (sdate.getTime()/1000)+60*60*24;
		sdate.setTime(myTime*1000);
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyyMMdd");
		String datetime = tempDate.format(sdate);
		return datetime;
	}
}
