package com.allscore.trans.iplat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static final String DATEFORMATLONG = "yyyy-MM-dd HH:mm:ss";
	public static final String DATEFORMATMEDIUM = "yyyy-MM-dd HH:mm";
	public static final String DATE_FORMAT_YMDH = "yyyyMMddHH";
	public static final String DATEFORMATSHORT = "yyyy-MM-dd";
	public static final String DATE_SHORT_YEARMONTH = "yyyy-MM";
	public static final String DATENUMBERFORMAT = "yyyyMMdd";
	public static final String DATEHOURNUMBERFORMAT = "yyyyMMddHH";
	public static final String DATE_FORMAT_HOUR = "H";
	public static final String Date_FORMAT_YMDHMS = "yyyyMMddHHmmss";
	public static final String TIME_FORMAT_HMS = "HH:mm:ss";

	private static Date date;

	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

	public DateUtils() {
	}

	public static Date getCurrentDate() {
		return new Date();
	}

	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	public static synchronized String getCurrentFormatDate(String formatDate) {
		date = getCurrentDate();
		simpleDateFormat.applyPattern(formatDate);
		return simpleDateFormat.format(date);
	}

	/**
	 * @return String YYYY-MM
	 */
	public static String getCurrentYearMonthDate() {
		return getCurrentFormatDate(DATE_SHORT_YEARMONTH);
	}

	public static String getCurrentFormatDateLong() {
		return getCurrentFormatDate(DATEFORMATLONG);
	}

	public static String getCurrentFormatDateMedium() {
		return getCurrentFormatDate(DATEFORMATMEDIUM);
	}

	public static String getCurrentFormatDateShort() {
		return getCurrentFormatDate(DATEFORMATSHORT);
	}

	private static synchronized String getDate2String(String format, Date date) {
		simpleDateFormat.applyPattern(format);
		return simpleDateFormat.format(date);
	}
	
	public static  String getCurTimeString() {
		simpleDateFormat.applyPattern(TIME_FORMAT_HMS);
		return simpleDateFormat.format(new Date());
	}
	
	public static String getDate2LongString(Date date) {
		return getDate2String(DATEFORMATLONG, date);
	}

	public static String getDate2MediumString(Date date) {
		return getDate2String(DATEFORMATMEDIUM, date);
	}

	public static String getDate2ShortString(Date date) {
		return getDate2String(DATEFORMATSHORT, date);
	}

	public static String getDate2HourString(Date date) {
		return getDate2String(DATE_FORMAT_HOUR, date);
	}

	public static String getDate2NumberString(Date date) {
		return getDate2String(DATENUMBERFORMAT, date);
	}

	public static String getDate2YmdhmsString(Date date){
		return getDate2String(Date_FORMAT_YMDHMS, date);
	}
	public static String getLong2LongString(long l) {
		date = getLong2Date(l);
		return getDate2LongString(date);
	}

	public static String getLong2MediumString(long l) {
		date = getLong2Date(l);
		return getDate2MediumString(date);
	}

	public static String getLong2ShortString(long l) {
		date = getLong2Date(l);
		return getDate2ShortString(date);
	}

	private static synchronized Date getString2Date(String format, String str) {
		simpleDateFormat.applyPattern(format);
		ParsePosition parseposition = new ParsePosition(0);
		return simpleDateFormat.parse(str, parseposition);
	}

	public static Date getString2LongDate(String str) {
		return getString2Date(DATEFORMATLONG, str);
	}

	public static Date getString2MediumDate(String str) {
		return getString2Date(DATEFORMATMEDIUM, str);
	}

	public static Date getString2YmdhDate(String str) {
		return getString2Date(DATE_FORMAT_YMDH, str);
	}

	public static Date getString2ShortDate(String str) {
		return getString2Date(DATEFORMATSHORT, str);
	}

	public static Date getString2YmdDate(String str) {
		return getString2Date(DATENUMBERFORMAT, str);
	}

	public static Date getString2YmdhmsDate(String str) {
		return getString2Date(Date_FORMAT_YMDHMS, str);
	}
	
	
	public static Date getEmptyDate() {
		return getString2ShortDate("1971-01-01");
	}

	public static String getEmptyDateString() {
		return "1971-01-01";
	}

	public static Date getLong2Date(long l) {
		return new Date(l);
	}

	public static int getOffMinutes(long l) {
		return getOffMinutes(l, getCurrentTimeMillis());
	}

	public static int getOffMinutes(long from, long to) {
		return (int) ((to - from) / 60000L);
	}

	public static int getYear() {
		return Calendar.getInstance().get(1);
	}

	public static int getMonth() {
		return Calendar.getInstance().get(2) + 1;
	}

	// DAY_OF_WEEK
	public static int getDayOfWeek() {
		int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 1) {
			dayOfWeek = 7;
		} else {
			dayOfWeek -= 1;
		}
		return dayOfWeek;
	}

	public static int getDay() {
		return Calendar.getInstance().get(5);
	}

	public static int getHour() {
		return Calendar.getInstance().get(11);
	}

	public static int getMinute() {
		return Calendar.getInstance().get(12);
	}

	public static int getSecond() {
		return Calendar.getInstance().get(13);
	}

	public static String add(int day) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.DATE, day);
		date = gregorianCalendar.getTime();
		return getDate2String(DATEFORMATSHORT, date);

	}

	public static String subtract(int day) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.DATE, -day);
		date = gregorianCalendar.getTime();
		return getDate2String(DATEFORMATSHORT, date);

	}
	
	public static String getBeforeDay(String dateStr) {
		simpleDateFormat.applyPattern(DATENUMBERFORMAT);
		try {
			Date _date = simpleDateFormat.parse(dateStr);
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(_date);
			gregorianCalendar.add(Calendar.DAY_OF_MONTH,-1);
			date = gregorianCalendar.getTime();
			return getDate2String(DATENUMBERFORMAT, date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		

	}

	public static String getlastMonth() {

		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		GregorianCalendar gc = new GregorianCalendar();
		gc.roll(Calendar.MONTH, false);
		return sdf.format(gc.getTime());

	}

	/**
	 * @return String
	 */
	public static String getCurrentLastMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return format.format(cal.getTime());
	}

	/**
	 * @return String[]
	 * 
	 */
	public static String[] getCurrentLastWeek() {
		String[] weeks = new String[2];
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -cal.get(Calendar.DAY_OF_WEEK));
		weeks[1] = format.format(cal.getTime());
		cal.add(Calendar.DAY_OF_YEAR, -6);
		weeks[0] = format.format(cal.getTime());
		return weeks;
	}

	/**
	 * if(?1?7始日?1?7结束日期)，return TURE;else return FALSE;
	 * 
	 * @return
	 */
	public static boolean isStartLessEndTime(String start, String end)
			throws ParseException {
		if (start.equals("") || end.equals(""))
			return false;
		Date startDate = DateFormat.getDateInstance().parse(start);
		Date endDate = DateFormat.getDateInstance().parse(end);
		if (startDate.compareTo(endDate) < 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * return first day in this week
	 * 
	 * @return Date
	 * @see Date
	 */
	public static Date getFirstDayOfThisWeek() {
		Date today = getToday();
		return getFirstDayInWeek(today);
	}

	public static Date getFirstDayOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1); // back on week
		return getFirstDayInWeek(cal.getTime());
	}

	public static Date getLastDayOfLastWeek() {
		Date firstdayofweek = getFirstDayOfLastWeek();
		Calendar cal = getCalendarByDate(firstdayofweek);
		cal.add(Calendar.DAY_OF_YEAR, 6);
		return cal.getTime();
	}

	public static Date getFirstDayInMonth(int year, int month) {
		month--; // Calendar month is base on 0; 0 is January
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(year, month, 1);
		return cal.getTime();
	}

	public static Date getLastDayInMonth(int year, int month) {
		Date firstDay = getFirstDayInMonth(year, month);
		Calendar firstCal = getCalendarByDate(firstDay);
		firstCal.add(Calendar.MONTH, 1); // forward one month
		firstCal.add(Calendar.DAY_OF_YEAR, -1); // back one day
		return firstCal.getTime();
	}

	public static Date getFirstDayOfThisMonth() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		month++;
		return getFirstDayInMonth(year, month);
	}

	public static Date getFirstDayOfLastMonth() {
		int year = getYearOfLastMonth();
		int month = getMonthOfLastMonth();
		return getFirstDayInMonth(year, month);
	}

	public static Date getLastDayOfLastMonth() {
		int year = getYearOfLastMonth();
		int month = getMonthOfLastMonth();
		return getLastDayInMonth(year, month);
	}

	private static int getYearOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return cal.get(Calendar.YEAR);
	}

	private static int getMonthOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		int month = cal.get(Calendar.MONTH);
		month++;
		return month;
	}

	public static int getThisMonth() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		month++;
		return month;
	}

	public static Date getFirstDayInYear(int year) {
		int month = 0;
		int day = 1;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}

	public static Date getLastDayInYear(int year) {
		int month = 11;
		int day = 31;
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}

	public static int getThisYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	public static int getLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		return cal.get(Calendar.YEAR);
	}

	/**
	 * return first day in week by date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayInWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int thisweek = cal.get(Calendar.WEEK_OF_YEAR);
		int lastweek = thisweek;
		Date thisday = null;
		while (lastweek == thisweek) {
			thisday = cal.getTime();
			cal.add(Calendar.DAY_OF_MONTH, -1); // back one day
			lastweek = cal.get(Calendar.WEEK_OF_YEAR);
		}
		return thisday;
	}

	/**
	 * return last day in week by date
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayInWeek(Date date) {
		Date firstdayofweek = getFirstDayInWeek(date);
		Calendar cal = getCalendarByDate(firstdayofweek);
		cal.add(Calendar.DAY_OF_YEAR, 6);
		return cal.getTime();
	}

	private static Calendar getCalendarByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static String getDateStr(Date date) {
		String pattern = "yyyy-MM-dd"; // should be locale
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String getMonthStr(Date date) {
		String pattern = "yyyy-MM"; // should be locale
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static Date getDateByStr(String dateStr) {
		String pattern = "yyyy-MM-dd"; // should be locale
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException("wrong date format, should be "
					+ pattern);
		}
		return date;
	}

	public static String addHour(int hour) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.HOUR, hour);
		date = gregorianCalendar.getTime();
		return getDate2String(DATEHOURNUMBERFORMAT, date);

	}

	public static Date addHour2Date(int hour) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.HOUR, hour);
		date = gregorianCalendar.getTime();
		return date;

	}

	public static String addHourReturnShortDate(int hour) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.HOUR, hour);
		date = gregorianCalendar.getTime();
		return getDate2String(DATEFORMATSHORT, date);

	}

	public static Date getNextHourDate(int hour) {

		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.HOUR, hour);
		date = gregorianCalendar.getTime();
		String longDateTime = getDate2LongString(date);
		String NextDateHour = longDateTime.subSequence(0, 14) + "00:00";
		return getString2LongDate(NextDateHour);

	}

	public static Date getNextMinuteDate(int minute) {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.add(GregorianCalendar.MINUTE, minute);
		date = gregorianCalendar.getTime();
		String longDateTime = getDate2LongString(date);
		String NextDateMinute = longDateTime.subSequence(0, 17) + "00";
		return getString2LongDate(NextDateMinute);

	}

	public static boolean inDateRange(Date startDate, Date endDate, Date date) {

		if (startDate == null || endDate == null || date == null)
			return false;

		// 是否 startDate<=date<=endDate
		return (startDate.before(date) || startDate.equals(date))
				&& (endDate.after(date) || endDate.equals(date));
	}

	public static void main(String[] args) {
//		System.out.println(DateUtils.getString2YmdDate("20120101").getTime());
//		System.out.println(DateUtils.getCurrentTimeMillis());
//		System.out.println(DateUtils.getCurrentFormatDate(DATEFORMATLONG));
		//System.out.println(DateUtils.getBeforeDay("20140301"));
		System.out.println(DateUtils.getCurTimeString());

	}
}