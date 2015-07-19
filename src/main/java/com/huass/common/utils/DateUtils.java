
package com.huass.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;


public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	public static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy-MM-dd HH:mm:ss.s" };
	
	private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

	public static String[] WEEK_DAYS = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
	
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
	
	 public static Date getMondayOfThisWeek(Date date) 
	 {
		Calendar c = Calendar.getInstance();
		if(date != null)
		{
			c.setTime(date);
		}
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
		{
			day_of_week = 7;
		}
		c.add(Calendar.DATE, -day_of_week + 1);
		return c.getTime();
	 }
	 
	 
	public static Date getFridayOfThisWeek(Date date)
	{
		Calendar c = Calendar.getInstance();
		if(date != null)
		{
			c.setTime(date);
		}
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0)
			day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 5);
		return c.getTime();
	}
	
	
	public static String getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
        {
        	w = 0;
        }
        return weekDays[w];
    }

	
    
	public static Date getDateStart(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd")+" 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date getDateEnd(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd") +" 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Date getDate(Date date) {
		if(date==null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date= sdf.parse(formatDate(date, "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(getDate("yyyy年MM月dd日 E"));
	}
}
