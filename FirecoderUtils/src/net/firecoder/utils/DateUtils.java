package net.firecoder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期转换处理类
 * @author 潘超
 *
 */
public class DateUtils {
	private static final transient Logger log = LoggerFactory
			.getLogger(DateUtils.class);

	public static final String FORMAT_DATE_YYYYMMDD_CHAR = "yyyy年MM月dd日";
	public static final String FORMAT_DATE_YYYYMMDDHHMISS_CHAR = "yyyy年MM月dd日 HH:mm:ss";
	public static final String FORMAT_DATE_YYYYMMDD_ACROSS = "yyyy-MM-dd";
	public static final String FORMAT_DATE_YYYYMMDD_DIAGONAL = "yyyy/MM/dd";
	public static final String FORMAT_DATE_YYYYMMDDHHMISS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 将字符串日期按格式转换为日期对象
	 * @param dateStr 日期字符串
	 * @param formatStr 转换格式，由DateTimeUtil中的静态变量定义。
	 * 			默认格式为yyyy年MM月dd日。
	 * @return 日期对象
	 */
	public static Date parseDate(String dateStr, String formatStr) {
		if (dateStr == null || dateStr.equals("")) {
			return null;
		}
		if (formatStr == null || formatStr.equals("")) {
			formatStr = FORMAT_DATE_YYYYMMDD_CHAR;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			log.error("parseDate出错：" + dateStr + "[" + formatStr);
			return null;
		}
	}

	/**
	 * 将日期按格式转换为字符串日期
	 * @param date 日期
	 * @param formatStr 转换格式，由DateTimeUtil中的静态变量定义。
	 * 			默认格式为yyyy年MM月dd日。
	 * @return 字符串日期
	 */
	public static String convertDate(Date date, String formatStr) {
		if (date == null) {
			return "";
		}
		if (formatStr == null || formatStr.equals("")) {
			formatStr = FORMAT_DATE_YYYYMMDD_CHAR;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		return format.format(date);
	}
}
