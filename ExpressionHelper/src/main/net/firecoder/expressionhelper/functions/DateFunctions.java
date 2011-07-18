package net.firecoder.expressionhelper.functions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>自定义的时间处理函数</p>
 *
 * @author 潘超
 * @date 2011-3-23 上午09:13:20
 */
public class DateFunctions {
	private Logger logger = LoggerFactory.getLogger(DateFunctions.class);
	
	private List<String> dateFormatList = new ArrayList<String>();
	
	public DateFunctions(){
		dateFormatList.add("yyyy-MM-dd");
		dateFormatList.add("yyyy-MM-dd HH:mm:ss");
		dateFormatList.add("yyyy-MM-dd HH:mm:ss.SSS");
		dateFormatList.add("yyyy-MM-dd HH:mm:ss:SSS");
		dateFormatList.add("yyyy-M-d");
		dateFormatList.add("yyyy-M-d HH:mm:ss");
		dateFormatList.add("yyyy-M-d HH:mm:ss.SSS");
		dateFormatList.add("yyyy-M-d HH:mm:ss:SSS");
		dateFormatList.add("yyyy/MM/dd");
		dateFormatList.add("yyyy/MM/dd HH:mm:ss");
		dateFormatList.add("yyyy/MM/dd HH:mm:ss.SSS");
		dateFormatList.add("yyyy/MM/dd HH:mm:ss:SSS");
		dateFormatList.add("yyyy/M/d");
		dateFormatList.add("yyyy/M/d HH:mm:ss");
		dateFormatList.add("yyyy/M/d HH:mm:ss.SSS");
		dateFormatList.add("yyyy/M/d HH:mm:ss:SSS");
		dateFormatList.add("yyyyMMdd");
		dateFormatList.add("yyyyMMdd HH:mm:ss");
		dateFormatList.add("yyyyMMdd HH:mm:ss.SSS");
		dateFormatList.add("yyyyMMdd HH:mm:ss:SSS");
		dateFormatList.add("yyyyMd");
		dateFormatList.add("yyyyMd HH:mm:ss");
		dateFormatList.add("yyyyMd HH:mm:ss.SSS");
		dateFormatList.add("yyyyMd HH:mm:ss:SSS");
		dateFormatList.add("yyyy-MM-dd-HH:mm:ss");
		dateFormatList.add("yyyy-MM-dd-HH:mm:ss.SSS");
		dateFormatList.add("yyyy-MM-dd-HH:mm:ss:SSS");
		dateFormatList.add("yyyy-MM-dd-HH-mm-ss");
		dateFormatList.add("yyyy-MM-dd-HH-mm-ss.SSS");
		dateFormatList.add("yyyy-MM-dd-HH-mm-ss:SSS");
		dateFormatList.add("yyyy-M-d-HH:mm:ss");
		dateFormatList.add("yyyy-M-d-HH:mm:ss.SSS");
		dateFormatList.add("yyyy-M-d-HH:mm:ss:SSS");
		dateFormatList.add("yyyy-M-d-HH-mm-ss");
		dateFormatList.add("yyyy-M-d-HH-mm-ss.SSS");
		dateFormatList.add("yyyy-M-d-HH-mm-ss:SSS");
		dateFormatList.add("yyyyMMddHH:mm:ss");
		dateFormatList.add("yyyyMMddHH:mm:ss.SSS");
		dateFormatList.add("yyyyMMddHH:mm:ss:SSS");
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：日期格式变换</p>
	 * @param dateStr 原日期字符串
	 * @param oldFormatStr 旧的格式
	 * @param newFormatStr 新的格式
	 * @return
	 */
	public String convertDateFormat(String dateStr, String oldFormatStr, String newFormatStr) {
		SimpleDateFormat format = new SimpleDateFormat(oldFormatStr);
		Date date = null;
		String newDateStr = dateStr;
		if (dateStr != null && !dateStr.trim().equals("")) {
			try{
				logger.debug("开始识别旧日期时间格式");
				date = format.parse(String.valueOf(dateStr));
				format = new SimpleDateFormat(newFormatStr);
				logger.debug("开始将日期时间对象转换为新格式");
				newDateStr = format.format(date);
			} catch (ParseException ex){
				logger.warn("日期格式转换函数执行失败：" + ex.getMessage() + "。格式：" + oldFormatStr + " 到 " + newFormatStr);
			}
		}
		return newDateStr;
	}
	
	/**
	 * <p>IKExpression表达式解析器的函数：将字符串的日期时间解析为对象</p>
	 * @param dateStr 需要识别并解析的日期时间字符串
	 * @return 日期时间对象java.util.Date
	 */
	public Date convertStrToDate(String dateStr){
		Date date = null;
		SimpleDateFormat format = null;
		
		logger.debug("识别日期时间字符串格式。");
		String formatStr = readDateFormat(dateStr);
		logger.debug("识别日期时间字符串格式为：" + formatStr);
		try {
			format = new SimpleDateFormat(formatStr);
			date = format.parse(dateStr);
			logger.debug("日期时间字符串解析成功。字符串：" + dateStr + "；格式：" + format);
		} catch (ParseException ex){
			logger.warn("日期时间字符串解析失败：" + ex.getMessage());
		}
		
		return date;
	}
	
	/**
	 * <p>查找匹配日期时间字符串的格式</p>
	 * @param dateStr 需要识别/查找的日期时间字符串
	 * @return 格式，如：yyyy-MM-dd HH:mm:ss
	 */
	public String readDateFormat(String dateStr){
		String realFormatStr = "yyyy-MM-dd HH:mm:ss";
		boolean success = false;
		SimpleDateFormat format = null;
		for (String formatStr : dateFormatList) {
			logger.debug("匹配日期时间格式：" + formatStr);
			try {
				format = new SimpleDateFormat(formatStr);
				format.parse(dateStr);
				success = true;
			} catch (ParseException ex){
				success = false;
			}
			if (success){
				logger.debug("日期时间格式匹配成功：" + formatStr);
				realFormatStr = formatStr;
				break;
			}
		}
		return realFormatStr;
	}
	
	/**
	 * IKExpression表达式解析器的函数：日期转换为字符串函数
	 * @param date 日期对象
	 * @param format 需要转换为的字符串格式，参照SimpleDateFormat类中关于格式的说明
	 * @return
	 */
	public String convertDateToString(Date date, String format) {
		if (date == null) {
			logger.debug("日期时间对象为空，未转换");
			return "";
		}
		logger.debug("开始转换日期对象，格式：" + format);
		SimpleDateFormat dateFormater = new SimpleDateFormat(format);
		return dateFormater.format(date);
	}
}
