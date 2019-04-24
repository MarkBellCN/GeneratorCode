package com.ytkj.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间格式转换<工具类>
 * @since Apr 10, 2016 5:28:32 PM
 */
public class DateUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4701711362498418350L;

	/**
	 * 得到一个<code>Log</code>的实例，用于生成日志信息。
	 */
	private final static Log log = LogFactory.getLog(DateUtil.class);

	public DateUtil() {
	}

	/**
	 * 执行型别转换:字符串表示的日期--->>日期型日期
	 * <li>1 格式为 yyyy-MM-dd
	 * <li>2 格式为 yyyy-MM-dd HH:mm:ss
	 * <li>3 格式为 yyyy/MM/dd HH:mm:ss
	 * <li>4 格式为 yy-MM-dd HH:mm:ss
	 * <li>5 格式为 yyyy年MM月dd日 HH时mm分ss秒
	 * <li>6 格式为 dd/MM/yyyy
	 * <li>7 格式为 yyyy年MM月dd日 HH时mm分
	 * <li>8 格式为 HH:mm:ss
	 * <li>默认格式为 yy年MM月dd日 HH时mm分ss秒
	 * 
	 * @param style
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate(final int style, String strDate) {

		SimpleDateFormat format = null;

		if (null == strDate || "".equals(strDate) || "null".equals(strDate)) {

			return null;
		} else {

			switch (style) {
			case 1:
				format = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case 2:
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case 3:
				format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				break;
			case 4:
				format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
				break;
			case 5:
				format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				break;
			case 6:
				format = new SimpleDateFormat("dd/MM/yyyy");
				break;
			case 7:
				format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
				break;
			case 8:
				format = new SimpleDateFormat("HH:mm:ss");
				break;
			default:
				format = new SimpleDateFormat("yy年MM月dd日 HH时mm分ss秒");
				break;
			}
			try {
				return format.parse(strDate);
			} catch (ParseException e) {
				log.error("字符串到Date对象的解析时发生了异常!", e);
				return null;
			}
		}
	}

	/**
	 * 执行型别转换:日期型日期--->>字符串表示的日期
	 * <li>1 格式为 yyyy-MM-dd
	 * <li>2 格式为 yyyy-MM-dd HH:mm:ss
	 * <li>3 格式为 yyyy/MM/dd HH:mm:ss
	 * <li>4 格式为 yy-MM-dd HH:mm:ss
	 * <li>5 格式为 yyyy年MM月dd日 HH时mm分ss秒
	 * <li>6 格式为 yyyy年MM月dd日
	 * <li>7 格式为 yy-MM-dd
	 * <li>8 格式为 HH:mm:ss
	 * <li>9 格式为 yyyy年MM月dd日 HH时mm分
	 * <li>10 格式为 yyMMddHHmmss
	 * <li>默认格式为 yy年MM月dd日 HH时mm分ss秒
	 * 
	 * @param style
	 * @param date
	 * @return
	 */
	public static String dateToString(final int style, Date date) {

		SimpleDateFormat format = null;

		if (null == date || "null".equals(date) || "".equals(date)) {

			return "";
		} else {

			switch (style) {
			case 1:
				format = new SimpleDateFormat("yyyy-MM-dd");
				break;
			case 2:
				format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				break;
			case 3:
				format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				break;
			case 4:
				format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
				break;
			case 5:
				format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
				break;
			case 6:
				format = new SimpleDateFormat("yyyy年MM月dd日");
				break;
			case 7:
				format = new SimpleDateFormat("yy-MM-dd");
				break;
			case 8:
				format = new SimpleDateFormat("HH:mm:ss");
				break;
			case 9:
				format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
				break;
			case 10:
				format = new SimpleDateFormat("yyyyMMddHHmmss");
				break;
			default:
				format = new SimpleDateFormat("yy年MM月dd日 HH时mm分ss秒");
				break;
			}
			try {
				return format.format(date);
			} catch (NumberFormatException e) {
				log.error("Date对象到字符串的解析时发生了异常!", e);
				return "";
			}
		}
	}

	/**
	 * 执行型别转换:字符串表示的日期--->>日期型日期
	 * 
	 * @param format
	 * @param strDate
	 * @return
	 */
	public static Date stringToDate(final String format, String strDate) {

		SimpleDateFormat dateFormat = null;

		if (null == strDate || "".equals(strDate) || "null".equals(strDate)) {

			return null;
		} else {

			dateFormat = new SimpleDateFormat(format);
			try {
				return dateFormat.parse(strDate);
			} catch (ParseException e) {
				log.error("字符串到Date对象的解析时发生了异常!", e);
				return null;
			}
		}
	}

	/**
	 * 执行型别转换:日期型日期--->>字符串表示的日期
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static String dateToString(final String format, Date date) {

		SimpleDateFormat dateFormat = null;

		if (null == date || "".equals(date) || "null".equals(date)) {

			return "";
		} else {

			dateFormat = new SimpleDateFormat(format);
			try {
				return dateFormat.format(date);
			} catch (NumberFormatException nfe) {
				log.error("Date对象到字符串的解析时发生了异常!", nfe);
				return "";
			}
		}
	}


	/**
	 * 获取当前日期的long值对应的字符串
	 * 
	 * @return 当前日期的long值对应的字符串
	 */
	public synchronized static String getTimeStamp() {
		Date d = new Date();
		return String.valueOf(d.getTime());
	}

	/**
	 * 获取当前年份
	 */
	public static final int getCurrentYear() {

		return new GregorianCalendar().get(Calendar.YEAR);
	}

	public static String getCurrentDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String strDate = formatter.format(new Date());
		return strDate.substring(4, 8);
	}

	public static String getCurrentWeek() {
		int week = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
		if (week == 1) {
			return "日";
		} else if (week == 2) {
			return "一";
		} else if (week == 3) {
			return "二";
		} else if (week == 4) {
			return "三";
		} else if (week == 5) {
			return "四";
		} else if (week == 6) {
			return "五";
		} else {
			return "六";
		}
	}

	public static String getWeek(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		if (week == 1) {
			return "星期日";
		} else if (week == 2) {
			return "星期一";
		} else if (week == 3) {
			return "星期二";
		} else if (week == 4) {
			return "星期三";
		} else if (week == 5) {
			return "星期四";
		} else if (week == 6) {
			return "星期五";
		} else {
			return "星期六";
		}
	}

	/**
	 * 得到指定时间在指定天数之后的时间(传入负整数表示之前)
	 * 
	 * @param date
	 * @param afterDay
	 * @return
	 */
	public static Date getDateAfter(Date date, int afterDay) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, afterDay);
		return calendar.getTime();
	}

	public static void main(String[] args) {
		String strDate  = dateToString("yyyy-MM-dd HH:mm", new Date());
		System.out.println(strDate);
		Date date = getDateAfterMinute(stringToDate("yyyy-MM-dd HH:mm", strDate),1);
		System.out.println(dateToString("yyyy-MM-dd HH:mm",date));
		String hourMinter  = dateToString("HH:mm", new Date());
		System.out.println(hourMinter);
	}
	
	/**
	 * 得到指定时间在指定小时数之后的时间(传入负整数表示之前)
	 * 
	 * @param date
	 * @param afterHour
	 * @return
	 */
	public static Date getDateAfterHour(Date date, int afterHour) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, afterHour);
		return calendar.getTime();
	}

	/**
	 * 得到指定时间在指定分钟数之后的时间(传入负整数表示之前)
	 * 
	 * @param date
	 * @param afterMinute
	 * @return
	 */
	public static Date getDateAfterMinute(Date date, int afterMinute) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, afterMinute);
		return calendar.getTime();
	}
	
	


	/**
	 * 获得一个时间(Date对象)在当天的起始时刻的时间(Date对象)
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDayStart(Date date) {

		String dateStr = dateToString("yyyy-MM-dd", date);
		date = stringToDate("yyyy-MM-dd", dateStr);
		return date;
	}

	/**
	 * 当天的结束时间
	 * @return
	 */
	public static Date endOfTodDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		Date date=calendar.getTime();
		return date;
	}
	
	/**
	 * 判断两个时间(Date对象)精确到天数以后是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean dateEquals(Date date1, Date date2) {
		String d1 = dateToString("yyyy-MM-dd", date1);
		String d2 = dateToString("yyyy-MM-dd", date2);
		if (d1.equalsIgnoreCase(d2)) {
			return true;
		} else {
			return false;
		}
	}

	private static List parseWeek(final String str) {

		List weekList = new ArrayList(5);
		for (int i = 0; i < str.length(); i++) {
			String s = str.substring(i, i + 1);
			if (s.equalsIgnoreCase("7")) {
				s = "0";
			}
			weekList.add(s);
		}
		return weekList;
	}
	public static int getGapDays(String fromDate, String endDate) {

		long from = stringToDate(1,fromDate).getTime();
		long end = stringToDate(1,endDate).getTime();

		return (int) ((end - from) / (24 * 60 * 60 * 1000));
	}
	public static int getGapDays(Date fromDate, Date endDate) {

		final long from = fromDate.getTime();
		final long end = endDate.getTime();
		return (int) ((end - from) / (24 * 60 * 60 * 1000));
	}
	private static List parseWeekX(final String str) {

		List weekList = new ArrayList(5);
		for (int j = 0; j < 7; j++) {
			boolean isRepeat = false;
			for (int i = 0; i < str.length(); i++) {
				String s = str.substring(i, i + 1);
				if (s.equalsIgnoreCase("7")) {
					s = "0";
				}
				if (Integer.parseInt(s) == j) {
					isRepeat = true;
					break;
				}
			}
			if (isRepeat == false) {
				weekList.add(String.valueOf(j));
			}
		}
		return weekList;
	}
	public static Map<String,String> getRangeYear(){
		Map<String,String> years = new LinkedHashMap<String,String>();
		int y = Calendar.getInstance().get(Calendar.YEAR);
		for(int i=y-3;i<=y+3;i++){
			years.put(""+i, ""+i);
		}
		return years;
	}
	
	public static String dateToWeekDay(Date date){
		String weekDay = "";
		 Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		  switch (dayOfWeek) {
		  case 1:
			  weekDay = "星期日";
		   break;
		  case 2:
			  weekDay = "星期一";
		   break;
		  case 3:
			  weekDay = "星期二";
		   break;
		  case 4:
			  weekDay = "星期三";
		   break;
		  case 5:
			  weekDay = "星期四";
		   break;
		  case 6:
			  weekDay = "星期五";
		   break;
		  case 7:
			  weekDay = "星期六";
		   break;
		  }
		return weekDay;
	}
	
	/**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekDayByToday(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

	/**
	 * return date1-date2 minute
	 * @param date1
	 * @param date2
	 * @return
	 */
    public static int compareMinute(Date date1,Date date2){
		return (int) ((date1.getTime()-date2.getTime())/(1000*60));
	}
	
}
