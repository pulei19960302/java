package com.qim.loan.util.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类名:DateUtil 描述:DateUtil 创建者:冯子文 创建时间: Dec 8, 2017 10:10:31 AM 更新者:冯子文 更新时间:
 * Dec 8, 2017 10:10:31 AM
 */
public class DateUtil {

	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static String currentStr() {
		return formatDateTime(new Date());
	}

	public static Integer getRealWeek() {
		return formatDate(getFrontDay(new Date(),7));	
	}
	// 周华
	public static String getWeek() {
		String str = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.roll(Calendar.DATE, -7);//日期回滚7天
        str=sdf.format(lastDate.getTime());
        return str;
	}
	
	
	// 返回某个日期前几天的日期
	public static Date getFrontDay(Date date, int i) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - i);
		return cal.getTime();
	}

	public static Integer formatCurrentDate() {
		return formatDate(new Date());
	}

	public static Boolean isSameMonth(Integer startDate, Integer endDate) {
		startDate = startDate / 100;
		endDate = endDate / 100;
		if (startDate == endDate || startDate.equals(endDate))
			return true;
		return false;
	}

	public static Integer subtractWeek(Integer today) {
		return subtract(today, 7);
	}

	public static Integer subtract(Integer today, Integer span) {
		Integer result = today % 100;
		result = result - span;
		if (result <= 0)
			return Integer.valueOf(today / 10) * 10 + 1;
		if (result < 10)
			return Integer.valueOf(today / 100) * 100 + result;
		else
			return Integer.valueOf(today / 100) * 100 + result;
	}

	public static Integer formatDate(final Date date) {
		return format(date)[0];
	}

	public static Integer formatCurrentTime() {
		return formatTime(new Date());
	}

	public static Integer formatTime(final Date date) {
		return format(date)[1];
	}

	public static Integer[] format(final Date date) {
		String result = formatDateTime(date == null ? new Date() : date);
		if (StringUtil.isNull(result))
			return null;
		return IntegerUtil.toArray(result.split(" "));
	}

	public static String formatNow(String format) {
		return formatDateTime(new Date(), format);
	}

	private static String formatDateTime(Object... args) {
		Object str = "";
		String format = "";
		switch (args.length) {
		case 0:
			return null;
		case 1:
			str = args[0];
			format = "yyyyMMdd HHmmssSSS";
			break;
		case 2:
			str = args[0];
			format = String.valueOf(args[1]);
			break;
		}
		DateFormat fmt = new SimpleDateFormat(format);
		try {
			return fmt.format(str);
		} catch (Exception e) {
			MessageUtil.error(logger, "将日期(" + str + ")转换成指定格式(" + format + ")的字符串时", e);
			return null;
		}
	}

	public static Date stringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try {
			return sdf.parse(dateString);
		} catch (Exception e) {
			MessageUtil.error(logger, "将字符串(" + dateString + ")转换成时间类型失败", e);
			return null;
		}
	}

	public static void main(String... args) {
		 System.out.println(subtract(20171005, 7));
	}

}
