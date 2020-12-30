package rubic.test.xq.gps.dbscan;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class DateUtil {

	public static String STRING_FORMAT_YMD = "yyyy-MM-dd";
	
	
	/**
	 * 将日期转换成字符串格式
	 * @Title: parseToString 
	 * @Description: 
	 * @param dateValue
	 * @param stringFormat
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String parseToString(Date dateValue, String stringFormat) {

		if (null == dateValue) {
			return "";
		}
		if (null == stringFormat || "".equals(stringFormat.trim())) {
			stringFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(stringFormat);
		return sdf.format(dateValue);
	}

	/**
	 * 将字符串日期转换成日期格式
	 * @Title: parseToDate 
	 * @Description: 
	 * @param stringValue
	 * @param dateFormat
	 * @return    设定文件 
	 * @return Date    返回类型 
	 * @throws
	 */
	public static Date parseToDate(String stringValue, String dateFormat) {
		if (null == stringValue) {
			return null;
		}
		if (null == dateFormat || "".equals(dateFormat.trim())) {
			dateFormat = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(stringValue);
		} catch (ParseException e) {
			date = null;
//			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取某个日期的英文星期
	 * @Title: toWeekDay 
	 * @Description: 
	 * @param date
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public final static String toWeekDay(Date date) {
		String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	/**
	 * 获取某个日期的中文星期
	 * @Title: toChineseWeekDay 
	 * @Description: 
	 * @param date
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public final static String toChineseWeekDay(Date date) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}
	
	/**
	 * 获取某个日期的星期数
	 * @Title: toWeekNum 
	 * @Description: 
	 * @param date
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public final static int toWeekNum(Date date) {
		int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0) {
			w = 0;
		}
		return weekDays[w];
	}

	/**
	 * 获取当天的星期数
	 * @Title: getWeekDay 
	 * @Description: 
	 * @return    设定文件 
	 * @return int    返回类型 
	 * @throws
	 */
	public final static int getWeekDay() {
		Calendar calendar = Calendar.getInstance();
		int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if(w <= 0) {
			w = 7;
		}
		return w;
	}

	/**
	 * 获取当前日期前后多少天的日期字符串，负数就是今天之前的日期，整数就是今天之后的日期，0就是今天的日期
	 * @Title: getDesignDate 
	 * @Description: 
	 * @param dateFormat
	 * @param days
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getDesignDate(String dateFormat, int days) {

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		// 增加天数
		calendar.add(Calendar.DATE, days);

		// 获取时间戳
		date = calendar.getTime();

		if (null == dateFormat || "".equals(dateFormat.trim())) {
			dateFormat = "yyyy-MM-dd";
		}

		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}
	
	public static String getDesignDate(String day, String dateFormat, int days) {
		
		Date date = parseToDate(day + " 00:00:00", null);
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		
		// 增加天数
		calendar.add(Calendar.DATE, days);
		
		// 获取时间戳
		date = calendar.getTime();
		
		if (null == dateFormat || "".equals(dateFormat.trim())) {
			dateFormat = "yyyy-MM-dd";
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}

	/**
	 * 获取今天0点0分0秒的时间戳
	 * @Title: getTodayZeroPointTimestamp 
	 * @Description: 
	 * @return    设定文件 
	 * @return long    返回类型 
	 * @throws
	 */
	public static long getTodayZeroPointTimestamp() {
		
		Date todayZeroPoint = parseToDate(getDesignDate(null, 0) + " 00:00:00", null);
		return todayZeroPoint.getTime();
	}
	
	/**
	 * 计算两个日期之间的天数
	 * @Title: getDateDiffDays 
	 * @Description: 
	 * @param date1			第一个日期，格式：yyyy-MM-dd
	 * @param date2			第二个日期，格式：yyyy-MM-dd
	 * @param inStart		是否从第一个日期开始，true：是，false：否
	 * @return    设定文件 
	 * @return long    返回类型 
	 * @throws
	 */
	public static long getDateDiffDays(String date1, String date2, boolean inStart) {
		
		
		Date one = DateUtil.parseToDate(date1, "yyyy-MM-dd");
		Date two = DateUtil.parseToDate(date2, "yyyy-MM-dd");
		long difference = Math.abs(one.getTime() - two.getTime()) / 86400000;
		
		if(inStart) {
			difference += 1;
		}
		return difference;
	}

	public static long getDateDiffDays(Long date1, Long date2, boolean inStart) {
		
		Date one = new Date(date1);
		Date two = new Date(date2);
		long difference = Math.abs(one.getTime() - two.getTime()) / 86400000;
		
		if(inStart) {
			difference += 1;
		}
		return difference;
	}
	
	
	public static int getLastDaysOfMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDayOfMonth = calendar.getTime();
		
		int days = Integer.parseInt(parseToString(lastDayOfMonth, "dd"));
		return days;
	}
	
	/**
	 * 将秒数转成时分秒
	 * @Title: secondsParseToHHmmss 
	 * @Description: 
	 * @param duration
	 * @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String secondsParseToHHmmss(int duration){
		String time = "";
		
		//小于60秒
		if(duration < 60){
			time = "00:00:" + duration;
		}else if(duration >= 60){
			//小于一个小时
			if(duration < 3600){
				
				int m = duration / 60;
				int s = duration % 60;
				if(0 == s){
					if(m < 10){
						time = "00:0" + m + ":00";
					}else {
						time = "00:" + m + ":00";
					}
				}else {
					if(s < 10){
						if(m < 10){
							time = "00:0" + m + ":0" + s;
						}else {
							time = "00:" + m + ":0" + s;
						}
					}else {
						if(m < 10){
							time = "00:0" + m + ":" + s;
						}else {
							time = "00:" + m + ":" + s;
						}
					}
				}
				
			}
			//大于或等于一个小时
			else {
				
				int h = duration / 3600;
				int f = duration % 3600;
				
				if(f < 60){
					if(0 == f){
						if(h < 10){
							time = "0" + h + ":00" + ":00";
						}else {
							time = h + ":00" + ":00";
						}
					}else {
						if(f < 10){
							if(h < 10){
								time = "0" + h + ":00:0" + f;
							}else {
								time = h + ":00:0" + f;
							}
						}else {
							if(h < 10){
								time = "0" + h + ":00:" + f;
							}else {
								time = h + ":00:" + f;
							}
						}
					}
				}else {
					
					int m = f / 60;
					int s = f % 60;
					
					if(0 == s){
						if(10 > m){
							if(h < 10){
								time = "0" + h + ":0" + m + ":00";
							}else {
								time = h + ":0" + m + ":00";
							}
						}else {
							if(h < 10){
								time = "0" + h + ":" + m + ":00";
							}else {
								time = h + ":" + m + ":00";
							}
						}
					}else if(s < 10){
						if(m <10){
							if(h < 10){
								time = "0" + h + ":0" + m + ":0" + s;
							}else {
								time = h + ":0" + m + ":0" + s;
							}
						}else {
							if(h < 10){
								time = "0" + h + ":" + m + ":0" + s;
							}else {
								time = h + ":" + m + ":0" + s;
							}
						}
					}else {
						if(m <10){
							if(h < 10){
								time = "0" + h + ":0" + m + ":" + s;
							}else {
								time = h + ":0" + m + ":" + s;
							}
						}else {
							if(h < 10){
								time = "0" + h + ":" + m + ":" + s;
							}else {
								time = h + ":" + m + ":" + s;
							}
						}
					}
				}
			}
		}
		
		return time;
	}
	
	/**
	 * change 08:00:00 to timestamp
	 * @param time
	 * @return
	 */
	public static long getTimeLongByString(String time){
		String date = DateUtil.parseToString(new Date(), "yyyy-MM-dd") + " " + time;
		long timeStamp = DateUtil.parseToDate(date, "yyyy-MM-dd HH:mm:ss").getTime();
		return timeStamp;
	}
	
	/**
	 * 将过期的时间平移到当天
	 * @param orderExpectedTimestamp
	 * @return
	 */
	public static long expectedTimestampToTodayTimestamp(long orderExpectedTimestamp) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String previousTimeStr = format.format(orderExpectedTimestamp);
		return getTimeLongByString(previousTimeStr);
	}

	/**
	 * 将过期的时间平移到指定日期
	 * @param orderExpectedTimestamp
	 * @return
	 */
	public static long expectedTimestampToDesignDayTimestamp(long orderExpectedTimestamp, int days) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		String time = format.format(orderExpectedTimestamp);
		
		String date = getDesignDate(null, days) + " " + time;
		long timeStamp = DateUtil.parseToDate(date, "yyyy-MM-dd HH:mm:ss").getTime();
		
		return timeStamp;
	}
	public static List<Map<String, Object>> getWeekOfMonth(String date){
		
		// 返回结果
		List<Map<String, Object>> result = new ArrayList<>();
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date1 = dateFormat.parse(date);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date1);
			int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			for (int i = 1; i <= days; i++) {
				DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
				Date date2 = dateFormat1.parse(date + "-" + i);
				calendar.clear();
				calendar.setTime(date2);
				int k = new Integer(calendar.get(Calendar.DAY_OF_WEEK));
				long firstDayOfWeek = 0;
				long lastDayOfWeek = 0;
				if (k == 1) {// 若当天是周日
					if (i - 6 <= 1) {
						firstDayOfWeek = parseToDate(date + "-" + 1 + " 00:00:00", null).getTime();
					} else {
						firstDayOfWeek = parseToDate(date + "-" + (i - 6) + " 00:00:00", null).getTime();
					}
					lastDayOfWeek = parseToDate(date + "-" + i + " 23:59:59", null).getTime();

				}
				if (k != 1 && i == days) {// 若是本月最后一天，且不是周日
					firstDayOfWeek = parseToDate(date + "-" + (i - k + 2) + " 00:00:00", null).getTime();
					lastDayOfWeek = parseToDate(date + "-" + i + " 23:59:59", null).getTime();
				}
				if (firstDayOfWeek != 0 && lastDayOfWeek != 0) {
					// 当前月份每周的起始时间和结束时间
					Map<String, Object> params = new HashMap<>();
					params.put("startTime", firstDayOfWeek);
					params.put("endTime", lastDayOfWeek);
					result.add(params);
				}
			}
		} catch (ParseException e) {
			System.out.println("获取时间失败");
		}
		return result;
	}

	/**
	 * 获取某个日期的小时数
	 * @param date
	 * @return
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取某个日期的分钟数
	 * @param date
	 * @return
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * 获取某个日期的秒数
	 * @param date
	 * @return
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.get(Calendar.SECOND);
	}
	
	public static long getDateDiffMinites(String date1, String date2, boolean inStart) {
		
		Date one = DateUtil.parseToDate(date1, "yyyy-MM-dd HH:mm");
		Date two = DateUtil.parseToDate(date2, "yyyy-MM-dd HH:mm");
		long difference = Math.abs(one.getTime() - two.getTime())/(1000*60);  //精确到分钟
		
		if(inStart) {
			difference += 1;
		}
		return difference;
	}

	/**
	 * 获取指定日期当月的最后一天
	 * @param dateStr
	 * @param format
	 * @return
	*/
	public static String getLastDayOfGivenMonth(String strMonth) {

		try{

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date =  formatter.parse(strMonth+"-01 23:59:59");

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Date lastDayOfMonth = calendar.getTime();

			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastDayOfMonth);

		}catch(Exception e){
			 e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取指定日期当月的第一天
	 * @param dateStr
	 * @param format
	 * @return
	*/
	public static String getFirstDayOfGivenMonth(String strMonth){

		    try {

		    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	Date date = formatter.parse(strMonth+"-01 00:00:00");
		    	Calendar calendar = Calendar.getInstance();
		    	calendar.setTime(date);
		    	calendar.set(Calendar.DAY_OF_MONTH,1);
		    	calendar.add(Calendar.MONTH, 0);
		    	Date firstDayOfMonth = calendar.getTime();

			    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(firstDayOfMonth);

		     } catch (ParseException e) {
		    	 e.printStackTrace();
		    }

		    return null;
	}

	public static List<String> initDaysList(String searchStartTime,String searchEndTime){

		List<String> daysList = new ArrayList<String>();

		Long diffDays = DateUtil.getDateDiffDays(searchStartTime,searchEndTime,true);

		for(int i=diffDays.intValue()-1;i>=0;i--){
			daysList.add(DateUtil.getDesignDate(searchEndTime,null, -i));
		}

		return daysList;
	}

	/**
	 * 计算两个时间戳时间的天数差
	 */
	public static int calcSubDays(long startTime, long endTime) {
		if (endTime <= startTime) {
			return 0;
		}
		long subTime = endTime - startTime;
		int subDays = (int) (subTime / (24 * 60 * 60 * 1000));
		return subDays;
	}

	public static List<String> getPerDay(Long startTime, Long endTime) {
		int num = calcSubDays(startTime, endTime);
		return getDatePeriod(new Date(endTime), num + 1);
	}

	public static List<String> getDatePeriod(Date date, int beforeDays){
		List<String> datePeriodList = new ArrayList<String>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int inputDayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		for(int i=beforeDays-1;i>=0;i--){
			cal.set(Calendar.DAY_OF_YEAR , inputDayOfYear-i);
			datePeriodList.add(dateFormat.format(cal.getTime()));
		}
		return datePeriodList;
	}

	public static Long userDefineDateConvert(String dateStr) {
		Date date = DateUtil.parseToDate(dateStr, "yyyy/MM/dd HH:mm:ss");
		if (date != null) {
			return date.getTime();
		}
		date = DateUtil.parseToDate(dateStr, "yyyy/MM/dd HH:mm");
		if (date != null) {
			return date.getTime();
		}
		date = DateUtil.parseToDate(dateStr, "yyyy/MM/dd");
		if (date != null) {
			return date.getTime();
		}
		date = DateUtil.parseToDate(dateStr, "yyyy-MM-dd HH:mm:ss");
		if (date != null) {
			return date.getTime();
		}
		date = DateUtil.parseToDate(dateStr, "yyyy-MM-dd HH:mm");
		if (date != null) {
			return date.getTime();
		}
		date = DateUtil.parseToDate(dateStr, "yyyy-MM-dd");
		if (date != null) {
			return date.getTime();
		}
		return null;
	}
	
	public static void main(String[] agrs){
		//当前日期前10天的日期
		/*List<String> dateList= getDatePeriod(new Date(), 10);
		for(String date:dateList){
			System.out.println(date);
		}

		System.out.println(9/5);

		int num = calcSubDays(1593532800000L, 1594137599000L);
		System.out.println(num);*/

		long ll = 1559785312800L + 3 * 60 * 60 * 1000;
		System.out.println(ll);

	}
}
