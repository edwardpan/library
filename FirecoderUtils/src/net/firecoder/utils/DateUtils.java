package net.firecoder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ����ת��������
 * @author �˳�
 *
 */
public class DateUtils {
	private static final transient Logger log = LoggerFactory
			.getLogger(DateUtils.class);

	public static final String FORMAT_DATE_YYYYMMDD_CHAR = "yyyy��MM��dd��";
	public static final String FORMAT_DATE_YYYYMMDDHHMISS_CHAR = "yyyy��MM��dd�� HH:mm:ss";
	public static final String FORMAT_DATE_YYYYMMDD_ACROSS = "yyyy-MM-dd";
	public static final String FORMAT_DATE_YYYYMMDD_DIAGONAL = "yyyy/MM/dd";
	public static final String FORMAT_DATE_YYYYMMDDHHMISS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * ���ַ������ڰ���ʽת��Ϊ���ڶ���
	 * @param dateStr �����ַ���
	 * @param formatStr ת����ʽ����DateTimeUtil�еľ�̬�������塣
	 * 			Ĭ�ϸ�ʽΪyyyy��MM��dd�ա�
	 * @return ���ڶ���
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
			log.error("parseDate����" + dateStr + "[" + formatStr);
			return null;
		}
	}

	/**
	 * �����ڰ���ʽת��Ϊ�ַ�������
	 * @param date ����
	 * @param formatStr ת����ʽ����DateTimeUtil�еľ�̬�������塣
	 * 			Ĭ�ϸ�ʽΪyyyy��MM��dd�ա�
	 * @return �ַ�������
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
